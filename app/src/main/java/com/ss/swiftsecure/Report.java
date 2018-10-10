package com.ss.swiftsecure;

import android.graphics.Bitmap;

public class Report {
    private String mTimeStamp;
    private Bitmap mImage;
    private String mLocation;
    private String mDescription;

    public Report(String description, String location,String timeStamp){
        mDescription=description;
        mLocation=location;
        mTimeStamp=timeStamp;
    }
    public Report(String description,String location,Bitmap image,String timeStamp){
        mDescription=description;
        mLocation=location;
        mImage=image;
        mTimeStamp=timeStamp;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getLocation() {
        return mLocation;
    }

    public Bitmap getImage() {
        return mImage;
    }
public String getTimeStamp(){
        return mTimeStamp;
}
    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setImage(Bitmap mImage) {
        this.mImage = mImage;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }
    public void setTimeSpamp(String timeSpamp){
        mTimeStamp=timeSpamp;
    }
}
