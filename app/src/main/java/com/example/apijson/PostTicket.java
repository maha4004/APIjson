package com.example.apijson;

public class PostTicket {
    String userName;
    String userEmail;
    String userPhone;
    String eventName;
    String eventPlace;
    String eventDate;

    public PostTicket(String userName, String userEmail, String userPhone, String eventName, String eventPlace, String eventDate) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.eventName = eventName;
        this.eventPlace = eventPlace;
        this.eventDate = eventDate;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public String getEventDate() {
        return eventDate;
    }
}
