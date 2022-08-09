package com.example.myapplication.newday


import com.example.myapplication.day.Contents
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Operating(
    @SerializedName("date_time")
    val dateTime: List<DateTime>

)
{
    companion object {
        fun toJson(user: Operating?): String? {
            return Gson().toJson(
                user, Operating::class.java
            )
        }

        fun fromJson(jsonString: String?): Operating? {
            return Gson().fromJson(
                jsonString, Operating::class.java
            )
        }
    }
}
