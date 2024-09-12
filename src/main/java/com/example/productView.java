package com.example;

import java.math.BigDecimal;
import javax.swing.*;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class productView extends JFrame {
    // Product Information Fields
    public JTextField productIDTextField = new JTextField(10);
    public JTextField nameTextField = new JTextField(10);
    public JTextField priceTextField = new JTextField(10);
    public JTextField quantityTextField = new JTextField(10);
    public JTextField supplierIDTextField = new JTextField(10);

    // CRUD Buttons
    public JButton addProductButton = new JButton("Add Product");
    public JButton readProductButton = new JButton("Read Product");
    public JButton updateProductButton = new JButton("Update Product");
    public JButton deleteProductButton = new JButton("Delete Product");

    public productView() {
        this.setSize(650,375);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        // Window Title
        JLabel titleLabel = new JLabel("Manage Products");
        titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 36));
        this.getContentPane().add(titleLabel);

        JPanel infoFieldPanel = new JPanel(new SpringLayout());

        // ProductID
        JLabel productIDLabel = new JLabel("ProductID:");
        productIDLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        infoFieldPanel.add(productIDLabel);
        infoFieldPanel.add(productIDTextField);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        infoFieldPanel.add(nameLabel);
        infoFieldPanel.add(nameTextField);

        // Price
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        infoFieldPanel.add(priceLabel);
        infoFieldPanel.add(priceTextField);

        // Quantity
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        infoFieldPanel.add(quantityLabel);
        infoFieldPanel.add(quantityTextField);

        // SupplierID
        JLabel supplierIDLabel = new JLabel("SupplierID:");
        supplierIDLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        infoFieldPanel.add(supplierIDLabel);
        infoFieldPanel.add(supplierIDTextField);

        // Buttons
        JPanel buttonPanel = new JPanel(new SpringLayout());
        buttonPanel.add(addProductButton);
        buttonPanel.add(readProductButton);
        buttonPanel.add(updateProductButton);
        buttonPanel.add(deleteProductButton);

        SpringUtilities.makeCompactGrid(infoFieldPanel,5, 2, 6, 6, 6, 6);
        SpringUtilities.makeCompactGrid(buttonPanel, 1, 4, 6, 25, 6, 6);
        this.getContentPane().add(infoFieldPanel);
        this.getContentPane().add(buttonPanel);

        // Create Product
        this.addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Product ID
                int productID;
                try {
                    productID = Integer.parseInt(productIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ProductID.");
                    return;
                }
                
                // Name
                String name = nameTextField.getText().trim();
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid name.");
                    return;
                }

                // Price
                /*  Gemini. "Parse JTextField ..." A.I. Archives, 24 March 2024,
                    https://aiarchives.org/id/Fq3uu3dmzG1oAjwYYj65-p    */
                BigDecimal price;
                try {
                    price = new BigDecimal(priceTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid price.");
                    return;
                }

                // Quantity
                BigDecimal quantity;
                try {
                    quantity = new BigDecimal(quantityTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity.");
                    return;
                }

                // Supplier ID
                int supplierID;
                try {
                    supplierID = Integer.parseInt(supplierIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid SupplierID.");
                    return;
                }

                productModel product = new productModel(productID, name, price, quantity, supplierID);

                boolean result = App.getInstance().dataAdapter.createProduct(product);
                if (!result) {
                    JOptionPane.showMessageDialog(null, "An error was encountered adding the product to the database.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Product Added!");
                }
            }
        });

        // Update Product
        this.updateProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Product ID
                int prdID;
                try {
                    prdID = Integer.parseInt(productIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ProductID.");
                    return;
                }

                // Name
                String name = nameTextField.getText().trim();
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid name.");
                    return;
                }

                // Price
                BigDecimal price;
                try {
                    price = new BigDecimal(priceTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid price.");
                    return;
                }

                // Quantity
                BigDecimal quantity;
                try {
                    quantity = new BigDecimal(quantityTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity.");
                    return;
                }

                // Supplier ID
                int supplierID;
                try {
                    supplierID = Integer.parseInt(supplierIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid SupplierID.");
                    return;
                }

                productModel product = new productModel(prdID, name, price, quantity, supplierID);
                boolean result = App.getInstance().dataAdapter.updateProduct(product);
                if (!result) {
                    JOptionPane.showMessageDialog(null, "An error was encountered updating the product.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Product Updated!");
                }
            }
        });

        // Delete Product
        this.deleteProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int prdID;
                try {
                    prdID = Integer.parseInt(productIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ProductID.");
                    return;
                }

                boolean result = App.getInstance().dataAdapter.deleteProduct(prdID);
                if (!result) {
                    JOptionPane.showMessageDialog(null, "An error was encountered deleting the product.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Product Deleted!");
                }
            }
        });

        // Read Product
        this.readProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int prdID;
                try {
                    prdID = Integer.parseInt(productIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ProductID.");
                    return;
                }

                productModel product = App.getInstance().dataAdapter.readProduct(prdID);
                if (product == null) {
                    JOptionPane.showMessageDialog(null, "An error was encountered reading the product.");
                }
                else {
                    nameTextField.setText(product.getName());
                    priceTextField.setText(String.valueOf(product.getPrice()));
                    quantityTextField.setText(String.valueOf(product.getQuantity()));
                    supplierIDTextField.setText(String.valueOf(product.getSupplierID()));
                }
            }
        });
    }
}
