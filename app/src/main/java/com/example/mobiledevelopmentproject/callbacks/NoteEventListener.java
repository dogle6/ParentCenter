package com.example.mobiledevelopmentproject.callbacks;


import com.example.mobiledevelopmentproject.model.Note;

public interface NoteEventListener {

    void onNoteClick(Note note);


    void onNoteLongClick(Note note);
}
