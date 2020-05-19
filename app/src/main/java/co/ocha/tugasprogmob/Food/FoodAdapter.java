package co.ocha.tugasprogmob.Food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import co.ocha.tugasprogmob.Category.CategoryAdapter;
import co.ocha.tugasprogmob.R;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private ArrayList<FoodModel> list;

    private OnItemClickCallback onItemClickCallback;

    public FoodAdapter( ArrayList<FoodModel> list) {
        this.list = list;
    }

    public ArrayList<FoodModel> getList() {
        return list;
    }

    public void setList(ArrayList<FoodModel> list) {
        this.list = list;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food,parent,false);
        return new FoodViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodAdapter.FoodViewHolder holder, int position) {
        holder.title.setText(getList().get(position).getFoodName());
        Glide.with(holder.itemView.getContext())
                .load(getList().get(position).getFoodImage())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.poster);
        holder.desc.setText(getList().get(position).getFoodDesc());

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

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView desc;
        private ImageView poster;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.img_item_photo);
            title = itemView.findViewById(R.id.tv_item_name);
            desc = itemView.findViewById(R.id.tv_item_description);
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(FoodModel foodModel, int position);
    }
}
