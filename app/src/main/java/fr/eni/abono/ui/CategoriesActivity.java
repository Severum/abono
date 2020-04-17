package fr.eni.abono.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    private ListView listViewData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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
        Intent intentAddCategory = new Intent(CategoriesActivity.this, AddCategoryActivity.class);

        startActivity(intentAddCategory);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(CategoriesActivity.this, MainActivity.class));
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
