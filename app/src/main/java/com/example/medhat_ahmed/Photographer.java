package com.example.medhat_ahmed;

public class Photographer {

    int image;

    int styleImage1;
    int styleImage2;
    int styleImage3;
    int styleImage4;
    String locationLongitude;
    String locationLatitude;
    String name;
    String number;
    String instagram_page;
    String facebook_page;
    String website;



    public Photographer(String locationLongitude,String facebook_page,String locationLatitude,String name, String number, String instagram_page, String website,
                        int image, int styleImage1, int styleImage2, int styleImage3, int styleImage4) {
        this.locationLongitude = locationLongitude;
        this.locationLatitude = locationLatitude;
        this.name = name;
        this.number = number;
        this.instagram_page = instagram_page;
        this.facebook_page = facebook_page;
        this.website = website;
        this.image = image;
        this.styleImage1=styleImage1;
        this.styleImage2=styleImage2;
        this.styleImage3=styleImage3;
        this.styleImage4=styleImage4;
    }

    public Photographer() {
    }


    public String getFacebook_page() {
        return facebook_page;
    }

    public void setFacebook_page(String facebook_page) {
        this.facebook_page = facebook_page;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
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

    public String getWebsite() {
        return website;
    }


    public void setWebsite(String website) {
        this.website = website;
    }
}
