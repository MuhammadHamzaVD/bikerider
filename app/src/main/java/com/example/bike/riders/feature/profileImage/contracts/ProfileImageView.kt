package com.example.bike.riders.feature.profileImage.contracts

import android.content.Intent

interface ProfileImageView {
    fun PickImagefromGallery()
    fun PickImagefromCamera()
    fun cameraRequest()
    fun galleryRequest()

    fun setCameraImage()
    fun setGalleryImage(data: Intent?)

    fun permissionDenied()
}