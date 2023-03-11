package com.most4dev.domain.usecases

import com.most4dev.domain.entities.GameResultEntity
import com.most4dev.domain.repositories.UserRepository

class FinishGameUseCase(private val userRepository: UserRepository) {

    operator fun invoke(score: Long): GameResultEntity {
        return userRepository.finishGame(score)
    }

}