package com.spoton.crypto.presentation.coin_list

import com.spoton.crypto.domain.model.Coin

// Data Class for representing coin list, loading & error on User interface.
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
