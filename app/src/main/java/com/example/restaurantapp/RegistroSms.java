package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class RegistroSms extends Activity {
    Integer code=0;
    Integer code2=0;
    Integer code3=0;
    EditText user, pass,num, cod;
    TextView txtc;
    int cont=0;

    ImageButton btni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_sms);


        user = (EditText) findViewById(R.id.Usreg);
        pass = (EditText) findViewById(R.id.Pwreg);
        cod = (EditText) findViewById(R.id.codigo);
        txtc = (TextView) findViewById(R.id.txtC);
        btni = (ImageButton) findViewById(R.id.save);


        cod.setVisibility(View.INVISIBLE);
        txtc.setVisibility(View.INVISIBLE);
        btni.setVisibility(View.INVISIBLE);
    }



    public void enviar(View v){
//int code=0;
        cont=cont+1;
        String numero ="0990715764" ;
        String codigo;

        final Random rand = new Random();
        code= rand.nextInt(8888);

        codigo=("El codigo es: "+code);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(numero, null, codigo, null, null);



        cod.setVisibility(View.VISIBLE);
        txtc.setVisibility(View.VISIBLE);
        btni.setVisibility(View.VISIBLE);


    }



    public void Registrar(View v) {

        try {
            AdminSQLiteOpenHelper guarda = new AdminSQLiteOpenHelper(this, "usuario", null, 1);

            SQLiteDatabase base = guarda.getWritableDatabase();

            String iid = user.getText().toString();
            String ides = pass.getText().toString();
            String codigo = cod.getText().toString();
            code2 = Integer.parseInt(codigo);

            ContentValues grabar_oficina = new ContentValues();
            grabar_oficina.put("username", iid);
            grabar_oficina.put("password", ides);

            base.insert("usuario", null, grabar_oficina);
            base.close();
            Toast.makeText(this, "Registrado", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(RegistroSms.this, administrador.class);
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(this, "Usuario existente", Toast.LENGTH_SHORT).show();
        }
    }




}