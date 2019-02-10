package com.example.baran.mobileprogrammingproject;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Hotels implements Serializable {
    @Exclude
    private String id;
    private String HotelName;
    private String HotelImage;
    private String HotelAddress;

    public Hotels(String id, String hotelName, String hotelImage, String hotelAddress) {
        this.id = id;
        HotelName = hotelName;
        HotelImage = hotelImage;
        HotelAddress = hotelAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public String getHotelImage() {
        return HotelImage;
    }

    public void setHotelImage(String hotelImage) {
        HotelImage = hotelImage;
    }

    public String getHotelAddress() {
        return HotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        HotelAddress = hotelAddress;
    }
}
