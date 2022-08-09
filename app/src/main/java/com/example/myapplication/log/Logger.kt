package vn.com.dsoft.ads2u.logger

import android.os.Environment
import android.util.Log
import com.example.myapplication.app.App
import org.jetbrains.annotations.Contract
import timber.log.Timber
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class Logger {
    companion object {
        private const val ROOT_DIR_NAME = "abc"
        private const val DIR_NAME = "xyz"
        private val LOG_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.JAPAN)
        private var LOG_WRITING_PRIORITY = Log.INFO
        private var IS_ENABLED_LOG_WRITE_TO_FILE = true
        fun initialize(app: App) {
            Timber.plant(Tree(app))
        }

        fun setLogWritingPriority(logWritingPriority: Int) {
            LOG_WRITING_PRIORITY = logWritingPriority
        }

        fun setEnabledLogWriteToFile(enabled: Boolean) {
            IS_ENABLED_LOG_WRITE_TO_FILE = enabled
        }

        fun flushOldLogs(app: App, retentionPeriod: Long) {
            flushOldLogs(app, DIR_NAME, retentionPeriod)
        }

        private fun log(currentMillis: Long, log: String) {
            val dir = getDirectory(DIR_NAME)
            val file = File(dir, getLogFilename(currentMillis))
            log(file, log)
        }

        fun flushOldLogs(app: App, dirName: String?, logRetentionDays: Long) {
            val rootDir = getDirectory(dirName)
            val files = rootDir.listFiles()
            val deleteMillis: Long =
                app.getKronosClockTimeMillis() - logRetentionDays * 24 * 60 * 60 * 1000
            val deleteStartPoint = getLogFilename(deleteMillis).toLong()
            for (file in files) {
                if (!file.isFile) {
                    return
                }
                var fileNumber: Long = 0
                fileNumber = try {
                    file.name.toLong()
                } catch (e: NumberFormatException) {
                    continue
                }
                if (fileNumber <= deleteStartPoint) {
                    Log.d("delete log " , " "+ file.name)
                    file.delete()
                }
            }
        }

        fun log(file: File, log: String?) {
            var fileOutputStream: FileOutputStream? = null
            var printWriter: PrintWriter? = null
            try {
                fileOutputStream = FileOutputStream(file.path, true)
                printWriter = PrintWriter(OutputStreamWriter(fileOutputStream, "UTF-8"))
                printWriter.println(log)
                printWriter.close()
            } catch (e: IOException) {
                Timber.e(e)
            } finally {
                printWriter?.close()
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close()
                    } catch (e: IOException) {
                        Timber.e(e)
                    }
                }
            }
        }

        @Contract(" -> new")
        fun getDirectory(directoryName: String?): File {
            val directory = File(rootDirectory, directoryName)
            if (!directory.exists()) {
                directory.mkdirs()
            }
            return directory
        }

        @get:Contract(" -> new")
        private val rootDirectory: File
            private get() {
                val directory = File(Environment.getExternalStorageDirectory(), ROOT_DIR_NAME)
                if (!directory.exists()) {
                    directory.mkdirs()
                }
                return directory
            }

        fun getLogFilename(millis: Long): String {
            val date = Date(millis)
            val sdf = SimpleDateFormat("yyyyMMdd", Locale.JAPAN)
            return sdf.format(date)
        }

        @Contract(pure = true)
        private fun getPriorityString(priority: Int): String {
            return when (priority) {
                Log.DEBUG -> "D/"
                Log.INFO -> "I/"
                Log.WARN -> "W/"
                Log.ERROR -> "E/"
                Log.ASSERT -> "A/"
                else -> " /"
            }
        }

         class Tree (val app: App) : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, tag, message, t)
                val stringBuilder = StringBuilder()
                val timeMillis: Long = app.getKronosClockTimeMillis()
                stringBuilder.append(LOG_DATE_FORMAT.format(timeMillis))
                stringBuilder.append(" ")
                stringBuilder.append(getPriorityString(priority))
                stringBuilder.append(tag)
                stringBuilder.append(" ")
                stringBuilder.append(message)
                log(app.getKronosClockTimeMillis(), stringBuilder.toString())
            }

        }
    }
}