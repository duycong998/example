package com.example.myapplication.day

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.newday.DateTime
import com.example.myapplication.newday.Operating
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MainActivityDay : AppCompatActivity() {
    val json = " {\n" +
            "                \"date_time\": [\n" +
            "                    {\n" +
            "                        \"start\": \"19:00\",\n" +
            "                        \"end\": \"21:00\",\n" +
            "                        \"date\": \"Sunday\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"start\": \"19:00\",\n" +
            "                        \"end\": \"21:00\",\n" +
            "                        \"date\": \"Monday\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"start\": \"19:00\",\n" +
            "                        \"end\": \"21:00\",\n" +
            "                        \"date\": \"Tuesday\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"start\": \"19:00\",\n" +
            "                        \"end\": \"21:00\",\n" +
            "                        \"date\": \"Tuesday\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"start\": \"19:00\",\n" +
            "                        \"end\": \"21:00\",\n" +
            "                        \"date\": \"Wednesday\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"start\": \"19:00\",\n" +
            "                        \"end\": \"21:00\",\n" +
            "                        \"date\": \"Thursday\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"start\": \"19:00\",\n" +
            "                        \"end\": \"21:00\",\n" +
            "                        \"date\": \"Friday\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"start\": \"19:00\",\n" +
            "                        \"end\": \"21:00\",\n" +
            "                        \"date\": \"Saturday\"\n" +
            "                    }\n" +
            "                ]}"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_day)
        doSomething()
        checkOperating(Operating.fromJson(json)!!)
    }
    fun doSomething(){
        val systemNow = System.currentTimeMillis()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = systemNow
        val dayOfWeek = calendar[Calendar.DAY_OF_WEEK]
        val hour = calendar[Calendar.HOUR_OF_DAY]
        val minute = calendar[Calendar.MINUTE]
        val sdf = SimpleDateFormat("kk:mm")
        Log.d("AAAA", "$dayOfWeek + hour: $hour + minute: $minute")

    }
//    private fun startRepeatingJob(timeInterval: Long): Job {
//        val content = Contents.fromJson(json)
//        Log.d("AAAcontent", Contents.toJson(content)!!)
//        return CoroutineScope(Dispatchers.Default).launch {
//            while (isActive) {
//             //   doSomething()
//                 if(checkOperating(content!!)) {
//                     Log.d("AAA", "AAAAAAAAAtrue")
//                 } else {
//                     Log.d("AAA", "AAAAAAAAAfalse")
//                 }
//                delay(timeInterval)
//            }
//        }
//    }

    fun checkOperating(contents: Operating): Boolean {
        val systemNow = System.currentTimeMillis()
        if (contents == null) {
            return true
        }

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = systemNow
        val dayOfWeek = calendar[Calendar.DAY_OF_WEEK]
        val hour = calendar[Calendar.HOUR_OF_DAY]
        val minute = calendar[Calendar.MINUTE]
        val sdf = SimpleDateFormat("kk:mm")
        Log.d("AAAA", "$dayOfWeek + hour: $hour + minute: $minute")

        for (content in contents.dateTime) {
            if (content!!.date.intValue == dayOfWeek) {
                if (content.start == null && content.end == null ) {
                    return false
                }
                val sb = StringBuffer()
                sb.append(content.date)
                sb.append("[")
                    sb.append(" open: ")
                    sb.append(content.start)
                    sb.append(", close: ")
                    sb.append(content.end)
                sb.append("]")
                Log.d("AAA",sb.toString())
//                // 24h 営業処理
//                if (content.hours.size == 1 && jp.co.awl.lite.signage.cms.android.api.ApiUtils.END_TIME_FOR_24h == content.hours[0][1] && jp.co.awl.lite.signage.cms.android.api.ApiUtils.START_TIME_FOR_24h == content.hours[0][0]) {
//                    return true
//                }
                try {
                        val startHour = Calendar.getInstance()
                        startHour.time = sdf.parse(content.start)
                        val endHour = Calendar.getInstance()
                        endHour.time = sdf.parse(content.end)
                        val startHourOfDay = startHour[Calendar.HOUR_OF_DAY]
                        val endHourOfDay = endHour[Calendar.HOUR_OF_DAY]
                        if (startHourOfDay <= hour && endHourOfDay >= hour) {
                            if (startHourOfDay == hour && startHour[Calendar.MINUTE] > minute) {
                                continue
                            }
                            if (endHourOfDay == hour && endHour[Calendar.MINUTE] < minute) {
                                continue
                            }
                            Log.d("AAA","Yes: d: " + dayOfWeek + ", open: " + content.start + ", close: " + content.end)
                            return true
                        }
                        Log.d("AAA","No: d: " + dayOfWeek + ", open: " + content.start + ", close: " + content.end)
                } catch (e: ParseException) {
                    Log.e("AAAA",e.toString())
                }
            }
        }
        return false


//        for( a: DayOfWeek in DayOfWeek.values()) {
//            if(a.intValue == dayOfWeek) {
//                if()
//            }
//        }
    }
}
