package fr.eni.abono.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;
import java.util.ArrayList;

import fr.eni.abono.R;
import fr.eni.abono.bo.Offer;

public class CheckOffers extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this, "Service Start", Toast.LENGTH_LONG).show();
        // API call
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://data.rennesmetropole.fr/api/records/1.0/search/?dataset=titres-et-tarifs-du-reseau-star&sort=libelleproduit&facet=libelleexploitant&facet=libellefamille&facet=libelletypevalidite&facet=estenvente&facet=tauxtva&facet=visibilite",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CheckOffers.this, "succes", Toast.LENGTH_LONG).show();
                        Log.e("","res: "+response);
                        Gson gson = new Gson();
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            JSONArray temp = jsonObject.getJSONArray("records");
                            ArrayList<Offer> test = new ArrayList<Offer>();
                            for (int i = 0; i<temp.length(); i++) {
                                test.add(gson.fromJson(temp.getJSONObject(i).toString(), Offer.class));
                                Log.e("","res: "+test.get(i).toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CheckOffers.this, "Error", Toast.LENGTH_LONG).show();
            }
        });

        queue.add(stringRequest);

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
