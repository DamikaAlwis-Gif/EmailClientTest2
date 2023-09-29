package com.company;

public class PersonalRecipients extends EmailRecipients implements Wishable{
    private String nickName;
    private String birthDay;
    private final static String wish = "Hugs and love on your birthday ";
    public PersonalRecipients(String name ,String nickName , String email , String birthDay){
        super(name , email);
        setNickName(nickName);
        setBirthDay(birthDay);
    }
    public String getNickName() { return nickName;}
    public void setNickName(String nickName) { this.nickName = nickName;}
    @Override
    public String getBirthDay() { return birthDay;}
    public void setBirthDay(String birthDay) { this.birthDay = birthDay;}
    @Override
    public String getEmail() {
        return super.getEmail();
    }
    @Override
    public String getWish() {
        return wish + getName()+".";
    }
    @Override
    public String getName() {
        return super.getName();
    }
}
