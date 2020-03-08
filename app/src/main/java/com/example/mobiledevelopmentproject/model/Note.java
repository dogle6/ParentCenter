package com.example.mobiledevelopmentproject.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;



@Entity(tableName = "notes")

public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id; // default value

    @ColumnInfo(name = "FBID")
    private String FBID;

    @ColumnInfo(name = "text")
    private String noteText;

    @ColumnInfo(name = "date")
    private long noteDate;

    @Ignore // we don't want to store this value on database so ignore it
    private boolean checked = false;


    public Note() {
    }

    public Note(String noteText, long noteDate, int id, String fbId) {
        this.noteText = noteText;
        this.noteDate = noteDate;
        this.id = id;
        this.FBID = fbId;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }


    public String getFBID() {
        return FBID;
    }
    public void setFBID( String fbid) { this.FBID = fbid; }


    public long getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(long noteDate) {
        this.noteDate = noteDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", noteDate=" + noteDate +
                '}';
    }
}
