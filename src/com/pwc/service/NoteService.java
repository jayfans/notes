package com.pwc.service;

import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public String[] getLatest(int number) {
        return noteWriter.getNotes(number);
    }

    public String getVersion() {
        return "3.0";
    }

    public void sendNotes(int top) {
        String[] notes = getLatest(top);
        StringBuilder content = new StringBuilder();
        for (String note : notes) {
            content.append(note);
        }
        try {
            MailExt.sendSimpleMessage(content.toString());
        } catch (EmailException e) {

            Logger logger = Logger.getLogger("main");
            String logPath = "logs.txt";
            FileHandler fileHandler = null;
            try {
                fileHandler = new FileHandler(logPath);
            } catch (IOException e1) {

            }

            logger.addHandler(fileHandler);
              logger.log(Level.SEVERE, content.toString());
            logger.log(Level.SEVERE, getStackTrace(e));
        }
    }

    public static String getStackTrace(Throwable t)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
}
