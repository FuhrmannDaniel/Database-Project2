package com.example;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class paymentView extends JFrame {
    // Payment Fields
    public JTextField orderIDTextField = new JTextField(10);
    public JTextField cardNumberTextField = new JTextField(10);
    public JTextField nameTextField = new JTextField(10);
    public JTextField expirationDateTextField = new JTextField(10);
    public JTextField cvvTextField = new JTextField(10);

    // Payment CRUD Buttons
    public JButton addPaymentButton = new JButton("Add Payment");
    public JButton readPaymentButton = new JButton("Read Payments");
    public JButton updatePaymentButton = new JButton("Update Payment");
    public JButton deletePaymentButton = new JButton("Delete Payment");

    public paymentView() {
        this.setSize(660, 325);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        // Window Title
        JLabel titleLabel = new JLabel("Manage Payments");
        titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 36));
        this.getContentPane().add(titleLabel);

        JPanel paymentInfoPanel = new JPanel(new SpringLayout());

        // OrderID
        JLabel orderIDLabel = new JLabel("OrderID: ");
        paymentInfoPanel.add(orderIDLabel);
        paymentInfoPanel.add(orderIDTextField);

        // Card Number
        JLabel cardNumberLabel = new JLabel("Card Number: ");
        paymentInfoPanel.add(cardNumberLabel);
        paymentInfoPanel.add(cardNumberTextField);

        // Name
        JLabel nameLabel = new JLabel("Card Holder Name: ");
        paymentInfoPanel.add(nameLabel);
        paymentInfoPanel.add(nameTextField);

        // Expiration Date
        JLabel expirationDateLabel = new JLabel("Card Expiration Date: ");
        paymentInfoPanel.add(expirationDateLabel);
        paymentInfoPanel.add(expirationDateTextField);

        // CVV
        JLabel cvvLabel = new JLabel("Card CVV: ");
        paymentInfoPanel.add(cvvLabel);
        paymentInfoPanel.add(cvvTextField);

        // Buttons
        JPanel buttonPanel = new JPanel(new SpringLayout());
        buttonPanel.add(addPaymentButton);
        buttonPanel.add(readPaymentButton);
        buttonPanel.add(updatePaymentButton);
        buttonPanel.add(deletePaymentButton);

        SpringUtilities.makeCompactGrid(paymentInfoPanel, 5, 2, 6, 6, 6, 6);
        SpringUtilities.makeCompactGrid(buttonPanel, 1, 4, 6, 6, 6, 6);

        this.getContentPane().add(paymentInfoPanel);
        this.getContentPane().add(buttonPanel);

        // Create Payment
        this.addPaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // OrderID
                int orderID;
                try {
                    orderID = Integer.parseInt(orderIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid OrderID.");
                    return;
                }

                // Card Number
                String cardNumber = cardNumberTextField.getText().trim();
                if (cardNumber.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid Card Number.");
                    return;
                }

                // Name
                String name = nameTextField.getText().trim();
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid name.");
                    return;
                }

                // Date
                String expirationDate = expirationDateTextField.getText();
                if (expirationDate.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid Expiration Date.");
                    return;
                }

                // CVV
                int CVV;
                try {
                    CVV = Integer.parseInt(cvvTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid CVV.");
                    return;
                }

                orderModel payment = new orderModel(orderID, cardNumber, name, expirationDate, CVV);

                boolean result = App.getInstance().dataAdapter.createPayment(payment);
                if (!result) {
                    JOptionPane.showMessageDialog(null, "An error was encountered creating the payment.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Payment Created!");
                }
            }
        });

        // Update Payment
        this.updatePaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // OrderID
                int orderID;
                try {
                    orderID = Integer.parseInt(orderIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid OrderID.");
                    return;
                }

                // Card Number
                String cardNumber = cardNumberTextField.getText().trim();
                if (cardNumber.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid Card Number.");
                    return;
                }

                // Name
                String name = nameTextField.getText().trim();
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid name.");
                    return;
                }

                // Date
                String expirationDate = expirationDateTextField.getText();
                if (expirationDate.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid Expiration Date.");
                    return;
                }

                // CVV
                int CVV;
                try {
                    CVV = Integer.parseInt(cvvTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid CVV.");
                    return;
                }

                orderModel payment = new orderModel(orderID, cardNumber, name, expirationDate, CVV);

                boolean result = App.getInstance().dataAdapter.updatePayment(payment);
                if (!result) {
                    JOptionPane.showMessageDialog(null, "An error was encountered updating the payment.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Payment Updated!");
                }
            }
        });

        // Delete Payment
        this.deletePaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int orderID;
                try {
                    orderID = Integer.parseInt(orderIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid OrderID.");
                    return;
                }

                boolean result = App.getInstance().dataAdapter.deletePayment(orderID);
                if (!result) {
                    JOptionPane.showMessageDialog(null, "An error was encountered deleting the payment.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Payment Deleted!");
                }
            }
        });

        // Read Payments
        this.readPaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int orderID;
                try {
                    orderID = Integer.parseInt(orderIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid OrderID.");
                    return;
                }

                orderModel payment = App.getInstance().dataAdapter.readPayment(orderID);
                if (payment == null) {
                    JOptionPane.showMessageDialog(null, "An error was encountered reading the payment from the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    cardNumberTextField.setText(payment.getCardNumber());
                    nameTextField.setText(payment.getCardName());
                    expirationDateTextField.setText(payment.getExpirationDate());
                    cvvTextField.setText(String.valueOf(payment.getCVV()));
                }
            }
        });
    }
}
