package com.example.mobiledevelopmentproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Schedule12months extends MainActivity {

    Button m3_button, m6_button, m12_button, home_button;
    Button C19_button, C20_button, C21_button, C22_button, C23_button, C24_button, C25_button, C26_button, C27_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule12months_activity);

        //Schedule Age Buttons
        m3_button = (Button)findViewById(R.id.m3_button);
        m6_button = (Button)findViewById(R.id.m6_button);
        m12_button = (Button)findViewById(R.id.m12_button);

        //Column of Time Buttons
        C19_button = (Button)findViewById(R.id.C19_button);
        C20_button = (Button)findViewById(R.id.C20_button);
        C21_button = (Button)findViewById(R.id.C21_button);
        C22_button = (Button)findViewById(R.id.C22_button);
        C23_button = (Button)findViewById(R.id.C23_button);
        C24_button = (Button)findViewById(R.id.C24_button);
        C25_button = (Button)findViewById(R.id.C25_button);
        C26_button = (Button)findViewById(R.id.C26_button);
        C27_button = (Button)findViewById(R.id.C27_button);

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
        C19_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule12months.this);
                adb.setTitle("7:00");
                adb.setMessage("Pureed fruits and veggies are the perfect introductory food for your baby! Make sure you mix it up!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C20_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule12months.this);
                adb.setTitle("9:00");
                adb.setMessage("Depending on their age, some babies may want their first nap pushed back a bit!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C21_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule12months.this);
                adb.setTitle("11:30");
                adb.setMessage("A mushy banana, applesauce, or avocado makes for a great side during lunch!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C22_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule12months.this);
                adb.setTitle("2:00");
                adb.setMessage("Make sure your baby gets ample play time! That is how they learn!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C23_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule12months.this);
                adb.setTitle("3:30");
                adb.setMessage("They do not need milk for a snack, but would love some more pureed food!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C24_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule12months.this);
                adb.setTitle("5:00");
                adb.setMessage("Oatmeal or yogurt is another great snack choice!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C25_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule12months.this);
                adb.setTitle("6:30");
                adb.setMessage("Their biggest meal should be dinner, so they will sleep as long as possible!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C26_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule12months.this);
                adb.setTitle("7:30");
                adb.setMessage("Baths can become more regular now! Especially if they get messy during dinner!");
                adb.setNegativeButton("Cancel", null);
                AlertDialog confirmDialog = adb.create();
                confirmDialog.show();
            }
        });

        C27_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens up a window
                AlertDialog.Builder adb = new AlertDialog.Builder(Schedule12months.this);
                adb.setTitle("8:00");
                adb.setMessage("Always try and read a story before bed. Even if it's only a page or two!");
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
