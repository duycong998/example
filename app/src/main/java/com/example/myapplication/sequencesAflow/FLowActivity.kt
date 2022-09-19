package com.example.myapplication.sequencesAflow

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.sequencesAflow.model.Test
import kotlinx.android.synthetic.main.activity_flow.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.*


class FLowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)

//        try {
//            val app = this.packageManager.getApplicationInfo("com.background.background", 0)
//            val icon = packageManager.getApplicationIcon(app)
//            val name: String = packageManager.getApplicationLabel(app) as String
//            image_view1.setImageDrawable(app.loadIcon(this.packageManager))
//            text_view.text = app.loadLabel(this.packageManager)
//            Log.d("####",name + "~~~" + icon )
//        } catch (e: PackageManager.NameNotFoundException) {
//            val toast = Toast.makeText(this, "error in getting icon", Toast.LENGTH_SHORT)
//            toast.show()
//            e.printStackTrace()
//        }



        var listTest = mutableListOf<Test>()
        listTest.add(0, Test(false, "cong", 1))
        listTest.add(1, Test(false, "duy", 2))
        listTest.add(2, Test(false, "nguyen", 4))
        listTest.add(3, Test(false, "luong", 6))
        var index = 0
        lifecycleScope.launch {
            while (isActive) {
             //   Log.d("###name", listTest[index].name )
               // listTest[index].isBoolean = true
//                if (index < listTest.size - 1) {
//                    index++
//                } else {
//                    listTest.map {
//                        it.isBoolean = false
//                    }
//                    index = 0
//                }

                for (i in index until listTest.size) {
                    if (listTest[i].indext % 2 == 1) {
                        continue
                    }
                   // listTest.swap(index, i)
                    if(listTest[i].indext == 4) {
                        Log.d("####ok", "4 ne")
                        break
                    }
                    Log.d("####ok", listTest[i].toString())
                }
            //    Log.d("####after", listTest.toString())
                delay(2000)
            }
        }


//
//        lifecycleScope.launch {
//            while (isActive) {
//                if(dem < listTest.size-1) {
//                    dem++
//                }
//            }
//            listTest.map {
//                it.isBoolean = true
//            }
//        }

        //    Log.d("####2", listTest.toString())

//        if(dem > 3 ) {
//            listTest.forEach {
//                it.isBoolean = false
//                Log.d("####2", listTest.toString())
//            }
//        }

//        var a = mutableListOf<Int>(1, 3, 4, 5, 3)
//        var indext = 0
//        lifecycleScope.launch {
//            while (isActive) {
//                Log.d("#####", a[indext].toString())
//                if (indext < a.size - 1) {
//                    indext++
//                } else {
//                    indext = 0
//                }
//
//                for (i in indext until a.size) {
//                    if (a[i] % 2 == 1) {
//                        continue
//                    }
//                    a.swap(i,indext)
//                    Log.d("####1", a[i].toString() + "~~~" + i.toString())
//                }
//                delay(2000)
//            }
//        }

    }

    fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }
}