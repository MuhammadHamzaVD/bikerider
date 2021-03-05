package com.example.bike.riders.feature.maps.di

import com.example.bike.riders.feature.maps.contracts.MapsInteractor
import com.example.bike.riders.feature.maps.contracts.MapsPresenter
import com.example.bike.riders.feature.maps.interactor.MapsInteractorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MapsModule {
    @Binds
    abstract fun bindMainInteractor(interactor: MapsInteractorImpl) : MapsInteractor

    @Binds
    abstract fun bindMainPresenter(presenter: MapsPresenter): MapsPresenter
}