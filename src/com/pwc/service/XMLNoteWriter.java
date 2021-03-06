package com.pwc.service;

/**
 * Created with IntelliJ IDEA.
 * User: zhixiong
 * Date: 9/9/12
 * Time: 6:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class XMLNoteWriter implements INoteWriter {

    private final static String defaultFilePath = "notes.txt";
    private String filePath;

    public XMLNoteWriter(String filePath) {
        this.filePath = filePath;
    }

    public XMLNoteWriter() {
        this.filePath = defaultFilePath;
    }

    @Override
    public void write(Note note) {
        FileExt.appendLine(filePath, note.getContent());
    }

    @Override
    public int getNoteNumber() {
        return FileExt.getLineNumber(this.filePath);
    }

    @Override
    public String[] getNotes(int number) {
        int total = FileExt.getLineNumber(filePath);
        if (number > total)
            return FileExt.getLines(filePath, 0, total - 1);
        else
            return FileExt.getLines(filePath, total - number, total - 1);
    }

}
