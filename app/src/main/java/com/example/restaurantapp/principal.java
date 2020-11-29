package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class principal extends Activity {
    String captar;
    EditText verga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        ImageButton lapiz=(ImageButton)findViewById(R.id.notas);
        lapiz.setVisibility(View.INVISIBLE);
        if(Global.log.equals("1")){
            lapiz.setVisibility(View.VISIBLE);
        }

        findViewById(R.id.Mesa1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.ivar1 = "1";
                Intent i = new Intent(principal.this, categorias.class);
                // i.putExtra("elcoso", captar);
                startActivity(i);
            }
        });
        findViewById(R.id.Mesa2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.ivar1 = "2";
                Intent i = new Intent(principal.this, categorias.class);
                startActivity(i);
            }
        });
        findViewById(R.id.Mesa3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principal.this.startActivity(new Intent(principal.this, categorias.class));
                Global.ivar1 = "3";
            }
        });
        findViewById(R.id.Mesa4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principal.this.startActivity(new Intent(principal.this, categorias.class));
                Global.ivar1 = "4";
            }
        });
        findViewById(R.id.Mesa5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principal.this.startActivity(new Intent(principal.this, categorias.class));
                Global.ivar1 = "5";
            }
        });

        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principal.this.startActivity(new Intent(principal.this, administrador.class));
            }
        });

        findViewById(R.id.help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principal.this.startActivity(new Intent(principal.this, Acerca.class));
            }
        });
        findViewById(R.id.notas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principal.this.startActivity(new Intent(principal.this, Notas.class));
            }
        });


    }
}