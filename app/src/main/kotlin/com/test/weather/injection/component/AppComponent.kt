package com.test.weather.injection.component

import android.app.Application
import com.test.weather.injection.module.*
import com.test.weather.ui.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ViewModelModule::class, NetworkModule::class, DataBaseModule::class, ProviderModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(_application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(_app: App)

}