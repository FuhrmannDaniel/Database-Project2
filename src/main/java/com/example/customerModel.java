package com.example;

public class customerModel {
    // Private Variables
    private int CustomerID;
    private String Name;
    private String Phone;
    private String Address;
    private String City;
    private String StateCode;
    private String Zip;

    // Constructor

    public customerModel(int cstID, String name, String phone, String address, String city, String code, String zip) {
        CustomerID = cstID;
        Name = name;
        Phone = phone;
        Address = address;
        City = city;
        StateCode = code;
        Zip = zip;
    }

    // Set Methods

    public void setCustomerID(int ID) { CustomerID = ID; }
    public void setName(String name) { Name = name; }
    public void setPhone(String phone) { Phone = phone; }
    public void setAddress(String address) { Address = address; }
    public void setCity(String city) { City = city; }
    public void setStateCode(String code) { StateCode = code; }
    public void setZip(String zip) { Zip = zip; }

    // Get Methods

    public int getCustomerID() { return CustomerID; }
    public String getName() { return Name; }
    public String getPhone() { return Phone; }
    public String getAddress() { return Address; }
    public String getCity() { return City; }
    public String getStateCode() { return StateCode; }
    public String getZip() { return Zip; }
}
