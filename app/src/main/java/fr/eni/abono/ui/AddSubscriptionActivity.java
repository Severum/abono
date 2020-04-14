package fr.eni.abono.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import fr.eni.abono.R;
import fr.eni.abono.bo.Priority;

public class AddSubscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.priorityDropDown);
        //create a list of items for the spinner.
        String[] items = new String[]{Priority.INDISPENSABLE.toString(), Priority.IMPORTANT.toString(), Priority.OPTIONAL.toString()};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }

    public void validSubscription(View view) {

    }
}
