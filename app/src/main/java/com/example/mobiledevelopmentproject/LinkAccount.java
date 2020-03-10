package com.example.mobiledevelopmentproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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
        final String email = user.getEmail();

        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DocumentReference userPartner = db.collection("Users").document(email).collection("Partner").document("Partner");
                userPartner.update("Partner", partnerName.getText().toString());

                DocumentReference userPartner2 = db.collection("Users").document(partnerName.getText().toString()).collection("Partner").document("Partner");
                userPartner2.update("Partner", email);
            }
        });

    }
}
