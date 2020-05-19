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

import co.ocha.tugasprogmob.Food.FoodAdapter;
import co.ocha.tugasprogmob.Food.FoodModel;

public class FoodActivity extends AppCompatActivity {
  private  String[] foodName;
  private  TypedArray foodImage;
  private  String[] foodMap;
  private  String[] foodDesc;
  private  String[] foodReceipt;
  private  String[] foodContact;
  private String categoryName;
  private int position;
  private RecyclerView recyclerView;
  private ArrayList<FoodModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        position=getIntent().getIntExtra("POSITION",0);
        recyclerView = findViewById(R.id.foodRecycle);
        recyclerView.setHasFixedSize(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        list.addAll(getListFood(position));
        showRecycle();

    }

    public ArrayList<FoodModel> getListFood(int position){
        switch (position){
            case 0:
                foodName = getResources().getStringArray(R.array.name_food_chinnees);
                foodImage = getResources().obtainTypedArray(R.array.img_food_chinnees);
                foodMap=getResources().getStringArray(R.array.map_food_chinnees);
                foodReceipt=getResources().getStringArray(R.array.receipt_food_chinnees);
                foodDesc=getResources().getStringArray(R.array.desc_food_chinnees);
                foodContact=getResources().getStringArray(R.array.contact_food_chinnees);
                break;
            case 1:
                foodName = getResources().getStringArray(R.array.name_food_traditional);
                foodImage = getResources().obtainTypedArray(R.array.img_food_traditional);
                foodMap=getResources().getStringArray(R.array.map_food_traditional);
                foodReceipt=getResources().getStringArray(R.array.receipt_food_traditional);
                foodDesc=getResources().getStringArray(R.array.desc_food_traditional);
                foodContact=getResources().getStringArray(R.array.contact_food_traditional);
                break;
            case 2:
                foodName = getResources().getStringArray(R.array.name_food_western);
                foodImage = getResources().obtainTypedArray(R.array.img_food_western);
                foodMap=getResources().getStringArray(R.array.map_food_western);
                foodReceipt=getResources().getStringArray(R.array.receipt_food_western);
                foodDesc=getResources().getStringArray(R.array.desc_food_western);
                foodContact=getResources().getStringArray(R.array.contact_food_western);
                break;

        }
        ArrayList<FoodModel> food = new ArrayList<>();
        for (int i=0;i<foodName.length;i++){
            FoodModel foodModel = new FoodModel();
            foodModel.setFoodName(foodName[i]);
            foodModel.setFoodImage(foodImage.getResourceId(i,-1));
            foodModel.setFoodDesc(foodDesc[i]);
            foodModel.setFoodMap(foodMap[i]);
            foodModel.setFoodRecipte(foodReceipt[i]);
            foodModel.setFoodContact(foodContact[i]);

            food.add(foodModel);
        }
        return food;
    }

    private void showRecycle(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FoodAdapter adapter = new FoodAdapter(list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickCallback(new FoodAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(FoodModel foodModel, int position) {
                Intent intent = new Intent(getApplicationContext(),DetailFoodActivity.class);
                intent.putExtra(DetailFoodActivity.EXTRA_FOOD,foodModel);
                startActivity(intent);
            }
        });
    }
}
