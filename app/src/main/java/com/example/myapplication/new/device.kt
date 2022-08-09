package com.example.myapplication.new


import com.google.gson.annotations.SerializedName

data class device(
    @SerializedName("deviceId")
    val deviceId: String
)