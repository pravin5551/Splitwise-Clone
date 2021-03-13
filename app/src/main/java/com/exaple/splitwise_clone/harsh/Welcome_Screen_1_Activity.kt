package com.exaple.splitwise_clone.harsh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.menubartrail.MenuMainActivity
import kotlinx.android.synthetic.main.activity_welcome__screen_1_.*

class Welcome_Screen_1_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome__screen_1_)
        btnAddYourApartment.setOnClickListener {
            startActivity(Intent(this, MenuMainActivity::class.java))
        }
        btnAddYourGroupTrip.setOnClickListener {
            startActivity(Intent(this, MenuMainActivity::class.java))

        }
        btnSkipForNow.setOnClickListener {
            startActivity(Intent(this, MenuMainActivity::class.java))

        }
    }
}