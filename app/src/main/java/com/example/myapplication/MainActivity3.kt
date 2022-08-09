package com.example.myapplication

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        a()
    }

    fun a() {
//        val imageAsBytes: ByteArray = Base64.decode(myImageData, Base64.DEFAULT)
//        image.setImageBitmap(
//            BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size)
//        )

//                  Picasso.get()
//                  .load("https://img.websosanh.vn/v2/users/review/images/4wvuq0i4ozs1q.jpg?compress=85")
//                  .fit()
//                  .centerInside()
//                  .into(image)
        val image: ImageView = findViewById<ImageView>(R.id.ImageViewabc) as ImageView
        val myImageData = "data:image/png;base64,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"
        val pureBase64Encoded: String = myImageData.substring(myImageData.indexOf(",") + 1)
        val decodedString = Base64.decode(pureBase64Encoded, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        image.setImageBitmap(decodedByte)

    }
}