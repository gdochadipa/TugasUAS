package co.ocha.tugasprogmob.Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import co.ocha.tugasprogmob.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<CategoryModel> list;

    private OnItemClickCallback onItemClickCallback;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<CategoryModel> getList() {
        return list;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setList(ArrayList<CategoryModel> list) {
        this.list = list;
    }

    public CategoryAdapter(Context context, ArrayList<CategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cat,parent,false);
        return new CategoryViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryAdapter.CategoryViewHolder holder, int i) {
        holder.title.setText(getList().get(i).getCategoryName());
//        holder.poster.setImageResource(getList().get(i).getCategoryImage());

        Glide.with(context)
                .load(getList().get(i).getCategoryImage())
                .apply(new RequestOptions().override(400,200))
                .into(holder.poster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(list.get(holder.getAdapterPosition()),holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView poster;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_cat);
            poster=itemView.findViewById(R.id.image);

        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(CategoryModel category, int position);
    }
}
