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

public class Schedule6months extends Home {

    Button m3_button, m6_button, m12_button, home_button;
    Button C10_button, C11_button, C12_button, C13_button, C14_button, C15_button, C16_button, C17_button, C18_button;
    TextView C10_view, C11_view, C12_view, C13_view, C14_view, C15_view, C16_view, C17_view, C18_view;
    AlertDialog dialog, dialog2, dialog3, dialog4, dialog5, dialog6, dialog7, dialog8, dialog9;
    EditText editText, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9;
    String str;
    SharedPreferences schedule_pref_6m;
    String[] defScheduleText = {"Breakfast (4-8oz formula/milk)","First Nap","Lunch (4-8oz formula/milk)",
            "Second Nap","Snack (4 oz of formula/milk)","Third Nap","Dinner (4-8oz formula/milk)","Bath","Bed"};

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

        //Column of Textviews
        C10_view = findViewById(R.id.textView2);
        C11_view = findViewById(R.id.textView3);
        C12_view = findViewById(R.id.textView4);
        C13_view = findViewById(R.id.textView5);
        C14_view = findViewById(R.id.textView6);
        C15_view = findViewById(R.id.textView7);
        C16_view = findViewById(R.id.textView8);
        C17_view = findViewById(R.id.textView9);
        C18_view = findViewById(R.id.textView10);

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

        schedule_pref_6m = getPreferences(Context.MODE_PRIVATE);
        C10_view.setText(schedule_pref_6m.getString("strKey0", defScheduleText[0]));
        C11_view.setText(schedule_pref_6m.getString("strKey1", defScheduleText[1]));
        C12_view.setText(schedule_pref_6m.getString("strKey2", defScheduleText[2]));
        C13_view.setText(schedule_pref_6m.getString("strKey3", defScheduleText[3]));
        C14_view.setText(schedule_pref_6m.getString("strKey4", defScheduleText[4]));
        C15_view.setText(schedule_pref_6m.getString("strKey5", defScheduleText[5]));
        C16_view.setText(schedule_pref_6m.getString("strKey6", defScheduleText[6]));
        C17_view.setText(schedule_pref_6m.getString("strKey7", defScheduleText[7]));
        C18_view.setText(schedule_pref_6m.getString("strKey8", defScheduleText[8]));

        editSchedule(C10_view, dialog, editText, 0);
        editSchedule(C11_view, dialog2, editText2, 1);
        editSchedule(C12_view, dialog3, editText3, 2);
        editSchedule(C13_view, dialog4, editText4, 3);
        editSchedule(C14_view, dialog5, editText5, 4);
        editSchedule(C15_view, dialog6, editText6, 5);
        editSchedule(C16_view, dialog7, editText7, 6);
        editSchedule(C17_view, dialog8, editText8, 7);
        editSchedule(C18_view, dialog9, editText9, 8);

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

    public void editSchedule(final TextView textView, final AlertDialog dialog, final EditText editText, final int j){
        dialog.setTitle("Edit Text");
        dialog.setView(editText);
        final String[] strKeys = {"strKey0","strKey1","strKey2","strKey3","strKey4","strKey5","strKey6","strKey7","strKey8"};
        final SharedPreferences.Editor editor = schedule_pref_6m.edit();

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