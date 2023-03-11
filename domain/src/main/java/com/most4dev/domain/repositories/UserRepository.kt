package com.most4dev.domain.repositories

interface UserRepository {

    fun startGame()

    fun finishGame()

    suspend fun setBestScore(long: Long)

    suspend fun getBestScore(): Long

}