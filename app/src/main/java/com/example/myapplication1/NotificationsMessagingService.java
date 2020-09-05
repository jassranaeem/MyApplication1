package com.example.myapplication1;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;


import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class NotificationsMessagingService extends FirebaseMessagingService {
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Log.i("Message Received", "Remote Message Received");
        super.onMessageReceived(remoteMessage);
        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Map<String, String> data = remoteMessage.getData();

        String interest = remoteMessage.getData().get("interest");

//        Log.i("Data Interest", interest);

        if (interest.equals("hello"))
        {
            sendNotification(notification, data);
        }
        else
        {
            receiveNotification(remoteMessage, data);
//            Log.i("Else", "Inside else condition");
        }
    }

    /**
     * Create and show a custom notification containing the received FCM message.
     *
     * @param notification FCM notification payload received.
     * @param data FCM data payload received.
     */
    private void sendNotification(RemoteMessage.Notification notification, Map<String, String> data)
    {
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "channel_id")
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent)
                .setContentInfo(notification.getTitle())
                .setLargeIcon(icon)
                .setLights(Color.RED, 1000, 300)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSmallIcon(R.mipmap.logo);

        try {
            String picture_url = data.get("picture_url");
            if (picture_url != null && !"".equals(picture_url))
            {
                URL url = new URL(picture_url);
                Bitmap bigPicture = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                notificationBuilder.setStyle(
                        new NotificationCompat.BigPictureStyle().bigPicture(bigPicture).setSummaryText(notification.getBody())
                );
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Notification Channel is required for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "channel_id", "channel_name", NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("channel description");
            channel.setShowBadge(true);
            channel.canShowBadge();
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500});
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notificationBuilder.build());
    }

    private void receiveNotification(RemoteMessage remoteMessage,  Map<String, String> data)
    {
        // This function is fetching the data of the phlebotomist comming through FCM
        // This function contains all the data of the phlebotomist
        // Use this function to render and display the data in the graphical form.
        // All your code should be here regarding the rendering of the data into graphical form

        String body = remoteMessage.getData().get("body");
        String pbId = remoteMessage.getData().get("id");
        String pbName = remoteMessage.getData().get("name");
        String pbPicture = remoteMessage.getData().get("picture");
        String pbNumber = remoteMessage.getData().get("phone_number");
        String pbLat = remoteMessage.getData().get("lat");
        String pbLong = remoteMessage.getData().get("long");
        String title = remoteMessage.getData().get("title");
        String pbSessionId = remoteMessage.getData().get("session_id");

        Log.i("body", body);
        Log.i("Id", pbId);
        Log.i("Name", pbName);
        Log.i("Image", pbPicture);
        Log.i("Number", pbNumber);
        Log.i("Lat", pbLat);
        Log.i("Long", pbLong);
        Log.i("Title", title);
        Log.i("Session Id", pbSessionId);
    }
}
