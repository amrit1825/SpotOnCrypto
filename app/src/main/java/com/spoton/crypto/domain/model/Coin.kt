package com.spoton.crypto.domain.model

data class Coin(
    val changePercent24Hr: String,
    val name: String,
    val priceUsd: String,
    val rank: String,
)
