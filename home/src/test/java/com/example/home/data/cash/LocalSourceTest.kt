package com.example.home.data.cash

import androidx.room.Room.inMemoryDatabaseBuilder
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.home.data.networking.MockUtils
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LocalSourceTest {

    private lateinit var universityDao: UniversityDao
    private lateinit var db: AppDatabase
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun createDb() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = inMemoryDatabaseBuilder(
            appContext, AppDatabase::class.java
        ).setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor())
            .build()
        universityDao = db.universityDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetUniversitiesTestCase() = runBlocking {
        val currencyList = MockUtils.Mocks.universitiesMockResponse
        universityDao.insert(currencyList)
        val currencyListResult = universityDao.getAllUniversities()
        Assert.assertEquals(currencyList, currencyListResult)
    }

}