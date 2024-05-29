package com.example.projectweb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver {

    private static final String TAG = "Alarm";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Alarm received!");

        String task = intent.getStringExtra("task");
        if (task != null) {
            Toast.makeText(context, "Time to: " + task, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Time to Arise!", Toast.LENGTH_SHORT).show();
        }
        MediaPlayer np = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        np.start();
    }
}
