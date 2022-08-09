package com.example.myapplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tonyodev.fetch2.Download;
import com.tonyodev.fetch2.Error;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;
import com.tonyodev.fetch2.FetchListener;
import com.tonyodev.fetch2.NetworkType;
import com.tonyodev.fetch2.Priority;
import com.tonyodev.fetch2.Request;
import com.tonyodev.fetch2core.DownloadBlock;
import com.tonyodev.fetch2core.Func;
import com.tonyodev.fetch2okhttp.OkHttpDownloader;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class MainActivity2 extends AppCompatActivity {
    private Context context;
    private Fetch fetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final String encodedString = "data:image/png;base64,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";
        final String pureBase64Encoded = encodedString.substring(encodedString.indexOf(",")  + 1);
        byte[] decodedString = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Log.d("AAAAAAdecodedByte", decodedByte.getWidth() + "");


        event();



       // check();
    }

    private void check() {
        File pathExternalDir = getApplication().getExternalFilesDir("ABC");
        Log.d("AAA", pathExternalDir.getPath());
        String fike = pathExternalDir.getPath();
        String define = "?auth=jwt";
        Uri uri = Uri.parse("https://drive.google.com/uc?id=1gIkXHWVq91ap8BY0Q0m9HMBhjdcMQKgh&export=download");
        String server = uri.getAuthority();
        String path = uri.getPath();
        String protocol = uri.getScheme();
        String a = uri.getQueryParameter("param");
        Uri.Builder builder = new Uri.Builder();
        // builder.scheme(protocol).authority(server).path(path);
        String myUrl = uri.toString();
        Log.d("AAA", myUrl);


        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(chain -> chain.proceed(chain.request()))
                .build();
        FetchConfiguration fetchConfiguration = new FetchConfiguration.Builder(getApplication())
                .setNamespace("AAA")
                .setDownloadConcurrentLimit(1)
                .setAutoRetryMaxAttempts(3)
                .enableFileExistChecks(true)
                .setHttpDownloader(new OkHttpDownloader(okHttpClient))
                .build();
        fetch = Fetch.Impl.getInstance(fetchConfiguration);
        fetch.hasActiveDownloads(false, new Func<Boolean>() {
            @Override
            public void call(@NonNull Boolean result) {
                if (result) {
                    Log.d("AAAAf", "11111");
                } else {
                    Log.d("AAAAf", result + "");
                }
            }
        });
        List<Request> list = new ArrayList<>();
        com.tonyodev.fetch2.Request request = new com.tonyodev.fetch2.Request(myUrl, fike);
        request.setPriority(Priority.HIGH);
        request.setNetworkType(NetworkType.ALL);
        list.add(request);
        Log.d("AAAC", list.get(0).getUrl() + "");
        for (Request req : list) {
            // memo: if file already exists in external storage, download immediately completed
            fetch.enqueue(req, updatedRequest -> {
                // Request was successfully enqueued for download.
                Log.d("AAAC2", req.getFile() + "");
            }, error -> {
                // An error occurred enqueuing the request.
                Log.d("AAAC2", error.toString());
            });
        }

        FetchListener fetchListener = new FetchListener() {
            @Override
            public void onQueued(@NonNull Download download, boolean b) {

            }

            @Override
            public void onWaitingNetwork(@NonNull Download download) {

            }

            @Override
            public void onStarted(@NonNull Download download, @NonNull List<? extends DownloadBlock> list, int i) {

            }


            @Override
            public void onDownloadBlockUpdated(@NonNull Download download, @NonNull DownloadBlock downloadBlock, int i) {

            }

            @Override
            public void onAdded(@NonNull Download download) {
            }

            @Override
            public void onCompleted(@NotNull Download download) {

            }

            @Override
            public void onError(@NonNull Download download, @NonNull Error error, @Nullable Throwable throwable) {
                Error erro = download.getError();
            }

            @Override
            public void onProgress(@NotNull Download download, long etaInMilliSeconds, long downloadedBytesPerSecond) {
                if (request.getId() == download.getId()) {
                  //  updateDownload(download, etaInMilliSeconds);
                }
                int progress = download.getProgress();
            }

            @Override
            public void onPaused(@NotNull Download download) {

            }

            @Override
            public void onResumed(@NotNull Download download) {

            }

            @Override
            public void onCancelled(@NotNull Download download) {

            }

            @Override
            public void onRemoved(@NotNull Download download) {

            }

            @Override
            public void onDeleted(@NotNull Download download) {

            }
        };

        fetch.addListener(fetchListener);

        //Remove listener when done.
        fetch.removeListener(fetchListener);
    }


    private void event() {
        findViewById(R.id.click).setOnClickListener(v -> {
            EditText editText = findViewById(R.id.adc);
            int number = Integer.parseInt(editText.getText().toString());
            check(number);
        });
    }

    private void check(int number) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 0; i < list.size(); i++) {
            if (number % 2 == 0) {
                continue;
            }

            if (number > 4) {
                continue;
            }

            if (number < 2) {
                continue;
            }
            Log.d("AAAB", "congdeptrai");
        }
    }
}