package com.spoton.crypto.data.remote.dto

// Data Class for data transfer object for API response.
data class DataDto(
    val data: List<CoinDto>,
    val timestamp: Long
)
