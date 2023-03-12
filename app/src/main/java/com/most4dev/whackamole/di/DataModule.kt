package com.most4dev.whackamole.di

import com.most4dev.data.impl.UserRepositoryImpl
import com.most4dev.data.mappers.GameMapper
import com.most4dev.domain.repositories.UserRepository
import org.koin.dsl.module

val dataModule = module {

    single<UserRepository> {
        UserRepositoryImpl(
            context = get(),
            gameMappers = get()
        )
    }

    single {
        GameMapper()
    }

}