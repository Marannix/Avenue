package com.example.avenue.dagger.modules

import android.app.Application
import androidx.room.Room
import com.example.avenue.database.ApplicationDatabase
import com.example.avenue.database.dao.CharacterDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application): ApplicationDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            ApplicationDatabase::class.java, "avenue.db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCharactersDao(applicationDatabase: ApplicationDatabase): CharacterDao {
        return applicationDatabase.charactersDao()
    }

}