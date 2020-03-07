package com.example.mobiledevelopmentproject;

import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mobiledevelopmentproject.db.NotesDB;
import com.example.mobiledevelopmentproject.db.NotesDao;
import com.example.mobiledevelopmentproject.model.Note;


import java.util.Date;

public class EditNotes extends AppCompatActivity {
    private EditText inputNote;
    private NotesDao dao;
    public static final String NOTE_EXTRA_Key = "note_id";
    private Note temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.edit_note_activity_toolbar);
        setSupportActionBar(toolbar);


        inputNote = findViewById(R.id.input_note);
        dao = NotesDB.getInstance(this).notesDao();
        if (getIntent().getExtras() != null) {
            int id = getIntent().getExtras().getInt(NOTE_EXTRA_Key, 0);
            temp = dao.getNoteById(id);
            Log.d("EditNotes: onCreate",  temp.toString() );
            inputNote.setText(temp.getNoteText());
        } else inputNote.setFocusable(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edite_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save_note)
            onSaveNote();
        return super.onOptionsItemSelected(item);
    }

    private void onSaveNote() {
        String text = inputNote.getText().toString();

        if (!text.isEmpty()) {
            long date = new Date().getTime(); // get  system time
            // if  exist update els crete new
            if (temp == null) {
                temp = new Note(text, date);
                dao.insertNote(temp); // create new note and inserted to database
            } else {
                temp.setNoteText(text);
                temp.setNoteDate(date);
                dao.updateNote(temp); // change text and date and update note on database
            }

            finish(); // return to the MainActivity
        }

    }
}
