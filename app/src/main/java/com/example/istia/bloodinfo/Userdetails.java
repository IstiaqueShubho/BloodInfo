package com.example.istia.bloodinfo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Userdetails extends AppCompatActivity {

    private TextView nm,em,pass,bg,dis,phn;
    private databasehelper databasehelper;
    private Button update,searchdonar;
    String phoneid;
    String name,email,password,bloodgroup,district,phnnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);

        databasehelper = new databasehelper(this);

        phoneid=getIntent().getStringExtra("phone");
        Cursor cursor=databasehelper.showdata(phoneid);
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), phoneid, Toast.LENGTH_LONG).show();
        }
        else{
            while(cursor.moveToNext()) {
                name=cursor.getString(1);
                email=cursor.getString(2);
                password=cursor.getString(3);
                district=cursor.getString(4);
                phnnumber=cursor.getString(5);
                bloodgroup=cursor.getString(6);
            }
        }

        nm=(TextView) findViewById(R.id.printname);
        nm.setText(name);

        em=(TextView) findViewById(R.id.printemail);
        em.setText(email);

        pass=(TextView)findViewById(R.id.printpassword);
        pass.setText(password);

        bg=(TextView) findViewById(R.id.printbgroup);
        bg.setText(bloodgroup);

        dis=(TextView) findViewById(R.id.printdistrict);
        dis.setText(district);

        phn=(TextView) findViewById(R.id.printphn);
        phn.setText(phnnumber);

        update=(Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent up=new Intent(Userdetails.this,updatedata.class);
                startActivity(up);
            }
        });

        searchdonar=(Button) findViewById(R.id.searchdonar);
        searchdonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search=new Intent(Userdetails.this,Finddonar.class);
                startActivity(search);
            }
        });

    }
}
