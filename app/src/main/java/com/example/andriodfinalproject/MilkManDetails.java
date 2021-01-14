package com.example.andriodfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MilkManDetails extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    String str,str2,s1,s2,s3,s4,s5,s6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk_man_details);

        Intent intent=getIntent();
        str=intent.getStringExtra("val");//milkman id
        str2=intent.getStringExtra("val2");//customer id
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();
        t1=(TextView)findViewById(R.id.txt1);
        t2=(TextView)findViewById(R.id.txt2);
        t3=(TextView)findViewById(R.id.txt3);
        t4=(TextView)findViewById(R.id.txt4);
        t5=(TextView)findViewById(R.id.txt5);
        t6=(TextView)findViewById(R.id.txt6);
        String[] colm={DatabaseContract.MilkMan.COL_NAME,DatabaseContract.MilkMan.COL_LOCATION,DatabaseContract.MilkMan.COL_CONTACT,
        DatabaseContract.MilkMan.COL_QUALITY,DatabaseContract.MilkMan.COL_QUANTITY,DatabaseContract.MilkMan.COL_PRICE};
        Cursor c = db.query(DatabaseContract.MilkMan.TABLE_NAME,colm,DatabaseContract.MilkMan._ID + "=?", new String[] {str}
                , null, null, null, null);
        if (c.getCount()==0) {
            Toast.makeText(getApplicationContext(),"No Record exist",Toast.LENGTH_LONG).show();
        }
        c.moveToFirst();
        s1=c.getString(0);
        s2=c.getString(1);
        s3=c.getString(2);
        s4=c.getString(3);
        long qnt=c.getLong(4);
        long prc=c.getLong(5);
        db.close();
        s5=String.valueOf(qnt);
        s6=String.valueOf(prc);
        t1.setText(s1);
t2.setText(s2);
t3.setText(s3);
t4.setText(s4);
t5.setText(s5);
t6.setText(s6);
    }
    public void onplace(View v)
    {
Intent intn=new Intent(this,OrderPage.class);
intn.putExtra("milkman",str);
intn.putExtra("customer",str2);
startActivity(intn);
    }
}