package com.example.andriodfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class orderDetails extends AppCompatActivity {
TextView t1,t2,t3,t4,t5,t6;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Intent intent=getIntent();
        String str=intent.getStringExtra("val");
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();
        t1=(TextView)findViewById(R.id.txt1);
        t2=(TextView)findViewById(R.id.txt2);
        t3=(TextView)findViewById(R.id.txt3);
        t4=(TextView)findViewById(R.id.txt4);
        t5=(TextView)findViewById(R.id.txt5);
        t6=(TextView)findViewById(R.id.txt6);
        String[] columns={DatabaseContract.OrderT.COL_PLACED_BY,DatabaseContract.OrderT.COL_QUALITY,DatabaseContract.OrderT.COL_QUANTITY,
        DatabaseContract.OrderT.COL_PRICE};
        Cursor c = db.query(DatabaseContract.OrderT.TABLE_NAME,columns,DatabaseContract.OrderT._ID + "=?", new String[] {str}
                , null, null, null, null);
        if (c.getCount()==0) {
            Toast.makeText(getApplicationContext(),"No Record exist",Toast.LENGTH_LONG).show();
        }
       c.moveToFirst();
        long by=c.getLong(0);
        String qlt=c.getString(1);
        long qnt=c.getLong(2);
        long prc=c.getLong(3);
        String[] colum={DatabaseContract.Customers.COL_NAME,DatabaseContract.Customers.COL_CONTACT,DatabaseContract.Customers.COL_LOCATION};
        Cursor cr = db.query(DatabaseContract.Customers.TABLE_NAME,colum,DatabaseContract.Customers._ID + "=?", new String[] {String.valueOf(by)}
                , null, null, null, null);
        if (cr.getCount()==0) {
            Toast.makeText(getApplicationContext(),"No Record exist",Toast.LENGTH_LONG).show();
        }
        cr.moveToFirst();
        String s1=cr.getString(0);
        String s2=cr.getString(1);
        String s3=cr.getString(2);
        t1.setText("Customer Name :   "+s1);
        t2.setText("Contact No :   "+s2);
        t3.setText("Destination :   "+s3);
        t4.setText("Milk Category :   "+qlt);
        t5.setText("Milk Quantity :   "+String.valueOf(qnt));
        t6.setText("Price :  "+String.valueOf(prc));
    }
}