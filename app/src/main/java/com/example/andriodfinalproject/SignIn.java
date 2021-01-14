package com.example.andriodfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class    SignIn extends AppCompatActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    String val1;
    int val2,val3,val4,val5;

    TextView tv,tt;
    EditText t1,t2;
    String str1,str2,str3;
    boolean bo=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        dbHelper = new DatabaseHelper(this);
        tv=(TextView)findViewById(R.id.txt1);
        tt=(TextView)findViewById(R.id.txt2);
        t1=(EditText) findViewById(R.id.edt1);
        t2=(EditText) findViewById(R.id.edt2);
    }
    public void onbtn1(View v)
    {
        db=dbHelper.getReadableDatabase();
        str1=t1.getText().toString();
        str2=t2.getText().toString();
        if((str1.isEmpty())||(str2.isEmpty()))
        {
            tt.setText("Field must be filled");
            tt.setVisibility(View.VISIBLE);
        }
        else {
            String[] columns= {DatabaseContract.Customers._ID,"Email","Password"};
            String[] values={str1,str2};
            Cursor cr=db.query(DatabaseContract.Customers.TABLE_NAME,columns,"Email=? AND Password=?",values,null,null,null);
                if (cr.getCount() > 0) {
                    cr.moveToFirst();
                    bo = true;
                    str3 = String.valueOf(cr.getLong(0));
                    tt.setText(str3);
                    tt.setVisibility(View.VISIBLE);
                    db.close();
                    Intent intent = new Intent(SignIn.this, MilkManList.class);
                    intent.putExtra("val", str3);
                    startActivity(intent);
                }
                else {
                    tt.setText("Email Address or Password is incorrect ");
                    tt.setVisibility(View.VISIBLE);
                }




        }

    }
    public void onbtn2(View v)
    {

        Intent intent=new Intent(this,CreateAccount.class);
        startActivity(intent);
    }
}