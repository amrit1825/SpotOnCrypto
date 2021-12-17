package com.spoton.crypto.data.remote.dto

import com.spoton.crypto.domain.model.Coin

// Data Class for data transfer object for API response.
data class CoinDto(
    val changePercent24Hr: String,
    val explorer: String,
    val id: String,
    val marketCapUsd: String,
    val maxSupply: String,
    val name: String,
    val priceUsd: String,
    val rank: String,
    val supply: String,
    val symbol: String,
    val volumeUsd24Hr: String,
    val vwap24Hr: String
)

// Extension function for converting Data transfer object into Model object that will be used to show data on screen.
fun CoinDto.toCoin(): Coin {
    return Coin(
        changePercent24Hr = changePercent24Hr,
        name = name,
        priceUsd = priceUsd,
        rank = rank
    )
}