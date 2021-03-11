package com.exaple.splitwise_clone.sanjoy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import com.exaple.splitwise_clone.R
import kotlinx.android.synthetic.main.activity_add_expense.*

class AddExpenseActivity : AppCompatActivity() {

    val category = arrayOf("Games","Movies","Music","Sports","Dining out","Groceries","Electronics","Furniture","Clothing","Education","Hotel")
    val currency = arrayOf("INR","USD ($)","AED (DH)","AFN ","ALL (L)","AMD ","ARS ($)","AUD ($)","STN (nDb)","THB (B)")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)
        showCategory()
        showCurrency()
        showNotesDialog()
    }

    private fun showCurrency() {
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,currency)
        spCurrency.adapter = arrayAdapter
        spCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }
        }
    }

    private fun showCategory() {

        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,category)
        spCategory.adapter = arrayAdapter
        spCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }
        }
    }

    private fun showNotesDialog() {
        ivNotes.setOnClickListener{

            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.add_notes,null)
            val editText  = dialogLayout.findViewById<EditText>(R.id.etNotes)


            with(builder){
                setTitle("Add notes")
                setPositiveButton("DONE"){dialog, which ->

                }
                setNegativeButton("CANCEL"){dialog, which ->

                }
                setView(dialogLayout)
                show()
            }
        }
    }
}