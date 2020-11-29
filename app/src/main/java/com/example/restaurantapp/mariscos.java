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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class mariscos extends Activity {
    ListView list;
    Integer p;
    String SlectedItem, SlectedPrecio, valor;
    EditText captar;

    String[] item_name={
            "Arroz con Camaron",
            "Camaron de Apanado-Tortilla de Camaron",
            "Camaron Reventado",
            "Camaron al Ajillo",
            "Camaron a la Plancha",
            "Camaron en Salsa de Mostaza"
    };

    String[] item_desc={
            "Arroz,papas fritas o patacones y guarniciones " ,
            "Papas fritas o arroz y mayonesa",
            "Reventado en mantequilla,arroz,papas fritas" ,
            "En salsa de aji y ajo ,papas fritas,arroz",
            "Arroz,papas salteadas con champinones  o papas fritas y guarniciones",
            "Camaron en cascara con mostaza ,patacones, y mayonesa"

    };

    String[] item_precio={
            "$3,50",
            "$6,50",
            "$9.00",
            "$1.00",
            "$1.50",
            "$8.50"


    };
    Integer[] img_id={
            R.drawable.arrozcamaron,
            R.drawable.tortilla,
            R.drawable.reventado,
            R.drawable.ajilll,
            R.drawable.plancha,
            R.drawable.salsa,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mariscos);


        list = (ListView) findViewById(R.id.listaVista);
        CustomListAdapter adaptador = new CustomListAdapter(this, item_name, item_desc, item_precio, img_id);
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p=position;
                // valor=captar.getText().toString();

                new AlertDialog.Builder(mariscos.this)
                        .setTitle("Confirmacion de Agregacion")
                        .setMessage("Deseas agregar ese plato?")
                        .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                SlectedItem = item_name[p];
                                SlectedPrecio = item_precio[p];

                                Intent i = new Intent(mariscos.this, Carrito.class);
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

        findViewById(R.id.imageButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mariscos.this.startActivity(new Intent(mariscos.this, categorias.class));
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