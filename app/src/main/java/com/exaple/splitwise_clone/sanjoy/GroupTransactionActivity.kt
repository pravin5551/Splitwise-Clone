package com.exaple.splitwise_clone.sanjoy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.vinod.database.groups.GroupEntity
import com.exaple.splitwise_clone.vinod.database.sharedpreferences.PreferenceHelper
import com.exaple.splitwise_clone.vinod.database.transactions.TransactionEntity
import com.exaple.splitwise_clone.vinod.recyclerviews.transactions.TransactionAdapter
import com.exaple.splitwise_clone.vinod.viewmodels.*
import com.exaple.splitwise_clone.vinod.views.SplitwiseApplication
import kotlinx.android.synthetic.main.activity_group_transaction.*

class GroupTransactionActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var groupViewModel: GroupViewModel
    private lateinit var friendTransactionViewModel: FriendTransactionViewModel
    private lateinit var transactionViewModel: GroupTransactionViewModel
    private val preferenceHelper = PreferenceHelper(this)
    private var transactionList = mutableListOf<TransactionEntity>()
    private lateinit var groupEntity: GroupEntity

    var transactionAdapter = TransactionAdapter(transactionList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_transaction)
        createDatabase()
        getTransactions(intent.getIntExtra("id", 0))
        transactionRecyclerView.layoutManager = LinearLayoutManager(this)
        transactionRecyclerView.adapter = transactionAdapter
        tvBackArrow.setOnClickListener {
            finish()
        }


        fabAddTransaction.setOnClickListener {
            val intent2 = Intent(this, AddTransactionActivity::class.java)
            intent2.putExtra("name", groupEntity.name)
            intent2.putExtra("id", groupEntity.id)
            intent2.putExtra("amt", groupEntity.name)
            intent2.putExtra("type", groupEntity.type)
            intent2.putExtra("size", groupEntity.no)
            startActivity(intent2)
        }
    }

    private fun getTransactions(id: Int?) {
        groupViewModel.getGroupList().observe(this, Observer {
            for (i in it) {
                if (i.id == id) {
                    groupEntity = i
                    tvTransactionGroupName.text = groupEntity.name
                    break
                }
            }
        })
        transactionViewModel.getTransactionsList().observe(this, Observer {
            transactionList.clear()
            for (i in it) {
                if (i.group_id == id) {
                    transactionList.add(i)
                }
            }
            transactionAdapter.notifyDataSetChanged()
        })
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