package com.example.mobiledevelopmentproject.callbacks;


import com.example.mobiledevelopmentproject.model.Note;

public interface ScheduleEventListener {

    void onNoteClick(Note note);


    void onNoteLongClick(Note note);
}
