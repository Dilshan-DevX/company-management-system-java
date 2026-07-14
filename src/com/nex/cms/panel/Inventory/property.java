/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.nex.cms.panel.Inventory;

import com.nex.cms.connection.MySQL;
import static com.nex.cms.frame.login.logger;
import com.nex.cms.model.InvoiceItem;
import com.nex.cms.model.User;
import com.nex.cms.model.tableAlign;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author User
 */
public class property extends javax.swing.JPanel {

    HashMap<String, String> PropertyBrandMap = new HashMap<>();

    /**
     * Creates new form property
     */
    public property() {
        initComponents();
        loadPropertyBrand();
        loadPropertyProducts();
        loadProperty();
        total();

        jTable1.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(204, 255, 255));
        jTable1.getTableHeader().setForeground(new Color(0, 0, 0));
        jTable1.setRowHeight(30);
        tableAlign.tableCellAlignment(jTable1, 0);

        jTable2.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 12));
        jTable2.getTableHeader().setOpaque(false);
        jTable2.getTableHeader().setBackground(new Color(204, 255, 255));
        jTable2.getTableHeader().setForeground(new Color(0, 0, 0));
        jTable2.setRowHeight(30);
        tableAlign.tableCellAlignment(jTable2, 0);

        jLabel5.setText(User.getFirst_name() + " " + User.getLast_name());
        jLabel6.setText(User.getEmail());
        jLabel3.setText(User.getEmployeedep());
    }

    private void total() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT SUM(amount) AS total_amount FROM inventory_account;");
            while (resultSet.next()) {
                double sum = resultSet.getDouble("total_amount");
                jLabel32.setText("Total: " + sum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPropertyBrand() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `property_brand`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                PropertyBrandMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPropertyProducts() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `property_product` INNER JOIN `property_brand`"
                    + "ON `property_product`.`property_brand_id` = `property_brand`.`id` ORDER BY `property_product`.`id` DESC");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            tableAlign.tableCellAlignment(jTable1, 0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("property_product.id"));
                vector.add(resultSet.getString("property_product.name"));
                vector.add(resultSet.getString("property_brand.id"));
                vector.add(resultSet.getString("property_brand.name"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadProperty() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `property` INNER JOIN `property_product`"
                    + "ON `property`.`Property_product_id` = `property_product`.`id` ORDER BY `property`.`id` DESC");

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            tableAlign.tableCellAlignment(jTable2, 0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("desc"));
                vector.add(resultSet.getString("pd"));
                vector.add(resultSet.getString("edu"));
                vector.add(resultSet.getString("price"));
                vector.add(resultSet.getString("qty"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void resetProduct() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        jTable1.clearSelection();
        jTable2.clearSelection();
        textField1.grabFocus();
        textField4.setText("");
        textField5.setText("");
        jFormattedTextField1.setText("");
        jFormattedTextField2.setText("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        loadPropertyProducts();
        loadProperty();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        textField1 = new com.nex.cms.components.TextField();
        textField2 = new com.nex.cms.components.TextField();
        jLabel8 = new javax.swing.JLabel();
        textField3 = new com.nex.cms.components.TextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        button1 = new com.nex.cms.components.Button();
        button3 = new com.nex.cms.components.Button();
        button4 = new com.nex.cms.components.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        textField4 = new com.nex.cms.components.TextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textField5 = new com.nex.cms.components.TextField();
        jLabel12 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        button5 = new com.nex.cms.components.Button();
        button6 = new com.nex.cms.components.Button();
        button7 = new com.nex.cms.components.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        button9 = new com.nex.cms.components.Button();
        button8 = new com.nex.cms.components.Button();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("Property");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel2.setText("Devicion :");

        jLabel3.setText("Devicion Name");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-user-30.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setText("Name");

        jLabel6.setText("Email");

        jSeparator1.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator1.setMaximumSize(new java.awt.Dimension(32767, 5));

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel7.setText("Product ID");

        textField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        textField2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel8.setText("Product Name");

        textField3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel9.setText("Brand");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-add-16.png"))); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button3.setBackground(new java.awt.Color(153, 255, 153));
        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-add-16.png"))); // NOI18N
        button3.setText("   Add");
        button3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-30.png"))); // NOI18N
        button4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Brand ID", "Brand Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        textField4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel10.setText("Property ID");

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel11.setText("Name & etc");

        textField5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jLabel12.setText("PD");

        jDateChooser1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jLabel13.setText("EDU");

        jDateChooser2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jDateChooser2.setDateFormatString("yyyy-MM-dd");

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel14.setText("Price");

        jLabel15.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel15.setText("QTY");

        jFormattedTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jFormattedTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        button5.setBackground(new java.awt.Color(153, 255, 153));
        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-add-16.png"))); // NOI18N
        button5.setText("   Add Property");
        button5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        button6.setBackground(new java.awt.Color(204, 255, 255));
        button6.setText("Update");
        button6.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        button7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-30.png"))); // NOI18N
        button7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Property ID", "Details", "PD", "EDU", "Price", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel35.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 51, 51));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Total Capital");

        jLabel32.setBackground(new java.awt.Color(226, 73, 73));
        jLabel32.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("0.00");

        button9.setBackground(new java.awt.Color(204, 204, 255));
        button9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button9.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button9.setRippleColor(new java.awt.Color(153, 204, 255));
        button9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button9ActionPerformed(evt);
            }
        });

        button8.setBackground(new java.awt.Color(204, 204, 255));
        button8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button8.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button8.setRippleColor(new java.awt.Color(153, 204, 255));
        button8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(262, 262, 262))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(215, 215, 215)
                                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button6, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button9, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textField4, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(button7, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button8, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addGap(13, 13, 13))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(16, 16, 16)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(button4, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button6, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button9, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel15))
                    .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed

        String productid = textField1.getText();
        String brand = String.valueOf(jComboBox1.getSelectedItem());
        String name = name = textField2.getText();

        if (productid.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Product ID", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (productid.length() > 10) {
            JOptionPane.showMessageDialog(this, "Product ID must have content lower than 10 characters.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter product name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (name.length() > 45) {
            JOptionPane.showMessageDialog(this, "Product name must have content lower than 45 characters.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (brand.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a brand", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `property_product` WHERE `id` = '" + productid + "' OR (`name` = '" + name + "' AND `property_brand_id` = '" + PropertyBrandMap.get(brand) + "')");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Product already added", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    MySQL.executeIUD("INSERT INTO `property_product`(`id`,`name`,`property_brand_id`) VALUES('" + productid + "','" + name + "','" + PropertyBrandMap.get(brand) + "')");

                    JOptionPane.showMessageDialog(this, "New product added", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadPropertyProducts();
                    resetProduct();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_button3ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        String brand = textField3.getText();

        if (brand.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter brand name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `property_brand` WHERE `name`='" + brand + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Brand allredy added", "Warning", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    if (jComboBox1.getSelectedIndex() == 0) {
                        MySQL.executeIUD("INSERT INTO `property_brand`(`name`) VALUES ('" + brand + "')");
                    } else {
                        int showConfirm = JOptionPane.showConfirmDialog(this, "Do you want to update brand?", "Update", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                        if (showConfirm == JOptionPane.YES_OPTION) {
                            MySQL.executeIUD("UPDATE `property_brand` SET `name`='" + brand + "' WHERE `name` = '" + String.valueOf(jComboBox1.getSelectedItem()) + "'");
                            JOptionPane.showMessageDialog(this, "Brand update", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                    loadPropertyBrand();
                    textField3.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int row = jTable1.getSelectedRow();

        textField1.setText(String.valueOf(jTable1.getValueAt(row, 0)));
        textField1.setEditable(false);
        textField2.setText(String.valueOf(jTable1.getValueAt(row, 1)));
        jComboBox1.setSelectedItem(String.valueOf(jTable1.getValueAt(row, 3)));

        button3.setEnabled(false);

    }//GEN-LAST:event_jTable1MouseClicked

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed


    }//GEN-LAST:event_button6ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        resetProduct();
    }//GEN-LAST:event_button4ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed

        try {
            int row = jTable1.getSelectedRow();

            String propertyid = textField4.getText();
            String propertyName = textField5.getText();
            Date purchDate = jDateChooser1.getDate();
            Date estDate = jDateChooser2.getDate();
            String price = jFormattedTextField1.getText();
            String qty = jFormattedTextField2.getText();

            double buyPrice = Double.parseDouble(price);
            double quantity = Double.parseDouble(qty);

            try {

                ResultSet resultSet2 = null;

                resultSet2 = MySQL.executeSearch("SELECT SUM(amount) AS amount FROM `inventory_account`");

                while (resultSet2.next()) {
                    double sum = resultSet2.getDouble("amount");
                    double calculate = sum -= buyPrice;
                    double processCal = calculate;

                    MySQL.executeIUD("UPDATE `hr_account` SET `amount`='" + calculate + "'");

                    jLabel32.setText("Total: " + processCal);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (row > -1) {
                String brandid = String.valueOf(jTable1.getValueAt(row, 0));

                if (propertyid.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter property id", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (propertyid.length() > 40) {
                    JOptionPane.showMessageDialog(this, "Property ID must be less than 40 characters", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (propertyName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter property Name and Details", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (propertyName.length() > 400) {
                    JOptionPane.showMessageDialog(this, "Property Details must be less than 400 characters", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (purchDate == null) {
                    JOptionPane.showMessageDialog(this, "Please select a Purchase date", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (estDate == null) {
                    JOptionPane.showMessageDialog(this, "Please select an Estimated date of use", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (!estDate.after(purchDate)) {
                    JOptionPane.showMessageDialog(this, "Estimated use date must be after the Purchase date", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (buyPrice <= 0) {
                    JOptionPane.showMessageDialog(this, "Price must be greater than zero", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (qty.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter quantity", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (quantity <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantity must be a positive number", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    int updateqtyOption = JOptionPane.showConfirmDialog(this, "This cannot be updated again, so have you entered the data correctly?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                    if (updateqtyOption == JOptionPane.YES_OPTION) {

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedPurchDate = sdf.format(purchDate);
                        String formattedEstDate = sdf.format(estDate);

                        MySQL.executeIUD("INSERT INTO `property` (`id`,`price`,`qty`,`pd`,`edu`,`desc`,`department_department_id`,`property_product_id`)"
                                + " VALUES ('" + propertyid + "','" + buyPrice + "','" + qty + "','" + formattedPurchDate + "','" + formattedEstDate + "','" + propertyName + "','1','" + brandid + "')");

                        JOptionPane.showMessageDialog(this, "Property item successfully added", "Information", JOptionPane.INFORMATION_MESSAGE);
                        resetProduct();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a brand row of: " + propertyName, "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_button5ActionPerformed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        resetProduct();
    }//GEN-LAST:event_button7ActionPerformed

    private void button9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button9ActionPerformed

        try {
            String path = "src/reports/inv_propertyProduct&Brand.jasper";

            LocalDateTime datetime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fdt = datetime.format(formatter);

            HashMap<String, Object> parameter = new HashMap<>();
            parameter.put("Parameter1", fdt);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
            JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameter, dataSource);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print Property Product & Brand fail JRException", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print Property Product & Brand unexpected exceptions", e);
        }

    }//GEN-LAST:event_button9ActionPerformed

    private void button8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button8ActionPerformed

        try {
            String path = "src/reports/inv_cureentProperty.jasper";

            LocalDateTime datetime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fdt = datetime.format(formatter);

            HashMap<String, Object> parameter = new HashMap<>();
            parameter.put("Parameter1", fdt);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable2.getModel());
            JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameter, dataSource);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print Current Property fail JRException", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print Current Property fail unexpected exceptions", e);
        }
    }//GEN-LAST:event_button8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button3;
    private com.nex.cms.components.Button button4;
    private com.nex.cms.components.Button button5;
    private com.nex.cms.components.Button button6;
    private com.nex.cms.components.Button button7;
    private com.nex.cms.components.Button button8;
    private com.nex.cms.components.Button button9;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private com.nex.cms.components.TextField textField1;
    private com.nex.cms.components.TextField textField2;
    private com.nex.cms.components.TextField textField3;
    private com.nex.cms.components.TextField textField4;
    private com.nex.cms.components.TextField textField5;
    // End of variables declaration//GEN-END:variables
}
