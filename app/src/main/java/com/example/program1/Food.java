package com.example.program1;

import androidx.annotation.NonNull;

public class Food {

    private int id;
    private String name;
    private int price;
    private String key;

    public Food()
    {

    }

    public Food(String name, int id, int price)
    {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @NonNull
    @Override
    public String toString() {
        return " " + name + "\n" +
                " " + price + "\n";
    }
}