package com.pwc.service;

import org.apache.commons.mail.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MailExt {

    private static String contactBook = "contactbook.txt";

    public static void sendMultiMessage(String content) throws EmailException {
        MultiPartEmail email = new MultiPartEmail();
        setUp(email);
        addContacts(email);
        addAttachments(email, new String[]{contactBook});
        email.setMsg(content);
        email.send();
    }

    public static void sendSimpleMessage(String content) throws EmailException {
        SimpleEmail email = new SimpleEmail();
        setUp(email);
        addContacts(email);
        send(content, email);
    }


    private static void addAttachments(MultiPartEmail email, String[] attachmentPaths) throws EmailException {
        List<EmailAttachment> attachments = new ArrayList<EmailAttachment>();
        for (int i = 0; i < attachmentPaths.length; i++) {
            EmailAttachment attachment = new EmailAttachment();

            if (attachmentPaths[i].indexOf("http") == -1) {
                attachment.setPath(attachmentPaths[i]);
            } else {
                try {
                    attachment.setURL(new URL(attachmentPaths[i]));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachments.add(attachment);
        }
        for (int a = 0; a < attachments.size(); a++) {
            email.attach(attachments.get(a));
        }
    }


    private static void send(String content, SimpleEmail email) throws EmailException {
        email.setMsg(content);
        email.send();
    }

    private static void setUp(Email email) throws EmailException {
        email.setHostName("smtp.gmail.com");
        email.setTLS(true);
        email.setSSL(Boolean.TRUE);
        email.setSslSmtpPort("465");
        email.setCharset("utf-8");
        email.setFrom("zhixiong0001@gmail.com");
        email.setAuthentication("zhixiong0001", "dearlele");
        email.setSubject("News From Notes Book " + getCurrentDate());
    }

    private static void addContacts(Email email) throws EmailException {
        String[] contacts = getContacts();
        if (contacts != null) {
            for (String contact : contacts) {
                email.addTo(contact);
            }
        }
    }

    public static String getCurrentDate() {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    public static String[] getContacts() {
        return FileExt.getLines(contactBook, 0);
    }
}
