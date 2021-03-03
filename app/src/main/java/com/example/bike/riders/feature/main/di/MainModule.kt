package com.example.bike.riders.feature.main.di

import com.example.bike.riders.feature.main.contracts.MainInteractor
import com.example.bike.riders.feature.main.contracts.MainPresenter
import com.example.bike.riders.feature.main.interactor.MainInteractorImpl
import dagger.Binds
import dagger.Module


@Module
abstract class MainModule {
    @Binds
    abstract fun bindMainInteractor(interactor: MainInteractorImpl) : MainInteractor

    @Binds
    abstract fun bindMainPresenter(presenter: MainPresenter): MainPresenter
}