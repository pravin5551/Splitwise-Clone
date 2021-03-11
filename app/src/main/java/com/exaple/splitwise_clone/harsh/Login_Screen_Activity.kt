package com.exaple.splitwise_clone.harsh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.menubartrail.ui.home.ActivityFragment
import com.exaple.splitwise_clone.pravinTrialAndError.FragmentMainActivity
import com.exaple.splitwise_clone.vinod.database.sharedpreferences.PreferenceHelper
import com.exaple.splitwise_clone.vinod.viewmodels.UserViewModel
import com.exaple.splitwise_clone.vinod.viewmodels.UserViewModelFactory
import com.exaple.splitwise_clone.vinod.views.SplitwiseApplication
import kotlinx.android.synthetic.main.activity_login__screen_.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Login_Screen_Activity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private val preferenceHelper = PreferenceHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login__screen_)
        etEmail.editText?.setText(intent.getStringExtra("email").toString())
        createDatabase()
        btnBackLogin.setOnClickListener {
            val intent = Intent(this, SplitWiseScreen::class.java)
            startActivity(intent)
        }

        btnDoneLogin.setOnClickListener {
            val email = etEmail.editText?.text.toString()
            val password = etPassword.editText?.text.toString()
            userViewModel.getUserList().observe(this, Observer {
                for (i in it) {
                    if (i.email == email
                        && i.password == password
                    ) {
                        val intent2 = Intent(this, FragmentMainActivity::class.java)
                        intent2.putExtra("name", i.name)
                        preferenceHelper.writeIntToPreference(
                            SplitwiseApplication.PREF_USER_ID,
                            i.id!!
                        )
                        preferenceHelper.writeBooleanToPreference(
                            SplitwiseApplication.PREF_IS_USER_LOGIN,
                            true
                        )
                        startActivity(intent2)
                        this.finish()
                        break
                    }
                }
                Toast.makeText(this, "Email or Password is wrong", Toast.LENGTH_SHORT).show()
            })
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