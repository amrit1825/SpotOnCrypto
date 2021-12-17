package com.spoton.crypto.domain.model

// Data Class for showing data on screen.
data class Coin(
    val changePercent24Hr: String,
    val name: String,
    val priceUsd: String,
    val rank: String,
)
