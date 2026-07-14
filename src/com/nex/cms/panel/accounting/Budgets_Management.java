package com.nex.cms.panel.accounting;//GEN-FIRST:event_button2ActionPerformed
//GEN-LAST:event_button2ActionPerformed
import com.nex.cms.connection.MySQL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

// <editor-fold defaultstate="collapsed" desc="Generated Code">
public class Budgets_Management extends Accounts_Managament {

    public Budgets_Management() {
        initComponents();
        loadHR();
        loadInventry();
        loadMarketing();
        loadLoan();
        total();
    }

    private void total() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT SUM(total) AS total FROM `total`");
            while (resultSet.next()) {
                double sum = resultSet.getDouble("total");
                jLabel32.setText("Total: " + sum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLoan() {

        try {

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `budget_loan`");

            while (resultSet.next()) {

                Vector vector = new Vector();
                vector.add(resultSet.getString("loan_id"));
                vector.add(resultSet.getString("account_number"));
                vector.add(resultSet.getString("get_date"));
                vector.add(resultSet.getString("amount"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadMarketing() {

        try {

            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `marketing_account`");

            while (resultSet.next()) {

                Vector vector = new Vector();
                vector.add(resultSet.getString("marketing_id"));
                vector.add(resultSet.getString("account_number"));
                vector.add(resultSet.getString("date"));
                vector.add(resultSet.getString("amount"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadHR() {

        try {

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `hr_account`");

            while (resultSet.next()) {

                Vector vector = new Vector();
                vector.add(resultSet.getString("hr_id"));
                vector.add(resultSet.getString("account_number"));
                vector.add(resultSet.getString("date"));
                vector.add(resultSet.getString("amount"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadInventry() {

        try {

            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
            model.setRowCount(0);

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `inventory_account`");

            while (resultSet.next()) {

                Vector vector = new Vector();
                vector.add(resultSet.getString("inventory_id"));
                vector.add(resultSet.getString("account_number"));
                vector.add(resultSet.getString("date"));
                vector.add(resultSet.getString("amount"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel11 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        textField1 = new com.nex.cms.components.TextField();
        button1 = new com.nex.cms.components.Button();
        textField2 = new com.nex.cms.components.TextField();
        button2 = new com.nex.cms.components.Button();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        textField3 = new com.nex.cms.components.TextField();
        button3 = new com.nex.cms.components.Button();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        textField4 = new com.nex.cms.components.TextField();
        button4 = new com.nex.cms.components.Button();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        button5 = new com.nex.cms.components.Button();

        jLabel24.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel24.setText("Budget Management");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Human Resourse Department");

        jLabel30.setBackground(new java.awt.Color(226, 73, 73));
        jLabel30.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel30.setText("Rs.");

        jLabel32.setBackground(new java.awt.Color(226, 73, 73));
        jLabel32.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 0, 0));
        jLabel32.setText("600000");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 51, 51));
        jLabel35.setText("Total Capital (Company)");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setText("Loans Management Department");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setText("Marketing Department");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setText("Inventry Management Department");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "ID", "Account Name", "Date", "Paid Amount"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "ID", "Account Number", "Date", "Paid Amount"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "ID", "Account Name", "Date", "Paid Ammount"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "ID", "Account Name", "Date", "Paid Amount"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jLabel1.setText("Payment ID");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Null");

        jLabel3.setText("Account Number");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("null");

        jLabel5.setText("Date");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Null");

        jLabel7.setText("Payed Amount");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Null");

        button1.setBackground(new java.awt.Color(153, 204, 255));
        button1.setText("Pay");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(153, 204, 255));
        button2.setText("Pay");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Null");

        jLabel10.setText("Payed Amount");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Null");

        jLabel12.setText("Date");

        jLabel13.setText("Account Number");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("null");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Null");

        jLabel16.setText("Payment ID");

        button3.setBackground(new java.awt.Color(153, 204, 255));
        button3.setText("Pay");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Null");

        jLabel18.setText("Payed Amount");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Null");

        jLabel20.setText("Date");

        jLabel21.setText("Account Number");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("null");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("Null");

        jLabel25.setText("Payment ID");

        button4.setBackground(new java.awt.Color(153, 204, 255));
        button4.setText("Pay");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("Null");

        jLabel27.setText("Payed Amount");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("Null");

        jLabel31.setText("Date");

        jLabel33.setText("Account Number");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel39.setText("null");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel40.setText("Null");

        jLabel41.setText("Payment ID");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("Rs.");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 51, 51));
        jLabel43.setText("null");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 51, 51));
        jLabel44.setText("Total");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 51, 51));
        jLabel45.setText("null");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setText("Rs.");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 51, 51));
        jLabel47.setText("Total");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 51, 51));
        jLabel48.setText("null");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 51, 51));
        jLabel50.setText("Total");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setText("Rs.");

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 51, 51));
        jLabel51.setText("Total");

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
        jLabel52.setText("Rs.");

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 51, 51));
        jLabel53.setText("null");

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-48.png"))); // NOI18N
        jLabel54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel54MouseClicked(evt);
            }
        });

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-setting-48.png"))); // NOI18N

        button5.setBackground(new java.awt.Color(153, 204, 255));
        button5.setText("Other Payment");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(1024, 1024, 1024)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1032, 1032, 1032)
                                .addComponent(button5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(70, 70, 70))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(200, 200, 200)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(190, 190, 190)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(180, 180, 180)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(260, 260, 260)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(280, 280, 280)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(280, 280, 280)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(220, 220, 220)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(240, 240, 240)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(240, 240, 240)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(260, 260, 260)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(280, 280, 280)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(280, 280, 280)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(246, 246, 246)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(227, 227, 227)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(263, 263, 263)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(260, 260, 260)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(280, 280, 280)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(280, 280, 280)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(220, 220, 220)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(240, 240, 240)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(240, 240, 240)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(260, 260, 260)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(280, 280, 280)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(280, 280, 280)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(220, 220, 220)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(240, 240, 240)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(240, 240, 240)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel44)
                                .addGap(345, 345, 345)
                                .addComponent(jLabel47)
                                .addGap(365, 365, 365)
                                .addComponent(jLabel50)
                                .addGap(364, 364, 364)
                                .addComponent(jLabel51))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(200, 200, 200)
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(220, 220, 220)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(219, 219, 219)
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel34)
                                                        .addComponent(jLabel54))))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(button5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(3, 3, 3)))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(jLabel14))
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(jLabel51)))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel52)
                                                        .addComponent(jLabel53)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 5, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(new Date());

            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = currentTime.format(formatter);

            String accName = jLabel28.getText();
            String accountNumber = "account_number";
            double amount = Double.valueOf(textField1.getText());

            if (textField1 == null) {
                JOptionPane.showMessageDialog(this, "Please Enter Transfer Amount", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                MySQL.executeIUD("INSERT INTO `hr_account`(`account_number`,`date`,`amount`) VALUES('" + accountNumber + "','" + date + "','" + amount + "')");

                ResultSet resultSet = MySQL.executeSearch("SELECT SUM(total) AS total FROM `total`");
                while (resultSet.next()) {
                    double sum = resultSet.getDouble("total");
                    double calculate = sum -= amount;

                    MySQL.executeIUD("UPDATE `total` SET `total`='" + calculate + "'");
                    jLabel32.setText("Total: " + calculate);
                }

                JOptionPane.showMessageDialog(this, "Transaction Successfully Human Resorse Department", "Warning", JOptionPane.WARNING_MESSAGE);
                loadHR();
                MySQL.executeIUD("INSERT INTO `transaction`(`account_number`,`account_name`,`transfer_balance`,`time`,`date`) VALUES('" + accountNumber + "','" + accName + "','" + amount + "','" + formattedTime + "','" + date + "')");
            }

            String path = "src//reports//BMhrm.jasper";

            HashMap<String, Object> params1 = new HashMap<>();

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable2.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, params1, dataSource);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {

        int row = jTable2.getSelectedRow();

        String id = (String) jTable2.getValueAt(row, 0);
        String accNumber = (String) jTable2.getValueAt(row, 1);
        String date = (String) jTable2.getValueAt(row, 2);
        String paidAmount = (String) jTable2.getValueAt(row, 3);

        jLabel2.setText(id);
        jLabel4.setText(accNumber);
        jLabel6.setText(date);
        jLabel8.setText(paidAmount);

    }

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTable3.getSelectedRow();

        String id = (String) jTable3.getValueAt(row, 0);
        String accNumber = (String) jTable3.getValueAt(row, 1);
        String date = (String) jTable3.getValueAt(row, 2);
        String paidAmount = (String) jTable3.getValueAt(row, 3);

        jLabel15.setText(id);
        jLabel14.setText(accNumber);
        jLabel11.setText(date);
        jLabel9.setText(paidAmount);
    }

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTable4.getSelectedRow();

        String id = (String) jTable4.getValueAt(row, 0);
        String accNumber = (String) jTable4.getValueAt(row, 1);
        String date = (String) jTable4.getValueAt(row, 2);
        String paidAmount = (String) jTable4.getValueAt(row, 3);

        jLabel23.setText(id);
        jLabel22.setText(accNumber);
        jLabel19.setText(date);
        jLabel17.setText(paidAmount);
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTable1.getSelectedRow();

        String id = (String) jTable1.getValueAt(row, 0);
        String accNumber = (String) jTable1.getValueAt(row, 1);
        String date = (String) jTable1.getValueAt(row, 2);
        String paidAmount = (String) jTable1.getValueAt(row, 3);

        jLabel40.setText(id);
        jLabel39.setText(accNumber);
        jLabel29.setText(date);
        jLabel17.setText(paidAmount);
    }

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(new Date());

            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = currentTime.format(formatter);

            String accName = jLabel37.getText();
            String accountNumber = "49433028";
            String amount = textField2.getText();
            double totalAmount = Double.valueOf(amount);

            if (amount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Transfer Amount", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                MySQL.executeIUD("INSERT INTO `marketing_account`(`account_number`,`date`,`amount`) VALUES('" + accountNumber + "','" + date + "','" + amount + "')");
                JOptionPane.showMessageDialog(this, "Transaction Successfully Marketing Department", "Warning", JOptionPane.WARNING_MESSAGE);
                total();
                loadMarketing();
                MySQL.executeIUD("INSERT INTO `transaction`(`account_number`,`account_name`,`transfer_balance`,`time`,`date`) VALUES('" + accountNumber + "','" + accName + "','" + amount + "','" + formattedTime + "','" + date + "')");

                if (true) {
                    try {
                        ResultSet resultSet = MySQL.executeSearch("SELECT SUM(total) AS total FROM `total`");
                        while (resultSet.next()) {
                            double sum = resultSet.getDouble("total");
                            double calculate = sum -= totalAmount;

                            MySQL.executeIUD("UPDATE `total` SET `total`='" + calculate + "'");
                            jLabel32.setText("Total: " + calculate);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            String path = "src//reports//BMmarketing.jasper";

            HashMap<String, Object> params1 = new HashMap<>();

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable3.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, params1, dataSource);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(new Date());

            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = currentTime.format(formatter);

            String accName = jLabel38.getText();
            String accountNumber = "36649738";
            String amount = textField3.getText();
            double totalAmount = Double.valueOf(amount);

            if (amount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Transfer Amount", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                MySQL.executeIUD("INSERT INTO `inventory_account`(`account_number`,`date`,`amount`) VALUES('" + accountNumber + "','" + date + "','" + amount + "')");
                JOptionPane.showMessageDialog(this, "Transaction Successfully Inventry Management Department", "Warning", JOptionPane.WARNING_MESSAGE);
                total();
                loadInventry();
                MySQL.executeIUD("INSERT INTO `transaction`(`account_number`,`account_name`,`transfer_balance`,`time`,`date`) VALUES('" + accountNumber + "','" + accName + "','" + amount + "','" + formattedTime + "','" + date + "')");

                if (true) {
                    try {
                        ResultSet resultSet = MySQL.executeSearch("SELECT SUM(total) AS total FROM `total`");
                        while (resultSet.next()) {
                            double sum = resultSet.getDouble("total");
                            double calculate = sum -= totalAmount;

                            MySQL.executeIUD("UPDATE `total` SET `total`='" + calculate + "'");
                            jLabel32.setText("Total: " + calculate);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            String path = "src//reports//BMinventry.jasper";

            HashMap<String, Object> params1 = new HashMap<>();

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable4.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, params1, dataSource);

            JasperViewer.viewReport(jasperPrint, false);
//           BMinventry.jasper
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(new Date());

            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = currentTime.format(formatter);

            String accName = jLabel36.getText();
            String accountNumber = "55507234";
            String amount = textField4.getText();
            double totalAmount = Double.valueOf(amount);

            if (amount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Transfer Amount", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                MySQL.executeIUD("INSERT INTO `budget_loan`(`account_number`,`get_date`,`amount`) VALUES('" + accountNumber + "','" + date + "','" + amount + "')");
                JOptionPane.showMessageDialog(this, "Transaction Successfully Loans Management Department", "Warning", JOptionPane.WARNING_MESSAGE);
                loadLoan();
                MySQL.executeIUD("INSERT INTO `transaction`(`account_number`,`account_name`,`transfer_balance`,`time`,`date`) VALUES('" + accountNumber + "','" + accName + "','" + amount + "','" + formattedTime + "','" + date + "')");

                if (true) {
                    try {
                        ResultSet resultSet = MySQL.executeSearch("SELECT SUM(total) AS total FROM `total`");
                        while (resultSet.next()) {
                            double sum = resultSet.getDouble("total");
                            double calculate = sum -= totalAmount;

                            MySQL.executeIUD("UPDATE `total` SET `total`='" + calculate + "'");
                            jLabel32.setText("Total: " + calculate);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            String path = "src//reports//BMloan.jasper";

            HashMap<String, Object> params1 = new HashMap<>();

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, params1, dataSource);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void jLabel54MouseClicked(java.awt.event.MouseEvent evt) {
        jTable2.clearSelection();
        jTable3.clearSelection();
        jTable4.clearSelection();
        jTable1.clearSelection();
        jLabel2.setText("null");
        jLabel15.setText("null");
        jLabel23.setText("null");
        jLabel40.setText("null");
        jLabel4.setText("null");
        jLabel14.setText("null");
        jLabel22.setText("null");
        jLabel39.setText("null");
        jLabel6.setText("null");
        jLabel11.setText("null");
        jLabel19.setText("null");
        jLabel29.setText("null");
        jLabel8.setText("null");
        jLabel9.setText("null");
        jLabel17.setText("null");
        jLabel26.setText("null");
    }

    // Variables declaration - do not modify                     
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button2;
    private com.nex.cms.components.Button button3;
    private com.nex.cms.components.Button button4;
    private com.nex.cms.components.Button button5;
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
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private com.nex.cms.components.TextField textField1;
    private com.nex.cms.components.TextField textField2;
    private com.nex.cms.components.TextField textField3;
    private com.nex.cms.components.TextField textField4;
    // End of variables declaration                   
}
