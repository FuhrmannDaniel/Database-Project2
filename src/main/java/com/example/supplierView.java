package com.example;

import javax.swing.*;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class supplierView extends JFrame {
    // Supplier Information Text Fields
    public JTextField supplierIDTextField = new JTextField(10);
    public JTextField nameTextField = new JTextField(10);
    public JTextField phoneTextField = new JTextField(10);
    public JTextField addressTextField = new JTextField(10);
    public JTextField cityTextField = new JTextField(10);
    public JTextField stateCodeTextField = new JTextField(10);
    public JTextField zipTextField = new JTextField(10);

    // CRUD Buttons
    public JButton addSupplierButton = new JButton("Add Supplier");
    public JButton readSupplierButton = new JButton("Read Supplier");
    public JButton updateSupplierButton = new JButton("Update Supplier");
    public JButton deleteSupplierButton = new JButton("Delete Supplier");

    public supplierView() {
        this.setSize(700,415);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        // Window Title
        JLabel titleLabel = new JLabel("Manage Suppliers");
        titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 36));
        this.getContentPane().add(titleLabel);

        JPanel infoFieldPanel = new JPanel(new SpringLayout());

        // SupplierID
        JLabel supplierIDLabel = new JLabel("SupplierID:");
        supplierIDLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        infoFieldPanel.add(supplierIDLabel);
        infoFieldPanel.add(supplierIDTextField);

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
        buttonPanel.add(addSupplierButton);
        buttonPanel.add(readSupplierButton);
        buttonPanel.add(updateSupplierButton);
        buttonPanel.add(deleteSupplierButton);

        SpringUtilities.makeCompactGrid(infoFieldPanel,7, 2, 6, 6, 6, 6);
        SpringUtilities.makeCompactGrid(buttonPanel, 1, 4, 6, 25, 6, 6);
        this.getContentPane().add(infoFieldPanel);
        this.getContentPane().add(buttonPanel);

        // Create Supplier
        this.addSupplierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // SupplierID
                int SupplierID = 0;
                String id = supplierIDTextField.getText().trim();
                try {
                    SupplierID = Integer.parseInt(id);
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid SupplierID.");
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

                supplierModel supplier = new supplierModel(SupplierID, name, phone, address, city, stateCode, zip);

                boolean result = App.getInstance().dataAdapter.createSupplier(supplier);
                if (!result) {
                    JOptionPane.showMessageDialog(null, "An error was encountered adding the supplier to the database.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Supplier Added!");
                }
            }
        });

        // Update Supplier
        this.updateSupplierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // SupplierID
                int supID;
                try {
                    supID = Integer.parseInt(supplierIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid SupplierID.");
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

                supplierModel supplier = new supplierModel(supID, name, phone, address, city, stateCode, zip);
                boolean result = App.getInstance().dataAdapter.updateSupplier(supplier);
                if (!result) {
                    JOptionPane.showMessageDialog(null, "An error was encountered updating the supplier.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Supplier Updated!");
                }
            }
        });

        // Delete Supplier
        this.deleteSupplierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int supID;
                try {
                    supID = Integer.parseInt(supplierIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid SupplierID.");
                    return;
                }

                boolean result = App.getInstance().dataAdapter.deleteSupplier(supID);
                if (!result) {
                    JOptionPane.showMessageDialog(null, "An error was encountered deleting the supplier from the database.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Supplier Deleted!");
                }
            }
        });

        // Read Supplier
        this.readSupplierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int supID;
                try {
                    supID = Integer.parseInt(supplierIDTextField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid SupplierID.");
                    return;
                }

                supplierModel supplier = App.getInstance().dataAdapter.readSupplier(supID);
                if (supplier == null) {
                    JOptionPane.showMessageDialog(null, "An error was encountered reading the supplier from the database.");
                }
                else {
                    nameTextField.setText(supplier.getName());
                    phoneTextField.setText(supplier.getPhone());
                    addressTextField.setText(supplier.getAddress());
                    cityTextField.setText(supplier.getCity());
                    stateCodeTextField.setText(supplier.getStateCode());
                    zipTextField.setText(supplier.getZip());
                }
            }
        });
    }
}
