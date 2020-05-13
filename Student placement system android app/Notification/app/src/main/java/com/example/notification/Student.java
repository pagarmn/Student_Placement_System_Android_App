package com.example.notification;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Student extends AppCompatActivity {

    DBHelper1 mydb;
    private EditText edittext, editText2, editText3;
    private Spinner editText1;
    private Button btnsave;
    String[] branch1={"CSE","IT"};
    String password,branch;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        edittext = (EditText) findViewById(R.id.edittext);
        editText1 = (Spinner) findViewById(R.id.edittext1);
        editText2 = (EditText) findViewById(R.id.edittext2);
        editText3 = (EditText) findViewById(R.id.edittext3);
        btnsave = (Button) findViewById(R.id.btnsave);

        mydb = new DBHelper1(this);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,branch1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editText1.setAdapter(adapter);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int us = 0;
                int temp = 0, flag = 0, num = 0;
                String username = edittext.getText().toString();
                String password = editText2.getText().toString();


                for (int i = 0; i < username.length(); i++) {
                    if ((username.charAt(i) > '0' && username.charAt(i) < '9'))
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

                    if ("".equals(edittext.getText().toString()) ||  "".equals(editText2.getText().toString())||"".equals(editText3.getText().toString())) {
                    showmessage("Error","Enter all details");
                }
                else if (password.length() < 8)
                    Toast.makeText(Student.this, "invalid length of password", Toast.LENGTH_LONG).show();


                else if (temp == 0)
                    Toast.makeText(Student.this, "no uppercase", Toast.LENGTH_LONG).show();
                else if (flag == 0)
                    Toast.makeText(Student.this, "no lowercase", Toast.LENGTH_LONG).show();

                else if (num == 0)
                    Toast.makeText(Student.this, "no number", Toast.LENGTH_LONG).show();



                else {
                    editText1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            branch=(String)editText1.getItemAtPosition(i);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    boolean isinserted = mydb.insertdata(username, password, editText2.getText().toString());
                    if (isinserted == true) {

                        showmessage("SUCESSFUL","STUDENT IS SUCESSFULLY ADDED");
                        edittext.setText("");
                        editText2.setText("");
                        editText3.setText("");
                        //Toast.makeText(Student.this, "Data is inserted", Toast.LENGTH_LONG).show();
                    } else {
                        showmessage("ERROR","ENTER VALID DETAILS");
                        //Toast.makeText(Student.this, "Data is not inserted", Toast.LENGTH_LONG).show();

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
