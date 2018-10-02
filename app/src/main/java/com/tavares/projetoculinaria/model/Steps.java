package com.tavares.projetoculinaria.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Steps implements Parcelable {
    private final int StepID;
    private String mShortDescription= "";
    private String mDescription= "";
    private String mVideoURL= "";
    private String mThumbURL= "";
    private String name= "";


    public Steps(int stepID, String mShortDescription, String mDescription, String mVideoURL, String mThumbURL, String name) {
        StepID = stepID;
        this.mShortDescription = mShortDescription;
        this.mDescription = mDescription;
        this.mVideoURL = mVideoURL;
        this.mThumbURL = mThumbURL;
        this.name = name;
    }


    protected Steps(Parcel in) {
        StepID = in.readInt();
        mShortDescription = in.readString();
        mDescription = in.readString();
        mVideoURL = in.readString();
        mThumbURL = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(StepID);
        dest.writeString(mShortDescription);
        dest.writeString(mDescription);
        dest.writeString(mVideoURL);
        dest.writeString(mThumbURL);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStepID() {
        return StepID;
    }

    public String getmShortDescription() {
        return mShortDescription;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmVideoURL() {
        return mVideoURL;
    }

    public String getmThumbURL() {
        return mThumbURL;
    }

}
