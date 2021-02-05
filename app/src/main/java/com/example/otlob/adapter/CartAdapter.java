package com.example.otlob.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otlob.R;
import com.example.otlob.model.MyCart;
import com.example.otlob.services.Constants;
import com.example.otlob.viewmodel.FragmentViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    MyCart model;
    int count;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView cart_order_name, tv_cart_total_price, tv_cart_size, tv_count;
        private CircleImageView img_cart;
        private ImageButton btn_add_item_cart, btn_minus_item_cart;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cart_order_name = itemView.findViewById(R.id.cart_order_name);
            tv_cart_total_price = itemView.findViewById(R.id.tv_cart_total_price);
            tv_cart_size = itemView.findViewById(R.id.tv_cart_size);
            tv_count = itemView.findViewById(R.id.tv_count);
            btn_add_item_cart = itemView.findViewById(R.id.btn_add_item_cart);
            btn_minus_item_cart = itemView.findViewById(R.id.btn_minus_item_cart);
            img_cart = itemView.findViewById(R.id.img_cart);

            btn_add_item_cart.setOnClickListener(this);
            btn_minus_item_cart.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            count = Integer.parseInt(String.valueOf(tv_count.getText()));
            int position = getAdapterPosition();
            model = MyCartList.get(position);

            switch (v.getId()){
                case R.id.btn_add_item_cart :
                    if (count < 15){
                        count++;
                        int totalItemPrice = model.getItemPrice() * count;
                        FragmentViewModel.getINSTANCE().updateTotalPrice(model, totalItemPrice,count);
                    }else {
                        Toast.makeText(context, "Maximum Order", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.btn_minus_item_cart :
                    if (count > 1){
                        count--;
                        int totalItemPrice = model.getItemPrice() * count;
                        FragmentViewModel.getINSTANCE().updateTotalPrice(model, totalItemPrice,count);
                    }else {
                        Toast.makeText(context, "Minimum Order", Toast.LENGTH_LONG).show();
                    }
            }
        }
    }

    private Context context;
    private List<MyCart> MyCartList;

    public CartAdapter(Context context, List<MyCart> MyCartList) {
        this.context = context;
        this.MyCartList = MyCartList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        model = MyCartList.get(position);

        holder.cart_order_name.setText(model.getOrderName());
        holder.tv_cart_total_price.setText(String.valueOf(model.getTotalItemPrice() + " EGP"));
        holder.tv_cart_size.setText(model.getSize());
        holder.tv_count.setText(String.valueOf(model.getPiece()));
        Picasso.get()
                .load(model.getImgUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.img_cart);

    }

    @Override
    public int getItemCount() {
        return MyCartList.size();
    }

}
