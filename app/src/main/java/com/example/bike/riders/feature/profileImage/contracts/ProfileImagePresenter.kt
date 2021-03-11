package com.example.bike.riders.feature.profileImage.contracts

import android.content.Intent

interface ProfileImagePresenter {
    fun cameraPermissionRequest(permission: Boolean,selfPermissionWrite: Boolean ,selfPermissionCamera: Boolean)
    fun galleryPermissionRequest(permission: Boolean,selfPermissionRead: Boolean)
    fun activityResult(permission: Boolean, check: Boolean, data: Intent?)

    fun checkCameraPermission(permission: Boolean)
    fun checkGalleryPermission(permission: Boolean)

}