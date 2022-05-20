package com.example.demo;

public class DonatieSH {
    int id_donatie;
    String productName;
    String productColor;
    String productSize;
    String date;

    public DonatieSH(int id_donatie, String productName, String productColor, String productSize, String date)
    {
        this.id_donatie = id_donatie;
        this.productName = productName;
        this.productColor = productColor;
        this.productSize = productSize;
        this.date = date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public int getId_donatie() {
        return id_donatie;
    }

    public void setId_donatie(int id_donatie) {
        this.id_donatie = id_donatie;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductSize() {
        return productSize;
    }
}
