package com.example.myapplication.ontouch

import android.app.Dialog
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_on_touch.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class OnTouchActivity : AppCompatActivity() {
    private var webView: WebView? = null
    var dialog: Dialog? = null
    var textTile: TextView? = null
    var container1: ConstraintLayout? = null
    var container2: ConstraintLayout? = null
    val json ="{\"touch_event\":[\n" +
            "   {\n" +
            "      \"type\":\"link\",\n" +
            "      \"id\":8,\n" +
            "      \"Description\":\"https://baomoi.com/\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"type\":\"popup\",\n" +
            "      \"id\":8,\n" +
            "      \"Description\":\"abcxzy\"\n" +
            "   }\n" +
            "]}"

    companion object {
        var dem = 5
    }

    override fun onBackPressed() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_touch)

        lifecycleScope.launch {
            while(isActive) {
                actionBar?.setDisplayShowHomeEnabled(false)
                ActivityUtils.hideStatusBar(this@OnTouchActivity)
                delay(2000L)
            }
        }
        val a = Touch.fromJson(json)

        Img1.setOnClickListener {
            showDiaLog(a!!, 0)
        }

        Img2.setOnClickListener {
            showDiaLog(a!!, 1)
        }

    }


    private fun showDiaLog( touch : Touch, position : Int) {
        dialog = Dialog(this)
        val screenSize = Point()
        windowManager.defaultDisplay.getSize(screenSize)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setContentView(R.layout.show_web_view)
        webView = dialog!!.findViewById(R.id.webView1)
        textTile = dialog!!.findViewById(R.id.txt_title)
        container1 = dialog!!.findViewById(R.id.container_webView)
        container2 = dialog!!.findViewById(R.id.container_popup)
        if (touch.touchEvent[position].typeTouch == TypeTouch.LINK) {
            container1!!.visibility = View.VISIBLE
            container2!!.visibility = View.GONE
            showWebView(webView!!, touch, position)
        } else {
            container1!!.visibility = View.GONE
            container2!!.visibility = View.VISIBLE
            textTile!!.text = touch.touchEvent[position].description
        }
        val window = dialog!!.window
        window?.apply {
            setLayout(
                screenSize.x * 4 / 5, screenSize.y * 4 / 5
            )
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        val windowAttributes = window?.attributes
        windowAttributes?.gravity = Gravity.CENTER
        window?.attributes = windowAttributes
        dialog!!.show()
    }


    private fun showWebView(webView: WebView, touch : Touch, position : Int) {
        webView.webViewClient = WebViewClient()
        webView.loadUrl(touch.touchEvent[position].description)
        val webSettings = webView.getSettings()
        webSettings.javaScriptEnabled = true
    }
}


class ActivityUtils {
    companion object {
        fun hideStatusBar(activity: AppCompatActivity) {
            val decorView = activity.window.decorView
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }
}