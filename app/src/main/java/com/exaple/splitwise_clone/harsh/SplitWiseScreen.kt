package com.exaple.splitwise_clone.harsh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exaple.splitwise_clone.R
import kotlinx.android.synthetic.main.activity_add_expense.*
import kotlinx.android.synthetic.main.activity_first_screen.*
import kotlinx.android.synthetic.main.activity_splitwise_screen.*

class SplitWiseScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splitwise_screen)




        btnLogin.setOnClickListener {

            val intent=Intent(this,Login_Screen_Activity::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener {
            val intent=Intent(this,Sign_up_Screen_Activity::class.java)
            startActivity(intent)

        }

        btnGoogle.setOnClickListener {

        }


    }
}