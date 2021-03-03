package com.example.bike.riders.feature.maps.contracts

import com.google.android.gms.maps.GoogleMap

interface MapsView{
    fun onMapReady(googleMap: GoogleMap)
    fun getLocationUpdates()
    fun startLocationUpdates()
}