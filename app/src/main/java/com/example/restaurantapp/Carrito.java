package com.example.restaurantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


public class Carrito extends Activity {

    ListView listado;
    ImageButton clear;
    EditText dato;
    Integer[] arreglo3;
    String [] arreglo, arreglo4;
    TextView desc, pre;
    Button b;
    int cc;
    String v = "1";


    private Button btnPrueba;

    private Socket socket;
    private static final int SERVERPORT = 6000;
    private static final String SERVER_IP = "192.168.0.101";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrilto);

        btnPrueba = (Button) findViewById(R.id.aaaa);

        btnPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               osdo();
            }
        });

        FirebaseMessaging.getInstance().subscribeToTopic("enviarNotificacion").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) { }
        });


        clear = (ImageButton) findViewById(R.id.Clear);
        desc = (TextView) findViewById(R.id.item);
        pre = (TextView) findViewById(R.id.itempre);
        //String val=dato.getText().toString();
        Bundle p = getIntent().getExtras();
        String item_name[] = {p.getString("itemn")};
        String item_desc[] = {p.getString("itemd")};
        String item_precio[] = {p.getString("itemp")};
        Integer img_id[] = {p.getInt("itemi")};
        Listar();
        new Thread(new ClientThread()).start();
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Carrito.this)
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
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long id) {
                Toast toast = Toast.makeText(Carrito.this, "Coordenada: " + posicion, Toast.LENGTH_LONG);
                toast.show();
            }
        });
        findViewById(R.id.imageButton21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Carrito.this.startActivity(new Intent(Carrito.this, categorias.class));
            }
        });
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void Listar() {
        AdminSQLiteOpenHelper lista = new AdminSQLiteOpenHelper(this, "item", null, 1);
        SQLiteDatabase base = lista.getReadableDatabase();
        if (base != null) {
            Cursor c = base.rawQuery("select * from item where pedido==" + Global.ivar1, null);
            int cantidad = c.getCount();
            int i = 0;
            arreglo = new String[cantidad];
            String[] arreglo2 = new String[cantidad];
            arreglo3 = new Integer[cantidad];
            arreglo4 = new String[cantidad];
            if (c.moveToFirst()) {
                do {
                    int codigo = c.getInt(0);
                    String nombre = c.getString(1);
                    String precio = c.getString(2);
                    String pedido = c.getString(3);
                    arreglo[i] = nombre;
                    arreglo2[i] = precio;
                    arreglo3[i] = codigo;
                    arreglo4[i]= pedido;
                    i++;
                } while (c.moveToNext());
            }
            listado = (ListView) findViewById(R.id.Pedidos);
            ListadoFinal otro = new ListadoFinal(this, arreglo, arreglo2);
            listado.setAdapter(otro);
        }
    }

    public void Borrar() {
        AdminSQLiteOpenHelper total = new AdminSQLiteOpenHelper(this, "item", null, 1);
        SQLiteDatabase base = total.getWritableDatabase();
        int cant = base.delete("item", "pedido=" + Global.ivar1, null); // (oficina es la nombre de la tabla, condición)
        base.close();
        if (cant == 1)
            Toast.makeText(this, "Acaba de borrar todos los platos...", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existen platos!\nAgrege Nuevamente Platos", Toast.LENGTH_SHORT).show();
    }

    public void osdo() {
        toas();
        RequestQueue myrequest = Volley.newRequestQueue(getApplicationContext());
        JSONObject json = new JSONObject();
        try {
            json.put("to", "/topics/"+"enviarNotificacion");
            JSONObject notificacion = new JSONObject();
            notificacion.put("titulo", "Nuevo pedido");
            notificacion.put("pedido", "Orden pendiente");

            json.put("data", notificacion);

            String URL = "https://fcm.googleapis.com/fcm/send";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,URL,json,null,null){
                @Override
                public Map<String, String> getHeaders() {
                    Map<String ,String > header = new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","key=AAAA02Xdczk:APA91bFlAReFi_OR8OUAGWdr5sNHdNjM2PG-o4P4JqvUxvXVVE73kk_oe19RnOhJyCV_hCzIQyNvGB78H0HbfTkydPmIIATLvfLSzNuo1prRGZvX5FMTXWvlLPkPmAf0G1KMXO9TZmTg");
                    return header;
                }
            };

            myrequest.add(request);

        }catch (JSONException e){
            e.printStackTrace();
        }
        try {
            do{
                cc++;
                String str = arreglo[cc-1];
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())),
                        true);
                out.println(str);
                String ped = arreglo4[cc-1];
                PrintWriter a = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())),
                        true);
                a.println(ped);
            }while(cc<50);

        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    class ClientThread implements Runnable {
        @Override
        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVERPORT);
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void toas(){
        Toast.makeText(Carrito.this,"Enviando...", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(Carrito.this,principal.class);
        startActivity(i);
    }
}