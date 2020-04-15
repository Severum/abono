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
    private EditText editTextFrequency;
    private Spinner dropdownPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);

        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextFrequency = findViewById(R.id.editTextFrequency);

        dropdownPriority = findViewById(R.id.priorityDropDown);
        String[] items = new String[]{
                Priority.INDISPENSABLE.toString(),
                Priority.IMPORTANT.toString(),
                Priority.OPTIONAL.toString()
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdownPriority.setAdapter(adapter);

    }

    public void validSubscription(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                float price = Float.parseFloat(editTextPrice.getText().toString());
                float frequency = Float.parseFloat(editTextFrequency.getText().toString());
                String name = String.valueOf(editTextName.getText());
                String description = String.valueOf(editTextDescription.getText());
                Subscription subscription = new Subscription(price, frequency, name, description, Priority.IMPORTANT);
                AppDatabase db = Connexion.getConnexion(AddSubscriptionActivity.this);
                db.subscriptionDao().insert(subscription);
            }
        }).start();

        Log.d("validSubscription", "Subscription added in database");

        Intent intentAddSubscription = new Intent(AddSubscriptionActivity.this, MainActivity.class);
        startActivity(intentAddSubscription);
    }
}
