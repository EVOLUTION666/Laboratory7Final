package com.example.laba6;

public class Model {
    private int id;
    private String name;
    private String description;
    private int count;
    private int price;

    public Model() {
    }

    public Model(String name,String description, int count, int price) {
        this.name = name;
        this.description = description;
        this.count = count;
        this.price = price;
    }

    public Model(int id, String name,String description, int count, int price) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
