package com.example;

public class App {
    public DataAccess dataAdapter = new remoteDataAdapter();
    
    private home home = new home();
    private customerView customerView = new customerView();
    private supplierView supplierView = new supplierView();
    private productView productView = new productView();
    private orderView orderView = new orderView();
    private paymentView paymentView = new paymentView();

    public home getHome() { return home; }
    public customerView getCustomerView() { return customerView; }
    public supplierView getSupplierView() { return supplierView; }
    public productView getProductView() { return productView; }
    public orderView getOrderView() { return orderView; }
    public paymentView getPaymentView() { return paymentView; }

    private static App instance;
    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        App.getInstance().dataAdapter.connect();
        //testDataAccess(App.getInstance().dataAdapter);
        App.getInstance().getHome().setVisible(true);
    }
}
