package com.exaple.splitwise_clone.harsh

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.exaple.splitwise_clone.R


class FirstScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        object : CountDownTimer(5000, 2000) {
            override fun onFinish() {
                val intent=Intent(this@FirstScreen,SplitWiseScreen::class.java)
                startActivity(intent)
            }

            override fun onTick(millisUntilFinished: Long) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start()

    }
}