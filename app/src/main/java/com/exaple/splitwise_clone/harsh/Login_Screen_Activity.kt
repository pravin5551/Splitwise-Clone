package com.exaple.splitwise_clone.harsh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exaple.splitwise_clone.R
import kotlinx.android.synthetic.main.activity_login__screen_.*
import kotlinx.android.synthetic.main.activity_splitwise_screen.*

class Login_Screen_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login__screen_)


        btnBackLogin.setOnClickListener {

            val intent= Intent(this,SplitWiseScreen::class.java)
            startActivity(intent)
        }




    }
}