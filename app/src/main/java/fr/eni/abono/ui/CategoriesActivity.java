package fr.eni.abono.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.eni.abono.R;
import fr.eni.abono.bo.Category;
import fr.eni.abono.dao.AppDatabase;
import fr.eni.abono.dao.Connexion;

public class CategoriesActivity extends AppCompatActivity {

    private EditText editTextCategoryName;
    private EditText editTextCategoryDescription;
    private ListView listViewData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_categories);

        editTextCategoryName = findViewById(R.id.editTextCategoryName);
        editTextCategoryDescription = findViewById(R.id.editTextCategoryDescription);

        listViewData = findViewById(R.id.listViewData);
        final List<Category> categories = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = Connexion.getConnexion(CategoriesActivity.this);
                List<Category> list = new ArrayList<>();
                list = db.categoryDao().findAll();
                categories.addAll(list);
            }
        }).start();

        listViewData.setAdapter(new CategoryAdapter(
                CategoriesActivity.this,
                R.layout.item_category,
                categories
        ));

        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Category item = categories.get(position);

                Intent intent = new Intent(CategoriesActivity.this, MainActivity.class);

                intent.putExtra("categoryId", item.getId());

                startActivity(intent);
            }
        });
    }

    public void addCategory(View view) {
        Intent intentAddCategory = new Intent(CategoriesActivity.this, DetailsActivity.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = String.valueOf(editTextCategoryName.getText());
                String description = String.valueOf(editTextCategoryDescription.getText());
                AppDatabase db = Connexion.getConnexion(CategoriesActivity.this);
                db.categoryDao().insert(new Category(name, description));
            }
        }).start();

        Log.d("validSubscription", "Subscription added in database");

        Intent intentAddSubscription = new Intent(CategoriesActivity.this, MainActivity.class);

        startActivity(intentAddCategory);
    }
}
