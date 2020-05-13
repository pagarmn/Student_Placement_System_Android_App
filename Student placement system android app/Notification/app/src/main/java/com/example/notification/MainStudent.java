package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.view.View;
import android.widget.Button;

public class MainStudent extends AppCompatActivity {
private Button btncomview,btnupview,btnselview;
SQLiteDatabase db;
String str="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);

       btncomview=(Button)findViewById(R.id.btncomview);
       btnupview=(Button)findViewById(R.id.btnupview);
        btnselview=(Button)findViewById(R.id.btnselview);

        btncomview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent=new Intent(MainStudent.this,companyview.class);

                startActivity(intent);
            }
        });
        btnupview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://console.firebase.google.com/u/0/project/notification-b3d26/storage/notification-b3d26.appspot.com/files~2FUploads~2F"));
                startActivity(browserIntent);
            }
        });

        btnselview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=new Intent(MainStudent.this,view.class);
              startActivity(intent);
            }
        });
    }
}
