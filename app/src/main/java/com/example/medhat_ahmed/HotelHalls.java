package com.example.medhat_ahmed;

public class HotelHalls {

    String hallName;
    String hallMaxNumberOfTabels;
    String hallMaxNumberOfPeople;
    String hotelName;
    int position;


    public HotelHalls(String hallName, String hallMaxNumberOfTabels,int position, String hallMaxNumberOfPeople, String hotelName) {
        this.hallName = hallName;
        this.hallMaxNumberOfTabels = hallMaxNumberOfTabels;
        this.hallMaxNumberOfPeople = hallMaxNumberOfPeople;
        this.hotelName = hotelName;
        this.position = position;
    }

    public HotelHalls()
    {
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getHallMaxNumberOfTabels() {
        return hallMaxNumberOfTabels;
    }

    public void setHallMaxNumberOfTabels(String hallMaxNumberOfTabels) {
        this.hallMaxNumberOfTabels = hallMaxNumberOfTabels;
    }

    public String getHallMaxNumberOfPeople() {
        return hallMaxNumberOfPeople;
    }

    public void setHallMaxNumberOfPeople(String hallMaxNumberOfPeople) {
        this.hallMaxNumberOfPeople = hallMaxNumberOfPeople;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
