package com.example.notification;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainTpo extends AppCompatActivity {


    Button add_c,noti,add_p,add_s,add_ft;
    SQLiteDatabase db;
ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tpo);

        add_c = (Button) findViewById(R.id.add_comp);
        noti = (Button) findViewById(R.id.not);
        add_p = (Button) findViewById(R.id.add_pp);
        add_s = (Button) findViewById(R.id.add_ss);

        db = openOrCreateDatabase("placement", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR,id VARCHAR,branch VARCHAR,cgpa INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS company(name VARCHAR,cgpa INTEGER,branch VARCHAR,date DATE,job VARCHAR)");

        add_c.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainTpo.this, AddCompany.class);
                startActivity(myIntent);
            }
        });


        noti.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainTpo.this, Notification.class);
                startActivity(intent);
            }
        });
        add_p.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
              //  Toast.makeText(MainTpo.this, "UploadFilee...", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(MainTpo.this, FileuploadFirebase.class);
               startActivity(intent);
            }
        });
        add_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainTpo.this, SelectedStudents.class);
                startActivity(intent);
            }
        });


    }
}