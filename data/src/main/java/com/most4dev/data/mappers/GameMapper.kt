package com.most4dev.data.mappers

import com.most4dev.data.models.GameResultDbModel
import com.most4dev.data.models.GameSettingsDbModel
import com.most4dev.domain.entities.GameResultEntity
import com.most4dev.domain.entities.GameSettingsEntity

class GameMapper {

    fun mapSettingsDbModelToEntity(gameSettingsDbModel: GameSettingsDbModel): GameSettingsEntity {
        return GameSettingsEntity(gameSettingsDbModel.millisTimer)
    }

    fun mapResultDbModelToEntity(gameResultDbModel: GameResultDbModel): GameResultEntity {
        return GameResultEntity(gameResultDbModel.currentScore, gameResultDbModel.bestScore)
    }

}