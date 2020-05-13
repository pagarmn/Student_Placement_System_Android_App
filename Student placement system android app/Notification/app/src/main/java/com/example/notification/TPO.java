package com.example.notification;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TPO extends AppCompatActivity {


    DBHelper mydb;

    private EditText edittext, editText1, editText3;
    private Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpo2);

        edittext = (EditText) findViewById(R.id.edittext);
        editText1 = (EditText) findViewById(R.id.edittext1);
        editText3 = (EditText) findViewById(R.id.edittext3);
        btnsave = (Button) findViewById(R.id.btnsave);

        mydb = new DBHelper(this);

        btnsave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int us = 0;
                int temp = 0, flag = 0, num = 0;
                String username = edittext.getText().toString();
                String password = editText1.getText().toString();
// validation of username
                for (int i = 0; i < username.length(); i++) {
                    if ((username.charAt(i) >= '0' && username.charAt(i) <= '9'))
                        us++;
                }

                for (int i = 0; i < password.length(); i++) {
                    if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z')
                        temp++;


                    if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z')
                        flag++;


                    if (password.charAt(i) >= '0' && password.charAt(i) <= '9')
                        num++;

                }
                if ("".equals(edittext.getText().toString()) ||"".equals(editText3.getText().toString())) {
                    showmessage("Error","Enter all details");
                }


                //validation for password

                else if (password.length() < 8)
                    Toast.makeText(TPO.this, "invalid length of password", Toast.LENGTH_LONG).show();


                else if (temp == 0)
                    Toast.makeText(TPO.this, "no uppercase", Toast.LENGTH_LONG).show();
                else if (flag == 0)
                    Toast.makeText(TPO.this, "no lowercase", Toast.LENGTH_LONG).show();

                else if (num == 0)
                    Toast.makeText(TPO.this, "no number", Toast.LENGTH_LONG).show();


                    //if satisfied
                else {
                    boolean isinserted = mydb.insertdata(edittext.getText().toString(), editText1.getText().toString());
                    if (isinserted == true) {
                        showmessage("SUCESSFUL","TPO IS SUCESSFULLY ADDED");
                        edittext.setText("");
                        editText1.setText("");
                        editText3.setText("");


                        //Toast.makeText(TPO.this, "TPO IS SUCESSFULLY ADDED ", Toast.LENGTH_LONG).show();
                    } else {
                        showmessage("ERROR","ENTER VALID DETAILS");
                        //Toast.makeText(TPO.this, "NOT ADDED", Toast.LENGTH_LONG).show();

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

