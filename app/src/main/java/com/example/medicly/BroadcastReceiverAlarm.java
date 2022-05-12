package com.example.medicly;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class BroadcastReceiverAlarm extends BroadcastReceiver {

    private static final String EXTRA_NOTIFICATION_ID = "userSnooze";

    @Override
    public void onReceive(Context context, Intent intent) {

        //SNOOZE
        Intent snoozeIntent = new Intent(context, MedicationHomepage.class);
        snoozeIntent.setAction(Intent.ACTION_PICK_ACTIVITY);
        snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 1);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(context, 1, snoozeIntent, 0);

        //CONFIRM
        Intent confirmIntent = new Intent(context, MedicationHomepage.class);
        confirmIntent.setAction(Intent.ACTION_PICK_ACTIVITY);
        confirmIntent.putExtra(EXTRA_NOTIFICATION_ID, 2);
        PendingIntent confirmPendingIntent =
                PendingIntent.getBroadcast(context, 2, confirmIntent, 0);


        //CANCEL

        Intent cancelIntent = new Intent(context, MedicationHomepage.class);
        cancelIntent.setAction(Intent.ACTION_PICK_ACTIVITY);
        cancelIntent.putExtra(EXTRA_NOTIFICATION_ID, 2);
        PendingIntent cancelPendingIntent =
                PendingIntent.getBroadcast(context, 2, cancelIntent, 0);



        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        Medication newMedication = new Medication();
        CharSequence name = newMedication.getMedicationName();
        CharSequence dose = newMedication.getMedicationDose();
        CharSequence measurement = newMedication.getMedicationMeasurement();
        CharSequence instructions = newMedication.getMedicationIntructions();

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context,"notifyUser");
        notifyBuilder.setColor(context.getResources().getColor((R.color.teal_700)))
                .setSmallIcon((R.drawable.medicly))
                .setContentTitle(name)
                .setContentText(dose+ "" + measurement)
                .setContentText(instructions)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.medicly, "Confirm",confirmPendingIntent)
                .addAction(R.drawable.medicly, "Snooze",snoozePendingIntent)
                .addAction(R.drawable.medicly, "Cancel",cancelPendingIntent)
                .extend(new NotificationCompat.WearableExtender()
                    .setContentIcon(R.drawable.medicly))
                .setAutoCancel(true);
       // long[] pattern = {0, 70, 500, 300, 200, 50, 300, 200, 90};
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        //Vibrator vibrationManager = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
//        vibrationManager.vibrate(pattern,50);
        notificationManager.notify(1, notifyBuilder.build());
        mediaPlayer.start();
        mediaPlayer.stop();



    }






}
