package com.most4dev.whackamole.di

import com.most4dev.whackamole.ui.fragments.game.GameViewModel
import com.most4dev.whackamole.ui.fragments.startGame.StartGameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        StartGameViewModel(
            getBestScoreUseCase = get()
        )
    }

    viewModel {
        GameViewModel(
            getBestScoreUseCase = get(),
            startGameUseCase = get(),
            setBestScoreUseCase = get(),
            finishGameUseCase = get()
        )
    }

}