package com.example.myapplication.ApplicationInfo

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_application_info.*
import kotlinx.android.synthetic.main.activity_flow.*

class ApplicationInfoActivity : AppCompatActivity() {
    val list = mutableListOf<Kiosk>()
    var live :LiveData<Boolean>? = null
    val liveData : MutableLiveData<Boolean>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_info)

        liveData?.value = true
        try {
            val app = this.packageManager.getApplicationInfo("com.background.background", 0)
            val icon = packageManager.getApplicationIcon(app)
            val name: String = packageManager.getApplicationLabel(app) as String
            image_view1.setImageDrawable(app.loadIcon(this.packageManager))
            text_view.text = app.loadLabel(this.packageManager)
            list.add(Kiosk(name, app.icon))
            Log.d("####",name + "~~~" + app.icon + "~~~~" + list.size)
        } catch (e: PackageManager.NameNotFoundException) {
            val toast = Toast.makeText(this, "error in getting icon", Toast.LENGTH_SHORT)
            toast.show()
            e.printStackTrace()
        }
    }
}