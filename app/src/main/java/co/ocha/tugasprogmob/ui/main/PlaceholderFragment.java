package co.ocha.tugasprogmob.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import co.ocha.tugasprogmob.Food.FoodModel;
import co.ocha.tugasprogmob.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private TextView desc_food,add_food;
    private Button map_food,contact_food;
    private FoodModel food;

    public static PlaceholderFragment newInstance(int index,FoodModel foodModel) {
        PlaceholderFragment fragment = new PlaceholderFragment(foodModel);
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    public PlaceholderFragment(FoodModel foodModel){
        this.food=foodModel;
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail_food, container, false);
        desc_food = root.findViewById(R.id.desc_food);
        add_food = root.findViewById(R.id.add_food);
        map_food=root.findViewById(R.id.map_food);
        contact_food=root.findViewById(R.id.contact_food);

        desc_food.setText(food.getFoodDesc());
        map_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri urimap = Uri.parse(food.getFoodMap());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, urimap);
                startActivity(mapIntent);
            }
        });

        contact_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriCall = Uri.parse("tel:"+food.getFoodContact());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(uriCall);
                startActivity(callIntent);
            }
        });

        return root;
    }
}