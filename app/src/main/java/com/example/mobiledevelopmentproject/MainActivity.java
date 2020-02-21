package com.example.mobiledevelopmentproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.text.TextUtils;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView signUp, status;
    private EditText username, password;
    private ImageButton login;
    private FirebaseAuth mAuth;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        signUp = (TextView) findViewById(R.id.login_textView_signUp);
        username = (EditText) findViewById(R.id.login_editText_username);
        password = (EditText) findViewById(R.id.login_editText_password);
        login = (ImageButton) findViewById(R.id.login_button);
        progressbar = (ProgressBar) findViewById(R.id.login_progressbar);
        status = (TextView) findViewById(R.id.login_textView_status);

        //Firebase Initialization
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(clickListener);
        signUp.setOnClickListener(clickListener);

        // Set status to null
        status.setText("");

        //MUST IMPLEMENT SIGN OUT BUTTON //
    }


    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int i = v.getId();
            if( i == R.id.login_button){
                signIn(username.getText().toString(), password.getText().toString() );
            }
            if( i == R.id.login_textView_signUp){
                createAccount( username.getText().toString(), password.getText().toString() );
            }
        }
    };

    @Override
    public void onStart(){
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    private void createAccount(String email, String password){
        Log.d("DEBUG", "createAccount(): " + email);
        if( !validateForm() ){
            return;
        }

        progressbar.setVisibility(View.VISIBLE); // Show progress bar

        // Start to create user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("INFO", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Toast.makeText(MainActivity.this, "Account Creation Successful.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("ERROR", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Account Creation Failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                progressbar.setVisibility(View.INVISIBLE); // Set progress bar to invisible
            }
        });

    }
    private  void signIn( String email, String password){
        Log.d("DEBUG", "signIn: " + email);
        if( !validateForm() ){
            return;
        }
        progressbar.setVisibility(View.VISIBLE); // Show progress bar
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() ){
                    // Sign in is successful, update UI with signed-in user's information
                    Log.d("DEGBUG", "signInWithEmail: success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else{
                    // If sign in fails, display a failure message to the user
                    Log.w("ERROR", "signInWithEmail: failure");
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
                progressbar.setVisibility(View.INVISIBLE); // Hide progress bar

            }
        });
    }
    private void signOut(){
        mAuth.signOut();
        updateUI(null);
    }
    private boolean validateForm() {
        boolean valid = true;

        String email = username.getText().toString();
        if (TextUtils.isEmpty(email)) {
            username.setError("Required.");
            valid = false;
        } else {
            username.setError(null);
        }

        String passwordString= password.getText().toString();
        if (TextUtils.isEmpty(passwordString)) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }

    // https://github.com/firebase/quickstart-android/blob/995be41782f84b3c54e41d7d7e5d3a5048fe329e/auth/app/src/main/java/com/google/firebase/quickstart/auth/java/EmailPasswordActivity.java#L75-L81
    private void updateUI(FirebaseUser user) {
        progressbar.setVisibility(View.INVISIBLE);
        if (user != null) {
            status.setText( user.getEmail() + " is verified: " + user.isEmailVerified() );

        } else {
            status.setText("");
        }
    }



}
