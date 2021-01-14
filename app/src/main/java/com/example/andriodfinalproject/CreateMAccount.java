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

public class CreateMAccount extends AppCompatActivity {
    EditText name,location,email,password,contact;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_m_account);
        name=findViewById(R.id.name);
        location=findViewById(R.id.location);
        dbHelper=new DatabaseHelper(this);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        contact=findViewById(R.id.contactnumber);


    }

    public void SaveDetails (View view) {
 db=dbHelper.getWritableDatabase();
        String Name=name.getText().toString();
        String Location=location.getText().toString();
        String Email=email.getText().toString();
        String Password=password.getText().toString();
        String ContactNumber=contact.getText().toString();

        if(Name.equals("") || Location.equals("") ||Email.equals("") ||Password.equals("") ||ContactNumber.equals(""))
        {
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ContentValues values = new ContentValues();
            values.put(DatabaseContract.MilkMan.COL_NAME, Name);
            values.put(DatabaseContract.MilkMan.COL_CONTACT,ContactNumber);
            values.put(DatabaseContract.MilkMan.COL_LOCATION,Location);
            values.put(DatabaseContract.MilkMan.COL_EMAIL,Email);
            values.put(DatabaseContract.MilkMan.COL_PASSWORD,Password);
            long newRowId = db.insert(DatabaseContract.MilkMan.TABLE_NAME, null, values);
            if (newRowId > 0) {
                Toast.makeText(this, "New Record Inserted: " + newRowId, Toast.LENGTH_LONG).show();
            }

            db.close();

            Intent intent=new Intent(this,AddMilkInfo.class);
            intent.putExtra("val1",Email);
            startActivity(intent);

        }


    }

}