package com.company;

//5
class OfficialRecipients extends EmailRecipients {
    private String designation ;
    public OfficialRecipients(String name , String email , String designation){
        super(name , email);
        setDesignation(designation);
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getDesignation() {
        return designation;
    }
}
