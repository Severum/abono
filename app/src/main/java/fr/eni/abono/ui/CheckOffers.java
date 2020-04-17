package fr.eni.abono.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import fr.eni.abono.R;

public class CheckOffers extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Start", Toast.LENGTH_LONG).show();
        // API call

        // notification
        NotificationCompat.Builder builder;

        builder = new NotificationCompat.Builder(this, "");
        builder.setAutoCancel(true);

        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setTicker("Notification Here");
        builder.setPriority(Notification.PRIORITY_DEFAULT);
        builder.setWhen(System.currentTimeMillis());
        builder.setContentTitle("Hello World");
        builder.setContentText("Hello everyOne !!!!");

        Intent intent2 = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
