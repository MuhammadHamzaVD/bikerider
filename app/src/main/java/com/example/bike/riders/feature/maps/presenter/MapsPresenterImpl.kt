package com.example.bike.riders.feature.maps.presenter

import com.example.bike.riders.feature.maps.contracts.MapsPresenter
import com.example.bike.riders.feature.maps.contracts.MapsView
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker

class MapsPresenterImpl(val view: MapsView) : MapsPresenter {

    override fun fusedLocationUpdate(fineLocationPermission: Boolean){
        if(fineLocationPermission){
            return
        }
        view.fusedLocation()
    }

    override fun finalLocation(locationFinal: Boolean) {
        TODO("Not yet implemented")
    }


    override fun selfLocation(selfPermission: Boolean) {
        if (selfPermission) {
            view.showLocation()
        }else{
            view.requestPermission()
        }
    }

    override fun onLocationResult(locationResult: LocationResult) {
        if (locationResult.locations.isNotEmpty()) {
            view.onMarkerLocation(locationResult)
        }
    }

    /*   fun startLocationUpdates(context: Context) {
           if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
               return
           }
           fusedLocationClient.requestLocationUpdates(
                   locationRequest,
                   locationCallback,
                   null
           )
       }*/
}