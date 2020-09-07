package com.example.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AreaActivity extends AppCompatActivity {

    private EditText tunid, tcant, tres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        tunid = (EditText) findViewById(R.id.txtunid);
        tcant = (EditText) findViewById(R.id.txtcant);
        tres = (EditText) findViewById(R.id.txtresultado);
    }

    //Cambio de activity
    public void siguiente2 (View view){
        Intent siguiente2 = new Intent(this, MainActivity.class);
        startActivity(siguiente2);
    }

    //conversor universal
    public void universal (View view){
        try {
            int contres = 0;
            int result = 0;
            int finall = 0;

            int contcant = Integer.parseInt(tcant.getText().toString());

            if (!tunid.getText().toString().isEmpty() && !tcant.getText().toString().isEmpty()){

                int contuni = Integer.parseInt(tunid.getText().toString());

                for (int i = finall; i <= contuni; i++){
                    if (contuni >= contcant){
                        result = contuni - contcant;
                        contres += 1;
                        contuni = result;
                    }
                }
                tres.setText(contres + "/" + result);

            }else if (tres.getText().toString().contains("/")) {
                String[] separated = tres.getText().toString().split("/");

                int posicion0 = Integer.parseInt(separated[0]);
                int posicion1 = Integer.parseInt(separated[1]);

                int Resultado = contcant * posicion0 + posicion1;

                tunid.setText(String.valueOf(Resultado));

            }
        }catch(Exception error){
            //TextView textView = (TextView)findViewById(R.id.txtresultado);
            //textView.setText("Faltan datos");
        }
    }
}