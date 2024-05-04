package com.example.home.data.cash

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.home.data.dto.UniversityDto

@Database(entities = [UniversityDto::class], version = 4 , exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun universityDao(): UniversityDao
}