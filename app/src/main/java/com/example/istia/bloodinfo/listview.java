package com.example.istia.bloodinfo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class listview extends AppCompatActivity {

    String bgroup,dis;
    private ListView listView;
    private databasehelper databasehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        bgroup = getIntent().getStringExtra("bloodgroup");
        dis = getIntent().getStringExtra("district");


        listView = findViewById(R.id.listviewid);
        databasehelper = new databasehelper(this);

        loaddata();
    }
    public  void loaddata(){
        ArrayList<String> listdata = new ArrayList<>();
        Cursor cursor = databasehelper.showalldata(bgroup,dis);
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "No Data Available!!", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                listdata.add(" Name : " +cursor.getString(1)+" \n Email : " +cursor.getString(2)+" \n Phone Number : "+cursor.getString(5));

            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.textviewid,listdata);
            listView.setAdapter(adapter);
        }
    }
}
