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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomerHistory extends AppCompatActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Activity activity;
    TextView tv;
    ArrayList<history1> customer=new ArrayList<history1>();
    private ProgressDialog progressDialog;
    ListView listView;
    String str1,str2,str3,str4,str5;
    String num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_history);
          Intent it=getIntent();
          num=it.getStringExtra("var");

        activity = this;
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();

        tv=(TextView)findViewById(R.id.txt);
        String[] columns={DatabaseContract.OrderT._ID,DatabaseContract.OrderT.COL_PLACED_TO,DatabaseContract.OrderT.COL_QUANTITY,
        DatabaseContract.OrderT.COL_PRICE};
        Cursor c = db.query(DatabaseContract.OrderT.TABLE_NAME,columns,DatabaseContract.OrderT.COL_PLACED_BY + "=?", new String[] {num}
                , null, null, null, null);
        if (c.getCount() > 0)  {
            while (c.moveToNext()) {
                Long id = c.getLong(0);
                long place = c.getLong(1);
                long quantity = c.getLong(2);
                long price = c.getLong(3);
                String[] column = {DatabaseContract.MilkMan.COL_NAME};
                Cursor cr = db.query(DatabaseContract.MilkMan.TABLE_NAME, column, DatabaseContract.MilkMan._ID + "=?", new String[]{String.valueOf(place)}, null, null, null, null);
                if (cr.getCount() == 0) {
                    str1 = "first Milk man";
                } else {

                    cr.moveToFirst();
                    str1 = cr.getString(0);
                    history1 mObj = new history1("Milk Man Name: " + str1, "Milk Quantity : " + String.valueOf(quantity),  String.valueOf(id), "Total Price : " + String.valueOf(price));
                    customer.add(mObj);
                }

            }
            listView=(ListView)findViewById(R.id.list);
            history2 customhList = new history2(activity,customer);
            listView.setAdapter(customhList);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    String ss=customer.get(position).getOrderNo();
                    Toast.makeText(getApplicationContext(),"You Selected "+ss+ " as Country", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(CustomerHistory.this,deleteRecord.class);
                    intent.putExtra("val",ss);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"You Selected "+customer.get(position).getName()+ " as Country", Toast.LENGTH_LONG).show();        }
            });
        }
        else{
            Toast.makeText(getApplicationContext(),"No Record exist",Toast.LENGTH_LONG).show();
            //history1 mObj = new history1("No ","Recods ","Exist ","here ");
            //customer.add(mObj);
            tv.setText("Your History is Empty");
            tv.setTextSize(32);
        }
        db.close();



    }
    }
