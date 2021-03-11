package com.exaple.splitwise_clone.menubartrail.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerSampleAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {

                return FriendsFragment()
            }
            1 -> {
                return GroupsFragment()
            }
            2 -> {

                return ActivityFragment()
            }
            else -> return null!!
        }
    }

    override fun getCount(): Int {
      return totalTabs
    }


}