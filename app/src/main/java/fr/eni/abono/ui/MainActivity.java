package fr.eni.abono.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eni.abono.R;
import fr.eni.abono.models.Priority;
import fr.eni.abono.models.Subscription;

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
                1/12,
                "eau",
                "Glou glou",
                Priority.INDISPENSABLE)
        );
        testData.add(new Subscription(
                30,
                1/12,
                "FAI",
                "acces internet",
                Priority.IMPORTANT)
        );
        testData.add(new Subscription(
                300,
                1/12,
                "Netflix",
                "acces internet",
                Priority.OPTIONAL)
        );

        listViewData.setAdapter(new SubscriptionAdapter(
                MainActivity.this,
                R.layout.item_subscription,
                testData
        ));
    }
}