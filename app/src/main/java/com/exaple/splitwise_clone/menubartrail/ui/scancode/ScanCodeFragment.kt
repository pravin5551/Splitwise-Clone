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

    private lateinit var ivdisplay: ImageView
    private lateinit var btnScan: Button
    private val cameraRequestId = 122


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scancode, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivdisplay = requireView().findViewById(R.id.ivDisplay) as ImageView
        btnScan = requireView().findViewById(R.id.btnScan) as Button
        btnScan.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, cameraRequestId)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequestId) {
            val images: Bitmap = data?.extras?.get("data") as Bitmap
            ivdisplay.setImageBitmap(images)

        }
    }
}