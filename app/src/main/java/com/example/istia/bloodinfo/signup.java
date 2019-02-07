package com.example.istia.bloodinfo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity implements View.OnClickListener {
    private EditText nameEditText,emailEditText,bloodgroupEditText,districtEditText,phoneEditText,passwordEditText;
    private Button signup;


    com.example.istia.bloodinfo.databasehelper databasehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        databasehelper=new databasehelper(this);
        SQLiteDatabase sqLiteDatabase = databasehelper.getWritableDatabase();
        nameEditText =(EditText) findViewById(R.id.name);
        emailEditText =(EditText) findViewById(R.id.email);
        passwordEditText=(EditText) findViewById(R.id.password);
        bloodgroupEditText =(EditText) findViewById(R.id.b_group);
        districtEditText =(EditText) findViewById(R.id.district);
        phoneEditText =(EditText) findViewById(R.id.phone);
        signup = (Button) findViewById(R.id.Sign_up);

        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password=passwordEditText.getText().toString();
        String bloodgroup = bloodgroupEditText.getText().toString();
        String district = districtEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        if(view.getId()==R.id.Sign_up)
        {
            if(name.equals("") || email.equals("") || password.equals("") || bloodgroup.equals("") || district.equals("") || phone.equals("")){
                Toast.makeText(getApplicationContext(),"Fill Up with required information!",Toast.LENGTH_LONG).show();
            }
            else {
                Cursor cursor = databasehelper.showdata(phone);
                if(cursor.getCount()==0){
                    long rowid = databasehelper.insertData(name,email,password,bloodgroup,district,phone);
                    if(rowid==-1)
                    {
                        Toast.makeText(getApplicationContext(), "Unsuccessful Insertion!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Registration Successfully!", Toast.LENGTH_LONG).show();
                        if(view.getId()==R.id.Sign_up){
                            Intent login=new Intent(signup.this,signin.class);
                            startActivity(login);
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Phone Number Already Exist!", Toast.LENGTH_LONG).show();
                }

            }
        }
    }
}
