package com.example.bike.riders.feature.maps.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.bike.riders.R
import com.example.bike.riders.feature.maps.contracts.MapsPresenter
import com.example.bike.riders.feature.maps.contracts.MapsView
import com.example.bike.riders.feature.maps.presenter.MapsPresenterImpl
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.toolbar_layout.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MapsView {

    private lateinit var presenter: MapsPresenter

    private lateinit var mMap: GoogleMap
    private val LOCATION_PERMISSION_REQUEST = 1

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var userLocationMarker : Marker? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.bike.riders.R.layout.activity_maps)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationRequest = LocationRequest()
        locationRequest.interval = 10
        locationRequest.fastestInterval = 5
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY


        presenter = MapsPresenterImpl(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        back_button.setOnClickListener {
            finish()
        }
    }

    override fun onMarkerLocation(locationResult: LocationResult) {
        val location = locationResult.lastLocation
        val latLng = LatLng(location.latitude, location.longitude)
        if(userLocationMarker == null){
            val markerOptions = MarkerOptions().position(latLng).title("Your Location").icon(BitmapDescriptorFactory.fromResource(R.drawable.bike))
            userLocationMarker = mMap.addMarker(markerOptions)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        }else{
            userLocationMarker?.position = latLng
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        }
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f))
    }

    override fun getLocationUpdates() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                presenter.onLocationResult(locationResult)
            }
        }
    }

    override fun startLocationUpdates() {
        val fineLocationPermission: Boolean

        fineLocationPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        presenter.fusedLocationUpdate(fineLocationPermission)
    }

    override fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf
        (android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return
                }
                mMap.isMyLocationEnabled = true
            } else {
                Toast.makeText(this, "User has not granted location access Permission", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    override fun getLocationAccess() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val selfPermission: Boolean = (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        presenter.selfLocation(selfPermission)
    }

    @SuppressLint("MissingPermission")
    override fun fusedLocation() {
        fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null
        )
    }

    @SuppressLint("MissingPermission")
    override fun showLocation() {
        mMap.isMyLocationEnabled = true
        getLocationUpdates()
        startLocationUpdates()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getLocationAccess()
    }
}