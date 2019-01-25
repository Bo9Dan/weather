package com.test.weather.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.weather.data.db.entity.CityEntity
import com.test.weather.data.db.entity.WeatherEntity

@Database(entities = [CityEntity::class, WeatherEntity::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
                ?: synchronized(LOCK) {
            instance
                    ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "WeatherData.db")
                        .fallbackToDestructiveMigration()
                        .build()
    }
}