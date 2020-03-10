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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
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

                Map<String, Object> partnerData = new HashMap<>();
                partnerData.put("Partner",  partnerName.getText().toString() );

                Map<String, Object> myData = new HashMap<>();
                myData.put("Partner",  email );

                db.collection("Users").document(email).collection("Partner").document("Partner")
                        .set( partnerData )
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LinkAccount.this, "Link successful.",
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

                db.collection("Users").document(partnerName.getText().toString()).collection("Partner").document("Partner")
                        .set( myData )
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LinkAccount.this, "Link successful.",
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
            }
        });

    }

    public void returnHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
