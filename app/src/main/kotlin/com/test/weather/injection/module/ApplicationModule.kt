package com.test.weather.injection.module

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import com.test.weather.injection.component.ActivityComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [
ActivityComponent::class
])
class ApplicationModule {

    /*@Binds
    @Singleton
    internal abstract fun application(_app: App): Context*/

    @Provides
    @Singleton
    internal fun providesContext(_application: Application): Context = _application.applicationContext

    @Provides
    @Singleton
    internal fun provideContentResolver(_context: Context): ContentResolver = _context.contentResolver
}