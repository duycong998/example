package com.example.myapplication.day

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import jp.co.awl.lite.signage.cms.android.api.entity.OperatingHoursContent

class Contents {
    @SerializedName("date_time")
    var content: Array<OperatingHoursContent?>? = null

    companion object {
        fun toJson(user: Contents?): String? {
            return Gson().toJson(
                user, Contents::class.java
            )
        }

        fun fromJson(jsonString: String?): Contents? {
            return Gson().fromJson(
                jsonString, Contents::class.java
            )
        }
    }
}
