package com.example.myapplication.new


import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("age_gte")
    val ageGte: Int? = null,
    @SerializedName("age_lte")
    val ageLte: Int? = null,
    @SerializedName("gender")
    val gender: Int? = null,
    @SerializedName("_id")
    val id: String? = null
)