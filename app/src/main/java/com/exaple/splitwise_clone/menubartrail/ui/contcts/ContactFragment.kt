package com.exaple.splitwise_clone.menubartrail.ui.contcts

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.harsh.FirstScreen
import com.exaple.splitwise_clone.harsh.SplitWiseScreen
import com.exaple.splitwise_clone.vinod.database.sharedpreferences.PreferenceHelper
import com.exaple.splitwise_clone.vinod.views.SplitwiseApplication


class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)
        val preferenceHelper = PreferenceHelper(view.context)
        preferenceHelper.writeBooleanToPreference(SplitwiseApplication.PREF_IS_USER_LOGIN, false)
        preferenceHelper.writeIntToPreference(SplitwiseApplication.PREF_USER_ID, 0)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent2 = Intent(view.context, SplitWiseScreen::class.java)
        startActivity(intent2)
        activity?.finish()
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            ContactFragment().apply {
                arguments = Bundle().apply {
//                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
                }
            }
    }
}