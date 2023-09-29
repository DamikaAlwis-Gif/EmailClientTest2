package com.company;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

//13
public class BirthDayMails implements BirthDayMailInterface {
    private EmailManager manager;
    private Factory factory;
    public BirthDayMails(EmailManager manager, Factory factory){
        this.manager = manager;
        this.factory = factory;
    }
    @Override
    public ArrayList<String> getPersonstoWish(String date ) throws ParseException {
        ArrayList<Wishable> birthDayList = factory.getBirthDayList();
        ArrayList<String> names = new ArrayList<>();
        Calendar tempDate = Datework.parseDate(date);
        for(Wishable i : birthDayList ){
            Calendar iDate = Datework.parseDate(i.getBirthDay());
            if(isEqual(iDate , tempDate)){
                names.add(i.getName());
            }
        }
        return names;
    }
    @Override
    public void sendWishes() throws ParseException, ClassCastException, IOException {
        ArrayList<Wishable> birthDayList = factory.getBirthDayList();
        Calendar today = Datework.getToday();
        String todayStr= Datework.getDayStr(today);
        ArrayList<String> pastDayList = FileManager.readFile("PastDays.txt");
        if ( !pastDayList.contains(todayStr)) {
            for (Wishable item : birthDayList){
                Calendar tempDate = Datework.parseDate(item.getBirthDay());
                if (isEqual(today , tempDate) ){
                    Email mail = new Email(item.getEmail(),"For Celebrating Bithday ", item.getWish());
                    System.out.println("Ready to send a birthday wish to " + item.getName());
                    manager.sendAMail(mail);
                }
            }
            String date = Datework.getDayStr(today);
            FileManager.writeToFile("PastDays.txt", date);
        }
    }
    private boolean isEqual(Calendar a, Calendar b){
        return (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) &&
                a.get(Calendar.DAY_OF_MONTH) == b.get(Calendar.DAY_OF_MONTH));
    }
    @Override
    public void sendAWish(EmailRecipients recipient) throws ParseException{
        if(recipient instanceof OfficialFriends || recipient instanceof PersonalRecipients){
            ArrayList<Wishable> li = factory.getBirthDayList();
            Wishable person = li.get(li.size()-1);
            if (isEqual(Datework.parseDate(person.getBirthDay()) , Calendar.getInstance())){
                Email mail = new Email(person.getEmail(),"For Celebrating Bithday ", person.getWish());
                System.out.println("Ready to send birthday wish to " + person.getName());
                manager.sendAMail(mail);
            }
        }
    }
}

