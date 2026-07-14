package com.nex.cms.panel.Inventory;

import com.nex.cms.connection.MySQL;
import com.nex.cms.frame.customer.customerReg;
import com.nex.cms.frame.inventory.fstock;
import com.nex.cms.model.InvoiceItem;
import com.nex.cms.model.User;
import com.nex.cms.model.tableAlign;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

public class Invoice extends javax.swing.JPanel {

    HashMap<String, InvoiceItem> invoiceItemMap = new HashMap<>();
    HashMap<String, String> paymentMethodMap = new HashMap<>();

    public JLabel getjLabel11() { //Customer Name
        return jLabel11;
    }

    public JLabel getjLabel36() { //Customer Mobile
        return jLabel36;
    }

    public JLabel getjLabel32() { //Customer id
        return jLabel32;
    }

    public JLabel jLabel13() { //Customer email
        return jLabel13;
    }

    //stock id
    public JLabel getjLabel31() {
        return jLabel31;
    }

    // Brand Name
    public JLabel getjLabel16() {
        return jLabel16;
    }

    // Product Name
    public JLabel getjLabel18() {
        return jLabel18;
    }

    //Selling Price 
    public JLabel getjLabel33() {
        return jLabel33;
    }

    // MFD
    public JLabel getjLabel22() {
        return jLabel22;
    }

    // EXP
    public JLabel getjLabel20() {
        return jLabel20;
    }

    //QTY
    public JFormattedTextField getjFormattedTextField1() {
        return jFormattedTextField1;
    }

    // Available Qty
    public JLabel getjLabel24() {
        return jLabel24;
    }

    // Available Points
    public JFormattedTextField getjFormattedTextField2() {
        return jFormattedTextField2;
    }

    public Invoice() {
        initComponents();

        jTable1.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(204, 255, 255));
        jTable1.getTableHeader().setForeground(new Color(0, 0, 0));
        jTable1.setRowHeight(30);
        tableAlign.tableCellAlignment(jTable1, 0);

        jLabel5.setText(User.getFirst_name() + " " + User.getLast_name());
        jLabel6.setText(User.getEmail());
        jLabel3.setText(User.getEmployeedep());
        jLabel8.setText(User.getEmployeeId());

        generateInvoiceID();
        loadPaymentMethods();

    }

    private void generateInvoiceID() {
        long id = System.currentTimeMillis();
        textField1.setText(String.valueOf(id));
        textField1.setEditable(false);
    }

    private void loadPaymentMethods() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `payment_methord`");

            Vector<String> vector = new Vector<>();

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                paymentMethodMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadInvoiceItems() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        tableAlign.tableCellAlignment(jTable1, 0);

        total = 0;

        for (InvoiceItem invoiceItem : invoiceItemMap.values()) {
            Vector<String> vector = new Vector<>();
            vector.add(invoiceItem.getStrockId());
            vector.add(invoiceItem.getBrand());
            vector.add(invoiceItem.getName());
            vector.add(invoiceItem.getQty());
            vector.add(invoiceItem.getSellingPrice());
            vector.add(invoiceItem.getMfd());
            vector.add(invoiceItem.getExp());

            double itemTotal = Double.parseDouble(invoiceItem.getQty()) * Double.parseDouble(invoiceItem.getSellingPrice());
            total += itemTotal;
            vector.add(String.valueOf(itemTotal));

            model.addRow(vector);
        }

        totalField.setText(String.valueOf(total));

        calculate();

    }

    private double total = 0;
    private double discount = 0;
    private double payment = 0;
    private boolean withdawPoints = false;
    private double balance = 0;
    private String paymentMethod = "Select";
    private double newPoints = 0;

    private void calculate() {

        //settings
        if (discountField.getText().isEmpty()) {
            discount = 0;
        } else {
            discount = Double.parseDouble(discountField.getText());
        }

        if (paymentField.getText().isEmpty()) {
            payment = 0;
        } else {
            payment = Double.parseDouble(paymentField.getText());
        }

        total = Double.parseDouble(totalField.getText());

        if (jCheckBox1.isSelected()) {
            withdawPoints = true;
        } else {
            withdawPoints = false;
        }

        paymentMethod = String.valueOf(jComboBox1.getSelectedItem());
        //settings

        total -= discount;

        if (total < 0) {
            //error
        } else {
            //discount ok
            if (withdawPoints) {

                if (Double.parseDouble(jFormattedTextField2.getText()) == total) {
                    newPoints = 0;
                    total = 0;
                    //no payment required

                } else if (Double.parseDouble(jFormattedTextField2.getText()) < total) {
                    newPoints = 0;
                    total -= Double.parseDouble(jFormattedTextField2.getText());
                    //payment required

                } else {
                    newPoints = Double.parseDouble(jFormattedTextField2.getText()) - total;
                    total = 0;
                    //no payment required
                }
            }
        }

        if (paymentMethod.equals("Cash")) {
            //cash
            paymentField.setEditable(true);
            balance = payment - total;

            if (balance < 0) {
                printInvoiceButton.setEnabled(false);
            } else {
                printInvoiceButton.setEnabled(true);
            }

        } else {
            //card
            payment = total;
            balance = 0;
            paymentField.setText(String.valueOf(payment));
            paymentField.setEditable(false);
            printInvoiceButton.setEnabled(true);
        }

        balanceFiled.setText(String.valueOf(balance));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        textField1 = new com.nex.cms.components.TextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        button1 = new com.nex.cms.components.Button();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        button2 = new com.nex.cms.components.Button();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        button3 = new com.nex.cms.components.Button();
        button4 = new com.nex.cms.components.Button();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        totalField = new javax.swing.JFormattedTextField();
        discountField = new javax.swing.JFormattedTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        paymentField = new javax.swing.JFormattedTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        balanceFiled = new javax.swing.JFormattedTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        printInvoiceButton = new com.nex.cms.components.Button();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel36 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setText("Name");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-user-30.png"))); // NOI18N

        jLabel6.setText("Email");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("Invoice");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel2.setText("Devicion :");

        jLabel3.setText("Devicion Name");

        jSeparator1.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator1.setMaximumSize(new java.awt.Dimension(32767, 5));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-employee-48.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        jLabel8.setText("Biller ID");

        jSeparator2.setBackground(new java.awt.Color(51, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(0, 204, 255));

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel9.setText("Invoice Number");

        textField1.setForeground(new java.awt.Color(0, 0, 0));
        textField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField1.setShadowColor(new java.awt.Color(51, 204, 255));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-customer-48.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel11.setText("Customer Name");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-phone-16.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel13.setText("Email");

        button1.setBackground(new java.awt.Color(204, 255, 255));
        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setText("Select Customer");
        button1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel14.setText("Stock");

        button2.setBackground(new java.awt.Color(204, 255, 255));
        button2.setForeground(new java.awt.Color(0, 0, 0));
        button2.setText("Select Stock");
        button2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel15.setText("Brand");

        jLabel16.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel16.setText("Brand Name");

        jLabel17.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel17.setText("Product");

        jLabel18.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel18.setText("Product Name");

        jLabel19.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel19.setText("Expire Date");

        jLabel20.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("XXXX / XX / XX");

        jLabel21.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel21.setText("Manufactured");

        jLabel22.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("XXXX / XX / XX");

        jFormattedTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jFormattedTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextField1KeyReleased(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel23.setText("Quantity");

        jLabel24.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(4, 210, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("0");

        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-30.png"))); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.setBackground(new java.awt.Color(153, 255, 153));
        button4.setForeground(new java.awt.Color(0, 0, 0));
        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-add-16.png"))); // NOI18N
        button4.setText("   Add Invoice");
        button4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        jSeparator4.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator4.setMaximumSize(new java.awt.Dimension(32767, 5));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock ID", "Brand", "Name", "Quantiy", "Selling Price", "MFD", "EXP", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        totalField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        totalField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        totalField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalField.setText("0");
        totalField.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N

        discountField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        discountField.setForeground(new java.awt.Color(4, 210, 0));
        discountField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        discountField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        discountField.setText("0");
        discountField.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        discountField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                discountFieldKeyReleased(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        paymentField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        paymentField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        paymentField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        paymentField.setText("0");
        paymentField.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        paymentField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentFieldActionPerformed(evt);
            }
        });
        paymentField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paymentFieldKeyReleased(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jCheckBox1.setText("Withdaw Points");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        balanceFiled.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        balanceFiled.setForeground(new java.awt.Color(255, 0, 0));
        balanceFiled.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        balanceFiled.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        balanceFiled.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel26.setText("Total");

        jLabel27.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel27.setText("Discount");

        jLabel28.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel28.setText("Payment Method");

        jLabel29.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel29.setText("Payment");

        jLabel30.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel30.setText("Balance");

        printInvoiceButton.setBackground(new java.awt.Color(255, 255, 204));
        printInvoiceButton.setForeground(new java.awt.Color(0, 0, 0));
        printInvoiceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        printInvoiceButton.setText("   Print");
        printInvoiceButton.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        printInvoiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printInvoiceButtonActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Stock ID");

        jLabel33.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 51, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("0.00");

        jLabel34.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel34.setText("Selling Price");

        jLabel35.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Points");

        jLabel25.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Available");

        jFormattedTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel36.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel36.setText("Mobile");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel1))
                        .addGap(440, 440, 440)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addGap(18, 18, 18)
                                        .addComponent(paymentField))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addGap(41, 41, 41)
                                        .addComponent(totalField)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel27)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(discountField, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel28)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addComponent(jLabel30)
                                        .addGap(18, 18, 18)
                                        .addComponent(balanceFiled)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(printInvoiceButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jScrollPane1)))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(6, 6, 6))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(12, 12, 12))))
                                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jSeparator2))
                                .addGap(25, 25, 25)))
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField2))
                                .addGap(55, 55, 55)
                                .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel23))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jFormattedTextField1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(6, 6, 6))
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(button4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(14, 14, 14))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(21, 21, 21))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)))
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(discountField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)
                            .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(paymentField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(balanceFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox1)))
                    .addComponent(printInvoiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void paymentFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentFieldActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed

        customerReg cr = new customerReg();
        cr.setVisible(true);
        cr.setInvoice(this);

    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed

        fstock fstock = new fstock();
        fstock.setVisible(true);
        fstock.setInvoice(this);

    }//GEN-LAST:event_button2ActionPerformed

    private void jFormattedTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField1KeyReleased

        try {
            String availableQtyStr = jLabel24.getText();
            String qtyStr = jFormattedTextField1.getText();

            if (availableQtyStr != null && !availableQtyStr.trim().isEmpty()
                    && qtyStr != null && !qtyStr.trim().isEmpty()) {

                double AvailableQty = Double.parseDouble(availableQtyStr);
                double qty = Double.parseDouble(qtyStr);

                if (qty > AvailableQty) {
                    JOptionPane.showMessageDialog(this, "The entered quantity is greater than the available quantity", "Error", JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField1.setText("");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Please make sure both values are entered", "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Invalid number format. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jFormattedTextField1KeyReleased

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed

        if (jLabel11.getText().equals("Customer Name")) {
            JOptionPane.showMessageDialog(this, "First select Customer", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (jLabel31.getText().equals("Stock ID")) {
            JOptionPane.showMessageDialog(this, "Please select Item", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String stockId = jLabel31.getText();
            String brand = jLabel16.getText();
            String pname = jLabel18.getText();
            String qty = jFormattedTextField1.getText();
            String exp = jLabel20.getText();
            String mfd = jLabel22.getText();
            String sellingPrice = jLabel33.getText();

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setStrockId(stockId);
            invoiceItem.setBrand(brand);
            invoiceItem.setExp(exp);
            invoiceItem.setMfd(mfd);
            invoiceItem.setName(pname);
            invoiceItem.setQty(qty);
            invoiceItem.setSellingPrice(sellingPrice);

            if (invoiceItemMap.get(stockId) == null) {
                invoiceItemMap.put(stockId, invoiceItem);

            } else {

                InvoiceItem found = invoiceItemMap.get(stockId);

                int updateqtyOption = JOptionPane.showConfirmDialog(this, "Do you want to update the quantity of : " + pname, "Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (updateqtyOption == JOptionPane.YES_OPTION) {

                    found.setQty(String.valueOf(Double.parseDouble(found.getQty()) + Double.parseDouble(qty)));

                }
            }
            loadInvoiceItems();
        }


    }//GEN-LAST:event_button4ActionPerformed

    private void discountFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discountFieldKeyReleased
        calculate();
    }//GEN-LAST:event_discountFieldKeyReleased

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        calculate();
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void paymentFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentFieldKeyReleased
        calculate();
    }//GEN-LAST:event_paymentFieldKeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        calculate();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void printInvoiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printInvoiceButtonActionPerformed

        try {
            String invoiceID = textField1.getText();
            String customerID = jLabel32.getText();
            String employeeID = jLabel8.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String paidAmount = paymentField.getText();
            String paymentMethodID = paymentMethodMap.get(String.valueOf(jComboBox1.getSelectedItem()));
            String discount = discountField.getText();

            // Insert into `invoice` table
            MySQL.executeIUD("INSERT INTO `invoice` VALUES('" + invoiceID + "','" + discount + "','" + dateTime + "',"
                    + "'" + paidAmount + "','" + customerID + "','" + employeeID + "','" + paymentMethodID + "')");

            for (InvoiceItem invoiceItem : invoiceItemMap.values()) {
                // Insert into `invoice_item` table
                MySQL.executeIUD("INSERT INTO `invoice_item`(`qty`, `invoice_id`, `stock_id`) VALUES('"
                        + invoiceItem.getQty() + "','" + invoiceID + "','" + invoiceItem.getStrockId() + "')");

                // Update `stock` table
                MySQL.executeIUD("UPDATE `stock` SET `qty` = `qty` - " + invoiceItem.getQty()
                        + " WHERE `id` = '" + invoiceItem.getStrockId() + "'");
            }

            String totalText = totalField.getText();

            if (totalText != null && !totalText.trim().isEmpty()) {
                try {
                    double points = Double.parseDouble(totalText) / 100;

                    if (withdawPoints) {
                        newPoints += points;
                        MySQL.executeIUD("UPDATE `customer` SET `points` = '" + newPoints + "' WHERE `id` = '" + customerID + "'");
                    } else {
                        MySQL.executeIUD("UPDATE `customer` SET `points` = `points` + " + points + " WHERE `id` = '" + customerID + "'");
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Invalid total value entered.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Total field is empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            }

            // Prepare parameters for the report
            // String path = "src//reports//invoice.jasper";
            InputStream stream = this.getClass().getResourceAsStream("/reports/inv_invoice.jasper");
            HashMap<String, Object> parameter = new HashMap<>();
            parameter.put("Parameter1", totalField.getText());
            parameter.put("Parameter2", discountField.getText());
            parameter.put("Parameter3", String.valueOf(jComboBox1.getSelectedItem())); //payment method
            parameter.put("Parameter4", paymentField.getText());
            parameter.put("Parameter5", balanceFiled.getText());
            parameter.put("Parameter9", textField1.getText()); //invoice no
            parameter.put("Parameter8", jLabel32.getText()); // cusid
            parameter.put("Parameter7", jLabel8.getText()); //empid
            parameter.put("Parameter6", dateTime);

            // Pass the TableModel to JRTableModelDataSource
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, parameter, dataSource);
//            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "SuccessFull!");
            JasperViewer.viewReport(jasperPrint, false);

            String email = jLabel13.getText();
            String subject = "INVOICE";

            // Fetching dynamic data from UI fields
            String paymentMethod = String.valueOf(jComboBox1.getSelectedItem());
            String Cdiscount = discountField.getText();
            String payment = paymentField.getText();
            String total = totalField.getText();
            String balanceDue = balanceFiled.getText();

            // Check internet connectivity before proceeding
            if (!isInternetAvailable()) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "No internet connection! Please check your network.");
                return;
            }

            // Sender's email credentials
            final String senderEmail = "deallssupper@gmail.com";  // Change to your email
            final String senderPassword = "ijkcnivvligvmjbv";  // Use an App Password if using Gmail

            // SMTP Server Configuration
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // Create a session with authentication
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            try {
                // Create the email message
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(senderEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject(subject);

                // **1. HTML Email Body with Invoice Details**
                String htmlBody = "<html><head><style>"
                        + "body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }"
                        + ".container { max-width: 600px; background: #ffffff; margin: 20px auto; padding: 20px; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); }"
                        + "h2 { color: #333; text-align: center; }"
                        + ".details { width: 100%; border-collapse: collapse; margin: 20px 0; }"
                        + ".details th, .details td { border: 1px solid #ddd; padding: 10px; text-align: left; }"
                        + ".details th { background-color: #4CAF50; color: white; }"
                        + ".total { background: #dff0d8; font-weight: bold; font-size: 18px; }"
                        + ".footer { text-align: center; color: #4CAF50; margin-top: 10px; font-weight: bold; }"
                        + "</style></head><body>"
                        + "<div class='container'>"
                        + "<h2>INVOICE</h2>"
                        + "<table class='details'>"
                        + "<tr><th>Description</th><th>Details</th></tr>"
                        + "<tr><td>Payment Method</td><td>" + paymentMethod + "</td></tr>"
                        + "<tr><td>Discount</td><td>" + Cdiscount + "</td></tr>"
                        + "<tr><td>Payment</td><td>" + payment + "</td></tr>"
                        + "<tr class='total'><td>Total</td><td>" + total + "</td></tr>"
                        + "<tr><td>Balance Due</td><td>" + balanceDue + "</td></tr>"
                        + "</table>"
                        + "<p class='footer'>THANK YOU FOR YOUR BUSINESS!</p>"
                        + "<p style='text-align: center; color: #666;'>Software By: NexOra |</p>"
                        + "</div>"
                        + "</body></html>";

                Multipart multipart = new MimeMultipart();

                MimeBodyPart textPart = new MimeBodyPart();
                textPart.setContent(htmlBody, "text/html; charset=utf-8");
                multipart.addBodyPart(textPart);

                message.setContent(multipart);

                Transport.send(message);
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Invoice Email Sent Successfully!");

            } catch (MessagingException e) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Email Sending Failed!");

                e.printStackTrace();
            }

            reset();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_printInvoiceButtonActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        reset();
    }//GEN-LAST:event_button3ActionPerformed

    private void reset() {
        jLabel11.setText("Customer Name");
        jLabel13.setText("Mobile");
        jLabel31.setText("Stock ID");
        jLabel16.setText("Brand Name");
        jLabel18.setText("Product Name");
        jFormattedTextField1.setText("");
        jLabel24.setText("0");
        jFormattedTextField2.setText("");
        jLabel20.setText("XXXX / XX / XX");
        jLabel22.setText("XXXX / XX / XX");
        jLabel11.setText("Customer Name");
        jLabel33.setText("0.00");
        jLabel13.setText("Email");
        jLabel36.setText("Mobile");
        totalField.setText("0");
        paymentField.setText("0");
        discountField.setText("0");
        balanceFiled.setText("");
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        generateInvoiceID();
        loadPaymentMethods();

    }

    private boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com"); // Ping Google
            return address.isReachable(3000); // Timeout 3 seconds
        } catch (UnknownHostException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField balanceFiled;
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button2;
    private com.nex.cms.components.Button button3;
    private com.nex.cms.components.Button button4;
    private javax.swing.JFormattedTextField discountField;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JFormattedTextField paymentField;
    private com.nex.cms.components.Button printInvoiceButton;
    private com.nex.cms.components.TextField textField1;
    private javax.swing.JFormattedTextField totalField;
    // End of variables declaration//GEN-END:variables
}
