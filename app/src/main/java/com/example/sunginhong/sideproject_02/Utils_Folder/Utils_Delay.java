package com.example.sunginhong.sideproject_02.Utils_Folder;

import android.content.Context;
import android.os.Handler;

public class Utils_Delay {
    protected Context context;

    public Utils_Delay() {
    }

    public interface DelayCallback {
        void afterDelay();
    }

    public static void delay(int secs, final DelayCallback delayCallback){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                delayCallback.afterDelay();
            }
        }, secs * 100); // afterDelay will be executed after (secs*1000) milliseconds.
    }

    public static void delayMin(int secs, final DelayCallback delayCallback){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                delayCallback.afterDelay();
            }
        }, secs * 10); // afterDelay will be executed after (secs*1000) milliseconds.
    }
}