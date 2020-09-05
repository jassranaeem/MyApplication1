package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.RemoteMessage;
import com.pusher.pushnotifications.PushNotificationReceivedListener;
import com.pusher.pushnotifications.PushNotifications;

import kotlin.jvm.internal.Intrinsics;

public class dashboard_activiy extends AppCompatActivity
{

    public View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_activiy);
        rootView = findViewById(R.id.rootView);

        // The code for starting the push notification and subscribing to an interest
        PushNotifications.start(getApplicationContext(), "1652ed4e-3c61-4164-afbc-1acc0eefc3dd");
        PushNotifications.addDeviceInterest("hello");
        PushNotifications.addDeviceInterest("patient");
        Log.i("Device Interest", String.valueOf(PushNotifications.getDeviceInterests()));

        ImageView img,img2,img3,img4;


        img=(ImageView)findViewById(R.id.testblood);
        img2=(ImageView)findViewById(R.id.donateblood);
        img3=(ImageView)findViewById(R.id.video);
        img4=(ImageView)findViewById(R.id.myprofile);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(dashboard_activiy.this, chose_lab.class);
                startActivity(intent);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(dashboard_activiy.this, donor_option.class);
                startActivity(intent);
            }
        });
       img3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       });
    img4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent= new Intent(dashboard_activiy.this, user_profile.class);
            startActivity(intent);
        }
    });
    }

    // The below code is to show the firebase cloud remote notification live
    // That is when the app is on the foreground not the background

//    public final View getRootView()
//    {
//        View var10000 = this.rootView;
//        if (var10000 == null) {
//            Intrinsics.throwUninitializedPropertyAccessException("rootView");
//        }
//
//        return var10000;
//    }

//    protected void onResume()
//    {
//        super.onResume();
//        PushNotifications.setOnMessageReceivedListenerForVisibleActivity(this, (PushNotificationReceivedListener)(new PushNotificationReceivedListener() {
//            public void onMessageReceived(RemoteMessage remoteMessage) {
//
//                // Handle FCM messages here.
//                // If the application is in the foreground handle both data and notification messages here.
//                // Also if you intend on generating your own notifications as a result of a received FCM
//                // message, here is where that should be initiated. See sendNotification method below.
//
//                Intrinsics.checkParameterIsNotNull(remoteMessage, "remoteMessage");
//                dashboard_activiy var10000 = dashboard_activiy.this;
//                View var10001 = dashboard_activiy.this.getRootView();
//                StringBuilder var10002 = new StringBuilder();
//                RemoteMessage.Notification var10003 = remoteMessage.getNotification();
//                var10002 = var10002.append(var10003 != null ? var10003.getTitle() : null).append(System.getProperty("line.separator"));;
//                var10003 = remoteMessage.getNotification();
//                var10000.showInSnackBar(var10001, var10002.append(var10003 != null ? var10003.getBody() : null).toString());
//            }
//        }));
//    }
//
//    private final void showInSnackBar(View view, String message) {
//        Snackbar.make(view, (CharSequence)message,Snackbar.LENGTH_INDEFINITE).show();
//        Log.i("DEMO", message);
//    }
}
