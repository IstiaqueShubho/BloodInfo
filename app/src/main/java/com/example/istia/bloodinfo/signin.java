package com.example.istia.bloodinfo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signin extends AppCompatActivity implements View.OnClickListener {
    databasehelper databasehelper;
    private Button signin;
    private EditText phone;
    private EditText password;

    String phoneid,passwordid;

    Button sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signin=(Button) findViewById(R.id.signin);

        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);

        databasehelper = new databasehelper(this);
        SQLiteDatabase sqLiteDatabase=databasehelper.getWritableDatabase();

        signin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        phoneid=phone.getText().toString();
        passwordid=password.getText().toString();

        if(view.getId()==R.id.signin){
            if(phoneid.equals("") || passwordid.equals("")){
                Toast.makeText(getApplicationContext(), "Enter Valid Phone Number and Password!!", Toast.LENGTH_SHORT).show();
            }
            else {
                Boolean result=databasehelper.findpassword(phoneid,passwordid);

                sign=(Button) findViewById(R.id.signin);

                if(result==true){
                    if(view.getId()==R.id.signin){
                        Intent user=new Intent(signin.this,Userdetails.class);
                        user.putExtra("phone",phoneid);
                        startActivity(user);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Phone Number or Password Incorrect!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
