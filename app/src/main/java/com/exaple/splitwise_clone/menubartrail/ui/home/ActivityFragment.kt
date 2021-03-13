package com.exaple.splitwise_clone.menubartrail.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionEntity
import com.exaple.splitwise_clone.vinod.database.sharedpreferences.PreferenceHelper
import com.exaple.splitwise_clone.vinod.recyclerviews.friendtransaction.FriendTransactionAdapter
import com.exaple.splitwise_clone.vinod.viewmodels.*
import com.exaple.splitwise_clone.vinod.views.SplitwiseApplication
import kotlinx.android.synthetic.main.fragment_activity.*

class ActivityFragment : Fragment() {
    private lateinit var preferenceHelper: PreferenceHelper

    //viewModels
    private lateinit var userViewModel: UserViewModel
    private lateinit var groupViewModel: GroupViewModel
    private lateinit var friendTransactionViewModel: FriendTransactionViewModel
    private lateinit var transactionViewModel: GroupTransactionViewModel

    private var transactionList = mutableListOf<FriendTransactionEntity>()

    private var friendTransactionAdapter = FriendTransactionAdapter(transactionList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        preferenceHelper = PreferenceHelper(container?.context!!)
        createDatabase()
        getTransactions()
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvTransaction.layoutManager = LinearLayoutManager(view.context)
        rvTransaction.adapter = friendTransactionAdapter
    }

    override fun onResume() {
        super.onResume()
        getTransactions()
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

    fun getTransactions() {
        friendTransactionViewModel.getFriendTransactionsList()
            .observe(viewLifecycleOwner, Observer {
                transactionList.clear()
                for (i in it) {
                    if (i.user_id == preferenceHelper.readIntFromPreference(SplitwiseApplication.PREF_USER_ID)) {
                        transactionList.add(i)
                    }
                }
                friendTransactionAdapter.notifyDataSetChanged()
            })
    }
}