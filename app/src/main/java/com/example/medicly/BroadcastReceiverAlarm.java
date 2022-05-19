package com.example.medicly;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import androidx.core.app.NotificationCompat;

public class BroadcastReceiverAlarm extends BroadcastReceiver {

    private static final String EXTRA_NOTIFICATION_ID = "userSnooze";

    @Override
    public void onReceive(Context context, Intent intent) {

        //SNOOZE INTENT - BUTTON ON NOTIFICATION
        Intent snoozeIntent = new Intent(context, MedicationHomepage.class);
        snoozeIntent.setAction(Intent.ACTION_PICK_ACTIVITY);
        snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 1);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(context, 1, snoozeIntent, 0);

        //CONFIRM INTENT - BUTTON ON NOTIFICATION
        Intent confirmIntent = new Intent(context, MedicationHomepage.class);
        confirmIntent.setAction(Intent.ACTION_PICK_ACTIVITY);
        confirmIntent.putExtra(EXTRA_NOTIFICATION_ID, 2);
        PendingIntent confirmPendingIntent =
                PendingIntent.getBroadcast(context, 2, confirmIntent, 0);


        //CANCEL INTENT - BUTTON ON NOTIFICATION
        Intent cancelIntent = new Intent(context, MedicationHomepage.class);
        cancelIntent.setAction(Intent.ACTION_PICK_ACTIVITY);
        cancelIntent.putExtra(EXTRA_NOTIFICATION_ID, 2);
        PendingIntent cancelPendingIntent =
                PendingIntent.getBroadcast(context, 2, cancelIntent, 0);


        //CREATION of default intent, with the medication name,dose, measurement and instructions to be displayed on the notification
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        Medication newMedication = new Medication();
        CharSequence name = newMedication.getMedicationName();
        CharSequence dose = newMedication.getMedicationDose();
        CharSequence measurement = newMedication.getMedicationMeasurement();
        CharSequence instructions = newMedication.getMedicationIntructions();

        //creation of notification builder, and assigning the 'buttons' on the notification to the colour teal, as well as setting the icon, title and main content
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context,"notifyUser");
        notifyBuilder.setColor(context.getResources().getColor((R.color.teal_700)))
                .setSmallIcon((R.drawable.medicly))
                .setContentTitle(name)
                .setContentText(dose+ "" +measurement + " " + instructions)
                .setContentIntent(pendingIntent)
                //displaying placeholder buttons on the notification
                .addAction(R.drawable.medicly, "Confirm",confirmPendingIntent)
                .addAction(R.drawable.medicly, "Snooze",snoozePendingIntent)
                .addAction(R.drawable.medicly, "Cancel",cancelPendingIntent)
                .extend(new NotificationCompat.WearableExtender()
                    .setContentIcon(R.drawable.medicly))
                .setAutoCancel(true);

        //creation of notification manager and notification service, this is what makes the notifications appear
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        //notification sound creation set to default ringtone - can be customised for future use
        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        notificationManager.notify(1, notifyBuilder.build());
        mediaPlayer.start();
        mediaPlayer.stop();



    }






}
