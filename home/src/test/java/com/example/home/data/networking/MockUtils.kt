package com.example.home.data.networking


import com.example.home.data.dto.UniversityDto
import retrofit2.Response


class MockUtils {
    object Mocks {

        val universitiesMockResponse = mutableListOf<UniversityDto>()

        init {

            universitiesMockResponse.add(
                UniversityDto(
                    alphaTwoCode = "EG",
                    name = "Arab Academy for Science & Technology",
                    country = "Egypt"
                )
            )
            universitiesMockResponse.add(
                UniversityDto(
                    alphaTwoCode = "EG",
                    name = "Akhbar El Yom Academy",
                    country = "Egypt"
                )
            )

        }

        fun getUniversitiesMockResponse(): Response<List<UniversityDto>> {
            return Response.success(universitiesMockResponse.toList())
        }
    }
}