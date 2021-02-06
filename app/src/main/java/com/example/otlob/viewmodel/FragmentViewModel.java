package com.example.otlob.viewmodel;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.otlob.adapter.CartAdapter;
import com.example.otlob.model.CategoryItem;
import com.example.otlob.model.MyCart;
import com.example.otlob.services.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentViewModel extends ViewModel {

    // Constants
    private static MutableLiveData<List<CategoryItem>> MUTABLE_RECYCLER;
    private static MutableLiveData<List<MyCart>> MUTABLE_CART_RECYCLER;
    private static MutableLiveData<CategoryItem> MUTABLE_ITEM;
    private static MutableLiveData<CategoryItem> MUTABLE_SIZE;
    private static FragmentViewModel INSTANCE;

    // Firebase
    private DatabaseReference refCategory = FirebaseDatabase.getInstance().getReference(Constants.CATEGORY);
    private DatabaseReference refSize = FirebaseDatabase.getInstance().getReference(Constants.SIZE);
    private DatabaseReference refCart = FirebaseDatabase.getInstance().getReference(Constants.CART);

    // Model
    private CategoryItem model;
    private MyCart cart;

    // ArrayList
    ArrayList<CategoryItem> itemArrayList;
    ArrayList<MyCart> cartArrayList;

    public static MutableLiveData<List<CategoryItem>> getMUTABLE_RECYCLER() {
        if (MUTABLE_RECYCLER == null) {
            MUTABLE_RECYCLER = new MutableLiveData<>();
        }
        return MUTABLE_RECYCLER;
    }

    public static MutableLiveData<List<MyCart>> getMUTABLE_CART_RECYCLER() {
        if (MUTABLE_CART_RECYCLER == null) {
            MUTABLE_CART_RECYCLER = new MutableLiveData<>();
        }
        return MUTABLE_CART_RECYCLER;
    }

    public static MutableLiveData<CategoryItem> getMUTABLE_ITEM() {
        if (MUTABLE_ITEM == null) {
            MUTABLE_ITEM = new MutableLiveData<>();
        }
        return MUTABLE_ITEM;
    }

    public static MutableLiveData<CategoryItem> getMUTABLE_SIZE() {
        if (MUTABLE_SIZE == null) {
            MUTABLE_SIZE = new MutableLiveData<>();
        }
        return MUTABLE_SIZE;
    }

    public static FragmentViewModel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new FragmentViewModel();
        }
        return INSTANCE;
    }

    public void getItemInRecycler() {

        itemArrayList = new ArrayList<>();
        refCategory.child(Constants.CATEGORY_NAME).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    model = dataSnapshot.getValue(CategoryItem.class);
                    itemArrayList.add(model);
                }
                FragmentViewModel.getMUTABLE_RECYCLER().setValue(itemArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void getItem() {

        refCategory.child(Constants.CATEGORY_NAME)
                .child(Constants.CATEGORY_ITEM_NAME).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                model = snapshot.getValue(CategoryItem.class);
                FragmentViewModel.getMUTABLE_ITEM().setValue(model);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getItemSize() {
        if (Constants.ITEM_SIZE != null) {
            refSize.child(Constants.CATEGORY_NAME).child(Constants.ITEM_SIZE)
                    .child(Constants.CATEGORY_ITEM_NAME).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    model = snapshot.getValue(CategoryItem.class);
                    FragmentViewModel.getMUTABLE_SIZE().setValue(model);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void uploadOrderToCart(CategoryItem model, String imgUrl) {

        Constants.CART_KEY = refCart.push().getKey();
        int piece = 0;
        int totalItemPrice = model.getPrice() * piece;

        cart = new MyCart(model.getName(), Constants.ITEM_SIZE, imgUrl, Constants.CART_KEY, model.getPrice(), piece, totalItemPrice);
        refCart.child(Constants.getUID()).child(Constants.CART_KEY).setValue(cart);

    }

    public void getItemToMyCartInRecycler() {

        cartArrayList = new ArrayList<>();

        refCart.child(Constants.getUID())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        cartArrayList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            cart = dataSnapshot.getValue(MyCart.class);
                            cartArrayList.add(cart);
                        }
                        FragmentViewModel.getMUTABLE_CART_RECYCLER().setValue(cartArrayList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    public void updateTotalPrice(MyCart model, int totalItemPrice, int count) {
        refCart.child(Constants.getUID()).child(model.getId()).child("totalItemPrice").setValue(totalItemPrice);
        refCart.child(Constants.getUID()).child(model.getId()).child("piece").setValue(count);
    }

    public void dialogDeleteItemFromCart(final Context context, final MyCart model){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle(model.getOrderName())
                .setMessage("Are You Sure From Delete This Order: " + model.getOrderName())
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        refCart.child(Constants.getUID()).child(model.getId()).removeValue();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

}
