package com.pwc.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: zhixiong
 * Date: 9/16/12
 * Time: 12:47 PM
 * To change this templateFile use File | Settings | File Templates.
 */
public class FileExtTest {

    private String templateFile = "./templateFile.txt";
    private String newFile = "./copytemplateFile.txt";

    @Before
    public void setUp() {
        newFile = FileExt.copy(templateFile, newFile);
    }

    @After
    public void cleanUp() {
        FileExt.delete(newFile);
    }

    @Test
    public void getLinesTest() {
        String[] expectLines = {"Line1", "Line2", "Line3"};

        String[] actualLines = FileExt.getLines(newFile, 1, 3);
        assertEquals(expectLines[0],actualLines[0]);
        assertEquals(expectLines[1],actualLines[1]);
        assertEquals(expectLines[2],actualLines[2]);
    }
}
