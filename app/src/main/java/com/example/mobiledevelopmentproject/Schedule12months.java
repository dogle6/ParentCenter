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

import org.w3c.dom.Text;

public class Schedule12months extends Home {

    Button m3_button, m6_button, m12_button, home_button;
    Button C19_button, C20_button, C21_button, C22_button, C23_button, C24_button, C25_button, C26_button, C27_button;
    TextView C19_view, C20_view, C21_view, C22_view, C23_view, C24_view, C25_view, C26_view, C27_view;
    AlertDialog dialog, dialog2, dialog3, dialog4, dialog5, dialog6, dialog7, dialog8, dialog9;
    EditText editText, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9;
    String str;
    SharedPreferences schedule_pref_12m;
    String[] defScheduleText = {"Breakfast (6-8oz milk and jar of food)","First Nap","Lunch (6-8oz of milk and jar of food)",
            "Second Nap","Snack 1 (jar of food)","Snack 2 (soft fruit)","Dinner (6-8oz milk and jar of food)","Bath","Bed"};

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

        //Column of Textviews
        C19_view = findViewById(R.id.textView2);
        C20_view = findViewById(R.id.textView3);
        C21_view = findViewById(R.id.textView4);
        C22_view = findViewById(R.id.textView5);
        C23_view = findViewById(R.id.textView6);
        C24_view = findViewById(R.id.textView7);
        C25_view = findViewById(R.id.textView8);
        C26_view = findViewById(R.id.textView9);
        C27_view = findViewById(R.id.textView10);

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

        schedule_pref_12m = getPreferences(Context.MODE_PRIVATE);
        C19_view.setText(schedule_pref_12m.getString("strKey0", defScheduleText[0]));
        C20_view.setText(schedule_pref_12m.getString("strKey1", defScheduleText[1]));
        C21_view.setText(schedule_pref_12m.getString("strKey2", defScheduleText[2]));
        C22_view.setText(schedule_pref_12m.getString("strKey3", defScheduleText[3]));
        C23_view.setText(schedule_pref_12m.getString("strKey4", defScheduleText[4]));
        C24_view.setText(schedule_pref_12m.getString("strKey5", defScheduleText[5]));
        C25_view.setText(schedule_pref_12m.getString("strKey6", defScheduleText[6]));
        C26_view.setText(schedule_pref_12m.getString("strKey7", defScheduleText[7]));
        C27_view.setText(schedule_pref_12m.getString("strKey8", defScheduleText[8]));

        editSchedule(C19_view, dialog, editText, 0);
        editSchedule(C20_view, dialog2, editText2, 1);
        editSchedule(C21_view, dialog3, editText3, 2);
        editSchedule(C22_view, dialog4, editText4, 3);
        editSchedule(C23_view, dialog5, editText5, 4);
        editSchedule(C24_view, dialog6, editText6, 5);
        editSchedule(C25_view, dialog7, editText7, 6);
        editSchedule(C26_view, dialog8, editText8, 7);
        editSchedule(C27_view, dialog9, editText9, 8);

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

    public void editSchedule(final TextView textView, final AlertDialog dialog, final EditText editText, final int j){
        dialog.setTitle("Edit Text");
        dialog.setView(editText);
        final String[] strKeys = {"strKey0","strKey1","strKey2","strKey3","strKey4","strKey5","strKey6","strKey7","strKey8"};
        final SharedPreferences.Editor editor = schedule_pref_12m.edit();

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
