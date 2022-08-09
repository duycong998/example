package com.example.myapplication.fetch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.tonyodev.fetch2.Error;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;
import com.tonyodev.fetch2.NetworkType;
import com.tonyodev.fetch2.Priority;
import com.tonyodev.fetch2.Request;
import com.tonyodev.fetch2core.Func;
import com.tonyodev.fetch2okhttp.OkHttpDownloader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;

public class DownloadActivity extends AppCompatActivity {
    private Fetch fetch;
    private CmsFetchListenerImpl fetchListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Random random = new Random();
        int number = random.nextInt(10);
         feth(number);
    }

    private void feth(int random) {
        File pathExternalDir = getApplication().getExternalFilesDir("ABC");
        Log.d("AAA", pathExternalDir.getPath());
        String fike = pathExternalDir.toString() +"/" + random ;
        // builder.scheme(protocol).authority(server).path(path);
        String myUrl = "https://drive.google.com/uc?id=1V1GO35jYdUIuGb_45ZePVL84hZ0DzDND&export=download";

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(chain -> chain.proceed(chain.request()))
                .build();
        FetchConfiguration fetchConfiguration = new FetchConfiguration.Builder(this)
                .setNamespace("AAA")
                .setDownloadConcurrentLimit(1)
                .setAutoRetryMaxAttempts(3)
                .enableFileExistChecks(true)
                .setHttpDownloader(new OkHttpDownloader(okHttpClient))
                .build();

        fetch = Fetch.Impl.getInstance(fetchConfiguration);
        fetchListener  = new CmsFetchListenerImpl();
        //   List<Request> list = new ArrayList<>();
        Request request = new Request(myUrl, fike );
        request.setPriority(Priority.HIGH);
        request.setNetworkType(NetworkType.ALL);
        //  list.add(request);

        //   for (Request req : list) {
        // memo: if file already exists in external storage, download immediately completed
        fetch.enqueue(request,
                result -> Log.d("AAAAcall", result.getFile()),
                result -> Log.d("AAAAcall", result.toString()));
        fetch.addListener(fetchListener);
        // Log.d("AAAC", list.get(0).getUrl() + "");
    }
}
