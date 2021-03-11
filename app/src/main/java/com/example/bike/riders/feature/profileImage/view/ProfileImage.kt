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
import androidx.appcompat.app.AppCompatActivity
import com.example.bike.riders.R
import kotlinx.android.synthetic.main.activity_profile_image.*

class ProfileImage : AppCompatActivity() {
    var check : Boolean = true
    var image_URL : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_image)

        img_pick_btn.setOnClickListener {
            check = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)
                }else{
                    PickImagefromGallery()
                }
            }else{
                PickImagefromGallery()

            }
        }

        capture_btn.setOnClickListener {
            check = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                        || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, 1000)
                }else{
                    PickImagefromCamera()
                }
            }else{
                PickImagefromCamera()

            }
        }
    }
    private fun PickImagefromGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    private fun PickImagefromCamera(){
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE,"New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION,"From The Camera")
        image_URL = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_URL)
        startActivityForResult(cameraIntent, PERMISSION_CODE)
    }

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
                    if (grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        PickImagefromGallery()
                    }else{
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }else{
            when(requestCode){
                IMAGE_PICK_CODE -> {
                    if (grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        PickImagefromCamera()
                    }else{
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(check){
                image_view.setImageURI(data?.data)
            }else{
                image_view.setImageURI(image_URL)
            }
        }
    }
}