package com.aniket.newnotify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;
    static String channelId = "My_channel";
    static int notificationId = 121;

    Notification notification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        btn = findViewById(R.id.btnNotify);

        //To save our image and convert it in bitmap
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.small_icon, null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap largeIcon = bitmapDrawable.getBitmap();

        //when we click on notification
        Intent notificationIntent = new Intent(MainActivity.this, NewActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    notification = new Notification.Builder(getApplicationContext())
                            .setLargeIcon(largeIcon)
                            .setSmallIcon(R.drawable.ic_notify) //set icon for notification
                            .setContentText("Notifications Example") //set title of notification
                            .setSubText("This is a notification message")//this is notification message
                            .setAutoCancel(true) // makes auto cancel of notification
                            .setChannelId(channelId)
                            .setContentIntent(pendingIntent)
                            .build();

                    //for notification channel
                    notificationManager.createNotificationChannel(new NotificationChannel(channelId, "New channel", NotificationManager.IMPORTANCE_HIGH));

                }else{
                    Notification notification = new Notification.Builder(getApplicationContext())
                            .setLargeIcon(largeIcon)
                            .setSmallIcon(R.drawable.ic_notify) //set icon for notification
                            .setContentText("Notifications Example") //set title of notification
                            .setSubText("This is a notification message")//this is notification message
                            .setAutoCancel(true) // makes auto cancel of notification
                            .setContentIntent(pendingIntent)
                            .build();
                }



//
                notificationManager.notify(notificationId, notification);
            }
        });
    }
}