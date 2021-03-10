package com.exaple.splitwise_clone.pravinTrialAndError

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.menubartrail.MenuMainActivity
import kotlinx.android.synthetic.main.fragment_d.*


class FragmentD : Fragment() {


    private var mTvData: TextView? = null
    private var mParam1: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_d, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivClickAnywhere.setOnClickListener {
            val intent = Intent(activity, MenuMainActivity::class.java)
            startActivity(intent)
        }

    }



    companion object {
        private const val ARG_PARAM1 = "param1"


        fun newInstance(param1: String?): FragmentD {
            val fragment = FragmentD()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}