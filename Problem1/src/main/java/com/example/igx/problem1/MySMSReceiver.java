package com.example.igx.problem1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Ryusungho on 2016-12-13.
 */

public class MySMSReceiver extends BroadcastReceiver {
    private static final String TAG = "MySMSReceiver";
    public MySMSReceiver() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG , "onReceive() 호출됨.");
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

