package co.ocha.tugasprogmob.ui.dashboard;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.ocha.tugasprogmob.Category.CategoryAdapter;
import co.ocha.tugasprogmob.Category.CategoryModel;
import co.ocha.tugasprogmob.CategoryRestaurantActivity;
import co.ocha.tugasprogmob.FoodActivity;
import co.ocha.tugasprogmob.R;

public class RestaurantFragment extends Fragment {

    private RestaurantViewModel dashboardViewModel;

    private RecyclerView catRecycle;
    private ArrayList<CategoryModel> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(RestaurantViewModel.class);
        View root = inflater.inflate(R.layout.fragment_restaurant, container, false);
        catRecycle = root.findViewById(R.id.resRecycle);
        catRecycle.setHasFixedSize(true);
        list.addAll(getListCategory());
        showRecycle();

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public ArrayList<CategoryModel> getListCategory(){
        String[] categoryName = getResources().getStringArray(R.array.restaurant_food_category);
        TypedArray categoryImage = getResources().obtainTypedArray(R.array.restaurant_img_category);

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
        catRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        CategoryAdapter adapter = new CategoryAdapter(getActivity(),list);
        catRecycle.setAdapter(adapter);

        adapter.setOnItemClickCallback(new CategoryAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(CategoryModel category, int position) {
//                Toast.makeText(getApplicationContext(),"halo "+position, LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), CategoryRestaurantActivity.class);
                intent.putExtra("NAME",category.getCategoryName());
                intent.putExtra("POSITION",position);

                startActivity(intent);
            }
        });
    }
}
