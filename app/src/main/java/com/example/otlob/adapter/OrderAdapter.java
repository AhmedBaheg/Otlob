package com.example.otlob.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otlob.R;
import com.example.otlob.model.Receipt;
import com.example.otlob.viewmodel.FragmentViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Receipt model;
    private SubOrderAdapter subAdapter;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_OrderID, tv_total_order_price;
        private RecyclerView sub_recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_OrderID = itemView.findViewById(R.id.tv_OrderID);
            tv_total_order_price = itemView.findViewById(R.id.tv_total_order_price);
            sub_recyclerView = itemView.findViewById(R.id.sub_recyclerView);

        }
    }


    private Context context;
    private List<Receipt> ReceiptList;

    public OrderAdapter(Context context, List<Receipt> ReceiptList) {
        this.context = context;
        this.ReceiptList = ReceiptList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        model = ReceiptList.get(position);

        holder.tv_OrderID.setText(model.getKey());
        holder.tv_total_order_price.setText(String.valueOf(model.getTotalOrderPrice()));

//        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.sub_recyclerView.getContext());
//        holder.sub_recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public int getItemCount() {
        return ReceiptList.size();
    }

}
