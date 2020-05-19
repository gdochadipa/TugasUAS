package co.ocha.tugasprogmob;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.ActionBar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import co.ocha.tugasprogmob.Food.FoodModel;
import co.ocha.tugasprogmob.ui.main.SectionsPagerAdapter;

public class DetailFoodActivity extends AppCompatActivity {
    public static final String EXTRA_FOOD = "extra_food";
    FoodModel foodModel ;
    private ImageView image_action;
    private TextView title_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);
        foodModel = new FoodModel();
        foodModel = getIntent().getParcelableExtra(EXTRA_FOOD);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),foodModel);
        ViewPager viewPager = findViewById(R.id.view_pager);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        title_food = findViewById(R.id.title_food);
        image_action = findViewById(R.id.image_action);
        Glide.with(this)
                .load(foodModel.getFoodImage())
                 .apply(new RequestOptions().override(600,300))
                .into(image_action);
        title_food.setText(foodModel.getFoodName());
    }
}