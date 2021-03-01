package com.example.medhat_ahmed;

public class Decorations {
    String name;
    String number;
    int image;
    String type;

    public Decorations(String name, String number, int image, String type) {
        this.name = name;
        this.number = number;
        this.image = image;
        this.type = type;
    }

    public Decorations()
    {
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
