package com.example.sailik.resumebuilder_24_feb;

import java.util.ArrayList;

/**
 * Created by saili.k on 27-02-2017.
 */

public class PersonResume {
    private ResumeHeader headerObj;
    private AboutMe aboutObj;
    private ArrayList<Projects> projectsList = new ArrayList<Projects>();
    private ArrayList<Education> educationList = new ArrayList<Education>();
    private ArrayList<Interests> interestsList = new ArrayList<Interests>();

    public PersonResume(){

    }
    public PersonResume(ResumeHeader headerObj,AboutMe aboutObj,Projects projectObj,Education educationObj,Interests interestsObj){
        this.headerObj = headerObj;
        this.aboutObj = aboutObj;
        this.projectsList.add(projectObj);
        this.educationList.add(educationObj);
        this.interestsList.add(interestsObj);
    }

    public ResumeHeader getHeaderObj() {
        return headerObj;
    }

    public AboutMe getAboutObj() {
        return aboutObj;
    }

    public ArrayList<Projects> getProjectsList() {
        return projectsList;
    }

    public ArrayList<Education> getEducationList() {
        return educationList;
    }

    public ArrayList<Interests> getInterestsList() {
        return interestsList;
    }

    public void setHeaderObj(ResumeHeader headerObj) {
        this.headerObj = headerObj;
    }

    public void setAboutObj(AboutMe aboutObj) {
        this.aboutObj = aboutObj;
    }

    public void setProjectsList(ArrayList<Projects> projectsList) {
        this.projectsList = projectsList;
    }

    public void setEducationList(ArrayList<Education> educationList) {
        this.educationList = educationList;
    }

    public void setInterestsList(ArrayList<Interests> interestsList) {
        this.interestsList = interestsList;
    }




}
