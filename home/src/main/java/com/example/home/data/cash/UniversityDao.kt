package com.example.home.data.cash

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.home.data.dto.UniversityDto
import kotlinx.coroutines.flow.Flow

@Dao
interface UniversityDao {

    @Upsert
    suspend fun insert(user: List<UniversityDto>)

    @Query("SELECT * FROM UniversityDto WHERE country =:country")
    fun getAllUniversitiesByCountry(country: String): Flow<List<UniversityDto>>

    @Query("SELECT * FROM UniversityDto")
    fun getAllUniversities(): Flow<List<UniversityDto>>

}


