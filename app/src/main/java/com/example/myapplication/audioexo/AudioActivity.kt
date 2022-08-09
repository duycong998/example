package com.example.myapplication.audioexo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.example.myapplication.R
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.activity_audio.*

class AudioActivity : AppCompatActivity() {

    val uri = "file://" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/AWL/audio";
    lateinit var player: SimpleExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)

        val renderersFactory = DefaultRenderersFactory(this)
        val trackSelectionFactory = AdaptiveTrackSelection.Factory()
        val trackSelectSelector = DefaultTrackSelector(trackSelectionFactory)
        val loadControl = DefaultLoadControl()

        player = ExoPlayerFactory.newSimpleInstance(this, renderersFactory, trackSelectSelector, loadControl)


        val dataSourceFactory = DefaultDataSourceFactory(this, getString(R.string.app_name))
        val extractorsFactory = DefaultExtractorsFactory()
        Log.d("####uri", uri)
        val mediaSource = ProgressiveMediaSource
            .Factory(dataSourceFactory, extractorsFactory)
            .createMediaSource(Uri.parse(uri))

        player.prepare(mediaSource)

        video_view.player = player
    }
    override fun onResume() {
        super.onResume()
        player.playWhenReady = true
    }

    override fun onPause() {
        super.onPause()
        player.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }


}