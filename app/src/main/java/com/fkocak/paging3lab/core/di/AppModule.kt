package com.fkocak.paging3lab.core.di

import com.fkocak.paging3lab.core.network.ApiService
import com.fkocak.paging3lab.data.datasource.remote.ApiRemoteDataSource
import com.fkocak.paging3lab.data.datasource.remote.ApiRemoteDataSourceImpl
import com.fkocak.paging3lab.data.repository.ApiRepositoryImpl
import com.fkocak.paging3lab.domain.repository.ApiRepository
import com.fkocak.paging3lab.domain.usecase.GetApiUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesMovieRemoteDataSource(
        api: ApiService
    ): ApiRemoteDataSource {
        return ApiRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun providesMovieRepository(
        movieRemoteDataSource: ApiRemoteDataSource
    ): ApiRepository {
        return ApiRepositoryImpl(movieRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Singleton
    @Provides
    fun providesGetMoviesUseCase(
        movieRepository: ApiRepository,
        dispatcher: CoroutineDispatcher,
    ): GetApiUseCase {
        return GetApiUseCase(movieRepository, dispatcher)
    }
}