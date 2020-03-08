package com.example.mobiledevelopmentproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiledevelopmentproject.db.NotesDB;
import com.example.mobiledevelopmentproject.db.NotesDao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import android.hardware.SensorManager;
import com.squareup.seismic.ShakeDetector;


public class Home extends AppCompatActivity implements ShakeDetector.Listener{

    private FirebaseAuth mAuth;
    private ImageButton schedule, signout, notes, camera;
    private NotesDao dao;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

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

        SensorManager SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector SD = new ShakeDetector(this);
        SD.start(SM);

        //DocumentReference gc = db.collection("Users").document("graham.caldwell99@gmail.com").collection("Notes").document("Example Note");
        //gc.update("Example", "Test");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_button_schedule:
                Intent intent = new Intent(this, Schedule3months.class);
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
        dao = NotesDB.getInstance(this).notesDao();
        dao.deleteNotes();
        updateUI(null);

    }

    @Override
    public void hearShake() {
        Toast.makeText(this, "Shaken", Toast.LENGTH_SHORT).show();
    }
}