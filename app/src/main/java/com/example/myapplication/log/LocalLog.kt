package com.example.myapplication.log

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.example.myapplication.permission.PermissionUtils
import timber.log.Timber
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*


class LocalLog(var context: Context) : Timber.Tree() {
    var nameStatus: String = ""

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (PermissionUtils.hasPermissions(context)) {
            try {
                if (priority == Log.INFO) {
                    nameStatus = "INFO :"
                } else if (priority == Log.ERROR) {
                    nameStatus = "ERROR :"
                }
                if (priority == Log.INFO || priority == Log.ERROR) {
                    logStatus(nameStatus + message)
                }
            } catch (error: Exception) {
                Log.d("####err", error.toString())
            }
        }
    }

    private fun getRootDirectory(): File? {
        val directory = File(
            Environment.getExternalStorageDirectory(),
            "LogBackGround"
        )
        if (!directory.exists()) {
            directory.mkdirs()
        }
        return directory
    }


    fun logStatus(data: String) {
        try {
            val gpxfile = File(getRootDirectory(), getFormatDate("yyyyMMdd") + ".txt")
            val writer = FileWriter(gpxfile,true)
            writer.append(getFormatDate("HH:mm:ss") + " " + data + "\n")
            writer.flush()
            writer.close()
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Timber.e("Error:" + e.message)

        }
    }

    fun getFormatDate(type: String): String {
        val cur: Long = System.currentTimeMillis()
        val value: String = SimpleDateFormat(type).format(Date(cur))
        return value
    }
}