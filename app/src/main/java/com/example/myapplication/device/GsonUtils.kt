package com.example.myapplication.device

import com.google.gson.Gson
import com.google.gson.internal.`$Gson$Types`
import java.lang.reflect.Type
import java.util.*


class GsonUtils {

    companion object {
        private object GsonHolder {

            private val gson = Gson()

            fun getGson(): Gson? {
                return GSON
            }

            private val GSON = Gson()

        }

        fun <T> objectsFromJson(json: String?, clazz: Class<T>?): List<T>? {
            val type: Type = `$Gson$Types`.newParameterizedTypeWithOwner(null, ArrayList::class.java, clazz)
            return GsonHolder.getGson()?.fromJson(json, type)
        }
    }
}
