package co.ocha.tugasprogmob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import co.ocha.tugasprogmob.Category.CategoryAdapter;
import co.ocha.tugasprogmob.Category.CategoryModel;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    private RecyclerView catRecycle;
    private ArrayList<CategoryModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catRecycle = findViewById(R.id.catRecycle);
        catRecycle.setHasFixedSize(true);

        list.addAll(getListCategory());
        showRecycle();
    }

    public ArrayList<CategoryModel> getListCategory(){
        String[] categoryName = getResources().getStringArray(R.array.name_category);
        TypedArray categoryImage = getResources().obtainTypedArray(R.array.img_category);

        ArrayList<CategoryModel> list = new ArrayList<>();
        for (int i=0; i < categoryName.length;i++){
            CategoryModel category = new CategoryModel();
            category.setCategoryName(categoryName[i]);
            category.setCategoryImage(categoryImage.getResourceId(i,-1));

            list.add(category);
        }
        return list;
    }

    private void showRecycle(){
        catRecycle.setLayoutManager(new LinearLayoutManager(this));
        CategoryAdapter adapter = new CategoryAdapter(this,list);
        catRecycle.setAdapter(adapter);

        adapter.setOnItemClickCallback(new CategoryAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(CategoryModel category, int position) {
//                Toast.makeText(getApplicationContext(),"halo "+position, LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,FoodActivity.class);
                intent.putExtra("NAME",category.getCategoryName());
                intent.putExtra("POSITION",position);
                startActivity(intent);
            }
        });
    }
}
