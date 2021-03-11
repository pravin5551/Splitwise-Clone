package com.exaple.splitwise_clone.harsh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.menubartrail.ui.home.ActivityFragment
import com.exaple.splitwise_clone.pravinTrialAndError.FragmentMainActivity
import kotlinx.android.synthetic.main.activity_login__screen_.*


class Login_Screen_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login__screen_)


        btnBackLogin.setOnClickListener {

            val intent= Intent(this,SplitWiseScreen::class.java)
            startActivity(intent)
        }

        btnDoneLogin.setOnClickListener {
            val intent2 = Intent(this, FragmentMainActivity::class.java)
            startActivity(intent2)
        }




    }
}