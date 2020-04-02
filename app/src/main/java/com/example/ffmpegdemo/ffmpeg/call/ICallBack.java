package com.example.ffmpegdemo.ffmpeg.call;

public interface ICallBack {
    void onError(Throwable t);

    void onComplete();

    void onStart();
}