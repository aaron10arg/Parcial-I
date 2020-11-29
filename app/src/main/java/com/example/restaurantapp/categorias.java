package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class categorias extends Activity {
    String cabron;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

       /* Bundle b=getIntent().getExtras();
        cabron=b.getString("elcoso");*/



        findViewById(R.id.porciones).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorias.this.startActivity(new Intent(categorias.this, porciones.class));
            }
        });
        findViewById(R.id.baquita).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorias.this.startActivity(new Intent(categorias.this, parrillada.class));
            }
        });


        findViewById(R.id.atras).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorias.this.startActivity(new Intent(categorias.this, principal.class));
            }
        });

        findViewById(R.id.jugo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorias.this.startActivity(new Intent(categorias.this, bebidas.class));
            }
        });
        findViewById(R.id.cangrejo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorias.this.startActivity(new Intent(categorias.this, mariscos.class));
            }
        });
    }


    public String  pedido() {
        String  ped=cabron;
        return ped;
    }
}