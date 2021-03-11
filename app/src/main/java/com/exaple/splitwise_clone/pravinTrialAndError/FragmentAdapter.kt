package com.exaple.splitwise_clone.pravinTrialAndError

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


/**
 * This class gives the fragments as the user swipes the screen horizontally
 */
class FragmentAdapter(fragmentActivity: FragmentActivity?,val name:String) :
    FragmentStateAdapter(fragmentActivity!!) {
    /**
     * This method is called when the user swipes the screen horizontally
     *
     * @param position position of the screen user wants to see
     * @return returns a fragment based on the position
     */
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return FragmentA.newInstance(name)
            1 -> return FragmentB.newInstance("This is second Fragment")
            2 -> return FragmentC.newInstance("This is Third Fragment")
            3 -> return FragmentD.newInstance("This is Third Fragment")

        }
        return FragmentA.newInstance("This is Default Fragment")
    }

    override fun getItemCount(): Int {
        return 4
    }
}
