package com.example.baran.mobileprogrammingproject;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Restaurants implements Serializable {

    @Exclude
    private String id;
    private String RestaurantName;
    private String RestaurantImage;
    private String RestaurantAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getRestaurantImage() {
        return RestaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        RestaurantImage = restaurantImage;
    }

    public String getRestaurantAddress() {
        return RestaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        RestaurantAddress = restaurantAddress;
    }

    public Restaurants(String id, String restaurantName, String restaurantImage, String restaurantAddress) {
        this.id = id;
        RestaurantName = restaurantName;

        RestaurantImage = restaurantImage;
        RestaurantAddress = restaurantAddress;
    }
}
