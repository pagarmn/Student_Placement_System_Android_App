package com.example.notification;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCompany extends AppCompatActivity {

    SQLiteDatabase db;
    EditText n,c,b,d,j;
    Button add;
    DBHelper2 mydb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);

        n=(EditText)findViewById(R.id.e1);
        c=(EditText)findViewById(R.id.e2);
        b=(EditText)findViewById(R.id.e3);
        d=(EditText)findViewById(R.id.e4);
        j=(EditText)findViewById(R.id.e5);
        add=(Button)findViewById(R.id.button) ;
        mydb2= new DBHelper2(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean isinserted = mydb2.insertdata(n.getText().toString(),c.getText().toString(),b.getText().toString(),d.getText().toString(),j.getText().toString());
               if (isinserted == true) {

                    showMessage("SUCESSFUL","COMPANY IS SUCESSFULLY ADDED");
                    clearText();
                    //Toast.makeText(Student.this, "Data is inserted", Toast.LENGTH_LONG).show();
                } else {
                    showMessage("ERROR","ENTER VALID DETAILS");
                    //Toast.makeText(Student.this, "Data is not inserted", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        n.setText("");
        c.setText("");
        d.setText("");
        b.setText("");
        j.setText("");
    }
}
