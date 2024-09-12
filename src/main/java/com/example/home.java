package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class home extends JFrame {
    
    // Home Screen Buttons
    private JButton customerButton = new JButton("Manage Customers");
    private JButton supplierButton = new JButton("Manage Suppliers");
    private JButton productButton = new JButton("Manage Products");
    private JButton orderButton = new JButton("Manage Orders");
    private JButton paymentButton = new JButton("Manage Payments");

    public home() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,400);  // Window Size

        // Button Size
        customerButton.setPreferredSize(new Dimension(250, 50));
        supplierButton.setPreferredSize(new Dimension(250, 50));
        productButton.setPreferredSize(new Dimension(250, 50));
        orderButton.setPreferredSize(new Dimension(250, 50));
        paymentButton.setPreferredSize(new Dimension(250, 50));

        // Window Title
        JLabel titleLabel = new JLabel("Store Management System");
        titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 36));  // Window Title Font
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        this.getContentPane().add(titlePanel);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(customerButton);
        buttonPanel.add(supplierButton);
        buttonPanel.add(productButton);
        buttonPanel.add(orderButton);
        buttonPanel.add(paymentButton);
        this.getContentPane().add(buttonPanel);

        // Customer Button
        customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App.getInstance().getCustomerView().setVisible(true);
            }
        });

        // Supplier Button
        supplierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App.getInstance().getSupplierView().setVisible(true);
            }
        });

        // Product Button
        productButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App.getInstance().getProductView().setVisible(true);
            }
        });

        // Order Button
        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App.getInstance().getOrderView().setVisible(true);
            }
        });

        // Payment Button
        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App.getInstance().getPaymentView().setVisible(true);
            }
        });
    }
}
