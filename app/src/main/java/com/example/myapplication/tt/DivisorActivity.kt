package com.example.myapplication.tt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Size
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_divisor.*
import java.util.*


class DivisorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_divisor)
        click.setOnClickListener {
            startActivity(Intent(this, IntentActivity.getInstance("congdeptrai")::class.java))
        }
        val list = checkDivisor(Size(1600,1080))
        Log.d("####", list.toString())
        for( i in list) {
            Log.d("####", i.height.toString() + "~~~" + i.width.toString())
        }
    }

    private fun checkDivisor(x: Size) : List<Size> {
        val divisors = listOf(3, 4, 5, 2, 6, 7, 8, 9)
        val arraySize = mutableListOf<Size>()
        for (div in divisors) {
            if (x.height % div == 0 && x.width % div == 0 && arraySize.size < 3) {
                val size = Size(x.height/div, x.width/div)
                arraySize.add(size)
            }
        }
      //  Collections.sort(arraySize, Comparator.comparingInt { obj: Size -> obj.width })
        arraySize.sortWith { o1: Size, o2: Size -> o1.width - o2.width }
        return arraySize
    }
}