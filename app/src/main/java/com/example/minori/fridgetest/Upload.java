package com.example.minori.fridgetest;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.Query;

/**
 * Created by yahata-minori on 2017/08/21.
 */

@IgnoreExtraProperties
public class Upload {
    public String name;
    public String url;
    public String takenDate;
    public Long longDate;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String name, String url, String takenDate, Long longDate) {
        this.name = name;
        this.url= url;
        this.takenDate= takenDate;
        this.longDate= longDate;
    }

    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
    public String getTakenDate() { return takenDate; }
   // public Long getLongDate() { return longDate; }





}
