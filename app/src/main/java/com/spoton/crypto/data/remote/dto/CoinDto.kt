package com.spoton.crypto.data.remote.dto

import com.spoton.crypto.domain.model.Coin

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

fun CoinDto.toCoin(): Coin {
    return Coin(
        changePercent24Hr = changePercent24Hr,
        name = name,
        priceUsd = priceUsd,
        rank = rank
    )
}