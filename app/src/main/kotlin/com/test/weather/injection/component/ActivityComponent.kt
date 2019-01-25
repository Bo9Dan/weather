package com.test.weather.injection.component

import com.test.weather.injection.module.ViewModelModule
import com.test.weather.injection.scopes.ActivityScope
import com.test.weather.ui.App
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ViewModelModule::class])
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ActivityComponent
    }

    fun inject(_app: App)

}