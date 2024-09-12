package com.example;

import java.math.BigDecimal;

public class productModel {
    // Private Variables
    private int ProductID;
    private String Name;
    private BigDecimal Price;
    private BigDecimal Quantity;
    private int SupplierID;

    // Constructor

    public productModel(int prdID, String name, BigDecimal price, BigDecimal quantity, int supID) {
        ProductID = prdID;
        Name = name;
        Price = price;
        Quantity = quantity;
        SupplierID = supID;
    }
    
    // Set Methods

    public void setProductID(int ID) { ProductID = ID; }
    public void setName(String name) { Name = name; }
    public void setPrice(BigDecimal price) { Price = price; }
    public void setQuantity(BigDecimal quantity) { Quantity = quantity; }
    public void setSupplierID(int ID) { SupplierID = ID; }

    // Get Methods

    public int getProductID() { return ProductID; }
    public String getName() { return Name; }
    public BigDecimal getPrice() { return Price; };
    public BigDecimal getQuantity() { return Quantity; }
    public int getSupplierID() { return SupplierID; }
}
