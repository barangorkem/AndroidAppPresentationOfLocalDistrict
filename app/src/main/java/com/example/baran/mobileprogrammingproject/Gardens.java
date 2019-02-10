package com.example.baran.mobileprogrammingproject;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Gardens implements Serializable {
    @Exclude
    private String id;
    private String GardenName;
    private String GardenImage;
    private String GardenAddress;

    public Gardens(String id, String gardenName, String gardenImage, String gardenAddress) {
        this.id = id;
        GardenName = gardenName;
        GardenImage = gardenImage;
        GardenAddress = gardenAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGardenName() {
        return GardenName;
    }

    public void setGardenName(String gardenName) {
        GardenName = gardenName;
    }

    public String getGardenImage() {
        return GardenImage;
    }

    public void setGardenImage(String gardenImage) {
        GardenImage = gardenImage;
    }

    public String getGardenAddress() {
        return GardenAddress;
    }

    public void setGardenAddress(String gardenAddress) {
        GardenAddress = gardenAddress;
    }
}
