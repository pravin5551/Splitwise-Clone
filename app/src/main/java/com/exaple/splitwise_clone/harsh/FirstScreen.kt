package com.exaple.splitwise_clone.harsh

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.menubartrail.MenuMainActivity
import com.exaple.splitwise_clone.vinod.database.sharedpreferences.PreferenceHelper
import com.exaple.splitwise_clone.vinod.views.SplitwiseApplication


class FirstScreen : AppCompatActivity() {

    private val preferenceHelper = PreferenceHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        object : CountDownTimer(5000, 2000) {
            override fun onFinish() {
                if (!preferenceHelper.readBooleanFromPreference(SplitwiseApplication.PREF_IS_USER_LOGIN)) {
                    val intent = Intent(this@FirstScreen, SplitWiseScreen::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this@FirstScreen, MenuMainActivity::class.java)
                    startActivity(intent)
                }
                finish()
            }
            override fun onTick(millisUntilFinished: Long) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start()

    }
}