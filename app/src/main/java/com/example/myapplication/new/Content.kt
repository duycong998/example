package com.example.myapplication.new


import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("content")
    val content: List<ContentX>,
    @SerializedName("date_time")
    val dateTime: List<DateTime>,
    @SerializedName("priority")
    val priority: Int
)