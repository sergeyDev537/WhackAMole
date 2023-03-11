package com.most4dev.data.impl

import android.content.Context
import com.most4dev.data.mappers.GameMapper
import com.most4dev.data.models.GameResultDbModel
import com.most4dev.data.models.GameSettingsDbModel
import com.most4dev.domain.entities.GameResultEntity
import com.most4dev.domain.entities.GameSettingsEntity
import com.most4dev.domain.repositories.UserRepository

class UserRepositoryImpl(
    private val context: Context,
    private val gameMappers: GameMapper,
) : UserRepository {

    override fun startGame(): GameSettingsEntity {
        return gameMappers.mapSettingsDbModelToEntity(
            GameSettingsDbModel(DEFAULT_TIMER)
        )
    }

    override fun finishGame(score: Long): GameResultEntity {
        return gameMappers.mapResultDbModelToEntity(
            GameResultDbModel(score, getBestScore())
        )
    }

    override fun setBestScore(bestScore: Long) {
        val sharedPref =
            context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putLong(KEY_BEST_SCORE, bestScore)
            apply()
        }
    }

    override fun getBestScore(): Long {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        val highScore = sharedPref.getLong(KEY_BEST_SCORE, DEFAULT_BEST_SCORE)
        return highScore
    }

    companion object {

        const val SHARED_PREFERENCE_KEY = "KEY_SHARED_PREFERENCE"
        const val KEY_BEST_SCORE = "BEST SCORE KEY"
        const val DEFAULT_BEST_SCORE = 0L
        const val DEFAULT_TIMER = 10000L

    }

}