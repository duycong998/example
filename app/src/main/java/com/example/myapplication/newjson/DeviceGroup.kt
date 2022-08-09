package com.example.myapplication.newjson


import com.google.gson.annotations.SerializedName

data class DeviceGroup(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String
)