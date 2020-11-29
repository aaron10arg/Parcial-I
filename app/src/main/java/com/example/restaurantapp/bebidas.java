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


public class bebidas extends Activity {
    ListView list;
    String SlectedItem, SlectedPrecio;
    Integer p;
    String[] item_name={
            "Coca Cola",
            "Club",
            "Sprite",
            "Limonada",
            "Jugo de Naranja",
            "Pilsener"
    };
    String[] item_desc={
            "",
            " ",
            " ",
            " ",
            " ",
            " "
    };
    String[] item_precio={
            "$0.50",
            "$1.50",
            "$0.50",
            "$5.00",
            "$5.00",
            "$1.25"
    };
    Integer[] img_id={
            R.drawable.cocacola,
            R.drawable.club,
            R.drawable.sprite,
            R.drawable.limonada,
            R.drawable.naranja,
            R.drawable.pilsener
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
        setContentView(R.layout.activity_bebidas);

        list = (ListView) findViewById(R.id.listaBebida);
        CustomListAdapter adaptador = new CustomListAdapter(this, item_name, item_desc, item_precio, img_id);
        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p=position;
                new AlertDialog.Builder(bebidas.this).setTitle("Confirmacion de Agregacion").setMessage("Deseas agregar ese plato?").setPositiveButton("Si",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SlectedItem = item_name[p];
                        SlectedPrecio = item_precio[p];

                        Intent i = new Intent(bebidas.this, Carrito.class);
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
        findViewById(R.id.imageButton4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bebidas.this.startActivity(new Intent(bebidas.this, categorias.class));
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