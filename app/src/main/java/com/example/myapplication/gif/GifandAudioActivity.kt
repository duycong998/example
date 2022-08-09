package com.example.myapplication.gif

import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_gifand_audio.*


class GifandAudioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gifand_audio)
       // val drawableResourceId = this.getResources().getIdentifier(imageName, "drawable", this.getPackageName())

       // Glide.with(this).load(resources.getIdentifier(imageName, "drawable", packageName)).into(image);
//        val  InputStream = resources.openRawResource(com.example.myapplication.R.drawable.demo)
//        val movie = Movie.decodeStream(InputStream)
//        val duration = movie.duration()
//        Log.d("###", duration.toString())
       // Glide.with(this).load(R.drawable.demo).into(image);

    }

    fun getImage(imageName: String?): Int {
        return resources.getIdentifier(imageName, "drawable", packageName)
    }
}