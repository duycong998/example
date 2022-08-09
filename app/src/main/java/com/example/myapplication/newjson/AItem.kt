package com.example.myapplication.newjson


import com.google.gson.annotations.SerializedName

data class AItem(
    @SerializedName("contents")
    val contents: List<Content>,
    @SerializedName("device_group")
    val deviceGroup: List<DeviceGroup>,
    @SerializedName("end_date")
    val endDate: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("layout_options")
    val layoutOptions: List<Any>,
    @SerializedName("name")
    val name: String,
    @SerializedName("record_update")
    val recordUpdate: Int,
    @SerializedName("screen_type")
    val screenType: String,
    @SerializedName("start_date")
    val startDate: Int
)