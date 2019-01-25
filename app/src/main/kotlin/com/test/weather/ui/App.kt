package com.test.weather.ui

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.test.weather.injection.ComponentHandler
import com.test.weather.injection.InjectionHelper
import dagger.Lazy
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    protected lateinit var dispatchingActivityInjector: Lazy<DispatchingAndroidInjector<Activity>>

    @Inject
    protected lateinit var dispatchingFragmentInjector: Lazy<DispatchingAndroidInjector<Fragment>>

    @Inject
    protected lateinit var componentHandler: Lazy<ComponentHandler>

    override fun onCreate() {
        super.onCreate()
        InjectionHelper.init(this)
        componentHandler.get().buildRequiredComponent()
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector.get()

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingFragmentInjector.get()
}