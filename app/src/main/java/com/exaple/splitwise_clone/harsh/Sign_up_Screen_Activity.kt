package com.exaple.splitwise_clone.harsh

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.pravinTrialAndError.FragmentMainActivity
import kotlinx.android.synthetic.main.activity_sign_up__screen_.*

class Sign_up_Screen_Activity : AppCompatActivity() {
    private lateinit var ivImageTakerSplitWise: ImageView
    private lateinit var ivCameraSplitWise: ImageView
    private val cameraRequestSplitWise = 1222


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up__screen_)
        ivImageTakerSplitWise = findViewById(R.id.ivImageTakerSplitWise)
        ivCameraSplitWise = findViewById(R.id.ivCameraSplitWise)


        btnBackSign.setOnClickListener {

            val intent = Intent(this, SplitWiseScreen::class.java)
            startActivity(intent)
        }

        btnDoneSign.setOnClickListener {
            val intent2 = Intent(this, FragmentMainActivity::class.java)
            startActivity(intent2)
        }

        //permission

        if (ContextCompat.checkSelfPermission(
                applicationContext, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA),cameraRequestSplitWise)

        ivCameraSplitWise.setOnClickListener {

            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, cameraRequestSplitWise)

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == cameraRequestSplitWise) {
            val images: Bitmap = data?.extras?.get("data") as Bitmap
            ivImageTakerSplitWise.setImageBitmap(images)


        }
    }
}