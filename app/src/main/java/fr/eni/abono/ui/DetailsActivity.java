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

public class DetailsActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextPrice;
    private EditText editTextFrequency;
    private EditText editTextDescription;
    private Spinner priorityDropDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        editTextName = findViewById(R.id.editTextName);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextFrequency = findViewById(R.id.editTextFrequency);
        editTextDescription = findViewById(R.id.editTextDescription);
        priorityDropDown = findViewById(R.id.priorityDropDown);

        //create a list of items for the spinner.
        String[] items = new String[]{Priority.INDISPENSABLE.toString(), Priority.IMPORTANT.toString(), Priority.OPTIONAL.toString()};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        priorityDropDown.setAdapter(adapter);

        // test vérification extras
        if(getIntent().getExtras() != null) {
            Subscription item = (Subscription) getIntent().getExtras().get("object");

            editTextName.setText(item.getName());
            editTextPrice.setText(String.valueOf(item.getPrice()));
            editTextFrequency.setText(String.valueOf(item.getFrequency()));
            editTextDescription.setText(item.getDescription());
            switch (item.getPriority()) {
                case INDISPENSABLE:
                    priorityDropDown.setSelection(0);
                    break;
                case IMPORTANT:
                    priorityDropDown.setSelection(1);
                    break;
                case OPTIONAL:
                    priorityDropDown.setSelection(2);
                    break;
                default:
                    break;
            }
        }
    }

    public void validSubscription(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Subscription item = (Subscription) getIntent().getExtras().get("object");
                item.setPrice(Float.parseFloat(editTextPrice.getText().toString()));
                item.setFrequency(Float.parseFloat(editTextFrequency.getText().toString()));
                item.setName(String.valueOf(editTextName.getText()));
                item.setDescription(String.valueOf(editTextDescription.getText()));
                AppDatabase db = Connexion.getConnexion(DetailsActivity.this);
                db.subscriptionDao().update(item);
            }
        }).start();

        Log.d("validSubscription", "Subscription added in database");

        Intent intentAddSubscription = new Intent(DetailsActivity.this, MainActivity.class);
        startActivity(intentAddSubscription);
    }

    public void removeSubscription(View view) {
        final Subscription item = (Subscription) getIntent().getExtras().get("object");

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = Connexion.getConnexion(DetailsActivity.this);
                db.subscriptionDao().delete(item);
            }
        }).start();

        Log.d("removeSubscription", "Subscription deleted from database");

        Intent intentAddSubscription = new Intent(DetailsActivity.this, MainActivity.class);
        startActivity(intentAddSubscription);
    }
}
