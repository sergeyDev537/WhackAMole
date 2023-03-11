package com.most4dev.domain.usecases

import com.most4dev.domain.entities.GameSettingsEntity
import com.most4dev.domain.repositories.UserRepository

class StartGameUseCase(private val userRepository: UserRepository) {

    operator fun invoke(): GameSettingsEntity {
        return userRepository.startGame()
    }

}