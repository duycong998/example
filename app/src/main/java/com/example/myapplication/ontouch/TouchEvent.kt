package com.example.myapplication.ontouch

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class TouchEvent(
    @SerializedName("type")
    val typeTouch: TypeTouch,
    @SerializedName("id")
    val id: String,
    @SerializedName("Description")
    val description: String
) {
    companion object {
        fun fromJson(jsonString: String?): TouchEvent? {
            return Gson().fromJson(
                jsonString, TouchEvent::class.java
            )
        }
    }
}

enum class TypeTouch {
    @SerializedName("link")
    LINK,

    @SerializedName("popup")
    POPUP
}

data class Touch(
    @SerializedName("touch_event")
    val touchEvent: List<TouchEvent>
) {
    companion object {
        fun fromJson(jsonString: String?): Touch? {
            return Gson().fromJson(
                jsonString, Touch::class.java
            )
        }
    }
}