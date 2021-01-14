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

public class CreateAccount extends AppCompatActivity {
    DatabaseHelper dbHelper;
    EditText et1, et2,et3,et4,et5;
    String val1,val2,val3,val4,val5;
    String vall;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        dbHelper = new DatabaseHelper(this);
    }
    public void onCreate(View v) {
        db = dbHelper.getWritableDatabase();
        et1 = (EditText) findViewById(R.id.edt1);
        et2 = (EditText) findViewById(R.id.edt2);
        et3 = (EditText) findViewById(R.id.edt3);
        et4 = (EditText) findViewById(R.id.edt4);
        et5 = (EditText) findViewById(R.id.edt5);
        val1 = et1.getText().toString();
        val2 = et2.getText().toString();
        val3 = et3.getText().toString();
        val4 = et4.getText().toString();
        val5 = et5.getText().toString();
        if((val1.isEmpty())||(val2.isEmpty())||(val3.isEmpty())||(val4.isEmpty())||(val5.isEmpty()))
        {
            Toast.makeText(this,"Fill the field First",Toast.LENGTH_LONG).show();
        }else {


            ContentValues values = new ContentValues();
            values.put(DatabaseContract.Customers.COL_NAME, val1);
            values.put(DatabaseContract.Customers.COL_CONTACT, val2);
            values.put(DatabaseContract.Customers.COL_LOCATION, val3);
            values.put(DatabaseContract.Customers.COL_EMAIL, val4);
            values.put(DatabaseContract.Customers.COL_PASSWORD, val5);
            long newRowId = db.insert(DatabaseContract.Customers.TABLE_NAME, null, values);
            if (newRowId > 0) {
                Toast.makeText(this, "New Record Inserted: " + newRowId, Toast.LENGTH_LONG).show();
            }
            String[] columns = {DatabaseContract.Customers._ID, DatabaseContract.Customers.COL_EMAIL};
            Cursor c = db.query(DatabaseContract.Customers.TABLE_NAME, columns, DatabaseContract.Customers.COL_EMAIL + "=?", new String[]{val4}
                    , null, null, null, null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                vall = String.valueOf(c.getLong(0));
                Toast.makeText(getApplicationContext(), "Record id" + vall, Toast.LENGTH_SHORT).show();
                db.close();

                Intent intent = new Intent(this, MilkManList.class);
                intent.putExtra("val", vall);
                startActivity(intent);
            }
        }
    }
    }
