package fr.eni.abono.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import fr.eni.abono.R;
import fr.eni.abono.bo.Subscription;
import fr.eni.abono.dao.AppDatabase;
import fr.eni.abono.dao.Connexion;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTotMonth;
    private TextView textViewTotYear;
    private ListView listViewData;

    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTotMonth = findViewById(R.id.textViewTotMonth);
        textViewTotYear = findViewById(R.id.textViewTotYear);
        listViewData = findViewById(R.id.listViewData);

        Intent serviceIntent = new Intent(MainActivity.this, CheckOffers.class);

        startService(serviceIntent);

        final List<Subscription> subscriptions = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = Connexion.getConnexion(MainActivity.this);
                List<Subscription> list = new ArrayList<>();
                if(getIntent().getExtras() == null) {
                    list = db.subscriptionDao().findAll();
                } else {
                    list = db.subscriptionDao().findByCategoryId(getIntent().getExtras().getInt("categoryId"));
                }
                subscriptions.addAll(list);
                db.close();

                float yearTot = 0.f;
                for (Subscription s : subscriptions) {
                    yearTot += s.getPrice() * (1/s.getFrequency().getValue());
                }

                textViewTotMonth.setText("" + df.format(yearTot / 12) + "€");
                textViewTotYear.setText("" + df.format(yearTot) + "€");

            }
        }).start();

        listViewData.setAdapter(new SubscriptionAdapter(
                MainActivity.this,
                R.layout.item_subscription,
                subscriptions
        ));

        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Subscription item = subscriptions.get(position);

                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);

                intent.putExtra("object", item);

                startActivity(intent);
            }
        });
    }

    public void addSubscription(View view) {
        Intent intentAddSubscription = new Intent(MainActivity.this, DetailsActivity.class);

        startActivity(intentAddSubscription);
    }

    public void categoryView(View view) {
        Intent intentCategory = new Intent(MainActivity.this, CategoriesActivity.class);

        startActivity(intentCategory);
    }
}
