package com.exaple.splitwise_clone.menubartrail.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.exaple.splitwise_clone.R
import com.google.android.material.tabs.TabLayout

class HomeFragment() : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

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

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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