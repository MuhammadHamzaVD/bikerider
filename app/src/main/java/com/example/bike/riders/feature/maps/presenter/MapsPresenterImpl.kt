package com.example.bike.riders.feature.maps.presenter

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.bike.riders.feature.maps.contracts.MapsInteractor
import com.example.bike.riders.feature.maps.contracts.MapsInteractorOut
import com.example.bike.riders.feature.maps.contracts.MapsPresenter
import com.example.bike.riders.feature.maps.contracts.MapsView
import com.google.android.gms.maps.GoogleMap

class MapsPresenterImpl(val view: MapsView, val interactor: MapsInteractor) : MapsPresenter, MapsInteractorOut {
    init {
        interactor.setInteractorOut(this)
    }
    override fun getLocationAccess(mMap: GoogleMap) {
    }
}