package com.example.apijson;

public class items {

private String title;
private String image;
private String body;
private String location;
private String date;


    public items(String title, String image, String body, String location,String date) {
        this.title = title;
        this.image = image;
        this.body = body;
        this.location=location;
        this.date=date;
    }

public String getTitle(){return title;}
public String getImage(){return image;}
public String getBody(){return body;}
public String getLocation(){return location;}
public String getDate(){return date;}

}
