package com.exaple.splitwise_clone.pravinTrialAndError

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.exaple.splitwise_clone.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_fragment_main.*

class FragmentMainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener{

    private var mViewPager: ViewPager2? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_main)
        mViewPager = findViewById(R.id.viewPager)

        setViewPager()

    }

    private fun setViewPager() {
        val fragmentAdapter=FragmentAdapter(this)

        mViewPager?.adapter = fragmentAdapter


        /*
        A mediator to link a TabLayout with a ViewPager2. The mediator will synchronize the ViewPager2's position
         with the selected tab when a tab is selected, and the TabLayout's scroll position when the user drags the ViewPager2.
         */TabLayoutMediator(
            tabLayout, mViewPager!!
        )
        { tab, position -> Log.d("Splitwise", "onConfigureTab called") }.attach()

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }
}