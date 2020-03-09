package com.example.mobiledevelopmentproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class LinkAccount extends AppCompatActivity {

    private FirebaseFirestore db2 = FirebaseFirestore.getInstance();
    private Button btnLink;
    private EditText partnerName;
     String username, partnerUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_account);

        partnerName = findViewById(R.id.partner_username);
        partnerUsername = partnerName.getText().toString();
        btnLink = findViewById(R.id.btn_link);

        //Intent intent = getIntent();
        //username = intent.getStringExtra("USERNAME");

        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                username = intent.getStringExtra("USERNAME");
                //DocumentReference userPartner = db2.collection("Users").document((String)username).collection("Partner").document("Partner");
                //userPartner.update("Partner", (String)partnerUsername);
                Log.i("","AAA");
                Log.i("", partnerName.getText().toString());
                Log.i("", username);
                //DocumentReference userPartner2 = db2.collection("Users").document(partnerUsername).collection("Partner").document("Partner");
                //userPartner2.update("Partner", username);
            }
        });

    }
}
