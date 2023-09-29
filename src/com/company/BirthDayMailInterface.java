package com.company;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public interface BirthDayMailInterface {
    ArrayList<String> getPersonstoWish(String date ) throws ParseException;
    void sendWishes() throws ParseException, ClassCastException, IOException;
    void sendAWish(EmailRecipients recipient) throws ParseException;


}
