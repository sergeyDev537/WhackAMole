package com.most4dev.domain.usecases

import com.most4dev.domain.repositories.UserRepository

class SetBestScoreUseCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(bestScore: Long){
        userRepository.setBestScore(bestScore)
    }

}