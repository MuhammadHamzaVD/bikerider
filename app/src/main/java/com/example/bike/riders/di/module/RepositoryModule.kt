package com.example.bike.riders.di.module

import com.example.bike.riders.repository.*
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindUserRepository(repository: UserRepo): UserRepository.Repo
}