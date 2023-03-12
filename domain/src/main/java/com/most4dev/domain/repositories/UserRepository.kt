package com.most4dev.domain.repositories

import com.most4dev.domain.entities.GameResultEntity
import com.most4dev.domain.entities.GameSettingsEntity

interface UserRepository {

    fun startGame(): GameSettingsEntity

    fun finishGame(score: Long): GameResultEntity

    fun setBestScore(long: Long)

    fun getBestScore(): Long

}