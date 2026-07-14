package com.nex.cms.panel.cusutomer;

import com.nex.cms.connection.MySQL;
import com.nex.cms.model.User;
import com.nex.cms.model.Validations;
import com.nex.cms.model.tableAlign;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class customerReg extends javax.swing.JPanel {

    private static HashMap<String, String> genderMap = new HashMap<>();
    private static HashMap<String, String> cityMap = new HashMap<>();
    private int selectedCustomerID = 0;

    public customerReg() {
        initComponents();

        jLabel5.setText(User.getFirst_name() + " " + User.getLast_name());
        jLabel6.setText(User.getEmail());
        jLabel3.setText(User.getEmployeedep());
        
        loadGender();
        loadCity();
        loadCustomers("");
    }

    private void loadCustomers(String searchQuery) {

        //gets search query from universalSerach
        String query = "";

        try {

            button3.setEnabled(true);
            button2.setEnabled(false);

            textField3.setEnabled(true);

            //if no query is passed load customers noramally else load search result
            if (searchQuery.isBlank()) {
                query = "SELECT * FROM customer "
                        + "INNER JOIN gender ON gender.id = customer.gender_id "
                        + "INNER JOIN city ON city.id = customer.city_id ";
            } else {
                query = searchQuery;
            }

            ResultSet rs = MySQL.executeSearch(query);

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            tableAlign.tableCellAlignment(jTable1, 0);

            while (rs.next()) {

                Vector<String> vector = new Vector<>();

                vector.add(rs.getString("id"));
                vector.add(rs.getString("fname"));
                vector.add(rs.getString("lname"));
                vector.add(rs.getString("email"));
                vector.add(rs.getString("mobile"));
                vector.add(rs.getString("gender.id"));

                if (rs.getString("points") == null) {
                    vector.add("No Points");
                } else {
                    vector.add(rs.getString("points"));
                }

                dtm.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadGender() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `gender`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("type"));
                genderMap.put(resultSet.getString("type"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCity() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `city`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                cityMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerCustomer() {
        String fname = textField1.getText();
        String lname = textField2.getText();
        String email = textField3.getText();
        String mobile = textField4.getText();

        String line1 = textField5.getText();
        String line2 = textField6.getText();

        String gender = String.valueOf(this.jComboBox1.getSelectedItem());
        String city = String.valueOf(this.jComboBox2.getSelectedItem());

        Validations validate = new Validations();

        if (fname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "First Name Is Empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!validate.charLen(fname, 45)) {
            JOptionPane.showMessageDialog(this, "First Name Length Must Be Less Than 45 Characters!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (lname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Last Name Is Empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!validate.charLen(lname, 45)) {
            JOptionPane.showMessageDialog(this, "Last Name Length Must Be Less Than 45 Characters!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (validate.validateMobile(mobile)) {
            JOptionPane.showMessageDialog(this, "Invalid Mobile Number!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!validate.charLen(email, 100)) {
            JOptionPane.showMessageDialog(this, "Email Length Must Be Less Than 100 Characters!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (validate.validateEmail(email)) {
            JOptionPane.showMessageDialog(this, "Invalid Email Address!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (validate.validateComboBox(gender)) {
            JOptionPane.showMessageDialog(this, "Please Select A Gender", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (line1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Address Line 1 Is Empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!validate.charLen(line1, 45)) {
            JOptionPane.showMessageDialog(this, "Address Line 1 Length Must Be Less Than 45 Characters!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (line2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Address Line 2 Is Empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!validate.charLen(line2, 45)) {
            JOptionPane.showMessageDialog(this, "Address Line 2 Length Must Be Less Than 45 Characters!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (validate.validateComboBox(city)) {
            JOptionPane.showMessageDialog(this, "Please Select A City For Address", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `customer` WHERE `email` = '" + email + "' OR `mobile` = '" + mobile + "' ");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Diffrend Customer With Same Name Mobile OR Email Already Registered!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MySQL.executeIUD("INSERT INTO `customer` "
                            + "(`fname`,`lname`,`mobile`,`gender_id`,`email`,`city_id`,`line1`,`line2`) "
                            + "VALUES ('" + fname + "','" + lname + "','" + mobile + "','" + genderMap.get(gender) + "','" + email + "' ,'" + cityMap.get(city) + "','" + line1 + "','" + line2 + "')");

                    JOptionPane.showMessageDialog(this, "New Customer Registered Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateButtonClicked() {

        if (selectedCustomerID != 0) {
            String fname = textField1.getText();
            String lname = textField2.getText();
            String email = textField3.getText();
            String mobile = textField4.getText();

            String line1 = textField5.getText();
            String line2 = textField6.getText();

            String gender = String.valueOf(this.jComboBox1.getSelectedItem());
            String city = String.valueOf(this.jComboBox2.getSelectedItem());

            Validations validate = new Validations();

            if (fname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "First Name Is Empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!validate.charLen(fname, 45)) {
                JOptionPane.showMessageDialog(this, "First Name Length Must Be Less Than 45 Characters!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (lname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Last Name Is Empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!validate.charLen(lname, 45)) {
                JOptionPane.showMessageDialog(this, "Last Name Length Must Be Less Than 45 Characters!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (validate.validateMobile(mobile)) {
                JOptionPane.showMessageDialog(this, "Invalid Mobile Number!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!validate.charLen(email, 100)) {
                JOptionPane.showMessageDialog(this, "Email Length Must Be Less Than 45 Characters!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (validate.validateEmail(email)) {
                JOptionPane.showMessageDialog(this, "Invalid Email Address!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (validate.validateComboBox(gender)) {
                JOptionPane.showMessageDialog(this, "Please Select A Gender", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (line1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Address Line 1 Is Empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!validate.charLen(line1, 45)) {
                JOptionPane.showMessageDialog(this, "Address Line 1 Length Must Be Less Than 45 Characters!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (line2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Address Line 2 Is Empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!validate.charLen(line2, 45)) {
                JOptionPane.showMessageDialog(this, "Address Line 2 Length Must Be Less Than 45 Characters!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (validate.validateComboBox(city)) {
                JOptionPane.showMessageDialog(this, "Please Select A City For Address", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                try {

                    ResultSet rs = MySQL.executeSearch("SELECT * FROM `customer` WHERE `email` = '" + email + "' AND `id` = '" + this.selectedCustomerID + "' ");

                    if (rs.next()) {

                        MySQL.executeIUD("UPDATE `customer` SET "
                                + "`fname` = '" + fname + "', "
                                + "`lname` = '" + lname + "', "
                                + "`mobile` = '" + mobile + "', "
                                + "`gender_id` = '" + genderMap.get(gender) + "', "
                                + "`email` = '" + email + "', "
                                + "`city_id` = '" + cityMap.get(city) + "', "
                                + "`line1` = '" + line1 + "', "
                                + "`line2` = '" + line2 + "' "
                                + "WHERE `id` = '" + this.selectedCustomerID + "' ");

                        JOptionPane.showMessageDialog(this, "Customer Data Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        reset();

                    } else {
                        JOptionPane.showMessageDialog(this, "User ID Does Not Match With Email! Invalid User!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "NO Customer Selected!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    //put in button4 actionevent
    private void reset() {

        loadCustomers("");

        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");

        textField5.setText("");
        textField6.setText("");

        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);

        button3.setEnabled(true);
        button2.setEnabled(false);
    }

    private void universalSerach(int type, String string) {

        //needs 2 inputs from a comboBox and a textfield. type is used to set search criterea and string is the search input text
        String queryFilter = "";

        if (type == 0) {
            queryFilter = "id";
        } else if (type == 1) {
            queryFilter = "fname";
        } else if (type == 2) {
            queryFilter = "lname";
        } else if (type == 3) {
            queryFilter = "email";
        } else if (type == 4) {
            queryFilter = "mobile";
        } else {
            queryFilter = "";
        }

        if (queryFilter != "") {

            //if queryFilter is not empty search query is assembled and passed into loadCustomers(query)
            String query = "SELECT * FROM customer "
                    + "INNER JOIN gender ON gender.id = customer.gender_id "
                    + "INNER JOIN city ON city.id = customer.city_id "
                    + "WHERE '" + queryFilter + "' LIKE '%'" + string + "'%'";

            loadCustomers(query);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        textField3 = new com.nex.cms.components.TextField();
        textField7 = new com.nex.cms.components.TextField();
        textField4 = new com.nex.cms.components.TextField();
        button1 = new com.nex.cms.components.Button();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        textField5 = new com.nex.cms.components.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textField6 = new com.nex.cms.components.TextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        button3 = new com.nex.cms.components.Button();
        jLabel4 = new javax.swing.JLabel();
        button4 = new com.nex.cms.components.Button();
        jLabel7 = new javax.swing.JLabel();
        button2 = new com.nex.cms.components.Button();
        textField1 = new com.nex.cms.components.TextField();
        textField2 = new com.nex.cms.components.TextField();

        jSeparator5.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator5.setMaximumSize(new java.awt.Dimension(32767, 5));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "Email", "Mobile", "Gender", "Points"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
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

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel8.setText("Last Name");

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel9.setText("Email");

        jLabel16.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel16.setText("Sort By Name");

        textField3.setForeground(new java.awt.Color(0, 0, 0));
        textField3.setCaretColor(new java.awt.Color(0, 0, 0));
        textField3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField3.setShadowColor(new java.awt.Color(0, 153, 204));

        textField7.setBackground(new java.awt.Color(204, 255, 255));
        textField7.setCaretColor(new java.awt.Color(0, 0, 0));
        textField7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        textField4.setForeground(new java.awt.Color(0, 0, 0));
        textField4.setCaretColor(new java.awt.Color(0, 0, 0));
        textField4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField4.setShadowColor(new java.awt.Color(0, 153, 204));

        button1.setText("Customer  Details Report");
        button1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel10.setText("Mobile");

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel11.setText("Gendr");

        jComboBox1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));

        jSeparator2.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator2.setMaximumSize(new java.awt.Dimension(32767, 5));

        jSeparator3.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator3.setMaximumSize(new java.awt.Dimension(32767, 5));

        jSeparator4.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setMaximumSize(new java.awt.Dimension(32767, 5));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("CustomerAddress");

        textField5.setForeground(new java.awt.Color(0, 0, 0));
        textField5.setCaretColor(new java.awt.Color(0, 0, 0));
        textField5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField5.setShadowColor(new java.awt.Color(0, 153, 204));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("Customer Registration");

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel13.setText("Line 1");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel2.setText("Devicion :");

        textField6.setForeground(new java.awt.Color(0, 0, 0));
        textField6.setCaretColor(new java.awt.Color(0, 0, 0));
        textField6.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField6.setShadowColor(new java.awt.Color(0, 153, 204));

        jLabel3.setText("Devicion Name");

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel14.setText("Line 2");

        jSeparator1.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator1.setMaximumSize(new java.awt.Dimension(32767, 5));

        jComboBox2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));

        jLabel6.setText("Email");

        jLabel15.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel15.setText("City");

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setText("Name");

        button3.setBackground(new java.awt.Color(153, 255, 153));
        button3.setForeground(new java.awt.Color(0, 0, 0));
        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-add-16.png"))); // NOI18N
        button3.setText("   Add to GRN");
        button3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button3.setRippleColor(new java.awt.Color(153, 204, 255));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-user-30.png"))); // NOI18N

        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-30.png"))); // NOI18N
        button4.setRippleColor(new java.awt.Color(153, 204, 255));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel7.setText("First Name");

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

        textField1.setForeground(new java.awt.Color(0, 0, 0));
        textField1.setCaretColor(new java.awt.Color(0, 0, 0));
        textField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField1.setShadowColor(new java.awt.Color(0, 153, 204));

        textField2.setForeground(new java.awt.Color(0, 0, 0));
        textField2.setCaretColor(new java.awt.Color(0, 0, 0));
        textField2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField2.setShadowColor(new java.awt.Color(0, 153, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                                .addGap(16, 16, 16))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(textField7, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                .addGap(291, 291, 291)))
                        .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(button4, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(357, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel1))
                        .addGap(445, 445, 445)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                            .addComponent(textField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(16, 16, 16)
                        .addComponent(textField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(9, 9, 9))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(79, 79, 79))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(9, 9, 9)))
                        .addGap(3, 3, 3))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)))
                        .addGap(22, 22, 22)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        updateButtonClicked();
    }//GEN-LAST:event_button2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();

        String userid = String.valueOf(jTable1.getValueAt(row, 0));

        if (row > -1) {

            if (evt.getClickCount() == 2) {
                try {

                    button3.setEnabled(false);
                    button2.setEnabled(true);

                    textField3.setEnabled(false);

                    ResultSet rs = MySQL.executeSearch("SELECT * FROM `customer` "
                            + "INNER JOIN `gender` ON `gender`.`id` = `customer`.`gender_id` "
                            + "INNER JOIN `city` ON `city`.`id` = `customer`.`city_id`"
                            + "WHERE `customer`.`id` = '" + userid + "'");

                    System.out.println("rs check");
                    while (rs.next()) {
                        System.out.println("rsok");

                        this.selectedCustomerID = rs.getInt("customer.id");

                        textField1.setText(rs.getString("fname"));
                        textField2.setText(rs.getString("lname"));
                        textField3.setText(rs.getString("email"));
                        textField4.setText(rs.getString("mobile"));

                        textField5.setText(rs.getString("line1"));
                        textField6.setText(rs.getString("line2"));

                        jComboBox1.setSelectedItem(rs.getString("gender.type"));
                        jComboBox2.setSelectedItem(rs.getString("city.name"));

                        System.out.println("ok");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Select A Valid Row!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        registerCustomer();
    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        reset();
    }//GEN-LAST:event_button4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button2;
    private com.nex.cms.components.Button button3;
    private com.nex.cms.components.Button button4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable1;
    private com.nex.cms.components.TextField textField1;
    private com.nex.cms.components.TextField textField2;
    private com.nex.cms.components.TextField textField3;
    private com.nex.cms.components.TextField textField4;
    private com.nex.cms.components.TextField textField5;
    private com.nex.cms.components.TextField textField6;
    private com.nex.cms.components.TextField textField7;
    // End of variables declaration//GEN-END:variables
}
