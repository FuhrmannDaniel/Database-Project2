package com.example;

//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.sql.*;
//import java.util.ArrayList;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

public class remoteDataAdapter implements DataAccess {
    private Jedis jedis = null;

    public boolean connect() {
        try {
            System.out.println("Connecting to Redis Database");
            jedis = new Jedis("redis://default:nptybjkbA7N@redis-16760.c16.us-east-1-2.ec2.redns.redis-cloud.com:16760");
            return jedis.isConnected();
        }
        catch (JedisException e) {
            System.out.println("Error encountered connecting to Redis DB: " + e.getMessage());
            System.exit(2);
            return false;
        }
    }
    public boolean disconnect() {
        try {
            System.out.println("Disconnecting from Redis DB");
            jedis.close();
            return true;
        }
        catch (JedisException e) {
            System.out.println("Error encountered disconnecting from Redis DB: " + e.getMessage());
            return false;
        }
    }

    /*****************************************
     * Customer : Create, Update, Read, Delete
     ****************************************/

    public boolean createCustomer(customerModel customer) {
        System.out.println("Adding Customer to Redis DB");

        Map<String, String> newCustomer = new HashMap<>();
        newCustomer.put("Name", customer.getName());
        newCustomer.put("Phone", customer.getPhone());
        newCustomer.put("Address", customer.getAddress());
        newCustomer.put("City", customer.getCity());
        newCustomer.put("StateCode", customer.getStateCode());
        newCustomer.put("Zip", customer.getZip());

        String customerID = "customer:" + customer.getCustomerID();

        try {
            long result = jedis.hset(customerID, newCustomer);
            return result == 6;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }

    public boolean updateCustomer(customerModel customer) {
        System.out.println("Updating Customer in Redis DB");
        
        Map<String, String> customerUpdate = new HashMap<>();
        customerUpdate.put("Name", customer.getName());
        customerUpdate.put("Phone", customer.getPhone());
        customerUpdate.put("Address", customer.getAddress());
        customerUpdate.put("City", customer.getCity());
        customerUpdate.put("StateCode", customer.getStateCode());
        customerUpdate.put("Zip", customer.getZip());

        String customerID = "customer:" + customer.getCustomerID();

        try {
            jedis.hset(customerID, customerUpdate);
            return true;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }
    
    public customerModel readCustomer(int cstID) {
        System.out.println("Reading Customer from Redis DB");
        
        customerModel customerModel = null;
        String customerID = "customer:" + cstID;

        try {
            Map<String, String> customer = jedis.hgetAll(customerID);
            // https://g.co/gemini/share/b3ca77927984
            if (!customer.containsKey("Name") || !customer.containsKey("Phone") || !customer.containsKey("Address") || !customer.containsKey("City") || !customer.containsKey("StateCode") || !customer.containsKey("Zip")) {
                System.out.println("Error encountered reading customer from Redis DB");
                return null;
            }

            customerModel = new customerModel(cstID, customer.get("Name"), customer.get("Phone"), customer.get("Address"), customer.get("City"), customer.get("StateCode"), customer.get("Zip"));

            return customerModel;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return customerModel;
        }
    }
    
    public boolean deleteCustomer(int cstID) {
        System.out.println("Deleting Customer from Redis DB");

        // https://g.co/gemini/share/801495c14fcd
        try {
            String customerID = "customer:" + cstID;

            long result = jedis.del(customerID);
            return result == 1;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }
    

    /*****************************************
     * Supplier : Create, Update, Read, Delete
     ****************************************/

    public boolean createSupplier(supplierModel supplier) {
        System.out.println("Adding Supplier to Redis DB");

        Map<String, String> newSupplier = new HashMap<>();
        newSupplier.put("Name", supplier.getName());
        newSupplier.put("Phone", supplier.getPhone());
        newSupplier.put("Address", supplier.getAddress());
        newSupplier.put("City",supplier.getCity());
        newSupplier.put("StateCode", supplier.getStateCode());
        newSupplier.put("Zip", supplier.getZip());

        String supplierID = "supplier:" + supplier.getSupplierID();

        try {
            long result = jedis.hset(supplierID, newSupplier);
            return result == 6;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }

    public boolean updateSupplier(supplierModel supplier) {
        System.out.println("Updating Supplier in Redis DB");

        Map<String, String> newSupplier = new HashMap<>();
        newSupplier.put("Name", supplier.getName());
        newSupplier.put("Phone", supplier.getPhone());
        newSupplier.put("Address", supplier.getAddress());
        newSupplier.put("City",supplier.getCity());
        newSupplier.put("StateCode", supplier.getStateCode());
        newSupplier.put("Zip", supplier.getZip());

        String supplierID = "supplier:" + supplier.getSupplierID();

        try {
            jedis.hset(supplierID, newSupplier);
            return true;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }

    public supplierModel readSupplier(int supID) {
        System.out.println("Reading Supplier from Redis DB");

        supplierModel supplierModel = null;
        String supplierID = "supplier:" + supID;

        try {
            Map<String, String> supplier = jedis.hgetAll(supplierID);
            if (!supplier.containsKey("Name") || !supplier.containsKey("Phone") || !supplier.containsKey("Address") || !supplier.containsKey("City") || !supplier.containsKey("StateCode") || !supplier.containsKey("Zip")) {
                System.out.println("Error encountered reading supplier from Redis DB");
                return null;
            }

            supplierModel = new supplierModel(supID, supplier.get("Name"), supplier.get("Phone"), supplier.get("Address"), supplier.get("City"), supplier.get("StateCode"), supplier.get("Zip"));

            return supplierModel;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return supplierModel;
        }
    }

    public boolean deleteSupplier(int supID) {
        System.out.println("Deleting Supplier from Redis DB");

        try {
            String supplierID = "supplier:" + supID;

            long result = jedis.del(supplierID);
            return result == 1;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }


    /****************************************
     * Product : Create, Update, Read, Delete
     ***************************************/
    
    public boolean createProduct(productModel product) {
        System.out.println("Adding Product to Redis DB");

        Map<String, String> newProduct = new HashMap<>();
        newProduct.put("Name", product.getName());
        newProduct.put("Price", product.getPrice().toString());
        newProduct.put("Quantity", product.getQuantity().toString());
        newProduct.put("SupplierID", "supplier:" + String.valueOf(product.getSupplierID()));

        String productID = "product:" + product.getProductID();

        try {
            long result = jedis.hset(productID, newProduct);
            return result == 4;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }

    public boolean updateProduct(productModel product) {
        System.out.println("Updating Product in Redis DB");

        Map<String, String> newProduct = new HashMap<>();
        newProduct.put("Name", product.getName());
        newProduct.put("Price", product.getPrice().toString());
        newProduct.put("Quantity", product.getQuantity().toString());
        newProduct.put("SupplierID", "supplier:" + String.valueOf(product.getSupplierID()));

        String productID = "product:" + product.getProductID();

        try {
            jedis.hset(productID, newProduct);
            return true;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }

    public productModel readProduct(int prdID) {
        System.out.println("Reading Product " + prdID + " from Redis DB");

        productModel productModel = null;
        String productID = "product:" + prdID;

        try {
            Map<String, String> product = jedis.hgetAll(productID);

            if (!product.containsKey("Name") || !product.containsKey("Price") || !product.containsKey("Quantity") || !product.containsKey("SupplierID")) {
                System.out.println("Error encountered reading product from Redis DB");
                System.out.println(product.containsKey("Name"));
                System.out.println(product.containsKey("Price"));
                System.out.println(product.containsKey("Quantity"));
                System.out.println(product.containsKey("SupplierID"));
                return null;
            }

            BigDecimal price = new BigDecimal(product.get("Price"));
            BigDecimal quantity = new BigDecimal(product.get("Quantity"));
            int supplierID = Integer.valueOf(product.get("SupplierID").substring(9));

            productModel = new productModel(prdID, product.get("Name"), price, quantity, supplierID);

            return productModel;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return productModel;
        }
    }

    public boolean deleteProduct(int prdID) {
        System.out.println("Deleting Product from Redis DB");

        try {
            String productID = "product:" + prdID;

            long result = jedis.del(productID);
            return result == 1;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }


    /**************************************
     * Order : Create, Update, Read, Delete
     *************************************/

    public boolean createOrder(orderModel order) {
        System.out.println("Adding Order to Redis DB");

        String customerID = "customer:" + order.getCustomerID();
        String productID = "product:" + order.getProductID();

        Map<String, String> newOrder = new HashMap<>();
        newOrder.put("CustomerID", customerID);
        newOrder.put("ProductID", productID);
        newOrder.put("Date", order.getOrderDate().toString());
        newOrder.put("Quantity", order.getQuantity().toString());
        newOrder.put("Cost", order.getCost().toString());

        newOrder.put("CardNumber", "");
        newOrder.put("CardName", "");
        newOrder.put("ExpirationDate", "");
        newOrder.put("CVV", "");

        String orderID = "order:" + order.getOrderID();

        try {
            long result = jedis.hset(orderID, newOrder);
            if (result == 9) {
                double value = Double.parseDouble(order.getQuantity().negate().toString());
                jedis.hincrByFloat(productID, "Quantity", value);
                return true;
            }
            else {
                System.out.println("Failed to update product inventory");
                return false;
            }
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }

    public boolean updateOrder(orderModel order) {
        System.out.println("Updating Order in Redis DB");

        String customerID = "customer:" + order.getCustomerID();
        String productID = "product:" + order.getProductID();

        Map<String, String> newOrder = new HashMap<>();
        newOrder.put("CustomerID", customerID);
        newOrder.put("ProductID", productID);
        newOrder.put("Date", order.getOrderDate().toString());
        newOrder.put("Quantity", order.getQuantity().toString());
        newOrder.put("Cost", order.getCost().toString());

        String orderID = "order:" + order.getOrderID();

        try {
            jedis.hincrByFloat(jedis.hget(orderID, "ProductID"), "Quantity", Double.parseDouble(jedis.hget(orderID, "Quantity")));

            jedis.hset(orderID, newOrder);
            
            double value = Double.parseDouble(order.getQuantity().negate().toString());
            jedis.hincrByFloat(productID, "Quantity", value);

            return true;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }

    public orderModel readOrder(int ordID) {
        System.out.println("Reading Order from Redis DB");

        orderModel orderModel = null;
        String orderID = "order:" + ordID;

        try {
            Map<String, String> order = jedis.hgetAll(orderID);

            if (!order.containsKey("CustomerID") || !order.containsKey("ProductID") || !order.containsKey("Quantity") || !order.containsKey("Cost") || !order.containsKey("CardNumber") || !order.containsKey("CardName") || !order.containsKey("ExpirationDate") || !order.containsKey("CVV") || !order.containsKey("Date")) {
                System.out.println("Error encountered reading order from Redis DB");
                return null;
            }

            int CustomerID = Integer.valueOf(order.get("CustomerID").substring(9));
            int ProductID = Integer.valueOf(order.get("ProductID").substring(8));
            BigDecimal quantity = new BigDecimal(order.get("Quantity"));
            BigDecimal cost = new BigDecimal(order.get("Cost"));

            orderModel = new orderModel(ordID, CustomerID, Date.valueOf(order.get("Date")), ProductID, quantity, cost, order.get("CardNumber"), order.get("CardName"), order.get("ExpirationDate"), Integer.parseInt(order.get("CVV")));
            return orderModel;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return orderModel;
        }
    }

    public boolean deleteOrder(int ordID) {
        System.out.println("Deleting Order from Redis DB");

        try {
            String orderID = "order:" + ordID;
            double quantity = Double.parseDouble(jedis.hget(orderID, "Quantity"));
            String productID = jedis.hget(orderID, "ProductID");
            
            long result = jedis.del(orderID);
            if (result == 1) {
                jedis.hincrByFloat(productID, "Quantity", quantity);
                return true;
            }
            else {
                return false;
            }
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }


    /****************************************
     * Payment : Create, Update, Read, Delete
     ***************************************/
    
    public boolean createPayment(orderModel order) {
        System.out.println("Adding Payment to Redis DB");

        Map<String, String> newPayment = new HashMap<>();
        newPayment.put("CardNumber", order.getCardNumber());
        newPayment.put("CardName", order.getCardName());
        newPayment.put("ExpirationDate", order.getExpirationDate());
        newPayment.put("CVV", String.valueOf(order.getCVV()));

        String orderID = "order:" + order.getOrderID();

        try {
            jedis.hset(orderID, newPayment);
            return true;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }

    public boolean updatePayment(orderModel order) {
        System.out.println("Updating Payment in Redis DB");

        Map<String, String> newPayment = new HashMap<>();
        newPayment.put("CardNumber", order.getCardNumber());
        newPayment.put("CardName", order.getCardName());
        newPayment.put("ExpirationDate", order.getExpirationDate());
        newPayment.put("CVV", String.valueOf(order.getCVV()));

        String orderID = "order:" + order.getOrderID();

        try {
            jedis.hset(orderID, newPayment);
            return true;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }

    public orderModel readPayment(int ordID) {
        System.out.println("Reading Payment from Redis DB");

        orderModel orderModel = null;
        String orderID = "order:" + ordID;

        try {
            Map<String, String> order = jedis.hgetAll(orderID);

            if (!order.containsKey("CardNumber") || !order.containsKey("CardName") || !order.containsKey("CVV") || !order.containsKey("ExpirationDate")) {
                System.out.println("Error encountered reading payment from Redis DB");
                return null;
            }

            orderModel = new orderModel(ordID, order.get("CardNumber"), order.get("CardName"), order.get("ExpirationDate"), Integer.parseInt(order.get("CVV")));
            return orderModel;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return orderModel;
        }
    }

    public boolean deletePayment(int OrderID) {
        System.out.println("Deleting Payment from Redis DB");

        Map<String, String> newPayment = new HashMap<>();
        newPayment.put("CardNumber", "");
        newPayment.put("CardName", "");
        newPayment.put("ExpirationDate", "");
        newPayment.put("CVV", "");

        String orderID = "order:" + OrderID;

        try {
            jedis.hset(orderID, newPayment);
            return true;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }

    
    /*********
     * Helpers
     ********/
    public ArrayList<productModel> getAllProducts() {
        System.out.println("Reading all products from Redis DB");

        Set<String> productKeys = jedis.keys("product:*");

        ArrayList<productModel> productModels = new ArrayList<>();

        for (String prdKey : productKeys) {
            System.out.println("Adding " + prdKey + " to return ArrayList");
            productModels.add(readProduct(Integer.valueOf(prdKey.substring(8))));
        }

        return productModels;
    }

    public BigDecimal getPrice(int prdID) {
        try {
            String productID = "product:" + prdID;
            BigDecimal price = new BigDecimal(jedis.hget(productID, "Price"));

            return price;
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return BigDecimal.valueOf(-1);
        }
        catch (NumberFormatException ex) {
            System.out.println("Error encountered: " + ex.getMessage());
            return BigDecimal.valueOf(-1);
        }
    }

    public boolean isOrderPaid(int ordID) {
        try {
            String orderID = "order:" + ordID;
            String result = jedis.hget(orderID, "CardNumber");
            return !(result.equals(""));
        }
        catch (JedisException e) {
            System.out.println("Error encountered: " + e.getMessage());
            return false;
        }
    }
}
