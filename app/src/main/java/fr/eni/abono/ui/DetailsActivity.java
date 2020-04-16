package fr.eni.abono.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import fr.eni.abono.R;
import fr.eni.abono.bo.Category;
import fr.eni.abono.bo.Frequency;
import fr.eni.abono.bo.Priority;
import fr.eni.abono.bo.Subscription;
import fr.eni.abono.dao.AppDatabase;
import fr.eni.abono.dao.Connexion;

public class DetailsActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private Spinner priorityDropDown;
    private Spinner frequencyDropDown;
    private Spinner categoryDropDown;
    private Button buttonAdd;
    private Button buttonUpdate;
    private Button buttonRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        editTextName = findViewById(R.id.editTextName);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextDescription = findViewById(R.id.editTextDescription);
        frequencyDropDown = findViewById(R.id.frequencyDropDown);
        priorityDropDown = findViewById(R.id.priorityDropDown);
        // categoryDropDown = findViewById(R.id.categoryDropDown);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonRemove = findViewById(R.id.buttonRemove);

        ArrayAdapter<CharSequence> frequencyAdapter =  ArrayAdapter.createFromResource(
                this,
                R.array.frequency_array,
                android.R.layout.simple_spinner_dropdown_item
        );
        frequencyDropDown.setAdapter(frequencyAdapter);

        ArrayAdapter<CharSequence> priorityAdapter =  ArrayAdapter.createFromResource(
                this,
                R.array.priority_array,
                android.R.layout.simple_spinner_dropdown_item
        );
        priorityDropDown.setAdapter(priorityAdapter);

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = Connexion.getConnexion(DetailsActivity.this);
                List<Category> list = new ArrayList<>();
                list = db.categoryDao().findAll();
                String[] temp = new String[list.size()];
                for (int i = 0; i<list.size(); i++) {
                    temp[i] = list.get(i).getName();
                }
                ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, temp);
                priorityDropDown.setAdapter(categoryAdapter);
            }
        }).start();*/

        // test vérification extras
        if(getIntent().getExtras() != null) {


            Subscription item = (Subscription) getIntent().getExtras().get("object");

            editTextName.setText(item.getName());
            editTextPrice.setText(String.valueOf(item.getPrice()));
            editTextDescription.setText(item.getDescription());

            switch (item.getFrequency()) {
                case DAILY:
                    frequencyDropDown.setSelection(0);
                    break;
                case WEEKLY:
                    frequencyDropDown.setSelection(1);
                    break;
                case MONTHLY:
                    frequencyDropDown.setSelection(2);
                    break;
                case QUARTERLY:
                    frequencyDropDown.setSelection(3);
                    break;
                case SEMESTERLY:
                    frequencyDropDown.setSelection(4);
                    break;
                case ANNUALLY:
                    frequencyDropDown.setSelection(5);
            }

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
            buttonAdd.setVisibility(View.GONE);
        } else {
            buttonRemove.setVisibility(View.GONE);
            buttonUpdate.setVisibility(View.GONE);
        }
    }

    public void validSubscription(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                float price = Float.parseFloat(editTextPrice.getText().toString());
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
                Priority priority = Priority.OPTIONAL;
                switch (priorityDropDown.getSelectedItemPosition()) {
                    case 0:
                        priority = Priority.INDISPENSABLE;
                        break;
                    case 1:
                        priority = Priority.IMPORTANT;
                        break;
                    case 2:
                        priority = Priority.OPTIONAL;
                }
                AppDatabase db = Connexion.getConnexion(DetailsActivity.this);
                db.subscriptionDao().insert(new Subscription(price, frequency, name, description, priority, 0));
            }
        }).start();

        Log.d("validSubscription", "Subscription added in database");

        Intent intentAddSubscription = new Intent(DetailsActivity.this, MainActivity.class);
    }

    public void updateSubscription(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Subscription item = (Subscription) getIntent().getExtras().get("object");
                item.setPrice(Float.parseFloat(editTextPrice.getText().toString()));
                switch (frequencyDropDown.getSelectedItemPosition()) {
                    case 0:
                        item.setFrequency(Frequency.DAILY);
                        break;
                    case 1:
                        item.setFrequency(Frequency.WEEKLY);
                        break;
                    case 2:
                        item.setFrequency(Frequency.MONTHLY);
                        break;
                    case 3:
                        item.setFrequency(Frequency.QUARTERLY);
                        break;
                    case 4:
                        item.setFrequency(Frequency.SEMESTERLY);
                        break;
                    case 5:
                        item.setFrequency(Frequency.ANNUALLY);
                }
                switch (priorityDropDown.getSelectedItemPosition()) {
                    case 0:
                        item.setPriority(Priority.INDISPENSABLE);
                        break;
                    case 1:
                        item.setPriority(Priority.IMPORTANT);
                        break;
                    case 2:
                        item.setPriority(Priority.OPTIONAL);
                }
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

        new AlertDialog.Builder(DetailsActivity.this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
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
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
