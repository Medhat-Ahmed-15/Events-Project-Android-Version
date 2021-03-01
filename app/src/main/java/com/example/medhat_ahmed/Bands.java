package com.example.medhat_ahmed;

public class Bands {

    private String bandName;
    private int bandImage;
    private String videoUrl;


    public Bands(String bandName, int bandImage, String videoUrl) {
        this.bandName = bandName;
        this.bandImage = bandImage;
        this.videoUrl = videoUrl;
    }

    public Bands()
    {
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public int getBandImage() {
        return bandImage;
    }

    public void setBandImage(int bandImage) {
        this.bandImage = bandImage;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
