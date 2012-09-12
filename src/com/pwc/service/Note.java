package com.pwc.service;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: zhixiong
 * Date: 7/14/12
 * Time: 4:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class Note {
    private String content;
    private String id;

    public Note(String content) {
        this.id= UUID.randomUUID().toString();
        this.content=content;
    }


    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public static Note create(String content) {
         return new Note(content);
    }
}
