package com.example.myapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val collection1 = mutableListOf(1, 2, 3, 4, 5)
//        val collection2 = mutableListOf( 1, 2, 3, 4, 5, 6, 7, 8, 9)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            val output: MutableList<Int> = (collection1 + collection2).toMutableList().apply {
//                removeIf { a -> count { b -> a == b } > 1 }
//            }
//            output.forEach{
//                Log.d("AAA", it.toString())
//            }
//
//        }





        val peoples: List<Person> = getPeople()

      //  val list = listOf<Int>(1,3,4,5,6,7,8)
//        val allowedEntrance = people
//            .filter { it.age >= 21 }
//            .map { it.name}
//            .take(5)
//        runBlocking {
//            list.asFlow().transform<Int,Int> {
//                emit(it*it)
//            }.collect {
//                Log.d("AAAAAAACCCC"," $it")
//            }
//        }
//
//        for (i in allowedEntrance) {
//            Log.d("AAAAAAA", allowedEntrance.toString())
//        }
//        getTime()
//        val time = getDate(1626167761561, "dd/MM/yyyy HH:mm:ss")
//        Log.d("AA", time.toString())
        // if( time == )
//        var time = 0;
//        val a = 0
//        if (a == 0) {
//            time++
//            Log.d("AAA", time++.toString())
//        }

//        val time = Calendar.getInstance().timeInMillis
//        if(time > 1626167761561 ) {
//            Log.d("AAAAA", "CCC")
//            if(time < 1826167761561) {
//                Log.d("AAA", "CCCCCCCC1111")
//            }
//        }
//
//        for(i in 1..3)
//            if(i ==3) {
//                Log.d("AAAA", "ABC")
//            }
//        }

    }

    fun getTime() {
        val time = Calendar.getInstance().timeInMillis
        Log.d("AAA", time.toString())
    }

    fun getDate(milliSeconds: Long, dateFormat: String?): String? {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat)

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }

    fun abc(a: String) {
        if (Integer.parseInt(a) > 100) {
            Log.d("AAAA", "AAAAAA")

        } else {
            Log.d("AAAAA", "CCCCCCC")
        }
    }

    fun getPeople() = mutableListOf(
        Person("Jane", 25),
        Person("Sally", 39),
        Person("Joe", 44),
        Person("Jimmy", 15),
        Person("Samantha", 56),
        Person("Claire", 47),
        Person("Susan", 27)
    )
}
