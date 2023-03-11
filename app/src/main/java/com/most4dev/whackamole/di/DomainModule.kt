package com.most4dev.whackamole.di

import com.most4dev.domain.usecases.FinishGameUseCase
import com.most4dev.domain.usecases.GetBestScoreUseCase
import com.most4dev.domain.usecases.SetBestScoreUseCase
import com.most4dev.domain.usecases.StartGameUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        StartGameUseCase(userRepository = get())
    }

    factory {
        FinishGameUseCase(userRepository = get())
    }

    factory {
        SetBestScoreUseCase(userRepository = get())
    }

    factory {
        GetBestScoreUseCase(userRepository = get())
    }

}