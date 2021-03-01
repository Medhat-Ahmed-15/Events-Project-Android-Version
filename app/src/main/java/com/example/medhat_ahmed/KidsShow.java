package com.example.medhat_ahmed;

public class KidsShow {

    private String kidsShowName;
    private int kidsshowImage;
    private String videoUrl;
    private String number;

    public KidsShow(String kidsShowName, int kidsshowImage, String videoUrl, String number)
    {
        this.kidsShowName = kidsShowName;
        this.kidsshowImage = kidsshowImage;
        this.videoUrl = videoUrl;
        this.number = number;
    }

    public KidsShow()
    {
    }

    public String getKidsShowName() {
        return kidsShowName;
    }

    public void setKidsShowName(String kidsShowName) {
        this.kidsShowName = kidsShowName;
    }

    public int getKidsshowImage() {
        return kidsshowImage;
    }

    public void setKidsshowImage(int kidsshowImage) {
        this.kidsshowImage = kidsshowImage;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
