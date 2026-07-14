package com.nex.cms.panel.accounting;

import com.nex.cms.connection.MySQL;
import com.nex.cms.frame.hrm.selectEmployee;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mac
 */
public class Loans_Management extends javax.swing.JPanel {

    HashMap<String, String> loanType = new HashMap<>();
    HashMap<String, String> InterestRate = new HashMap<>();
    HashMap<String, String> timeFrame = new HashMap<>();

    /**
     * Creates new form Loan
     */
    public Loans_Management() {
        initComponents();
        loadLoanType();
        interestRate();
        timeFrame();
        loadLoans();
        button20.setEnabled(false);
        button21.setEnabled(false);
        jLabel55.setEnabled(true);
        jLabel46.setEnabled(true);
        button19.setEnabled(false);

        jLabel44.setEnabled(false);
        textField10.setEnabled(false);
        button22.setEnabled(false);

        jTextArea1.setEnabled(false);
        jLabel47.setEnabled(false);
    }

    //    employee id
    public JLabel getjLabel55() {
        return jLabel55;
    }

//    employee name
    public JLabel getjLabel46() {
        return jLabel46;
    }

    private void loadLoans() {
        try {

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `loan` INNER JOIN `interest_rate` ON `loan`.`interest_rate_id` = `interest_rate`.`interest_rate_id` INNER JOIN `loan_type` ON `loan`.`loan_type_id` = `loan_type`.`loan_id` INNER JOIN `time_frame` ON `loan`.`time_frame_id` = `time_frame`.`time_frame_id`");

            while (resultSet.next()) {

                Vector vector = new Vector();
                vector.add(resultSet.getString("loan_id"));
                vector.add(resultSet.getString("amount"));
                vector.add(resultSet.getString("get_date"));
                vector.add(resultSet.getString("employee_id"));
                vector.add(resultSet.getString("employee_fname"));
                vector.add(resultSet.getString("loan_type.type_name"));
                vector.add(resultSet.getString("interest_rate.rate"));
                vector.add(resultSet.getString("time_frame.time_frame"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLoanType() {

        try {
            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `loan_type`");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultset.next()) {
                vector.add(resultset.getString("type_name"));
                loanType.put(resultset.getString("type_name"), resultset.getString("loan_id"));
            }
            DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(vector);
            jComboBox4.setModel(defaultComboBoxModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void interestRate() {

        try {
            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `interest_rate`");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultset.next()) {
                vector.add(resultset.getString("rate"));
                InterestRate.put(resultset.getString("rate"), resultset.getString("interest_rate_id"));
            }
            DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(defaultComboBoxModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void timeFrame() {

        try {
            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `time_frame`");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultset.next()) {
                vector.add(resultset.getString("time_frame"));
                timeFrame.put(resultset.getString("time_frame"), resultset.getString("time_frame_id"));
            }
            DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(vector);
            jComboBox5.setModel(defaultComboBoxModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        textField9 = new com.nex.cms.components.TextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        button19 = new com.nex.cms.components.Button();
        button21 = new com.nex.cms.components.Button();
        button20 = new com.nex.cms.components.Button();
        jLabel27 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        button22 = new com.nex.cms.components.Button();
        jLabel50 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel44 = new javax.swing.JLabel();
        textField10 = new com.nex.cms.components.TextField();
        button23 = new com.nex.cms.components.Button();
        jLabel45 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        button24 = new com.nex.cms.components.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        button36 = new com.nex.cms.components.Button();
        button1 = new com.nex.cms.components.Button();

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel34.setText("Loan ID");

        jLabel37.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel37.setText("Loan Amount RS.");

        textField9.setForeground(new java.awt.Color(0, 0, 0));

        jLabel38.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel38.setText("Employee");

        jLabel43.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel43.setText("Loan Type");

        jLabel42.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel42.setText("Interest Rate");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel46.setBackground(new java.awt.Color(226, 73, 73));
        jLabel46.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel46.setText("null");

        button19.setBackground(new java.awt.Color(204, 255, 255));
        button19.setForeground(new java.awt.Color(0, 0, 0));
        button19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-send-24 (1).png"))); // NOI18N
        button19.setText("  Send Mail To Manager");
        button19.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button19ActionPerformed(evt);
            }
        });

        button21.setBackground(new java.awt.Color(255, 204, 204));
        button21.setForeground(new java.awt.Color(0, 0, 0));
        button21.setText("Delete Loan");
        button21.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button21ActionPerformed(evt);
            }
        });

        button20.setBackground(new java.awt.Color(204, 255, 255));
        button20.setForeground(new java.awt.Color(0, 0, 0));
        button20.setText("Edit Loan");
        button20.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button20ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 153, 255));
        jLabel27.setText("Loan page");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel47.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel47.setText("Write The Reason");

        jLabel48.setBackground(new java.awt.Color(255, 255, 255));
        jLabel48.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel48.setText("xxx-xxxxxxxx");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel49.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel49.setText("Select Time Frame");

        button22.setBackground(new java.awt.Color(153, 255, 153));
        button22.setForeground(new java.awt.Color(0, 0, 0));
        button22.setText("Pay");
        button22.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button22ActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel50.setText("Get Date");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel44.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel44.setText("Paid Amount RS.");

        textField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField10KeyReleased(evt);
            }
        });

        button23.setBackground(new java.awt.Color(153, 255, 153));
        button23.setForeground(new java.awt.Color(0, 0, 0));
        button23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-add-16.png"))); // NOI18N
        button23.setText("  Add Loan");
        button23.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button23ActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel45.setText("Total");

        jLabel51.setBackground(new java.awt.Color(226, 73, 73));
        jLabel51.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel51.setText("null");

        jLabel52.setBackground(new java.awt.Color(226, 73, 73));
        jLabel52.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel52.setText("Rs.");

        button24.setBackground(new java.awt.Color(204, 255, 255));
        button24.setForeground(new java.awt.Color(0, 0, 0));
        button24.setText("Select Employee");
        button24.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button24ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Loan ID", "Loan Amount", "Date", "Employee ID", "First Name", "Loan Type", "Interest Rate", "Time Frame"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jLabel54.setBackground(new java.awt.Color(226, 73, 73));
        jLabel54.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel54.setText("null");

        jLabel55.setBackground(new java.awt.Color(226, 73, 73));
        jLabel55.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel55.setText("null");

        jLabel32.setBackground(new java.awt.Color(226, 73, 73));
        jLabel32.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 0, 0));
        jLabel32.setText("0.00");

        jLabel35.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 51, 51));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Total Capital (Loan)");

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(button24, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel49)
                                    .addComponent(jLabel37))
                                .addGap(21, 21, 21))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox5, 0, 271, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(271, 271, 271)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(textField10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(button22, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(152, 152, 152)
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3))
                        .addGap(7, 7, 7))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(button19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(button23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(3, 3, 3))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(button20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(23, 23, 23)
                                .addComponent(button21, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel44)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textField10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(jLabel32))
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel45)
                                .addComponent(jLabel52)
                                .addComponent(jLabel54))
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(button23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(button20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button21, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(button1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(button19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button24, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(jLabel48))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 630, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        jTextArea1.setEnabled(true);
        jLabel47.setEnabled(true);
        jLabel38.setEnabled(false);
        button19.setEnabled(true);
        button20.setEnabled(true);
        button21.setEnabled(true);
        button24.setEnabled(false);
        button23.setEnabled(false);
        jLabel44.setEnabled(true);
        textField10.setEnabled(true);
        button22.setEnabled(true);
        jLabel55.setEnabled(false);
        jLabel46.setEnabled(false);

        int row = jTable2.getSelectedRow();

        String id = String.valueOf(jTable2.getValueAt(row, 0));
        jLabel48.setText(id);

        String loanType = String.valueOf(jTable2.getValueAt(row, 5));
        jComboBox4.setSelectedItem(loanType);

        String interestRate = String.valueOf(jTable2.getValueAt(row, 6));
        jComboBox1.setSelectedItem(interestRate);

        String amount = String.valueOf(jTable2.getValueAt(row, 1));
        textField9.setText(amount);

        String timeFrame = String.valueOf(jTable2.getValueAt(row, 7));
        jComboBox5.setSelectedItem(timeFrame);

        String date = String.valueOf(jTable2.getValueAt(row, 2));
        jLabel51.setText(date);

        String empID = String.valueOf(jTable2.getValueAt(row, 3));
        jLabel55.setText(empID);

        String empName = String.valueOf(jTable2.getValueAt(row, 4));
        jLabel46.setText(empName);
    }//GEN-LAST:event_jTable2MouseClicked

    private void button24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button24ActionPerformed
        selectEmployee seleEmployee = new selectEmployee();
        seleEmployee.setVisible(true);
        seleEmployee.setLoans_Management(this);
    }//GEN-LAST:event_button24ActionPerformed

    private void button23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button23ActionPerformed
        String loanID = jLabel48.getText();
        String loanType = String.valueOf(jComboBox4.getSelectedItem());
        String interestRate = String.valueOf(jComboBox1.getSelectedItem());
        String amount = textField9.getText();
        String timeFrame = String.valueOf(jComboBox5.getSelectedItem());
        String empID = jLabel55.getText();
        String empName = jLabel46.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());

        try {

            String loan = textField9.getText();
            double totalLoan = Double.valueOf(loan);

            String text = jLabel32.getText().replace("Total: ", "").trim();
            double total = Double.valueOf(text);

            ResultSet resultSet2 = null;

            resultSet2 = MySQL.executeSearch("SELECT SUM(amount) AS amount FROM `budget_loan`");

            while (resultSet2.next()) {
                double sum = resultSet2.getDouble("amount");
                double calculate = sum -= totalLoan;
                double processCal = calculate;

                MySQL.executeIUD("UPDATE `budget_loan` SET `amount`='" + calculate + "'");

                jLabel32.setText("Total: " + processCal);

            }

            if (loanType == "Select") {
                JOptionPane.showMessageDialog(this, "Please Select A Loan Type", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (interestRate == "Select") {
                JOptionPane.showMessageDialog(this, "Please Select A Interest Rate", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (amount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter the Loan Amount", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (timeFrame == "Select") {
                JOptionPane.showMessageDialog(this, "Please Select A Time Frame", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `loan` WHERE `employee_id`='" + empID + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "The Given Employee ID Has Been Registered Before A Loan !", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    MySQL.executeIUD("INSERT INTO `loan` (`amount`,`get_date`,`employee_id`,`employee_fame`,`loan_type_id`,`interest_rate_id`,`time_frame_id`) VALUES('" + amount + "','" + date + "','" + empID + "','" + empName + "','" + this.loanType.get(loanType) + "','" + InterestRate.get(interestRate) + "','" + this.timeFrame.get(timeFrame) + "')");
                    loadLoans();
                    JOptionPane.showMessageDialog(this, "Loan Record Add Success", "Warning", JOptionPane.WARNING_MESSAGE);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_button23ActionPerformed

    private void button20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button20ActionPerformed
        int row = jTable2.getSelectedRow();

        String loanID = jLabel48.getText();
        String loanType = String.valueOf(jComboBox4.getSelectedItem());
        String interestRate = String.valueOf(jComboBox1.getSelectedItem());
        String amount = textField9.getText();
        String timeFrame = String.valueOf(jComboBox5.getSelectedItem());
        String empID = jLabel55.getText();
        String empName = jLabel46.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please Select A Loan", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {

            try {

                if (loanType == "Select") {
                    JOptionPane.showMessageDialog(this, "Please Select A LoanType", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (interestRate == "Select") {
                    JOptionPane.showMessageDialog(this, "Please Select A Loan Interest Rate", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (amount.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Enter Loan Amount", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (timeFrame == "Select") {
                    JOptionPane.showMessageDialog(this, "Please Select A Loan Time Frame", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (empID == "null") {
                    JOptionPane.showMessageDialog(this, "Please Select Employee", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MySQL.executeIUD("UPDATE `Loan` SET `amount` = '" + amount + "',`employee_id` = '" + empID + "',`employee_fame` = '" + empName + "',`loan_type_id`='" + this.loanType.get(loanType) + "',`interest_rate_id`='" + InterestRate.get(interestRate) + "',`time_frame_id`='" + this.timeFrame.get(timeFrame) + "' WHERE `loan_id` = '" + loanID + "'");
                    loadLoans();
                    JOptionPane.showMessageDialog(this, "Loan Update Successfuly", "Warning", JOptionPane.WARNING_MESSAGE);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_button20ActionPerformed

    private void button21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button21ActionPerformed
        int row = jTable2.getSelectedRow();

        try {
            String id = jLabel48.getText();

            MySQL.executeIUD("DELETE FROM `loan` WHERE `loan_id`='" + id + "'");
            loadLoans();
            JOptionPane.showMessageDialog(this, "Loan Delete Success", "Warning", JOptionPane.WARNING_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button21ActionPerformed

    private void button19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button19ActionPerformed

    }//GEN-LAST:event_button19ActionPerformed

    private void button22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button22ActionPerformed

    }//GEN-LAST:event_button22ActionPerformed

    private void textField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField10KeyReleased
        int row = jTable2.getSelectedRow();

        String payment = textField10.getText();
    }//GEN-LAST:event_textField10KeyReleased

    private void button36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button36ActionPerformed
        try {
            String path = "src//reports//AccountManagement.jasper";

            HashMap<String, Object> params1 = new HashMap<>();

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable2.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, params1, dataSource);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button36ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        jTextArea1.setEnabled(true);
        jLabel47.setEnabled(true);
        jLabel38.setEnabled(false);
        button19.setEnabled(true);
        button20.setEnabled(true);
        button21.setEnabled(true);
        button24.setEnabled(false);
        button23.setEnabled(false);
        jLabel44.setEnabled(true);
        textField10.setEnabled(true);
        button22.setEnabled(true);
        jLabel55.setEnabled(false);
        jLabel46.setEnabled(false);

        int row = jTable2.getSelectedRow();

        String id = String.valueOf(jTable2.getValueAt(row, 0));
        jLabel48.setText(id);

        String loanType = String.valueOf(jTable2.getValueAt(row, 5));
        jComboBox4.setSelectedItem(loanType);

        String interestRate = String.valueOf(jTable2.getValueAt(row, 6));
        jComboBox1.setSelectedItem(interestRate);

        String amount = String.valueOf(jTable2.getValueAt(row, 1));
        textField9.setText(amount);

        String timeFrame = String.valueOf(jTable2.getValueAt(row, 7));
        jComboBox5.setSelectedItem(timeFrame);

        String date = String.valueOf(jTable2.getValueAt(row, 2));
        jLabel51.setText(date);

        String empID = String.valueOf(jTable2.getValueAt(row, 3));
        jLabel55.setText(empID);

        String empName = String.valueOf(jTable2.getValueAt(row, 4));
        jLabel46.setText(empName);
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button19;
    private com.nex.cms.components.Button button20;
    private com.nex.cms.components.Button button21;
    private com.nex.cms.components.Button button22;
    private com.nex.cms.components.Button button23;
    private com.nex.cms.components.Button button24;
    private com.nex.cms.components.Button button36;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private com.nex.cms.components.TextField textField10;
    private com.nex.cms.components.TextField textField9;
    // End of variables declaration//GEN-END:variables
}
