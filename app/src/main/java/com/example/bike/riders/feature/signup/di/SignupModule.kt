package com.example.bike.riders.feature.signup.di

import com.example.bike.riders.feature.signup.contracts.SignupInteractor
import com.example.bike.riders.feature.signup.contracts.SignupPresenter
import com.example.bike.riders.feature.signup.interactor.SignupInteractorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class SignupModule {
    @Binds
    abstract fun bindSignupInteractor(interactor: SignupInteractorImpl) : SignupInteractor

    @Binds
    abstract fun bindSignupPresenter(presenter: SignupPresenter): SignupPresenter
}