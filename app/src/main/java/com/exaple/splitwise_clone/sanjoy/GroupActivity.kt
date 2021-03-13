package com.exaple.splitwise_clone.sanjoy

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.vinod.database.groups.GroupEntity
import com.exaple.splitwise_clone.vinod.database.sharedpreferences.PreferenceHelper
import com.exaple.splitwise_clone.vinod.viewmodels.*
import com.exaple.splitwise_clone.vinod.views.SplitwiseApplication
import kotlinx.android.synthetic.main.activity_group.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GroupActivity : AppCompatActivity() {

    //viewModels
    private lateinit var userViewModel: UserViewModel
    private lateinit var groupViewModel: GroupViewModel
    private lateinit var friendTransactionViewModel: FriendTransactionViewModel
    private lateinit var transactionViewModel: GroupTransactionViewModel

    var type = "apartment"
    private val preferenceHelper = PreferenceHelper(this)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)
        createDatabase()
        tvApartment.setOnClickListener {
            type = "apartment"
            tvApartment.setBackgroundColor(Color.parseColor("#1CC29F"))
            tvHouse.setBackgroundColor(Color.parseColor("#4b4b4b"))
            tvTrip.setBackgroundColor(Color.parseColor("#4b4b4b"))
            tvOther.setBackgroundColor(Color.parseColor("#4b4b4b"))
        }
        tvHouse.setOnClickListener {
            type = "house"
            tvHouse.setBackgroundColor(Color.parseColor("#1CC29F"))
            tvApartment.setBackgroundColor(Color.parseColor("#4b4b4b"))
            tvTrip.setBackgroundColor(Color.parseColor("#4b4b4b"))
            tvOther.setBackgroundColor(Color.parseColor("#4b4b4b"))
        }
        tvTrip.setOnClickListener {
            type = "trip"
            tvTrip.setBackgroundColor(Color.parseColor("#1CC29F"))
            tvHouse.setBackgroundColor(Color.parseColor("#4b4b4b"))
            tvApartment.setBackgroundColor(Color.parseColor("#4b4b4b"))
            tvOther.setBackgroundColor(Color.parseColor("#4b4b4b"))
        }
        tvOther.setOnClickListener {
            type = "other"
            tvOther.setBackgroundColor(Color.parseColor("#1CC29F"))
            tvHouse.setBackgroundColor(Color.parseColor("#4b4b4b"))
            tvTrip.setBackgroundColor(Color.parseColor("#4b4b4b"))
            tvApartment.setBackgroundColor(Color.parseColor("#4b4b4b"))
        }

        saveGroup.setOnClickListener {
            val dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
            val groupEntity = GroupEntity(
                etGroupName.text.toString(),
                etSize.text.toString().toInt(),
                type,
                0,
                dtf.format(LocalDateTime.now()),
                preferenceHelper.readIntFromPreference(SplitwiseApplication.PREF_USER_ID)
            )
            groupViewModel.addGroup(groupEntity)
            finish()
        }
        tvBackArrow.setOnClickListener {
            finish()
        }
    }

    private fun createDatabase() {

        val appClass = application as SplitwiseApplication
        val userRepository = appClass.userRepository
        val groupRepository = appClass.groupRepository
        val transactionRepository = appClass.transactionRepository
        val friendRepository = appClass.friendsRepository

        val userViewModelFactory = UserViewModelFactory(userRepository)
        val groupViewModelFactory = GroupViewModelFactory(groupRepository)
        val transactionViewModelFactory = GroupTransactionViewModelFactory(transactionRepository)
        val friendViewModelFactory = FriendTransactionViewModelFactory(friendRepository)

        userViewModel = ViewModelProviders.of(this, userViewModelFactory)
            .get(UserViewModel::class.java)

        groupViewModel = ViewModelProviders.of(this, groupViewModelFactory)
            .get(GroupViewModel::class.java)

        friendTransactionViewModel = ViewModelProviders.of(this, friendViewModelFactory)
            .get(FriendTransactionViewModel::class.java)

        transactionViewModel = ViewModelProviders.of(this, transactionViewModelFactory)
            .get(GroupTransactionViewModel::class.java)
    }

}