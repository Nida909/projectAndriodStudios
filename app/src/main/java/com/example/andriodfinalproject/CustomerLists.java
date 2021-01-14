package com.example.andriodfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomerLists extends BaseAdapter {
    private Activity context;
    ArrayList<MilkMan> customer;


    public CustomerLists(Activity context, ArrayList cust) {
        // super(context, R.layout.row_item, countries);
        this.context = context;
        this.customer=cust;

    }

    public static class ViewHolder
    {
        TextView textViewCapital;
        TextView textViewCountry;
        TextView tv;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;

        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder vh;
        if(convertView==null) {
            vh=new ViewHolder();
            row = inflater.inflate(R.layout.activity_customer_lists, null, true);
            vh.textViewCountry = (TextView) row.findViewById(R.id.textViewCountry);
            vh.textViewCapital = (TextView) row.findViewById(R.id.textViewCapital);
            vh.tv = (TextView) row.findViewById(R.id.ordertxt);
            // store the holder with the view.
            row.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.textViewCountry.setText(customer.get(position).getName());
        vh.textViewCapital.setText(""+customer.get(position).getContact());
        vh.tv.setText(""+customer.get(position).getOrderNo());
        Log.d("Tag",customer.get(position).getContact());
        return  row;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return position;
    }

    public int getCount() {

        if(customer.size()<=0)
            return 1;
        return customer.size();
    }
}