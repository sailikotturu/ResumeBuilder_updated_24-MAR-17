package com.example.sailik.resumebuilder_24_feb;

/**
 * Created by saili.k on 27-02-2017.
 */

public class Education {

    private String collegeName;
    private String studyDuration;
    private String courseOfStudy;
    public Education(){

    }

    public Education(String cName,String studyDuration,String courseOfStudy){
        this.collegeName =cName;
        this.studyDuration = studyDuration;
        this.courseOfStudy = courseOfStudy;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public void setCourseOfStudy(String courseOfStudy) {
        this.courseOfStudy = courseOfStudy;
    }

    public void setStudyDuration(String studyDuration) {
        this.studyDuration = studyDuration;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public String getCourseOfStudy() {
        return courseOfStudy;
    }

    public String getStudyDuration() {
        return studyDuration;
    }
}
