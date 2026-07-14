/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.nex.cms.panel.marketing;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import com.nex.cms.connection.MySQL;
import static com.nex.cms.frame.login.logger;
import java.awt.Color;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class socialMediaManagement extends javax.swing.JPanel {

    private static HashMap<String, String> socailnetworkTypeMap = new HashMap<>();

    public socialMediaManagement() {
        initComponents();
        loadSocailnetwork();
        loadSocailmedia();
        total();

        jTable1.getTableHeader().setBackground(new Color(0, 204, 255));
        jTable1.getTableHeader().setForeground(Color.black);

    }

    private void total() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT SUM(amount) AS total_amount FROM marketing_account;");
            while (resultSet.next()) {
                double sum = resultSet.getDouble("total_amount");
                jLabel32.setText("Total: " + sum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculate() {

        double spendAmount = Double.parseDouble(jFormattedTextField1.getText());
        double budgetHRM = Double.parseDouble(jLabel32.getText());

        if (spendAmount > budgetHRM) {
            JOptionPane.showMessageDialog(this, "Your total Budgets is Law! Please Check", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            double calculateBalance = budgetHRM - spendAmount;
            try {
                MySQL.executeIUD("UPDATE `total` SET `total` = '" + calculateBalance + "'");
                total();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void loadSocailnetwork() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `network`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("nName"));
                socailnetworkTypeMap.put(resultSet.getString("nName"), resultSet.getString("nId"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSocailmedia() {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `socail`"
                    + "INNER JOIN `network` ON `socail`.`network_nId` = `network`.`nId`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("cID"));
                vector.add(resultSet.getString("cName"));
                vector.add(resultSet.getString("network.nName"));
                vector.add(resultSet.getString("des"));
                vector.add(resultSet.getString("amount"));
                vector.add(resultSet.getString("sDate"));
                vector.add(resultSet.getString("eDate"));

                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadItem() {
        try {
            // Base query
            String query = "SELECT * FROM `socail` "
                    + "INNER JOIN `network` ON `socail`.`network_nId` = `network`.`nId` ";

            // Initialize filters
            List<String> conditions = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            // Add date filters
            if (jDateChooser4.getDate() != null) {
                String sDate = format.format(jDateChooser4.getDate());
                conditions.add("`socail`.`sDate` > '" + sDate + "'");
            }
            if (jDateChooser5.getDate() != null) {
                String eDate = format.format(jDateChooser5.getDate());
                conditions.add("`socail`.`eDate` < '" + eDate + "'");
            }

            // Append conditions
            if (!conditions.isEmpty()) {
                query += "WHERE " + String.join(" AND ", conditions) + " ";
            }

            String sort = String.valueOf(jComboBox1.getSelectedItem());
            if (sort.equals("Campaion Name ASC")) {
                query += "ORDER BY `socail`.`cName` ASC";
            } else if (sort.equals("Campaion Name DESC")) {
                query += "ORDER BY `socail`.`cName` DESC";
            } else if (sort.equals("Socail Network  ASC")) {
                query += "ORDER BY `network`.`nName` ASC";
            } else if (sort.equals("Socail Network DESC")) {
                query += "ORDER BY `network`.`nName` DESC";
            }

            // Execute query
            ResultSet resultSet = MySQL.executeSearch(query);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            // Populate table
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("cId"));
                vector.add(resultSet.getString("cName"));
                vector.add(resultSet.getString("network.nName"));
                vector.add(resultSet.getString("des"));
                vector.add(resultSet.getString("amount"));
                vector.add(resultSet.getString("sDate"));
                vector.add(resultSet.getString("eDate"));

                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jMenu1 = new javax.swing.JMenu();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        button2 = new com.nex.cms.components.Button();
        button4 = new com.nex.cms.components.Button();
        textField1 = new com.nex.cms.components.TextField();
        textField3 = new com.nex.cms.components.TextField();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        button7 = new com.nex.cms.components.Button();
        button3 = new com.nex.cms.components.Button();
        jSeparator2 = new javax.swing.JSeparator();
        textField2 = new com.nex.cms.components.TextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        button9 = new com.nex.cms.components.Button();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        button5 = new com.nex.cms.components.Button();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        button1 = new com.nex.cms.components.Button();
        jLabel1 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jMenu1.setText("jMenu1");

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1250, 630));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Campaion Name");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Socail network");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Date");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("to");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Description");

        jDateChooser2.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jDateChooser2.setForeground(new java.awt.Color(255, 255, 255));
        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jDateChooser2.setFont(new java.awt.Font("Poppins", 0, 11)); // NOI18N

        button2.setText("UPDATE");
        button2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button2.setRippleColor(new java.awt.Color(51, 204, 0));
        button2.setShadowColor(new java.awt.Color(51, 204, 0));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button4.setText("ADD");
        button4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button4.setRippleColor(new java.awt.Color(0, 204, 204));
        button4.setShadowColor(new java.awt.Color(0, 0, 255));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        textField1.setShadowColor(new java.awt.Color(0, 204, 204));
        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });

        textField3.setShadowColor(new java.awt.Color(0, 204, 204));

        jDateChooser3.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jDateChooser3.setForeground(new java.awt.Color(255, 255, 255));
        jDateChooser3.setDateFormatString("yyyy-MM-dd");
        jDateChooser3.setFont(new java.awt.Font("Poppins", 0, 11)); // NOI18N

        button7.setText("DELETE");
        button7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button7.setRippleColor(new java.awt.Color(255, 51, 51));
        button7.setShadowColor(new java.awt.Color(255, 51, 51));
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });

        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-48.png"))); // NOI18N
        button3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button3.setRippleColor(new java.awt.Color(102, 102, 0));
        button3.setShadowColor(new java.awt.Color(0, 0, 0));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        jSeparator2.setBackground(new java.awt.Color(204, 255, 153));
        jSeparator2.setForeground(new java.awt.Color(0, 204, 255));

        textField2.setText("ADD");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Youtube ads", "facebook ads", "whatsapp msg", "podcaste" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));

        button9.setText("ADD");
        button9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button9.setRippleColor(new java.awt.Color(102, 0, 102));
        button9.setShadowColor(new java.awt.Color(255, 153, 0));
        button9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button9ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Spend Amount");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(textField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1215, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel6)))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(102, 51, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("sort");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Campaion Name ASC", "Campaion Name DESC", "Socail Network  ASC", "Socail Network DESC" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jDateChooser4.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jDateChooser4.setForeground(new java.awt.Color(255, 255, 255));
        jDateChooser4.setDateFormatString("yyyy-MM-dd");
        jDateChooser4.setFont(new java.awt.Font("Poppins", 0, 11)); // NOI18N

        jDateChooser5.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jDateChooser5.setForeground(new java.awt.Color(255, 255, 255));
        jDateChooser5.setDateFormatString("yyyy-MM-dd");
        jDateChooser5.setFont(new java.awt.Font("Poppins", 0, 11)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Campaion Name", "Socail Network", "Description", "Spend Amount", " Start Date", "End Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        button5.setText("Search");
        button5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button5.setRippleColor(new java.awt.Color(204, 204, 0));
        button5.setShadowColor(new java.awt.Color(204, 204, 0));
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("To");

        jSeparator1.setBackground(new java.awt.Color(204, 255, 153));
        jSeparator1.setForeground(new java.awt.Color(0, 204, 255));

        button1.setText("Print");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button1.setRippleColor(new java.awt.Color(102, 0, 102));
        button1.setShadowColor(new java.awt.Color(102, 0, 102));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(261, 261, 261)
                                .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel8)
                                .addGap(42, 42, 42)
                                .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1135, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Socail Media Campaion");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 51, 51));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Total Capital");

        jLabel32.setBackground(new java.awt.Color(226, 73, 73));
        jLabel32.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 0, 0));
        jLabel32.setText("0.00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(102, 102, 102)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(313, 313, 313))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed

        String text = jLabel32.getText().replace("Total: ", "").trim();
        double total = Double.valueOf(text);

        String cName = textField1.getText().trim();
        String network = String.valueOf(jComboBox2.getSelectedItem());
        String des = textField3.getText().trim();
        double amountSpend = Double.parseDouble(jFormattedTextField1.getText());
        Date sDate = jDateChooser2.getDate();
        Date eDate = jDateChooser3.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (amountSpend > total) {
            System.out.println(">");
        }

        if (cName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your Campaign Name", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (network.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Social Network", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (des.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your Description", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (sDate == null) {
            JOptionPane.showMessageDialog(this, "Please enter your Start Date", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (eDate == null) {
            JOptionPane.showMessageDialog(this, "Please select an End Date", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ResultSet resultSet = null;
        try {
            resultSet = MySQL.executeSearch("SELECT SUM(total) AS total FROM `social_account`");
        } catch (Exception ex) {
            Logger.getLogger(socialMediaManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (resultSet.next()) {
                double sum = resultSet.getDouble("total");
                double calculate = sum -= amountSpend;
                double processCal = calculate;
                try {
                    MySQL.executeIUD("UPDATE `social_account` SET `total`='" + calculate + "'");
                } catch (Exception ex) {
                    Logger.getLogger(socialMediaManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
                jLabel32.setText("Total: " + processCal);

                try {
                    MySQL.executeIUD("INSERT INTO `socail` (`cName`, `network_nId` ,`des`, `amount`,`sDate`, `eDate`) "
                            + "VALUES( '" + cName + "','" + socailnetworkTypeMap.get(network) + "','" + des + "','" + amountSpend + "','" + sdf.format(sDate) + "',"
                            + "'" + sdf.format(eDate) + "')");
                } catch (Exception ex) {
                    Logger.getLogger(socialMediaManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
                loadSocailnetwork();
                loadSocailmedia();

                reset();
            }
        } catch (SQLException ex) {
            Logger.getLogger(socialMediaManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, " Socail Campaion Add Error", e);
        }
    }//GEN-LAST:event_button4ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        reset();
    }//GEN-LAST:event_button3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();

        String cName = String.valueOf(jTable1.getValueAt(row, 1));
        textField1.setText(cName);

        String des = String.valueOf(jTable1.getValueAt(row, 3));
        textField3.setText(des);

        String amount = String.valueOf(jTable1.getValueAt(row, 4));
        jFormattedTextField1.setText(amount);

        try {
            String sDate = String.valueOf(jTable1.getValueAt(row, 5));
            java.util.Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
            Calendar startCal = Calendar.getInstance();
            startCal.setTime(startDate);
            jDateChooser2.setCalendar(startCal);

            String eDate = String.valueOf(jTable1.getValueAt(row, 6));
            java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(eDate);
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(endDate);
            jDateChooser3.setCalendar(endCal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sNet = String.valueOf(jTable1.getValueAt(row, 2));
        jComboBox2.setSelectedItem(sNet);


    }//GEN-LAST:event_jTable1MouseClicked

    private void button9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button9ActionPerformed
        String network = textField2.getText();
        if (network.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter network name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `network` WHERE `nName` ='" + network + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "network already added", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (jComboBox2.getSelectedIndex() == 0) {
                        MySQL.executeIUD("INSERT INTO `network`(`nName`) VALUES('" + network + "')");
                        JOptionPane.showMessageDialog(this, "New network added", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int showConfirm = JOptionPane.showConfirmDialog(this, "Do you want to update network?", "Update",
                                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        if (showConfirm == JOptionPane.YES_OPTION) {
                            MySQL.executeIUD("UPDATE `network` SET `nName`='" + network + "' WHERE `"
                                    + "nName` = '" + String.valueOf(jComboBox2.getSelectedItem()) + "'");
                            JOptionPane.showMessageDialog(this, "network update", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    loadSocailnetwork();
                    textField2.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }//GEN-LAST:event_button9ActionPerformed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a Socailcampaion to delete", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String cId = String.valueOf(jTable1.getValueAt(row, 0));
                MySQL.executeIUD("DELETE FROM `socail` WHERE `cId` ='" + cId + "' ");

                loadSocailmedia();

                reset();
                JOptionPane.showMessageDialog(this, " Socailcampaion successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_button7ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        try {
            int row = jTable1.getSelectedRow();

            String cId = String.valueOf(jTable1.getValueAt(row, 0));

            String cName = textField1.getText().trim();
            String network = String.valueOf(jComboBox2.getSelectedItem());
            String des = textField3.getText().trim();
            String amount = String.valueOf(jFormattedTextField1.getText());

            Date sDate = jDateChooser2.getDate();
            Date eDate = jDateChooser3.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (cName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your Campaign Name", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (network.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select a Social Network", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (des.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your Description", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (amount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your Amount", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (sDate == null) {
                JOptionPane.showMessageDialog(this, "Please enter your Start Date", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (eDate == null) {
                JOptionPane.showMessageDialog(this, "Please select an End Date", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            MySQL.executeIUD("UPDATE `socail` SET `cName` = '" + cName + "', `network_nId`='" + socailnetworkTypeMap.get(network) + "', `des` = '" + des + "',`amount` = '" + amount + "',"
                    + "`sDate`='" + sdf.format(sDate) + "', `eDate`= '" + sdf.format(eDate) + "' WHERE `cId` = '" + cId + "'");

            loadSocailmedia();
            reset();

        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, " Socail Campaion Update Error", e);

        }

    }//GEN-LAST:event_button2ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        loadItem();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        loadItem();
    }//GEN-LAST:event_button5ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        try {
            String path = "src/reports/mar_Social.jasper";

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
            logger.log(Level.WARNING, "Print mar_Social fail JRException", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print mar_Social unexpected exceptions", e);
        }
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button2;
    private com.nex.cms.components.Button button3;
    private com.nex.cms.components.Button button4;
    private com.nex.cms.components.Button button5;
    private com.nex.cms.components.Button button7;
    private com.nex.cms.components.Button button9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private com.nex.cms.components.TextField textField1;
    private com.nex.cms.components.TextField textField2;
    private com.nex.cms.components.TextField textField3;
    // End of variables declaration//GEN-END:variables

    private void reset() {

        textField1.setText("");
        textField3.setText("");
        jFormattedTextField1.setText("");
        jDateChooser2.setCalendar(null);
        jDateChooser3.setCalendar(null);
        jComboBox2.setSelectedIndex(0);

    }

}
