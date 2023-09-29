package com.company;

import java.util.ArrayList;

//7
public class Factory {
    private String type ;
    private String[] details;
    ArrayList<EmailRecipients> list ;
    ArrayList<Wishable> birthDayList ;
    public Factory(){
        list = new ArrayList<EmailRecipients>();
        birthDayList = new ArrayList<Wishable>();
    }
    public EmailRecipients createRecipients(String input){
        String[] temp1 = input.split(":");
        String temp2 = temp1[0].trim();
        String temp3 = temp1[1].trim();
        String[] temp4 = temp3.split(",");
        type = temp2;
        details = temp4;
// for Official Recipients Official: nimal,nimal@gmail.com,ceo
        if (type.equals("Official")){
            OfficialRecipients obj = new OfficialRecipients(details[0], details[1],details[2]);
            list.add(obj);
            return obj;
        }
// for Office friends Office_friend: kamal,kamal@gmail.com,clerk,2000/12/12
        else if (type.equals("Office_friend")){
            OfficialFriends obj = new OfficialFriends(details[0], details[1], details[2], details[3]);
            list.add(obj);
            birthDayList.add(obj);
            return obj;
        }
// for Personal friends Personal: sunil,<nick-name>,sunil@gmail.com,2000/10/10
        else if (type.equals("Personal")){
            PersonalRecipients obj = new PersonalRecipients(details[0], details[1],details[2],details[3]);
            list.add(obj);
            birthDayList.add(obj);
            return obj;
        }
        else{
            return null;
        }
    }
    public ArrayList<EmailRecipients> getList() { return list;
    }
    public ArrayList<Wishable > getBirthDayList() {
        return birthDayList;
    }
    // load all recipients in the clientList to the application
    public void loadRecipients(){
        ArrayList<String> lineList = FileManager.readFile("clientList.txt");
        for (String temp1 : lineList){
            this.createRecipients(temp1);
        }
    }
}
