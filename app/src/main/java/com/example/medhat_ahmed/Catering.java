package com.example.medhat_ahmed;

public  class Catering {

    String name;
    int restaurantImage;
    String number;
    int menuPt1;
    int menuPt2;

    public Catering(String name, int restaurantImage, String number, int menuPt1, int menuPt2)
    {
        this.name = name;
        this.restaurantImage = restaurantImage;
        this.number = number;
        this.menuPt1 = menuPt1;
        this.menuPt2 = menuPt2;
    }


    public Catering()
    {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(int restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getMenuPt1() {
        return menuPt1;
    }

    public void setMenuPt1(int menuPt1) {
        this.menuPt1 = menuPt1;
    }

    public int getMenuPt2() {
        return menuPt2;
    }

    public void setMenuPt2(int menuPt2) {
        this.menuPt2 = menuPt2;
    }
}