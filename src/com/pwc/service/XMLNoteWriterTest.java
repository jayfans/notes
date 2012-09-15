package com.pwc.service;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: zhixiong
 * Date: 9/14/12
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class XMLNoteWriterTest {

    @Test
    public void testWrite() throws Exception {

        String filePath = "./testnotes.txt";
        //delete the file
        File file=new File(filePath) ;
        if (file.exists()) {
            file.delete();
        }
        XMLNoteWriter xmlNoteWriter = new XMLNoteWriter(filePath);
        xmlNoteWriter.write(new Note("New Line"));
        String newLine = FileExt.readLastLine(filePath, null);
        assertEquals("New Line\n", newLine);
    }

}
