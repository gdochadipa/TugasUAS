package co.ocha.tugasprogmob.Restaurant;

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

import co.ocha.tugasprogmob.R;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private ArrayList<RestaurantModel> list;

    private OnItemClickCallback onItemClickCallback;

    public ArrayList<RestaurantModel> getList() {
        return list;
    }

    public RestaurantAdapter(ArrayList<RestaurantModel> list) {
        this.list = list;
    }

    public void setList(ArrayList<RestaurantModel> list) {
        this.list = list;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public RestaurantAdapter.RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food,parent,false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RestaurantAdapter.RestaurantViewHolder holder, final int position) {
        holder.title.setText(getList().get(position).getResName());
        Glide.with(holder.itemView.getContext())
                .load(getList().get(position).getResImage())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.poster);
        holder.desc.setText(getList().get(position).getResDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(list.get(holder.getAdapterPosition()),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView desc;
        private ImageView poster;
        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.img_item_photo);
            title = itemView.findViewById(R.id.tv_item_name);
            desc = itemView.findViewById(R.id.tv_item_description);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(RestaurantModel model,int position);
    }
}
