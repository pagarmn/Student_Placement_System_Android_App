package com.example.notification;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class view extends AppCompatActivity {
private TextView txt1;
    DBHelper3 mydb3;
    String student,str="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        txt1=(TextView)findViewById(R.id.txt1);
        mydb3= new DBHelper3(this);

        Cursor res = mydb3.getAllData();
        if (res.getCount() == 0) {
            showmessage("ERROR!!!", "nothing found");
        }

        while (res.moveToNext()) {
            student= res.getString(0);
            str+="\n"+student+"";
            //al.add(stupre);

        }
        txt1.setText(str+"");
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
