package com.example.mobiledevelopmentproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.ActionMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledevelopmentproject.adapters.NotesAdapter;
import com.example.mobiledevelopmentproject.callbacks.MainActionModeCallback;
import com.example.mobiledevelopmentproject.db.NotesDB;
import com.example.mobiledevelopmentproject.db.NotesDao;
import com.example.mobiledevelopmentproject.model.Note;
import com.example.mobiledevelopmentproject.utils.NoteUtils;
import com.example.mobiledevelopmentproject.callbacks.NoteEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.mobiledevelopmentproject.EditNotes.NOTE_EXTRA_Key;

public class Notes extends AppCompatActivity implements NoteEventListener, Drawer.OnDrawerItemClickListener {
    public static final String THEME_Key = "app_theme";
    public static final String APP_PREFERENCES = "notepad_settings";
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ArrayList<Note> notes;
    private NotesAdapter adapter;
    private NotesDao dao;
    private MainActionModeCallback actionModeCallback;
    private int chackedCount = 0;
    private FloatingActionButton fab;
    private Button button_home;
    private SharedPreferences settings;
    private FirebaseAuth mAuth;
    private String email;
    private int theme;
    // swipe to right or to left te delete
    private ItemTouchHelper swipeToDeleteHelper = new ItemTouchHelper(
            new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    if (notes != null) {
                        // get swiped note
                        Note swipedNote = notes.get(viewHolder.getAdapterPosition());
                        if (swipedNote != null) {
                            swipeToDelete(swipedNote, viewHolder);

                        }

                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_main);


        // init recyclerView
        recyclerView = findViewById(R.id.notes_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // init fab Button
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddNewNote();
            }
        });

        button_home = (Button)findViewById(R.id.home_button);

        //FirebaseAuth Initialization
        mAuth = FirebaseAuth.getInstance();

        // Firebase DB
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Firebase User
        FirebaseUser currentUser = mAuth.getCurrentUser();

        dao = NotesDB.getInstance(this).notesDao();




    }

    private int getNotesForUser(final String email, FirebaseFirestore db){
        final String TAG = " myDB getNotesForUser";
        Log.i("myDB getUserNotes()", "Started." );

        Log.i(TAG, "User:  " + email );
        final long date = new Date().getTime(); // get  system time

        db.collection("Users").document(email).collection("Notes").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if( task.isSuccessful() ){
                            int count = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.i(TAG, document.getId() + " => " + document.getData());

                                //Get Note document string and remove brackets
                                String documentData = document.getData().values().toString();
                                documentData = documentData.replace("[", "").replace("]", "");

                                //Get DOA note instance and check if its already stored
                                List<Note> list = dao.getNotes();
                                boolean found = false;
                                for( int i = 0; i < list.size(); i++ ){
                                    list.contains(documentData);
                                    if( list.get(i).getNoteText().equals(documentData) ){
                                        //Log.i(TAG, list.get(i).getNoteText() );
                                        //Log.i(TAG, String.valueOf( list.get(i).getNoteText().equals(documentData) ));
                                        found = true;
                                    }


                                }
                                // If not document instance is not found in doa, store it.
                                if( !found ){
                                    count = count + 1;
                                    String FBID = document.getId();
                                    Note temp = new Note(documentData, date, count, FBID );
                                    dao.insertNote(temp);
                                    list = dao.getNotes();
                                    Log.i("myDB getUserNotes()", "List: " + list.toString());

                                }

                                loadNotes();
                            }


                        }
                    }
                });

        Log.i("myDB getUserNotes()", "Ended." );
        return 1;
    }
    public void loadNotes() {
        Log.i("myDB loadNotes()", "Started.");
        this.notes = new ArrayList<>();
        List<Note> list = dao.getNotes();// get All notes from DataBase
        Log.i("Notes", list.toString());
        this.notes.addAll(list);
        this.adapter = new NotesAdapter(this, this.notes);
        // set listener to adapter
        this.adapter.setListener((com.example.mobiledevelopmentproject.callbacks.NoteEventListener) this);
        this.recyclerView.setAdapter(adapter);
        showEmptyView();
        // add swipe helper to recyclerView

        swipeToDeleteHelper.attachToRecyclerView(recyclerView);
        Log.i("myDB loadNotes()", "Ended.");

    }


    private void showEmptyView() {
        if (notes.size() == 0) {
            this.recyclerView.setVisibility(View.GONE);
            findViewById(R.id.empty_notes_view).setVisibility(View.VISIBLE);

        } else {
            this.recyclerView.setVisibility(View.VISIBLE);
            findViewById(R.id.empty_notes_view).setVisibility(View.GONE);
        }
    }


    private void onAddNewNote() {
        startActivity(new Intent(this, EditNotes.class));

    }

    @Override
    protected void onResume() {
        dao.deleteNotes();
        Log.i("myDB OnResume", "started");
        super.onResume();
        final FirebaseFirestore db = FirebaseFirestore.getInstance(); // Firebase
        FirebaseUser currentUser = mAuth.getCurrentUser(); // Firebase User
        getNotesForUser(currentUser.getEmail().toString(), db);
        loadNotes();
    }

    @Override
    public void onNoteClick(Note note) {
        Intent edit = new Intent(this, EditNotes.class);
        edit.putExtra(NOTE_EXTRA_Key, note.getId());
        startActivity(edit);

    }

    @Override
    public void onNoteLongClick(Note note) {
        note.setChecked(true);
        chackedCount = 1;
        adapter.setMultiCheckMode(true);

        // set new listener to adapter intend off MainActivity listener that we have implement
        adapter.setListener(new NoteEventListener() {
            @Override
            public void onNoteClick(Note note) {
                note.setChecked(!note.isChecked()); // inverse selected
                if (note.isChecked())
                    chackedCount++;
                else chackedCount--;

                if (chackedCount > 1) {
                    actionModeCallback.changeShareItemVisible(false);
                } else actionModeCallback.changeShareItemVisible(true);

                if (chackedCount == 0) {
                    //  finish multi select mode wen checked count =0
                    actionModeCallback.getAction().finish();
                }

                actionModeCallback.setCount(chackedCount + "/" + notes.size());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNoteLongClick(Note note) {

            }
        });

        actionModeCallback = new MainActionModeCallback() {
            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_delete_notes)
                    onDeleteMultiNotes();

                actionMode.finish();
                return false;
            }

        };

        // start action mode
        startActionMode(actionModeCallback);
        // hide fab button
        fab.setVisibility(View.GONE);
        actionModeCallback.setCount(chackedCount + "/" + notes.size());
    }

    public void removeNoteFromUser(String email, String id, FirebaseFirestore db){
        db.collection("Users").document(email).collection("Notes").document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });

    }

    private void onDeleteMultiNotes() {

        List<Note> chackedNotes = adapter.getCheckedNotes();
        if (chackedNotes.size() != 0) {
            for (Note note : chackedNotes) {
                dao.deleteNote(note);
            }
            // refresh Notes
            loadNotes();
            Toast.makeText(this, chackedNotes.size() + " Note(s) Delete successfully !", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "No Note(s) selected", Toast.LENGTH_SHORT).show();

        //adapter.setMultiCheckMode(false);
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);

        adapter.setMultiCheckMode(false); // uncheck the notes
        adapter.setListener(this); // set back the old listener
        fab.setVisibility(View.VISIBLE);
    }

    private void swipeToDelete(final Note swipedNote, final RecyclerView.ViewHolder viewHolder) {

        final FirebaseFirestore db = FirebaseFirestore.getInstance(); // Firebase
        final FirebaseUser currentUser = mAuth.getCurrentUser(); // Firebase User

        new AlertDialog.Builder(Notes.this)
                .setMessage("Delete Note?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.deleteNote(swipedNote);
                        notes.remove(swipedNote);
                        Log.i( "Delete", String.valueOf(swipedNote.getId()) );
                        removeNoteFromUser( currentUser.getEmail().toString(), swipedNote.getFBID(), db );
                        adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                        showEmptyView();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        recyclerView.getAdapter().notifyItemChanged(viewHolder.getAdapterPosition());


                    }
                })
                .setCancelable(false)
                .create().show();

    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        return false;
    }

    public void onClick (View view){
        switch (view.getId()) {

            case R.id.button_home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}



