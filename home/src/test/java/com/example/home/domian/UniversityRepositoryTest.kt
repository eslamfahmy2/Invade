package com.example.home.domian

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.home.data.cash.AppDatabase
import com.example.home.data.cash.MockInterceptor
import com.example.home.data.cash.MockTestUtil
import com.example.home.data.cash.UniversityDao
import com.example.home.data.networking.AppServices
import com.example.home.data.networking.MockUtils
import com.example.home.domain.models.University
import com.example.home.domain.models.toDomain
import com.example.home.domain.repository.UniversityRepository
import com.example.home.domain.repository.UniversityRepositoryImpl

import com.google.gson.GsonBuilder
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
class UniversityRepositoryTest {

    private lateinit var myRepository: UniversityRepository
    private lateinit var apiService: AppServices
    private val mockInterceptor = MockInterceptor()
    private lateinit var mockDatabase: AppDatabase
    private lateinit var dao: UniversityDao
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        mockDatabase = Room.inMemoryDatabaseBuilder(
            appContext, AppDatabase::class.java
        ).setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor()).build()
        dao = mockDatabase.universityDao()

        val gson = GsonBuilder().setLenient().create()

        apiService = Retrofit.Builder()
            .baseUrl("http://localhost:8080/") // Replace with the appropriate base URL
            .addConverterFactory(GsonConverterFactory.create(gson)).client(
                OkHttpClient.Builder().addInterceptor(mockInterceptor).build()
            ).build().create(AppServices::class.java)

        myRepository = UniversityRepositoryImpl(
            appServices = apiService,
            universityDao = dao,
            dispatcher = testDispatcher
        )

    }

    @After
    fun cleanup() {
        mockDatabase.close()
    }

    @Test
    fun testGetCurrencyFromNetworkIfNotExistInCash() = runBlocking {

        val expectedResponse = MockTestUtil.getCurrenciesMockResponse()
        mockInterceptor.mockResponse("currencies", expectedResponse)

        var response = emptyList<University>()

        myRepository.getUniversitiesByCountry("Egypt").collect {
            response = it
        }
        val expectedResponseData = MockUtils.Mocks.universitiesMockResponse
        assertEquals(expectedResponseData, response)

    }

    @Test
    fun testGetCurrencyFromCash() = runBlocking {

        val expectedResponse = MockUtils.Mocks.universitiesMockResponse
        dao.insert(expectedResponse)

        var response = emptyList<University>()

        myRepository.getUniversitiesByCountry("Egypt").collect {
            response = it
        }
        val expectedResponseData = expectedResponse.map { it.toDomain() }
        assertEquals(expectedResponseData, response)

    }


}

