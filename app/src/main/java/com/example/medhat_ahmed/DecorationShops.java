package com.example.medhat_ahmed;

public class DecorationShops {
    int image;
    String name;
    String number;

    public DecorationShops(int image, String name, String number)
    {
        this.image = image;
        this.name = name;
        this.number = number;
    }

    public DecorationShops()
    {
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
}