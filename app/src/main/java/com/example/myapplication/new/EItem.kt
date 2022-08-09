package com.example.myapplication.new


import com.example.myapplication.device.Example
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import java.util.*

data class EItem(
    @SerializedName("contents")
    val contents: List<Content>,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("device_group")
    val deviceGroup: List<Any>,
    @SerializedName("end_date")
    val endDate: Date,
    @SerializedName("_id")
    val id: String,
    @SerializedName("layout_options")
    val layoutOptions: List<LayoutOption>,
    @SerializedName("name")
    val name: String,
    @SerializedName("screen_type")
    val screenType: String,
    @SerializedName("start_date")
    val startDate: Date,
    @SerializedName("status")
    val status: Int,
    @SerializedName("updatedAt")
    val updatedAt: String
){
    companion object {
//        fun fromJson(jsonString: String?): EItem? {
//            return Gson().fromJson(
//                jsonString, EItem::class.java
//            )
//        }
        fun fromJsonn(jsonString: String?): EItem? {
            val gson = GsonBuilder().setDateFormat("yyyyMMdd").create()
            return gson.fromJson(jsonString, EItem::class.java)
        }
    }

    fun getTimeEndDay(): Date? {
        //start Fri Oct 15 00:00:00 GMT+09:00 2021
        //now Thu Oct 14 19:18:40 GMT+09:00 2021
        //end Sat Oct 15 00:00:00 GMT+09:00 2021
        //If we don't add + 1 day, the campaign never show through end_day
        return Date(endDate!!.time + 24 * 60 * 60 * 1000)
    }
}
