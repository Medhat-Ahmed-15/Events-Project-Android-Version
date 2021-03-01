package com.example.medhat_ahmed;

public class MakeupArtist {

    int image;
    int styleImage1;
    int styleImage2;
    int styleImage3;
    int styleImage4;
    String name;
    String number;
    String instagram_page;
    String facebook;
    String website;
    String longitude;
    String latitude;



    public MakeupArtist(String name, String number, String longitude,String latitude,String instagram_page, String facebook,
                       int image, int styleImage1,String website, int styleImage2, int styleImage3, int styleImage4) {

        this.name = name;
        this.number = number;
        this.instagram_page = instagram_page;
        this.facebook = facebook;
        this.website = website;
        this.image = image;
        this.styleImage1=styleImage1;
        this.styleImage2=styleImage2;
        this.styleImage3=styleImage3;
        this.styleImage4=styleImage4;
        this.longitude=longitude;
        this.latitude=latitude;
    }

    public MakeupArtist() {
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getStyleImage1() {
        return styleImage1;
    }

    public void setStyleImage1(int styleImage1) {
        this.styleImage1 = styleImage1;
    }

    public int getStyleImage2() {
        return styleImage2;
    }

    public void setStyleImage2(int styleImage2) {
        this.styleImage2 = styleImage2;
    }

    public int getStyleImage3() {
        return styleImage3;
    }

    public void setStyleImage3(int styleImage3) {
        this.styleImage3 = styleImage3;
    }

    public int getStyleImage4() {
        return styleImage4;
    }

    public void setStyleImage4(int styleImage4) {
        this.styleImage4 = styleImage4;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getInstagram_page() {
        return instagram_page;
    }

    public void setInstagram_page(String instagram_page) {
        this.instagram_page = instagram_page;
    }

    public String getFacebook() {
        return facebook;
    }


    public void setFacebook(String website) {
        this.facebook = website;
    }
}
