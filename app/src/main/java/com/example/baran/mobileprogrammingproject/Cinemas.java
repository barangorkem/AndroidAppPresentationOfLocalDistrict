package com.example.baran.mobileprogrammingproject;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Cinemas implements Serializable {

    @Exclude
    private String id;
    private String CinemaName;
    private String CinemaImage;
    private String CinemaAddress;
    public Cinemas(String id,String cinemaName, String cinemaImage, String cinemaAddress) {
        this.id=id;
        CinemaName = cinemaName;
        CinemaImage = cinemaImage;
        CinemaAddress = cinemaAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCinemaName() {
        return CinemaName;
    }

    public void setCinemaName(String cinemaName) {
        CinemaName = cinemaName;
    }

    public String getCinemaImage() {
        return CinemaImage;
    }

    public void setCinemaImage(String cinemaImage) {
        CinemaImage = cinemaImage;
    }

    public String getCinemaAddress() {
        return CinemaAddress;
    }

    public void setCinemaAddress(String cinemaAddress) {
        CinemaAddress = cinemaAddress;
    }
}
