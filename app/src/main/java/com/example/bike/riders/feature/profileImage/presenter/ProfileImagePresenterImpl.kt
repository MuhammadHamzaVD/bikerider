package com.example.bike.riders.feature.profileImage.presenter

import android.content.Intent
import android.widget.Toast
import com.example.bike.riders.feature.profileImage.contracts.ProfileImagePresenter
import com.example.bike.riders.feature.profileImage.contracts.ProfileImageView

class ProfileImagePresenterImpl(val view: ProfileImageView) : ProfileImagePresenter {
    override fun cameraPermissionRequest(
        permission: Boolean,
        selfPermissionWrite: Boolean,
        selfPermissionCamera: Boolean
    ) {
        if (permission){
            if(selfPermissionCamera || selfPermissionWrite){
               view.cameraRequest()
            }else{
                view.PickImagefromCamera()
            }
        }else{
            view.PickImagefromCamera()

        }
    }

    override fun galleryPermissionRequest(permission: Boolean, selfPermissionRead: Boolean) {
        if (permission){
            if(selfPermissionRead){
               view.galleryRequest()
            }else{
                view.PickImagefromGallery()
            }
        }else{
            view.PickImagefromGallery()

        }
    }

    override fun activityResult(permission: Boolean, check: Boolean, data: Intent?) {
        if(permission){
            if(check){
                view.setGalleryImage(data)
            }else{
                view.setCameraImage()
            }
        }
    }

    override fun checkCameraPermission(permission: Boolean) {
        if (permission){
            view.PickImagefromCamera()
        }else{
            view.permissionDenied()
        }
    }

    override fun checkGalleryPermission(permission: Boolean) {
        if (permission){
            view.PickImagefromGallery()
        }else{
            view.permissionDenied()
        }
    }

}