package com.nex.cms.panel.Inventory;

import com.nex.cms.connection.MySQL;
import com.nex.cms.frame.inventory.SupplierRegistration;
import com.nex.cms.frame.inventory.fstock;
import static com.nex.cms.frame.login.logger;
import com.nex.cms.model.User;
import com.nex.cms.model.grnItem;
import com.nex.cms.model.tableAlign;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class GRN extends javax.swing.JPanel {

    HashMap<String, grnItem> grnItemMap = new HashMap<>();

    public GRN() {
        initComponents();

        jTable1.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(204, 255, 255));
        jTable1.getTableHeader().setForeground(new Color(0, 0, 0));
        jTable1.setRowHeight(30);
         tableAlign.tableCellAlignment(jTable1, 0);

        jLabel5.setText(User.getFirst_name() + " " + User.getLast_name());
        jLabel6.setText(User.getEmail());
        jLabel3.setText(User.getEmployeedep());

        generateGRNId();
        loadGRNItem();
        total();
    }

    private void total() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT SUM(amount) AS total_amount FROM inventory_account;");
            while (resultSet.next()) {
                double sum = resultSet.getDouble("total_amount");
                jLabel32.setText("Total: " + sum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateGRNId() {
        long GRNId = System.currentTimeMillis();
        textField1.setText(String.valueOf(GRNId));
        textField1.setEditable(false);
    }

    public JLabel getjLabel9() { //Supplier Name
        return jLabel9;
    }

    public JLabel getjLabel10() { //Supplier ID
        return jLabel10;
    }

    public JLabel getjLabel27() { //Supplier Mobile
        return jLabel27;
    }

    public JLabel getjLabel11() { //Supplier Email
        return jLabel11;
    }

    public JLabel getjLabel13() { // PRODUCT ID
        return jLabel13;
    }

    public JLabel getjLabel14() { // PRODUCT BRAND
        return jLabel14;
    }

    public JLabel getjLabel15() { // PRODUCT NAME
        return jLabel15;
    }

    public JFormattedTextField getjFormattedTextField3() { // grabFocus
        return jFormattedTextField3;
    }

    private void resetGRN() {
        jLabel9.setText("Name");
        jLabel10.setText("ID");
        jLabel27.setText("Mobile");
        jLabel11.setText("Email");
        jLabel13.setText("ID");
        jLabel14.setText("Brand");
        jLabel15.setText("Name");
        jFormattedTextField3.setText("0");
        jFormattedTextField1.setText("0");
        jFormattedTextField2.setText("0");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jLabel25.setText("0.00");
        jLabel23.setText("0.00");
        jFormattedTextField4.setText("");
        generateGRNId();
        jTable1.clearSelection();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
    }

    private void loadGRNItem() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        tableAlign.tableCellAlignment(jTable1, 0);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        double total = 0;

        for (grnItem GRNItem : grnItemMap.values()) {
            Vector<String> vector = new Vector<>();
            vector.add(GRNItem.getProductId());
            vector.add(GRNItem.getBrandName());
            vector.add(GRNItem.getProductName());
            vector.add(String.valueOf(GRNItem.getQty()));
            vector.add(String.valueOf(GRNItem.getBuyingPrice()));
            vector.add(String.valueOf(GRNItem.getSellingPrice()));
            vector.add(format.format(GRNItem.getMfd()));
            vector.add(format.format(GRNItem.getExp()));

            double itemTotal = GRNItem.getQty() * GRNItem.getBuyingPrice();

            total += itemTotal;
            vector.add(String.valueOf(itemTotal));

            model.addRow(vector);

        }

        jLabel25.setText(String.valueOf(total));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        textField1 = new com.nex.cms.components.TextField();
        button1 = new com.nex.cms.components.Button();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        button2 = new com.nex.cms.components.Button();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jSeparator5 = new javax.swing.JSeparator();
        button3 = new com.nex.cms.components.Button();
        button4 = new com.nex.cms.components.Button();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        button5 = new com.nex.cms.components.Button();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        button6 = new com.nex.cms.components.Button();
        jLabel35 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(975, 630));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("Goods Received Note");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel2.setText("Devicion :");

        jLabel3.setText("Devicion Name");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-user-30.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setText("Name");

        jLabel6.setText("Email");

        jSeparator1.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator1.setMaximumSize(new java.awt.Dimension(32767, 5));

        jLabel7.setText("GRN No");

        textField1.setForeground(new java.awt.Color(0, 0, 0));
        textField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        textField1.setShadowColor(new java.awt.Color(51, 204, 255));

        button1.setBackground(new java.awt.Color(204, 255, 255));
        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setText("Select Supplier");
        button1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button1.setRippleColor(new java.awt.Color(153, 204, 255));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-supplier-48.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jLabel9.setText("Name");

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel10.setText("ID");

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel11.setText("Email");

        jSeparator2.setForeground(new java.awt.Color(51, 204, 255));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        button2.setBackground(new java.awt.Color(204, 255, 255));
        button2.setForeground(new java.awt.Color(0, 0, 0));
        button2.setText("Select Product");
        button2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button2.setRippleColor(new java.awt.Color(153, 204, 255));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-product-48.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel13.setText("ID");

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel14.setText("Brand");

        jLabel15.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel15.setText("Name");

        jSeparator3.setForeground(new java.awt.Color(51, 204, 255));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel16.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel16.setText("Buying Price");

        jLabel17.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel17.setText("Single Piece");

        jSeparator4.setForeground(new java.awt.Color(51, 204, 255));

        jFormattedTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));
        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField1.setText("0");
        jFormattedTextField1.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel19.setText("Selling Price");

        jFormattedTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));
        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField2.setText("0");
        jFormattedTextField2.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel18.setText("Manufact Date");

        jDateChooser1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel20.setText("Expire Date");

        jDateChooser2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));
        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jDateChooser2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel21.setText("Quantity");

        jFormattedTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));
        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField3.setText("0");
        jFormattedTextField3.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jFormattedTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField3ActionPerformed(evt);
            }
        });

        jSeparator5.setForeground(new java.awt.Color(51, 204, 255));

        button3.setBackground(new java.awt.Color(153, 255, 153));
        button3.setForeground(new java.awt.Color(0, 0, 0));
        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-add-16.png"))); // NOI18N
        button3.setText("   Add to GRN");
        button3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button3.setRippleColor(new java.awt.Color(153, 204, 255));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-clear-30.png"))); // NOI18N
        button4.setRippleColor(new java.awt.Color(153, 204, 255));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        jSeparator6.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator6.setMaximumSize(new java.awt.Dimension(32767, 5));

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Brand", "Name", "Quantity", "Buying Price", "Selling Price", "MFD", "EXP", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
            jTable1.getColumnModel().getColumn(8).setResizable(false);
        }

        button5.setBackground(new java.awt.Color(255, 255, 204));
        button5.setForeground(new java.awt.Color(0, 0, 0));
        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-save-as-24.png"))); // NOI18N
        button5.setText("   Save");
        button5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        button5.setRippleColor(new java.awt.Color(153, 204, 255));
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        jFormattedTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 255)));
        jFormattedTextField4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField4.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jFormattedTextField4.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jFormattedTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextField4KeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel22.setText("Payment");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("0.00");

        jLabel24.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel24.setText("Balance");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 102, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("0.00");

        jLabel26.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel26.setText("Total");

        jSeparator7.setForeground(new java.awt.Color(51, 204, 255));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator8.setForeground(new java.awt.Color(51, 204, 255));
        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel27.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel27.setText("Mobile");

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-phone-16.png"))); // NOI18N

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-email-16.png"))); // NOI18N

        button6.setBackground(new java.awt.Color(204, 204, 255));
        button6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 51, 51));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Total Capital");

        jLabel32.setBackground(new java.awt.Color(226, 73, 73));
        jLabel32.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("0.00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                                .addGap(291, 291, 291))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(156, 156, 156)
                                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(130, 130, 130)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel28)
                                            .addComponent(jLabel29))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextField3))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(148, 148, 148))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator4)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFormattedTextField2)
                                            .addComponent(jFormattedTextField1))
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(28, 28, 28))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel19))
                                        .addGap(142, 142, 142)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel20)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(button4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(28, 28, 28)))
                        .addGap(1, 1, 1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator3)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(9, 9, 9)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel16)
                                                    .addComponent(jLabel18))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel20))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel14))
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21)
                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(12, 12, 12))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel22)))
                                .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(button6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        SupplierRegistration sr = new SupplierRegistration();
        sr.setVisible(true);
        sr.setGrn(this);
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        fstock fstock = new fstock();
        fstock.setVisible(true);
        fstock.setGrn(this);
    }//GEN-LAST:event_button2ActionPerformed

    private void jFormattedTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField3ActionPerformed
        jFormattedTextField1.grabFocus();
    }//GEN-LAST:event_jFormattedTextField3ActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        jFormattedTextField2.grabFocus();
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed

        String qty = jFormattedTextField3.getText();
        String buyingPrice = jFormattedTextField1.getText();
        String sellingPrice = jFormattedTextField2.getText();
        Date mfd = jDateChooser1.getDate();
        Date exp = jDateChooser2.getDate();

        double quantity = Double.parseDouble(qty);
        double buyPrice = Double.parseDouble(buyingPrice);
        double sellPrice = Double.parseDouble(sellingPrice);

        if ("Name".equals(jLabel9.getText())) {
            JOptionPane.showMessageDialog(this, "Select Supplier first", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if ("ID".equals(jLabel13.getText())) {
            JOptionPane.showMessageDialog(this, "Please Select Product", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (qty.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter quantity", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Quantity must be a positive number", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (buyPrice <= 0) {
            JOptionPane.showMessageDialog(this, "Buying price must be greater than zero", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (sellingPrice.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter selling price", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (sellPrice <= 0) {
            JOptionPane.showMessageDialog(this, "Selling price must be greater than zero", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (sellPrice <= buyPrice) {
            JOptionPane.showMessageDialog(this, "Selling price must be greater than buying price", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (mfd == null) {
            JOptionPane.showMessageDialog(this, "Please select a manufacturing date", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (exp == null) {
            JOptionPane.showMessageDialog(this, "Please select an expiry date", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!exp.after(mfd)) {
            JOptionPane.showMessageDialog(this, "Expiry date must be after the manufacturing date", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            grnItem GRNItem = new grnItem();
            GRNItem.setProductId(jLabel13.getText());
            GRNItem.setBrandName(jLabel14.getText());
            GRNItem.setProductName(jLabel15.getText());
            GRNItem.setQty(Double.parseDouble(qty));
            GRNItem.setBuyingPrice(Double.parseDouble(buyingPrice));
            GRNItem.setSellingPrice(Double.parseDouble(sellingPrice));
            GRNItem.setMfd(mfd);
            GRNItem.setExp(exp);

            if (grnItemMap.get(jLabel13.getText()) == null) {
                grnItemMap.put(jLabel13.getText(), GRNItem);
                loadGRNItem();

            } else {

                grnItem found = grnItemMap.get(jLabel13.getText());

                if (found.getExp().compareTo(exp) == 0
                        && found.getMfd().compareTo(mfd) == 0
                        && found.getBuyingPrice() == Double.parseDouble(buyingPrice)
                        && found.getSellingPrice() == Double.parseDouble(sellingPrice)) {

                    found.setQty(found.getQty() + Double.parseDouble(qty));
                    loadGRNItem();

                } else {

                    JOptionPane.showMessageDialog(this, "GRN Item alredy exsists with deffrent date's and prices Or Not Valid", "Error", JOptionPane.ERROR_MESSAGE);

                }
            }

        }


    }//GEN-LAST:event_button3ActionPerformed

    private void jFormattedTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField4KeyReleased

        String total = jLabel25.getText();
        String payment = jFormattedTextField4.getText();

        if (payment.isEmpty()) {
            payment = "0";
        } else if (!payment.matches("^(0|[1-9]\\d*)?(\\.\\d+)?(?<=\\d)$")) {
            jLabel23.setText("INVALID INPUT");
            jLabel23.setForeground(Color.ORANGE);
        } else {
            double balance = Double.parseDouble(payment) - Double.parseDouble(total);
            jLabel23.setText(String.valueOf(balance));
            jLabel23.setForeground(Color.RED);

        }


    }//GEN-LAST:event_jFormattedTextField4KeyReleased

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed

        try {

            String GRNNO = textField1.getText();
            String SupplierId = jLabel10.getText();
            String EmployeId = User.getEmployeeId();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String paidAmount = jFormattedTextField4.getText();
            double amount = Double.valueOf(paidAmount);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            try {

                ResultSet resultSet2 = null;

                resultSet2 = MySQL.executeSearch("SELECT SUM(amount) AS amount FROM `inventory_account`");

                while (resultSet2.next()) {
                    double sum = resultSet2.getDouble("amount");
                    double calculate = sum -= amount;
                    double processCal = calculate;

                    MySQL.executeIUD("UPDATE `inventory_account` SET `amount`='" + calculate + "'");

                    jLabel32.setText("Total: " + processCal);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            MySQL.executeIUD("INSERT INTO `grn` VALUES ('" + GRNNO + "','" + dateTime + "','" + paidAmount + "','" + EmployeId + "','" + SupplierId + "')");
            resetGRN();
            for (grnItem GRNItem : grnItemMap.values()) {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `stock` WHERE "
                        + "`product_id`= '" + GRNItem.getProductId() + "' AND "
                        + "`sellingprice` = '" + GRNItem.getSellingPrice() + "' AND "
                        + "`mfd`= '" + sdf.format(GRNItem.getMfd()) + "' AND"
                        + "`exp`= '" + sdf.format(GRNItem.getExp()) + "'");

                String sid = "";

                if (resultSet.next()) {
                    // stock allredy added
                    sid = resultSet.getString("id");

                    String currentQty = resultSet.getString("qty");
                    String updatedQuantity = String.valueOf(Double.parseDouble(currentQty) + GRNItem.getQty());

                    MySQL.executeIUD("UPDATE `stock` SET `qty` = '" + updatedQuantity + "' WHERE `id` = '" + sid + "'");
                    resetGRN();

                } else {
                    // new stock
                    MySQL.executeIUD("INSERT INTO `stock` (`product_id`, `qty`, `sellingprice`, `mfd`, `exp`,`department_department_id`)"
                            + "VALUES('" + GRNItem.getProductId() + "',"
                            + "'" + GRNItem.getQty() + "',"
                            + "'" + GRNItem.getSellingPrice() + "',"
                            + "'" + sdf.format(GRNItem.getMfd()) + "',"
                            + "'" + sdf.format(GRNItem.getExp()) + "',"
                            + "'1')");
                    resetGRN();

                    ResultSet resultSet2 = MySQL.executeSearch("SELECT * FROM `stock` WHERE "
                            + "`product_id`= '" + GRNItem.getProductId() + "' AND "
                            + "`sellingprice` = '" + GRNItem.getSellingPrice() + "' AND "
                            + "`mfd`= '" + sdf.format(GRNItem.getMfd()) + "' AND"
                            + "`exp`= '" + sdf.format(GRNItem.getExp()) + "'");

                    if (resultSet2.next()) {

                        sid = resultSet2.getString("id");

                    }

                }

                MySQL.executeIUD("INSERT INTO `grn_item`(`WeBuyingPrice`,`qty`,`stock_id`,`grn_id`) "
                        + "VALUES('" + GRNItem.getBuyingPrice() + "','" + GRNItem.getQty() + "','" + sid + "','" + GRNNO + "')");

                JOptionPane.showMessageDialog(this, "Your goods received note Successfuly added", "information", JOptionPane.INFORMATION_MESSAGE);

                resetGRN();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_button5ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        resetGRN();
    }//GEN-LAST:event_button4ActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        try {
            String path = "src/reports/inv_grn.jasper";

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
            logger.log(Level.WARNING, "Print GRN fail JRException", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print GRN fail unexpected exceptions", e);
        }
    }//GEN-LAST:event_button6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button2;
    private com.nex.cms.components.Button button3;
    private com.nex.cms.components.Button button4;
    private com.nex.cms.components.Button button5;
    private com.nex.cms.components.Button button6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    // End of variables declaration//GEN-END:variables
}
