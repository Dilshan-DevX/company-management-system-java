
package com.nex.cms.panel.marketing;

import com.nex.cms.connection.MySQL;
import static com.nex.cms.frame.login.logger;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author User
 */
public class advertising extends javax.swing.JPanel {

    public static HashMap<String, String> productMap = new HashMap<>();

    public advertising() {
        initComponents();
        loadPromotion();
        loadProduct();

        jTable1.getTableHeader().setBackground(new Color(0, 204, 255));
        jTable1.getTableHeader().setForeground(Color.black);
    }

    private void loadPromotion() {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `promotion`"
                    + "INNER JOIN `product` ON `promotion`.`product_id` = `product`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("pId"));
                vector.add(resultSet.getString("pName"));
                vector.add(resultSet.getString("product.name"));
                vector.add(resultSet.getString("discount"));
                vector.add(resultSet.getString("des"));
                vector.add(resultSet.getString("sDate"));
                vector.add(resultSet.getString("eDate"));

                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadItem() {
        try {
            String query = "SELECT * FROM `promotion`"
                    + "INNER JOIN `product` ON `promotion`.`product_id` = `product`.`id` ";

            List<String> conditions = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            if (jDateChooser4.getDate() != null) {
                String sDate = format.format(jDateChooser4.getDate());
                conditions.add("`promotion`.`sDate` > '" + sDate + "'");
            }
            if (jDateChooser5.getDate() != null) {
                String eDate = format.format(jDateChooser5.getDate());
                conditions.add("`promotion`.`eDate` < '" + eDate + "'");
            }

            if (!conditions.isEmpty()) {
                query += "WHERE " + String.join(" AND ", conditions) + " ";
            }

            String sort = String.valueOf(jComboBox1.getSelectedItem());
            if (sort.equals("Promotion Name ASC")) {
                query += "ORDER BY `promotion`.`pName` ASC";
            } else if (sort.equals("Promotion Name DESC")) {
                query += "ORDER BY `promotion`.`pName` DESC";

            } else if (sort.equals("Product Name ASC")) {
                query += "ORDER BY `product`.`name` ASC";
            } else if (sort.equals("Product Name DESC")) {
                query += "ORDER BY `product`.`name` DESC";

            } else if (sort.equals("Discount  ASC")) {
                query += "ORDER BY `promotion`.`discount` ASC";
            } else if (sort.equals("Discount DESC")) {
                query += "ORDER BY `promotion`.`discount` DESC";

            }

            // Execute query
            ResultSet resultSet = MySQL.executeSearch(query);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            // Populate table
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("pId"));
                vector.add(resultSet.getString("pName"));
                vector.add(resultSet.getString("product.name"));
                vector.add(resultSet.getString("discount"));
                vector.add(resultSet.getString("des"));
                vector.add(resultSet.getString("sDate"));
                vector.add(resultSet.getString("eDate"));
                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadProduct() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `product`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {
                vector.add(rs.getString("name"));
                productMap.put(rs.getString("name"), rs.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        button2 = new com.nex.cms.components.Button();
        button4 = new com.nex.cms.components.Button();
        textField5 = new com.nex.cms.components.TextField();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        button7 = new com.nex.cms.components.Button();
        button3 = new com.nex.cms.components.Button();
        jSeparator2 = new javax.swing.JSeparator();
        textField4 = new com.nex.cms.components.TextField();
        jLabel9 = new javax.swing.JLabel();
        textField1 = new com.nex.cms.components.TextField();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        button5 = new com.nex.cms.components.Button();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        button1 = new com.nex.cms.components.Button();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Promations Name");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Discount");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Date");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("to");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Product");

        jDateChooser2.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jDateChooser2.setForeground(new java.awt.Color(255, 255, 255));
        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jDateChooser2.setFont(new java.awt.Font("Poppins", 0, 11)); // NOI18N

        button2.setText("UPDATE");
        button2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button2.setRippleColor(new java.awt.Color(51, 204, 0));
        button2.setShadowColor(new java.awt.Color(51, 204, 0));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button4.setText("ADD");
        button4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button4.setRippleColor(new java.awt.Color(0, 204, 204));
        button4.setShadowColor(new java.awt.Color(0, 0, 255));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        textField5.setShadowColor(new java.awt.Color(0, 204, 204));
        textField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField5ActionPerformed(evt);
            }
        });

        jDateChooser3.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jDateChooser3.setForeground(new java.awt.Color(255, 255, 255));
        jDateChooser3.setDateFormatString("yyyy-MM-dd");
        jDateChooser3.setFont(new java.awt.Font("Poppins", 0, 11)); // NOI18N

        button7.setText("DELETE");
        button7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button7.setRippleColor(new java.awt.Color(255, 51, 51));
        button7.setShadowColor(new java.awt.Color(255, 51, 51));
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });

        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-48.png"))); // NOI18N
        button3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button3.setRippleColor(new java.awt.Color(102, 102, 0));
        button3.setShadowColor(new java.awt.Color(0, 0, 0));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        jSeparator2.setBackground(new java.awt.Color(204, 255, 153));
        jSeparator2.setForeground(new java.awt.Color(0, 204, 255));

        textField4.setShadowColor(new java.awt.Color(0, 204, 204));
        textField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField4ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Description");

        textField1.setShadowColor(new java.awt.Color(0, 204, 204));
        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel10.setText("%");

        jComboBox2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2)
                        .addGap(8, 8, 8)
                        .addComponent(button4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(34, 34, 34)
                                .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(23, 23, 23))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(44, 44, 44)
                        .addComponent(button7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(108, 108, 108))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(132, 132, 132)
                    .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addGap(679, 679, 679)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4))
                            .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel5)))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBox2))))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)))
                .addGap(18, 18, 18))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(51, 51, 51)
                    .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(147, 147, 147)))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(102, 51, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("sort");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Promotion Name ASC", "Promotion Name DESC", "Product Name ASC", "Product Name DESC", "Discount  ASC", "DiscountDESC" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jDateChooser4.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jDateChooser4.setForeground(new java.awt.Color(255, 255, 255));
        jDateChooser4.setDateFormatString("yyyy-MM-dd");
        jDateChooser4.setFont(new java.awt.Font("Poppins", 0, 11)); // NOI18N

        jDateChooser5.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jDateChooser5.setForeground(new java.awt.Color(255, 255, 255));
        jDateChooser5.setDateFormatString("yyyy-MM-dd");
        jDateChooser5.setFont(new java.awt.Font("Poppins", 0, 11)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Promations Name", "Product", "Discount ( % )", "Description", " Start Date", "End Date"
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
        jScrollPane1.setViewportView(jTable1);

        button5.setText("Search");
        button5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button5.setRippleColor(new java.awt.Color(204, 204, 0));
        button5.setShadowColor(new java.awt.Color(204, 204, 0));
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("To");

        jSeparator1.setBackground(new java.awt.Color(204, 255, 153));
        jSeparator1.setForeground(new java.awt.Color(0, 204, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(261, 261, 261)
                        .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8)
                        .addGap(42, 42, 42)
                        .addComponent(jDateChooser5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(56, 56, 56)
                        .addComponent(button5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(71, 71, 71))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addGap(36, 36, 36))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBox1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jDateChooser5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8))
                        .addGap(4, 4, 4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(5, 5, 5)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        button1.setText("Print");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button1.setRippleColor(new java.awt.Color(102, 0, 102));
        button1.setShadowColor(new java.awt.Color(102, 0, 102));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 981, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jLabel1.setText("Promations / Discounts");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(356, 356, 356)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        try {
            int row = jTable1.getSelectedRow();

            String pId = String.valueOf(jTable1.getValueAt(row, 0));

            String pName = textField1.getText().trim();
            String product = String.valueOf(jComboBox2.getSelectedItem());
            String discount = String.valueOf(textField4.getText());
            String des = textField5.getText().trim();
            Date sDate = jDateChooser2.getDate();
            Date eDate = jDateChooser3.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (pName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your Promotion Name", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (product.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your Product Name", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (discount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your Discount ", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (des.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your Description", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (sDate == null) {
                JOptionPane.showMessageDialog(this, "Please enter your Start Date", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (eDate == null) {
                JOptionPane.showMessageDialog(this, "Please select an End Date", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            MySQL.executeIUD("UPDATE `promotion` SET `pName` = '" + pName + "', `product_id`='" + productMap.get(product) + "',`discount`='" + discount + "', `des` = '" + des + "',"
                    + "`sDate`='" + sdf.format(sDate) + "', `eDate`= '" + sdf.format(eDate) + "' WHERE `pId` = '" + pId + "'");

            loadPromotion();
            reset();

        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, " Promotion Update Error", e);

        }
    }//GEN-LAST:event_button2ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed

        try {
            String pName = textField1.getText().trim();
            String product = String.valueOf(jComboBox2.getSelectedItem());
            String discount = String.valueOf(textField4.getText());
            String des = textField5.getText().trim();
            Date sDate = jDateChooser2.getDate();
            Date eDate = jDateChooser3.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (pName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your Promotion Name", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (product.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please enter your Product Name", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (discount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your Discount ", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (des.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your Description", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (sDate == null) {
                JOptionPane.showMessageDialog(this, "Please enter your Start Date", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (eDate == null) {
                JOptionPane.showMessageDialog(this, "Please select an End Date", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            MySQL.executeIUD("INSERT INTO `promotion` (`pName`, `product_id` ,`discount` ,`des`, `sDate`, `eDate`) "
                    + "VALUES( '" + pName + "','" + productMap.get(product) + "','" + discount + "','" + des + "','" + sdf.format(sDate) + "',"
                    + "'" + sdf.format(eDate) + "')");

            loadPromotion();

            reset();
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, " Promotion Add Error", e);

        }
    }//GEN-LAST:event_button4ActionPerformed

    private void textField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField5ActionPerformed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a Promotion / Discount to delete", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String pId = String.valueOf(jTable1.getValueAt(row, 0));
                MySQL.executeIUD("DELETE FROM `promotion` WHERE `pId` ='" + pId + "' ");

                loadPromotion();
                reset();
                JOptionPane.showMessageDialog(this, " Socailcampaion successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }//GEN-LAST:event_button7ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        reset();
    }//GEN-LAST:event_button3ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        loadItem();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();

        String pName = String.valueOf(jTable1.getValueAt(row, 1));
        textField1.setText(pName);

        String product_id = String.valueOf(jTable1.getValueAt(row, 2));
        jComboBox2.setSelectedItem(product_id);

        String discount = String.valueOf(jTable1.getValueAt(row, 3));
        textField4.setText(discount);

        String des = String.valueOf(jTable1.getValueAt(row, 4));
        textField5.setText(des);

        try {
            String sDate = String.valueOf(jTable1.getValueAt(row, 5));
            java.util.Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
            Calendar startCal = Calendar.getInstance();
            startCal.setTime(startDate);
            jDateChooser2.setCalendar(startCal);

            String eDate = String.valueOf(jTable1.getValueAt(row, 6));
            java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(eDate);
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(endDate);
            jDateChooser3.setCalendar(endCal);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jTable1MouseClicked

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        loadItem();
    }//GEN-LAST:event_button5ActionPerformed

    private void textField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField4ActionPerformed

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed

       try {
            String path = "src/reports/mar_promo.jasper";

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
            logger.log(Level.WARNING, "Print mar_promo fail JRException", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print mar_promo unexpected exceptions", e);
        }
       
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button2;
    private com.nex.cms.components.Button button3;
    private com.nex.cms.components.Button button4;
    private com.nex.cms.components.Button button5;
    private com.nex.cms.components.Button button7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private com.nex.cms.components.TextField textField1;
    private com.nex.cms.components.TextField textField4;
    private com.nex.cms.components.TextField textField5;
    // End of variables declaration//GEN-END:variables

    private void reset() {

        textField5.setText("");
        jComboBox2.setSelectedIndex(0);
        textField4.setText("");
        textField1.setText("");

        jDateChooser2.setCalendar(null);
        jDateChooser3.setCalendar(null);
        jDateChooser4.setCalendar(null);
        jDateChooser5.setCalendar(null);

    }

}
