package com.example.myapplication.activityforresult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_finish.*
import kotlinx.android.synthetic.main.show_web_view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        edit_finish.text = intent.extras!!.getString("AAA")

        lifecycleScope.launch {
            delay(5000)
            val finishIntent = Intent()
            finishIntent.putExtra("AAA", edit_finish.text.toString())
            finishIntent.putExtra("boolean", true)
            setResult(RESULT_OK, finishIntent)
            finish()
        }
    }
}