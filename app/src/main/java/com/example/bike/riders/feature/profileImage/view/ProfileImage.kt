package com.example.bike.riders.feature.profileImage.view

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.bike.riders.R
import com.example.bike.riders.feature.login.presenter.LoginPresenterImpl
import com.example.bike.riders.feature.profileImage.contracts.ProfileImagePresenter
import com.example.bike.riders.feature.profileImage.contracts.ProfileImageView
import com.example.bike.riders.feature.profileImage.presenter.ProfileImagePresenterImpl
import kotlinx.android.synthetic.main.activity_profile_image.*

class ProfileImage : AppCompatActivity() , ProfileImageView{
    var check : Boolean = true
    var permission : Boolean = true
    var selfPermissionRead: Boolean = true
    var selfPermissionWrite: Boolean = true
    var selfPermissionCamera: Boolean = true
    var image_URL : Uri? = null
    private lateinit var presenter: ProfileImagePresenter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_image)

        presenter = ProfileImagePresenterImpl(this)

        img_pick_btn.setOnClickListener {
            check = true
            permission = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            selfPermissionRead = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED

            presenter.galleryPermissionRequest(permission, selfPermissionRead)
        }

        capture_btn.setOnClickListener {
            check = false
            permission = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            selfPermissionWrite = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
            selfPermissionCamera = checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED

            presenter.cameraPermissionRequest(permission, selfPermissionWrite, selfPermissionCamera)
        }
    }
    override fun PickImagefromGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        image_URL = intent.data
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun PickImagefromCamera(){
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE,"New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION,"From The Camera")
        image_URL = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_URL)
        startActivityForResult(cameraIntent, PERMISSION_CODE)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun cameraRequest() {
        val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        requestPermissions(permission, IMAGE_PICK_CODE)    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun galleryRequest() {
        val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        requestPermissions(permission, PERMISSION_CODE)    }

    companion object{
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }
    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        if (check){
            when(requestCode){
                PERMISSION_CODE -> {
                    permission = (grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)

                    presenter.checkGalleryPermission(permission)
                }
            }
        }else{
            when(requestCode){
                IMAGE_PICK_CODE -> {
                    permission = (grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)

                    presenter.checkCameraPermission(permission)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        permission = (resultCode == Activity.RESULT_OK)

        presenter.activityResult(permission,check,data)
    }

    override fun setCameraImage() {
        image_view.setImageURI(image_URL)
    }

    override fun setGalleryImage(data: Intent?) {
        image_view.setImageURI(data?.data)
    }

    override fun permissionDenied() {
        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
    }
}