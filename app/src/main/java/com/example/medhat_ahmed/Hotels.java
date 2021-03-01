package com.example.medhat_ahmed;

public class Hotels {

    int image;
    int imageStyle1;
    int imageStyle2;
    int imageStyle3;
    int imageStyle4;
    String imageUsingBitmap;

    int hall1;
    int hall2;
    int hall3;


    String name;
    String locationLongitude;
    String locationLatitude;
    String number;
    String rate;
    String website;
    String facebook;
    String instagram;

    public Hotels(int image, int imageStyle1, int imageStyle2, int imageStyle3, int imageStyle4, String imageUsingBitmap,
                  int hall1, int hall2, int hall3, String name, String locationLongitude, String locationLatitude,
                  String number, String rate, String website, String facebook, String instagram) {
        this.image = image;
        this.imageStyle1 = imageStyle1;
        this.imageStyle2 = imageStyle2;
        this.imageStyle3 = imageStyle3;
        this.imageStyle4 = imageStyle4;
        this.imageUsingBitmap = imageUsingBitmap;
        this.hall1 = hall1;
        this.hall2 = hall2;
        this.hall3 = hall3;
        this.name = name;
        this.locationLongitude = locationLongitude;
        this.locationLatitude = locationLatitude;
        this.number = number;
        this.rate = rate;
        this.website = website;
        this.facebook = facebook;
        this.instagram = instagram;
    }

    public Hotels()
    {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImageStyle1() {
        return imageStyle1;
    }

    public void setImageStyle1(int imageStyle1) {
        this.imageStyle1 = imageStyle1;
    }

    public int getImageStyle2() {
        return imageStyle2;
    }

    public void setImageStyle2(int imageStyle2) {
        this.imageStyle2 = imageStyle2;
    }

    public int getImageStyle3() {
        return imageStyle3;
    }

    public void setImageStyle3(int imageStyle3) {
        this.imageStyle3 = imageStyle3;
    }

    public int getImageStyle4() {
        return imageStyle4;
    }

    public void setImageStyle4(int imageStyle4) {
        this.imageStyle4 = imageStyle4;
    }

    public String getImageUsingBitmap() {
        return imageUsingBitmap;
    }

    public void setImageUsingBitmap(String imageUsingBitmap) {
        this.imageUsingBitmap = imageUsingBitmap;
    }

    public int getHall1() {
        return hall1;
    }

    public void setHall1(int hall1) {
        this.hall1 = hall1;
    }

    public int getHall2() {
        return hall2;
    }

    public void setHall2(int hall2) {
        this.hall2 = hall2;
    }

    public int getHall3() {
        return hall3;
    }

    public void setHall3(int hall3) {
        this.hall3 = hall3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
}
