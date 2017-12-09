package com.example.ryan5.foodbudgettracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan5 on 12/8/2017.
 */

public class Budgets {
    private Double amount;
    private String name;
    private ArrayList<Charges> c;

    public Budgets(Double amount, String name){
        this.amount = amount;
        this.name = name;
    }

    public Budgets(){}

/*    public void addCharge(Charges newC)
    {
        c.add(newC);
    }

    public void updateCharge(int item, Charges updatedC){
        c.set(item, updatedC);
    }*/

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
