package com.example.mobiledevelopmentproject;

import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mobiledevelopmentproject.db.NotesDB;
import com.example.mobiledevelopmentproject.db.NotesDao;
import com.example.mobiledevelopmentproject.model.Note;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditNotes extends AppCompatActivity {
    private EditText inputNote;
    private NotesDao dao;
    public static final String NOTE_EXTRA_Key = "note_id";
    private Note temp;
    private FirebaseAuth mAuth;


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


    private void addNoteToUser(final String email, final FirebaseFirestore db, final String noteData){
        final String TAG = "addNoteToUser";
        Log.i(TAG, "Email:  " + email );
        final long date = new Date().getTime(); // get  system time

        db.collection("Users").document(email).collection("Notes").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if( task.isSuccessful() ){
                            boolean found = false;
                            int count = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.i(TAG, document.getId() + " => " + document.getData());
                                Log.i(TAG, noteData);

                                String documentData = document.getData().values().toString();
                                documentData = documentData.replace("[", "").replace("]", "");
                                count = count + 1;

                                if( documentData.equals(noteData) ){
                                    Log.i(TAG, documentData );
                                    Log.i(TAG, String.valueOf( documentData.equals(noteData) ));
                                    found = true;
                                }




                            }
                            // If not document instance is not found in doa, store it.
                            if( !found ){
                                Log.i(TAG, "Note not found" );
                                Map<String, Object> noteMap = new HashMap<>();
                                noteMap.put("Note", noteData);
                                String noteTitle = "Note" +  (count + 1);
                                db.collection("Users").document(email).collection("Notes").document( noteTitle ).set(noteMap);
                                Note temp = new Note(noteData, date, count, noteTitle );
                                dao.insertNote(temp);
                            }
                        }
                    }
                });

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
        mAuth = FirebaseAuth.getInstance();  //FirebaseAuth Initialization
        final FirebaseFirestore db = FirebaseFirestore.getInstance(); // Firebase DB
        FirebaseUser currentUser = mAuth.getCurrentUser(); // Firebase User

        if (!text.isEmpty()) {
            long date = new Date().getTime(); // get  system time
            // if  exist update els crete new
            if (temp == null) {
                addNoteToUser(currentUser.getEmail().toString(), db, text);
            } else {
                temp.setNoteText(text);
                temp.setNoteDate(date);
                dao.updateNote(temp); // change text and date and update note on database
                db.collection("Users").document(currentUser.getEmail().toString() ).collection("Notes").document(temp.getFBID()).set(text);

            }

            finish(); // return to the MainActivity
        }

    }
}
