package com.example.andriodfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class deleteRecord extends AppCompatActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    String val1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_record);
        dbHelper = new DatabaseHelper(this);
        Intent intent=getIntent();
      val1=intent.getStringExtra("val");
    }
    public void delbtn(View v)
    {
        db = dbHelper.getWritableDatabase();
        Toast.makeText(deleteRecord.this,"You Selected  "+val1, Toast.LENGTH_LONG).show();
        Integer i1= db.delete(DatabaseContract.OrderT.TABLE_NAME, DatabaseContract.OrderT._ID+" = ?",new String[] {val1});
        if (i1 > 0) {
            Toast.makeText(getApplicationContext(),"You Selected  as Country", Toast.LENGTH_LONG).show();
            Toast.makeText(this, i1+"  Records deleted: " , Toast.LENGTH_SHORT).show();
        }
        db.close();

    }

}