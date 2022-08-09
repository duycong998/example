package com.example.myapplication.new


import com.google.gson.annotations.SerializedName

data class LayoutOption(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: String
)