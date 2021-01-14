package com.example.andriodfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddMilkInfo extends AppCompatActivity {
    android.widget.RadioGroup RadioGroup;
    EditText Milkquantity, Milkprice;
    String str,vall;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_milk_info);
        Intent inten=getIntent();
       str= inten.getStringExtra("val1");
       dbHelper=new DatabaseHelper(this);
        RadioGroup=findViewById(R.id.radiogroup);
        Milkquantity=findViewById(R.id.milkquantitiy);
        Milkprice=findViewById(R.id.milkprice);
        db=dbHelper.getReadableDatabase();
        String[] columns = {DatabaseContract.MilkMan._ID};
        Cursor c = db.query(DatabaseContract.MilkMan.TABLE_NAME, columns, DatabaseContract.MilkMan.COL_EMAIL + "=?", new String[]{str}
                , null, null, null, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            vall=String.valueOf(c.getLong(0));
        }
        else{
            vall="0";
        }
    }


    public void PostDetails (View view) {
        db=dbHelper.getWritableDatabase();
        String quantity=Milkquantity.getText().toString();
        int price=Integer.parseInt(Milkprice.getText().toString());
        int checkid=RadioGroup.getCheckedRadioButtonId();
        RadioButton radioButton=findViewById(checkid);
        String category= radioButton.getText().toString();

        if(quantity.equals("") || price==0 )
        {
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ContentValues args = new ContentValues();
            args.put(DatabaseContract.MilkMan.COL_QUANTITY,quantity);
            args.put(DatabaseContract.MilkMan.COL_PRICE,price);
            args.put(DatabaseContract.MilkMan.COL_QUALITY,category);
            String[] wherearg={str};
            Integer count= db.update(DatabaseContract.MilkMan.TABLE_NAME, args, DatabaseContract.MilkMan.COL_NAME + "=?",wherearg);
            if (count > 0) {
                Toast.makeText(this, count+"  Records updated: " , Toast.LENGTH_SHORT).show();
            }
            db.close();

        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.SearchOrders:
                Intent intent=new Intent(this,CustomerList1.class);
                intent.putExtra("var",vall);
                startActivity(intent);
                return true;
            case R.id.OtherMilkMans:
                Intent inten=new Intent(this,MilkManList.class);
                inten.putExtra("val",vall);
                startActivity(inten);

                return true;
            default:
                return super.onOptionsItemSelected(item);


        }





    }
}