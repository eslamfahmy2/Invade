package com.example.home.data.networking

import com.example.home.data.dto.UniversityDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppServices {

    @GET("search")
    suspend fun getUniversitiesByCountry(@Query("country") country: String): Response<List<UniversityDto>>

}