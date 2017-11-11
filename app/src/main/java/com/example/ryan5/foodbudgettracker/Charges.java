package com.example.ryan5.foodbudgettracker;

/**
 * Created by Ryan5 on 11/10/2017.
 */

public class Charges {
    private String place;
    private String amount;

    public Charges(String place, String amount){
        this.place = place;
        this.amount = amount;
    }

    public String getPlace() {
        return place;
    }

    public String getAmountmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
