package com.exaple.splitwise_clone.menubartrail.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionEntity
import com.exaple.splitwise_clone.vinod.database.sharedpreferences.PreferenceHelper
import com.exaple.splitwise_clone.vinod.recyclerviews.contactlist.ContactListAdapter
import com.exaple.splitwise_clone.vinod.viewmodels.*
import com.exaple.splitwise_clone.vinod.views.SplitwiseApplication
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_friends.*


class HomeFragment() : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null


    private lateinit var transactionAdapter: ContactListAdapter
    private var transactionList = mutableListOf<FriendTransactionEntity>()

    //viewModels
    private lateinit var userViewModel: UserViewModel
    private lateinit var groupViewModel: GroupViewModel
    private lateinit var friendTransactionViewModel: FriendTransactionViewModel
    private lateinit var transactionViewModel: GroupTransactionViewModel

    //preferenceHelper
    private lateinit var preferenceHelper: PreferenceHelper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })
        handleViewPager(root)
        preferenceHelper = PreferenceHelper(root.context)
        createDatabase()

        return root
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
        userViewModel.getUserList().observe(viewLifecycleOwner, Observer {
            var x = 0
            for (i in it) {
                if (i.id == preferenceHelper.readIntFromPreference(SplitwiseApplication.PREF_USER_ID)) {
                    x = i.owe.toInt()
                }
            }
            if (x == 0) {
                tvSettledUp.text = "you are all settled up"
                tvSettledUp.setTextColor(Color.parseColor("#000000"))
            } else if (x > 0) {
                tvSettledUp.text = "You owe \u20B9 $x "
                tvSettledUp.setTextColor(Color.parseColor("#FF5216"))
            } else {
                tvSettledUp.text = "You owes \u20B9 $x "
                tvSettledUp.setTextColor(Color.parseColor("#00ff00"))
            }
        })
        var hashMap = HashMap<String, Int>()
        friendTransactionViewModel.getFriendTransactionsList()
            .observe(viewLifecycleOwner, Observer {
                transactionList.clear()
                for (i in it) {
                    if (i.user_id == preferenceHelper.readIntFromPreference(SplitwiseApplication.PREF_USER_ID)) {
                        if (hashMap.containsKey(i.name)) {
                            val x = hashMap.get(i.name)?.plus(i.amount)
                            hashMap.put(i.name, x!!)
                            for (j in 0 until transactionList.size) {
                                if (transactionList[j].name == i.name) {
                                    i.amount = x
                                    transactionList.set(j, i)
                                    break
                                }
                            }
                        } else {
                            transactionList.add(i)
                            hashMap.put(i.name, i.amount)
                        }
                    }
                }
                transactionAdapter = ContactListAdapter(transactionList)
                listOfUserContacts.layoutManager =
                    LinearLayoutManager(activity?.applicationContext)
                listOfUserContacts.adapter = transactionAdapter
            })
    }

    private fun handleViewPager(view: View) {
        tabLayout = view.findViewById<TabLayout>(R.id.HomeTablayout)
        viewPager = view.findViewById<ViewPager>(R.id.HomeviewPager)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("FRIENDS"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("GROUPS"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("ACTIVITY"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter =
            ViewPagerSampleAdapter(view.context, childFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = adapter
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

}