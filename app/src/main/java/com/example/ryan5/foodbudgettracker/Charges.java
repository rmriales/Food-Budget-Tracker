package com.example.ryan5.foodbudgettracker;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ryan5 on 11/10/2017.
 */

public class Charges {
    private String place;
    private Double amount;

    private static final String JSON_PLACE = "name";
    private static final String JSON_AMOUNT = "amount";

    public Charges(String place, Double amount){
        this.place = place;
        this.amount = amount;
    }

    public Charges(){}

    public Charges(JSONObject jo) throws JSONException{
        place = jo.getString(JSON_PLACE);
        amount = jo.getDouble(JSON_AMOUNT);
    }

    public JSONObject createJSONObject() throws JSONException {
        JSONObject jo = new JSONObject();

        jo.put(JSON_PLACE, place);
        jo.put(JSON_AMOUNT, amount);

        return jo;
    }

    public String getPlace() {
        return place;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
