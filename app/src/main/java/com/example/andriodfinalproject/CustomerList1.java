package com.example.andriodfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomerList1 extends AppCompatActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Activity activity;
    ArrayList<MilkMan> customer=new ArrayList<MilkMan>();
    //private ProgressDialog progressDialog;
    ListView listView;
    String str1,str2,str3,str4,str5;
    String num,first;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list1);
        activity = this;
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();
        Intent inte=getIntent();
        first=inte.getStringExtra("var");

        tv=(TextView)findViewById(R.id.txt);
        String[] columns={DatabaseContract.OrderT._ID,DatabaseContract.OrderT.COL_PLACED_BY};
        Cursor c = db.query(DatabaseContract.OrderT.TABLE_NAME,columns,DatabaseContract.OrderT.COL_PLACED_TO + "=?", new String[] {first}
                , null, null, null, null);
        if (c.getCount()>0) {


            while (c.moveToNext()) {
                Long id = c.getLong(0);
                long place=c.getLong(1);
                String[] column = {DatabaseContract.Customers.COL_NAME, DatabaseContract.Customers.COL_LOCATION, DatabaseContract.Customers.COL_CONTACT};
                Cursor cr = db.query(DatabaseContract.Customers.TABLE_NAME, column, DatabaseContract.Customers._ID + "=?", new String[]{String.valueOf(place)}, null, null, null, null);
                cr.moveToFirst();
                str1 = cr.getString(0);
                str2 = cr.getString(1);
                str3 = cr.getString(2);
                MilkMan mObj = new MilkMan(str1, "Loc : "+str2 + " ,  Contact : " + str3,String.valueOf(id));
                customer.add(mObj);
            }
            listView = (ListView) findViewById(R.id.clist);
            CustomerLists customList = new CustomerLists(activity,customer);


            listView.setAdapter(customList);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    String ss=customer.get(position).getOrderNo();
                    Intent intent=new Intent(CustomerList1.this,orderDetails.class);
                    intent.putExtra("val",ss);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"You Selected "+customer.get(position).getName()+ " as Country", Toast.LENGTH_LONG).show();        }
            });
        }else {

            tv.setText("You have No Customer At The Movement");
            Toast.makeText(getApplicationContext(), "No Record exist", Toast.LENGTH_LONG).show();
        }
    }
}