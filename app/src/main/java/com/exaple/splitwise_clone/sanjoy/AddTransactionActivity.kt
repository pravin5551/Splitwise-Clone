package com.exaple.splitwise_clone.sanjoy

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.vinod.database.groups.GroupEntity
import com.exaple.splitwise_clone.vinod.database.sharedpreferences.PreferenceHelper
import com.exaple.splitwise_clone.vinod.database.transactions.TransactionEntity
import com.exaple.splitwise_clone.vinod.viewmodels.*
import com.exaple.splitwise_clone.vinod.views.SplitwiseApplication
import kotlinx.android.synthetic.main.activity_add_transaction.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddTransactionActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var groupViewModel: GroupViewModel
    private lateinit var friendTransactionViewModel: FriendTransactionViewModel
    private lateinit var transactionViewModel: GroupTransactionViewModel
    private val preferenceHelper = PreferenceHelper(this)
    private lateinit var groupEntity: GroupEntity

    override fun onResume() {
        super.onResume()
        getGroupEntity()
    }

    private fun getGroupEntity() {
        groupViewModel.getGroupList().observe(this, Observer {
            for (i in it) {
                if (i.id == intent.getIntExtra("id", 0)) {
                    groupEntity = i
                    break
                }
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)
        createDatabase()
        etNameEmailPhone.text = intent.getStringExtra("name")
        tvBackArrow.setOnClickListener {
            finish()
        }
        addAllUsers.setOnClickListener {
            var amt = 0
            if (intent.getIntExtra("size", 1) != 0) {
                amt = tvAmount.text.toString().toInt() / intent.getIntExtra("size", 1)
            }
            amt = (tvAmount.text.toString().toInt() - amt)
            val dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
            val entity = TransactionEntity(
                intent.getIntExtra("id", 0),
                preferenceHelper.readIntFromPreference(SplitwiseApplication.PREF_USER_ID),
                dtf.format(LocalDateTime.now()),
                amt,
                tvAmount.text.toString().toInt(),
                "INR",
                tvDescription.text.toString()
            )
            transactionViewModel.addTransaction(entity)
            val x = groupEntity.amt
            groupEntity.amt = x + amt
            groupViewModel.updateGroup(groupEntity)
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