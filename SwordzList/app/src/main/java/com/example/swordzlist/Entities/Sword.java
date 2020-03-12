package com.example.swordzlist.Entities;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Sword implements Serializable {

    private int id;
    private String name;
    private Double price;
    private Bitmap profilePicture;

    public Sword(int id, String name, Double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Double getPrice(){
        return price;
    }

    public void setPrice(Double price){
        this.price = price;
    }
}
