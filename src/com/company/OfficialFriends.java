package com.company;

//6
public class OfficialFriends extends OfficialRecipients implements Wishable{
    private String birthDay;
    private final static String wish = "Wish you a Happy Birthday ";
    public OfficialFriends(String name , String email , String designation , String birthDay){
        super(name , email , designation);
        setBirthDay(birthDay);
    }
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
    @Override
    public String getEmail() {
        return super.getEmail();
    }
    @Override
    public String getBirthDay() {
        return birthDay;
    }
    @Override
    public String getWish() {
        String fullWish = wish + getName()+".";
        return fullWish;
    }
    @Override
    public String getName() {
        return super.getName();
    }
}