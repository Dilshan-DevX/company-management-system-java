package com.nex.cms.frame.inventory;

import com.nex.cms.panel.Inventory.GRN;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import com.nex.cms.connection.MySQL;
import com.nex.cms.model.tableAlign;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class SupplierRegistration extends javax.swing.JFrame {

    private String companyId;//for store a company id

    public void setCompanyId(String companyId) {
        this.companyId = companyId; //for set a company id
    }

    private GRN grn;

    public void setGrn(GRN grn) {
        this.grn = grn;
    }

    public SupplierRegistration() {
        initComponents();
        jTable1.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(204, 255, 255));
        jTable1.getTableHeader().setForeground(new Color(0, 0, 0));
        jTable1.setRowHeight(30);
        tableAlign.tableCellAlignment(jTable1, 0);

        textField2.grabFocus();

        generateSuplierId();
        loadsuplier("suplier_id", "ASC", "");

    }

    public void getCompanyName(String name) {
        jLabel7.setText(name);
    }

    public void addSupplierGrabFocus() {
        button2.grabFocus();
    }

    private void loadsuplier(String column, String order, String mobile) {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `suplier`"
                    + "INNER JOIN `company` ON `suplier`.`company_id` = `company`.`id`"
                    + "WHERE `mobile` LIKE '" + mobile + "%' ORDER BY `" + column + "` " + order + "");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("suplier_id"));
                vector.add(resultSet.getString("fname"));
                vector.add(resultSet.getString("lname"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("company.name"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void generateSuplierId() {
        long id = System.currentTimeMillis();
        textField1.setText(String.valueOf(id));
        textField1.setEditable(false);
    }

    private void reset() {

        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        jLabel7.setText("COMPANY NAME HERE");
        companyId = null;
        jComboBox1.setSelectedIndex(0);
        jLabel10.setText("0  ");
        jLabel12.setText("0.00  ");
        textField2.grabFocus();
        jTable1.clearSelection();
        generateSuplierId();
        loadsuplier("suplier_id", "ASC", textField5.getText());
        textField1.setEnabled(true);
        button2.setEnabled(true);

    }

    private void search() {
        String mobile = textField5.getText();

        switch (jComboBox1.getSelectedIndex()) {
            case 0 ->
                loadsuplier("suplier_id", "ASC", mobile);
            case 1 ->
                loadsuplier("suplier_id", "DESC", mobile);
            case 2 ->
                loadsuplier("fname", "ASC", mobile);
            case 3 ->
                loadsuplier("fname", "DESC", mobile);
            case 4 ->
                loadsuplier("company`.`name", "ASC", mobile);
            case 5 ->
                loadsuplier("company`.`name", "DESC", mobile);
            default -> {
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        textField1 = new com.nex.cms.components.TextField();
        jLabel3 = new javax.swing.JLabel();
        textField2 = new com.nex.cms.components.TextField();
        textField3 = new com.nex.cms.components.TextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textField4 = new com.nex.cms.components.TextField();
        jLabel6 = new javax.swing.JLabel();
        textField5 = new com.nex.cms.components.TextField();
        jLabel7 = new javax.swing.JLabel();
        button1 = new com.nex.cms.components.Button();
        button2 = new com.nex.cms.components.Button();
        button3 = new com.nex.cms.components.Button();
        button4 = new com.nex.cms.components.Button();
        jSeparator2 = new javax.swing.JSeparator();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        button5 = new com.nex.cms.components.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NexOra | Supplier Registration ");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("Supplier Registration");

        jSeparator1.setForeground(new java.awt.Color(0, 204, 255));

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel2.setText("ID");

        textField1.setForeground(new java.awt.Color(0, 0, 0));
        textField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField1.setShadowColor(new java.awt.Color(51, 204, 255));
        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel3.setText("First Name");

        textField2.setForeground(new java.awt.Color(0, 0, 0));
        textField2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField2.setShadowColor(new java.awt.Color(51, 204, 255));
        textField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField2ActionPerformed(evt);
            }
        });

        textField3.setForeground(new java.awt.Color(0, 0, 0));
        textField3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField3.setShadowColor(new java.awt.Color(51, 204, 255));
        textField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel4.setText("Last Name");

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel5.setText("Email");

        textField4.setForeground(new java.awt.Color(0, 0, 0));
        textField4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField4.setShadowColor(new java.awt.Color(51, 204, 255));
        textField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField4ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel6.setText("Mobile");

        textField5.setForeground(new java.awt.Color(0, 0, 0));
        textField5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField5.setShadowColor(new java.awt.Color(51, 204, 255));
        textField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField5ActionPerformed(evt);
            }
        });
        textField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField5KeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("COMPANY NAME HERE");

        button1.setBackground(new java.awt.Color(204, 255, 255));
        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setText("Select Company");
        button1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button1.setRippleColor(new java.awt.Color(153, 204, 255));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(153, 255, 153));
        button2.setForeground(new java.awt.Color(0, 0, 0));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-add-16.png"))); // NOI18N
        button2.setText("   Add Supplier");
        button2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button2.setRippleColor(new java.awt.Color(153, 204, 255));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button3.setBackground(new java.awt.Color(204, 255, 255));
        button3.setForeground(new java.awt.Color(0, 0, 0));
        button3.setText("Update");
        button3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button3.setRippleColor(new java.awt.Color(153, 204, 255));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-30.png"))); // NOI18N
        button4.setRippleColor(new java.awt.Color(153, 204, 255));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 204, 255));

        jComboBox1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Supplier ID ASC", "Supplier ID DESC", "Supplier Name ASC", "Supplier Name DESC", "Company ASC", "Company DESC" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel8.setText("Sort By");

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel9.setText("Total GRN   :");

        jLabel10.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("0  ");
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel11.setText("Pending Payment   :");

        jLabel12.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("0.00  ");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));

        jSeparator3.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator4.setForeground(new java.awt.Color(0, 204, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "Email", "Mobile", "Company"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        button5.setText("More Details");
        button5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button5.setRippleColor(new java.awt.Color(153, 204, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(11, 11, 11)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(textField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(button2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        CompanyRegistration companyRegistration = new CompanyRegistration(this, true);
        companyRegistration.setVisible(true);
    }//GEN-LAST:event_button1ActionPerformed

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        textField2.grabFocus();
    }//GEN-LAST:event_textField1ActionPerformed

    private void textField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField2ActionPerformed
        textField3.grabFocus();
    }//GEN-LAST:event_textField2ActionPerformed

    private void textField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField3ActionPerformed
        textField4.grabFocus();
    }//GEN-LAST:event_textField3ActionPerformed

    private void textField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField4ActionPerformed
        textField5.grabFocus();
    }//GEN-LAST:event_textField4ActionPerformed

    private void textField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField5ActionPerformed
        button1.grabFocus();
    }//GEN-LAST:event_textField5ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed

        try {
            String suplierId = textField1.getText();
            String fname = textField2.getText();
            String lname = textField3.getText();
            String email = textField4.getText();
            String mobile = textField5.getText();

            if (fname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (fname.length() > 45) {
                JOptionPane.showMessageDialog(this, "Your name has grown too long. Enter the brief!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (lname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your last name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (lname.length() > 45) {
                JOptionPane.showMessageDialog(this, "Your name has grown too long. Enter the brief!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your email", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+"
                    + "(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
                JOptionPane.showMessageDialog(this, "Invalid email address, Please try with true", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (email.length() > 100) {
                JOptionPane.showMessageDialog(this, "Email address to long, please try with another one", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (mobile.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Mobile Number", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
                JOptionPane.showMessageDialog(this, "Invalid mobile number, Please try with true", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (mobile.length() > 12) {
                JOptionPane.showMessageDialog(this, "Invalid mobile number, Please try with true", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (companyId == null) {
                JOptionPane.showMessageDialog(this, "Please select a company", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `suplier` WHERE `mobile` = '" + mobile + "' OR `email`='" + email + "'OR `suplier_id`='" + suplierId + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This user allredy registerd", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MySQL.executeIUD("INSERT INTO `suplier` (`fname`,`lname`,`email`,`mobile`,`suplier_id`,`company_id`)"
                            + "VALUES ('" + fname + "','" + lname + "','" + email + "','" + mobile + "','" + suplierId + "','" + companyId + "')");
                    reset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_button2ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        reset();
    }//GEN-LAST:event_button4ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        search();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void textField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField5KeyReleased
        search();
    }//GEN-LAST:event_textField5KeyReleased

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed

        try {
            String suplierId = textField1.getText();
            String fname = textField2.getText();
            String lname = textField3.getText();
            String email = textField4.getText();
            String mobile = textField5.getText();

            if (fname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (fname.length() > 45) {
                JOptionPane.showMessageDialog(this, "Your name has grown too long. Enter the brief!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (lname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your last name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (lname.length() > 45) {
                JOptionPane.showMessageDialog(this, "Your name has grown too long. Enter the brief!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your email", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+"
                    + "(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
                JOptionPane.showMessageDialog(this, "Invalid email address, Please try with true", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (email.length() > 100) {
                JOptionPane.showMessageDialog(this, "Email address to long, please try with another one", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (mobile.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Mobile Number", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
                JOptionPane.showMessageDialog(this, "Invalid mobile number, Please try with true", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (mobile.length() > 12) {
                JOptionPane.showMessageDialog(this, "Invalid mobile number, Please try with true", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `suplier` WHERE `mobile` = '" + mobile + "' AND `email`='" + email + "' AND `suplier_id` !='" + suplierId + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This email or mobile already used", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    String a;

                    if (jLabel7.getText().equals(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 5)))) { //company update not requeried
                        a = "";
                    } else { //company update requeried
                        a = ", `company_id`= '" + companyId + "'";
                    }

                    MySQL.executeIUD("UPDATE `suplier` SET `fname`='" + fname + "',`lname`='" + lname + "',`email`='" + email + "',`mobile`='" + mobile + "'" + a + "WHERE `suplier_id`='" + suplierId + "'");

                    reset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_button3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        button2.setEnabled(false);
        int row = jTable1.getSelectedRow();

        textField1.setText(String.valueOf(jTable1.getValueAt(row, 0)));
        textField1.setEnabled(false);
        textField2.setText(String.valueOf(jTable1.getValueAt(row, 1)));
        textField3.setText(String.valueOf(jTable1.getValueAt(row, 2)));
        textField4.setText(String.valueOf(jTable1.getValueAt(row, 3)));
        textField5.setText(String.valueOf(jTable1.getValueAt(row, 4)));
        jLabel7.setText(String.valueOf(jTable1.getValueAt(row, 5)));

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `grn` INNER JOIN `grn_item`"
                    + "ON `grn`.`id` = `grn_item`.`grn_id` WHERE `grn`.`suplier_suplier_id` = '" + String.valueOf(jTable1.getValueAt(row, 0)) + "'");

            double total = 0;

            HashMap<Long, Double> grns = new HashMap<>();

            while (resultSet.next()) {

                double qty = resultSet.getDouble("grn_item.qty");

                double buyingPrice = resultSet.getDouble("grn_item.WeBuyingPrice");

                double itemTotal = qty * buyingPrice;

                total += itemTotal;

                grns.put(resultSet.getLong("grn.id"), resultSet.getDouble("grn.WePaidAmount"));
            }

            double totalPaid = 0;

            for (Double paid : grns.values()) {

                totalPaid = totalPaid + paid;
            }

            jLabel10.setText("  " + String.valueOf(grns.size()));
            jLabel12.setText(" " + String.valueOf(total - totalPaid));

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (evt.getClickCount() == 2) {

            if (grn != null) {

                grn.getjLabel9().setText(String.valueOf(jTable1.getValueAt(row, 1) + " " + (jTable1.getValueAt(row, 2)))); //Supplier Name
                grn.getjLabel10().setText(String.valueOf(jTable1.getValueAt(row, 0))); //Supplier ID
                grn.getjLabel11().setText(String.valueOf(jTable1.getValueAt(row, 3))); //Supplier Email
                grn.getjLabel27().setText(String.valueOf(jTable1.getValueAt(row, 4))); //Supplier Mobile
                
                this.dispose();

            }

        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SupplierRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SupplierRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SupplierRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SupplierRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

//        /* Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SupplierRegistration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button2;
    private com.nex.cms.components.Button button3;
    private com.nex.cms.components.Button button4;
    private com.nex.cms.components.Button button5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private com.nex.cms.components.TextField textField1;
    private com.nex.cms.components.TextField textField2;
    private com.nex.cms.components.TextField textField3;
    private com.nex.cms.components.TextField textField4;
    private com.nex.cms.components.TextField textField5;
    // End of variables declaration//GEN-END:variables

}
