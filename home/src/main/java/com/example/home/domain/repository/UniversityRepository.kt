package com.example.home.domain.repository

import com.example.home.data.cash.UniversityDao
import com.example.home.data.networking.AppServices
import com.example.home.data.networking.wrappers.unwrapResponseD
import com.example.home.di.IoDispatcher
import com.example.home.domain.models.University
import com.example.home.domain.models.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


interface UniversityRepository {

    fun getUniversitiesByCountry(country: String): Flow<List<University>>

    suspend fun loadUniversitiesByCountry(country: String)
}

class UniversityRepositoryImpl(
    private val appServices: AppServices,
    private val universityDao: UniversityDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UniversityRepository {

    override fun getUniversitiesByCountry(country: String): Flow<List<University>> {
        return if (country.isNotEmpty())
            universityDao.getAllUniversitiesByCountry(country)
                .map { it.map { universityDto -> universityDto.toDomain() } }
                .flowOn(dispatcher)
        else
            universityDao.getAllUniversities()
                .map { it.map { universityDto -> universityDto.toDomain() } }
                .flowOn(dispatcher)
    }

    override suspend fun loadUniversitiesByCountry(country: String) {
        withContext(dispatcher) {
            val apiResponse = appServices.getUniversitiesByCountry(country)
            val data = unwrapResponseD(apiResponse)
            universityDao.insert(data)
        }
    }

}









