package fr.eni.abono.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eni.abono.R;
<<<<<<< HEAD
import fr.eni.abono.bo.Priority;
import fr.eni.abono.bo.Subscription;
=======
import fr.eni.abono.models.Priority;
import fr.eni.abono.models.Subscription;
>>>>>>> database

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
<<<<<<< HEAD
                (float)1/(float)12,
=======
                1/12,
>>>>>>> database
                "eau",
                "Glou glou",
                Priority.INDISPENSABLE)
        );
        testData.add(new Subscription(
                30,
<<<<<<< HEAD
                (float)1/(float)12,
=======
                1/12,
>>>>>>> database
                "FAI",
                "acces internet",
                Priority.IMPORTANT)
        );
        testData.add(new Subscription(
                14,
<<<<<<< HEAD
                (float)1/(float)12,
=======
                1/12,
>>>>>>> database
                "Netflix",
                "acces internet",
                Priority.OPTIONAL)
        );

<<<<<<< HEAD
        float yearTot = 0;
        double temp = 0;
        for (int i = 0; i<testData.size(); i++) {
            temp = testData.get(i).getFrequency();
            yearTot += testData.get(i).getPrice() * (1/testData.get(i).getFrequency());
        }

        textViewTotMonth.setText(String.valueOf(yearTot / 12));
        textViewTotYear.setText(String.valueOf(yearTot));
=======
        int yearTot = 0;
        for (int i = 0; i<testData.size(); i++) {
            yearTot += testData.get(i).getPrice() * (1/testData.get(i).getFrequency());
        }

        // textViewTotMonth.setText();
        textViewTotYear.setText(String.valueOf(yearTot));
        Log.d("TOT", String.valueOf(yearTot));
>>>>>>> database

        listViewData.setAdapter(new SubscriptionAdapter(
                MainActivity.this,
                R.layout.item_subscription,
                testData
        ));
    }
}
