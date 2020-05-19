package co.ocha.tugasprogmob;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import co.ocha.tugasprogmob.Restaurant.RestaurantAdapter;
import co.ocha.tugasprogmob.Restaurant.RestaurantModel;

public class CategoryRestaurantActivity extends AppCompatActivity {
    private  String[] resName;
    private TypedArray resImage;
    private  String[] resMap;
    private  String[] resDesc;
    private  String[] resMenu;
    private  String[] resContact;
    private String categoryName;
    private int position;
    private RecyclerView recyclerView;
    private ArrayList<RestaurantModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_restaurant);
        position=getIntent().getIntExtra("POSITION",0);

        recyclerView = findViewById(R.id.resRecycle);
        recyclerView.setHasFixedSize(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        list.addAll(getList(position));
        showRecycleview();
    }

    public ArrayList<RestaurantModel> getList(int position){
        switch (position){
            case 0:
                resName = getResources().getStringArray(R.array.name_res_western);
                resImage = getResources().obtainTypedArray(R.array.img_res_western);
                resMap=getResources().getStringArray(R.array.location_res_western);
                resMenu=getResources().getStringArray(R.array.menu_res_western);
                resDesc=getResources().getStringArray(R.array.desc_res_western);
                resContact=getResources().getStringArray(R.array.contact_res_western);
                break;

            case 1:
                resName = getResources().getStringArray(R.array.name_res_chinnees);
                resImage = getResources().obtainTypedArray(R.array.img_res_chinnees);
                resMap=getResources().getStringArray(R.array.location_res_chinnees);
                resMenu=getResources().getStringArray(R.array.menu_res_chinnees);
                resDesc=getResources().getStringArray(R.array.desc_res_chinnees);
                resContact=getResources().getStringArray(R.array.contact_res_chinnees);
                break;

            case 2:
                resName = getResources().getStringArray(R.array.name_res_traditional);
                resImage = getResources().obtainTypedArray(R.array.img_res_traditional);
                resMap=getResources().getStringArray(R.array.location_res_traditional);
                resMenu=getResources().getStringArray(R.array.menu_res_traditional);
                resDesc=getResources().getStringArray(R.array.desc_res_traditional);
                resContact=getResources().getStringArray(R.array.contact_res_traditional);
                break;
        }
        ArrayList<RestaurantModel> restaurant = new ArrayList<>();
        for (int i=0;i<resName.length;i++){
            RestaurantModel model = new RestaurantModel();
            model.setResName(resName[i]);
            model.setResImage(resImage.getResourceId(i,-1));
            model.setResMap(resMap[i]);
            model.setResMenu(resMenu[i]);
            model.setResDesc(resDesc[i]);
            model.setResDesc(resDesc[i]);
            restaurant.add(model);
        }
        return restaurant;
    }

    private void showRecycleview(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RestaurantAdapter adapter = new RestaurantAdapter(list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickCallback(new RestaurantAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(RestaurantModel model, int position) {
                Intent intent = new Intent(getApplicationContext(),RestaurantActivity.class);
                intent.putExtra(DetailFoodActivity.EXTRA_FOOD,model);
                startActivity(intent);
            }
        });
    }
}
