package fr.eni.abono.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import fr.eni.abono.R;
import fr.eni.abono.bo.Category;
import fr.eni.abono.bo.Subscription;
import fr.eni.abono.dao.AppDatabase;
import fr.eni.abono.dao.Connexion;

public class AddCategoryActivity extends AppCompatActivity {

    private EditText editTextCategoryName;
    private EditText editTextCategoryDescription;

    private Button buttonAdd;
    private Button buttonUpdate;
    private Button buttonRemove;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_category_activity);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        editTextCategoryName = findViewById(R.id.editTextCategoryName);
        editTextCategoryDescription = findViewById(R.id.editTextCategoryDescription);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonRemove = findViewById(R.id.buttonRemove);

        // test vérification extras
        if(getIntent().getExtras() != null) {

            Category item = (Category) getIntent().getExtras().get("category");

            editTextCategoryName.setText(item.getName());
            editTextCategoryDescription.setText(item.getDescription());

            buttonAdd.setVisibility(View.GONE);
        } else {
            buttonRemove.setVisibility(View.GONE);
            buttonUpdate.setVisibility(View.GONE);
        }
    }

    public void validCategory(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = String.valueOf(editTextCategoryName.getText());
                String description = String.valueOf(editTextCategoryDescription.getText());
                AppDatabase db = Connexion.getConnexion(AddCategoryActivity.this);
                db.categoryDao().insert(new Category(name, description));
            }
        }).start();

        Log.d("validCategory", "Subscription added in database");

        Intent intentCategory = new Intent(AddCategoryActivity.this, CategoriesActivity.class);

        startActivity(intentCategory);
    }

    public void updateCategory(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Category item = (Category) getIntent().getExtras().get("object");
                item.setName(String.valueOf(editTextCategoryName.getText()));
                item.setDescription(String.valueOf(editTextCategoryDescription.getText()));
                AppDatabase db = Connexion.getConnexion(AddCategoryActivity.this);
                db.categoryDao().update(item);
            }
        }).start();

        Log.d("validSubscription", "Subscription added in database");

        Intent intentAddSubscription = new Intent(AddCategoryActivity.this, CategoriesActivity.class);
        startActivity(intentAddSubscription);
    }

    public void removeSubscription(View view) {
        final Category item = (Category) getIntent().getExtras().get("object");

        new AlertDialog.Builder(AddCategoryActivity.this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                AppDatabase db = Connexion.getConnexion(AddCategoryActivity.this);
                                db.categoryDao().delete(item);
                            }
                        }).start();

                        Log.d("removeCategory", "Category deleted from database");

                        Intent intentAddSubscription = new Intent(AddCategoryActivity.this, MainActivity.class);
                        startActivity(intentAddSubscription);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
