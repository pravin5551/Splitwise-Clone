package com.exaple.splitwise_clone.sanjoy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.menubartrail.ui.home.ViewPagerSampleAdapter
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        tabLayout = findViewById<TabLayout>(R.id.HomeTablayout)
        viewPager = findViewById<ViewPager>(R.id.HomeviewPager)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("FRIENDS"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("GROUPS"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("ACTIVITY"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL


        val adapter = ViewPagerSampleAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
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