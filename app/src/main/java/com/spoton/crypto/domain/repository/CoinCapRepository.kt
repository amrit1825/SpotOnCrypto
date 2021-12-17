package com.spoton.crypto.domain.repository

import com.spoton.crypto.data.remote.dto.DataDto

interface CoinCapRepository {

    suspend fun getCoins(): DataDto

}