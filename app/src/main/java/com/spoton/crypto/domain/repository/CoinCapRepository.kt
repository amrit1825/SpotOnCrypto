package com.spoton.crypto.domain.repository

import com.spoton.crypto.data.remote.dto.DataDto

// Repository interface. Will be implemented in data layer.
interface CoinCapRepository {

    suspend fun getCoins(): DataDto

}