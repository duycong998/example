package com.example.myapplication.activityforresult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_start_for_result.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartForResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_for_result)
        lifecycleScope.launch {
            delay(2000)
            openActivityForResult()
        }
    }

    private fun openActivityForResult() {
        startForResult.launch(Intent(this, FinishActivity::class.java).putExtra("AAA", "congdeptrai"))
    }


    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            if(intent!!.hasExtra("AAA")){
                val title = intent.getStringExtra("AAA")
                val mBoolean = intent.getBooleanExtra("boolean", false)
                txt_startForResult.text = title
                Log.d("####", title + "~~~" + mBoolean)
            }
        }
    }
}