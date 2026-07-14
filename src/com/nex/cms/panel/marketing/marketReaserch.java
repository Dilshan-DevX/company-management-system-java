package com.nex.cms.panel.marketing;

import chart.ModelPolarAreaChart;
import com.nex.cms.connection.MySQL;
import static com.nex.cms.frame.login.logger;

import java.sql.ResultSet;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.InputStream;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

/**
 *
 * @author PIYUMAL
 */
public class marketReaserch extends javax.swing.JPanel {

    public marketReaserch() {

        initComponents();
        loardProduct();
        loardCustomer();
        loardSupplier();
        loardEmployee();
        loardTotIncome();
        loardTodaySelling();
        loardcustomerdata();
        loardProductdata();
        loardBrand();
        loardCompany();

        jTable1.getTableHeader().setBackground(new Color(0, 204, 255));
        jTable1.getTableHeader().setForeground(Color.white);
        jTable1.getTableHeader().setFont(new Font("Poppins", Font.BOLD, 12));

        jTable2.getTableHeader().setBackground(new Color(0, 204, 255));
        jTable2.getTableHeader().setForeground(Color.white);
        jTable2.getTableHeader().setFont(new Font("Poppins", Font.BOLD, 12));

    }

    public void loardProduct() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) AS `rowCount` FROM `product`");
            if (rs.next()) {
                jLabel12.setText(String.valueOf(rs.getInt("rowCount")));
                polarAreaChart2.addItem(new ModelPolarAreaChart(new Color(215, 0, 166), " Products", rs.getInt("rowCount")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loardBrand() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) AS `rowCount` FROM `brand`");
            if (rs.next()) {
                polarAreaChart2.addItem(new ModelPolarAreaChart(new Color(0, 102, 0), "Product Brands", rs.getInt("rowCount")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loardCompany() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) AS `rowCount` FROM `company`");
            if (rs.next()) {
                polarAreaChart2.addItem(new ModelPolarAreaChart(new Color(0, 51, 153), "Companies", rs.getInt("rowCount")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loardCustomer() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) AS `rowCount` FROM `customer`");
            if (rs.next()) {
                jLabel4.setText(String.valueOf(rs.getInt("rowCount")));
                polarAreaChart1.addItem(new ModelPolarAreaChart(new Color(153, 0, 51), "Customers", rs.getInt("rowCount")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loardSupplier() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) AS `rowCount` FROM `suplier`");
            if (rs.next()) {
                jLabel6.setText(String.valueOf(rs.getInt("rowCount")));
                polarAreaChart1.addItem(new ModelPolarAreaChart(new Color(102, 0, 102), "Suppliers", rs.getInt("rowCount")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loardEmployee() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) AS `rowCount` FROM `employee`");
            if (rs.next()) {
                jLabel9.setText(String.valueOf(rs.getInt("rowCount")));
                polarAreaChart1.addItem(new ModelPolarAreaChart(new Color(255, 153, 0), "Employess", rs.getInt("rowCount")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loardTotIncome() {
        double tot = 0;
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `invoice` INNER JOIN `invoice_item` ON "
                    + "`invoice_item`.`invoice_id` = `invoice`.`id` INNER JOIN `stock` ON `stock`.`id` = `invoice_item`.`stock_id` "
                    + "INNER JOIN `product` ON `product`.`id` = `stock`.`product_id`");
            while (rs.next()) {
                double qty = rs.getDouble("invoice_item.qty");
                double price = rs.getDouble("stock.sellingprice");
                double itemPrice = qty * price;
                tot += itemPrice;
            }
            jLabel8.setText(String.valueOf(tot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    double totAmou = 0;

    public void loardTodaySelling() {
        HashMap<Long, Double> totInvoice = new HashMap<>();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `invoice` INNER JOIN `invoice_item` "
                    + "ON `invoice_item`.`invoice_id` = `invoice`.`id` INNER JOIN `stock` "
                    + "ON `stock`.`id` = `invoice_item`.`stock_id` INNER JOIN `product` ON `product`.`id` = `stock`.`product_id` "
                    + "WHERE `date` = '" + date + "'");

            while (rs.next()) {
                double qty = rs.getDouble("invoice_item.qty");
                double price = rs.getDouble("stock.price");
                double itemPrice = qty * price;
                totAmou += itemPrice;
                totInvoice.put(rs.getLong("invoice.id"), rs.getDouble("invoice.paid_amount"));
            }
            jLabel13.setText(String.valueOf(totAmou));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loardcustomerdata() {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `company` ");

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("name"));
                vector.add(resultSet.getString("hotline"));

                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loardProductdata() {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `product`"
                    + "INNER JOIN `brand` ON `product`.`brand_id` = `brand`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("name"));

                vector.add(resultSet.getString("brand.name"));

                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        background3 = new component.Background();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        background2 = new component.Background();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        background1 = new component.Background();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        background6 = new component.Background();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        background8 = new component.Background();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        background7 = new component.Background();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        background9 = new component.Background();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        background10 = new component.Background();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        button1 = new com.nex.cms.components.Button();
        button2 = new com.nex.cms.components.Button();
        jPanel3 = new javax.swing.JPanel();
        polarAreaChart1 = new chart.PolarAreaChart();
        jButton1 = new javax.swing.JButton();
        polarAreaChart2 = new chart.PolarAreaChart();
        jButton3 = new javax.swing.JButton();

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Sales Analyze");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(426, 426, 426)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        background3.setBackground(new java.awt.Color(0, 102, 0));

        jLabel5.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Total Suppliers");

        jLabel6.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("0");

        javax.swing.GroupLayout background3Layout = new javax.swing.GroupLayout(background3);
        background3.setLayout(background3Layout);
        background3Layout.setHorizontalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(background3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        background3Layout.setVerticalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        background2.setBackground(new java.awt.Color(0, 51, 153));

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total Customers");

        jLabel4.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("0");

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(background2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        background1.setBackground(new java.awt.Color(153, 0, 51));

        jLabel10.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Total Employees");

        jLabel9.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("0");

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        background6.setBackground(new java.awt.Color(102, 102, 0));

        jLabel7.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Income");

        jLabel8.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("00");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Rs.");

        javax.swing.GroupLayout background6Layout = new javax.swing.GroupLayout(background6);
        background6.setLayout(background6Layout);
        background6Layout.setHorizontalGroup(
            background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(45, 45, 45))
        );
        background6Layout.setVerticalGroup(
            background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        background8.setBackground(new java.awt.Color(255, 153, 0));

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Total Products");

        jLabel12.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("0");

        javax.swing.GroupLayout background8Layout = new javax.swing.GroupLayout(background8);
        background8.setLayout(background8Layout);
        background8Layout.setHorizontalGroup(
            background8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background8Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );
        background8Layout.setVerticalGroup(
            background8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        background7.setBackground(new java.awt.Color(102, 0, 102));

        jLabel11.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Today Income");

        jLabel13.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("00");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Rs.");

        javax.swing.GroupLayout background7Layout = new javax.swing.GroupLayout(background7);
        background7.setLayout(background7Layout);
        background7Layout.setHorizontalGroup(
            background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background7Layout.createSequentialGroup()
                .addGroup(background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background7Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(background7Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(34, 34, 34)))
                .addGap(0, 0, 0))
        );
        background7Layout.setVerticalGroup(
            background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(30, 30, 30))
        );

        background9.setBackground(new java.awt.Color(204, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Product Name", "Brand"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout background9Layout = new javax.swing.GroupLayout(background9);
        background9.setLayout(background9Layout);
        background9Layout.setHorizontalGroup(
            background9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
        );
        background9Layout.setVerticalGroup(
            background9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        background10.setBackground(new java.awt.Color(204, 255, 255));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Name", "Hotline"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout background10Layout = new javax.swing.GroupLayout(background10);
        background10.setLayout(background10Layout);
        background10Layout.setHorizontalGroup(
            background10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        background10Layout.setVerticalGroup(
            background10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("Available Companies");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setText("Available Product");

        button1.setBackground(new java.awt.Color(204, 204, 255));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(204, 204, 255));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(background8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(background6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(69, 69, 69))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(162, 162, 162))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(background2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(background1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(background3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(background8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(background7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background10, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(background9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("System", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(204, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("Refresh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(polarAreaChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(polarAreaChart2, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(polarAreaChart2, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addComponent(polarAreaChart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48))
        );

        jTabbedPane1.addTab("Chart", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        polarAreaChart1.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        polarAreaChart2.start();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        
        try {
            String path = "src/reports/mar_analizeRepoet2.jasper";

            LocalDateTime datetime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fdt = datetime.format(formatter);

            HashMap<String, Object> parameter = new HashMap<>();
            parameter.put("Parameter1", fdt);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable2.getModel());
            JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameter, dataSource);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print analizeRepoet2 fail JRException", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print analizeRepoet2 unexpected exceptions", e);
        }
        
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        
        try {
            String path = "src/reports/mar_analizeRepoet1.jasper";

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
            logger.log(Level.WARNING, "Print analizeRepoet1 fail JRException", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print analizeRepoet1 unexpected exceptions", e);
        }
        
    }//GEN-LAST:event_button2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Background background1;
    private component.Background background10;
    private component.Background background2;
    private component.Background background3;
    private component.Background background6;
    private component.Background background7;
    private component.Background background8;
    private component.Background background9;
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button button2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private chart.PolarAreaChart polarAreaChart1;
    private chart.PolarAreaChart polarAreaChart2;
    // End of variables declaration//GEN-END:variables

}
