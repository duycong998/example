package com.example.myapplication.device

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Person
import com.example.myapplication.R
import com.example.myapplication.new.ContentX
import com.example.myapplication.new.EItem
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.`$Gson$Types`
import java.lang.reflect.Type
import java.util.ArrayList


class MainActivityDevice : AppCompatActivity() {
    lateinit var deviceGroup: DeviceGroup
     var listScheduler: MutableList<Person> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_device)
        val person = Person("cong", 18)
        listScheduler.add(person)
        Log.d("AAAlog", listScheduler.size.toString())


        val json = "{\"contents\":[{\"content\":[{\"checksum\":\"b70e27863102f84c73650da3a841658a\",\"condition\":{\"age_gte\":20,\"age_lte\":50,\"_id\":\"6253155b2113639e352b0a68\"},\"createdAt\":\"2022-04-13T09:44:16.633Z\",\"duration\":0,\"file_name\":\"moto.mp4\",\"file_size\":\"3 MB\",\"_id\":\"62569b70fe5693ba20d8be76\",\"status\":1,\"type\":\"video\",\"URL\":\"https://test-awlcms-device-api.awlengine.com/download/contents/GpA2RejwJvb94x3NzkPW43V8Mnd5rW6Qg1yqa0ml\",\"updatedAt\":\"2022-04-13T09:44:16.633Z\"},{\"checksum\":\"741d65bb57a226313ad3770f2a7dbf49\",\"condition\":{\"age_gte\":50,\"age_lte\":70,\"_id\":\"625400ad633165dec7cd9f94\"},\"createdAt\":\"2022-04-13T09:44:28.899Z\",\"duration\":0,\"file_name\":\"background.png\",\"file_size\":\"193.1 KB\",\"_id\":\"62569b7cfe5693ba20d8be79\",\"status\":1,\"type\":\"image\",\"URL\":\"https://drive.google.com/uc?id\\u003d18k0nC9lJ9YNFJLXHOd5y950607h57_-B\\u0026export\\u003ddownload\",\"updatedAt\":\"2022-04-13T09:44:28.899Z\"},{\"checksum\":\"399f2f492e033c217f442c4802544645\",\"condition\":{},\"createdAt\":\"2022-04-13T09:44:28.899Z\",\"duration\":0,\"file_name\":\"background.png\",\"file_size\":\"193.1 KB\",\"_id\":\"62569b7cfe569ba20d8b7179\",\"status\":1,\"type\":\"video\",\"URL\":\"https://test-awlcms-device-api.awlengine.com/download/contents/E0jAayMYL6vq3WK4dOV5DkPpQbNz187BxwGgJk5X\",\"updatedAt\":\"2022-04-13T09:44:28.899Z\"}],\"date_time\":[{\"hours\":[],\"name\":\"Sunday\"},{\"hours\":[[\"00:03\",\"23:59\"]],\"name\":\"Monday\"},{\"hours\":[[\"00:03\",\"23:58\"]],\"name\":\"Tuesday\"},{\"hours\":[[\"00:03\",\"23:57\"]],\"name\":\"Wednesday\"},{\"hours\":[[\"00:03\",\"23:56\"]],\"name\":\"Thursday\"},{\"hours\":[[\"00:00\",\"23:55\"]],\"name\":\"Friday\"},{\"hours\":[[\"00:03\",\"23:54\"]],\"name\":\"Saturday\"}],\"priority\":1}],\"createdAt\":\"2022-04-14T03:31:51.355Z\",\"device_group\":[{\"_id\":\"623d33eb8ff1c1451547e7b4\",\"description\":\"ad2u_default\"},[{\"serial\":\"68658585775877\",\"id\":\"624cf2b4f9e06e8e016bbd4f\"},{\"serial\":\"68658585775877\",\"id\":\"624e9c56bdca2196d5dd6750\"},{\"serial\":\"68658585775877\",\"id\":\"624e9def07f686990eaff02c\"}]],\"end_date\":\"Apr 30, 2022 00:00:00\",\"_id\":\"625795a7cda95b16ebf4608a\",\"layout_options\":[],\"name\":\"ad2utest\",\"screen_type\":\"fullscreen\",\"start_date\":\"Mar 29, 2022 00:00:00\",\"status\":1,\"updatedAt\":\"2022-04-14T03:31:51.355Z\"}"
        val campaign: EItem? = fromJson(json)
        var dem : Int = 0
        var demnull : Int = 0
        Log.d("AAA",Gson().toJson(campaign))
        for (i in campaign!!.contents.indices) {
            for (j in campaign.contents[i].content.indices) {
                Log.d("AAAc", Gson().toJson(campaign.contents[i].content[2].condition!!.id))
                val a = campaign.contents[i].content[j].condition
                if(campaign.contents[i].content[j].condition!!.id != null) {
                    demnull++
                } else {
                    dem++
                }


            }
        }
        Log.d("AAAA", "$dem" + " ~~~~ $demnull ")

    }

    fun fromJsonn(jsonString: String?): List<EItem>? {
        return GsonUtils.objectsFromJson(jsonString, EItem::class.java)
    }
    fun fromJson(jsonString: String?): EItem? {

        return Gson().fromJson(jsonString, EItem::class.java)
    }
}