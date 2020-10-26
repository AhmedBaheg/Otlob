package com.example.otlob.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.otlob.activity.Login;
import com.example.otlob.activity.Register;
import com.example.otlob.model.User;
import com.example.otlob.services.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserViewModel extends ViewModel {

    // Constants
    private static MutableLiveData<User> MUTABLE;
    private static UserViewModel INSTANCE;
    // Firebase
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.USER);
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    // Model
    private User user;

    public static MutableLiveData<User> getMUTABLE() {
        if (MUTABLE == null) {
            MUTABLE = new MutableLiveData<>();
        }
        return MUTABLE;
    }

    public static UserViewModel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UserViewModel();
        }
        return INSTANCE;
    }

    public void register(final String fullName, final String email, String password, final String phoneNumber, final Context context) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    user = new User(fullName, email, phoneNumber);
                    ref.child(Constants.getUID()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                context.startActivity(new Intent(context, Login.class));

                            } else {
                                String message = task.getException().toString();
                                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(context, "Your Email Already Exists", Toast.LENGTH_LONG).show();
                }
            }
        });


    }



}
