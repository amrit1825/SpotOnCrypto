package com.spoton.crypto.presentation.coin_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spoton.crypto.common.Resource
import com.spoton.crypto.domain.use_case.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(private val getCoinsUseCase: GetCoinsUseCase) :
    ViewModel() {

    private val mState = MutableLiveData(CoinListState())
    val state: LiveData<CoinListState> = mState

    fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    mState.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    mState.value =
                        CoinListState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    mState.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}
