package com.example.istia.bloodinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signinsignup extends AppCompatActivity {

    Button signin,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinsignup);
        signin=findViewById(R.id.signin);
        signup=findViewById(R.id.signup);
        signin.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view){
                Intent signin=new Intent(signinsignup.this,signin.class);
                startActivity(signin);
            }
        });
        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent signup=new Intent(signinsignup.this, com.example.istia.bloodinfo.signup.class);
                startActivity(signup);
            }
        });
    }
}
