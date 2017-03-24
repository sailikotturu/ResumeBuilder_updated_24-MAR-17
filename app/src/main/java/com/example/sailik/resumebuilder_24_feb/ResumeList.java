package com.example.sailik.resumebuilder_24_feb;

import java.util.ArrayList;

/**
 * Created by saili.k on 04-03-2017.
 */

public class ResumeList {
    private static ArrayList<PersonResume> list = new ArrayList<PersonResume>();
    public ResumeList(){

    }

    public ResumeList(PersonResume obj){
        list.add(obj);
    }

    public ArrayList<PersonResume> getList() {
        return list;
    }

    public void setList(ArrayList<PersonResume> slist) {
        list = slist;
    }

}
