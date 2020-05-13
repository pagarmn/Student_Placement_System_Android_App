package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Notification extends AppCompatActivity {

    private Button b1;
    private EditText edit;

    private final String CHANNEL_ID = "1";
    private final int NOTIFICATION_ID = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        b1 = (Button) findViewById(R.id.b1);
        edit = (EditText) findViewById(R.id.txt1);
        final String msg = edit.getText().toString();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = "personal notification";
                    String description = "Include All";
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;

                    NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "001", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationChannel.setDescription(description);


                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.createNotificationChannel(notificationChannel);


                    android.app.Notification.Builder builder = new android.app.Notification.Builder(Notification.this, CHANNEL_ID);
                    builder.setSmallIcon(R.mipmap.ic_launcher);

                    builder.setContentTitle(" Notification");
                    builder.setContentText("new file uploaded"+msg+"");
                    builder.setPriority(android.app.Notification.PRIORITY_DEFAULT);

                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(Notification.this);
                    notificationManagerCompat.notify(001, builder.build());
                }

            }
        });
    }
}
