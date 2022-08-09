package com.example.myapplication.exo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.EventLogger;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.video.MediaCodecVideoRenderer;
import com.google.android.exoplayer2.video.VideoFrameMetadataListener;

public class ExoActivity extends AppCompatActivity {

    //    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    MediaPlayer mediaPlayer2;
    //    String uri = "file://" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/AWL/19.mp4";
    //  String  uri = "file://" +  getApplication().getExternalFilesDir("ABC") + "/0.mp4";'
    String uri = RawResourceDataSource.buildRawResourceUri(R.raw.a9b9d2f80017cc4a524ba8b8d7ce7e57).toString();
    Uri uri2 = RawResourceDataSource.buildRawResourceUri(R.raw.dog);
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

//
////        surfaceView = findViewById(R.id.surfaceView);
////        surfaceHolder = surfaceView.getHolder();
//        //     surfaceHolder.addCallback(this);
//        // String uri = "https://awlcms.blob.core.windows.net/awlsign/contents/167/YA/lX/YAlXCajNumY3mAAGSsY1.mp4";
//
//
//        //    final String playerInfo = Util.getUserAgent(this, "ExoPlayerInfo");
////        final DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this, playerInfo);
////
////        final MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory, new DefaultExtractorsFactory())
////                .createMediaSource(Uri.parse(uri));
////        DefaultTrackSelector.ParametersBuilder parametersBuilder = new MediaCodecVideoRenderer(
////                this, MediaCodecSelector.DEFAULT, MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT);
////        trackSelector.setParameters(parametersBuilder);
//
////        HttpDataSource.Factory httpDataSourceFactory =
////                new DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true);
////        MediaCodecSelector mediaCodecSelector = new MediaCodecSelector() {
////            @Override
////            public List<MediaCodecInfo> getDecoderInfos(String mimeType, boolean requiresSecureDecoder, boolean requiresTunnelingDecoder) throws MediaCodecUtil.DecoderQueryException {
////                mediaCodecSelector.getDecoderInfos()
////                return null;
////            }
////        };
//        DefaultDataSource.Factory factory = new DefaultDataSource.Factory(this);
//
//        MediaSource mediaSource1 =
//                new ProgressiveMediaSource.Factory(factory).createMediaSource(uri2);
//        MediaSourceFactory mediaSourceFactory = new DefaultMediaSourceFactory(factory);
//        DefaultTrackSelector trackSelector = new DefaultTrackSelector(this);
//
//        DefaultTrackSelector.ParametersBuilder defaultTrackSelector = new DefaultTrackSelector.ParametersBuilder(this)
//                .setMaxVideoSizeSd()
//                .setMaxAudioBitrate(5000000)
//                .setMaxVideoBitrate(5000000)
//                .setForceLowestBitrate(true);
//        trackSelector.setParameters(defaultTrackSelector);
//        VideoFrameMetadataListener frameMetadataListener = (presentationTimeUs, releaseTimeNs, format, mediaFormat) -> {
//         //   value = (int) presentationTimeUs / 1000;
//
//            newPresentationTimeUs = presentationTimeUs;
//          //  Log.d("AAApresentationTimeUs", "result : " + (newPresentationTimeUs - oldPresentationTimeUs));
//           // Log.d("AAApresentationTimeUs", "result : " + (newPresentationTimeUs - oldPresentationTimeUs));
//              Log.d("AAA", " result: " + (newPresentationTimeUs - oldPresentationTimeUs)) ;
//              result = newPresentationTimeUs - oldPresentationTimeUs ;
//              if(result > 0){
//                  if(avg != 0) {
//                      avg = (result + avg)/2 ;
//                      Log.d("AAA1" , " avg : " + avg);
//                  } else  {
//                      avg = result;
//                  }
//              }
//            oldPresentationTimeUs = newPresentationTimeUs;
//              runOnUiThread(() -> {
//                  textView.setText("" + avg);
//              });
//        };
//
//
//        exoPlayer = new ExoPlayer.Builder(this)
//                .setTrackSelector(trackSelector)
//                .setMediaSourceFactory(mediaSourceFactory)
//                .build();
//        exoPlayer.setVideoFrameMetadataListener(frameMetadataListener);
//        exoPlayer.setMediaSource(mediaSource1);
//        exoPlayer.setPlayWhenReady(true);
//        mediaPlayer.setPlayer(exoPlayer);
//        exoPlayer.setRepeatMode(exoPlayer.REPEAT_MODE_ONE);
//        exoPlayer.addAnalyticsListener(new EventLogger(trackSelector));
//        exoPlayer.prepare();


//        DataSource dataSource = new DefaultUriDataSource(this, null, getUserAgent(this, "ExoPlayerExample"));
//
//        ExtractorSampleSource sampleSource = new ExtractorSampleSource(
//                uri, dataSource, allocator, BUFFER_SEGMENT_COUNT * BUFFER_SEGMENT_SIZE);
//
//        MediaCodecVideoTrackRenderer videoRenderer = new MediaCodecVideoTrackRenderer(
//                this, sampleSource, MediaCodecSelector.DEFAULT, MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT);
//        MediaCodecAudioTrackRenderer audioRenderer = new MediaCodecAudioTrackRenderer(
//                sampleSource, MediaCodecSelector.DEFAULT);
//        player = ExoPlayer.Factory.newInstance(RENDERER_COUNT);
//
//        player.prepare(videoRenderer, audioRenderer, secondAudioRenderer);
//        player.sendMessage(videoRenderer, MediaCodecVideoTrackRenderer.MSG_SET_SURFACE, surfaceView.getHolder().getSurface());
//        player.setPlayWhenReady(true);

    }
//
//    @Override
//    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setDisplay(surfaceHolder);
//        try {
//            mediaPlayer.setDataSource(uri);
//            mediaPlayer.prepare();
//            mediaPlayer.setOnPreparedListener(this);
//            mediaPlayer.setLooping(true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    @Override
//    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//
//    }
//
//    @Override
//    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        releasePlayer();
//    }
//
//    private void releasePlayer() {
//        if (mediaPlayer != null) {
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
//    }

//    @Override
//    public void onPrepared(MediaPlayer mediaPlayer) {
//        mediaPlayer.start();
//    }
}
