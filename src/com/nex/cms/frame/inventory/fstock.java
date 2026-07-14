
package com.nex.cms.frame.inventory;

import com.nex.cms.connection.MySQL;
import com.nex.cms.model.User;
import com.nex.cms.model.tableAlign;
import com.nex.cms.panel.Inventory.GRN;
import com.nex.cms.panel.Inventory.Invoice;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class fstock extends javax.swing.JFrame {
    
   private GRN grn;

    public void setGrn(GRN grn) {
        this.grn = grn;
    }
    
    private Invoice invoice;

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    

    HashMap<String, String> brandMap = new HashMap<>();

    
    public fstock() {
        initComponents();
        jTable1.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(204, 255, 255));
        jTable1.getTableHeader().setForeground(new Color(0, 0, 0));
        jTable1.setRowHeight(30);
        tableAlign.tableCellAlignment(jTable1, 0);

        jTable2.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 12));
        jTable2.getTableHeader().setOpaque(false);
        jTable2.getTableHeader().setBackground(new Color(204, 255, 255));
        jTable2.getTableHeader().setForeground(new Color(0, 0, 0));
        jTable2.setRowHeight(30);
        tableAlign.tableCellAlignment(jTable2, 0);
        
        jLabel7.setText(User.getFirst_name()+" "+User.getLast_name());
        jLabel8.setText(User.getEmail());

        loadBrand();
        loadProducts();
        loadStock();
    }

    private void loadBrand() {

        try {

            Vector<String> vector = new Vector();
            vector.add("Select");

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `brand`");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                brandMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            jComboBox1.setModel(new DefaultComboBoxModel<>(vector));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadProducts() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `product` INNER JOIN `brand`"
                    + "ON `product`.`brand_id` = `brand`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            tableAlign.tableCellAlignment(jTable1, 0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("product.id"));
                vector.add(resultSet.getString("brand.id"));
                vector.add(resultSet.getString("brand.name"));
                vector.add(resultSet.getString("product.name"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void resetProduct() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        jTable1.clearSelection();
        textField1.grabFocus();
        button2.setEnabled(true);
        loadProducts();
    }

    private void resetStock() {
        jFormattedTextField3.setText("0.00");
        jFormattedTextField4.setText("0.00");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jTable2.clearSelection();
        loadStock();
    }

    private void loadStock() {

        try {

            String query = "SELECT * FROM `stock` INNER JOIN `product`"
                    + "ON `stock`.`product_id` = `product`.`id` "
                    + "INNER JOIN `brand` ON `brand`.`id` = `product`.`brand_id` ";

            int row = jTable1.getSelectedRow();

            if (row != -1) {
                String pid = String.valueOf(jTable1.getValueAt(row, 0));
                query += "WHERE `stock`.`product_id` = '" + pid + "' ";
            }

            if (query.contains("WHERE")) {
                query += "AND ";
            } else {
                query += "WHERE ";
            }

            double min_price = 0;
            double max_price = 0;

            if (!jFormattedTextField3.getText().isEmpty()) {
                min_price = Double.parseDouble(jFormattedTextField3.getText());
            }

            if (!jFormattedTextField4.getText().isEmpty()) {
                max_price = Double.parseDouble(jFormattedTextField4.getText());
            }

            if (min_price > 0 && max_price == 0) {
                query += "`stock`.`sellingprice` > '" + min_price + "' ";
            } else if (min_price == 0 && max_price > 0) {
                query += "`stock`.`sellingprice` < '" + max_price + "' ";
            } else if (min_price > 0 && max_price > 0) {
                query += "`stock`.`sellingprice` > '" + min_price + "' AND `stock`.`sellingprice` <  '" + max_price + "'";
            }

            //exp date sorting
            Date start = null;
            Date end = null;

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            if (jDateChooser1.getDate() != null) {
                start = jDateChooser1.getDate();
                query += "`stock`.`exp` > '" + format.format(start) + "' AND";
            }

            if (jDateChooser2.getDate() != null) {
                end = jDateChooser2.getDate();
                query += "`stock`.`exp` < '" + format.format(end) + "' ";
            }

            String sort = String.valueOf(jComboBox3.getSelectedItem());
            query += "ORDER BY ";
            query = query.replace("WHERE ORDER BY ", "ORDER BY ");
            query = query.replace("AND ORDER BY ", "ORDER BY ");

            if (sort.equals("Stock ID ASC")) {
                query += "`stock`.`id` ASC";
            } else if (sort.equals("Stock ID DESC")) {
                query += "`stock`.`id` DESC";
            } else if (sort.equals("Product ID ASC")) {
                query += "`product`.`id` ASC";
            } else if (sort.equals("Product ID DESC")) {
                query += "`product`.`id` DESC";
            } else if (sort.equals("Brand ASC")) {
                query += "`brand`.`id` ASC";
            } else if (sort.equals("Brand DESC")) {
                query += "`brand`.`id` DESC";
            } else if (sort.equals("Name ASC")) {
                query += "`brand`.`name` ASC";
            } else if (sort.equals("Name DESC")) {
                query += "`brand`.`name` DESC";
            } else if (sort.equals("Selling Price ASC")) {
                query += "`stock`.`sellingprice` ASC";
            } else if (sort.equals("Selling Price DESC")) {
                query += "`stock`.`sellingprice` DESC";
            } else if (sort.equals("Quantity ASC")) {
                query += "`stock`.`qty` ASC";
            } else if (sort.equals("Quantity DESC")) {
                query += "`stock`.`qty` DESC";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            tableAlign.tableCellAlignment(jTable2, 0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("stock.id"));
                vector.add(resultSet.getString("product.id"));
                vector.add(resultSet.getString("brand.name"));
                vector.add(resultSet.getString("product.name"));
                vector.add(resultSet.getString("stock.sellingprice"));
                vector.add(resultSet.getString("qty"));
                vector.add(resultSet.getString("mfd"));
                vector.add(resultSet.getString("exp"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        textField1 = new com.nex.cms.components.TextField();
        jLabel3 = new javax.swing.JLabel();
        textField2 = new com.nex.cms.components.TextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        textField3 = new com.nex.cms.components.TextField();
        button1 = new com.nex.cms.components.Button();
        jSeparator2 = new javax.swing.JSeparator();
        button2 = new com.nex.cms.components.Button();
        button3 = new com.nex.cms.components.Button();
        button4 = new com.nex.cms.components.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        button5 = new com.nex.cms.components.Button();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        button6 = new com.nex.cms.components.Button();
        button7 = new com.nex.cms.components.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("Stock For Sale");

        jSeparator1.setForeground(new java.awt.Color(0, 204, 255));

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel2.setText("Product ID");

        textField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField1.setShadowColor(new java.awt.Color(51, 204, 255));
        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel3.setText("Name");

        textField2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField2.setShadowColor(new java.awt.Color(51, 204, 255));
        textField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel4.setText("Brand");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        textField3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField3.setShadowColor(new java.awt.Color(51, 204, 255));
        textField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField3ActionPerformed(evt);
            }
        });

        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-add-16.png"))); // NOI18N
        button1.setText("  Add");
        button1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button1.setRippleColor(new java.awt.Color(51, 204, 255));
        button1.setShadowColor(new java.awt.Color(153, 204, 255));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 204, 255));

        button2.setBackground(new java.awt.Color(153, 255, 153));
        button2.setForeground(new java.awt.Color(0, 0, 0));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-add-16.png"))); // NOI18N
        button2.setText("   Add Product");
        button2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button2.setRippleColor(new java.awt.Color(51, 204, 255));
        button2.setShadowColor(new java.awt.Color(153, 204, 255));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button3.setBackground(new java.awt.Color(204, 255, 255));
        button3.setForeground(new java.awt.Color(0, 0, 0));
        button3.setText("Update");
        button3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button3.setRippleColor(new java.awt.Color(51, 204, 255));
        button3.setShadowColor(new java.awt.Color(153, 204, 255));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.setForeground(new java.awt.Color(0, 0, 0));
        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-30.png"))); // NOI18N
        button4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button4.setRippleColor(new java.awt.Color(51, 204, 255));
        button4.setShadowColor(new java.awt.Color(153, 204, 255));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Brand ID", "Brand Name", "Product Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-user-30.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setText("Name");

        jLabel8.setText("Email");

        jLabel9.setText("Devicion Name");

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel10.setText("Devicion :");

        jLabel15.setText("Sort By");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stock ID ASC", "Stock ID DESC", "Product ID ASC", "Product ID DESC", "Brand ASC", "Brand DESC", "Name ASC", "Name DESC", "Selling Price ASC", "Selling Price DESC", "Quantity ASC", "Quantity DESC" }));
        jComboBox3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jLabel12.setText("Selling Price");

        jFormattedTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField3.setText("0");
        jFormattedTextField3.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jFormattedTextField3.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jFormattedTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField3ActionPerformed(evt);
            }
        });

        jLabel13.setText("TO");

        jFormattedTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        jFormattedTextField4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField4.setText("0");
        jFormattedTextField4.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jFormattedTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField4ActionPerformed(evt);
            }
        });

        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-search-30 (1).png"))); // NOI18N
        button5.setRippleColor(new java.awt.Color(51, 204, 255));
        button5.setShadowColor(new java.awt.Color(153, 204, 255));
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        jDateChooser1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jLabel14.setText("TO");

        jDateChooser2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        jDateChooser2.setDateFormatString("yyyy-MM-dd");

        jLabel5.setText("EXP");

        button6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-search-30 (1).png"))); // NOI18N
        button6.setRippleColor(new java.awt.Color(51, 204, 255));
        button6.setShadowColor(new java.awt.Color(153, 204, 255));
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        button7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-30.png"))); // NOI18N
        button7.setRippleColor(new java.awt.Color(51, 204, 255));
        button7.setShadowColor(new java.awt.Color(153, 204, 255));
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product ID", "Brand", "Name", "Selling Price", "Quantity", "MFD", "EXP"
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
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(116, 116, 116)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(199, 199, 199)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(209, 209, 209))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(109, 109, 109)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jFormattedTextField3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jFormattedTextField4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(9, 9, 9))))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(button6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(button7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(343, 343, 343)
                                .addComponent(jLabel12))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(343, 343, 343)
                                .addComponent(jLabel13))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(331, 331, 331)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(343, 343, 343)
                                .addComponent(jLabel15)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 982, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField3ActionPerformed

    }//GEN-LAST:event_jFormattedTextField3ActionPerformed

    private void jFormattedTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField4ActionPerformed

    }//GEN-LAST:event_jFormattedTextField4ActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        loadStock();
    }//GEN-LAST:event_button6ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed

        String brand = textField3.getText();

        if (brand.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter brand name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `brand` WHERE `name`='" + brand + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Brand allredy added", "Warning", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    if (jComboBox1.getSelectedIndex() == 0) {
                        MySQL.executeIUD("INSERT INTO `brand`(`name`) VALUES ('" + brand + "')");
                    } else {
                        int showConfirm = JOptionPane.showConfirmDialog(this, "Do you want to update brand?", "Update", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                        if (showConfirm == JOptionPane.YES_OPTION) {
                            MySQL.executeIUD("UPDATE `brand` SET `name`='" + brand + "' WHERE `name` = '" + String.valueOf(jComboBox1.getSelectedItem()) + "'");
                            JOptionPane.showMessageDialog(this, "Brand update", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                    loadBrand();
                    textField3.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed

        String productid = textField1.getText();
        String brand = String.valueOf(jComboBox1.getSelectedItem());
        String name = name = textField2.getText();

        if (productid.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Product ID", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (productid.length() > 10) {
            JOptionPane.showMessageDialog(this, "Product ID must have content lower than 10 characters.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter product name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (name.length() > 45) {
            JOptionPane.showMessageDialog(this, "Product name must have content lower than 45 characters.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (brand.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a brand", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `product` WHERE `id` = '" + productid + "' OR (`name` = '" + name + "' AND `brand_id` = '" + brandMap.get(brand) + "')");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Product already added", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    MySQL.executeIUD("INSERT INTO `product`(`id`,`name`,`brand_id`) VALUES('" + productid + "','" + name + "','" + brandMap.get(brand) + "')");

                    JOptionPane.showMessageDialog(this, "New product added", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadProducts();
                    resetProduct();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_button2ActionPerformed

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        textField2.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    private void textField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField2ActionPerformed
        jComboBox1.grabFocus();
    }//GEN-LAST:event_textField2ActionPerformed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        if (evt.getKeyCode() == 10) {
            textField3.grabFocus();
        }
    }//GEN-LAST:event_jComboBox1KeyPressed

    private void textField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField3ActionPerformed
        button2.grabFocus();
    }//GEN-LAST:event_textField3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        resetProduct();
    }//GEN-LAST:event_button4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int row = jTable1.getSelectedRow();

        textField1.setText(String.valueOf(jTable1.getValueAt(row, 0)));
        textField1.setEditable(false);

        jComboBox1.setSelectedItem(String.valueOf(jTable1.getValueAt(row, 2)));
        textField2.setText(String.valueOf(jTable1.getValueAt(row, 3)));
        button2.setEnabled(false);

        if (evt.getClickCount() == 2) {
            
            if (grn != null) {
                
                grn.getjLabel13().setText(String.valueOf(jTable1.getValueAt(row, 0)));
                grn.getjLabel14().setText(String.valueOf(jTable1.getValueAt(row, 2)));
                grn.getjLabel15().setText(String.valueOf(jTable1.getValueAt(row, 3)));
                this.dispose();
                grn.getjFormattedTextField3().grabFocus();

            }
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed

        String productid = textField1.getText();
        String brand = String.valueOf(jComboBox1.getSelectedItem());
        String name = name = textField2.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter product name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (name.length() > 45) {
            JOptionPane.showMessageDialog(this, "Product name must have content lower than 45 characters.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (brand.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a brand", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `product` WHERE `name` = '" + name + "' AND `brand_id` = '" + brandMap.get(brand) + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Change name or brand, for update", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    MySQL.executeIUD("UPDATE `product` SET `brand_id` = '" + brandMap.get(brand) + "' , `name` ='" + name + "' "
                            + "WHERE `id` = '" + productid + "'");
                    loadProducts();
                    resetProduct();
                    JOptionPane.showMessageDialog(this, "Product Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_button3ActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        loadStock();
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        loadStock();
    }//GEN-LAST:event_button5ActionPerformed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        resetProduct();
        resetStock();
    }//GEN-LAST:event_button7ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        
        int row = jTable2.getSelectedRow();
        
        if(evt.getClickCount() == 2){
            
            if (invoice != null) {
                invoice.getjLabel31().setText(String.valueOf(jTable2.getValueAt(row, 0)));
                invoice.getjLabel16().setText(String.valueOf(jTable2.getValueAt(row, 2)));
                invoice.getjLabel18().setText(String.valueOf(jTable2.getValueAt(row, 3)));
                invoice.getjLabel33().setText(String.valueOf(jTable2.getValueAt(row, 4)));
                invoice.getjLabel22().setText(String.valueOf(jTable2.getValueAt(row, 6)));
                invoice.getjLabel20().setText(String.valueOf(jTable2.getValueAt(row, 7)));
                invoice.getjFormattedTextField1().grabFocus();
                invoice.getjLabel24().setText(String.valueOf(jTable2.getValueAt(row, 5)));
                this.dispose();
            }
        }
        
    }//GEN-LAST:event_jTable2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fstock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fstock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fstock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fstock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fstock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button2;
    private com.nex.cms.components.Button button3;
    private com.nex.cms.components.Button button4;
    private com.nex.cms.components.Button button5;
    private com.nex.cms.components.Button button6;
    private com.nex.cms.components.Button button7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private com.nex.cms.components.TextField textField1;
    private com.nex.cms.components.TextField textField2;
    private com.nex.cms.components.TextField textField3;
    // End of variables declaration//GEN-END:variables
}
