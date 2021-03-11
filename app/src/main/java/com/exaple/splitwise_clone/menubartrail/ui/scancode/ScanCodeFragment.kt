package com.exaple.splitwise_clone.menubartrail.ui.scancode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.exaple.splitwise_clone.R

class ScanCodeFragment : Fragment() {

    private lateinit var scanCodeViewModel: ScanCodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scanCodeViewModel =
            ViewModelProvider(this).get(ScanCodeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_scancode, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        scanCodeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}