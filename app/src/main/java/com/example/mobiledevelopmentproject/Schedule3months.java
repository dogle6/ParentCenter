package com.example.mobiledevelopmentproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Schedule3months extends Home {

    Button m3_button, m6_button, m12_button, home_button;
    Button C1_button, C2_button, C3_button, C4_button, C5_button, C6_button, C7_button, C8_button, C9_button;
    TextView C1_view, C2_view, C3_view, C4_view, C5_view, C6_view, C7_view, C8_view, C9_view;
    AlertDialog dialog, dialog2, dialog3, dialog4, dialog5, dialog6, dialog7, dialog8, dialog9;
    EditText editText, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9;
    String str;
    SharedPreferences schedule_pref_3m;
    String[] defScheduleText = {"Breakfast (3-6oz formula/milk)","First Nap","Lunch (3-6oz formula/milk)",
            "Second Nap","Snack (3 oz of formula/milk)","Third Nap","Dinner (3-6oz formula/milk)","Bath","Bed"};

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

        //Column of Textviews
        C1_view = findViewById(R.id.textView2);
        C2_view = findViewById(R.id.textView3);
        C3_view = findViewById(R.id.textView4);
        C4_view = findViewById(R.id.textView5);
        C5_view = findViewById(R.id.textView6);
        C6_view = findViewById(R.id.textView7);
        C7_view = findViewById(R.id.textView8);
        C8_view = findViewById(R.id.textView9);
        C9_view = findViewById(R.id.textView10);

        dialog = new AlertDialog.Builder(this).create();
        dialog2 = new AlertDialog.Builder(this).create();
        dialog3 = new AlertDialog.Builder(this).create();
        dialog4 = new AlertDialog.Builder(this).create();
        dialog5 = new AlertDialog.Builder(this).create();
        dialog6 = new AlertDialog.Builder(this).create();
        dialog7 = new AlertDialog.Builder(this).create();
        dialog8 = new AlertDialog.Builder(this).create();
        dialog9 = new AlertDialog.Builder(this).create();

        editText = new EditText(this);
        editText2 = new EditText(this);
        editText3 = new EditText(this);
        editText4 = new EditText(this);
        editText5 = new EditText(this);
        editText6 = new EditText(this);
        editText7 = new EditText(this);
        editText8 = new EditText(this);
        editText9 = new EditText(this);

        schedule_pref_3m = getPreferences(Context.MODE_PRIVATE);
        C1_view.setText(schedule_pref_3m.getString("strKey0", defScheduleText[0]));
        C2_view.setText(schedule_pref_3m.getString("strKey1", defScheduleText[1]));
        C3_view.setText(schedule_pref_3m.getString("strKey2", defScheduleText[2]));
        C4_view.setText(schedule_pref_3m.getString("strKey3", defScheduleText[3]));
        C5_view.setText(schedule_pref_3m.getString("strKey4", defScheduleText[4]));
        C6_view.setText(schedule_pref_3m.getString("strKey5", defScheduleText[5]));
        C7_view.setText(schedule_pref_3m.getString("strKey6", defScheduleText[6]));
        C8_view.setText(schedule_pref_3m.getString("strKey7", defScheduleText[7]));
        C9_view.setText(schedule_pref_3m.getString("strKey8", defScheduleText[8]));

        editSchedule(C1_view, dialog, editText, 0);
        editSchedule(C2_view, dialog2, editText2, 1);
        editSchedule(C3_view, dialog3, editText3, 2);
        editSchedule(C4_view, dialog4, editText4, 3);
        editSchedule(C5_view, dialog5, editText5, 4);
        editSchedule(C6_view, dialog6, editText6, 5);
        editSchedule(C7_view, dialog7, editText7, 6);
        editSchedule(C8_view, dialog8, editText8, 7);
        editSchedule(C9_view, dialog9, editText9, 8);

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

    public void editSchedule(final TextView textView, final AlertDialog dialog, final EditText editText, final int j){
        dialog.setTitle("Edit Text");
        dialog.setView(editText);
        final String[] strKeys = {"strKey0","strKey1","strKey2","strKey3","strKey4","strKey5","strKey6","strKey7","strKey8"};
        final SharedPreferences.Editor editor = schedule_pref_3m.edit();

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE TEXT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                str = editText.getText().toString();
                editor.putString(strKeys[j], str);
                editor.commit();
                textView.setText(str);
                //textView.setText(editText.getText());
            }
        });

        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                editText.setText(textView.getText());
                dialog.show();
            }
        });
    }

}
