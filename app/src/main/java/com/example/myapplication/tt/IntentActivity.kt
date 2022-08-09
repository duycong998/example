package com.example.myapplication.tt

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_intent.*

class IntentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)
        textView.text = b
    }

    companion object {
        var b : String? = null ;
        fun getInstance(a: String) = IntentActivity().apply {
            b = a
        }
    }
}