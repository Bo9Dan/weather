package com.test.weather.injection

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.test.weather.injection.component.DaggerAppComponent
import com.test.weather.injection.module.Injectable
import com.test.weather.ui.App
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

class InjectionHelper {

    companion object {

        @JvmStatic
        fun init(_application: App) {
            DaggerAppComponent.builder()
                .application(_application)
                .build()
                .inject(_application)
            registerActivityInjector(_application)
        }

        @JvmStatic
        private fun registerActivityInjector(_application: App) {
            _application.registerActivityLifecycleCallbacks(object : SimpleActivityLifecycleCallbacks() {

                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    injectActivity(activity)
                    registerLifecycleCallback(activity)
                }

                override fun onActivityDestroyed(activity: Activity) {
                    unregisterFragmentLifecycleCallback(activity)
                }
            })
        }

        private fun injectActivity(_activity: Activity) {
            if (_activity is Injectable) {
                AndroidInjection.inject(_activity)
            }
        }

        private val LIFECYCLE_CALLBACK = object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
                if (f is Injectable) {
                    AndroidSupportInjection.inject(f)
                }
            }
        }

        private fun registerLifecycleCallback(_activity: Activity) {
            if (_activity is FragmentActivity) {
                _activity.getSupportFragmentManager()
                    .registerFragmentLifecycleCallbacks(LIFECYCLE_CALLBACK, true)
            }
        }

        private fun unregisterFragmentLifecycleCallback(_activity: Activity) {
            if (_activity is FragmentActivity) {
                _activity.getSupportFragmentManager()
                    .unregisterFragmentLifecycleCallbacks(LIFECYCLE_CALLBACK)
            }
        }

    }
}