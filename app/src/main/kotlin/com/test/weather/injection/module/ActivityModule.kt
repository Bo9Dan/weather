package com.test.weather.injection.module

import com.test.weather.ui.detail.DetailActivity
import com.test.weather.ui.main.MainActivity
import com.test.weather.ui.search.CitySearchActivity
import com.test.weather.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector()
    abstract fun searchActivity(): CitySearchActivity

//    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun detailActivity(): DetailActivity
}