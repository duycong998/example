package com.example.myapplication.fetch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.exoplayer2.util.Log;
import com.tonyodev.fetch2.Download;
import com.tonyodev.fetch2.Error;
import com.tonyodev.fetch2.FetchListener;
import com.tonyodev.fetch2core.DownloadBlock;

import java.util.List;

public class CmsFetchListenerImpl implements FetchListener {

    @Override
    public void onAdded(@NonNull Download download) {
        Log.d("AAAonAddedFetch", download.getUrl());
    }

    @Override
    public void onCancelled(@NonNull Download download) {

    }

    @Override
    public void onCompleted(@NonNull Download download) {
        Log.d("AAAonCompletedFetch", download.getUrl());
    }

    @Override
    public void onDeleted(@NonNull Download download) {

    }

    @Override
    public void onDownloadBlockUpdated(@NonNull Download download, @NonNull DownloadBlock downloadBlock, int i) {

    }

    @Override
    public void onError(@NonNull Download download, @NonNull Error error, @Nullable Throwable throwable) {
        Log.d("AAAFetch", error.toString());
    }

    @Override
    public void onPaused(@NonNull Download download) {

    }

    @Override
    public void onProgress(@NonNull Download download, long l, long l1) {
        int a = download.getProgress();
        Log.d("AAAA", a + "%");
    }

    @Override
    public void onQueued(@NonNull Download download, boolean b) {

    }

    @Override
    public void onRemoved(@NonNull Download download) {

    }

    @Override
    public void onResumed(@NonNull Download download) {

    }

    @Override
    public void onStarted(@NonNull Download download, @NonNull List<? extends DownloadBlock> list, int i) {

    }

    @Override
    public void onWaitingNetwork(@NonNull Download download) {

    }
}
