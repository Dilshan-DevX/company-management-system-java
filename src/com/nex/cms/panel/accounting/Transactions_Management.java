package com.nex.cms.panel.accounting;

import com.nex.cms.connection.MySQL;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author User
 */
public class Transactions_Management extends javax.swing.JPanel {

    public Transactions_Management() {
        initComponents();
        loadTransaction();
        jTextArea1.setEnabled(false);
        button2.setEnabled(false);

    }

    private void total() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT SUM(amount) AS total_value FROM hr");
            if (resultSet.next()) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTransaction() {
        try {

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `transaction`");

            while (resultSet.next()) {

                Vector vector = new Vector();
                vector.add(resultSet.getString("transaction_id"));
                vector.add(resultSet.getString("account_number"));
                vector.add(resultSet.getString("account_name"));
                vector.add(resultSet.getString("transfer_balance"));
                vector.add(resultSet.getString("date"));
                vector.add(resultSet.getString("time"));
                vector.add(resultSet.getString("description"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel27 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        textField1 = new com.nex.cms.components.TextField();
        button1 = new com.nex.cms.components.Button();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        button2 = new com.nex.cms.components.Button();
        button36 = new com.nex.cms.components.Button();
        button3 = new com.nex.cms.components.Button();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 153, 255));
        jLabel27.setText("Transaction page");

        jLabel41.setText("Transaction Description");

        jLabel49.setBackground(new java.awt.Color(226, 73, 73));
        jLabel49.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel49.setText("null");

        jLabel37.setText("Filter Transaction");

        jLabel50.setBackground(new java.awt.Color(226, 73, 73));
        jLabel50.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel50.setText("null");

        jLabel39.setText("Account Balance");

        jLabel52.setBackground(new java.awt.Color(226, 73, 73));
        jLabel52.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 0, 0));
        jLabel52.setText("null");

        jLabel40.setText("Transfer Date");

        jLabel53.setBackground(new java.awt.Color(226, 73, 73));
        jLabel53.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel53.setText("null");

        jLabel54.setBackground(new java.awt.Color(226, 73, 73));
        jLabel54.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel54.setText("null");

        jLabel42.setText("Time");

        jLabel43.setText("Account Name");

        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });
        textField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField1KeyReleased(evt);
            }
        });

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-search-30 (1).png"))); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        button1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                button1KeyReleased(evt);
            }
        });

        jLabel44.setText("Transaction ID");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Transaction ID", "Account Number", "Account Name", "Transfer Balance", "Transfer Date", "Time", "Description"
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
        jScrollPane2.setViewportView(jTable1);

        button2.setBackground(new java.awt.Color(204, 255, 255));
        button2.setForeground(new java.awt.Color(0, 0, 0));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-add-16.png"))); // NOI18N
        button2.setText("  Add Description");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button36.setBackground(new java.awt.Color(204, 204, 255));
        button36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button36ActionPerformed(evt);
            }
        });

        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-30.png"))); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37)
                        .addGap(18, 18, 18)
                        .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(button2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(608, 608, 608)
                                .addComponent(button36, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        try {
            String transaction_id = jLabel49.getText();
            String description = jTextArea1.getText();
            MySQL.executeIUD("UPDATE `transaction` SET `description`= '" + description + "' WHERE `transaction_id` = '" + transaction_id + "'");
            loadTransaction();
            JOptionPane.showMessageDialog(this, "Transaction Description Added Successfully", "Warning", JOptionPane.WARNING_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void button36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button36ActionPerformed
        try {
            String path = "src//reports//TransactionManagement.jasper";

            HashMap<String, Object> params1 = new HashMap<>();

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, params1, dataSource);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button36ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        String search = textField1.getText();

        try {
            if (search.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Account Number", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

            }
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `transaction` WHERE `transaction_id`='" + search + "'");

            while (resultSet.next()) {

                Vector vector = new Vector();
                vector.add(resultSet.getString("transaction_id"));
                vector.add(resultSet.getString("account_number"));
                vector.add(resultSet.getString("account_name"));
                vector.add(resultSet.getString("transfer_balance"));
                vector.add(resultSet.getString("date"));
                vector.add(resultSet.getString("time"));
                vector.add(resultSet.getString("description"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_button1ActionPerformed

    private void button1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_button1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_button1KeyReleased

    private void textField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField1KeyReleased

    }//GEN-LAST:event_textField1KeyReleased

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        
        int row = jTable1.getSelectedRow();

        jTextArea1.setEnabled(true);
        button2.setEnabled(true);

        String transaction_id = (String) jTable1.getValueAt(row, 0);
        jLabel49.setText(transaction_id);

        String account_number = (String) jTable1.getValueAt(row, 1);
        String account_name = (String) jTable1.getValueAt(row, 2);
        jLabel50.setText(account_name);

        String transfer_balance = (String) jTable1.getValueAt(row, 3);
        jLabel52.setText(transfer_balance);

        String transfer_date = (String) jTable1.getValueAt(row, 4);
        jLabel53.setText(transfer_date);

        String time = (String) jTable1.getValueAt(row, 5);
        jLabel54.setText(time);

        String description = (String) jTable1.getValueAt(row, 6);
        jTextArea1.setText(description);
        
    }//GEN-LAST:event_button3ActionPerformed

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button2;
    private com.nex.cms.components.Button button3;
    private com.nex.cms.components.Button button36;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private com.nex.cms.components.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
