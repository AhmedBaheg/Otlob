package com.example.otlob.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otlob.R;
import com.example.otlob.model.SubReceipt;
import com.example.otlob.model.SubReceipt;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SubOrderAdapter extends RecyclerView.Adapter<SubOrderAdapter.ViewHolder> {

    private SubReceipt model;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_order_name, tv_order_size, tv_order_total_price, tv_count;
        private CircleImageView img_order;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_order_name = itemView.findViewById(R.id.tv_order_name);
            tv_order_size = itemView.findViewById(R.id.tv_order_size);
            tv_order_total_price = itemView.findViewById(R.id.tv_order_total_price);
            tv_count = itemView.findViewById(R.id.tv_count);
            img_order = itemView.findViewById(R.id.img_order);
        }
    }

    private Context context;
    public List<SubReceipt> SubReceiptList;

    public SubOrderAdapter(Context context, List<SubReceipt> SubReceiptList) {
        this.context = context;
        this.SubReceiptList = SubReceiptList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sub_item_order, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        model = SubReceiptList.get(position);

        holder.tv_order_name.setText(model.getOrderName());
        holder.tv_order_size.setText(model.getSize());
        holder.tv_order_total_price.setText(model.getTotalItemPrice() + " EGP");
        holder.tv_count.setText(String.valueOf(model.getPiece()));
        Picasso.get()
                .load(model.getImgUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.img_order);

    }

    @Override
    public int getItemCount() {
        return SubReceiptList.size();
    }

}
