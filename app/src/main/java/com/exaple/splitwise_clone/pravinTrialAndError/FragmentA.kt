package com.exaple.splitwise_clone.pravinTrialAndError

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.exaple.splitwise_clone.R
import kotlinx.android.synthetic.main.fragment_a.view.*

class FragmentA : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.welcomeName.text = "Welcome to Splitwise, \n${FragmentMainActivity.name}!"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }


    companion object {
        var ARG_PARAM1 = ""

        fun newInstance(param1: String?): FragmentA {
            val fragment = FragmentA()
            val args = Bundle()
            ARG_PARAM1 = param1!!
            fragment.arguments = args
            return fragment
        }
    }
}