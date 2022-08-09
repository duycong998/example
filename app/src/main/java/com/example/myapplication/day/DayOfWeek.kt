package com.example.myapplication.day

import com.google.gson.annotations.SerializedName
import java.util.*

enum class DayOfWeek(val intValue: Int) {
    @SerializedName("Monday")
    MONDAY(Calendar.MONDAY),

    @SerializedName("Tuesday")
    TUESDAY(Calendar.TUESDAY),

    @SerializedName("Wednesday")
    WEDNESDAY(Calendar.WEDNESDAY),

    @SerializedName("Thursday")
    THURSDAY(Calendar.THURSDAY),

    @SerializedName("Friday")
    FRIDAY(Calendar.FRIDAY),

    @SerializedName("Saturday")
    SATURDAY(Calendar.SATURDAY),

    @SerializedName("Sunday")
    SUNDAY(Calendar.SUNDAY);
}


