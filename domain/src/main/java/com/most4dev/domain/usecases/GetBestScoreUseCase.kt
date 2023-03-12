package com.most4dev.domain.usecases

import com.most4dev.domain.repositories.UserRepository

class GetBestScoreUseCase(private val userRepository: UserRepository) {

    operator fun invoke(): Long{
        return userRepository.getBestScore()
    }

}