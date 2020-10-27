package com.example.otlob.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.otlob.R;
import com.example.otlob.databinding.ActivityLoginBinding;
import com.example.otlob.services.Validation;
import com.example.otlob.viewmodel.UserViewModel;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Arrays;


public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserViewModel model;

    private String str_Email, str_Password;
    private GoogleSignInClient mGoogleSignInClient;

    private final static int RC_SIGN_IN_GOOGLE = 10;
    private final static int RC_SIGN_IN_FACEBOOK = 64206;
    private FirebaseAuth mAuth;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        model = new ViewModelProvider(this).get(UserViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);


        // Initialize Facebook SDK
        FacebookSdk.sdkInitialize(Login.this);


        mAuth = FirebaseAuth.getInstance();

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_Email = binding.edEmail.getEditText().getText().toString();
                str_Password = binding.edPassword.getEditText().getText().toString();

                if (!Validation.validationEmail(str_Email, binding.edEmail)) {
                    binding.edEmail.requestFocus();
                } else if (!Validation.validationPassword(str_Password, binding.edPassword)) {
                    binding.edPassword.requestFocus();
                } else {
                    UserViewModel.getINSTANCE().login(str_Email, str_Password, Login.this);
                }
            }
        });

        binding.btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSignInToGoogle();
            }
        });

        binding.btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { requestSignInToFacebook(); }});

    }

    private void requestSignInToFacebook() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(Login.this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                UserViewModel.getINSTANCE().handleFacebookAccessToken(loginResult.getAccessToken(), Login.this);
            }

            @Override
            public void onCancel() {
                Toast.makeText(Login.this, "Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.println(Log.ASSERT, "Error", error.getMessage());
            }
        });
    }

    private void requestSignInToGoogle() {

        /** this allow to user choose any email to login by it */
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Sign In
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN_GOOGLE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN_FACEBOOK) {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        } else {

            Log.println(Log.ASSERT, "Request", String.valueOf(requestCode));
            Log.println(Log.ASSERT, "Result", String.valueOf(resultCode));
            Log.println(Log.ASSERT, "Data", String.valueOf(data));
        }

//         Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN_GOOGLE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                UserViewModel.getINSTANCE().firebaseAuthWithGoogle(account.getIdToken(), this);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}