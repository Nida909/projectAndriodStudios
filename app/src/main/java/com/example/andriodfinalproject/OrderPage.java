package com.example.andriodfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OrderPage extends AppCompatActivity {
String str,str1;
EditText ed1,ed2,ed3;
DatabaseHelper dbh;
SQLiteDatabase db;
long prc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
        Intent intn=getIntent();
        str=intn.getStringExtra("milkman");
        str1=intn.getStringExtra("customer");
        dbh=new DatabaseHelper(this);
        ed1=(EditText)findViewById(R.id.edt1);
        ed2=(EditText)findViewById(R.id.edt2);
        ed3=(EditText)findViewById(R.id.edt3);
    }
    public void onprice(View v)
    {
        db=dbh.getReadableDatabase();
        int qnt=Integer.parseInt(ed1.getText().toString());
        String[] colm={DatabaseContract.MilkMan.COL_PRICE};
        Cursor cr=db.query(DatabaseContract.MilkMan.TABLE_NAME,colm,DatabaseContract.MilkMan._ID+"=?", new String[] {str}
                , null, null, null, null);
        if (cr.getCount()==0) {
            Toast.makeText(getApplicationContext(),"No Record exist",Toast.LENGTH_LONG).show();
        }
        cr.moveToFirst();
        long price=cr.getLong(0);
        db.close();
        prc=price*qnt;
        ed3.setText("Total Price : "+prc);
    }
    public void onconfirm(View v)
    {
        db = dbh.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(DatabaseContract.OrderT.COL_QUALITY, ed1.getText().toString());
        values.put(DatabaseContract.OrderT.COL_PLACED_BY, str1);
        values.put(DatabaseContract.OrderT.COL_PLACED_TO, str);
        values.put(DatabaseContract.OrderT.COL_QUANTITY, ed2.getText().toString());
        values.put(DatabaseContract.OrderT.COL_PRICE, String.valueOf(prc));
        long newRowId = db.insert(DatabaseContract.OrderT.TABLE_NAME, null, values);
        if (newRowId > 0) {
            Toast.makeText(getApplicationContext(), "New Record Inserted: " + newRowId, Toast.LENGTH_LONG).show();
        }
        db.close();
    }
}