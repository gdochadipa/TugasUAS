package co.ocha.tugasprogmob.ui.main;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.ocha.tugasprogmob.Food.FoodModel;
import co.ocha.tugasprogmob.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReceptFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private FoodModel food;
    private TextView reciept;
    private PageViewModel pageViewModel;

    public static ReceptFragment  newInstance(int index,FoodModel foodModel) {
        ReceptFragment receptFragment = new ReceptFragment(foodModel);
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        receptFragment.setArguments(bundle);
        return receptFragment;
    }

    public ReceptFragment(FoodModel foodModel) {
        this.food=foodModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_recept, container, false);
        reciept = root.findViewById(R.id.reciept_food);
        reciept.setText(food.getFoodRecipte());
        return root;
    }

}
