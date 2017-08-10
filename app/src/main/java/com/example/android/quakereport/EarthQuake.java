package com.example.android.quakereport;

/**
 * Created by setha on 04-08-2017.
 */

public class EarthQuake {
    private double mMagnitude;
    private String mLocation;
    private long mDate;
    private String mUrl;

    public EarthQuake(double magnitude, String location, long date, String url){
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
        mUrl = url;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getmDate() {
        return mDate;
    }

    public String getmUrl() {
        return mUrl;
    }
}
