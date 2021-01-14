package com.example.andriodfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInPage extends AppCompatActivity {
    Button createaccount;
    EditText name,password;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    String val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        dbHelper = new DatabaseHelper(this);
        createaccount=findViewById(R.id.createaccount);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);



        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInPage.this,CreateMAccount.class));
                finish();
            }
        });

    }
    public void login(View view)
    {
        db=dbHelper.getReadableDatabase();
        if(name.equals("") || password.equals(""))
        {
            Toast.makeText(LogInPage.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String[] columns= {"Email","Name","Password"};
            String[] values={name.getText().toString(),password.getText().toString()};
            Cursor cursor=db.query("MilkMan",columns,"Name=? AND Password=?",values,null,null,null);
            if (cursor != null)
            {
                if(cursor.moveToFirst())
                {
                   val= cursor.getString(0);
                    Toast.makeText(this, "values "+val, Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(LogInPage.this,AddMilkInfo.class);
                    intent.putExtra("val1",val);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(LogInPage.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }


}