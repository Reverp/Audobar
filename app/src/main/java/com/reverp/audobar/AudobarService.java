package com.reverp.audobar;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.security.Provider;
import java.util.List;
import java.util.Map;

import jp.co.recruit_lifestyle.android.floatingview.FloatingViewListener;
import jp.co.recruit_lifestyle.android.floatingview.FloatingViewManager;

/**
 * Created by Michael on 10/16/2015.
 */
public class AudobarService extends Service {

    private FloatingViewManager mFloatingViewManager;

    private WindowManager windowManager;

    @Override public IBinder onBind(Intent intent) {
        // Not used
        return null;
    }

    @Override public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        RelativeLayout mRootLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.audio_bar, null);

        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        float screenDensity = metrics.density;

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                Math.round(100 * screenDensity),
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.BOTTOM | Gravity.FILL_HORIZONTAL;
        params.x = 0;

        windowManager.addView(mRootLayout, params);


        headphoneBroadcastReceiver = new HeadphoneBroadcastReceiver();
        registerReceiver(headphoneBroadcastReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //if (chatHead != null) windowManager.removeView(chatHead);
    }
}
