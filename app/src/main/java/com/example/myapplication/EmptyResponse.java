package com.example.myapplication;

import io.reactivex.Observable;

public class EmptyResponse {
    public EmptyResponse() {
    }

    public static Observable<EmptyResponse> createObservable() {
        return Observable.create(emitter -> {
            emitter.onNext(new EmptyResponse());
            emitter.onComplete();
        });
    }
}