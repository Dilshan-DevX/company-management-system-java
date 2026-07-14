package com.nex.cms.panel.hrm;

import com.nex.cms.connection.MySQL;
import java.sql.ResultSet;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

public class employee extends javax.swing.JPanel {

    private static HashMap<String, String> genderMap = new HashMap<>();
    private static HashMap<String, String> jobRollMap = new HashMap<>();
    private static HashMap<String, String> departmentMap = new HashMap<>();
    private static HashMap<String, String> CityMap = new HashMap<>();
    
    public employee() {
        initComponents();
        
        jLabel15.setVisible(false);
        jLabel16.setVisible(false);
        button2.setEnabled(false);
        
        loadGender();
        loadJobRoll();
        loadDepartment();
        loadCity();
        loadEmployeeTable();
        
    }
    
    private void loadGender() {

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `gender` ");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {
                vector.add(rs.getString("type"));
                genderMap.put(rs.getString("type"), rs.getString("id"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private void loadJobRoll() {

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `jobs` ");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {
                vector.add(rs.getString("job_title"));
                jobRollMap.put(rs.getString("job_title"), rs.getString("job_id"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(dcm);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private void loadDepartment() {

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `department` ");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {
                vector.add(rs.getString("department_name"));
                departmentMap.put(rs.getString("department_name"), rs.getString("department_id"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);
            jComboBox4.setModel(dcm);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private void loadCity() {

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `city` ");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {
                vector.add(rs.getString("name"));
                CityMap.put(rs.getString("name"), rs.getString("id"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(dcm);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    //load tables
    
    public void loadEmployeeTable() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee` INNER JOIN `gender` ON `employee`.`gender_id`=`gender`.`id`  INNER JOIN `jobs` ON `employee`.`jobs_job_id`=`jobs`.`job_id` INNER JOIN `department` ON `employee`.`department_department_id`=`department`.`department_id`  ");

            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
            defaultTableModel.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("employee_id"));
                vector.add(resultSet.getString("employee_fname"));
                vector.add(resultSet.getString("employee_lname"));
                vector.add(resultSet.getString("employee_email"));
                vector.add(resultSet.getString("employee_phone"));
                vector.add(resultSet.getString("gender.type"));
                vector.add(resultSet.getString("jobs.job_title"));
                vector.add(resultSet.getString("department.department_name"));

                defaultTableModel.addRow(vector);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     private void RegMail(String EmpId, String UserName, String Password, String lname, String fname, String email) {

        String Id = EmpId;
        String un = UserName;
        String pw = Password;
        String fn = fname;
        String ln = lname;

        // Sender's email credentials
        final String senderEmail = "tharindu2003wxyz@gmail.com";  // Change to your email
        final String senderPassword = "klzqgvygjbhnpnbw";  // Use an App Password if using Gmail   bqbi lezg ynks wlgv

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Welcome! Your Registration is Complete");

            String htmlBody = "<html><head><style>"
                    + "body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;}"
                    + ".container { max-width: 600px; background: #ffffff; margin: 20px auto; padding: 20px; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); }"
                    + "h2 { color: #333;}"
                    + ".info { font-size: 16px; color: #555; margin-top: 10px;}"
                    + ".info1 {text-align: center; font-size: 14px; color: #555; margin-top: 10px;}"
                    + ".highlight { color: #4CAF50; font-weight: bold; }"
                    + ".footer { text-align: center; color: #ff4d4d; margin-top: 20px; font-weight: bold; font-size: 16px; }"
                    + "</style></head><body>"
                    + "<div class='container'>"
                    + "<h1>Dear " + fname + " " + lname + ",</h1>"
                    + "<h2>Thank you for registering with Us ! Your account has been successfully created ,</h2>"
                    + "<p class='info'>your profile Details :</p>"
                    + "<table style='width: 100%; border-collapse: collapse; margin: 20px auto; display: table;'>"
                    + "<tr><td><strong>Name:</strong></td><td class='highlight'> " + fname + " " + lname + "</td></tr>"
                    + "<tr><td><strong>EMP ID :</strong></td><td class='highlight'>" + EmpId + "</td></tr>"
                    + "<tr><td><strong>User Name :</strong></td><td class='highlight'>" + un + "</td></tr>"
                    + "<tr><td><strong>Password :</strong></td><td class='highlight'>" + pw + "</td></tr>"
                    + "</table>"
                    + "<p class='info'>To access your account,</p>"
                    + "<p class='info'>If you did not register for this account, please contact our support team immediately.</p>"
                    + "<p class='info1'>448, Purahala Place, Stage 2, Anuradhapura, Sri Lanka<br/>Tel :- 071 902 8888, 071 902 8869<br/>Email :- megamartz@gmail.com</p>"
                    + "<p class='footer'>THANK YOU !</p>"
                    + "<p style='text-align: center; color: #666;'>Software By: RaavanaSoft Software solution </p>"
                    + "</div>"
                    + "</body></html>";

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(htmlBody, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);

            message.setContent(multipart);

            Transport.send(message);
//                        JOptionPane.showMessageDialog(null, "✅ Registration Confirmation Email Sent Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Registration Confirmation Email Sent Successfully!");

        } catch (MessagingException e) {
//                        JOptionPane.showMessageDialog(null, "Email Sending Failed! " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Email Sending Failed!");
            e.printStackTrace();
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textField1 = new com.nex.cms.components.TextField();
        textField3 = new com.nex.cms.components.TextField();
        button1 = new com.nex.cms.components.Button();
        button2 = new com.nex.cms.components.Button();
        textField4 = new com.nex.cms.components.TextField();
        textField5 = new com.nex.cms.components.TextField();
        textField6 = new com.nex.cms.components.TextField();
        textField7 = new com.nex.cms.components.TextField();
        jLabel9 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        button3 = new com.nex.cms.components.Button();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        textField9 = new com.nex.cms.components.TextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        textField8 = new com.nex.cms.components.TextField();
        jSeparator4 = new javax.swing.JSeparator();
        button4 = new com.nex.cms.components.Button();

        setPreferredSize(new java.awt.Dimension(717, 465));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(717, 465));

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setText("Employee Registration");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-setting-48.png"))); // NOI18N

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setText("First Name");

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel4.setText("Last Name");

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setText("Email");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setText("Line 2");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "First Name", "Last Name", "Email", "Mobile", "Gender", "job roll", "Department"
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

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel10.setText("Line 1");

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel11.setText("City");

        textField1.setShadowColor(new java.awt.Color(0, 51, 204));

        textField3.setShadowColor(new java.awt.Color(0, 51, 204));

        button1.setBackground(new java.awt.Color(153, 204, 255));
        button1.setText("Add");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(153, 204, 255));
        button2.setText("Update");
        button2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        textField4.setShadowColor(new java.awt.Color(0, 51, 204));

        textField5.setShadowColor(new java.awt.Color(0, 51, 204));

        textField6.setShadowColor(new java.awt.Color(0, 51, 204));

        textField7.setShadowColor(new java.awt.Color(0, 51, 204));

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setText("Mobile");

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel12.setText("Gender");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel13.setText("Job roll");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel14.setText("Department");

        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-48.png"))); // NOI18N
        button3.setIconTextGap(10);
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel15.setText("Employee ID");

        jLabel16.setFont(new java.awt.Font("Poppins", 1, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 51, 51));
        jLabel16.setText("Emplyee ID HERE");

        textField9.setShadowColor(new java.awt.Color(0, 51, 204));

        jLabel18.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel18.setText("Password");

        jLabel17.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel17.setText("UserName");

        textField8.setShadowColor(new java.awt.Color(0, 51, 204));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button4.setText("Print");
        button4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(301, 301, 301)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(140, 140, 140))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(124, 124, 124))
                                            .addComponent(textField6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel17)
                                            .addComponent(textField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(38, 38, 38))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(43, 43, 43)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textField8, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(33, 33, 33)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel18)
                                            .addComponent(textField9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(1, 1, 1)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(1, 1, 1)
                                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(textField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1098, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
     
        // User details Updates
        
        try {
            
            String empId = jLabel16.getText();

            String fname = textField1.getText();
            String lname = textField6.getText();
            String mobile = textField7.getText();
            String email = textField3.getText();
            String gender = String.valueOf(jComboBox2.getSelectedItem());
            String jobRoll = String.valueOf(jComboBox3.getSelectedItem());
            String department = String.valueOf(jComboBox4.getSelectedItem());
            
            String line1 = textField4.getText();
            String line2 = textField5.getText();
            String city = String.valueOf(jComboBox1.getSelectedItem());
            
           

            if(fname.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Please enter Your first Name !", "Warning", JOptionPane.WARNING_MESSAGE);

            } else if (lname.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Please enter Your Last Name !", "Warning", JOptionPane.WARNING_MESSAGE);
                
            }else if (email.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Please enter Your Email !", "Warning", JOptionPane.WARNING_MESSAGE);

            } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {

                JOptionPane.showMessageDialog(this, "Invalid Email !", "Warning", JOptionPane.WARNING_MESSAGE);
                
            } else if (mobile.isEmpty()) {
                
                JOptionPane.showMessageDialog(this, "Please enter youer mobile number !", "Warning", JOptionPane.WARNING_MESSAGE);
                
            } else if (!mobile.matches("^[0]{1}[7]{1}[01245678]{1}[0-9]{7}$")) {
                
                JOptionPane.showMessageDialog(this, "Invalid Mobile Number !", "Warning", JOptionPane.WARNING_MESSAGE);  
                
            } else if (gender.equals("Select")) {

                JOptionPane.showMessageDialog(this, "Please Select a Gender ! ", "Warning", JOptionPane.WARNING_MESSAGE);
                
            } else if (jobRoll.equals("Select")) {

                JOptionPane.showMessageDialog(this, "Please Select a Job Roll ! ", "Warning", JOptionPane.WARNING_MESSAGE);

            } else if (department.equals("Select")) {

                JOptionPane.showMessageDialog(this, "Please Select a Department ! ", "Warning", JOptionPane.WARNING_MESSAGE);
             
            } else if (line1.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Please enter Your Address Line 1 !", "Warning", JOptionPane.WARNING_MESSAGE);

            } else if (line2.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Please enter Your  Address Line 2 !", "Warning", JOptionPane.WARNING_MESSAGE);

            } else if (city.equals("Select")) {

                JOptionPane.showMessageDialog(this, "Please Select a City ! ", "Warning", JOptionPane.WARNING_MESSAGE);

            } else {

                MySQL.executeIUD("UPDATE `employee` SET `employee_fname` = '" + fname + "', `employee_lname` = '" + lname + "',`employee_email` = '"+email+"',`employee_phone` = '"+mobile+"', `jobs_job_id` = '" + jobRollMap.get(jobRoll) + "', `department_department_id` = '" + departmentMap.get(department) + "', `gender_id` = '" + genderMap.get(gender) + "'"
                        + "WHERE `employee_id` = '" + empId + "'");
                
               ResultSet resultSet =  MySQL.executeSearch("SELECT * FROM `employee_adrs` WHERE `employee_employee_id` = '"+empId+"' ");
                
                if (resultSet.next()) {
    
                     MySQL.executeIUD("UPDATE `employee_adrs` SET  `line1` = '" + line1 + "', `line2` = '" + line2 + "', `city_id` = '" + CityMap.get(city) + "'"
                     + "WHERE `employee_employee_id` = '" + empId + "'");
                     
                } else {
                     MySQL.executeIUD("INSERT INTO `employee_adrs` (`line1`,`line2`,`city_id`,`employee_employee_id`) VALUES ('" + line1 + "','" + line2 + "','" + CityMap.get(city) + "','"+empId+"')");
                }
                
               

                JOptionPane.showMessageDialog(this, "Successfully Updated  ", "Success", JOptionPane.INFORMATION_MESSAGE);

                loadEmployeeTable();
                reset();
                
             

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
//        // Address Updates
//        
//         try {
//
//           
//            String line1 = jTextField6.getText();
//            String line2 = jTextField7.getText();
//            
//            String gender = String.valueOf(jComboBox4.getSelectedItem());
//
//            if (email.isEmpty()) {
//
//                JOptionPane.showMessageDialog(this, "Please enter Your Email !", "Warning", JOptionPane.WARNING_MESSAGE);
//
//            } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
//
//                JOptionPane.showMessageDialog(this, "Invalid Email !", "Warning", JOptionPane.WARNING_MESSAGE);
//            }else if (fname.isEmpty()) {
//
//                JOptionPane.showMessageDialog(this, "Please enter Your first Name !", "Warning", JOptionPane.WARNING_MESSAGE);
//
//            } else if (lname.isEmpty()) {
//
//                JOptionPane.showMessageDialog(this, "Please enter Your Last Name !", "Warning", JOptionPane.WARNING_MESSAGE);
//
//            } else if (line1.isEmpty()) {
//
//                JOptionPane.showMessageDialog(this, "Please enter Your Address Line 1 !", "Warning", JOptionPane.WARNING_MESSAGE);
//
//            } else if (line2.isEmpty()) {
//
//                JOptionPane.showMessageDialog(this, "Please enter Your  Address Line 2 !", "Warning", JOptionPane.WARNING_MESSAGE);
//
//            } else if (city.equals("Select")) {
//
//                JOptionPane.showMessageDialog(this, "Please Select a Gender ! ", "Warning", JOptionPane.WARNING_MESSAGE);
//
//            } else if (year.equals("Select")) {
//
//                JOptionPane.showMessageDialog(this, "Please Select a Type ! ", "Warning", JOptionPane.WARNING_MESSAGE);
//
//            } else if (stream.equals("Select")) {
//
//                JOptionPane.showMessageDialog(this, "Please Select a Gender ! ", "Warning", JOptionPane.WARNING_MESSAGE);
//
//            } else if (gender.equals("Select")) {
//
//                JOptionPane.showMessageDialog(this, "Please Select a Gender ! ", "Warning", JOptionPane.WARNING_MESSAGE);
//
//            } else {
//
//                MYSQL.executeIUD("UPDATE `student` SET `fname` = '" + fname + "', `lname` = '" + lname + "', `line1` = '" + line1 + "', `line2` = '" + line2 + "', `city_id` = '" + cityMap.get(city) + "', `gender_id` = '" + genderMap.get(gender) + "', `clz_year_id` = '" + yearMap.get(year) + "', `stream_id` = '" + streamMap.get(stream) + "'"
//                        + "WHERE `email` = '" + email + "'");
//
//                JOptionPane.showMessageDialog(this, "Successfully Updated  ", "Success", JOptionPane.INFORMATION_MESSAGE);
//
//                studentLoad();
//                reset();
//
//            }
//
//        } catch (Exception e) {
//        }
        
    }//GEN-LAST:event_button2ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed

        try {

            String fname = textField1.getText();
            String lname = textField6.getText();
            String mobile = textField7.getText();
            String email = textField3.getText();
            String Un = textField8.getText();
            String pw = textField9.getText();

            String gender = String.valueOf(jComboBox2.getSelectedItem());
            String jobroll = String.valueOf(jComboBox3.getSelectedItem());
            String department = String.valueOf(jComboBox4.getSelectedItem());

            int id = this.textField1.getText().hashCode() + this.textField6.getText().hashCode();
            int empid = Math.abs(id);

            SecureRandom random = new SecureRandom();
            int range = 100000;
            int number = random.nextInt(range) + empid;
            String id2 = "EMP" + String.format("%08d", number);

//            System.out.println("Unique random ID2: " + id2);

            if (fname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter youer First Name !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (lname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Youer Last Name !", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (mobile.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter youer mobile number !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!mobile.matches("^[0]{1}[7]{1}[01245678]{1}[0-9]{7}$")) {
                JOptionPane.showMessageDialog(this, "Invalid Mobile Number !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Youer Email !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
                JOptionPane.showMessageDialog(this, "Invalid Email !", "Warning", JOptionPane.WARNING_MESSAGE);

            } else if (gender.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select Gender !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (jobroll.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select JobRoll !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (department.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select Department !", "Warning", JOptionPane.WARNING_MESSAGE);
            }if (Un.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Username !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!Un.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])[a-zA-Z[^0-9]]{8,}$")) {
                JOptionPane.showMessageDialog(this, "Invalid Username!  Min 8 chars Must have uppercase, lowercase, symbol No numbers !", "Warning", JOptionPane.WARNING_MESSAGE);
            }if (pw.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Password !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!pw.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$")) {
                JOptionPane.showMessageDialog(this, "Invalid Password! Must be at least 8 characters !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee` WHERE `employee_email` = '"+ email+"' OR `employee_phone` = '"+mobile+"' AND `username` = '"+Un+"' ");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Employee Allready registered! ", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    MySQL.executeIUD("INSERT INTO `employee` (`employee_id`,`employee_fname`,`employee_lname`,`employee_email`,`employee_phone`,`hire_date`,`jobs_job_id`,`department_department_id`,`gender_id`,`username`,`password`)"
                            + "VALUES ('" + id2 + "','" + fname + "','" + lname + "','" + email + "','" + mobile + "','" + sdf.format(date) + "','" + jobRollMap.get(jobroll) + "','" + departmentMap.get(department) + "','" + genderMap.get(gender) + "','" + Un + "','" + pw + "')");

                    JOptionPane.showMessageDialog(this, "SuccessFully Registred " + id2 + " ! " , "Success", JOptionPane.INFORMATION_MESSAGE);
                    RegMail(id2, Un, pw, lname, fname, email);
                    loadEmployeeTable();
                    reset();
                }
               }

                }catch (Exception e) {
                      e.printStackTrace();  
                }
         


    }//GEN-LAST:event_button1ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
      reset();
    }//GEN-LAST:event_button3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();

        String EmpId = String.valueOf(jTable1.getValueAt(row, 0));
        button1.setEnabled(false);
        button2.setEnabled(true);
        
        jLabel15.setVisible(true);
        jLabel16.setVisible(true);
        jLabel16.setText(EmpId);
        
        String fname = String.valueOf(jTable1.getValueAt(row, 1));
        textField1.setText(fname);
        
        String lname = String.valueOf(jTable1.getValueAt(row, 2));
        textField6.setText(lname);
        
        String email = String.valueOf(jTable1.getValueAt(row, 3));
        textField3.setText(email);
        
        String mobile = String.valueOf(jTable1.getValueAt(row, 4));
        textField7.setText(mobile);
        
        String gender = String.valueOf(jTable1.getValueAt(row, 5));
        jComboBox2.setSelectedItem(gender);
        
        String jobRoll = String.valueOf(jTable1.getValueAt(row, 6));
        jComboBox3.setSelectedItem(jobRoll);
        
        String Depaetment = String.valueOf(jTable1.getValueAt(row, 7));
        jComboBox4.setSelectedItem(Depaetment);
        
       


        
    }//GEN-LAST:event_jTable1MouseClicked

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
         try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee` ");

            if (resultSet.next()) {

                String path = "src//reports//EmpReg_Repo.jasper";

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
    }//GEN-LAST:event_button4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button2;
    private com.nex.cms.components.Button button3;
    private com.nex.cms.components.Button button4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private com.nex.cms.components.TextField textField1;
    private com.nex.cms.components.TextField textField3;
    private com.nex.cms.components.TextField textField4;
    private com.nex.cms.components.TextField textField5;
    private com.nex.cms.components.TextField textField6;
    private com.nex.cms.components.TextField textField7;
    private com.nex.cms.components.TextField textField8;
    private com.nex.cms.components.TextField textField9;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        textField1.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        textField7.setText("");
        textField8.setText("");
        textField9.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jLabel15.setVisible(false);
        jLabel16.setVisible(false);
        button1.setEnabled(true);
        button2.setEnabled(false);
    }
}
