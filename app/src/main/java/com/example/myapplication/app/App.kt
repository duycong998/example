package com.example.myapplication.app

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.lyft.kronos.AndroidClockFactory
import com.lyft.kronos.KronosClock
import com.lyft.kronos.SyncListener
import timber.log.Timber
import vn.com.dsoft.ads2u.logger.Logger

class App : Application() {

    companion object {
        const val NANOS_TO_MILLIS = 1000000
        private var instance: App? = null
        fun applicationContext(): App {
            return instance as App
        }
    }

    override fun onCreate() {
        super.onCreate()


        Timber.d(TAG, "createKronosClock: onStartSync: ~~")
        Log.d(TAG, "createKronosClock: onStartSync: ~~~")

        Log.d(TAG, "createKronosClock: onStartSync: ~~")
        kronosClock = AndroidClockFactory.createKronosClock(this, object : SyncListener {
            override fun onStartSync(host: String) {
                Log.d(TAG, "createKronosClock: onStartSync: $host")
            }

            override fun onSuccess(ticksDelta: Long, responseTimeMs: Long) {
                Timber.d(
                    "createKronosClock: onSuccess: ticksDelta: "
                            + ticksDelta + ", responseTimeMs: " + responseTimeMs
                )
            }

            override fun onError(host: String, throwable: Throwable) {
                Log.e(TAG, "createKronosClock: onError: " + host + ", " + throwable.message)
            }
        })

        syncKronos()

        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED)
        ) {
            initLog()
        }
    }


    fun initLog() {
        Logger.initialize(this)
    }

    private val TAG = "App"
    private val KRONOS_FALLBACK_THRESHOLD = 1000L * 60L * 5L // 5 min
    // Note: If you don't want to use the mock, set it to false.
    private var isInitLog = false

    private var kronosClock: KronosClock? = null


    fun getKronosClockTimeMillis(): Long {
        val systemNow = System.currentTimeMillis()
        if (kronosClock == null) {
            return systemNow
        }
        val kronosNow = kronosClock!!.getCurrentTimeMs()
        val delay = systemNow - kronosNow
        if (delay > KRONOS_FALLBACK_THRESHOLD) {
            // Timberでこのメソッドを使用するためここでTimberを使用しない
            Log.w(TAG, "Fallback clock: $delay")
            return systemNow
        }
        return kronosNow
    }

    fun getKronosClockTimeMillisOriginal(): Long {
        return if (kronosClock == null) {
            System.currentTimeMillis()
        } else kronosClock!!.getCurrentTimeMs()
    }

    fun getKronosClockTimeNanosOriginal(): Long {
        return getKronosClockTimeMillisOriginal() * NANOS_TO_MILLIS
    }

    fun syncKronos() {
        if (kronosClock != null) {
            kronosClock!!.syncInBackground()
        }
    }
}