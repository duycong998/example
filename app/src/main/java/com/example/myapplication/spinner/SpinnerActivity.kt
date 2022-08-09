package com.example.myapplication.spinner

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R


class SpinnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spiner)
        val list = listOf("A", "B", "C")
        val spinner : Spinner = findViewById(R.id.spinner)
        val image1 : ImageView = findViewById(R.id.image1)
        val image2 : ImageView = findViewById(R.id.image2)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                i: Int,
                l: Long
            ) {
                when(i){
                    0 -> {
                        image1.visibility = View.VISIBLE
                        image2.visibility = View.GONE
                    }
                    1 -> {
                        image2.visibility = View.VISIBLE
                        image1.visibility = View.GONE
                    }
                }
                Toast.makeText(applicationContext, "You selected: $i", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }
}