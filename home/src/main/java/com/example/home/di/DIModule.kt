package com.example.home.di


import com.example.home.data.cash.UniversityDao
import com.example.home.data.networking.AppServices
import com.example.home.domain.repository.UniversityRepository
import com.example.home.domain.repository.UniversityRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIModule {


    @IoDispatcher
    @Provides
    @Singleton
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    @Singleton
    fun provideRepository(
        appServices: AppServices,
        universityDao: UniversityDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): UniversityRepository = UniversityRepositoryImpl(
        appServices = appServices,
        universityDao = universityDao,
        dispatcher = ioDispatcher
    )

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher
