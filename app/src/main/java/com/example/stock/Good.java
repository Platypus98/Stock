package com.example.stock;

public class Good {

    private String name;
    private Integer quantity;
    private Integer sellingPrice;
    private Integer purchasePrice;

    public Good(String name, Integer quantity, Integer sellingPrice, Integer purchasePrice){
        this.name = name;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return this.quantity.toString();
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSellingPrice() {
        return this.sellingPrice.toString();
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getPurchasePrice() {
        return this.purchasePrice.toString();
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }




}
