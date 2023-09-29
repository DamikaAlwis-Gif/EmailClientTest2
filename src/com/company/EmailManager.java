package com.company;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

//9
public class EmailManager implements IEmailManager{
    final String username = "damika7temp@gmail.com";
    final String password = "joyqqwekvbpecoys";
    private ArrayList<Email> emailList= new ArrayList<Email>();
    final String emailFile = "email.txt";
    // to send a mail
    @Override
    public void sendAMail(Email email) throws ParseException {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email.getTo()));
            message.setSubject(email.getSubject());
            message.setText(email.getContent());
            Transport.send(message);
            email.setDate(Datework.getToday());
            emailList.add(email);
            storeEmail(email);
            System.out.println("Email sent!");
        } catch (
                MessagingException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
    // get all the emails sent on a specified date
    @Override
    public ArrayList<Email> getEmailbyDate(String date) throws ParseException{
        Calendar tempdate = Datework.parseDate(date);
        ArrayList <Email> emailsonDate = new ArrayList<>();
        for (Email email: emailList){
            if (email.getDate().get(Calendar.YEAR) == tempdate.get(Calendar.YEAR) && email.getDate().get(Calendar.MONTH) == tempdate.get(Calendar.MONTH)
                    && email.getDate().get(Calendar.DAY_OF_MONTH) == tempdate.get(Calendar.DAY_OF_MONTH)){
                emailsonDate.add(email);
            }
        }
        return emailsonDate;
    }
    // store email object to email.txt
    @Override
    public void storeEmail(Email email) throws IOException {
// make a new file
        File file = new File(emailFile);
        file.createNewFile();
        if (email != null) {
            FileManager.serializeFile(file, emailFile, email);
        }
    }
    @Override
// load all emails to email.txt
    public void loadEmails() throws IOException ,ClassNotFoundException {
        setEmailList(FileManager.deserializeFile(emailFile));
    }
    @Override
    public ArrayList<Email> getEmailList() {
        return emailList;
    }
    private void setEmailList(ArrayList<Email> emailList) {
        this.emailList = emailList;
    }
}
