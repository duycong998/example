package com.example.myapplication.newjson


import com.google.gson.annotations.SerializedName

data class DateTime(
    @SerializedName("hours")
    val hours: List<List<String>>,
    @SerializedName("name")
    val name: String
)