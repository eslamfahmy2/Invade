package com.example.home.data.networking

import com.example.home.data.dto.UniversityDto

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

import retrofit2.Response


class RemoteSourceTest {

    @Mock
    lateinit var apiService: AppServices

    private lateinit var universitiesMockResponse: Response<List<UniversityDto>>


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        universitiesMockResponse = MockUtils.Mocks.getUniversitiesMockResponse()
    }

    @Test
    fun `test get universities  success`() = runBlocking {

        `when`(apiService.getUniversitiesByCountry("Egypt")).thenReturn(universitiesMockResponse)

        val response = apiService.getUniversitiesByCountry("Egypt")

        assertTrue(response.isSuccessful)
        assertEquals(MockUtils.Mocks.universitiesMockResponse, response.body())
    }

}

