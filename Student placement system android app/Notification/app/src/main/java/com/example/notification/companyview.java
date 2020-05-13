package com.example.notification;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class companyview extends AppCompatActivity {
private TextView txt1;
DBHelper2 mydb2;
String str="";
StringBuffer sb=new StringBuffer();
String name,cgpa,branch,date,job;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companyview);
        txt1 = (TextView) findViewById(R.id.txt1);
        Intent intent=getIntent();
        mydb2=new DBHelper2(companyview.this);
        Cursor res = mydb2.getAllData();
        if (res.getCount() == 0) {
            showmessage("ERROR!!!", "nothing found");
        }

        while (res.moveToNext()) {
            name = res.getString(0);
            cgpa = res.getString(1);
            branch=res.getString(2);
            date=res.getString(3);
            job=res.getString(4);

            str="Company Name :"+name+"\nCGPA Required :"+cgpa+"\nBranch :"+branch+"\nJob Description :"+job+"\n\n";
            sb.append(str+"");

         Toast.makeText(companyview.this, "COMPANY NAME :"+name+"CGPA :"+cgpa+"branch :"+branch+"Job "+job, Toast.LENGTH_LONG).show();
        }
        txt1.setText(sb.toString()+"\n\n");

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
