package com.example;

import java.math.BigDecimal;
import java.sql.Date;

public class orderModel {
    // Private Variables
    private int OrderID;
    private int CustomerID;
    private Date OrderDate;
    private int ProductID;
    private BigDecimal Quantity;
    private BigDecimal Cost;
        // Payment
        private String CardNumber;
        private String CardName;
        private String ExpirationDate;
        private int CVV;

    // Constructor
    public orderModel(int orderID, int customerID, Date orderDate, int productID, BigDecimal quantity, BigDecimal cost, String cardNumber, String cardName, String expirationDate, int cVV) {
        OrderID = orderID;
        CustomerID = customerID;
        OrderDate = orderDate;
        ProductID = productID;
        Quantity = quantity;
        Cost = cost;
        CardNumber = cardNumber;
        CardName = cardName;
        ExpirationDate = expirationDate;
        CVV = cVV;
    }
    public orderModel(int orderID, int customerID, Date orderDate, int productID, BigDecimal quantity, BigDecimal cost) {
        OrderID = orderID;
        CustomerID = customerID;
        OrderDate = orderDate;
        ProductID = productID;
        Quantity = quantity;
        Cost = cost;

        CardNumber = null;
        CardName = null;
        ExpirationDate = null;
        CVV = -1;
    }
    public orderModel(int orderID, String cardNumber, String cardName, String expirationDate, int cVV) {
        OrderID = orderID;
        CardNumber = cardNumber;
        CardName = cardName;
        ExpirationDate = expirationDate;
        CVV = cVV;

        CustomerID = -1;
        OrderDate = null;
        ProductID = -1;
        Quantity = null;
        Cost = null;
    }

    // Setters
    public void setOrderID(int orderID) { OrderID = orderID; }
    public void setCustomerID(int customerID) { CustomerID = customerID; }
    public void setOrderDate(Date orderDate) { OrderDate = orderDate; }
    public void setProductID(int productID) { ProductID = productID; }
    public void setQuantity(BigDecimal quantity) { Quantity = quantity; }
    public void setCost(BigDecimal cost) { Cost = cost; }
    public void setCardNumber(String cardNumber) { CardNumber = cardNumber; }
    public void setCardName(String cardName) { CardName = cardName; }
    public void setExpirationDate(String expirationDate) { ExpirationDate = expirationDate; }
    public void setCVV(int cVV) { CVV = cVV; }

    // Getters
    public int getOrderID() { return OrderID; }
    public int getCustomerID() { return CustomerID; }
    public Date getOrderDate() { return OrderDate; }
    public int getProductID() { return ProductID; }
    public BigDecimal getQuantity() { return Quantity; }
    public BigDecimal getCost() { return Cost; }
    public String getCardNumber() { return CardNumber; }
    public String getCardName() { return CardName; }
    public String getExpirationDate() { return ExpirationDate; }
    public int getCVV() { return CVV; }
}
