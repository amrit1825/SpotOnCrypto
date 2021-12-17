package com.spoton.crypto.data.remote

import com.spoton.crypto.data.remote.dto.DataDto
import retrofit2.http.GET

// Retrofit interface for defining API calls.
interface CoinCapApiService {

    @GET("v2/assets?limit=20&offset=0")
    suspend fun getCoins(): DataDto

}