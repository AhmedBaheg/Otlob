package com.example.otlob.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.otlob.model.CategoryItem;
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
    private static MutableLiveData<CategoryItem> MUTABLE_ITEM;
    private static MutableLiveData<CategoryItem> MUTABLE_SIZE;
    private static FragmentViewModel INSTANCE;

    // Firebase
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.CATEGORY);
    private DatabaseReference refSize = FirebaseDatabase.getInstance().getReference(Constants.SIZE);

    // Model
    private CategoryItem model;

    // ArrayList
    ArrayList<CategoryItem> itemArrayList;

    public static MutableLiveData<List<CategoryItem>> getMUTABLE_RECYCLER() {
        if (MUTABLE_RECYCLER == null) {
            MUTABLE_RECYCLER = new MutableLiveData<>();
        }
        return MUTABLE_RECYCLER;
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
        itemArrayList.clear();
        ref.child(Constants.CATEGORY_NAME).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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

        ref.child(Constants.CATEGORY_NAME)
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

}