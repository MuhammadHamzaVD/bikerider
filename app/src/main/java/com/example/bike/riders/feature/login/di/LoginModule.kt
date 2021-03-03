package com.example.bike.riders.feature.login.di

import com.example.bike.riders.feature.login.contracts.LoginInteractor
import com.example.bike.riders.feature.login.contracts.LoginPresenter
import com.example.bike.riders.feature.login.interactor.LoginInteractorImpl
import dagger.Binds
import dagger.Module


@Module
abstract class LoginModule {
    @Binds
    abstract fun bindLoginInteractor(interactor: LoginInteractorImpl) : LoginInteractor

    @Binds
    abstract fun bindLoginPresenter(presenter: LoginPresenter): LoginPresenter

}