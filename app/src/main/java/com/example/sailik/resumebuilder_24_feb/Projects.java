package com.example.sailik.resumebuilder_24_feb;

/**
 * Created by saili.k on 27-02-2017.
 */

public class Projects {

    private String projectName;
    private String projectDuration;
    private String description;

    public Projects(){

    }

    public Projects(String pName,String pDuration,String desc){
        this.projectName = pName;
        this.projectDuration = pDuration;
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }

    public String getProjectDuration() {
        return projectDuration;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProjectDuration(String projectDuration) {
        this.projectDuration = projectDuration;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
