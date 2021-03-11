package com.exaple.splitwise_clone.harsh

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.exaple.splitwise_clone.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_sign_up__screen_.*


class Sign_up_Screen_Activity : AppCompatActivity() {

    private lateinit var camera: ImageView
    private lateinit var imageTaker: ImageView

    // private lateinit var etfullName: EditText
    // private lateinit var etPhoneNumber: EditText
    // private lateinit var etEmailSignUp: TextInputEditText
    val CAMERA_REQUEST_ID = 1222


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up__screen_)
        camera = findViewById(R.id.ivCamera)
        imageTaker = findViewById(R.id.ivImageTaker)
        //  etfullName = findViewById(R.id.etFullName)

        btnBackSign.setOnClickListener {


            val intent = Intent(this, SplitWiseScreen::class.java)
            startActivity(intent)
        }


        /** get permission */

        if (ContextCompat.checkSelfPermission(
                applicationContext, android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED
        )
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_ID
            )
        /** set camera open*/
        camera.setOnClickListener {

            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST_ID)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST_ID) {
            val Images: Bitmap = data?.extras?.get("data") as Bitmap
            imageTaker.setImageBitmap(Images)

        }
    }


}