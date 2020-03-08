package com.example.mobiledevelopmentproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Schedule3months extends MainActivity {

    Button m3_button, m6_button, m12_button, home_button;
    Button C1_button, C2_button, C3_button, C4_button, C5_button, C6_button, C7_button, C8_button, C9_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule3months_activity);

        //Schedule Age Buttons
        m3_button = (Button)findViewById(R.id.m3_button);
        m6_button = (Button)findViewById(R.id.m6_button);
        m12_button = (Button)findViewById(R.id.m12_button);

        //Column of Time Buttons
        C1_button = (Button)findViewById(R.id.C1_button);
        C2_button = (Button)findViewById(R.id.C2_button);
        C3_button = (Button)findViewById(R.id.C3_button);
        C4_button = (Button)findViewById(R.id.C4_button);
        C5_button = (Button)findViewById(R.id.C5_button);
        C6_button = (Button)findViewById(R.id.C6_button);
        C7_button = (Button)findViewById(R.id.C7_button);
        C8_button = (Button)findViewById(R.id.C8_button);
        C9_button = (Button)findViewById(R.id.C9_button);

        //Home Buttons
        home_button = (Button)findViewById(R.id.home_button);

        final Intent m3intent = new Intent(this, Schedule3months.class);
        final Intent m6intent = new Intent(this, Schedule6months.class);
        final Intent m12intent = new Intent(this, Schedule12months.class);
        final Intent homeintent = new Intent(this, MainActivity.class);

        //Age Buttons
        m3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tag", "onClick");
                startActivity(m3intent);
            }
        });

        m6_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tag", "onClick");
                startActivity(m6intent);
            }
        });

        m12_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tag", "onClick");
                startActivity(m12intent);
            }
        });

        //Column Time Buttons
        C1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule3months.this);
                adb.setTitle("7:00");
                adb.setMessage("Do not be alarmed if your newborn sleeps in!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule3months.this);
                adb.setTitle("9:00");
                adb.setMessage("After eating, your newborn will be ready for a nap! Even if they just slept all night!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule3months.this);
                adb.setTitle("11:00");
                adb.setMessage("All newborns are different! Yours may eat more than 3-6oz! It's okay!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule3months.this);
                adb.setTitle("1:00");
                adb.setMessage("Take advantage of these naps! You need rest too! Or bottles need cleaned...");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C5_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule3months.this);
                adb.setTitle("3:00");
                adb.setMessage("Newborns like to snack just like the rest of us!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C6_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule3months.this);
                adb.setTitle("4:30");
                adb.setMessage("This will be the shortest nap! Don't be alarmed if they skip it!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C7_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule3months.this);
                adb.setTitle("6:00");
                adb.setMessage("This should be where they eat the most! A full tummy will help them sleep longer!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C8_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule3months.this);
                adb.setTitle("6:30");
                adb.setMessage("Newborns don't need bathed every night! Shoot for every 2-3 nights. It will help their skin.");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C9_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule3months.this);
                adb.setTitle("7:00");
                adb.setMessage("Its finally bed time! Remember, swaddle them tight and nothing goes in the bassinet with them!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        //Home Button
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tag", "onClick");
                startActivity(homeintent);
            }
        });

    }

}
