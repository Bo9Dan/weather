package com.test.weather.injection.module

import androidx.lifecycle.ViewModel
import com.test.weather.ui.detail.DetailActivityViewModel
import com.test.weather.ui.main.MainActivityViewModel
import com.test.weather.ui.search.CitySearchActivityViewModel
import dagger.Binds
import dagger.Module

@Module(includes = [ActivityModule::class])
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindMain(_mainViewModel: MainActivityViewModel): ViewModel

    @Binds
    internal abstract fun bindSearch(_mainViewModel: CitySearchActivityViewModel): ViewModel

    @Binds
    internal abstract fun bindDetail(_mainViewModel: DetailActivityViewModel): ViewModel

}