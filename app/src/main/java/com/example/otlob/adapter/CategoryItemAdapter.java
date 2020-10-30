package com.example.otlob.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otlob.R;
import com.example.otlob.model.CategoryItem;
import com.example.otlob.services.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView img_item, img_right;
        private TextView tv_item_name;

        private CategoryItem model;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_item = itemView.findViewById(R.id.img_item);
            img_right = itemView.findViewById(R.id.img_right);
            tv_item_name = itemView.findViewById(R.id.tv_item_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    model = CategoryItemList.get(position);
                    Constants.CATEGORY_ITEM_NAME = model.getName();
                }
            });

        }
    }

    private Context context;
    private List<CategoryItem> CategoryItemList;

    public CategoryItemAdapter(Context context, List<CategoryItem> CategoryItemList) {
        this.context = context;
        this.CategoryItemList = CategoryItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.category_item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get()
                .load(CategoryItemList.get(position).getImgUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.img_item);
        holder.tv_item_name.setText(CategoryItemList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return CategoryItemList.size();
    }

}
