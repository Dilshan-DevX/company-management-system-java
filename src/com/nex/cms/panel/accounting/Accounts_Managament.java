package com.nex.cms.panel.accounting;

import java.sql.ResultSet;
import com.nex.cms.connection.MySQL;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author user
 */
public class Accounts_Managament extends javax.swing.JPanel {

    HashMap<String, String> accTypeMap = new HashMap<>();

    /**
     * Creates new form Accounts_Management
     */
    public Accounts_Managament() {
        initComponents();
        
        jTable1.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(204, 255, 255));
        jTable1.getTableHeader().setForeground(new Color(0, 0, 0));
        jTable1.setRowHeight(30);
        
        loadAccounts();
        loadAccCategory();
        
        button12.setEnabled(false);
        button10.setEnabled(false);
    }

    private void loadAccounts() {
        try {

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `account` INNER JOIN `account_category`ON `account`.`account_category_acc_category_id` = `account_category`.`acc_category_id`");

            while (resultSet.next()) {

                Vector vector = new Vector();
                vector.add(resultSet.getString("account_number"));
                vector.add(resultSet.getString("account_name"));
                vector.add(resultSet.getString("balance"));
                vector.add(resultSet.getString("created_date"));
                vector.add(resultSet.getString("account_category.acc_category_name"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAccCategory() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `account_category`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("acc_category_name"));
                accTypeMap.put(resultSet.getString("acc_category_name"), resultSet.getString("acc_category_id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox6.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        textField7 = new com.nex.cms.components.TextField();
        button10 = new com.nex.cms.components.Button();
        button11 = new com.nex.cms.components.Button();
        button12 = new com.nex.cms.components.Button();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        textField8 = new com.nex.cms.components.TextField();
        button35 = new com.nex.cms.components.Button();
        textField21 = new com.nex.cms.components.TextField();
        jLabel1 = new javax.swing.JLabel();
        button36 = new com.nex.cms.components.Button();
        button1 = new com.nex.cms.components.Button();

        jButton1.setText("jButton1");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel35.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 153, 255));
        jLabel35.setText("Account Management");

        jLabel36.setText("Account Number");

        jLabel37.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel37.setText("Account Category");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));

        jLabel38.setText("Balance RS.");

        button10.setBackground(new java.awt.Color(255, 204, 204));
        button10.setForeground(new java.awt.Color(0, 0, 0));
        button10.setText("Delete Account");
        button10.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button10ActionPerformed(evt);
            }
        });

        button11.setBackground(new java.awt.Color(153, 255, 153));
        button11.setForeground(new java.awt.Color(0, 0, 0));
        button11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-add-16.png"))); // NOI18N
        button11.setText("  Add Account");
        button11.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11ActionPerformed(evt);
            }
        });

        button12.setBackground(new java.awt.Color(204, 255, 255));
        button12.setForeground(new java.awt.Color(0, 0, 0));
        button12.setText("Edit Account");
        button12.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button12ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Account Number", "Account Name", "Balance Rs.", "Created Date", "Account Type"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(jTable1);

        jLabel40.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel40.setText("Account Name");

        textField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField8ActionPerformed(evt);
            }
        });

        button35.setForeground(new java.awt.Color(0, 0, 0));
        button35.setText("View Transaction");
        button35.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button35ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel1.setText("Created Date");

        button36.setBackground(new java.awt.Color(204, 204, 255));
        button36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button36ActionPerformed(evt);
            }
        });

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-30.png"))); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textField7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textField8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textField21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(618, 618, 618)
                        .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(422, 422, 422)
                        .addComponent(button35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(button11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addGap(41, 41, 41)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(textField8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(textField21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(button35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button10ActionPerformed

        int row = jTable1.getSelectedRow();

        try {
            String id = (String) jTable1.getValueAt(row, 0);

            MySQL.executeIUD("DELETE FROM `account` WHERE `account_number`='" + id + "'");
            loadAccounts();
            reset();
            JOptionPane.showMessageDialog(this, "Accoun Delete Success", "Warning", JOptionPane.WARNING_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button10ActionPerformed

    private void button11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11ActionPerformed
        String accNumber = textField8.getText();
        String accCategory = (String) jComboBox6.getSelectedItem();
        String balance = textField7.getText();
        String accName = textField21.getText();

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(new Date());

            if (accNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Account Number", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (jComboBox6.getSelectedItem() == "Select") {
                JOptionPane.showMessageDialog(this, "Please Select A Account Category", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (balance.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Account Balance", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (date == null) {
                JOptionPane.showMessageDialog(this, "Please Enter Created Date", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (accName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Account Name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `account` WHERE `account_number` = '" + accNumber + "'");
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Is a Account Already exists", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    MySQL.executeIUD("INSERT INTO `account`(`account_number`,`account_name`,`balance`,`created_date`,`account_category_acc_category_id`) VALUES('" + accNumber + "','" + accName + "','" + balance + "','" + date + "','" + accTypeMap.get(accCategory) + "')");
                    loadAccounts();
                    reset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button11ActionPerformed

    private void button12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button12ActionPerformed
        int row = jTable1.getSelectedRow();
        String accNumber = textField8.getText();
        String accCategory = (String) jComboBox6.getSelectedItem();
        String balance = textField7.getText();
        String accName = textField21.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
        try {

            if (accNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Account Number", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (jComboBox6.getSelectedItem() == "Select") {
                JOptionPane.showMessageDialog(this, "Please Select A Account Category", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (balance.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Account Balance", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (accName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Account Name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `account` WHERE `account_number` = '" + accNumber + "' AND `account_name` = '" + accName + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Account Already exists", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    MySQL.executeIUD("UPDATE `account` SET `account_name` = '" + accName + "',`balance` = '" + balance + "',`created_date` = '" + date + "',`account_category_acc_category_id`='" + accTypeMap.get(accCategory) + "' WHERE `account_number`='" + accNumber + "'");
                    JOptionPane.showMessageDialog(this, "This Account Edit Success", "Warning", JOptionPane.WARNING_MESSAGE);
                    loadAccounts();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button12ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();

        button11.setEnabled(false);

        button12.setEnabled(true);
        button10.setEnabled(true);

        String accNumber = String.valueOf(jTable1.getValueAt(row, 0));
        textField8.setText(accNumber);

        String accName = String.valueOf(jTable1.getValueAt(row, 1));
        textField21.setText(accName);

        String accCategory = String.valueOf(jTable1.getValueAt(row, 4));
        jComboBox6.setSelectedItem(accCategory);

        String balance = String.valueOf(jTable1.getValueAt(row, 2));
        textField7.setText(balance);

        String createDate = String.valueOf(jTable1.getValueAt(row, 3));
        jLabel1.setText(createDate);

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyPressed

    private void textField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField8ActionPerformed

    private void button35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button35ActionPerformed

        //        view transaction?
    }//GEN-LAST:event_button35ActionPerformed

    private void button36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button36ActionPerformed
        try {
            String path = "src//reports//AccountManagement.jasper";

            HashMap<String, Object> params1 = new HashMap<>();

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, params1, dataSource);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button36ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
      reset();
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button10;
    private com.nex.cms.components.Button button11;
    private com.nex.cms.components.Button button12;
    private com.nex.cms.components.Button button35;
    private com.nex.cms.components.Button button36;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private com.nex.cms.components.TextField textField21;
    private com.nex.cms.components.TextField textField7;
    private com.nex.cms.components.TextField textField8;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        textField8.setText("");
        jComboBox6.setSelectedItem("Select");
        textField7.setText("");
        jLabel1.setText("Created Date");
        textField21.setText("");
        button12.setEnabled(false);
        button10.setEnabled(false);
        button11.setEnabled(true);
        jTable1.clearSelection();
    }

}
