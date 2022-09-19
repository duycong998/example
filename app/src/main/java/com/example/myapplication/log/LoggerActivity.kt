package com.example.myapplication.log

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.app.App
import com.example.myapplication.permission.PermissionUtils
import org.jetbrains.annotations.Contract
import timber.log.Timber
import vn.com.dsoft.ads2u.logger.Logger
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class LoggerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logger)
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.hide()

        Timber.d("####")
        Log.d("####", "CCC")
        Timber.plant(LocalLog(this))
        Timber.d("####")
        Timber.e("####")
        Timber.i("####")
        Timber.e("####2222222222222222")
        Timber.i("####2222222222")

//        val directory = File(Environment.getExternalStorageDirectory(), "congdeptrai" )
//            Log.d("####", directory.path)
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            (application as App).initLog()
            Timber.d("###","Congdeptrai")
           File(Environment.getExternalStorageDirectory(), "congdeptrai")
        } else {
            if (PermissionUtils.hasPermissions(this)) {
                (application as App).initLog()
                Timber.d("###","Congdeptrai")
            } else {
                requestPermissions(
                    PermissionUtils.permissions,
                    PermissionUtils.REQUEST_CODE_PERMISSION
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        requestPermissions(permissions, PermissionUtils.REQUEST_CODE_PERMISSION)
    }
}