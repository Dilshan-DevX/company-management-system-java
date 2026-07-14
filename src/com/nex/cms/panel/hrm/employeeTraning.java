package com.nex.cms.panel.hrm;

import com.nex.cms.panel.cusutomer.*;
import com.nex.cms.connection.MySQL;
import com.nex.cms.model.Validations;
import com.nex.cms.model.tableAlign;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

public class employeeTraning extends javax.swing.JPanel {

  private static HashMap<String, String> LeaveMap = new HashMap<>();

   
    public employeeTraning() {
        initComponents();
//        loadLeave();
//        loadSearchLeave();
        jLabel184.setVisible(false);
        jLabel185.setVisible(false);
        button5.setEnabled(false);
        loadTrainingTable();
//        loadLeaveTable();
    }
     public void loadTrainingTable() {

        try {
            

            
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `training` INNER JOIN `employee` ON `training`.`employee_employee_id`=`employee`.`employee_id` INNER JOIN `department` ON `employee`.`department_department_id`=`department`.`department_id` INNER JOIN `gender` ON `employee`.`gender_id`=`gender`.`id` INNER JOIN `jobs` ON `employee`.`jobs_job_id`=`jobs`.`job_id` WHERE training.` traning_status_id` = '1' ");

            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
            defaultTableModel.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("employee_employee_id"));
                vector.add(resultSet.getString("employee_fname"));
                vector.add(resultSet.getString("employee_lname"));
                vector.add(resultSet.getString("employee_email"));
                vector.add(resultSet.getString("employee_phone"));
//                vector.add(resultSet.getString("gender.type"));
                vector.add(resultSet.getString("jobs.job_title"));
                vector.add(resultSet.getString("training.training_name"));
                vector.add(resultSet.getString("department.department_name"));

                defaultTableModel.addRow(vector);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

   
 




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        button4 = new com.nex.cms.components.Button();
        jLabel7 = new javax.swing.JLabel();
        textField1 = new com.nex.cms.components.TextField();
        jLabel172 = new javax.swing.JLabel();
        jLabel173 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        jLabel175 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        jLabel181 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        button5 = new com.nex.cms.components.Button();
        button7 = new com.nex.cms.components.Button();
        textField2 = new com.nex.cms.components.TextField();
        jLabel184 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        button6 = new com.nex.cms.components.Button();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "Email", "Mobile", "Job Roll", "Traning Type", "Department"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel11.setText("Traning Type");

        jSeparator2.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator2.setMaximumSize(new java.awt.Dimension(32767, 5));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("Traning Management");

        jSeparator1.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator1.setMaximumSize(new java.awt.Dimension(32767, 5));

        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-30.png"))); // NOI18N
        button4.setRippleColor(new java.awt.Color(153, 204, 255));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel7.setText("Employee Reg ID");

        textField1.setForeground(new java.awt.Color(0, 0, 0));
        textField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField1.setShadowColor(new java.awt.Color(0, 153, 204));
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

        jLabel172.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel172.setText("Employee ID                   :");

        jLabel173.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel173.setText("Employee job roll           :");

        jLabel174.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel174.setText("Employee Department :");

        jLabel175.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel175.setText("Employee Department");

        jLabel176.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel176.setText("Employee job roll");

        jLabel177.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel177.setText("Employee ID");

        jLabel178.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel178.setText("Employee Name    :");

        jLabel179.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel179.setText("Employee Email     :");

        jLabel180.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel180.setText("Employee Mobile   :");

        jLabel181.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel181.setText("Employee Mobile");

        jLabel182.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel182.setText("Employee Email");

        jLabel183.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel183.setText("Employee Name");

        jSeparator3.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator3.setMaximumSize(new java.awt.Dimension(32767, 5));

        button5.setBackground(new java.awt.Color(255, 153, 153));
        button5.setText("Mark as Completed");
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        button7.setBackground(new java.awt.Color(153, 204, 255));
        button7.setText("Add to Traning List");
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });

        textField2.setForeground(new java.awt.Color(0, 0, 0));
        textField2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField2.setShadowColor(new java.awt.Color(0, 153, 204));
        textField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField2ActionPerformed(evt);
            }
        });

        jLabel184.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel184.setText("Type");

        jLabel185.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel185.setText("Type :");

        button6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button6.setRippleColor(new java.awt.Color(153, 204, 255));
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(373, 373, 373))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel173)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel176))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel172, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel177))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel174)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel175)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel179, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel180))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel181, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel182, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel178)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel183, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel185)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel184, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel177)
                                .addComponent(jLabel172))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel178)
                                    .addComponent(jLabel183)
                                    .addComponent(jLabel185)
                                    .addComponent(jLabel184))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel179)
                                    .addComponent(jLabel182)
                                    .addComponent(jLabel175)
                                    .addComponent(jLabel174))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel180)
                                    .addComponent(jLabel181)
                                    .addComponent(jLabel176, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel173))))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
         int row = jTable1.getSelectedRow();

        button7.setEnabled(false);
        button5.setEnabled(true);

        String EmpId = String.valueOf(jTable1.getValueAt(row, 0));
        jLabel177.setText(EmpId);

        jLabel184.setVisible(true);
        jLabel185.setVisible(true);

        String fname = String.valueOf(jTable1.getValueAt(row, 1));
        jLabel183.setText(fname);

        String email = String.valueOf(jTable1.getValueAt(row, 3));
        jLabel182.setText(email);

        String mobile = String.valueOf(jTable1.getValueAt(row, 4));
        jLabel181.setText(mobile);

        String department = String.valueOf(jTable1.getValueAt(row, 7));
        jLabel175.setText(department);

        String jobroll = String.valueOf(jTable1.getValueAt(row, 5));
        jLabel176.setText(jobroll);

        String Ttype = String.valueOf(jTable1.getValueAt(row, 6));
        jLabel184.setText(Ttype);

    }//GEN-LAST:event_jTable1MouseClicked

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        reset();
    }//GEN-LAST:event_button4ActionPerformed

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
      
    }//GEN-LAST:event_textField1ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
 try {

            String EmpId = jLabel177.getText();

            if (EmpId.equals("Employee ID")) {
                JOptionPane.showMessageDialog(this, "Pleace Select a Trainer", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else {

                
                String Ttype = jLabel184.getText();
                           

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `training` WHERE `employee_employee_id` = '" + EmpId + "' AND `training`.` traning_status_id` = '2' AND `training_name` = '" + Ttype + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Employee Completed Traning ! ", "Warning", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    MySQL.executeIUD("UPDATE `training` SET `training`.` traning_status_id` = '2' WHERE `employee_employee_id` = '"+EmpId+"' AND `training_name` = '" + Ttype + "'");

                    JOptionPane.showMessageDialog(this, "Mark as a Completed Traniner", "Success", JOptionPane.INFORMATION_MESSAGE);

                    loadTrainingTable();

                    reset();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }//GEN-LAST:event_button5ActionPerformed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        try {

            String empId = jLabel177.getText();

            String empIdtext = textField1.getText();

//             ResultSet resultSet = MySQL.dbSearch("SELECT * FROM `employee`");
//            
//            if (resultSet.next()) {
//                
//            } else {
            String traningType = textField2.getText();

            if (empIdtext.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter youer Employee ID !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (traningType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Youer Traning Type !", "warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `training` WHERE `employee_employee_id` = '" + empIdtext + "' AND `training_name` = '" + traningType + "'");
//          AND `traning_status_id` = '2'
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Employee Allready In Traning ! ", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MySQL.executeIUD("INSERT INTO `training` (`training_name`,`employee_employee_id`,` traning_status_id`)"
                            + "VALUES ('" + traningType + "','" + empIdtext + "','1')");

                    JOptionPane.showMessageDialog(this, "SuccessFully Added", "Success", JOptionPane.INFORMATION_MESSAGE);

                    loadTrainingTable();

                    reset();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button7ActionPerformed

    private void textField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField2ActionPerformed

    private void textField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField1KeyReleased
        try {

            String Empid = textField1.getText();
            String TraningName = textField2.getText();

//            System.out.println(Empid);
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee` INNER JOIN `gender` ON `employee`.`gender_id`=`gender`.`id`  INNER JOIN `jobs` ON `employee`.`jobs_job_id`=`jobs`.`job_id` INNER JOIN `department` ON `employee`.`department_department_id`=`department`.`department_id`  WHERE `employee_id` = '" + Empid + "' ");

            while (resultSet.next()) {

                jLabel177.setText(resultSet.getString("employee_id"));
                jLabel183.setText(resultSet.getString("employee_fname"));
                jLabel175.setText(resultSet.getString("department.department_name"));
                jLabel182.setText(resultSet.getString("employee_email"));
                jLabel181.setText(resultSet.getString("employee_phone"));
                jLabel176.setText(resultSet.getString("jobs.job_title"));

            }

//                if (resultSet.next()) {
//                    JOptionPane.showMessageDialog(this, "This Employee Allready registered! ", "Warning", JOptionPane.WARNING_MESSAGE);
//                } else {
//
//                    Date date = new Date();
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//                    MySQL.dbIUD("INSERT INTO `employee` (`employee_id`,`employee_fname`,`employee_lname`,`employee_email`,`employee_phone`,`hire_date`,`jobs_job_id`,`department_department_id`,`gender_id`)"
//                            + "VALUES ('" + id2 + "','" + fname + "','" + lname + "','" + email + "','" + mobile + "','" + sdf.format(date) + "','" + jobRollMap.get(jobroll) + "','" + departmentMap.get(department) + "','" + genderMap.get(gender) + "')");
//
//                    JOptionPane.showMessageDialog(this, "SuccessFully Registred " + id2 + " ! " , "Success", JOptionPane.INFORMATION_MESSAGE);
////                    loadEmployeeTable();
////                    reset();
//                }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_textField1KeyReleased

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `training` ");

            if (resultSet.next()) {

                String path = "src//reports//EmpTran_Repo.jasper";

                HashMap<String, Object> params = new HashMap<>();
//                params.put("Parameter1", jLabel9.getText());
//                params.put("Parameter2", jLabel13.getText());
//                params.put("Parameter2", jFormattedTextField1.getText());

                JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

                JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, dataSource);

//                JOptionPane.showMessageDialog(this, " SuccessFull !", "Success", JOptionPane.INFORMATION_MESSAGE);
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "SuccessFull !");

                JasperViewer.viewReport(jasperPrint, false);

            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "No Marked employee !");
//                JOptionPane.showMessageDialog(this, "No Rejisterd employee !", "Warning", JOptionPane.WARNING_MESSAGE);

            }
        } catch (Exception e) {
            e.printStackTrace();
//            Home.logger.log(Level.WARNING,"FM_LOGGER_ERROR",e);
        }  
    }//GEN-LAST:event_button6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button4;
    private com.nex.cms.components.Button button5;
    private com.nex.cms.components.Button button6;
    private com.nex.cms.components.Button button7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private com.nex.cms.components.TextField textField1;
    private com.nex.cms.components.TextField textField2;
    // End of variables declaration//GEN-END:variables

    private void reset() {
      
      
        button5.setEnabled(false);
        jLabel177.setText("Employee ID ");
        jLabel183.setText("Employee Name ");
        jLabel175.setText("Employee Department ");
        jLabel182.setText("Employee Email ");
        jLabel181.setText("Employee Mobile ");
        jLabel176.setText("Employee job roll ");
 

        textField1.setText("");
        textField2.setText("");

        button7.setEnabled(true);
        jLabel184.setVisible(false);
        jLabel185.setVisible(false);
    
    }
}
