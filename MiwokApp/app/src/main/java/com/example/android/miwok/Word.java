package com.example.android.miwok;

/**
 * Created by goeic admin on 08-Feb-18.
 */

public class Word {
    String mDefaultTranslation;
    String mMiwokTranslation;
    int img = -1;
    int audioFile;


    public Word (String mDefaultTranslation, String mMiwokTranslation,int audioFile){
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation ;
        this.audioFile = audioFile;

    }
    public Word (String mDefaultTranslation, String mMiwokTranslation, int img, int audioFile){
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.img = img;
        this.audioFile = audioFile;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getImgResourceId() {
        return img;
    }

    public int getAudioFile() {
        return audioFile;
    }
}
