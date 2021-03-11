package com.exaple.splitwise_clone.harsh

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.pravinTrialAndError.FragmentMainActivity
import com.exaple.splitwise_clone.vinod.database.sharedpreferences.PreferenceHelper
import com.exaple.splitwise_clone.vinod.database.users.UserEntity
import com.exaple.splitwise_clone.vinod.viewmodels.*
import com.exaple.splitwise_clone.vinod.views.SplitwiseApplication
import kotlinx.android.synthetic.main.activity_login__screen_.*
import kotlinx.android.synthetic.main.activity_sign_up__screen_.*

class Sign_up_Screen_Activity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up__screen_)

        btnBackSign.setOnClickListener {
            val intent = Intent(this, SplitWiseScreen::class.java)
            startActivity(intent)
            this.finish()
        }

        btnDoneSign.setOnClickListener {
            if (etSignUpFullName.text.isNotEmpty() && etSignUpEmail.text.isNotEmpty()
                && etSignUpPassword.text.isNotEmpty()
            ) {

                createDatabase()
                val userEntity = UserEntity(
                    etSignUpFullName.text.toString(),
                    etSignUpPhone.text.toString(),
                    etSignUpEmail.text.toString(), etSignUpPassword.text.toString(),
                    "", "", "", 0
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
}