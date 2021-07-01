package com.nure.bearer.Adapter;

public class Cell {

    private String status;
    private String name;
    private String tmp;
    private double price;
    private String shelfLife;
    private String foodName;
    private int number;
    private int id;
    private double weight;

    public Cell(String status, String name, String tmp, double price, String life, String shelfLife, String foodName, int number, int id, double weight) {
        this.status = status;
        this.name = name;
        this.tmp = tmp;
        this.price = price;
        this.shelfLife = shelfLife;
        this.foodName = foodName;
        this.number = number;
        this.id = id;
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
