package com.spoton.crypto.di

import com.spoton.crypto.common.Constants
import com.spoton.crypto.data.remote.CoinCapApiService
import com.spoton.crypto.data.repository.CoinCapRepositoryImpl
import com.spoton.crypto.domain.repository.CoinCapRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCoinCapApiService(): CoinCapApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinCapApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinCapRepository(coinCapApiService: CoinCapApiService): CoinCapRepository {
        return CoinCapRepositoryImpl(coinCapApiService)
    }

}