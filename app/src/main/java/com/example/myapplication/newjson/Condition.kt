package com.example.myapplication.newjson


import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("age_gte")
    val ageGte: Int,
    @SerializedName("age_lte")
    val ageLte: Int,
    @SerializedName("gender")
    val gender: String
)