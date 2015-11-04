package com.reverp.audobar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HeadphoneBroadcastReceiver extends BroadcastReceiver {
    @Override public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
            int state = intent.getIntExtra("state", -1);
            switch (state) {
                case 0:

                    break;
                case 1:
                    context.startService(new Intent(context, AudobarService.class));
                    break;
                default:
                    //"I have no idea what the headset state is"
            }
        }
    }
}