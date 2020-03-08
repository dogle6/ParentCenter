package com.example.mobiledevelopmentproject;

import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.mobiledevelopmentproject.Notes;
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
        final String TAG = "myDB addNoteToUser";
        Log.i("myDB addNoteToUser()", "Started." );
        Log.i(TAG, "Email:  " + email );
        final long date = new Date().getTime(); // get  system time

        db.collection("Users").document(email).collection("Notes").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if( task.isSuccessful() ){
                            boolean found = false;
                            int count = 0;
                            QueryDocumentSnapshot finalDocument = null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.i(TAG, document.getId() + " => " + document.getData());
                                //Log.i(TAG, noteData);

                                String documentData = document.getData().values().toString();
                                documentData = documentData.replace("[", "").replace("]", "");
                                count = count + 1;

                                if( documentData.equals(noteData) ){
                                    Log.i(TAG, documentData );
                                    Log.i(TAG, String.valueOf( documentData.equals(noteData) ));
                                    found = true;
                                }
                                finalDocument = document;



                            }
                            // If not document instance is not found in doa, store it.
                            if( !found ){
                                if( finalDocument != null ){
                                    String finalDocIdString = finalDocument.getId();
                                    char finalDocIdNumberChar= finalDocIdString.charAt( finalDocIdString.length() - 1);
                                    Log.i(TAG, "finalDocIdNumberChar: " + finalDocIdNumberChar);
                                    int finalId = Character.getNumericValue(finalDocIdNumberChar);
                                    Log.i(TAG, "FinalID: " + ( finalId + 1));
                                    final Map<String, Object> noteMap = new HashMap<>();
                                    noteMap.put("Note", noteData);
                                    final String noteTitle = "Note" +  ( finalId + 1);
                                    Log.i(TAG, "Note not found, adding following: title: " +noteTitle + " data: " + noteData );
                                    db.collection("Users").document(email).collection("Notes").document( noteTitle )
                                            .set(noteMap)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    List<Note> list = dao.getNotes();
                                                    Log.i("myDB addNoteToUser()", "List: " + list.toString());

                                                }
                                            });

                                }
                                else{
                                    final Map<String, Object> noteMap = new HashMap<>();
                                    noteMap.put("Note", noteData);
                                    final String noteTitle = "Note"  + 1;
                                    Log.i(TAG, "Note not found, adding following: title: " +noteTitle + " data: " + noteData );
                                    db.collection("Users").document(email).collection("Notes").document( noteTitle )
                                            .set(noteMap)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    List<Note> list = dao.getNotes();
                                                    Log.i("myDB addNoteToUser()", "List: " + list.toString());

                                                }
                                            });

                                }


                            }
                        }
                    }
                });
        Log.i("myDB addNoteToUser()", "Ended." );

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

        Log.i("myDB onSaveNote()", "Started." );

        String text = inputNote.getText().toString();
        mAuth = FirebaseAuth.getInstance();  //FirebaseAuth Initialization
        final FirebaseFirestore db = FirebaseFirestore.getInstance(); // Firebase DB
        FirebaseUser currentUser = mAuth.getCurrentUser(); // Firebase User

        if (!text.isEmpty()) {

            long date = new Date().getTime(); // get  system time
            // if  exist update els crete new
            if (temp == null) {

                // This handle case where addNoteToUser lags behind and doesnt update the DB before
                // the main note activity is loaded.
                List<Note> list = dao.getNotes();
                int size = list.size();

                if( list.size() != 0){

                    Note lastNoteInList = list.get( size  - 1);
                    int id = lastNoteInList.getId();
                    Log.i("myDB onSaveNote()", "Last note in list: " + lastNoteInList );
                    Log.i("myDB onSaveNote()", "size: " + list.size() );
                    Log.i("myDB onSaveNote()", "id: " + id );
                    Note note = new Note(text, date, (id + 1), "Note" + (id + 1) );

                    Log.i("myDB onSaveNote()", "Nre note in list: " + lastNoteInList );
                    Log.i("myDB onSaveNote()", "size: " + list.size() );
                    Log.i("myDB onSaveNote()", "id: " + id );
                    dao.insertNote(note); // change text and date and update note on database
                    list = dao.getNotes();
                    Log.i("myDB onSaveNote()", "List: " + list.toString());
                }
                else{
                    Note note = new Note(text, date, 1, "Note" + 1 );
                    dao.insertNote(note); // change text and date and update note on database
                    list = dao.getNotes();
                    Log.i("myDB onSaveNote()", "List: " + list.toString());
                }

                addNoteToUser(currentUser.getEmail().toString(), db, text);


            } else {
                Log.i("myDB editing note ()", temp.getFBID() );
                temp.setNoteText(text);
                temp.setNoteDate(date);
                dao.updateNote(temp); // change text and date and update note on database
                Map<String, Object> noteMap = new HashMap<>();
                noteMap.put("Note", text);
                db.collection("Users").document(currentUser.getEmail().toString() ).collection("Notes").document(temp.getFBID()).set(noteMap);

            }
            Log.i("myDB onSaveNote()", "Ended." );

            finish(); // return to the MainActivity
        }

    }
}
