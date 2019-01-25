package com.test.weather.injection.module

import android.content.Context
import dagger.Module
import com.test.weather.data.db.AppDatabase
import androidx.room.Room
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {

        return Room.databaseBuilder(context, AppDatabase::class.java, "WeatherData.db").build()
    }


}