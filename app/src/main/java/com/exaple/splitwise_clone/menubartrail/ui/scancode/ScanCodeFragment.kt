package com.exaple.splitwise_clone.menubartrail.ui.scancode

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.exaple.splitwise_clone.R

class ScanCodeFragment : Fragment() {

    private lateinit var scanCodeViewModel: ScanCodeViewModel
    private lateinit var btnScanner: Button
    private lateinit var ivScanImage: ImageView
    val CAMERA_REQUEST_ID = 1111


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        btnScanner.setOnClickListener {

            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST_ID)

        }
        return null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST_ID) {
            val Images: Bitmap = data?.extras?.get("data") as Bitmap
            ivScanImage.setImageBitmap(Images)

        }
    }
}


//        scanCodeViewModel =
//            ViewModelProvider(this).get(ScanCodeViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_scancode, container, false)
//        val textView: TextView = root.findViewById(R.id.text_slideshow)
//        scanCodeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//        return root








