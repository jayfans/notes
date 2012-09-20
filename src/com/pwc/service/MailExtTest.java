package com.pwc.service;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: zhixiong
 * Date: 9/20/12
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class MailExtTest {
    @Test
    public void testSendMultiMessage() throws Exception {
        MailExt.sendMultiMessage("This multipart email was sent by jenkins automatically when running unit testing!");
    }

    @Test
    public void testSendSimpleMessage() throws Exception {
        MailExt.sendSimpleMessage("This simple email was sent by jenkins automatically when running unit testing!");
    }
}
