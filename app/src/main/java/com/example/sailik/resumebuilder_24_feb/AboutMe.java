package com.example.sailik.resumebuilder_24_feb;

/**
 * Created by saili.k on 03-03-2017.
 */

public class AboutMe {

    private String aboutmeText;
    public AboutMe(){

    }

    public AboutMe(String text){
        this.aboutmeText = text;
    }

    public void setAboutmeText(String text){
        this.aboutmeText=text;
    }

    public String getAboutmeText() {
        return aboutmeText;
    }
}
