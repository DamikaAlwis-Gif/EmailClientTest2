package com.company;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public interface IEmailManager {
    void sendAMail(Email email) throws ParseException;
    ArrayList<Email> getEmailbyDate(String date) throws ParseException;
    void storeEmail(Email email) throws IOException;
    void loadEmails() throws IOException,ClassNotFoundException;
    ArrayList<Email> getEmailList();
}
