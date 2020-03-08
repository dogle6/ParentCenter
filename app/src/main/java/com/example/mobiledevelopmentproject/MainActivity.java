package com.example.mobiledevelopmentproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.nfc.Tag;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //FirebaseAuth Initialization
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
                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                signIn(username.getText().toString(), password.getText().toString(), db );
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
    private void createAccount(final String email, String password){
        Log.i("DEBUG", "createAccount(): " + email);
        if( !validateForm() ){
            return;
        }

        progressbar.setVisibility(View.VISIBLE); // Show progress bar

        // Start to create user
        mAuth.createUserWithEmailAndPassword( email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("INFO", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            final FirebaseFirestore db = FirebaseFirestore.getInstance();
                            addUserToFirestore(db, email);
                            Toast.makeText(MainActivity.this, "Account Creation Successful.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("ERROR", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Account Creation Failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                progressbar.setVisibility(View.INVISIBLE); // Set progress bar to invisible
            }
        });

    }
    private  void signIn(final String email, String password, final FirebaseFirestore db){
        Log.i("DEBUG", "signIn: " + email);
        if( !validateForm() ){
            return;
        }
        progressbar.setVisibility(View.VISIBLE); // Show progress bar
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() ){
                    // Sign in is successful, update UI with signed-in user's information
                    Log.i("DEGBUG", "signInWithEmail: success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    getUserDate(db, email);
                    // TODO: Switch intent to home page
                    // Test Push

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
    private void updateUI(FirebaseUser user) {
        progressbar.setVisibility(View.INVISIBLE);
        if (user != null) {
            status.setText( "Login Successful" );
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);

        } else {
            status.setText("");
        }
    }
    private void getUserDate(final FirebaseFirestore db, final String username ){
        final String TAG = "getUserData";
        Log.i(TAG, "Getting " + username );
        boolean userFound = false;
        db.collection("Users").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if( task.isSuccessful() ){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.i(TAG, document.getId() + " => " + document.getData());
                                if( document.getId().equals(username) ){
                                    Log.i(TAG, username + " found." );
                                    return;

                                }
                            }
                            Log.i(TAG, username + " NOT found." );
                            addUserToFirestore(db, username);
                        }
                    }
                });


    }
    private void addUserToFirestore(FirebaseFirestore db, String username) {
        final String TAG = "addUserToFirestore";
        Log.i(TAG, "Adding new " + username + " to user collection");

        // Hash map of account creation date
        Map<String, Object> userCreation = new HashMap<>();
        Date date = new Date();
        userCreation.put("Created", date.toString() );

        // Variable for collection's document storage
        Map<String, Object> noteExample = new HashMap<>();
        noteExample.put("Example", "Hello, welcome to Parent Center!");

        Map<String, Object> scheduleExample = new HashMap<>();
        scheduleExample.put("Example", "Hello, welcome to Parent Center!");

        // Setting up structure of firestore instance
        db.collection("Users").document(username).set(userCreation);
        db.collection("Users").document(username).collection("Notes").document("Example Note").set(noteExample);
        db.collection("Users").document(username).collection("Schedule").document("Example Instance").set(scheduleExample);



    }


}
