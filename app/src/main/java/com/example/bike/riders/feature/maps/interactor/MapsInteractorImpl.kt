package com.example.bike.riders.feature.maps.interactor

import com.example.bike.riders.feature.maps.contracts.MapsInteractor
import com.example.bike.riders.feature.maps.contracts.MapsInteractorOut
import javax.inject.Inject

class MapsInteractorImpl @Inject constructor() : MapsInteractor{

    private var mapsInteractorOut: MapsInteractorOut? = null

    override fun setInteractorOut(interactorOut: MapsInteractorOut) {}
}