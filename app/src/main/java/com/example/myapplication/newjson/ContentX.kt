package com.example.myapplication.newjson


import com.google.gson.annotations.SerializedName

data class ContentX(
    @SerializedName("condition")
    val condition: Condition,
    @SerializedName("content_item")
    val contentItem: List<ContentItem>
)