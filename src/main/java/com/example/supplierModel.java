package com.example;

public class supplierModel {
    // Private Variables
    private int SupplierID;
    private String Name;
    private String Phone;
    private String Address;
    private String City;
    private String StateCode;
    private String Zip;

    // Constructor

    public supplierModel(int supID, String name, String phone, String address, String city, String code, String zip) {
        SupplierID = supID;
        Name = name;
        Phone = phone;
        Address = address;
        City = city;
        StateCode = code;
        Zip = zip;
    }

    // Set Methods

    public void setSupplierID(int ID) { SupplierID = ID; }
    public void setName(String name) { Name = name; }
    public void setPhone(String phone) { Phone = phone; }
    public void setAddress(String address) { Address = address; }
    public void setCity(String city) { City = city; }
    public void setStateCode(String code) { StateCode = code; }
    public void setZip(String zip) { Zip = zip; }

    // Get Methods

    public int getSupplierID() { return SupplierID; }
    public String getName() { return Name; }
    public String getPhone() { return Phone; }
    public String getAddress() { return Address; }
    public String getCity() { return City; }
    public String getStateCode() { return StateCode; }
    public String getZip() { return Zip; }
}
