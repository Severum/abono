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
        String[] frequencyItems = new String[]{
                "weekly",
                "monthly",
                "annually"
        };
        ArrayAdapter<String> frequencyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, frequencyItems);
        frequencyDropDown.setAdapter(frequencyAdapter);

        dropdownPriority = findViewById(R.id.priorityDropDown);
        String[] priorityItems = new String[]{
                Priority.INDISPENSABLE.toString(),
                Priority.IMPORTANT.toString(),
                Priority.OPTIONAL.toString()
        };
        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, priorityItems);
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
                float frequency = 1; // default annually
                switch (frequencyDropDown.getSelectedItem().toString()) {
                    case "weekly":
                        frequency = (float)1/(float)53;
                        break;
                    case "monthly":
                        frequency = (float)1/(float)12;
                        break;
                    case "annually":
                        frequency = (float)1;
                        break;
                    default:
                        break;
                }
                Subscription subscription;
                switch (dropdownPriority.getSelectedItem().toString()) {
                    case "Indispensable":
                        subscription = new Subscription(price, frequency, name, description, Priority.INDISPENSABLE);
                        break;
                    case "Important":
                        subscription = new Subscription(price, frequency, name, description, Priority.IMPORTANT);
                        break;
                    case "Optional":
                        subscription = new Subscription(price, frequency, name, description, Priority.OPTIONAL);
                        break;
                    default:
                        // TODO handle error
                        subscription = null;
                        break;
                }
                AppDatabase db = Connexion.getConnexion(AddSubscriptionActivity.this);
                db.subscriptionDao().insert(subscription);
            }
        }).start();

        Log.d("validSubscription", "Subscription added in database");

        Intent intentAddSubscription = new Intent(AddSubscriptionActivity.this, MainActivity.class);
        startActivity(intentAddSubscription);
    }
}
