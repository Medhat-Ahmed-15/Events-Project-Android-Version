package com.example.medhat_ahmed;

public class Other {
    private String eventName;
    private String eventLocation;
    private String eventdate;
    private String eventTime;
    private String eventDescription;
    private String eventNumberOfGuests;
    private String userID;
    private int image;

    public Other(String eventName, String eventLocation, String eventdate, String eventTime, String eventDescription, String eventNumberOfGuests, String userID, int image) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventdate = eventdate;
        this.eventTime = eventTime;
        this.eventDescription = eventDescription;
        this.eventNumberOfGuests = eventNumberOfGuests;
        this.userID = userID;
        this.image = image;
    }

    public Other()
    {
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventNumberOfGuests() {
        return eventNumberOfGuests;
    }

    public void setEventNumberOfGuests(String eventNumberOfGuests) {
        this.eventNumberOfGuests = eventNumberOfGuests;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
