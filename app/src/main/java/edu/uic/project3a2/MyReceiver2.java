package edu.uic.project3a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver {

    private callbackMethod listener;

    public MyReceiver2(callbackMethod listener ){

        this.listener = listener;

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Intent startIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());

        startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(startIntent);
        listener.transactionRestaurant();


    }
}