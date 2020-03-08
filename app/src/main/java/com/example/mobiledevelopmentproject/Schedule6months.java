package com.example.mobiledevelopmentproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Schedule6months extends MainActivity {

    Button m3_button, m6_button, m12_button, home_button;
    Button C10_button, C11_button, C12_button, C13_button, C14_button, C15_button, C16_button, C17_button, C18_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule6months_activity);

        //Schedule Age Buttons
        m3_button = (Button)findViewById(R.id.m3_button);
        m6_button = (Button)findViewById(R.id.m6_button);
        m12_button = (Button)findViewById(R.id.m12_button);

        //Column of Time Buttons
        C10_button = (Button)findViewById(R.id.C10_button);
        C11_button = (Button)findViewById(R.id.C11_button);
        C12_button = (Button)findViewById(R.id.C12_button);
        C13_button = (Button)findViewById(R.id.C13_button);
        C14_button = (Button)findViewById(R.id.C14_button);
        C15_button = (Button)findViewById(R.id.C15_button);
        C16_button = (Button)findViewById(R.id.C16_button);
        C17_button = (Button)findViewById(R.id.C17_button);
        C18_button = (Button)findViewById(R.id.C18_button);

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
        C10_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule6months.this);
                adb.setTitle("7:00");
                adb.setMessage("Your baby may wake up at 6:00 or sleep til 8:00! Adjust your schedule accordingly!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C11_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule6months.this);
                adb.setTitle("9:00");
                adb.setMessage("Before their first nap, try some tummy time to build those core muscles!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C12_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule6months.this);
                adb.setTitle("11:00");
                adb.setMessage("Some babies backtrack in their milk intake. Do not be alarmed if they don't finish their bottle.");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C13_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule6months.this);
                adb.setTitle("1:00");
                adb.setMessage("The second nap is usually the longest nap! Take advantage!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C14_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule6months.this);
                adb.setTitle("3:00");
                adb.setMessage("Remember, babies do not drink plain water yet!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C15_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule6months.this);
                adb.setTitle("4:30");
                adb.setMessage("This nap will be less than an hour! And they may wake up grumpy!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C16_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule6months.this);
                adb.setTitle("6:00");
                adb.setMessage("It's okay to give them a little extra milk at night!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C17_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule6months.this);
                adb.setTitle("6:30");
                adb.setMessage("If it's not bath night, then rock them while you read them a story!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C18_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule6months.this);
                adb.setTitle("7:00");
                adb.setMessage("It's okay to put them down while they are still awake! They need to learn how to fall asleep on their own!");
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