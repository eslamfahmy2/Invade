package com.example.home.di

import android.content.Context
import androidx.room.Room
import com.example.home.data.cash.AppDatabase


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ): AppDatabase = Room.databaseBuilder(
        app, AppDatabase::class.java, "your_db_name"
    ).fallbackToDestructiveMigration()
        .build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun provideUniversityDao(db: AppDatabase) =
        db.universityDao() // The reason we can implement a Dao for the database


}