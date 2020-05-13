package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class SelectedStudents extends AppCompatActivity {
    EditText e;
    SQLiteDatabase db;
    Button add;
    String name,nameedit,stupre;
    DBHelper1 mydb1;

DBHelper3 mydb3;
    ArrayList<String> al=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_students);
        e = (EditText) findViewById(R.id.e1);
        add = (Button) findViewById(R.id.b);
        mydb1 = new DBHelper1(this);
        mydb3 = new DBHelper3(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameedit = e.getText().toString();
                Cursor res = mydb1.getAllData1();
                if (res.getCount() == 0) {
                    showMessage("ERROR!!!", "nothing found");
                }

                while (res.moveToNext()) {
                    stupre= res.getString(1);
                   al.add(stupre);

                    Toast.makeText(SelectedStudents.this, "student" +al , Toast.LENGTH_LONG).show();
                }




                if(al.contains(nameedit)) {
                    boolean isinserted = mydb3.insertdata(nameedit);
                    if (isinserted == true) {

                        showMessage("SUCESSFUL", "STUDENT IS SUCESSFULLY ADDED");
                        e.setText("");
                        //Toast.makeText(Student.this, "Data is inserted", Toast.LENGTH_LONG).show();
                    } else {
                        showMessage("ERROR", "ENTER VALID DETAILS");
                        //Toast.makeText(Student.this, "Data is not inserted", Toast.LENGTH_LONG).show();

                    }
                }
                else {
                    showMessage("ERROR", "ENTER VALID STUDENT");
                    //Toast.makeText(Student.this, "Data is not inserted", Toast.LENGTH_LONG).show();

                }
            }
        });
    }



    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(SelectedStudents.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}