package com.nex.cms.panel.reporting;

import com.nex.cms.connection.MySQL;
import static com.nex.cms.frame.login.logger;
import com.nex.cms.model.tableAlign;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author xDhanu
 */
public class report extends javax.swing.JPanel {

    public report() {
        initComponents();

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

        jTable3.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 12));
        jTable3.getTableHeader().setOpaque(false);
        jTable3.getTableHeader().setBackground(new Color(204, 255, 255));
        jTable3.getTableHeader().setForeground(new Color(0, 0, 0));
        jTable3.setRowHeight(30);
        tableAlign.tableCellAlignment(jTable3, 0);

        jTable4.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 12));
        jTable4.getTableHeader().setOpaque(false);
        jTable4.getTableHeader().setBackground(new Color(204, 255, 255));
        jTable4.getTableHeader().setForeground(new Color(0, 0, 0));
        jTable4.setRowHeight(30);
        tableAlign.tableCellAlignment(jTable4, 0);

        jTable5.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 12));
        jTable5.getTableHeader().setOpaque(false);
        jTable5.getTableHeader().setBackground(new Color(204, 255, 255));
        jTable5.getTableHeader().setForeground(new Color(0, 0, 0));
        jTable5.setRowHeight(30);
        tableAlign.tableCellAlignment(jTable5, 0);

        jTable6.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 12));
        jTable6.getTableHeader().setOpaque(false);
        jTable6.getTableHeader().setBackground(new Color(204, 255, 255));
        jTable6.getTableHeader().setForeground(new Color(0, 0, 0));
        jTable6.setRowHeight(30);
        tableAlign.tableCellAlignment(jTable6, 0);

        loadGrn();
        loadProdouct();
        loadStock();
        loadInvoice();
        loadProperty();
        loadPropertyProdouct();
    }

    private void loadGrn() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT `grn`.`id`,`employee`.`employee_fname`,`employee`.`employee_lname`,`suplier`.`fname`,`suplier`.`lname`,`grn`.`WePaidAmount`"
                    + "FROM `grn` INNER JOIN `employee` ON `grn`.`employee_employee_id`= `employee`.`employee_id`"
                    + "INNER JOIN `suplier` ON `grn`.`suplier_suplier_id`=`suplier`.`suplier_id`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {

                Vector<String> v = new Vector<>();
                v.add(rs.getString("grn.id"));
                v.add(rs.getString("employee_fname"));
                v.add(rs.getString("employee_lname"));
                v.add(rs.getString("suplier.fname"));
                v.add(rs.getString("suplier.lname"));
                v.add(rs.getString("WePaidAmount"));

                model.addRow(v);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadProdouct() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT `product`.`id`,`product`.`name`,`brand`.`name`"
                    + " FROM `product` INNER JOIN `brand` ON `product`.`brand_id` = `brand`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            while (rs.next()) {

                Vector<String> v = new Vector<>();
                v.add(rs.getString("product.id"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("brand.name"));

                model.addRow(v);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadStock() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT `stock`.`id`,`product`.`name`,`stock`.`qty`,`stock`.`sellingprice`,`stock`.`mfd`,`stock`.`exp`"
                    + " FROM `stock` INNER JOIN `product` ON `stock`.`product_id`=`product`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

            while (rs.next()) {

                Vector<String> v = new Vector<>();
                v.add(rs.getString("stock.id"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("stock.qty"));
                v.add(rs.getString("stock.sellingprice"));
                v.add(rs.getString("stock.mfd"));
                v.add(rs.getString("stock.exp"));

                model.addRow(v);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadInvoice() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT `invoice`.`id`,`invoice`.`date`,`invoice`.`discount`,`invoice`.`paid_amount`,`customer`.`fname`,`customer`.`lname`,"
                    + "`employee`.`employee_fname`,`employee`.`employee_lname`,`payment_methord`.`name` "
                    + "FROM `invoice` INNER JOIN `employee` ON `invoice`.`employee_employee_id`=`employee`.`employee_id`"
                    + "INNER JOIN `customer` ON `invoice`.`customer_id`=`customer`.`id`"
                    + "INNER JOIN `payment_methord` ON `invoice`.`payment_methord_id`=`payment_methord`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
            model.setRowCount(0);

            while (rs.next()) {

                Vector<String> v = new Vector<>();
                v.add(rs.getString("invoice.id"));
                v.add(rs.getString("invoice.date"));
                v.add(rs.getString("invoice.discount"));
                v.add(rs.getString("invoice.paid_amount"));
                v.add(rs.getString("customer.fname"));
                v.add(rs.getString("customer.lname"));
                v.add(rs.getString("employee.employee_fname"));
                v.add(rs.getString("employee.employee_lname"));
                v.add(rs.getString("payment_methord.name"));

                model.addRow(v);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadProperty() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `property` INNER JOIN `property_product` ON `property`.`property_product_id` = `property_product`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
            model.setRowCount(0);

            while (rs.next()) {

                Vector<String> v = new Vector<>();
                v.add(rs.getString("property.id"));
                v.add(rs.getString("property_product.name"));
                v.add(rs.getString("property.qty"));
                v.add(rs.getString("property.price"));
                v.add(rs.getString("property.pd"));
                v.add(rs.getString("property.edu"));

                model.addRow(v);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadPropertyProdouct() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `property_product` INNER JOIN `property_brand` ON `property_product`.`property_brand_id` = `property_product`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
            model.setRowCount(0);

            while (rs.next()) {

                Vector<String> v = new Vector<>();
                v.add(rs.getString("property_product.id"));
                v.add(rs.getString("property_product.name"));
                v.add(rs.getString("property_brand.name"));

                model.addRow(v);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        materialTabbed1 = new com.nex.cms.components.MaterialTabbed();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        textField1 = new com.nex.cms.components.TextField();
        button1 = new com.nex.cms.components.Button();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textField2 = new com.nex.cms.components.TextField();
        button2 = new com.nex.cms.components.Button();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textField3 = new com.nex.cms.components.TextField();
        button3 = new com.nex.cms.components.Button();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textField4 = new com.nex.cms.components.TextField();
        button4 = new com.nex.cms.components.Button();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        textField5 = new com.nex.cms.components.TextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        textField6 = new com.nex.cms.components.TextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        button5 = new com.nex.cms.components.Button();
        button6 = new com.nex.cms.components.Button();

        setBackground(new java.awt.Color(255, 255, 255));

        materialTabbed1.setToolTipText("");
        materialTabbed1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(972, 580));

        jTable1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Employee First Name", "Employee Last Name", "Suplier First Name", "Suplier Last Name", "Piad Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Search");

        textField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField1KeyReleased(evt);
            }
        });

        button1.setBackground(new java.awt.Color(204, 204, 255));
        button1.setForeground(new java.awt.Color(204, 204, 255));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 255));
        jLabel1.setText("All Goods Received Note");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Date", "Paid Amount", "Customer First  Name", "Customer Last Name", "Employee First  Name", "Employee Last Name", "Product Name", "Payment method"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jLabel8.setText("Search");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 255));
        jLabel3.setText("All Invoice Report");

        textField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField2KeyReleased(evt);
            }
        });

        button2.setBackground(new java.awt.Color(204, 204, 255));
        button2.setForeground(new java.awt.Color(204, 204, 255));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(83, 83, 83)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(296, 296, 296)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                .addGap(471, 471, 471))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        materialTabbed1.addTab("Inventory Reports", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(972, 580));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Name", "Brand Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable2);

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 153, 255));
        jLabel6.setText("All Product Report");

        jLabel7.setText("Search");

        textField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField3KeyReleased(evt);
            }
        });

        button3.setBackground(new java.awt.Color(204, 204, 255));
        button3.setForeground(new java.awt.Color(204, 204, 255));
        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 153, 255));
        jLabel9.setText("All Stock Report");

        jLabel10.setText("Search");

        textField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField4KeyReleased(evt);
            }
        });

        button4.setBackground(new java.awt.Color(204, 204, 255));
        button4.setForeground(new java.awt.Color(204, 204, 255));
        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Product Name", "Quantity", "Selling Price", "MFD", "EXP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textField4, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                        .addGap(302, 302, 302)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textField3, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                        .addGap(461, 461, 461))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)))
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        materialTabbed1.addTab("Stock Reports", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(972, 580));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Name", "Brand Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable5);

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 153, 255));
        jLabel11.setText("All Product Report");

        jLabel12.setText("Search");

        textField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField5KeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 153, 255));
        jLabel13.setText("All Property Report");

        jLabel14.setText("Search");

        textField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField6KeyReleased(evt);
            }
        });

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Product Name", "Quantity", "Selling Price", "MFD", "EXP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTable6);

        button5.setBackground(new java.awt.Color(204, 204, 255));
        button5.setForeground(new java.awt.Color(204, 204, 255));
        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        button6.setBackground(new java.awt.Color(204, 204, 255));
        button6.setForeground(new java.awt.Color(204, 204, 255));
        button6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textField6, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                        .addGap(291, 291, 291)
                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                        .addGap(461, 461, 461))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)))
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        materialTabbed1.addTab("Property Reports", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        materialTabbed1.getAccessibleContext().setAccessibleName("Inventory Reports");
    }// </editor-fold>//GEN-END:initComponents

    private void textField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField1KeyReleased

        DefaultTableModel ob = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        jTable1.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(textField1.getText()));

    }//GEN-LAST:event_textField1KeyReleased

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed

        try {
            String path = "src/reports/repSec_allGrnReport.jasper";

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
            logger.log(Level.WARNING, "Print repSec_allInvoiceReport fail JRException", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print repSec_allInvoiceReport fail unexpected exceptions", e);
        }

    }//GEN-LAST:event_button1ActionPerformed

    private void textField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField2KeyReleased

        DefaultTableModel ob = (DefaultTableModel) jTable4.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        jTable4.setRowSorter(obj);

        obj.setRowFilter(RowFilter.regexFilter(textField2.getText()));

    }//GEN-LAST:event_textField2KeyReleased

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed

        try {
            String path = "src/reports/repSec_allInvoiceReport.jasper";

            LocalDateTime datetime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fdt = datetime.format(formatter);

            HashMap<String, Object> parameter = new HashMap<>();
            parameter.put("Parameter1", fdt);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable4.getModel());
            JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameter, dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print repSec_allInvoiceReport fail JRException", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print repSec_allInvoiceReport fail unexpected exceptions", e);
        }

    }//GEN-LAST:event_button2ActionPerformed

    private void textField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField3KeyReleased

        DefaultTableModel ob = (DefaultTableModel) jTable2.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        jTable2.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(textField3.getText()));

    }//GEN-LAST:event_textField3KeyReleased

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed

        try {
            String path = "src/reports/repSec_allProductReport.jasper";

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
            logger.log(Level.WARNING, "Print repSec_allInvoiceReport fail JRException", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print repSec_allInvoiceReport fail unexpected exceptions", e);
        }

    }//GEN-LAST:event_button3ActionPerformed

    private void textField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField4KeyReleased

        DefaultTableModel ob = (DefaultTableModel) jTable3.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        jTable3.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(textField4.getText()));

    }//GEN-LAST:event_textField4KeyReleased

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed

        try {
            String path = "src/reports/repSec_stockReport.jasper";

            LocalDateTime datetime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fdt = datetime.format(formatter);

            HashMap<String, Object> parameter = new HashMap<>();
            parameter.put("Parameter1", fdt);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable3.getModel());
            JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameter, dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print repSec_stockReport fail JRException", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print repSec_stockReport fail unexpected exceptions", e);
        }


    }//GEN-LAST:event_button4ActionPerformed

    private void textField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField5KeyReleased

        DefaultTableModel ob = (DefaultTableModel) jTable5.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        jTable5.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(textField5.getText()));

    }//GEN-LAST:event_textField5KeyReleased

    private void textField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField6KeyReleased

        DefaultTableModel ob = (DefaultTableModel) jTable6.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        jTable6.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(textField6.getText()));

    }//GEN-LAST:event_textField6KeyReleased

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed

        try {
            String path = "src/reports/repSec_allPropertyProductReport.jasper";

            LocalDateTime datetime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fdt = datetime.format(formatter);

            HashMap<String, Object> parameter = new HashMap<>();
            parameter.put("Parameter1", fdt);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable5.getModel());
            JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameter, dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print repSec_stockReport fail JRException", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print repSec_stockReport fail unexpected exceptions", e);
        }

    }//GEN-LAST:event_button5ActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed

        try {
            String path = "src/reports/repSec_proprtyReport.jasper";

            LocalDateTime datetime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fdt = datetime.format(formatter);

            HashMap<String, Object> parameter = new HashMap<>();
            parameter.put("Parameter1", fdt);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable6.getModel());
            JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameter, dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print repSec_stockReport fail JRException", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print repSec_stockReport fail unexpected exceptions", e);
        }

    }//GEN-LAST:event_button6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button2;
    private com.nex.cms.components.Button button3;
    private com.nex.cms.components.Button button4;
    private com.nex.cms.components.Button button5;
    private com.nex.cms.components.Button button6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private com.nex.cms.components.MaterialTabbed materialTabbed1;
    private com.nex.cms.components.TextField textField1;
    private com.nex.cms.components.TextField textField2;
    private com.nex.cms.components.TextField textField3;
    private com.nex.cms.components.TextField textField4;
    private com.nex.cms.components.TextField textField5;
    private com.nex.cms.components.TextField textField6;
    // End of variables declaration//GEN-END:variables
}
