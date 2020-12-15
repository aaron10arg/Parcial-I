package com.example.restaurantapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class notificacionPush extends FirebaseMessagingService {

    //valida mensaje
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().size() > 0){
            String titulo = remoteMessage.getData().get("titulo");
            String pedido = remoteMessage.getData().get("pedido");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notifi(titulo, pedido);
            }else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
                notifi(titulo, pedido);
            }
        }
    }

    private void notifi(String titulo, String pedido){
        String id = "mensaje";

        NotificationManager nm = (NotificationManager)getSystemService((Context.NOTIFICATION_SERVICE));
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,id);

        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            NotificationChannel nc = new NotificationChannel(id, "nuevo",NotificationManager.IMPORTANCE_HIGH);
            nc.setShowBadge(true);
            assert nm != null;
            nm.createNotificationChannel(nc);
        }
        builder.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(titulo)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(pedido)
                .setContentIntent(clickNotificacion())
                .setContentInfo("nuevo");
        Random random = new Random();
        int idNotfy = random.nextInt(8000);

        nm.notify(idNotfy,builder.build());
    }

    public PendingIntent clickNotificacion(){
        Intent nf = new Intent(getApplicationContext(), principal.class);
        nf.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(this,0,nf,0);
    }

}
