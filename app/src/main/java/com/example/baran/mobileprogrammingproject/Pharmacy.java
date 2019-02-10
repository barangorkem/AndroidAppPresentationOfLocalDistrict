package com.example.baran.mobileprogrammingproject;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Pharmacy implements Serializable {
    @Exclude
    private String id;
    private String PharmacyName;
    private String PharmacyImage;
    private String PharmacyAddress;

    public Pharmacy(String id, String pharmacyName, String pharmacyImage, String pharmacyAddress) {
        this.id = id;
        PharmacyName = pharmacyName;
        PharmacyImage = pharmacyImage;
        PharmacyAddress = pharmacyAddress;
    }

    public String getPharmacyName() {
        return PharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        PharmacyName = pharmacyName;
    }

    public String getPharmacyImage() {
        return PharmacyImage;
    }

    public void setPharmacyImage(String pharmacyImage) {
        PharmacyImage = pharmacyImage;
    }

    public String getPharmacyAddress() {
        return PharmacyAddress;
    }

    public void setPharmacyAddress(String pharmacyAddress) {
        PharmacyAddress = pharmacyAddress;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
