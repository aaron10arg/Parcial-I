package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class administrador extends AppCompatActivity {
    EditText user, pass;
    ImageButton lapiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        lapiz=(ImageButton)findViewById(R.id.notas);



        findViewById(R.id.imageButton5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                administrador.this.startActivity(new Intent(administrador.this, principal.class));    }
        });
        findViewById(R.id.imageButton20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                administrador.this.startActivity(new Intent(administrador.this, RegistroSms.class));      }
        });
    }

    public void login(View v) {
        AdminSQLiteOpenHelper lg = new AdminSQLiteOpenHelper(this,"usuario",null,1);
        SQLiteDatabase db = lg.getWritableDatabase();
        //devuelve 0 o 1 fila //es una consulta
        String lu = user.getText().toString();
        String lp = pass.getText().toString();
        Cursor fila = db.rawQuery("select username, password from usuario where password= "+lp, null);
        if (fila.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)

            Toast.makeText(this, "Login Correcto",
                    Toast.LENGTH_SHORT).show();
            Global.log="1";

            Intent i =new Intent(administrador.this,principal.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Login Fallido", Toast.LENGTH_SHORT).show();
            Global.log="0";
        }
        db.close();

    }
}
