package com.nex.cms.frame.inventory;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import com.nex.cms.connection.MySQL;
import com.nex.cms.model.tableAlign;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class CompanyRegistration extends javax.swing.JDialog {

    SupplierRegistration supplierRegistration;

    private static HashMap<String, String> cityMap = new HashMap<>();

    public CompanyRegistration(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();

        supplierRegistration = (SupplierRegistration) parent;

        jTable1.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(204, 255, 255));
        jTable1.getTableHeader().setForeground(new Color(0, 0, 0));
        jTable1.setRowHeight(30);
        tableAlign.tableCellAlignment(jTable1, 0);

        loadcompany();
        loadcity();
    }

    public void loadcompany() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `company`"
                    + "INNER JOIN `city` ON `company`.city_id = `city`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            tableAlign.tableCellAlignment(jTable1, 0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("name"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("hotline"));
                vector.add(resultSet.getString("desc"));
                vector.add(resultSet.getString("line_1"));
                vector.add(resultSet.getString("line_2"));
                vector.add(resultSet.getString("city.name"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadcity() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `city`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                cityMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void reset() {

        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        jTextArea1.setText("");
        jComboBox1.setSelectedIndex(0);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        textField1 = new com.nex.cms.components.TextField();
        jLabel4 = new javax.swing.JLabel();
        textField2 = new com.nex.cms.components.TextField();
        jLabel5 = new javax.swing.JLabel();
        textField3 = new com.nex.cms.components.TextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textField4 = new com.nex.cms.components.TextField();
        textField5 = new com.nex.cms.components.TextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        button1 = new com.nex.cms.components.Button();
        button2 = new com.nex.cms.components.Button();
        button3 = new com.nex.cms.components.Button();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        button4 = new com.nex.cms.components.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NexOra | Company Registration ");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 255));
        jLabel2.setText("Company Registration");

        jSeparator2.setForeground(new java.awt.Color(0, 204, 255));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel3.setText("Name");

        textField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField1.setShadowColor(new java.awt.Color(0, 204, 255));

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel4.setText("Hotline");

        textField2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField2.setShadowColor(new java.awt.Color(0, 204, 255));

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel5.setText("Email");

        textField3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField3.setShadowColor(new java.awt.Color(0, 204, 255));

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel6.setText("Description");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel1.setText("Address");

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel7.setText("Line 1");

        textField4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField4.setShadowColor(new java.awt.Color(0, 204, 255));

        textField5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField5.setShadowColor(new java.awt.Color(0, 204, 255));

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel8.setText("Line 2");

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel9.setText("City");

        jComboBox1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        button1.setBackground(new java.awt.Color(153, 255, 153));
        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-add-16.png"))); // NOI18N
        button1.setText("   Add Company");
        button1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button1.setRippleColor(new java.awt.Color(153, 204, 255));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(204, 255, 255));
        button2.setForeground(new java.awt.Color(0, 0, 0));
        button2.setText("Update");
        button2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button2.setRippleColor(new java.awt.Color(153, 204, 255));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-30.png"))); // NOI18N
        button3.setRippleColor(new java.awt.Color(153, 204, 255));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(0, 204, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email", "Hotline", "Description", "Address Line 1", "Address Line 2", "City"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(6).setResizable(false);
        }

        button4.setText("More Details");
        button4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button4.setRippleColor(new java.awt.Color(153, 204, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textField4, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                        .addComponent(jLabel6)
                                        .addGap(21, 21, 21))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed

        try {

            String name = textField1.getText();
            String hotline = textField2.getText();
            String email = textField3.getText();
            String desc = jTextArea1.getText();

            String adrsline1 = textField4.getText();
            String adrsline2 = textField5.getText();
            String city = String.valueOf(jComboBox1.getSelectedItem());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter company name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (name.length() > 45) {
                JOptionPane.showMessageDialog(this, "Company name has grown too long. Enter the brief!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (hotline.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter company hotline number", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!hotline.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$")) {
                JOptionPane.showMessageDialog(this, "Please enter valid hotline number", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (hotline.length() > 12) {
                JOptionPane.showMessageDialog(this, "Please enter valid hotline number", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter comopany email address", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+"
                    + "(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
                JOptionPane.showMessageDialog(this, "please enter valid email address", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (email.length() > 100) {
                JOptionPane.showMessageDialog(this, "Email address to long, please try with another one", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (desc.length() > 2000) {
                JOptionPane.showMessageDialog(this, "Description has grown too long. Enter the brief!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (adrsline1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter address line 1", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (adrsline1.length() > 45) {
                JOptionPane.showMessageDialog(this, "address Line 1 has grown too long. Enter the brief!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (adrsline2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter address line 2", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (adrsline2.length() > 45) {
                JOptionPane.showMessageDialog(this, "address Line 2 has grown too long. Enter the brief!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (city.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select a city", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `company` WHERE `hotline` = '" + hotline + "' OR `email` = '" + email + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This company already registered", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MySQL.executeIUD("INSERT INTO `company` (`name`,`hotline`,`email`,`desc`,`line_1`,`line_2`,`city_id`)"
                            + " VALUES ('" + name + "','" + hotline + "','" + email + "','" + desc + "','" + adrsline1 + "','" + adrsline2 + "','" + cityMap.get(city) + "')");

                }

                loadcompany();
                reset();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        int row = jTable1.getSelectedRow();

        if (row == 1) {
            JOptionPane.showMessageDialog(this, "Please select a row and try again", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                String name = textField1.getText();
                String hotline = textField2.getText();
                String email = textField3.getText();
                String desc = jTextArea1.getText();

                String adrsline1 = textField4.getText();
                String adrsline2 = textField5.getText();
                String city = String.valueOf(jComboBox1.getSelectedItem());

                String selectedId = String.valueOf(jTable1.getValueAt(row, 0));

                String selectedName = String.valueOf(jTable1.getValueAt(row, 1));
                String selectedEmail = String.valueOf(jTable1.getValueAt(row, 2));
                String selectedhotline = String.valueOf(jTable1.getValueAt(row, 3));
                String selecteddesc = String.valueOf(jTable1.getValueAt(row, 4));
                String selectedadrsline1 = String.valueOf(jTable1.getValueAt(row, 5));
                String selectedadrsline2 = String.valueOf(jTable1.getValueAt(row, 6));
                String selectedcity = String.valueOf(jTable1.getValueAt(row, 7));

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter company name", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (name.length() > 45) {
                    JOptionPane.showMessageDialog(this, "Company name has grown too long. Enter the brief!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (hotline.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter company hotline number", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (!hotline.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$")) {
                    JOptionPane.showMessageDialog(this, "Please enter valid hotline number", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (hotline.length() > 12) {
                    JOptionPane.showMessageDialog(this, "Please enter valid hotline number", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter comopany email address", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+"
                        + "(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
                    JOptionPane.showMessageDialog(this, "please enter valid email address", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (email.length() > 100) {
                    JOptionPane.showMessageDialog(this, "Email address to long, please try with another one", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (desc.length() > 2000) {
                    JOptionPane.showMessageDialog(this, "Description has grown too long. Enter the brief!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (adrsline1.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter address line 1", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (adrsline1.length() > 45) {
                    JOptionPane.showMessageDialog(this, "address Line 1 has grown too long. Enter the brief!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (adrsline2.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter address line 2", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (adrsline2.length() > 45) {
                    JOptionPane.showMessageDialog(this, "address Line 2 has grown too long. Enter the brief!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (city.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Please select a city", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (selectedName.equals(name) && selectedEmail.equals(email) && selectedhotline.equals(hotline) && selecteddesc.equals(desc) && selectedadrsline1.equals(adrsline1) && selectedadrsline2.equals(adrsline2) && selectedcity.equals(city)) {
                    JOptionPane.showMessageDialog(this, "Please change something to update", "Warning", JOptionPane.WARNING_MESSAGE);

                } else {

                    ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `company` WHERE (`name`= '" + name + "' OR `hotline` = '" + hotline + "' OR `email` = '" + email + "') AND `id` != '" + selectedId + "'");

                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(this, "Company name, email or hotine already used", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {

                        MySQL.executeIUD("UPDATE `company` SET `name` = '" + name + "' , `hotline` = '" + hotline + "' , `email` = '" + email + "' , `desc` = '" + desc + "' , `line_1` = '" + adrsline1 + "' , "
                                + "`line_2` = '" + adrsline2 + "' , `city_id` = '" + cityMap.get(city) + "' WHERE `id` = '" + selectedId + "'");
                    }

                    loadcompany();
                    reset();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_button2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int row = jTable1.getSelectedRow();

        textField1.setText(String.valueOf(jTable1.getValueAt(row, 1)));
        textField2.setText(String.valueOf(jTable1.getValueAt(row, 3)));
        textField3.setText(String.valueOf(jTable1.getValueAt(row, 2)));
        jTextArea1.setText(String.valueOf(jTable1.getValueAt(row, 4)));
        textField4.setText(String.valueOf(jTable1.getValueAt(row, 5)));
        textField5.setText(String.valueOf(jTable1.getValueAt(row, 6)));
        jComboBox1.setSelectedItem(String.valueOf(jTable1.getValueAt(row, 7)));

        button1.setEnabled(false);

        if (evt.getClickCount() == 2) {

            String name = String.valueOf(jTable1.getValueAt(row, 1));

            supplierRegistration.getCompanyName(name);

            supplierRegistration.addSupplierGrabFocus();

            supplierRegistration.setCompanyId(String.valueOf(jTable1.getValueAt(row, 0))); //for get a company id

            this.dispose();

        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        reset();
    }//GEN-LAST:event_button3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button2;
    private com.nex.cms.components.Button button3;
    private com.nex.cms.components.Button button4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private com.nex.cms.components.TextField textField1;
    private com.nex.cms.components.TextField textField2;
    private com.nex.cms.components.TextField textField3;
    private com.nex.cms.components.TextField textField4;
    private com.nex.cms.components.TextField textField5;
    // End of variables declaration//GEN-END:variables
}
