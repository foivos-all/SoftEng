package com.example.healthcare;

import java.io.Serializable;

public class Product implements Serializable {
    public String productName;
    public String productStock;
    public String productPrice;

    public Product() {

    }

    public Product(String productName, String productStock, String productPrice) {
        this.productName = productName;
        this.productStock = productStock;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductStock() {
        return productStock;
    }

    public void setProductStock(String productStock) {
        this.productStock = productStock;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
