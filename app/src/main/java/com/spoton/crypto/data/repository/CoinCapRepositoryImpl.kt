package com.spoton.crypto.data.repository

import com.spoton.crypto.data.remote.CoinCapApiService
import com.spoton.crypto.data.remote.dto.DataDto
import com.spoton.crypto.domain.repository.CoinCapRepository
import javax.inject.Inject

class CoinCapRepositoryImpl @Inject constructor(private val coinCapApiService: CoinCapApiService) :
    CoinCapRepository {

    override suspend fun getCoins(): DataDto {
        return coinCapApiService.getCoins()
    }

}