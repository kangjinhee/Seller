package com.example.r20np.seller.data.model;

/**
 * Created by R20NP on 2016-04-26.
 */
public class CurrentAddress {

    private String district;
    private int id;



    public CurrentAddress() {

    }

    public CurrentAddress(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

