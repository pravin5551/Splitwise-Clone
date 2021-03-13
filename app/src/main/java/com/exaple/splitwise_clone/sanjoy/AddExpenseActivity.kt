package com.exaple.splitwise_clone.sanjoy

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.menubartrail.MenuMainActivity
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionEntity
import com.exaple.splitwise_clone.vinod.database.sharedpreferences.PreferenceHelper
import com.exaple.splitwise_clone.vinod.database.users.UserEntity
import com.exaple.splitwise_clone.vinod.recyclerviews.ContactCommunicator
import com.exaple.splitwise_clone.vinod.recyclerviews.ContactTempAddAdapter
import com.exaple.splitwise_clone.vinod.recyclerviews.ContactTempModel
import com.exaple.splitwise_clone.vinod.viewmodels.*
import com.exaple.splitwise_clone.vinod.views.SplitwiseApplication
import kotlinx.android.synthetic.main.activity_add_expense.*
import kotlinx.android.synthetic.main.activity_add_expense.lvContacts
import kotlinx.android.synthetic.main.activity_add_expense.rvContactAddTemp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddExpenseActivity : AppCompatActivity(), ContactCommunicator {

    private val REQ_CODE = 1
    private lateinit var cursor: Cursor
    private var contactList = mutableListOf<ContactTempModel>()
    private var usersList = mutableListOf<UserEntity>()
    private lateinit var contactAdapter: ContactTempAddAdapter
    private lateinit var to: IntArray
    private val preferenceHelper = PreferenceHelper(this)
    private lateinit var friendTransactionViewModel: FriendTransactionViewModel
    private lateinit var userViewModel: UserViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)
        createDatabase()
        getUsersList()
        tvBackArrow.setOnClickListener {
            finish()
        }
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS
            ), REQ_CODE
        )
        ibClose.setOnClickListener {
            lvContacts.visibility = View.GONE
            ibClose.visibility = View.GONE
            containerLayout.visibility = View.VISIBLE
        }
        etNameEmailPhone.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                lvContacts.visibility = View.VISIBLE
                ibClose.visibility = View.VISIBLE
                containerLayout.visibility = View.GONE
            } else {
                lvContacts.visibility = View.GONE
                ibClose.visibility = View.GONE
                containerLayout.visibility = View.VISIBLE
            }
        }

        lvContacts.setOnItemClickListener { parent, view, position, id ->
            var cTM =
                ContactTempModel(
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                    cursor.getString(cursor.getColumnIndex("DISPLAY_NAME"))
                )
            var flag = true
            for (i in contactList) {
                if (i.name == cTM.name && i.number == cTM.number) {
                    Toast.makeText(this, "Already Added", Toast.LENGTH_SHORT).show()
                    flag = false
                    break;
                }
            }
            if (flag) {
                contactList.add(cTM)
                contactAdapter =
                    ContactTempAddAdapter(
                        contactList,
                        this
                    )
                rvContactAddTemp.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                rvContactAddTemp.adapter = contactAdapter
            }
        }
        addAllUsers.setOnClickListener {
            if (contactList.size != 0 &&
                preferenceHelper.readBooleanFromPreference(SplitwiseApplication.PREF_IS_USER_LOGIN)
            ) {
                val dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
                var eachShare = 0
                if (tvAmount.text.toString().toInt() > 0) {
                    eachShare = tvAmount.text.toString().toInt() / (contactList.size + 1)

                    for (i in 0 until contactList.size) {
                        val friendTransactionEntity =
                            FriendTransactionEntity(
                                preferenceHelper.readIntFromPreference(SplitwiseApplication.PREF_USER_ID),
                                contactList[i].name,
                                contactList[i].number,
                                eachShare,
                                dtf.format(LocalDateTime.now()),
                                tvDescription.text.toString()
                            )
                        friendTransactionViewModel.addFriendTransaction(friendTransactionEntity)
                        Toast.makeText(
                            applicationContext,
                            "Friends Added",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    lvContacts.visibility = View.GONE
                    contactList.clear()
                    contactAdapter.notifyDataSetChanged()
                    lateinit var x: UserEntity
                    getUsersList()
                    usersList.forEach {
                        if (it.id == preferenceHelper.readIntFromPreference(SplitwiseApplication.PREF_USER_ID)) {
                            x = it
                            x.owe = (x.owe.toInt() + (tvAmount.text.toString()
                                .toInt() - eachShare)).toString()
                            Log.d(
                                "TAG",
                                "onCreate: " + (tvAmount.text.toString().toInt() - eachShare)
                            )
                            userViewModel.updateUser(x)
                            Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT)
                                .show()
                            val intent2 = Intent(this, MenuMainActivity::class.java)
                            startActivity(intent2)
                            this.finish()
                        }
                    }

                } else {
                    Toast.makeText(this, "Invalid Amount", Toast.LENGTH_SHORT).show()
                    val intent2 = Intent(this, MenuMainActivity::class.java)
                    startActivity(intent2)
                    this.finish()
                }
            }
        }
    }

    private fun getUsersList() {
        userViewModel.getUserList().observe(this, Observer {
            usersList.clear()
            usersList.addAll(it)
        })
    }

    private fun fetchContacts() {

        val cursor: Cursor? = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        this.cursor = cursor!!
        startManagingCursor(cursor)

        val from = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone._ID
        )

        to = IntArray(2)
        to[0] = android.R.id.text1
        to[1] = android.R.id.text2

        val simpleCursorAdapter =
            SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from, to)

        lvContacts.adapter = simpleCursorAdapter
        lvContacts.choiceMode = ListView.CHOICE_MODE_MULTIPLE
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQ_CODE -> {
                if (grantResults.isNotEmpty()) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        fetchContacts()
                    } else {
                        Toast.makeText(
                            this,
                            "Contact Permission Denied",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
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

    override fun onContactDelete(tempModel: ContactTempModel) {
        var x = 0
        for (i in contactList) {
            if (i.name == tempModel.name && i.number == tempModel.number) {
                contactList.removeAt(x)
                break;
            }
            x++
        }
        contactAdapter.notifyDataSetChanged()
    }
}