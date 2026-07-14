package com.nex.cms.panel.hrm;

import com.nex.cms.panel.cusutomer.*;
import com.nex.cms.connection.MySQL;
import static com.nex.cms.frame.login.logger;
import com.nex.cms.model.Validations;
import com.nex.cms.model.tableAlign;
import com.nex.cms.panel.marketing.socialMediaManagement;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class payrollMng extends javax.swing.JPanel {

    public payrollMng() {
        initComponents();

        button9.setEnabled(false);
        textField6.setEditable(false);
        textField5.setEditable(false);
        textField11.setEditable(false);
        textField12.setEditable(false);
        textField9.setEditable(false);
        textField10.setEditable(false);
        textField15.setEditable(false);
        textField4.setEnabled(false);
        loadPaymentTable();
        total();
    }

    private void total() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT SUM(amount) AS total_amount FROM hr_account;");
            while (resultSet.next()) {
                double sum = resultSet.getDouble("total_amount");
                jLabel32.setText("Total: " + sum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculate() {
//        double basic = Double.valueOf(textField5.getText());
//        double overTime = Double.valueOf(textField7.getText());
//         double netPay = Double.valueOf(textField9.getText());
//        double pension = Double.valueOf(textField6.getText());
//        double deduction = Double.valueOf(textField10.getText());
//         double loanDue = Double.valueOf(textField12.getText());
//        double niPayment = Double.valueOf(textField13.getText());

        double totalWage = Double.valueOf(textField15.getText());
        double budgetHRM = Double.valueOf(jLabel32.getText());

        if (totalWage > budgetHRM) {
            JOptionPane.showMessageDialog(this, "Your HRM total Budgets is Law! Please Check", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            double calculateBalance = budgetHRM - totalWage;
            try {
                MySQL.executeIUD("UPDATE `total` SET `total` = '" + calculateBalance + "'");
                total();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void SalaryMail(String EmpId, String acnum, String pid, String nic, String fname, String ldue, String twage, String date, String email) {

//        String Id = EmpId;
//        String accNum = acnum;
//        String p = pid;
//        String fn = fname;
//        String ln = lname;
//        String ln = nic;
//        String ln = ldue;
//        String ln = twage;
//        String ln = date;
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
                    + "<h1>Dear " + fname + " ,</h1>"
                    + "<h2>We are pleased to inform you that your salary calculation for this mounth has been completed successfully. ,</h2>"
                    + "<p class='info'>Below are your salary details:</p>"
                    + "<table style='width: 100%; border-collapse: collapse; margin: 20px auto; display: table;'>"
                    + "<tr><td><strong>EMP ID :</strong></td><td class='highlight'>" + EmpId + "</td></tr>"
                    + "<tr><td><strong>Date :</strong></td><td class='highlight'>" + date + "</td></tr>"
                    + "<tr><td><strong>Account Number :</strong></td><td class='highlight'>" + acnum + "</td></tr>"
                    + "<tr><td><strong>Pay ID :</strong></td><td class='highlight'>" + pid + "</td></tr>"
                    + "<tr><td><strong>NIC :</strong></td><td class='highlight'>" + nic + "</td></tr>"
                    + "<tr><td><strong>Loan Due :</strong></td><td class='highlight'>" + ldue + "</td></tr>"
                    + "<tr><td><strong>Total Wage :</strong></td><td class='highlight'>" + twage + "</td></tr>"
                    + "</table>"
                    + "<p class='info'>HR Department,</p>"
                    + "<p class='info'>If you did not expect this email, please contact our support team immediately.</p>"
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
    public void loadPaymentTable() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee` INNER JOIN `payroll` ON `employee`.`employee_id` = `payroll`.`employee_employee_id` INNER JOIN `paymentinfo` ON `employee`.`employee_id` = `paymentinfo`.`employee_employee_id`");
                    
                   
            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
            defaultTableModel.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("employee_employee_id"));
                vector.add(resultSet.getString(" nic"));
                vector.add(resultSet.getString("Acc_num"));
                vector.add(resultSet.getString(" payment_id"));
                vector.add(resultSet.getString("ni_code"));
                vector.add(resultSet.getString("salary"));
                vector.add(resultSet.getString(" total_wage"));
                vector.add(resultSet.getString("pay_date"));

                defaultTableModel.addRow(vector);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        button4 = new com.nex.cms.components.Button();
        jLabel7 = new javax.swing.JLabel();
        textField1 = new com.nex.cms.components.TextField();
        jLabel172 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        jLabel181 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        button8 = new com.nex.cms.components.Button();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel186 = new javax.swing.JLabel();
        jLabel187 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        textField2 = new com.nex.cms.components.TextField();
        textField3 = new com.nex.cms.components.TextField();
        jLabel9 = new javax.swing.JLabel();
        textField4 = new com.nex.cms.components.TextField();
        jLabel10 = new javax.swing.JLabel();
        button9 = new com.nex.cms.components.Button();
        jLabel11 = new javax.swing.JLabel();
        textField5 = new com.nex.cms.components.TextField();
        jLabel12 = new javax.swing.JLabel();
        textField6 = new com.nex.cms.components.TextField();
        jLabel13 = new javax.swing.JLabel();
        textField7 = new com.nex.cms.components.TextField();
        textField8 = new com.nex.cms.components.TextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        textField9 = new com.nex.cms.components.TextField();
        textField10 = new com.nex.cms.components.TextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        textField11 = new com.nex.cms.components.TextField();
        textField12 = new com.nex.cms.components.TextField();
        jLabel18 = new javax.swing.JLabel();
        textField13 = new com.nex.cms.components.TextField();
        jLabel19 = new javax.swing.JLabel();
        textField14 = new com.nex.cms.components.TextField();
        jLabel20 = new javax.swing.JLabel();
        textField15 = new com.nex.cms.components.TextField();
        jLabel21 = new javax.swing.JLabel();
        button10 = new com.nex.cms.components.Button();
        button13 = new com.nex.cms.components.Button();
        button14 = new com.nex.cms.components.Button();
        button15 = new com.nex.cms.components.Button();
        jSeparator7 = new javax.swing.JSeparator();
        button16 = new com.nex.cms.components.Button();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();

        jSeparator2.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator2.setMaximumSize(new java.awt.Dimension(32767, 5));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setText("Payroll Management");

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
        jLabel7.setText("Employee  ID");

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

        jLabel172.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel172.setText("Employee ID                   :");

        jLabel177.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel177.setText("Employee ID");

        jLabel178.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel178.setText("Employee Name        :");

        jLabel179.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel179.setText("Employee Email            :");

        jLabel180.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel180.setText("Employee Mobile          :");

        jLabel181.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel181.setText("Employee Mobile");

        jLabel182.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel182.setText("Employee Email");

        jLabel183.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel183.setText("Employee Name");

        jSeparator3.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator3.setMaximumSize(new java.awt.Dimension(32767, 5));

        button8.setBackground(new java.awt.Color(204, 204, 204));
        button8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-search-16_1.png"))); // NOI18N
        button8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button8ActionPerformed(evt);
            }
        });

        jSeparator4.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setMaximumSize(new java.awt.Dimension(32767, 5));

        jLabel186.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel186.setText("Job Roll                         :");

        jLabel187.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel187.setText("Job Roll");

        jSeparator6.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator6.setMaximumSize(new java.awt.Dimension(32767, 5));

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel8.setText("Employee Account Number");

        textField2.setForeground(new java.awt.Color(0, 0, 0));
        textField2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField2.setShadowColor(new java.awt.Color(0, 153, 204));
        textField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField2MouseClicked(evt);
            }
        });
        textField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField2ActionPerformed(evt);
            }
        });
        textField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField2KeyReleased(evt);
            }
        });

        textField3.setForeground(new java.awt.Color(0, 0, 0));
        textField3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField3.setShadowColor(new java.awt.Color(0, 153, 204));
        textField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField3ActionPerformed(evt);
            }
        });
        textField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField3KeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel9.setText("NIC");

        textField4.setForeground(new java.awt.Color(0, 0, 0));
        textField4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField4.setShadowColor(new java.awt.Color(0, 153, 204));
        textField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField4ActionPerformed(evt);
            }
        });
        textField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField4KeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel10.setText("Payment ID");

        button9.setBackground(new java.awt.Color(153, 204, 255));
        button9.setText("Update Details");
        button9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button9ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 204, 255));
        jLabel11.setText("Basic");

        textField5.setForeground(new java.awt.Color(0, 0, 0));
        textField5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField5.setShadowColor(new java.awt.Color(0, 153, 204));
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

        jLabel12.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 102, 102));
        jLabel12.setText("Pension");

        textField6.setForeground(new java.awt.Color(0, 0, 0));
        textField6.setText("1000");
        textField6.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField6.setShadowColor(new java.awt.Color(0, 153, 204));
        textField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField6ActionPerformed(evt);
            }
        });
        textField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField6KeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 204, 255));
        jLabel13.setText("Over Time");

        textField7.setForeground(new java.awt.Color(0, 0, 0));
        textField7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField7.setShadowColor(new java.awt.Color(0, 153, 204));
        textField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField7ActionPerformed(evt);
            }
        });
        textField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField7KeyReleased(evt);
            }
        });

        textField8.setForeground(new java.awt.Color(0, 0, 0));
        textField8.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField8.setShadowColor(new java.awt.Color(0, 153, 204));
        textField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField8ActionPerformed(evt);
            }
        });
        textField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField8KeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 102, 102));
        jLabel14.setText("Loans");

        jLabel15.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 204, 255));
        jLabel15.setText("Net Pay");

        textField9.setForeground(new java.awt.Color(0, 0, 0));
        textField9.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField9.setShadowColor(new java.awt.Color(0, 153, 204));
        textField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField9ActionPerformed(evt);
            }
        });
        textField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField9KeyReleased(evt);
            }
        });

        textField10.setForeground(new java.awt.Color(0, 0, 0));
        textField10.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField10.setShadowColor(new java.awt.Color(0, 153, 204));
        textField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField10ActionPerformed(evt);
            }
        });
        textField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField10KeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 102, 102));
        jLabel16.setText("Deduction");

        jLabel17.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel17.setText("Pay Date");

        textField11.setForeground(new java.awt.Color(0, 0, 0));
        textField11.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField11.setShadowColor(new java.awt.Color(0, 153, 204));
        textField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField11ActionPerformed(evt);
            }
        });
        textField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField11KeyReleased(evt);
            }
        });

        textField12.setForeground(new java.awt.Color(0, 0, 0));
        textField12.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField12.setShadowColor(new java.awt.Color(0, 153, 204));
        textField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField12ActionPerformed(evt);
            }
        });
        textField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField12KeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel18.setText("Loan Due");

        textField13.setForeground(new java.awt.Color(0, 0, 0));
        textField13.setText("500");
        textField13.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField13.setShadowColor(new java.awt.Color(0, 153, 204));
        textField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField13ActionPerformed(evt);
            }
        });
        textField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField13KeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel19.setText("NI Payment");

        textField14.setForeground(new java.awt.Color(0, 0, 0));
        textField14.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField14.setShadowColor(new java.awt.Color(0, 153, 204));
        textField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField14ActionPerformed(evt);
            }
        });
        textField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField14KeyReleased(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel20.setText("NI Code");

        textField15.setForeground(new java.awt.Color(0, 0, 0));
        textField15.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField15.setShadowColor(new java.awt.Color(0, 153, 204));
        textField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField15ActionPerformed(evt);
            }
        });
        textField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField15KeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 153, 255));
        jLabel21.setText("Total Wage");

        button10.setBackground(new java.awt.Color(204, 204, 204));
        button10.setText("Calculat Wage Payment");
        button10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button10ActionPerformed(evt);
            }
        });

        button13.setBackground(new java.awt.Color(153, 204, 255));
        button13.setText("Save Wage");
        button13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button13ActionPerformed(evt);
            }
        });

        button14.setBackground(new java.awt.Color(153, 204, 255));
        button14.setText("Save NI");
        button14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button14ActionPerformed(evt);
            }
        });

        button15.setBackground(new java.awt.Color(255, 153, 153));
        button15.setForeground(new java.awt.Color(0, 0, 0));
        button15.setText("Update Loan");
        button15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button15ActionPerformed(evt);
            }
        });

        jSeparator7.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator7.setMaximumSize(new java.awt.Dimension(32767, 5));

        button16.setBackground(new java.awt.Color(153, 204, 255));
        button16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button16.setText("Print");
        button16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button16ActionPerformed(evt);
            }
        });

        jSeparator5.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator5.setMaximumSize(new java.awt.Dimension(32767, 5));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NIC", "Account Number", "Payment ID", "NI Code", "Salary", "Total Wage", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jSeparator8.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator8.setMaximumSize(new java.awt.Dimension(32767, 5));

        jLabel32.setBackground(new java.awt.Color(226, 73, 73));
        jLabel32.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 0, 0));
        jLabel32.setText("0.00");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 51, 51));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Total Capital (HRM)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel180, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel172, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel179, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel177, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel181, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                    .addComponent(jLabel182, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel178, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel186, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel187, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel183, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)))
                            .addComponent(jLabel1))
                        .addGap(105, 105, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(658, 658, 658)
                                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addComponent(jSeparator8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                                            .addComponent(jLabel8)
                                            .addComponent(textField4, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(button9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                                    .addComponent(jLabel11)
                                    .addComponent(textField7, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                                    .addComponent(jLabel13)
                                    .addComponent(textField9, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textField8, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                                    .addComponent(textField10, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                                    .addComponent(textField6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel16))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textField13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel19))
                                .addGap(12, 12, 12))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(textField11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18))
                                .addGap(18, 18, 18)))
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(button10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(button13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(button4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(button16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel172)
                                .addComponent(jLabel177)
                                .addComponent(jLabel178)
                                .addComponent(jLabel183))
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel180)
                                    .addComponent(jLabel181)
                                    .addComponent(jLabel186)
                                    .addComponent(jLabel187))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel179)
                                    .addComponent(jLabel182))))))
                .addGap(10, 10, 10)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(button10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textField8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textField9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textField10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textField13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textField14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel21))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(button14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(button15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(button16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textField15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        reset();
    }//GEN-LAST:event_button4ActionPerformed

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed

    }//GEN-LAST:event_textField1ActionPerformed

    private void textField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField1KeyReleased


    }//GEN-LAST:event_textField1KeyReleased

    private void button8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button8ActionPerformed
        try {

            String EmpId = textField1.getText();

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee` INNER JOIN `gender` ON `employee`.`gender_id`=`gender`.`id`  INNER JOIN `jobs` ON `employee`.`jobs_job_id`=`jobs`.`job_id` INNER JOIN `basic` ON `jobs`.`job_id`=`basic`.`jobs_job_id` INNER JOIN `paymentinfo` ON `employee`.`employee_id`=`paymentinfo`.`employee_employee_id`  WHERE `employee_id` = '" + EmpId + "'");

            if (resultSet.next()) {

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `loans` WHERE `loans`.` paymentInfo_ P_info_id` = '" + EmpId + "'");

                if (rs.next()) {
                    textField12.setText(rs.getString("amount"));
                } else {
                    textField12.setText("0.00");
                }

                jLabel177.setText(resultSet.getString("employee_id"));
                jLabel183.setText(resultSet.getString("employee_fname"));
                jLabel182.setText(resultSet.getString("employee_email"));
                jLabel181.setText(resultSet.getString("employee_phone"));
                jLabel187.setText(resultSet.getString("jobs.job_title"));

                textField2.setEditable(false);
                textField3.setEditable(false);
                textField4.setEditable(false);

                textField2.setText(resultSet.getString("Acc_num"));
                textField3.setText(resultSet.getString(" nic"));
                textField4.setText(resultSet.getString(" payment_id"));
                textField5.setText(resultSet.getString("salary"));

                button9.setEnabled(false);

                LocalDate currentDate = LocalDate.now();
                String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                textField11.setText(formattedDate);

            } else {

//            String Id = textField1.getText();
                ResultSet rs = MySQL.executeSearch("SELECT * FROM `employee`  WHERE `employee_id` = '" + EmpId + "'");

                if (rs.next()) {
                    if (EmpId.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please enter Employee ID !", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int id = this.textField1.getText().hashCode();
                        int empid = Math.abs(id);

                        SecureRandom random = new SecureRandom();
                        int range = 100000;
                        int number = random.nextInt(range) + empid;
                        String PaymentID = "PAY" + String.format("%08d", number);
                        String NICode = "NI" + String.format("%08d", number);

                        textField4.setText(PaymentID);
                        textField4.setEnabled(false);

                    }
                } else {

                    JOptionPane.showMessageDialog(this, "Invalid ID OR Employee not found. You can add details. !", "Warning", JOptionPane.WARNING_MESSAGE);
                    textField4.setText("");

                }

                textField1.setText(EmpId);

                jLabel177.setText("Employee ID ");
                jLabel183.setText("Employee Name ");
                jLabel182.setText("Employee Email ");
                jLabel181.setText("Employee Mobile ");
                jLabel187.setText("Employee job roll ");

                textField2.setText("");
                textField3.setText("");

                textField2.setEditable(true);
                textField3.setEditable(true);
                textField4.setEditable(true);

                button9.setEnabled(true);
            }

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////
//              try {
//            PreparedStatement ps = conn.prepareStatement("SELECT name, account_number, salary FROM employees WHERE emp_id = ?");
//            ps.setString(1, empId);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                nameField.setText(rs.getString("name"));
//                accField.setText(rs.getString("account_number"));
//                salaryField.setText(String.valueOf(rs.getDouble("salary")));
//                JOptionPane.showMessageDialog(this, "Employee Found!");
//            } else {
//                nameField.setText("");
//                accField.setText("");
//                salaryField.setText("");
//                JOptionPane.showMessageDialog(this, "Employee not found. You can add details.");
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button8ActionPerformed

    private void textField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField2ActionPerformed

    private void textField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField2KeyReleased

    private void textField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField3ActionPerformed

    private void textField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField3KeyReleased

    private void textField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField4ActionPerformed

    private void textField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField4KeyReleased

    private void button9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button9ActionPerformed
        try {

            String Id = textField1.getText();
            String acc = textField2.getText();
            String nic = textField3.getText();
            String pid = textField4.getText();

            int id = this.textField1.getText().hashCode();
            int empid = Math.abs(id);

            SecureRandom random = new SecureRandom();
            int range = 100000;
            int number = random.nextInt(range) + empid;
            String NICode = "NI" + String.format("%08d", number);

//            System.out.println("Unique random ID2: " + id2);
            if (Id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Employee ID !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (acc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Employee Account Number !", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (nic.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Employee NIC !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (pid.isEmpty()) {
                JOptionPane.showMessageDialog(this, "INVALID ID !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `paymentinfo` WHERE `employee_employee_id` = '" + Id + "'  AND ` nic` = '" + nic + "' ");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Employee Allready Saved! ", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

//                    Date date = new Date();
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    MySQL.executeIUD("INSERT INTO `paymentinfo` (`employee_employee_id`,` nic`,`Acc_num`,` payment_id`,`ni_code`)"
                            + "VALUES ('" + Id + "','" + nic + "','" + acc + "','" + pid + "','" + NICode + "')");

                    JOptionPane.showMessageDialog(this, "SuccessFully Added " + Id + "'s Details ! ", "Success", JOptionPane.INFORMATION_MESSAGE);

                    reset();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button9ActionPerformed

    private void textField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField5ActionPerformed

    private void textField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField5KeyReleased

    private void textField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField6ActionPerformed

    private void textField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField6KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField6KeyReleased

    private void textField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField7ActionPerformed

    private void textField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField7KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField7KeyReleased

    private void textField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField8ActionPerformed

    private void textField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField8KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField8KeyReleased

    private void textField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField9ActionPerformed

    private void textField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField9KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField9KeyReleased

    private void textField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField10ActionPerformed

    private void textField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField10KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField10KeyReleased

    private void textField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField11ActionPerformed

    private void textField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField11KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField11KeyReleased

    private void textField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField12ActionPerformed

    private void textField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField12KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField12KeyReleased

    private void textField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField13ActionPerformed

    private void textField13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField13KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField13KeyReleased

    private void textField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField14ActionPerformed

    private void textField14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField14KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField14KeyReleased

    private void textField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField15ActionPerformed

    private void textField15KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField15KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField15KeyReleased

    private void button10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button10ActionPerformed
        try {

//            String Wage = textField15.getText();
//            double totalWage = Double.valueOf(Wage);
//
//            String text = jLabel32.getText().replace("Total: ", "").trim();
//            double total = Double.valueOf(text);

           

            String EmpId = textField1.getText();

            String bStr = textField5.getText().trim();
            String pStr = textField6.getText().trim();
            String oStr = textField7.getText().trim();
            String lStr = textField8.getText().trim();

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee` INNER JOIN `gender` ON `employee`.`gender_id`=`gender`.`id`  INNER JOIN `jobs` ON `employee`.`jobs_job_id`=`jobs`.`job_id` INNER JOIN `basic` ON `jobs`.`job_id`=`basic`.`jobs_job_id` INNER JOIN `paymentinfo` ON `employee`.`employee_id`=`paymentinfo`.`employee_employee_id`  WHERE `employee_id` = '" + EmpId + "'");

            if (resultSet.next()) {

                if (bStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Basic payment is not Filld", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (pStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "pension payment is not Filld !", "warning", JOptionPane.WARNING_MESSAGE);
                } else if (oStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "OtHours is Empty !", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (lStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "loan payment is not Filld !", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    double basic = Double.parseDouble(textField5.getText());
                    double pension = Double.parseDouble(textField6.getText());
                    double otHours = Double.parseDouble(textField7.getText());
                    double loan = Double.parseDouble(textField8.getText());

                    double otPay = otHours * 1000;
                    double deduction = pension + loan;
                    double netPay = basic + otPay - deduction;

                    textField10.setText(String.format("%.2f", deduction));
                    textField9.setText(String.format("%.2f", netPay));
                    textField9.setEditable(false);
                    textField10.setEditable(false);

                    textField14.setText(resultSet.getString("ni_code"));
                    textField14.setEditable(false);
                    textField15.setText(String.format("%.2f", netPay));
                    
  
                }

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Employee ID !", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button10ActionPerformed

    private void button13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button13ActionPerformed
        try {

            String basic = textField5.getText();
            String pension = textField6.getText();
            String ot = textField7.getText();
            String loan = textField8.getText();
            String netpay = textField9.getText();
            String deducation = textField10.getText();
            String paydate = textField11.getText();
            String totalWage = textField15.getText();
            String jobroall = jLabel187.getText();
            String EMPID = textField1.getText();

            String accnum = textField2.getText();
            String nic = textField3.getText();
            String payid = textField4.getText();
            String fname = jLabel183.getText();
            String email = jLabel182.getText();

            int monthNumber = LocalDate.now().getMonthValue();

            if (EMPID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Employee ID First !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (basic.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Basic Salary Is Not Set !", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (pension.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pension Is Not Set !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (ot.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Over Time is Not Set !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (loan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Loan is Not Set !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (netpay.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Net pay is Not Set !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (deducation.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Deduction is Not Set !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (paydate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Date is Not Set !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (totalWage.isEmpty()) {
                JOptionPane.showMessageDialog(this, " Wage is Not Set !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (EMPID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "INVALID ID !", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `payroll` WHERE `employee_employee_id` = '" + EMPID + "'  AND `month_mid` = '" + monthNumber + "' ");
                ResultSet rs = MySQL.executeSearch("SELECT `job_id` FROM `jobs` WHERE `job_title` = '" + jobroall + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Employee Wage Allready Saved! ", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (rs.next()) {
                        String jobid = rs.getString("job_id");
                        MySQL.executeIUD("INSERT INTO `payroll` (`salary`,`pay_date`,`jobs_job_id`,`employee_employee_id`,`pension`,`Loans`,`deduction`,`ot`,` total_wage`,`month_mid`)"
                                + "VALUES ('" + basic + "','" + paydate + "','" + jobid + "','" + EMPID + "','" + pension + "','" + loan + "','" + deducation + "','" + ot + "','" + totalWage + "','" + monthNumber + "')");
                        
                        ResultSet resultSet2 = null;

            resultSet2 = MySQL.executeSearch("SELECT SUM(amount) AS amount FROM `hr_account`");

            while (resultSet2.next()) {
                double sum = resultSet2.getDouble("amount");
                double calculate = sum -= Double.parseDouble(totalWage);
                double processCal = calculate;

                MySQL.executeIUD("UPDATE `hr_account` SET `amount`='" + calculate + "'");

                jLabel32.setText("Total: " + processCal);

            }

                        JOptionPane.showMessageDialog(this, "SuccessFully Saved " + EMPID + "'s Details ! ", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadPaymentTable();
                        SalaryMail(EMPID, accnum, payid, nic, fname, loan, totalWage, paydate, email);
                        reset();
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button13ActionPerformed

    private void button14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button14ActionPerformed
        try {

            String Nicode = textField14.getText();
            String Nipayment = textField13.getText();
            String date = textField11.getText();

//            if (Nicode.eq)) {
            ResultSet resultSet;

            resultSet = MySQL.executeSearch("SELECT * FROM `paymentinfo` WHERE `ni_code` =  '" + Nicode + "' ");

            if (resultSet.next()) {

                String pInfoID = resultSet.getString(" P_info_id");
                LocalDate currentDate = LocalDate.now();
                String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                ResultSet rs;
                rs = MySQL.executeSearch("SELECT * FROM `paymentinfo` INNER JOIN `nipayment` ON `paymentinfo`.` P_info_id`=`nipayment`.` paymentInfo_ P_info_id` WHERE `ni_code` =  '" + Nicode + "' AND `date` =  '" + date + "' ");

                if (rs.next()) {

                    JOptionPane.showMessageDialog(this, " AllReady Payed !", "Warning", JOptionPane.WARNING_MESSAGE);
                    double netpay = Double.parseDouble(textField9.getText());
                    double nipay = Double.parseDouble(textField13.getText());
                    double Twage = netpay - nipay;
                    textField15.setText(String.format("%.2f", Twage));

                } else {

                    MySQL.executeIUD("INSERT INTO `nipayment` (` paymentInfo_ P_info_id`,`Payment`,`date`)"
                            + "VALUES ('" + pInfoID + "','" + Nipayment + "','" + formattedDate + "')");

                    JOptionPane.showMessageDialog(this, "Completely saved !", "Success", JOptionPane.INFORMATION_MESSAGE);

                    double netpay = Double.parseDouble(textField9.getText());
                    double nipay = Double.parseDouble(textField13.getText());
                    double Twage = netpay - nipay;
                    textField15.setText(String.format("%.2f", Twage));

                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid NI Code  !", "Warning", JOptionPane.WARNING_MESSAGE);
            }
//            } else {
//                   JOptionPane.showMessageDialog(this, "No NI Code !", "Warning", JOptionPane.WARNING_MESSAGE);
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_button14ActionPerformed

    private void button15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button15ActionPerformed
        String EmpId = textField1.getText();
        String date = textField11.getText();
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `loans` WHERE ` paymentInfo_ P_info_id` = '" + EmpId + "'");

            if (rs.next()) {

                ResultSet rs1 = MySQL.executeSearch("SELECT * FROM `loans` WHERE ` paymentInfo_ P_info_id` = '" + EmpId + "' AND `date` = '" + date + "'");

                if (rs1.next()) {
                    JOptionPane.showMessageDialog(this, " Allready Updated This Month Loan !", "Warning", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    double amount = Double.parseDouble(textField12.getText());
                    double Payment = Double.parseDouble(textField8.getText());
                    double UpdateLoan = amount - Payment;

                    MySQL.executeIUD("UPDATE `loans` SET  `amount` = '" + UpdateLoan + "'"
                            + "WHERE `paymentInfo_P_info_id` = '" + EmpId + "'");

                    JOptionPane.showMessageDialog(this, " Success Fully Loan Updated !", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    textField8.setEditable(false);
                    button15.setEnabled(false);

                    textField12.setText(String.format("%.2f", UpdateLoan));
                }

            } else {
                textField12.setText("0.00");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_button15ActionPerformed

    private void button16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button16ActionPerformed
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `payroll` ");

            if (resultSet.next()) {

                String path = "src//reports//EmpSal_Repo.jasper";

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
    }//GEN-LAST:event_button16ActionPerformed

    private void textField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textField2MouseClicked

    }//GEN-LAST:event_textField2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button10;
    private com.nex.cms.components.Button button13;
    private com.nex.cms.components.Button button14;
    private com.nex.cms.components.Button button15;
    private com.nex.cms.components.Button button16;
    private com.nex.cms.components.Button button4;
    private com.nex.cms.components.Button button8;
    private com.nex.cms.components.Button button9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable1;
    private com.nex.cms.components.TextField textField1;
    private com.nex.cms.components.TextField textField10;
    private com.nex.cms.components.TextField textField11;
    private com.nex.cms.components.TextField textField12;
    private com.nex.cms.components.TextField textField13;
    private com.nex.cms.components.TextField textField14;
    private com.nex.cms.components.TextField textField15;
    private com.nex.cms.components.TextField textField2;
    private com.nex.cms.components.TextField textField3;
    private com.nex.cms.components.TextField textField4;
    private com.nex.cms.components.TextField textField5;
    private com.nex.cms.components.TextField textField6;
    private com.nex.cms.components.TextField textField7;
    private com.nex.cms.components.TextField textField8;
    private com.nex.cms.components.TextField textField9;
    // End of variables declaration//GEN-END:variables

    private void reset() {

        button9.setEnabled(false);

        textField1.setText("");

        jLabel177.setText("Employee ID ");
        jLabel183.setText("Employee Name ");
        jLabel182.setText("Employee Email ");
        jLabel181.setText("Employee Mobile ");
        jLabel187.setText("Employee job roll ");

        textField2.setText("");
        textField3.setText("");
        textField4.setText("");

        textField14.setEnabled(true);
        textField14.setText("");
        textField5.setText("");
//        textField6.setText("");
        textField7.setText("");
        textField8.setText("");
        textField9.setText("");
        textField10.setText("");
        textField11.setText("");
        textField12.setText("");
//        textField13.setText("");
        textField15.setText("");

        textField8.setEditable(true);
        button15.setEnabled(true);

//        textField2.setText("");
//
//        button7.setEnabled(true);
//        jLabel184.setVisible(false);
//        jLabel185.setVisible(false);
    }
}
