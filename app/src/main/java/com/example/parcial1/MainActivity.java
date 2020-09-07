package com.example.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    private EditText tv1;
    private TextView et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        tv1 = (EditText)findViewById(R.id.txtdata);
        et1 = (TextView) findViewById(R.id.lblresult);

        //Spinner1
        String [] opciones = {"Metro cuadrado","Pie Cuadrado","Vara Cuadrada","Yarda Cuadrada","Tareas","Manzana","Hectárea"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);

        //Spinner2
        String [] opciones2 = {"Metro cuadrado","Pie Cuadrado","Vara Cuadrada","Yarda Cuadrada","Tareas","Manzana","Hectárea"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones2);
        spinner2.setAdapter(adapter2);
    }

    //Cambio de activity
    public void siguiente (View view){
        Intent siguiente2 = new Intent(this, AreaActivity.class);
        startActivity(siguiente2);
    }

    //Conversor Area
    public void Calcular(View view) {
        String valor = tv1.getText().toString();

        int num = Integer.parseInt(valor);

        String seleccion = spinner1.getSelectedItem().toString();
        String seleccion2 = spinner2.getSelectedItem().toString();

        //metro cuadrado
        if (seleccion.equals("Metro cuadrado") & seleccion2.equals("Metro cuadrado")) {
            double mult = num * 1;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        } else if (seleccion.equals("Metro cuadrado") & seleccion2.equals("Pie Cuadrado")) {
            double mult = num * 10.76;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Metro cuadrado") & seleccion2.equals("Vara Cuadrada")) {
            double mult = num * 1.43;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Metro cuadrado") & seleccion2.equals("Yarda Cuadrada")) {
            double mult = num * 1.2;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Metro cuadrado") | seleccion2.equals("Tareas") | seleccion2.equals("Manzana") | seleccion2.equals("Hectárea")) {
            double mult = num * 0;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }

        //Pie Cuadrado
        if (seleccion.equals("Pie Cuadrado") & seleccion2.equals("Metro cuadrado")) {
            double mult = num * 0.09;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        } else if (seleccion.equals("Pie cuadrado") & seleccion2.equals("Pie Cuadrado")) {
            double mult = num * 1;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Pie cuadrado") & seleccion2.equals("Vara Cuadrada")) {
            double mult = num * 0.13;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Pie cuadrado") & seleccion2.equals("Yarda Cuadrada")) {
            double mult = num * 0.11;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Pie cuadrado") | seleccion2.equals("Tareas") | seleccion2.equals("Manzana") | seleccion2.equals("Hectárea")) {
            double mult = num * 0;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }

        //Vara Cuadrada
        if (seleccion.equals("Vara Cuadrada") & seleccion2.equals("Metro cuadrado")) {
            double mult = num * 0.7;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        } else if (seleccion.equals("Vara Cuadrada") & seleccion2.equals("Pie Cuadrado")) {
            double mult = num * 7.53;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Vara Cuadrada") & seleccion2.equals("Vara Cuadrada")) {
            double mult = num * 1;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Vara Cuadrada") & seleccion2.equals("Yarda Cuadrada")) {
            double mult = num * 0.84;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Vara Cuadrada") | seleccion2.equals("Tareas") | seleccion2.equals("Manzana") | seleccion2.equals("Hectárea")) {
            double mult = num * 0;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }

        //Yarda
        if (seleccion.equals("Yarda Cuadrada") & seleccion2.equals("Metro cuadrado")) {
            double mult = num * 0.84;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        } else if (seleccion.equals("Yarda Cuadrada") & seleccion2.equals("Pie Cuadrado")) {
            double mult = num * 9;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Yarda Cuadrada") & seleccion2.equals("Vara Cuadrada")) {
            double mult = num * 1.19;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Yarda Cuadrada") & seleccion2.equals("Yarda Cuadrada")) {
            double mult = num * 1;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Yarda Cuadrada") | seleccion2.equals("Tareas") | seleccion2.equals("Manzana") | seleccion2.equals("Hectárea")) {
            double mult = num * 0;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }


        //Tarea
        if (seleccion.equals("Tarea") & seleccion2.equals("Tarea")) {
            double mult = num * 1;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        } else if (seleccion.equals("Tarea") & seleccion2.equals("Manzana")) {
            double mult = num * 0.06;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Tarea") & seleccion2.equals("Hectárea")) {
            double mult = num * 0.04;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Tarea") & seleccion2.equals("Metro cuadrado")) {
            double mult = num * 437;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Tarea") & seleccion2.equals("Pie Cuadrado")) {
            double mult = num * 4703.82;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Tarea") & seleccion2.equals("Vara Cuadrada")) {
            double mult = num * 624.29;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Tarea") & seleccion2.equals("Yarda Cuadrada")) {
            double mult = num * 522.65;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }

        //Manzana
        if (seleccion.equals("Manzana") & seleccion2.equals("Tarea")) {
            double mult = num * 16.02;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        } else if (seleccion.equals("Manzana") & seleccion2.equals("Manzana")) {
            double mult = num * 1;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Manzana") & seleccion2.equals("Hectárea")) {
            double mult = num * 0.7;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Manzana") & seleccion2.equals("Metro cuadrado")) {
            double mult = num * 7000;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Manzana") & seleccion2.equals("Pie Cuadrado")) {
            double mult = num * 75347.32;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Manzana") & seleccion2.equals("Vara Cuadrada")) {
            double mult = num * 10000;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Manzana") & seleccion2.equals("Yarda Cuadrada")) {
            double mult = num * 8371.93;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }

        //Hectárea
        if (seleccion.equals("Hectárea") & seleccion2.equals("Tarea")) {
            double mult = num * 22.88;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        } else if (seleccion.equals("Hectárea") & seleccion2.equals("Manzana")) {
            double mult = num * 1.43;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Hectárea") & seleccion2.equals("Hectárea")) {
            double mult = num * 1;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Hectárea") & seleccion2.equals("Metro cuadrado")) {
            double mult = num * 10000;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Hectárea") & seleccion2.equals("Pie Cuadrado")) {
            double mult = num * 107639;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Hectárea") & seleccion2.equals("Vara Cuadrada")) {
            double mult = num * 14285.71;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }else if (seleccion.equals("Hectárea") & seleccion2.equals("Yarda Cuadrada")) {
            double mult = num * 11959.9;
            String resultado = String.valueOf(mult);
            et1.setText(resultado);
        }

    }
}