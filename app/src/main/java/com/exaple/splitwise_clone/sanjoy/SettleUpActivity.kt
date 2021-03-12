package com.exaple.splitwise_clone.sanjoy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionEntity
import com.exaple.splitwise_clone.vinod.database.sharedpreferences.PreferenceHelper
import com.exaple.splitwise_clone.vinod.database.users.UserEntity
import com.exaple.splitwise_clone.vinod.recyclerviews.friendtransaction.FriendTransactionAdapter
import com.exaple.splitwise_clone.vinod.viewmodels.FriendTransactionViewModel
import com.exaple.splitwise_clone.vinod.viewmodels.FriendTransactionViewModelFactory
import com.exaple.splitwise_clone.vinod.viewmodels.UserViewModel
import com.exaple.splitwise_clone.vinod.viewmodels.UserViewModelFactory
import com.exaple.splitwise_clone.vinod.views.SplitwiseApplication
import kotlinx.android.synthetic.main.activity_settle_up.*

class SettleUpActivity : AppCompatActivity() {

    private var usersList = mutableListOf<UserEntity>()
    private val preferenceHelper = PreferenceHelper(this)
    private lateinit var friendTransactionViewModel: FriendTransactionViewModel
    private lateinit var userViewModel: UserViewModel
    private var friendTransactionList = mutableListOf<FriendTransactionEntity>()
    private lateinit var userEntity: UserEntity
    private lateinit var friendTransactionAdapter: FriendTransactionAdapter
    val temp = mutableListOf<FriendTransactionEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settle_up)
        val name = intent.getStringExtra("name")
        val amount = intent.getIntExtra("amt", 0)
        tvProfileName.text = name
        val str = intent.getStringExtra("number")
        var number = ""
        for (i in str!!.indices) {
            if (str[i] in '0'..'9') {
                number += str[i]
            }
        }
        tvProfileEmail.text = number
        tvNameOwesYou.text = "${name} owes you INR(₹) ${amount}"

        createDatabase()
        getUsersList()
        getTransactions()
        getUserAndTransactions()

        settleupAll.setOnClickListener {
            getTransactions()
            if (temp.size >= 1){
                for (i in temp) {
                    i.amount = 0
                    friendTransactionViewModel.updateFriendTransaction(i)
                }
                userEntity.owe = (userEntity.owe.toInt() - amount).toString()
                userViewModel.updateUser(userEntity)
            }
        }
    }

    private fun getUserAndTransactions() {
        Log.d("TAG", "getUserAndTransactions: " + friendTransactionList.size)
    }

    private fun getTransactions() {
        friendTransactionViewModel.getFriendTransactionsList().observe(this, Observer {
            friendTransactionList.clear()
            friendTransactionList.addAll(it)
            Log.d("TAG", "getTransactions: " + friendTransactionList.size)
            for (i in friendTransactionList) {
                if (i.user_id == preferenceHelper.readIntFromPreference(SplitwiseApplication.PREF_USER_ID)
                    && i.name == intent.getStringExtra("name")
                ) {
                    temp.add(i)
                }
            }
            Log.d("TAG", "getUserAndTransactions: " + temp.size)

            friendTransactionAdapter = FriendTransactionAdapter(temp)
            friendTransactionRecyclerView.layoutManager = LinearLayoutManager(this)
            friendTransactionRecyclerView.adapter = friendTransactionAdapter
            friendTransactionAdapter.notifyDataSetChanged()
        })
    }
//    private fun showNotesDialog() {
//        ivNotes.setOnClickListener{
//
//            val builder = AlertDialog.Builder(this)
//            val inflater = layoutInflater
//            val dialogLayout = inflater.inflate(R.layout.add_notes,null)
//            val editText  = dialogLayout.findViewById<EditText>(R.id.etNotes)
//
//
//            with(builder){
//                setTitle("Add notes")
//                setPositiveButton("DONE"){dialog, which ->
//
//                }
//                setNegativeButton("CANCEL"){dialog, which ->
//
//                }
//                setView(dialogLayout)
//                show()
//            }
//        }
//    }

    private fun getUsersList() {
        userViewModel.getUserList().observe(this, Observer {
            usersList.clear()
            usersList.addAll(it)
            for (i in usersList) {
                if (i.id == preferenceHelper.readIntFromPreference(SplitwiseApplication.PREF_USER_ID)) {
                    userEntity = i
                    break
                }
            }
        })
    }

    private fun createDatabase() {
        val appClass = application as SplitwiseApplication
        val friendRepository = appClass.friendsRepository
        val friendViewModelFactory = FriendTransactionViewModelFactory(friendRepository)
        val userRepository = appClass.userRepository
        val userViewModelFactory = UserViewModelFactory(userRepository)

        userViewModel = ViewModelProviders.of(this, userViewModelFactory)
            .get(UserViewModel::class.java)
        friendTransactionViewModel = ViewModelProviders.of(this, friendViewModelFactory)
            .get(FriendTransactionViewModel::class.java)
    }

}