package com.example.myapplication.new


import com.google.gson.annotations.SerializedName

data class a(
    @SerializedName("id")
    val id: String,
    @SerializedName("record_update")
    val recordUpdate: Int
)
