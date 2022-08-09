package com.example.myapplication.new


import com.google.gson.annotations.SerializedName

data class ContentX(
    @SerializedName("checksum")
    val checksum: String,
    @SerializedName("condition")
    val condition: Condition? = null,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("file_name")
    val fileName: String,
    @SerializedName("file_size")
    val fileSize: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("URL")
    val uRL: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)
