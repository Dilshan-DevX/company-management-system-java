package com.nex.cms.panel.communication;

import com.nex.cms.connection.MySQL;
import com.nex.cms.model.User;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Internal extends javax.swing.JPanel {

    private File selectedFile;

    public Internal() {
        initComponents();
        textField2.setEnabled(false);
        jLabel7.setText(User.getFirst_name() + " " + User.getLast_name());
        jLabel6.setText(User.getEmail());
        jLabel8.setText(User.getEmployeedep());
    }

    private void selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            textField2.setText(selectedFile.getAbsolutePath());
        }
    }

    private void uploadFile() {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Please select a file first.");
            return;
        }

        String subject = textField1.getText().trim();
        String description = jTextArea1.getText().trim();

        if (subject.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both Subject and Description.");
            return;
        }

        String fileName = selectedFile.getName();
        String employeeId = User.getEmployeeId();
        Timestamp uploadedTime = new Timestamp(System.currentTimeMillis());

        String query = "INSERT INTO internal_com (subject, `desc`, file_name, file_data, employee_employee_id, uploaded_time) VALUES (?, ?, ?, ?, ?, ?)";

        try (
                FileInputStream fis = new FileInputStream(selectedFile); PreparedStatement pst = MySQL.connection.prepareStatement(query)) {
            MySQL.createConnection();

            pst.setString(1, subject);
            pst.setString(2, description);
            pst.setString(3, fileName);
            pst.setBinaryStream(4, fis, (int) selectedFile.length());
            pst.setString(5, employeeId);
            pst.setTimestamp(6, uploadedTime);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "File uploaded successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error uploading file.");
        }

    }

    private void helpDesk() {
        String request = textField3.getText();
        String complain = jTextArea2.getText();
        String suggest = jTextArea3.getText();
        String accusat = jTextArea4.getText();

        if (request.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your request", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (request.length() > 45) {
            JOptionPane.showMessageDialog(this, "Request must have content lower than 45 characters.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (suggest.length() > 250) {
            JOptionPane.showMessageDialog(this, "Suggest must have content lower than 250 characters.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (accusat.length() > 250) {
            JOptionPane.showMessageDialog(this, "Accusat must have content lower than 250 characters.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                MySQL.executeIUD("INSERT INTO `employee_req`(`employee_req`,`complain`,`suggest`,`accusat`,`employee_employee_id`) "
                        + "VALUES ('" + request + "','" + complain + "','" + suggest + "','" + accusat + "','" + User.getEmployeeId() + "')");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void clear() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        jTextArea1.setText("");
        jTextArea2.setText("");
        jTextArea3.setText("");
        jTextArea4.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textField1 = new com.nex.cms.components.TextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        textField2 = new com.nex.cms.components.TextField();
        button1 = new com.nex.cms.components.Button();
        button2 = new com.nex.cms.components.Button();
        button3 = new com.nex.cms.components.Button();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textField3 = new com.nex.cms.components.TextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        button4 = new com.nex.cms.components.Button();
        button5 = new com.nex.cms.components.Button();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("Internal Communication");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel2.setText("Subject");

        textField1.setShadowColor(new java.awt.Color(0, 204, 255));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel3.setText("Description");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jScrollPane1.setViewportView(jTextArea1);

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel4.setText("File Name");

        textField2.setShadowColor(new java.awt.Color(0, 204, 255));

        button1.setText("Select file here");
        button1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button1.setShadowColor(new java.awt.Color(0, 204, 255));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(255, 255, 204));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-save-as-24.png"))); // NOI18N
        button2.setText("  Upload");
        button2.setShadowColor(new java.awt.Color(0, 204, 255));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-30.png"))); // NOI18N
        button3.setShadowColor(new java.awt.Color(0, 204, 255));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-user-30.png"))); // NOI18N

        jLabel6.setText("Email");

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setText("Name");

        jLabel8.setText("Devicion Name");

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setText("Devicion :");

        jSeparator1.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator1.setMaximumSize(new java.awt.Dimension(32767, 5));

        jSeparator2.setForeground(new java.awt.Color(51, 204, 255));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 255));
        jLabel10.setText("Help Desk");

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel11.setText("Employee Requests");

        textField3.setShadowColor(new java.awt.Color(0, 204, 255));

        jLabel12.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel12.setText("Employee Complains");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jScrollPane2.setViewportView(jTextArea2);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jScrollPane3.setViewportView(jTextArea3);

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel13.setText("Employee Suggests");

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel14.setText("Employee Accusats");

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jTextArea4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jScrollPane4.setViewportView(jTextArea4);

        button4.setBackground(new java.awt.Color(255, 255, 204));
        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-save-as-24.png"))); // NOI18N
        button4.setText("  Upload");
        button4.setShadowColor(new java.awt.Color(0, 204, 255));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-30.png"))); // NOI18N
        button5.setShadowColor(new java.awt.Color(0, 204, 255));
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(175, 175, 175))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane3)
                                            .addComponent(textField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jScrollPane2)
                                            .addComponent(jScrollPane4)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(button5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(button4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(27, 27, 27))))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(109, 109, 109))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jSeparator2)
                                .addContainerGap())))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        selectFile();
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        uploadFile();
    }//GEN-LAST:event_button2ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        helpDesk();
    }//GEN-LAST:event_button4ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        clear();
    }//GEN-LAST:event_button5ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        clear();
    }//GEN-LAST:event_button3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button2;
    private com.nex.cms.components.Button button3;
    private com.nex.cms.components.Button button4;
    private com.nex.cms.components.Button button5;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private com.nex.cms.components.TextField textField1;
    private com.nex.cms.components.TextField textField2;
    private com.nex.cms.components.TextField textField3;
    // End of variables declaration//GEN-END:variables
}
