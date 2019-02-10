package com.example.baran.mobileprogrammingproject;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Hospitals implements Serializable {
    @Exclude
    private String id;
    private String HospitalName;
    private String HospitalImage;
    private String HospitalAddress;

    public Hospitals(String id, String hospitalName, String hospitalImage, String hospitalAddress) {
        this.id = id;
        HospitalName = hospitalName;
        HospitalImage = hospitalImage;
        HospitalAddress = hospitalAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getHospitalImage() {
        return HospitalImage;
    }

    public void setHospitalImage(String hospitalImage) {
        HospitalImage = hospitalImage;
    }

    public String getHospitalAddress() {
        return HospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        HospitalAddress = hospitalAddress;
    }
}
