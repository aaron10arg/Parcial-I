package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class ControlMenu extends Activity {
    ImageButton uno;
    ImageButton dos;
    RelativeLayout insertar;
    int cambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_menu);


        findViewById(R.id.btnimg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlMenu.this.startActivity(new Intent(ControlMenu.this, administrador.class));
            }
        });

        uno = (ImageButton) findViewById(R.id.p1);
        dos = (ImageButton) findViewById(R.id.p2);
        insertar = (RelativeLayout) findViewById(R.id.idRela);

        uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambio = R.drawable.pantallaso;
                insertar.setBackgroundResource(cambio);



            }
        });
        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambio = R.drawable.pantallaso2;
                insertar.setBackgroundResource(cambio);


            }
        });

        AdminSQLiteOpenHelper guarda = new AdminSQLiteOpenHelper(this, "fondo", null, 1);
        SQLiteDatabase base = guarda.getWritableDatabase();
        //es una clase para guardar datos
        ContentValues grabar_oficina = new ContentValues();
        grabar_oficina.put("imagen", cambio);
        base.insert("fondo", null, grabar_oficina);
        base.close();
        Toast.makeText(this, "Imagen Cambiada" + cambio, Toast.LENGTH_SHORT).show();


    }


   /* public void fondos(View v){
        AdminSQLiteOpenHelper guarda = new AdminSQLiteOpenHelper(this,"fondo",null,1);
        SQLiteDatabase base =guarda.getWritableDatabase();
        //es una clase para guardar datos
        ContentValues grabar_oficina =new ContentValues();
        grabar_oficina.put("imagen",cambio);
        base.insert("fondo",null,grabar_oficina);
        base.close();
        Toast.makeText(this, "Imagen Cambiada"+cambio, Toast.LENGTH_SHORT).show();

    }*/

}