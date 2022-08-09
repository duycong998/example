package com.example.myapplication.sequencesAflow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.sequencesAflow.model.Test

class FLowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
        var list = mutableListOf(1, 2, 3, 4, 5, 6)
        // Collections.swap(list, 1, 4)
        var listTest = mutableListOf<Test>()
        listTest.add(0, Test(false, "cong"))
        listTest.add(1, Test(false, "duy"))
        listTest.add(2, Test(false, "nguyen"))
        listTest.add(3, Test(false, "luong"))

        listTest.forEach {
        }


        var dem: Int = 0

        listTest.map {
          it.isBoolean = true

        }
        Log.d("####2", listTest.toString())

//        if(dem > 3 ) {
//            listTest.forEach {
//                it.isBoolean = false
//                Log.d("####2", listTest.toString())
//            }
//        }
    }

}