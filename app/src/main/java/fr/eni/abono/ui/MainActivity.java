package fr.eni.abono.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eni.abono.R;
import fr.eni.abono.bo.Priority;
import fr.eni.abono.bo.Subscription;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTotMonth;
    private TextView textViewTotYear;
    private ListView listViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTotMonth = findViewById(R.id.textViewTotMonth);
        textViewTotYear = findViewById(R.id.textViewTotYear);
        listViewData = findViewById(R.id.listViewData);

        final List<Subscription> testData = new ArrayList<>();

        testData.add(new Subscription(
                20,
                (float)1/(float)12,
                "eau",
                "Glou glou",
                Priority.INDISPENSABLE)
        );
        testData.add(new Subscription(
                30,
                (float)1/(float)12,
                "FAI",
                "acces internet",
                Priority.IMPORTANT)
        );
        testData.add(new Subscription(
                14,
                (float)1/(float)12,
                "Netflix",
                "acces internet",
                Priority.OPTIONAL)
        );

        float yearTot = 0;
        double temp = 0;
        for (int i = 0; i<testData.size(); i++) {
            temp = testData.get(i).getFrequency();
            yearTot += testData.get(i).getPrice() * (1/testData.get(i).getFrequency());
        }

        textViewTotMonth.setText(String.valueOf(yearTot / 12));
        textViewTotYear.setText(String.valueOf(yearTot));

        listViewData.setAdapter(new SubscriptionAdapter(
                MainActivity.this,
                R.layout.item_subscription,
                testData
        ));
    }

    public void addSubscription(View view) {
        Intent intentAddSubscription = new Intent(MainActivity.this, AddSubscriptionActivity.class);

        startActivity(intentAddSubscription);
    }
}
