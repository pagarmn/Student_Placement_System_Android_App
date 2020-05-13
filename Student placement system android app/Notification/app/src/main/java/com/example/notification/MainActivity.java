package com.example.notification;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText username,password;
    private Button login;
    private Spinner spinner;
    String spin;
    String usertpo,passtpo,userstu,passstu;
    DBHelper mydb;
    DBHelper1 mydb1;
    ArrayList<String> al=new ArrayList<>();
    ArrayList<String> al2=new ArrayList<>();
    ArrayList<String> alstu=new ArrayList<>();
    ArrayList<String> alstu1=new ArrayList<>();

    ArrayAdapter<String> adapter;
    String[] user={"Admin","TPO","Student"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);

        spinner=(Spinner)findViewById(R.id.spinner);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,user);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        mydb=new DBHelper(MainActivity.this);
        mydb1=new DBHelper1(MainActivity.this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spin=(String)spinner.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (spin.equals("Admin")) {
                    if ((username.getText().toString()).equals("")) {
                        Toast.makeText(MainActivity.this, "Enter Username", Toast.LENGTH_LONG).show();
                    }
                      if ((username.getText().toString()).equals("")) {
                        Toast.makeText(MainActivity.this, "Enter password", Toast.LENGTH_LONG).show();
                    }
                    if ((username.getText().toString()).equals("iamadmin") && (password.getText().toString()).equals("cell123")) {
                        // Toast.makeText(MainActivity.this, "Admin", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Admin.class);
                        startActivity(intent);
                   } else {
                        showmessage("Error!!!", "Enter Valid Details");
                    }


                }
                if (spin.equals("TPO")) {
                    Cursor res = mydb.getAllData();
                    if (res.getCount() == 0) {
                        showmessage("ERROR!!!", "nothing found");
                    }

                    while (res.moveToNext()) {
                        usertpo = res.getString(1);
                        passtpo = res.getString(2);
                        al.add(usertpo+"");
                        al2.add(passtpo+"");

                       // Toast.makeText(MainActivity.this, "UserTpo" + usertpo + "PassTpo" + passtpo, Toast.LENGTH_LONG).show();
                    }
                    if ((username.getText().toString()).equals("")) {
                        Toast.makeText(MainActivity.this, "Enter Username", Toast.LENGTH_LONG).show();
                    }
                     if ((username.getText().toString()).equals("")) {
                        Toast.makeText(MainActivity.this, "Enter password", Toast.LENGTH_LONG).show();
                    }
                   if (al.contains((username.getText().toString())) && al2.contains((password.getText().toString()))) {
                       Intent intent = new Intent(MainActivity.this, MainTpo.class);
                       startActivity(intent);
                   }


                    else {
                        showmessage("Error!!!", "Enter Valid Details");
                    }

                }
                if (spin.equals("Student")) {
                    Cursor res = mydb1.getAllData1();
                    if (res.getCount() == 0) {
                        showmessage("ERROR!!!", "nothing found");
                    }

                    while (res.moveToNext()) {
                        userstu = res.getString(1);
                        passstu = res.getString(3);
                        alstu.add(userstu);
                        alstu1.add(passstu);

                       // Toast.makeText(MainActivity.this, "UserTpo" + userstu + "PassTpo" + passstu, Toast.LENGTH_LONG).show();
                    }
                    if ((username.getText().toString()).equals("")) {
                        Toast.makeText(MainActivity.this, "Enter Username", Toast.LENGTH_LONG).show();
                    }
                     if ((username.getText().toString()).equals("")) {
                        Toast.makeText(MainActivity.this, "Enter password", Toast.LENGTH_LONG).show();
                    }
                  if (alstu.contains((username.getText().toString())) && alstu1.contains((password.getText().toString()))) {
                        Intent intent = new Intent(MainActivity.this, MainStudent.class);
                        startActivity(intent);

                    }
                    else {
                        showmessage("Error!!!", "Enter Valid Details");
                    }
                }
            }
        });




    }
    public void showmessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
