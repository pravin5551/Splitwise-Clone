package com.exaple.splitwise_clone.menubartrail.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.sanjoy.GroupActivity
import com.exaple.splitwise_clone.vinod.database.groups.GroupEntity
import com.exaple.splitwise_clone.vinod.database.sharedpreferences.PreferenceHelper
import com.exaple.splitwise_clone.vinod.database.transactions.TransactionEntity
import com.exaple.splitwise_clone.vinod.recyclerviews.grouptransaction.GroupTransactionAdapter
import com.exaple.splitwise_clone.vinod.viewmodels.*
import com.exaple.splitwise_clone.vinod.views.SplitwiseApplication
import kotlinx.android.synthetic.main.fragment_groups.*
import java.lang.Math.abs

class GroupsFragment : Fragment() {

    //viewModels
    private lateinit var userViewModel: UserViewModel
    private lateinit var groupViewModel: GroupViewModel
    private lateinit var friendTransactionViewModel: FriendTransactionViewModel
    private lateinit var transactionViewModel: GroupTransactionViewModel

    private var groupList = mutableListOf<GroupEntity>()
    private var transactionList = mutableListOf<TransactionEntity>()
    lateinit var preferenceHelper: PreferenceHelper

    private var groupAdapter = GroupTransactionAdapter(groupList)
    private var totalAmount = 0
    override fun onResume() {
        super.onResume()
        getGroups()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        createDatabase()
        return inflater!!.inflate(R.layout.fragment_groups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceHelper = PreferenceHelper(view.context)
        listOfGroups.layoutManager = LinearLayoutManager(view.context)
        listOfGroups.adapter = groupAdapter
        getGroups()
        cvvvv.setOnClickListener {
            startActivity(Intent(it.context, GroupActivity::class.java))
        }
        if (totalAmount == 0) {
            tvSettledUp.text = "All settled up"
        } else if (totalAmount > 0) {
            tvSettledUp.text = "Owes you $totalAmount"
        } else
            tvSettledUp.text = "you Owe ${abs(totalAmount)}"
    }

    private fun createDatabase() {

        val appClass = activity?.application as SplitwiseApplication
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

    fun getGroups() {
        groupViewModel.getGroupList().observe(viewLifecycleOwner, Observer {
            groupList.clear()
            for (i in it) {
                if (i.creator == preferenceHelper.readIntFromPreference(SplitwiseApplication.PREF_USER_ID)) {
                    groupList.add(i)
                    totalAmount += i.amt
                }
            }
            groupAdapter.notifyDataSetChanged()
        })
    }
}