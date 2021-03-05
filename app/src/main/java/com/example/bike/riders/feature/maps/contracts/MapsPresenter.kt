package com.example.bike.riders.feature.maps.contracts

import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker

interface MapsPresenter {
    fun selfLocation(selfPermission: Boolean/*, locationResult: LocationRequest, locationCallback: LocationCallback*/)
    fun fusedLocationUpdate(fineLocationPermission: Boolean)
    fun finalLocation(locationFinal: Boolean)
    fun onLocationResult(locationResult: LocationResult)
}