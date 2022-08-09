package com.example.myapplication.newday


import com.example.myapplication.day.DayOfWeeks
import com.google.gson.annotations.SerializedName
import java.time.DayOfWeek

data class DateTime(
    @SerializedName("date")
    val date: com.example.myapplication.day.DayOfWeek,
    @SerializedName("end")
    val end: String?,
    @SerializedName("start")
    val start: String?
)