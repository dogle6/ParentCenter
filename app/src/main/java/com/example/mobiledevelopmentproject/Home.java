package com.example.mobiledevelopmentproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Home extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ImageButton schedule, signout, notes, camera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        schedule = (ImageButton) findViewById(R.id.home_button_schedule);
        signout = (ImageButton) findViewById(R.id.home_button_sign_out);
        notes = (ImageButton) findViewById(R.id.home_button_notes);
        camera = (ImageButton) findViewById(R.id.home_button_camera);

        //Firebase Initialization
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_button_schedule:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.home_button_sign_out:
                signOut();
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
                break;

            case R.id.home_button_notes:
                intent = new Intent(this, Notes.class);
                startActivity(intent);
                break;

            case R.id.home_button_camera:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(Home.this, "Logged In.",
                    Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(Home.this, "Logged Out.",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    //TODO: Remove local data on sign-out
    private void signOut(){
        mAuth.signOut();
        updateUI(null);
    }

}