package com.company;

// 200021T
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.Serializable;
import java.util.Calendar;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.*;
import java.io.*;
import java.util.Properties;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
//1
public class Main {
    public static void main(String[] args) {
        Factory factory;
        EmailManager emailManager;
        BirthDayMails birthDayMails;
        try {
            factory = new Factory();
            emailManager = new EmailManager();
            factory.loadRecipients();
            emailManager.loadEmails();
            birthDayMails = new BirthDayMails(emailManager, factory);
            birthDayMails.sendWishes();
        } catch (IOException | ParseException | ClassNotFoundException | ClassCastException e) {
            System.out.println(e.getMessage());
            return;
        }
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Enter option type: \n"
                    + "1 - Adding a new recipient\n"
                    + "2 - Sending an email\n"
                    + "3 - Printing out all the recipients who have birthdays\n"
                    + "4 - Printing out details of all the emails sent\n"
                    + "5 - Printing out the number of recipient objects in the application");
            int option = scanner.nextInt();
            String input;
            switch(option){
                case 1:
                    System.out.println("Use these formats for your input \n" +
                            "Personal: <Name>,<Nick-name>,<Email>,<Birthday>\n" +
                            "Office_friend: <Name>,<Email>,<Designation>,<Birthday>\n"+
                            "Official: <Name>,<Email>,<Designation>"
                    );
                    scanner.nextLine();
                    if(scanner.hasNext()) { // getting inputs
                        input = scanner.nextLine();
                        try {
                            EmailRecipients recipient = factory.createRecipients(input);// create a new obj for recipient
                            birthDayMails.sendAWish(recipient); //if newly added recipient's birthday is today , send a wish
                            FileManager.writeToFile("clientList.txt", input); //store details in clientList.txt file
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Enter a valid input! ");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Put format - email, subject, content");
                    scanner.nextLine();
                    if(scanner.hasNext()){
                        input = scanner.nextLine();
                        List<String> li = Arrays.stream(input.split(",")) .map(String::trim).toList();
                        Email email = new Email(li.get(0) ,li.get(1),li.get(2)); // create a mail obj
                        try {
                            emailManager.sendAMail(email);// send a mail
                        }
                        catch (ParseException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 3:
                    System.out.println("Input format - yyyy/MM/dd (ex: 2018/09/17)");
                    scanner.nextLine();
                    if(scanner.hasNext()) {
                        input = scanner.nextLine();
                        ArrayList<String> nameList = null;
                        try {
                            nameList = birthDayMails.getPersonstoWish(input);
                        }
                        catch (ParseException e){
                            System.out.println(e.getMessage());
                        }
                        if (nameList != null) {
                            if (nameList.size() == 0) {System.out.println("Anybody doesn't have a birth Day on "+ input);}
                            else{
                                System.out.println("Persons who have Birth Day on "+ input +" :");
                                int x=1;
                                for (String i : nameList){
                                    System.out.println(x+ ". " + i);
                                    x++;
                                }
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("Input format - YYYY/MM/dd (ex: 2018/09/17)");
                    scanner.nextLine();
                    if(scanner.hasNext()) {
                        input = scanner.nextLine();
                        ArrayList<Email> emailList= null;
                        try{
                            emailList = emailManager.getEmailbyDate(input);
                        }
                        catch (ParseException e){
                            System.out.println(e.getMessage());
                        }
                        if (emailList != null) {
                            if (emailList.size() == 0 ){
                                System.out.println("No email has sent on "+ input +".");
                            }
                            else{
                                int i=1;
                                for (Email e : emailList){
                                    System.out.println(i+ ". Sent a email to " + e.getTo() +" Subject on : "+ e.getSubject());
                                    i++;
                                }
                            }
                        }
                    }
                    break;
                case 5:
                    int count = EmailRecipients.getCount();
                    System.out.println("The number of recipients so far :" + count);
                    break;
                default:
                    break;
            }
        }
    }
}