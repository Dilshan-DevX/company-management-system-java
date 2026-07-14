
package com.nex.cms.panel.hrm;

import com.nex.cms.connection.MySQL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import raven.toast.Notifications;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author User
 */
public class attendance extends javax.swing.JPanel {

    /**
     * Creates new form attendance
     */
    public attendance() {
        initComponents();
        loadEmployeeTable("employee_fname", "ASC");
    }

    public void loadEmployeeTable(String column, String orderby) {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee` INNER JOIN `attendance` ON `employee`.`employee_id`=`attendance`.`employee_employee_id` INNER JOIN `att_status` ON `attendance`.`att_status_id`=`att_status`.`id`  INNER JOIN `jobs` ON `employee`.`jobs_job_id`=`jobs`.`job_id` INNER JOIN `department` ON `employee`.`department_department_id`=`department`.`department_id` WHERE employee.employee_id NOT IN (SELECT DISTINCT leave.employee_employee_id FROM `leave` WHERE leave.leave_type_id = 5) ORDER BY  `" + column + "`  " + orderby + " ");

            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable6.getModel();
            defaultTableModel.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("employee_id"));
                vector.add(resultSet.getString("employee_fname"));
                vector.add(resultSet.getString("employee_lname"));
                vector.add(resultSet.getString("employee_email")); 
                vector.add(resultSet.getString("jobs.job_title"));
                vector.add(resultSet.getString("department.department_name"));
                vector.add(resultSet.getString("attendance.date"));
                vector.add(resultSet.getString("att_status.status"));

                defaultTableModel.addRow(vector);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel71 = new javax.swing.JLabel();
        textField5 = new com.nex.cms.components.TextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        button8 = new com.nex.cms.components.Button();
        button9 = new com.nex.cms.components.Button();
        button10 = new com.nex.cms.components.Button();
        button6 = new com.nex.cms.components.Button();

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel69.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel69.setText("Attendance Marking");

        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-setting-48.png"))); // NOI18N

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        jLabel71.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel71.setText("Employee Reg ID");

        textField5.setShadowColor(new java.awt.Color(0, 51, 204));
        textField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField5textField1ActionPerformed(evt);
            }
        });
        textField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField5textField1KeyReleased(evt);
            }
        });

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "First Name", "Last Name", "Email", "job roll", "Department", "Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable6);

        jLabel72.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel72.setText("Employee");

        jLabel73.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel73.setText("Search By");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name ASC", "Name DESC", "ID ASC", "ID DESC", "Date ASC", "Date DESC", "Status ASC", "Status DESC" }));
        jComboBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox6jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6jComboBox2ActionPerformed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel74.setText("Employee ID                   :");

        jLabel75.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel75.setText("Employee Name            :");

        jLabel76.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel76.setText("Employee Email    :");

        jLabel77.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel77.setText("Employee Department :");

        jLabel78.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel78.setText("Employee Mobile  :");

        jLabel79.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel79.setText("Employee ID");

        jLabel80.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel80.setText("Employee Name");

        jLabel81.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel81.setText("Employee Email");

        jLabel82.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel82.setText("Employee Mobile");

        jLabel83.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel83.setText("Employee Department");

        jLabel84.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel84.setText("Employee Job roll");

        jLabel85.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel85.setText("Employee Job roll  :");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(79, 79, 79)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel82, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel74, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel76)
                        .addComponent(jLabel79)
                        .addComponent(jLabel81)))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(jLabel78)
                    .addComponent(jLabel80)
                    .addComponent(jLabel82))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(jLabel83)
                    .addComponent(jLabel85)
                    .addComponent(jLabel84))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        button8.setBackground(new java.awt.Color(153, 204, 255));
        button8.setText("Search");
        button8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button8ActionPerformed(evt);
            }
        });

        button9.setBackground(new java.awt.Color(255, 153, 153));
        button9.setText("Absent");
        button9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button9ActionPerformed(evt);
            }
        });

        button10.setBackground(new java.awt.Color(153, 204, 255));
        button10.setText("Present");
        button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button10ActionPerformed(evt);
            }
        });

        button6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button6.setRippleColor(new java.awt.Color(153, 204, 255));
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button10, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(347, 347, 347)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel70))
                    .addComponent(jSeparator5))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel71))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textField5textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField5textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField5textField1ActionPerformed

    private void textField5textField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField5textField1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField5textField1KeyReleased

    private void jComboBox6jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox6jComboBox2ItemStateChanged
        seacrch();
    }//GEN-LAST:event_jComboBox6jComboBox2ItemStateChanged

    private void jComboBox6jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6jComboBox2ActionPerformed

    private void button8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button8ActionPerformed
          try {

            String Empid = textField5.getText();

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee` INNER JOIN `gender` ON `employee`.`gender_id`=`gender`.`id`  INNER JOIN `jobs` ON `employee`.`jobs_job_id`=`jobs`.`job_id` INNER JOIN `department` ON `employee`.`department_department_id`=`department`.`department_id` WHERE employee.employee_id NOT IN (SELECT DISTINCT leave.employee_employee_id FROM `leave` WHERE leave.leave_type_id = 5) AND `employee`.`employee_id` = '" + Empid + "'");

             if (!resultSet.isBeforeFirst()) {
               JOptionPane.showMessageDialog(this, "This Employee Not At Job! ", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                while (resultSet.next()) {
                    
                jLabel79.setText(resultSet.getString("employee_id"));
                jLabel80.setText(resultSet.getString("employee_fname"));
                jLabel81.setText(resultSet.getString("employee_email"));
                jLabel82.setText(resultSet.getString("employee_phone"));
                jLabel83.setText(resultSet.getString("department.department_name"));
                jLabel84.setText(resultSet.getString("jobs.job_title"));
                
                }
            }
//            while (resultSet.next()) {

//            }

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
    }//GEN-LAST:event_button8ActionPerformed

    private void button9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button9ActionPerformed
        try {

            String empId = jLabel79.getText();
            String empIdvf = textField5.getText();
            
            // Get the current date
            Date currentDate = new Date();
            SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sqlDateFormat.format(currentDate);
            

            if (empIdvf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter  Employee ID !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (empId.equals("Employee ID")) {
                JOptionPane.showMessageDialog(this, "Please Enter Employee ID !", "warning", JOptionPane.WARNING_MESSAGE);
            } else {
                
                  ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee` INNER JOIN `gender` ON `employee`.`gender_id`=`gender`.`id`  INNER JOIN `jobs` ON `employee`.`jobs_job_id`=`jobs`.`job_id` INNER JOIN `department` ON `employee`.`department_department_id`=`department`.`department_id` WHERE employee.employee_id NOT IN (SELECT DISTINCT leave.employee_employee_id FROM `leave` WHERE leave.leave_type_id = 5) AND `employee`.`employee_id` = '" + empIdvf + "'");

             if (!resultSet.isBeforeFirst()) {
               JOptionPane.showMessageDialog(this, "This Employee Not At Job! ", "Warning", JOptionPane.WARNING_MESSAGE);
             }else{
                 
               ResultSet resultSet2 = MySQL.executeSearch("SELECT * FROM `attendance` WHERE `employee_employee_id` = '" + empIdvf + "' AND `att_status_id` = '1' AND `date` = '"+formattedDate+"'");
       
                if (resultSet2.next()) {
                    JOptionPane.showMessageDialog(this, "This Employee Allready Marked ! ", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    
     ///////////////////////////////////
                 ResultSet resultSet3 = MySQL.executeSearch("SELECT * FROM `attendance` WHERE `employee_employee_id` = '" + empIdvf + "' AND `att_status_id` = '2'  AND `date` = '"+formattedDate+"'");
       
                if (resultSet3.next()) {
                    JOptionPane.showMessageDialog(this, "This Employee Allready Marked ! ", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
     //////////////////////////////

                    MySQL.executeIUD("INSERT INTO `attendance` (`date`,`att_status_id`,`employee_employee_id`) VALUES ('" + formattedDate + "','2','"+ empIdvf+ "')");

                    JOptionPane.showMessageDialog(this, "Attendance Marked SuccessFully ", "Success", JOptionPane.INFORMATION_MESSAGE);

                    loadEmployeeTable("employee_fname", "ASC");

                    reset();
                }
                }
            }
                
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button9ActionPerformed

    private void button10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button10ActionPerformed
         try {

            String empId = jLabel79.getText();
            String empIdvf = textField5.getText();
            
            // Get the current date
            Date currentDate = new Date();
            SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sqlDateFormat.format(currentDate);
            

            if (empIdvf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter  Employee ID !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (empId.equals("Employee ID")) {
                JOptionPane.showMessageDialog(this, "Please Enter Employee ID !", "warning", JOptionPane.WARNING_MESSAGE);
            } else {
                
                  ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee` INNER JOIN `gender` ON `employee`.`gender_id`=`gender`.`id`  INNER JOIN `jobs` ON `employee`.`jobs_job_id`=`jobs`.`job_id` INNER JOIN `department` ON `employee`.`department_department_id`=`department`.`department_id` WHERE employee.employee_id NOT IN (SELECT DISTINCT leave.employee_employee_id FROM `leave` WHERE leave.leave_type_id = 1) AND `employee`.`employee_id` = '" + empIdvf + "'");

             if (!resultSet.isBeforeFirst()) {
               JOptionPane.showMessageDialog(this, "This Employee Not At Job! ", "Warning", JOptionPane.WARNING_MESSAGE);
             }else{
                 
               ResultSet resultSet2 = MySQL.executeSearch("SELECT * FROM `attendance` WHERE `employee_employee_id` = '" + empIdvf + "' AND `att_status_id` = '1' AND `date` = '"+formattedDate+"'");
       
                if (resultSet2.next()) {
                    JOptionPane.showMessageDialog(this, "This Employee Allready Marked ! ", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    
     ///////////////////////////////////
                 ResultSet resultSet3 = MySQL.executeSearch("SELECT * FROM `attendance` WHERE `employee_employee_id` = '" + empIdvf + "' AND `att_status_id` = '2'  AND `date` = '"+formattedDate+"'");
       
                if (resultSet3.next()) {
                    JOptionPane.showMessageDialog(this, "This Employee Allready Marked ! ", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
     //////////////////////////////

                    MySQL.executeIUD("INSERT INTO `attendance` (`date`,`att_status_id`,`employee_employee_id`) VALUES ('" + formattedDate + "','1','"+ empIdvf+ "')");

                    JOptionPane.showMessageDialog(this, "Attendance Marked SuccessFully ", "Success", JOptionPane.INFORMATION_MESSAGE);

                    loadEmployeeTable("employee_fname", "ASC");

                    reset();
                }
                }
            }
                
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button10ActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `attendance` ");

            if (resultSet.next()) {

                String path = "src//reports//EmpAtt_Repot.jasper";

                HashMap<String, Object> params = new HashMap<>();
//                params.put("Parameter1", jLabel9.getText());
//                params.put("Parameter2", jLabel13.getText());
//                params.put("Parameter2", jFormattedTextField1.getText());

                JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable6.getModel());

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
    private com.nex.cms.components.Button button10;
    private com.nex.cms.components.Button button6;
    private com.nex.cms.components.Button button8;
    private com.nex.cms.components.Button button9;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable6;
    private com.nex.cms.components.TextField textField5;
    // End of variables declaration//GEN-END:variables

    private void seacrch() {
          int sort = jComboBox6.getSelectedIndex();

        if (sort == 0) {
            loadEmployeeTable("employee_fname", "ASC");
        } else if (sort == 1) {
            loadEmployeeTable("employee_fname", "DESC");
        } else if (sort == 2) {
            loadEmployeeTable("employee_id", "ASC");
        } else if (sort == 3) {
            loadEmployeeTable("employee_id", "DESC");
        } else if (sort == 4) {
            loadEmployeeTable("date", "ASC");
        } else if (sort == 5) {
            loadEmployeeTable("date", "DESC");
        } else if (sort == 6) {
            loadEmployeeTable("status", "ASC");
        } else if (sort == 7) {
            loadEmployeeTable("status", "DESC");
        }
        
    }

    private void reset() {
        textField5.setText("");
        jComboBox6.setSelectedIndex(0);
         
        jLabel79.setText("Employee ID ");
        jLabel80.setText("Employee Name ");
        jLabel83.setText("Employee Department ");
        jLabel81.setText("Employee Email ");
        jLabel82.setText("Employee Mobile ");
        jLabel84.setText("Employee job roll ");
    }


}
