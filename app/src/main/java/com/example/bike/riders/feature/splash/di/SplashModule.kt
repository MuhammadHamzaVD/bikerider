package com.example.bike.riders.feature.splash.di

import com.example.bike.riders.feature.splash.contracts.SplashInteractor
import com.example.bike.riders.feature.splash.contracts.SplashPresenter
import com.example.bike.riders.feature.splash.interactor.SplashInteractorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class SplashModule {

    @Binds
    abstract fun bindSplashInteractor(interactor: SplashInteractorImpl): SplashInteractor

    @Binds
    abstract fun bindSplashPresenter(presenter: SplashPresenter): SplashPresenter
}