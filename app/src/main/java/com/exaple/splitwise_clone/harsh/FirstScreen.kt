package com.exaple.splitwise_clone.harsh

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.exaple.splitwise_clone.R
import kotlinx.android.synthetic.main.activity_first_screen.*
import java.util.*
import kotlin.concurrent.timerTask


class FirstScreen : AppCompatActivity() {

    private lateinit var screen: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)
        screen=findViewById(R.id.ivScreen)

        screen.setOnClickListener {

            val intent=Intent(this,SplitWiseScreen::class.java)
            startActivity(intent)
        }


        val timer = Timer()
        timer.schedule(timerTask {val intent=Intent(this@FirstScreen,SplitWiseScreen::class.java)
            startActivity(intent) }, 3000)






//        object : CountDownTimer(5000, 1000) {
//            override fun onFinish() {
//
//            }
//
//            override fun onTick(millisUntilFinished: Long) {
//                // millisUntilFinished    The amount of time until finished.
//            }
//        }.start()

    }
}