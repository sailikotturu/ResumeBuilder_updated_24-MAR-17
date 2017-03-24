package com.example.sailik.resumebuilder_24_feb;

/**
 * Created by saili.k on 03-03-2017.
 */

public class TypeDecipher {
//    AboutMe a ;
//    Projects b;
//    Education c;
    Object obj;
    //String objectType;
    int objType;
    public TypeDecipher(int objType){
        this.objType = objType;
        //this.objectType = objectType;
        if(objType==4){
            obj = new ResumeHeader();
        }
        if(objType==0){
            obj = new AboutMe();
        }
        if(objType==1){
            obj = new Projects();
        }
        if(objType==2){
            obj= new Education();
        }
        if(objType==3){
            obj= new Interests();
        }

    }
//    public TypeDecipher(Object obj){
//        if(a.equals(obj)){
//            this.objectType = "AboutMe";
//            a= new AboutMe();
//            a.setAboutmeText();
//        }
//        else if(b.equals(obj)){
//            this.objectType = "Projects";
//        }
//        else if(c.equals(obj)){
//            this.objectType = "Interests";
//        }
//        else{
//
//        }
//    }

    public int getObjectType(){
        return this.objType;
    }

    public void setObjectType(int objType) {
        this.objType = objType;
    }
}
