package com.nex.cms.panel;

import com.raven.chart.ModelChart;
import com.nex.cms.connection.MySQL;
import java.awt.Color;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import static com.nex.cms.frame.login.logger;
import mainPanel.scrollbar.ScrollBarCustom;

public class DashBord extends javax.swing.JPanel {

    public DashBord() {
        initComponents();
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.setHorizontalScrollBar(new ScrollBarCustom());
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setViewportBorder(null);
        setOpaque(false);
        init();
        gioChart1();
        giochart2();
        databox();
    }

    public void init() {
        // Define Bar Chart Legends (For Income)
        barChart.addLegend("Total Income", new Color(12, 84, 175), new Color(0, 108, 247));
        barChart.addLegend("Income Summary", new Color(5, 125, 0), new Color(95, 209, 69));
        barChart.addLegend("Cost", new Color(186, 37, 37), new Color(241, 100, 120));

        // Define Line Chart Legends (For GRN)
        lineChart.addLegend("Total GRN", new Color(54, 4, 143), new Color(104, 49, 200));
        lineChart.addLegend("GRN Summary", new Color(5, 125, 0), new Color(95, 209, 69));
        lineChart.addLegend("Cost", new Color(186, 37, 37), new Color(241, 100, 120));

        String incomeQuery = "SELECT DATE_FORMAT(date, '%M') AS month, SUM(paid_amount) AS total_income "
                + "FROM invoice WHERE YEAR(date) = YEAR(CURDATE()) "
                + "GROUP BY month ORDER BY MIN(date)";

        String grnQuery = "SELECT DATE_FORMAT(date, '%M') AS month, SUM(WePaidAmount) AS total_grn "
                + "FROM grn WHERE YEAR(date) = YEAR(CURDATE()) "
                + "GROUP BY month ORDER BY MIN(date)";

        Map<String, Double> incomeData = new HashMap<>();
        Map<String, Double> grnData = new HashMap<>();

        try {

            ResultSet rsIncome = MySQL.executeSearch(incomeQuery);
            ResultSet rsGRN = MySQL.executeSearch(grnQuery);

            while (rsIncome.next()) {
                incomeData.put(rsIncome.getString("month"), rsIncome.getDouble("total_income"));
            }

            while (rsGRN.next()) {
                grnData.put(rsGRN.getString("month"), rsGRN.getDouble("total_grn"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "bar chart and line chart load fail", e);
        }

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        for (String month : months) {
            double income = incomeData.getOrDefault(month, 0.0);
            double grn = grnData.getOrDefault(month, 0.0);
            double incomeSummary = income * 0.9; // Example: Assuming summary is 90% of total
            double grnSummary = grn * 0.9; // Example: Assuming summary is 90% of total
            double cost = grn; // Assuming cost = GRN amount

            barChart.addData(new ModelChart(month, new double[]{income, incomeSummary, cost}));

            lineChart.addData(new ModelChart(month, new double[]{grn, grnSummary, cost}));
        }

        barChart.start();
        lineChart.start();
    }

    private void giochart2() {
        String totalGrnQuery = "SELECT SUM(WePaidAmount) AS total_grn FROM grn WHERE YEAR(date) = YEAR(CURDATE());";

        double totalGrn = 0;

        try {
            ResultSet resultSet = MySQL.executeSearch(totalGrnQuery);
            if (resultSet.next()) {
                totalGrn = resultSet.getDouble("total_grn");

                if (resultSet.wasNull()) {
                    totalGrn = 0;
                }
            }
            TotalGRNLable.setText("Rs. : " + String.valueOf(totalGrn));
        } catch (Exception e) {
            System.err.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
            logger.log(Level.WARNING, "gio chart2 load fail", e);
        }

        double targetAmount = 100000; // Set your target value here
        double percentage = (totalGrn / targetAmount) * 100;

        int percentageInt = (int) Math.round(percentage);

        gaugeChart2.setValueWithAnimation(percentageInt);

    }

    private void gioChart1() {

        String totalInvoiceQuery = "SELECT SUM(paid_amount) AS total_invoice FROM invoice WHERE YEAR(date) = YEAR(CURDATE())";

        double totalInvoice = 0;

        try {
            ResultSet resultSet = MySQL.executeSearch(totalInvoiceQuery);
            if (resultSet.next()) {
                totalInvoice = resultSet.getDouble("total_invoice");
                if (resultSet.wasNull()) {
                    totalInvoice = 0;
                }
            }

            TotalIncomeLable.setText("Rs. : " + String.valueOf(totalInvoice));

        } catch (Exception e) {
            System.err.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
            logger.log(Level.WARNING, "gio chart1 load fail", e);
        }

        double targetAmount = 100000; // Set your target value here
        double percentage = (totalInvoice / targetAmount) * 100;

        int percentageInt = (int) Math.round(percentage);

        gaugeChart1.setValueWithAnimation(percentageInt);
    }

    private void databox() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT COUNT(*) AS company_count FROM company");
            while (resultSet.next()) {

                String count = resultSet.getString("company_count");
                CompanyLabel.setText(count);
            }

            ResultSet resultSet1 = MySQL.executeSearch("SELECT COUNT(*) AS employee_count FROM employee;");
            while (resultSet1.next()) {

                String count = resultSet1.getString("employee_count");
                EmployeeLable.setText(count);
            }

            ResultSet resultSet2 = MySQL.executeSearch("SELECT COUNT(*) AS customer_count FROM customer;");
            while (resultSet2.next()) {

                String count = resultSet2.getString("customer_count");
                CustomerLabel.setText(count);
            }

            ResultSet resultSet3 = MySQL.executeSearch("SELECT COUNT(*) AS supplier_count FROM suplier;");
            while (resultSet3.next()) {

                String count = resultSet3.getString("supplier_count");
                SupplierLabel.setText(count);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "databox load fail", e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        panelShadow1 = new mainPanel.PanelShadow();
        gaugeChart1 = new com.raven.chart.GaugeChart();
        jLabel2 = new javax.swing.JLabel();
        TotalIncomeLable = new javax.swing.JLabel();
        panelShadow2 = new mainPanel.PanelShadow();
        gaugeChart2 = new com.raven.chart.GaugeChart();
        jLabel3 = new javax.swing.JLabel();
        TotalGRNLable = new javax.swing.JLabel();
        panelShadow3 = new mainPanel.PanelShadow();
        lineChart = new com.raven.chart.LineChart();
        panelShadow4 = new mainPanel.PanelShadow();
        barChart = new com.raven.chart.Chart();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelShadow5 = new mainPanel.PanelShadow();
        jPanel1 = new javax.swing.JPanel();
        EmployeeLable = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        CustomerLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        SupplierLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        CompanyLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        body.setBackground(new java.awt.Color(255, 255, 255));
        body.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        gaugeChart1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gaugeChart1.setColor1(new java.awt.Color(153, 255, 153));
        gaugeChart1.setColor2(new java.awt.Color(255, 0, 0));
        gaugeChart1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Total Income");

        TotalIncomeLable.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        TotalIncomeLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TotalIncomeLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gaugeChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gaugeChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TotalIncomeLable, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        gaugeChart2.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gaugeChart2.setColor1(new java.awt.Color(153, 255, 153));
        gaugeChart2.setColor2(new java.awt.Color(0, 0, 204));
        gaugeChart2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Total GRN");

        TotalGRNLable.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        TotalGRNLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
        panelShadow2.setLayout(panelShadow2Layout);
        panelShadow2Layout.setHorizontalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gaugeChart2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(TotalGRNLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gaugeChart2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TotalGRNLable, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelShadow3Layout = new javax.swing.GroupLayout(panelShadow3);
        panelShadow3.setLayout(panelShadow3Layout);
        panelShadow3Layout.setHorizontalGroup(
            panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lineChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        panelShadow3Layout.setVerticalGroup(
            panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow3Layout.createSequentialGroup()
                .addComponent(lineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelShadow4Layout = new javax.swing.GroupLayout(panelShadow4);
        panelShadow4.setLayout(panelShadow4Layout);
        panelShadow4Layout.setHorizontalGroup(
            panelShadow4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(barChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        panelShadow4Layout.setVerticalGroup(
            panelShadow4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel1.setText("Goods Receive Note Record");

        jLabel4.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel4.setText("Invoice Record");

        jPanel1.setBackground(new java.awt.Color(255, 255, 102));

        EmployeeLable.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        EmployeeLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EmployeeLable.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EmployeeLable, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EmployeeLable, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(153, 255, 153));

        CustomerLabel.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        CustomerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CustomerLabel.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CustomerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CustomerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(51, 255, 255));

        SupplierLabel.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        SupplierLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SupplierLabel.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SupplierLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SupplierLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 204, 204));

        CompanyLabel.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        CompanyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CompanyLabel.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CompanyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CompanyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Employees");

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Customers");

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Suppliers");

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Companys");

        javax.swing.GroupLayout panelShadow5Layout = new javax.swing.GroupLayout(panelShadow5);
        panelShadow5.setLayout(panelShadow5Layout);
        panelShadow5Layout.setHorizontalGroup(
            panelShadow5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelShadow5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelShadow5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        panelShadow5Layout.setVerticalGroup(
            panelShadow5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelShadow5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelShadow4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelShadow5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelShadow5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelShadow3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelShadow4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        body.add(jPanel2, java.awt.BorderLayout.CENTER);

        jScrollPane1.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1155, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CompanyLabel;
    private javax.swing.JLabel CustomerLabel;
    private javax.swing.JLabel EmployeeLable;
    private javax.swing.JLabel SupplierLabel;
    private javax.swing.JLabel TotalGRNLable;
    private javax.swing.JLabel TotalIncomeLable;
    private com.raven.chart.Chart barChart;
    private javax.swing.JPanel body;
    private com.raven.chart.GaugeChart gaugeChart1;
    private com.raven.chart.GaugeChart gaugeChart2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private com.raven.chart.LineChart lineChart;
    private mainPanel.PanelShadow panelShadow1;
    private mainPanel.PanelShadow panelShadow2;
    private mainPanel.PanelShadow panelShadow3;
    private mainPanel.PanelShadow panelShadow4;
    private mainPanel.PanelShadow panelShadow5;
    // End of variables declaration//GEN-END:variables
}
