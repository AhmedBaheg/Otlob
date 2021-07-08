package com.example.otlob.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.otlob.activity.Home;
import com.example.otlob.activity.Login;
import com.example.otlob.model.User;
import com.example.otlob.model.UserProfile;
import com.example.otlob.services.Constants;
import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserViewModel extends ViewModel {

    // Constants
    private static MutableLiveData<UserProfile> MUTABLE_USER_PROFILE;
    private static UserViewModel INSTANCE;
    // Firebase
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.USER);
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    // Model
    private User user;

    public static MutableLiveData<UserProfile> getMUTABLE_USER_PROFILE() {
        if (MUTABLE_USER_PROFILE == null) {
            MUTABLE_USER_PROFILE = new MutableLiveData<>();
        }
        return MUTABLE_USER_PROFILE;
    }

    public static UserViewModel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UserViewModel();
        }
        return INSTANCE;
    }

    private void finishFunction(Context context) {
        Activity activity = (Activity) context;
        activity.finish();
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
                                finishFunction(context);
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

    public void login(String email, String password, final Context context) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    context.startActivity(new Intent(context, Home.class));
                    finishFunction(context);
                } else {
                    String message = task.getException().toString();
                    Toast.makeText(context, "Your email or password incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void firebaseAuthWithGoogle(String idToken, final Context context) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser firebaseUser = auth.getCurrentUser();

                            if (firebaseUser.getPhoneNumber() == null) {
                                user = new User(firebaseUser.getDisplayName(), firebaseUser.getEmail());
                            } else {
                                user = new User(firebaseUser.getDisplayName(), firebaseUser.getEmail(), firebaseUser.getPhoneNumber());
                            }

                            ref.child(Constants.getUID()).setValue(user);

                            context.startActivity(new Intent(context, Home.class));
                            finishFunction(context);
                        } else {
                            // If sign in fails, display a message to the user.
                            String message = task.getException().toString();
//                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void signOut(GoogleSignInClient googleSignInClient, final Context context) {
        auth.signOut();
        googleSignInClient.signOut()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            context.startActivity(new Intent(context, Login.class));
                            finishFunction(context);
                        } else {
                            Toast.makeText(context, "Failed Sign Out", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void handleFacebookAccessToken(AccessToken token, final Context context) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                if (firebaseUser.getPhoneNumber() == null) {
                                    user = new User(firebaseUser.getDisplayName(), firebaseUser.getEmail());
                                } else {
                                    user = new User(firebaseUser.getDisplayName(), firebaseUser.getEmail(), firebaseUser.getPhoneNumber());
                                }
                                ref.child(Constants.getUID()).setValue(user);

                                context.startActivity(new Intent(context, Home.class));
                                finishFunction(context);
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(context, "Authentication failed.", Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }

}
