package com.example.myapplication.device


import com.example.myapplication.day.Contents
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Example(
    @SerializedName("device_group")
    @Expose
    var deviceGroup: List<Any>?  = null
) {
    companion object {
        fun fromJson(jsonString: String?): Example? {
            return Gson().fromJson(
                jsonString, Example::class.java
            )
        }
    }
}

sealed class DeviceGroup {
     class DeviceTypeOneValue(val value: DeviceTypeOne) : DeviceGroup()
     class DeviceTypeTwoValue(val value: List<DeviceTypeTwo>) : DeviceGroup()
}

data class DeviceTypeOne(
    @SerializedName("_id")
    val id: String?,
    val description: String?
)

data class DeviceTypeTwo(
    val serial: String?,
    val id: String?
)
