package com.example.bike.riders.feature.maps.contracts

import com.example.bike.riders.feature.main.api.Location
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

interface MapsView{
    fun requestPermission()
    fun onMarkerLocation(locationResult: LocationResult)
    fun getLocationUpdates()
    fun startLocationUpdates()
    fun getLocationAccess()
    fun fusedLocation()

    fun showLocation()

}