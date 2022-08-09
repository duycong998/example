package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IVLCVout;

import java.io.IOException;
import java.util.ArrayList;

public class VLCActivity extends AppCompatActivity implements IVLCVout.Callback {
    private static final int STORAGE_PERMISSION_CODE = 123;

    // String SAMPLE_URL = "https://awlcms.blob.core.windows.net/awlsign/contents/167/YA/lX/YAlXCajNumY3mAAGSsY1.mp4";
    //    private static final String SAMPLE_URL1 = "http://192.168.1.203:80/acer.mov";
    private SurfaceView mVideoSurface = null;
    private LibVLC mLibVLC = null;
    private MediaPlayer mMediaPlayer = null;
    VideoView videoView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlcactivity);
        videoView = findViewById(R.id.videoView);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        videoView.setVideoPath("file://" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/AWL/19.mp4");
        videoView.start();
    }

    private void playVideoMedia() {
        String url = "file://" + getApplication().getExternalFilesDir("ABC") + "/0";
        android.media.MediaPlayer mediaPlayer = new android.media.MediaPlayer();
   //     mediaPlayer.setAudioStreamType(AudioManager.);
        try {
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(url));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mLibVLC.release();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //  startPlayVLC();
    }

    private void startPlayVLC() {
        final ArrayList<String> args = new ArrayList<>();
        args.add("-vvv");
        mLibVLC = new LibVLC(this, args);
        mMediaPlayer = new MediaPlayer(mLibVLC);
//        FrameLayout mVideoSurfaceFrame = (FrameLayout) findViewById(R.id.video_surface_frame);
//        mVideoSurface = (SurfaceView) findViewById(R.id.video_surface);
        String pathExternalDir = "file://" + getApplication().getExternalFilesDir("ABC") + "/0.mp4";
        Log.d("AAA", pathExternalDir);
        IVLCVout vlcVout = mMediaPlayer.getVLCVout();
        vlcVout.setVideoView(mVideoSurface);
        vlcVout.attachViews();
        mMediaPlayer.getVLCVout().addCallback(this);
        Media media = new Media(mLibVLC, Uri.parse(pathExternalDir));
        mMediaPlayer.setMedia(media);
        media.release();
        mMediaPlayer.play();
    }


    @Override
    protected void onStop() {
        super.onStop();
        stopVLC();
    }

    private void stopVLC() {
        mMediaPlayer.stop();
        mMediaPlayer.getVLCVout().detachViews();
        mMediaPlayer.getVLCVout().removeCallback(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Checking the request code of our request
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();

            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onSurfacesCreated(IVLCVout vlcVout) {

    }

    @Override
    public void onSurfacesDestroyed(IVLCVout vlcVout) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
