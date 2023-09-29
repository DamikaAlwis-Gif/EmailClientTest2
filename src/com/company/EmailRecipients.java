package com.company;

//3
public abstract class EmailRecipients {
    private String name;
    private String email;
    private static int count =0;
    public EmailRecipients(String name , String email){
        setName(name);
        setEmail(email);
        count++;
    }
    public static int getCount(){return count;}
    public String getName() { return name;}
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email;}
    public void setEmail(String email) { this.email = email;}
}
