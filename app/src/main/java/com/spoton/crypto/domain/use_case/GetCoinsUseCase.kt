package com.spoton.crypto.domain.use_case

import com.spoton.crypto.common.Resource
import com.spoton.crypto.data.remote.dto.toCoin
import com.spoton.crypto.domain.model.Coin
import com.spoton.crypto.domain.repository.CoinCapRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

// Clean architecture use-case to represent get coins feature.
class GetCoinsUseCase @Inject constructor(private val coinCapRepository: CoinCapRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = coinCapRepository.getCoins().data.map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Could not reach server. Check your internet connection."))
        }
    }

}