package co.ocha.tugasprogmob;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.ocha.tugasprogmob.Restaurant.RestaurantModel;

public class RestaurantActivity extends AppCompatActivity {
    public static final String EXTRA_FOOD = "extra_food";
    private TextView resDesc,resMenu;
    private Button mapRes,contactRes;
    private RestaurantModel model;
    private CollapsingToolbarLayout toolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        model = new RestaurantModel();
        model = getIntent().getParcelableExtra(EXTRA_FOOD);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(model.getResName());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        toolbarLayout = findViewById(R.id.toolbar_layout);
        resDesc = findViewById(R.id.desc_res);
        resMenu = findViewById(R.id.add_res);
        mapRes = findViewById(R.id.map_res);
        contactRes = findViewById(R.id.contact_res);

        Glide.with(this)
                .load(model.getResImage())
                .apply(new RequestOptions().override(600,300))
                .into(new CustomViewTarget<CollapsingToolbarLayout, Drawable>(toolbarLayout) {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        toolbarLayout.setBackground(resource);
                    }

                    @Override
                    protected void onResourceCleared(@Nullable Drawable placeholder) {

                    }
                });

        resDesc.setText(model.getResDesc());
        resMenu.setText(model.getResMenu());
        mapRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri urimap = Uri.parse(model.getResMap());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, urimap);
                startActivity(mapIntent);
            }
        });

        contactRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriCall = Uri.parse("tel:"+model.getResContact());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(uriCall);
                startActivity(callIntent);
            }
        });
    }
}
