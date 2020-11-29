package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ListadoFinal extends ArrayAdapter<String> {


    private final Activity context;
    private final String[] item_name;
    private final String[] item_precio;



    public ListadoFinal(Activity context, String[] item_name,String[] item_precio) {
        super(context, R.layout.listadofinal, item_name);
        this.context = context;
        this.item_name = item_name;
        this.item_precio = item_precio;


    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listadofinal, null, true);


        TextView txtitem = (TextView) rowView.findViewById(R.id.item);
        TextView txtprecio = (TextView) rowView.findViewById(R.id.itempre);

        txtitem.setText(item_name[position]);
        txtprecio.setText(item_precio[position]);

        return rowView;


    }
}