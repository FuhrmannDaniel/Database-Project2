package com.example;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface DataAccess {
    public boolean connect();
    public boolean disconnect();

    public boolean createCustomer(customerModel customer);
    public boolean updateCustomer(customerModel customer);
    public customerModel readCustomer(int cstID);
    public boolean deleteCustomer(int cstID);

    public boolean createSupplier(supplierModel supplier);
    public boolean updateSupplier(supplierModel supplier);
    public supplierModel readSupplier(int supID);
    public boolean deleteSupplier(int supID);

    public boolean createProduct(productModel product);
    public boolean updateProduct(productModel product);
    public productModel readProduct(int prdID);
    public boolean deleteProduct(int prdID);

    public boolean createOrder(orderModel order);
    public boolean updateOrder(orderModel order);
    public orderModel readOrder(int ordID);
    public boolean deleteOrder(int ordID);

    public ArrayList<productModel> getAllProducts();
    public BigDecimal getPrice(int prdID);

    public boolean createPayment(orderModel order);
    public boolean updatePayment(orderModel order);
    public orderModel readPayment(int OrderID);
    public boolean deletePayment(int OrderID);

    public boolean isOrderPaid(int OrderID);
}
