package com.exaple.splitwise_clone.harsh

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.menubartrail.MenuMainActivity
import com.exaple.splitwise_clone.vinod.database.sharedpreferences.PreferenceHelper
import com.exaple.splitwise_clone.vinod.views.SplitwiseApplication
import java.util.*
import kotlin.concurrent.timerTask


class FirstScreen : AppCompatActivity() {

    private val preferenceHelper = PreferenceHelper(this)
    private lateinit var screen: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)
        screen.setOnClickListener {
            val intent = Intent(this, SplitWiseScreen::class.java)
            startActivity(intent)
        }


//        object : CountDownTimer(3000, 1000) {
//            override fun onFinish() {
//                if (!preferenceHelper.readBooleanFromPreference(SplitwiseApplication.PREF_IS_USER_LOGIN)) {
//                    val intent = Intent(this@FirstScreen, SplitWiseScreen::class.java)
//                    startActivity(intent)
//                } else {
//                    val intent = Intent(this@FirstScreen, MenuMainActivity::class.java)
//                    startActivity(intent)
//                }
//                finish()
//            }
//
//            override fun onTick(millisUntilFinished: Long) {
//                // millisUntilFinished    The amount of time until finished.
//            }
//
//        }.start()

        val timer = Timer()
        timer.schedule(timerTask {val intent=Intent(this@FirstScreen,SplitWiseScreen::class.java)
            startActivity(intent) }, 3000)

    }
}