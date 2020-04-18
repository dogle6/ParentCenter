package com.example.mobiledevelopmentproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LinkAccount extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private Button btnLink;
    private EditText partnerName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_account);

        partnerName = findViewById(R.id.partner_username);
        btnLink = findViewById(R.id.btn_link);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        final String email = user.getEmail().toLowerCase();

        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> tempPartnerData = new HashMap<>();
                tempPartnerData.put("Partner", partnerName.getText().toString());
                final Map<String, Object> partnerData = tempPartnerData;

                Map<String, Object> tempMyData = new HashMap<>();
                tempMyData.put("Partner", email);
                final Map<String, Object> myData = tempMyData;

                // Check if the partner Email actually exists in the firestore
                db.collection("Users").document(partnerName.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d("myDB", "INFO | Partner User Exists.");

                                // Adding partner data to current user's information
                                db.collection("Users").document(email).collection("Partner").document("Partner")
                                        .set(partnerData)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(LinkAccount.this, "Link successful.",
                                                        Toast.LENGTH_SHORT).show();

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(LinkAccount.this, "Link unsuccessful.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                // Adding current user's data to partner information
                                db.collection("Users").document(partnerName.getText().toString()).collection("Partner").document("Partner")
                                        .set(myData)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(LinkAccount.this, "Link successful (in partner).",
                                                        Toast.LENGTH_SHORT).show();
                                                returnHome();

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(LinkAccount.this, "Link unsuccessful.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        });


                            } else {
                                Log.d("myDB", "ERROR | Partner User Does Not Exist.");
                                Toast.makeText(LinkAccount.this, "This account does not exist.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d("myDB", "get failed with ", task.getException());

                        }
                    }
                });
            }
        });

    }

    public void returnHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }





}

