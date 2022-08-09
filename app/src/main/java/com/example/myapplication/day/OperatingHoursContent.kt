package jp.co.awl.lite.signage.cms.android.api.entity

import com.example.myapplication.day.DayOfWeek
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class OperatingHoursContent {
    @SerializedName("name")
    lateinit var name: DayOfWeek

    @SerializedName("hours")
    lateinit var hours: Array<Array<String>>

    companion object {
        fun toJson(content: OperatingHoursContent?): String {
            return Gson().toJson(content, OperatingHoursContent::class.java)
        }

        fun fromJson(jsonString: String?): OperatingHoursContent {
            return Gson().fromJson(jsonString, OperatingHoursContent::class.java)
        }
    }
}
