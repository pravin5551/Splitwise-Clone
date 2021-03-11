package com.exaple.splitwise_clone.vinod.views

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionEntity
import com.exaple.splitwise_clone.vinod.database.sharedpreferences.PreferenceHelper
import com.exaple.splitwise_clone.vinod.recyclerviews.ContactCommunicator
import com.exaple.splitwise_clone.vinod.recyclerviews.ContactTempAddAdapter
import com.exaple.splitwise_clone.vinod.recyclerviews.ContactTempModel
import com.exaple.splitwise_clone.vinod.recyclerviews.contactlist.ContactListAdapter
import com.exaple.splitwise_clone.vinod.viewmodels.*
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity(), ContactCommunicator {

    private val REQ_CODE = 1
    private lateinit var to: IntArray
    private lateinit var cursor: Cursor
    private lateinit var contactAdapter: ContactTempAddAdapter
    private lateinit var transactionAdapter: ContactListAdapter

    private var contactList = mutableListOf<ContactTempModel>()
    private var transactionList = mutableListOf<FriendTransactionEntity>()

    //viewModels
    private lateinit var userViewModel: UserViewModel
    private lateinit var groupViewModel: GroupViewModel
    private lateinit var friendTransactionViewModel: FriendTransactionViewModel
    private lateinit var transactionViewModel: GroupTransactionViewModel

    //preferenceHelper
    private val preferenceHelper = PreferenceHelper(this)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferenceHelper.writeIntToPreference(SplitwiseApplication.PREF_USER_ID, 1)
        preferenceHelper.writeBooleanToPreference(SplitwiseApplication.PREF_IS_USER_LOGIN, true)

        createDatabase()

        btnFetchContacts.setOnClickListener {
            lvContacts.visibility = View.VISIBLE
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS
                ), REQ_CODE
            )
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

        btnAddUsers.setOnClickListener {

        }

        addAllUsers.setOnClickListener {
            if (contactList.size != 0 &&
                preferenceHelper.readBooleanFromPreference(SplitwiseApplication.PREF_IS_USER_LOGIN)
            ) {
                val dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
                for (i in 0 until contactList.size) {
                    val friendTransactionEntity =
                        FriendTransactionEntity(
                            preferenceHelper.readIntFromPreference(SplitwiseApplication.PREF_USER_ID)
                            , contactList[i].name, contactList[i].number, 0,
                            dtf.format(LocalDateTime.now()), "etc..."
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
                transactionAdapter.notifyDataSetChanged()
            }
        }
        getTransactions()
    }

    private fun getTransactions() {
        friendTransactionViewModel.getFriendTransactionsList().observe(this, Observer {
            transactionList.clear()
            for (i in it) {
                if (i.user_id == preferenceHelper.readIntFromPreference(SplitwiseApplication.PREF_USER_ID)) {
                    transactionList.add(i)
                }
            }
            transactionAdapter = ContactListAdapter(transactionList)
            listOfUserContacts.layoutManager =
                LinearLayoutManager(this)
            listOfUserContacts.adapter = transactionAdapter
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