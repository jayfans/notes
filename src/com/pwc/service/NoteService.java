package com.pwc.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhixiong
 * Date: 7/14/12
 * Time: 4:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class NoteService {
    private INoteWriter noteWriter;

    public NoteService() {
        noteWriter = new XMLNoteWriter();
    }

    public void addNote(String content) {
        Note note = Note.create(content);
        noteWriter.write(note);
        return;
    }


    public int getNotesNumber() {
        return noteWriter.getNoteNumber();
    }

    public String[] getLatest(int number){
        return noteWriter.getNotes(number);
    }

    public String getVersion(){
        return "3.0";
    }


}
