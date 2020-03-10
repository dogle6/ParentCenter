package com.example.mobiledevelopmentproject;

import android.app.DownloadManager;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiledevelopmentproject.db.NotesDB;
import com.example.mobiledevelopmentproject.db.NotesDao;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import android.hardware.SensorManager;

import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.seismic.ShakeDetector;

import java.util.HashMap;
import java.util.Map;


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
        camera = (ImageButton) findViewById(R.id.home_button_link);

        //Firebase Initialization
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);

        //Used for shake detection
        SensorManager SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector SD = new ShakeDetector(this);
        SD.start(SM);

        //Checks if partner has shaken phone and opens alert
        String email = user.getEmail();
        final DocumentReference checkRequest = db.collection("Users").document(email).collection("Requested").document("Requested");
        checkRequest.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    boolean requested = documentSnapshot.getBoolean("Requested");
                    if (requested){
                        Log.i("", "Alert");
                        openDialog();
                        checkRequest.update("Requested",false);
                    }
                }
                else{
                    Log.i("", "Failed");
                }
            }
        });
    }

    public void openDialog(){
        AlertDialog.Builder adb = new AlertDialog.Builder(Home.this);
        adb.setTitle("Checkup");
        adb.setMessage("Is everything good?");
        adb.setNeutralButton("Cancel", null);
        adb.setPositiveButton("Yes", null);
        AlertDialog confirmDialog = adb.create();
        confirmDialog.show();
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

            case R.id.home_button_link:
                intent = new Intent(this, LinkAccount.class);
                startActivity(intent);
                break;

        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            //Toast.makeText(Home.this, "Logged In.",
                    //Toast.LENGTH_SHORT).show();
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
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail().toString();
        DocumentReference partner = db.collection("Users").document(email).collection("Partner").document("Partner");

        partner.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String partnerName = documentSnapshot.getString("Partner");
                            Log.i("myDB", partnerName);

                            // Request data to be stored in partner Request doc
                            Map<String, Object> requestData = new HashMap<>();
                            requestData.put("Requested",  true);

                            DocumentReference partnerRequest = db.collection("Users").document(partnerName).collection("Requested").document("Requested");
                            partnerRequest.update("Requested", true);

                            Log.i("myDB", "Success");
                            Toast.makeText(Home.this, "Successful.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Log.i("", "Failed");
                            Toast.makeText(Home.this, "Failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}