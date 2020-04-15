package fr.eni.abono.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import fr.eni.abono.R;
import fr.eni.abono.bo.Frequency;
import fr.eni.abono.bo.Priority;
import fr.eni.abono.bo.Subscription;
import fr.eni.abono.dao.AppDatabase;
import fr.eni.abono.dao.Connexion;

public class AddSubscriptionActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextDescription;
    private EditText editTextPrice;
    private Spinner frequencyDropDown;
    private Spinner dropdownPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);

        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPrice = findViewById(R.id.editTextPrice);
        frequencyDropDown = findViewById(R.id.frequencyDropDown);
        ArrayAdapter<CharSequence> frequencyAdapter =  ArrayAdapter.createFromResource(
                this,
                R.array.frequency_array,
                android.R.layout.simple_spinner_dropdown_item
        );
        frequencyDropDown.setAdapter(frequencyAdapter);

        dropdownPriority = findViewById(R.id.priorityDropDown);
        ArrayAdapter<CharSequence> priorityAdapter =  ArrayAdapter.createFromResource(
                this,
                R.array.priority_array,
                android.R.layout.simple_spinner_dropdown_item
        );
        dropdownPriority.setAdapter(priorityAdapter);
    }

    public void validSubscription(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                float price = Float.parseFloat(editTextPrice.getText().toString());
                // float frequency = Float.parseFloat(editTextFrequency.getText().toString());
                String name = String.valueOf(editTextName.getText());
                String description = String.valueOf(editTextDescription.getText());
                Frequency frequency = null;
                switch (frequencyDropDown.getSelectedItemPosition()) {
                    case 0:
                        frequency = Frequency.DAILY;
                        break;
                    case 1:
                        frequency = Frequency.WEEKLY;
                        break;
                    case 2:
                        frequency = Frequency.MONTHLY;
                        break;
                    case 3:
                        frequency = Frequency.QUARTERLY;
                        break;
                    case 4:
                        frequency = Frequency.SEMESTERLY;
                        break;
                    case 5:
                        frequency = Frequency.ANNUALLY;
                        break;
                }
                Subscription subscription;
                Priority priority = Priority.OPTIONAL;
                switch (dropdownPriority.getSelectedItemPosition()) {
                    case 0:
                        priority = Priority.INDISPENSABLE;
                        break;
                    case 1:
                        priority = Priority.IMPORTANT;
                        break;
                    case 2:
                        priority = Priority.OPTIONAL;
                }
                subscription = new Subscription(price, frequency, name, description, priority);
                AppDatabase db = Connexion.getConnexion(AddSubscriptionActivity.this);
                db.subscriptionDao().insert(subscription);
            }
        }).start();

        Log.d("validSubscription", "Subscription added in database");

        Intent intentAddSubscription = new Intent(AddSubscriptionActivity.this, MainActivity.class);
        startActivity(intentAddSubscription);
    }
}
