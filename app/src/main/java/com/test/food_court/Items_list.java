package com.test.food_court;


import java.util.ArrayList;

public class Items_list {

    private String Name;
    private String Qty;
    private String Total;

    public Items_list() {

    }

    public Items_list(String name, String qty,String total) {

        this.Name = name;
        this.Qty = qty;
        this.Total = total;

    }


    //Getters and setters
    public void setName(String Name) {
        this.Name = Name;
    }


    public void setQty(String qty) {
        this.Qty = qty;
    }

    public void setTotal(String total) {
        this.Total = total;
    }

    public String getName() {
        return Name;
    }


    public String getQty() {
        return Qty;
    }

    public String getTotal() {
        return Total;
    }


}