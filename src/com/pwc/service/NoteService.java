package com.pwc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhixiong
 * Date: 7/14/12
 * Time: 4:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class NoteService {
    List<Note> notes = new ArrayList<Note>();

    public void addNote(String content) {
        notes.add(createNote(content));
    }

    private Note createNote(String content) {
        return new Note(content);
    }

     public int getNotesNumber(){
        return  notes.size();
     }

}
