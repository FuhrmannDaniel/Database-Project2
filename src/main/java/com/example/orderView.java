package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class orderView extends JFrame{
    // Order Information Text Fields
    public JTextField orderIDTextField = new JTextField(10);
    public JTextField customerIDTextField = new JTextField(10);
    public JTextField orderDateTextField = new JTextField(10);
    public JTextField paidTextField = new JTextField(10);

    public JLabel costLabel = new JLabel("Total Cost ($): ");
    public JLabel costValueLabel = new JLabel("0.00");

    // Product Information Text Fields
    public JTextField productIDTextField = new JTextField(10);
    public JTextField quantityTextField = new JTextField(10);

    // Order CRUD Buttons
    public JButton addOrderButton = new JButton("Add Order");
    public JButton readOrderButton = new JButton("Read Order");
    public JButton updateOrderButton = new JButton("Update Order");
    public JButton deleteOrderButton = new JButton("Delete Order");

    // Read Products Button
    public JButton readProductsButton = new JButton("Read Products");

    private DefaultTableModel products = new DefaultTableModel();
    private JTable productsTable = new JTable(products);

    public orderView() {
        this.setSize(590, 600);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        // Window Title
        JLabel titleLabel = new JLabel("Manage Orders");
        titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 36));
        this.getContentPane().add(titleLabel);

        JPanel orderInfoPanel = new JPanel(new SpringLayout());

        // OrderID
        JLabel orderIDLabel = new JLabel("OrderID: ");
        orderInfoPanel.add(orderIDLabel);
        orderInfoPanel.add(orderIDTextField);

        // CustomerID
        JLabel customerIDLabel = new JLabel("CustomerID: ");
        orderInfoPanel.add(customerIDLabel);
        orderInfoPanel.add(customerIDTextField);

        // OrderDate
        JLabel orderDateLabel = new JLabel("Order Date: ");
        orderInfoPanel.add(orderDateLabel);
        orderInfoPanel.add(orderDateTextField);
        
        // ProductID
        JLabel productIDLabel = new JLabel("ProductID: ");
        orderInfoPanel.add(productIDLabel);
        orderInfoPanel.add(productIDTextField);

        // Quantity
        JLabel quantityLabel = new JLabel("Quantity");
        orderInfoPanel.add(quantityLabel);
        orderInfoPanel.add(quantityTextField);
        
        // Cost
        orderInfoPanel.add(costLabel);
        orderInfoPanel.add(costValueLabel);

        // Paid Check box
        JCheckBox paidCheckBox = new JCheckBox("Paid");

        // Buttons
        JPanel buttonPanel = new JPanel(new SpringLayout());
        buttonPanel.add(addOrderButton);
        buttonPanel.add(readOrderButton);
        buttonPanel.add(updateOrderButton);
        buttonPanel.add(deleteOrderButton);
        buttonPanel.add(readProductsButton);

        // Products Table
        JPanel productsTablePanel = new JPanel(new SpringLayout());
        products.addColumn("ProductID");
        products.addColumn("Name");
        products.addColumn("Quantity");
        products.addColumn("Cost");

        productsTablePanel.add(productsTable.getTableHeader());
        productsTablePanel.add(new JScrollPane(productsTable));

        SpringUtilities.makeCompactGrid(orderInfoPanel, 6, 2, 6, 6, 6, 6);
        SpringUtilities.makeCompactGrid(buttonPanel, 1, 5, 6, 6, 6, 6);

        productsTable.setFillsViewportHeight(true);

        this.getContentPane().add(orderInfoPanel);
        this.getContentPane().add(paidCheckBox);
        this.getContentPane().add(productsTablePanel);
        this.getContentPane().add(buttonPanel);

        // Read Products
        this.readProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<productModel> productsArrayList = App.getInstance().dataAdapter.getAllProducts();

                // Clear Table
                while (products.getRowCount() > 0) {
                    products.removeRow(0);
                }

                if (productsArrayList == null) {
                    JOptionPane.showMessageDialog(null, "An error was encountered reading the products from the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    for (int index = 0; index < productsArrayList.size(); index++) {
                        productModel product = productsArrayList.get(index);

                        Object[] row = new Object[4];
                        row[0] = product.getProductID();
                        row[1] = product.getName();
                        row[2] = product.getQuantity();
                        row[3] = product.getPrice();

                        products.addRow(row);
                    }
                }
            }
        });

        // Create Order
        this.addOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // OrderID
                int ordID;
                try {
                    ordID = Integer.parseInt(orderIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid OrderID.");
                    return;
                }

                // CustomerID
                int cstID;
                try {
                    cstID = Integer.parseInt(customerIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid CustomerID.");
                    return;
                }

                // Date
                Date ordDate;
                try {
                    ordDate = Date.valueOf(orderDateTextField.getText());
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Order Date. Enter Date in YYYY-MM-DD format.");
                    return;
                }

                // ProductID
                int prdID;
                try {
                    prdID = Integer.parseInt(productIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ProductID.");
                    return;
                }

                // Quantity & Cost
                BigDecimal quantity;
                BigDecimal cost;
                try {
                    quantity = BigDecimal.valueOf(Double.parseDouble(quantityTextField.getText()));
                    cost = App.getInstance().dataAdapter.getPrice(prdID).multiply(quantity);
                    // https://g.co/gemini/share/dd5ed911c947
                    cost = cost.setScale(2, RoundingMode.HALF_EVEN);
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage() + "\nFailed to Parse Quantity", "Invalid Quantity", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // OrderModel
                orderModel order = new orderModel(ordID, cstID, ordDate, prdID, quantity, cost);

                boolean result = App.getInstance().dataAdapter.createOrder(order);
                if (!result) {
                    JOptionPane.showMessageDialog(null, "An error was encountered adding the order to the database.");
                }
                else {
                    costValueLabel.setText(order.getCost().toString());
                    paidCheckBox.setSelected(false);
                    JOptionPane.showMessageDialog(null, "Order Added!");
                }
            }
        });

        // Delete Order
        this.deleteOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // OrderID
                int ordID;
                try {
                    ordID = Integer.parseInt(orderIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid OrderID.");
                    return;
                }

                boolean result = App.getInstance().dataAdapter.deleteOrder(ordID);
                if (!result) {
                    JOptionPane.showMessageDialog(null, "An error was encountered deleting the order.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Order Deleted!");
                }
            }
        });

        // Update Order
        this.updateOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // OrderID
                int ordID;
                try {
                    ordID = Integer.parseInt(orderIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid OrderID.");
                    return;
                }

                // CustomerID
                int cstID;
                try {
                    cstID = Integer.parseInt(customerIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid CustomerID.");
                    return;
                }

                // Date
                Date ordDate;
                try {
                    ordDate = Date.valueOf(orderDateTextField.getText());
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Order Date. Enter Date in YYYY-MM-DD format.");
                    return;
                }

                // ProductID
                int prdID;
                try {
                    prdID = Integer.parseInt(productIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ProductID.");
                    return;
                }

                // Quantity & Cost
                BigDecimal quantity;
                BigDecimal cost;
                try {
                    quantity = BigDecimal.valueOf(Double.parseDouble(quantityTextField.getText()));
                    cost = App.getInstance().dataAdapter.getPrice(prdID).multiply(quantity);
                    cost = cost.setScale(2, RoundingMode.HALF_EVEN);
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage() + "\nFailed to Parse Quantity", "Invalid Quantity", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // OrderModel
                orderModel order = new orderModel(ordID, cstID, ordDate, prdID, quantity, cost);

                boolean result = App.getInstance().dataAdapter.updateOrder(order);
                if (!result) {
                    JOptionPane.showMessageDialog(null, "An error was encountered updating the order.");
                }
                else {
                    costValueLabel.setText(order.getCost().toString());
                    paidCheckBox.setSelected(App.getInstance().dataAdapter.isOrderPaid(ordID));
                    JOptionPane.showMessageDialog(null, "Order Updated!");
                }
            }
        });
    
        // Read Order
        this.readOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // OrderID
                int ordID;
                try {
                    ordID = Integer.parseInt(orderIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage() + "\nOrderID must be an Integer", "Invalid OrderID", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                orderModel order = App.getInstance().dataAdapter.readOrder(ordID);
                if (order == null) {
                    JOptionPane.showMessageDialog(null, "An error was encountered reading the order from the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    customerIDTextField.setText(String.valueOf(order.getCustomerID()));
                    orderDateTextField.setText(String.valueOf(order.getOrderDate()));
                    productIDTextField.setText(String.valueOf(order.getProductID()));
                    quantityTextField.setText(String.valueOf(order.getQuantity()));
                    costValueLabel.setText(String.valueOf(order.getCost()));
                    paidCheckBox.setSelected(App.getInstance().dataAdapter.isOrderPaid(ordID));
                }
            }
        });
    
    }
}
