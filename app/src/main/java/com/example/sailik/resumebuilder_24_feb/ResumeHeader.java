package com.example.sailik.resumebuilder_24_feb;

/**
 * Created by saili.k on 04-03-2017.
 */

public class ResumeHeader {

    private String personName;
    private String personEmail;
    private String personExperience;
    private String imageURL;
    private int current;

    public ResumeHeader(){

    }
    public ResumeHeader(String personName,String personEmail,String imageURL,String personExperience){
        this.personName = personName;
        this.personEmail = personEmail;
        this.imageURL = imageURL;
        this.personExperience = personExperience;
    }
    public ResumeHeader(String personName,String personEmail,String imageURL){
        this.personName = personName;
        this.personEmail = personEmail;
        this.imageURL = imageURL;
//        this.personExperience = "";

    }
    public void setPersonName(String personName){
        this.personName = personName;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setPersonExperience(String personExperience) {
        this.personExperience = personExperience;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getPersonExperience() {
        return personExperience;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
