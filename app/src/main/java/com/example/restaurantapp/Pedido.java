package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Pedido extends Activity {
    ListView listado;
    ImageButton clear;
    TextView desc, pre;
    Integer[] arreglo3;
    String dato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        clear = (ImageButton) findViewById(R.id.clear);
        desc = (TextView) findViewById(R.id.item);
        pre = (TextView) findViewById(R.id.itempre);

        Bundle p = getIntent().getExtras();
        dato = p.getString("eso");

        Listar();
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Pedido.this)
                        .setTitle("Confirmacion de Eliminacion")
                        .setMessage("Estas seguro de eliminar\n todos los platos?")
                        .setPositiveButton("Si Completamente ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Borrar();
                            }
                        }).setNegativeButton("No!", null).show();
            }
        });
        findViewById(R.id.imageButton7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pedido.this.startActivity(new Intent(Pedido.this, Notas.class));
            }
        });

    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void Listar() {
        AdminSQLiteOpenHelper lista = new AdminSQLiteOpenHelper(this,"item",null,1);
        SQLiteDatabase base =lista.getReadableDatabase();
        if(base!=null){
            Cursor c =base.rawQuery("select * from item where pedido=="+dato,null);
            int cantidad=c.getCount();
            int i=0;
            String[] arreglo= new String[cantidad];
            String[] arreglo2= new String[cantidad];
            arreglo3= new Integer[cantidad];
            if(c.moveToFirst()){
                do{
                    int codigo =c.getInt(0);
                    String nombre= c.getString(1);
                    String precio= c.getString(2);
                    String pedido= c.getString(3);
                    arreglo[i] =nombre;
                    arreglo2[i]=precio;
                    arreglo3[i]=codigo;
                    i++;
                }while(c.moveToNext());
            }


            listado = (ListView) findViewById(R.id.pedido);
            ListadoFinal otro = new ListadoFinal(this, arreglo, arreglo2);
            listado.setAdapter(otro);

        }

    }
    public void Borrar() {
        AdminSQLiteOpenHelper total = new AdminSQLiteOpenHelper(this,"item", null, 1);
        SQLiteDatabase base = total.getWritableDatabase();

        int cant = base.delete("item" ,"pedido="+dato, null); // (oficina es el nombre de la tabla, condición)
        base.close();
        if (cant == 1)
            Toast.makeText(this, "Acaba de borrar todos los platos...",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existen platos!\nAgrege Nuevamente Platos", Toast.LENGTH_SHORT).show();
    }
}