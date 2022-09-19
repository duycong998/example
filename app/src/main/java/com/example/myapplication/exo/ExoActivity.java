package com.example.myapplication.exo;

import android.media.MediaCodec;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.permission.PermissionUtils;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.EventLogger;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoFrameMetadataListener;

public class ExoActivity extends AppCompatActivity {

    //    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    MediaPlayer mediaPlayer2;
   String uri = "file://" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/AWL/children.mp4";
    //  String  uri = "file://" +  getApplication().getExternalFilesDir("ABC") + "/0.mp4";'
  //  Uri uri = RawResourceDataSource.buildRawResourceUri(R.raw.a9b9d2f80017cc4a524ba8b8d7ce7e57);
//    Uri uri2 = RawResourceDataSource.buildRawResourceUri(R.raw.dog);
    ExoPlayer exoPlayer;
    PlayerView mediaPlayer;
    long oldPresentationTimeUs = 0;
    long newPresentationTimeUs = 0;
    long avg = 0;
    long result = 0;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo);
        mediaPlayer = findViewById(R.id.playerView);
        textView = findViewById(R.id.textview_avg);

        exoPlayer = new SimpleExoPlayer.Builder(this).build();
        Uri videouri = Uri.parse(uri);
        MediaSource mediaSource = new ExtractorMediaSource(videouri,
                new DefaultDataSourceFactory(this,"ua"),
                new DefaultExtractorsFactory(),null,null);
        mediaPlayer.setPlayer(exoPlayer);
        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
        } else {
            if (PermissionUtils.Companion.hasPermissions(this)) {
            } else {
                requestPermissions(
                        PermissionUtils.Companion.getPermissions(),
                        PermissionUtils.REQUEST_CODE_PERMISSION
                );
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        requestPermissions(permissions, PermissionUtils.REQUEST_CODE_PERMISSION);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
