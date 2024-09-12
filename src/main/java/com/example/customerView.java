package com.example;

import javax.swing.*;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class customerView extends JFrame{
    // Customer Information Text Fields
    public JTextField customerIDTextField = new JTextField(10);
    public JTextField nameTextField = new JTextField(10);
    public JTextField phoneTextField = new JTextField(10);
    public JTextField addressTextField = new JTextField(10);
    public JTextField cityTextField = new JTextField(10);
    public JTextField stateCodeTextField = new JTextField(10);
    public JTextField zipTextField = new JTextField(10);

    // CRUD Buttons
    public JButton addCustomerButton = new JButton("Add Customer");
    public JButton readCustomerButton = new JButton("Read Customer");
    public JButton updateCustomerButton = new JButton("Update Customer");
    public JButton deleteCustomerButton = new JButton("Delete Customer");

    public customerView() {
        this.setSize(700,415);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        // Window Title
        JLabel titleLabel = new JLabel("Manage Customers");
        titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 36));
        this.getContentPane().add(titleLabel);

        JPanel infoFieldPanel = new JPanel(new SpringLayout());

        // CustomerID
        JLabel customerIDLabel = new JLabel("CustomerID:");
        customerIDLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        infoFieldPanel.add(customerIDLabel);
        infoFieldPanel.add(customerIDTextField);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        infoFieldPanel.add(nameLabel);
        infoFieldPanel.add(nameTextField);

        // Phone
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        infoFieldPanel.add(phoneLabel);
        infoFieldPanel.add(phoneTextField);

        // Address
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        infoFieldPanel.add(addressLabel);
        infoFieldPanel.add(addressTextField);

        // City
        JLabel cityLabel = new JLabel("City:");
        cityLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        infoFieldPanel.add(cityLabel);
        infoFieldPanel.add(cityTextField);

        // StateCode
        JLabel stateCodeLabel = new JLabel("State Code:");
        stateCodeLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        infoFieldPanel.add(stateCodeLabel);
        infoFieldPanel.add(stateCodeTextField);

        // Zip
        JLabel zipLabel = new JLabel("Zip:");
        zipLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        infoFieldPanel.add(zipLabel);
        infoFieldPanel.add(zipTextField);

        // Buttons
        JPanel buttonPanel = new JPanel(new SpringLayout());
        buttonPanel.add(addCustomerButton);
        buttonPanel.add(readCustomerButton);
        buttonPanel.add(updateCustomerButton);
        buttonPanel.add(deleteCustomerButton);

        SpringUtilities.makeCompactGrid(infoFieldPanel,7, 2, 6, 6, 6, 6);
        SpringUtilities.makeCompactGrid(buttonPanel, 1, 4, 6, 25, 6, 6);
        this.getContentPane().add(infoFieldPanel);
        this.getContentPane().add(buttonPanel);

        // Add Customer
        this.addCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // CustomerID
                int CustomerID = 0;
                String id = customerIDTextField.getText().trim();
                try {
                    CustomerID = Integer.parseInt(id);
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid CustomerID.");
                    return;
                }

                // Name
                String name = nameTextField.getText().trim();
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid name.");
                    return;
                }

                // Phone
                String phone = phoneTextField.getText().trim();
                if (phone.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid phone.");
                    return;
                }

                // Address
                String address = addressTextField.getText().trim();
                if (address.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid address.");
                    return;
                }

                // City
                String city = cityTextField.getText().trim();
                if (city.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid city.");
                    return;
                }

                // State Code
                String stateCode = stateCodeTextField.getText().trim();
                if (stateCode.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid State Code.");
                    return;
                }

                // Zip
                String zip = zipTextField.getText().trim();
                if (zip.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid zip.");
                    return;
                }

                customerModel customer = new customerModel(CustomerID, name, phone, address, city, stateCode, zip);

                boolean result = App.getInstance().dataAdapter.createCustomer(customer);
                if (!result) {
                    JOptionPane.showMessageDialog(null, "An error was encountered adding the customer to the database.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Customer Added!");
                }
            }
        });

        // Update Customer
        this.updateCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // CustomerID
                int cstID;
                try {
                    cstID = Integer.parseInt(customerIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid CustomerID.");
                    return;
                }
                
                // Name
                String name = nameTextField.getText().trim();
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid name.");
                    return;
                }

                // Phone
                String phone = phoneTextField.getText().trim();
                if (phone.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid phone.");
                    return;
                }

                // Address
                String address = addressTextField.getText().trim();
                if (address.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid address.");
                    return;
                }

                // City
                String city = cityTextField.getText().trim();
                if (city.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid city.");
                    return;
                }

                // State Code
                String stateCode = stateCodeTextField.getText().trim();
                if (stateCode.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid State Code.");
                    return;
                }

                // Zip
                String zip = zipTextField.getText().trim();
                if (zip.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid zip.");
                    return;
                }

                customerModel customer = new customerModel(cstID, name, phone, address, city, stateCode, zip);
                boolean result = App.getInstance().dataAdapter.updateCustomer(customer);
                if (!result) {
                    JOptionPane.showMessageDialog(null, "An error was encountered updating the customer.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Customer Updated!");
                }
            }
        });

        // Delete Customer
        this.deleteCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int cstID;
                try {
                    cstID = Integer.parseInt(customerIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid CustomerID.");
                    return;
                }

                boolean result = App.getInstance().dataAdapter.deleteCustomer(cstID);
                if (!result) {
                    JOptionPane.showMessageDialog(null, "An error was encountered deleting the customer from the database.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Customer Deleted!");
                }
            }
        });

        // Read Customer
        this.readCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int cstID;
                try {
                    cstID = Integer.parseInt(customerIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid CustomerID.");
                    return;
                }

                customerModel customer = App.getInstance().dataAdapter.readCustomer(cstID);
                if (customer == null) {
                    JOptionPane.showMessageDialog(null, "An error was encountered reading the customer from the database.");
                }
                else {
                    nameTextField.setText(customer.getName());
                    phoneTextField.setText(customer.getPhone());
                    addressTextField.setText(customer.getAddress());
                    cityTextField.setText(customer.getCity());
                    stateCodeTextField.setText(customer.getStateCode());
                    zipTextField.setText(customer.getZip());
                }
            }
        });
    }
}
