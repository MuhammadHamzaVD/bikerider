package com.example.bike.riders.feature.maps.contracts

import com.google.android.gms.maps.GoogleMap

interface MapsPresenter {
    fun getLocationAccess(mMap: GoogleMap)
}