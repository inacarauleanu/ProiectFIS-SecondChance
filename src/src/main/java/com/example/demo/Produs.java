package com.example.demo;

import static com.example.demo.HelloController.username;

public class Produs {
    int id;
    String denumire;
    String size;
    String color;
    int price;
    String usernameSH;

    public Produs(String usernameSH, int id, String denumire, String size, String color, int price)
    {
        usernameSH = username;
        this.id=id;
        this.denumire=denumire;
        this.size=size;
        this.color=color;
        this.price=price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price)
    {
        this.price=price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
