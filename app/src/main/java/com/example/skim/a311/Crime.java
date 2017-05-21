package com.example.skim.a311;

/**
 * Created by SriramHariharan on 5/20/17.
 */

public class Crime {

    String time;
    String date;
    String location;
    String crime;

    public Crime(String t, String d, String l, String c) {
        time = t;
        date = d;
        location = l;
        crime = c;
    }

    public String getCrime() {
        return crime;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return time + " " + date + " " + location + " " + crime;
    }
}
