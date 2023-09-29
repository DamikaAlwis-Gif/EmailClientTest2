package com.company;

import java.io.Serializable;
import java.util.Calendar;

public class Email implements Serializable {
    private String to;
    private String subject;
    private String content;
    private Calendar date;
    public Email(String to , String subject , String content ){
        this.to = to;
        this.content = content;
        this.subject = subject;
    }
    public Calendar getDate() {
        return date;
    }
    public void setDate(Calendar date) {
        this.date = date;
    }
    public String getTo() {
        return to;
    }
    public String getContent() {
        return content;
    }
    public String getSubject() {
        return subject;
    }
}