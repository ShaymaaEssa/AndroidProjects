package com.example.android.bakingapp.Model;

import android.graphics.Movie;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by goeic admin on 18-Feb-18.
 */

public class Steps implements Parcelable{
    int id;
    String shortDescription;
    String description;
    String videoURL;
    String thumbnailURL;

    public Steps(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }


    //Parcing Movie object so we can send it in intent
    public Steps(Parcel in) {
        String[] data = new String[5];
        in.readStringArray(data);
        this.id = Integer.parseInt(data[0]);
        this.shortDescription = data[1];
        this.description = data[2];
        this.videoURL = data[3];
        this.thumbnailURL = data[4];
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{String.valueOf(this.id),this.shortDescription,this.description,this.videoURL,this.thumbnailURL});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Steps createFromParcel(Parcel in) {
            return new Steps (in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };
}
