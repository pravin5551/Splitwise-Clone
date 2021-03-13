package com.exaple.splitwise_clone.harsh

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.vinod.database.users.UserEntity
import com.exaple.splitwise_clone.vinod.viewmodels.*
import com.exaple.splitwise_clone.vinod.views.SplitwiseApplication
import kotlinx.android.synthetic.main.activity_sign_up__screen_.*

class Sign_up_Screen_Activity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var ivImageTakerSplitWise: ImageView
    private lateinit var ivCameraSplitWise: ImageView
    private val cameraRequestSplitWise = 1222
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up__screen_)
        ivImageTakerSplitWise = findViewById(R.id.ivImageTakerSplitWise)
        ivCameraSplitWise = findViewById(R.id.ivCameraSplitWise)
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED
        )
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA), cameraRequestSplitWise
            )
        ivCameraSplitWise.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, cameraRequestSplitWise)
        }
        btnBackSign.setOnClickListener {
            val intent = Intent(this, SplitWiseScreen::class.java)
            startActivity(intent)
            this.finish()
        }

        btnDoneSign.setOnClickListener {
            if (etSignUpFullName.text.isNotEmpty() && etSignUpEmail.editText?.text!!.isNotEmpty()
                && etSignUpPassword.editText?.text!!.isNotEmpty()
            ) {

                createDatabase()
                val userEntity = UserEntity(
                    etSignUpFullName.text.toString(),
                    etSignUpPhone.text.toString(),
                    etSignUpEmail.editText?.text!!.toString(),
                    etSignUpPassword.editText?.text!!.toString(),
                    "",
                    "0",
                    "0",
                    0
                )

                userViewModel.addUser(userEntity)

                val intent2 = Intent(this, Login_Screen_Activity::class.java)
                intent2.putExtra("email", userEntity.email)
                startActivity(intent2)
                this.finish()
            } else Toast.makeText(this, "fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createDatabase() {
        val appClass = application as SplitwiseApplication
        val userRepository = appClass.userRepository
        val userViewModelFactory = UserViewModelFactory(userRepository)

        userViewModel = ViewModelProviders.of(this, userViewModelFactory)
            .get(UserViewModel::class.java)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequestSplitWise) {
            val images: Bitmap = data?.extras?.get("data") as Bitmap
            ivImageTakerSplitWise.setImageBitmap(images)
        }
    }
}