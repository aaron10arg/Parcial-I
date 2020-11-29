package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class porciones extends Activity {
    ListView list;
    String SlectedItem, SlectedPrecio;
    Integer p;
    principal pr;
    String[] item_name={
            "Calamar",
            "Camaron",
            "Gratin ",
            "Tenasas",
            "Pulpo",
            "Marinero",
            "Casa"

    };

    String[] item_desc={
            "Porcion Calamar Apanado",
            "Porcion Camaron Apanado",
            "Porcion de Gratin",
            "Porcion Tenasas Apanado",
            "Porcion Pulpo Apanado",
            "Porcion de Arroz Marinero",
            "Porcion Extra"
    };

    String[] item_precio={
            "$4,25",
            "$4,25",
            "$5,25",
            "$5,25",
            "$7,70",
            "$4,80",
            "$5,64"

    };
    Integer[] img_id={

            R.drawable.calamar,
            R.drawable.pcamaron,
            R.drawable.gratin,
            R.drawable.tenazas,
            R.drawable.pulpo,
            R.drawable.marinero,
            R.drawable.casa,
    };
    Boolean[] eleccion={
            false,
            false,
            false,
            false,
            false,
            false,
            false
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porciones);

        list = (ListView) findViewById(R.id.listaPorciones);
        CustomListAdapter adaptador = new CustomListAdapter(this, item_name, item_desc, item_precio, img_id);
        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p=position;
                new AlertDialog.Builder(porciones.this).setTitle("Confirmacion de Agregacion").setMessage("Deseas agregar ese plato?").setPositiveButton("Si",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //  Toast.makeText(porciones.this,"si",Toast.LENGTH_LONG).show();

                        SlectedItem = item_name[p];
                        SlectedPrecio = item_precio[p];

                        Intent i = new Intent(porciones.this, Carrito.class);
                        i.putExtra("itemn", item_name[p].toString());
                        i.putExtra("itemd", item_desc[p].toString());
                        i.putExtra("itemp", item_precio[p].toString());
                        i.putExtra("itemi", img_id[p]);
                        finish();
                        startActivity(i);
                        GuardaItem();
                    }
                }).setNegativeButton("No!",null).show();
                //   Toast toast = Toast.makeText(mariscos.this,position+ " Item: " + SlectedItem, Toast.LENGTH_LONG);
                //   toast.show();
                //   item_precio.equals("S0");
            }
        });
        findViewById(R.id.imageButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                porciones.this.startActivity(new Intent(porciones.this, categorias.class));
            }
        });
    }
    public void GuardaItem() {
        AdminSQLiteOpenHelper guarda = new AdminSQLiteOpenHelper(this,"item",null,1);
        SQLiteDatabase base =guarda.getWritableDatabase();

        String hola = Global.ivar1;

        //es una clase para guardar datos
        ContentValues guardar_item =new ContentValues();
        guardar_item.put("descripcion",SlectedItem);
        guardar_item.put("precio",SlectedPrecio);
        guardar_item.put("pedido",hola);

        base.insert("item",null,guardar_item);
        base.close();
        Toast.makeText(this,"Item Guardado: "+SlectedItem+" "+hola, Toast.LENGTH_SHORT).show();

    }
}