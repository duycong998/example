package com.example.myapplication.newjson


import com.google.gson.annotations.SerializedName

data class ContentItem(
    @SerializedName("checksum")
    val checksum: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("file_name")
    val fileName: String,
    @SerializedName("file_size")
    val fileSize: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("URL")
    val uRL: String
)