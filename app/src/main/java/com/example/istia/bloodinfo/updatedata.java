package com.example.istia.bloodinfo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updatedata extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText,emailEditText,bloodgroupEditText,districtEditText,phoneEditText,passwordEditText;
    private Button updateinfo;
    databasehelper databasehelper;
    String phn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedata);

        databasehelper=new databasehelper(this);
        SQLiteDatabase sqLiteDatabase = databasehelper.getWritableDatabase();
        nameEditText =(EditText) findViewById(R.id.name);
        emailEditText =(EditText) findViewById(R.id.email);
        passwordEditText=(EditText) findViewById(R.id.password);
        bloodgroupEditText =(EditText) findViewById(R.id.b_group);
        districtEditText =(EditText) findViewById(R.id.district);
        phoneEditText =(EditText) findViewById(R.id.phone);
        updateinfo=(Button) findViewById(R.id.updateinfo);

        updateinfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password=passwordEditText.getText().toString();
        String bloodgroup = bloodgroupEditText.getText().toString();
        String district = districtEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        phn=phone;
        if(name.equals("") || email.equals("") || password.equals("") || bloodgroup.equals("") || district.equals("") || phone.equals("")){
            Toast.makeText(getApplicationContext(),"Enter Valid Data.",Toast.LENGTH_LONG).show();
        }
        else{
            boolean ck=databasehelper.updatedata(name,email,password,bloodgroup,district,phone);
            if(ck==true){
                Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_LONG).show();
                if(view.getId()==R.id.updateinfo) {
                    Intent tmp=new Intent(updatedata.this,Userdetails.class);
                    tmp.putExtra("phone",phn);
                    startActivity(tmp);
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"Enter Valid Data!",Toast.LENGTH_LONG).show();
            }
        }

    }
}
