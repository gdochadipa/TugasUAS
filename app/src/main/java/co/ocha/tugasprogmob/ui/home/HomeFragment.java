package co.ocha.tugasprogmob.ui.home;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.ocha.tugasprogmob.Category.CategoryAdapter;
import co.ocha.tugasprogmob.Category.CategoryModel;
import co.ocha.tugasprogmob.FoodActivity;
import co.ocha.tugasprogmob.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private RecyclerView catRecycle;
    private ArrayList<CategoryModel> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_receipt_home, container, false);
        catRecycle = root.findViewById(R.id.catRecycle);
        catRecycle.setHasFixedSize(true);

        list.addAll(getListCategory());
        showRecycle();
        return root;
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
        catRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        CategoryAdapter adapter = new CategoryAdapter(getActivity(),list);
        catRecycle.setAdapter(adapter);

        adapter.setOnItemClickCallback(new CategoryAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(CategoryModel category, int position) {
//                Toast.makeText(getApplicationContext(),"halo "+position, LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), FoodActivity.class);
                intent.putExtra("NAME",category.getCategoryName());
                intent.putExtra("POSITION",position);
                startActivity(intent);
            }
        });
    }
}
