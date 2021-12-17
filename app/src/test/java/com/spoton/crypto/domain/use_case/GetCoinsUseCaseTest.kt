package com.spoton.crypto.domain.use_case

import com.spoton.crypto.data.remote.dto.toCoin
import com.spoton.crypto.data.repository.FakeCoinCapRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetCoinsUseCaseTest {

    private lateinit var getCoinsUseCase: GetCoinsUseCase
    private lateinit var fakeCoinCapRepository: FakeCoinCapRepository

    @Before
    fun setUp() {
        fakeCoinCapRepository = FakeCoinCapRepository()
        getCoinsUseCase = GetCoinsUseCase(fakeCoinCapRepository)
    }

    @Test
    fun getCoinsTest() {
        runBlocking {
            val list = getCoinsUseCase().toList()
            assertEquals(fakeCoinCapRepository.getCoins().data.map { it.toCoin() }, list[1].data)
        }
    }

}