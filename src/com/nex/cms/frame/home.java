package com.nex.cms.frame;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.nex.cms.model.MenuEvent;
import com.nex.cms.model.User;
import com.nex.cms.panel.DashBord;
import com.nex.cms.panel.Inventory.GRN;
import com.nex.cms.panel.Inventory.Invoice;
import com.nex.cms.panel.Inventory.property;
import com.nex.cms.panel.Inventory.stock;
import com.nex.cms.panel.accounting.Accounts_Managament;
import com.nex.cms.panel.accounting.Budgets_Management;
import com.nex.cms.panel.accounting.Loans_Management;
import com.nex.cms.panel.accounting.Transactions_Management;
import com.nex.cms.panel.communication.Internal;
import com.nex.cms.panel.communication.extranal;
import com.nex.cms.panel.communication.mailBox;
import com.nex.cms.panel.cusutomer.customerReg;
import com.nex.cms.panel.hrm.attendance;
import com.nex.cms.panel.hrm.employee;
import com.nex.cms.panel.hrm.employeeLeave;
import com.nex.cms.panel.hrm.employeeTraning;
import com.nex.cms.panel.hrm.payrollMng;
import com.nex.cms.panel.marketing.advertising;
import com.nex.cms.panel.marketing.digitalMarketing;
import com.nex.cms.panel.marketing.marketReaserch;
import com.nex.cms.panel.marketing.socialMediaManagement;
import com.nex.cms.panel.reporting.events;
import com.nex.cms.panel.reporting.report;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class home extends javax.swing.JFrame {
//String email, String fName, String lName, String username

    public home() {

        initComponents();
        showPanel(new DashBord());
        menu1.setEvent(new MenuEvent() {
            @Override
            public void selected(int index, int subIndex) {

                // Validate subIndex (e.g., ensuring it's non-negative or within a certain range)
                switch (index) {

                    default ->
                        showPanel(new DashBord());

                    case 0 ->
                        showPanel(new DashBord());

                    case 1 -> {
                        if ("Inventory Management".equals(User.getEmployeedep()) || "HR Management".equals(User.getEmployeedep())) {
                            if (subIndex == 1) {
                                showPanel(new employee());
                            } else if (subIndex == 2) {
                                showPanel(new employeeLeave());
                            } else if (subIndex == 3) {
                                showPanel(new employeeTraning());
                            } else if (subIndex == 4) {
                                showPanel(new attendance());
                            } else if (subIndex == 5) {
                                showPanel(new payrollMng());
                            } else {
                                showPanel(new DashBord());
                            }
                        } else {
                            showPanel(new DashBord());
                            JOptionPane.showMessageDialog(new home(), "Youdo not have permission to access this Section. Please login and verify your identity", "System Error Message",
                                    JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Source Image/icons8-error.gif"));
                        }
                    }

                    case 2 -> {
                        if ("Inventory Management".equals(User.getEmployeedep()) || "Account Management".equals(User.getEmployeedep())) {
                            if (subIndex == 1) {
                                showPanel(new Accounts_Managament());
                            } else if (subIndex == 2) {
                                showPanel(new Transactions_Management());
                            } else if (subIndex == 3) {
                                showPanel(new Budgets_Management());
                            } else if (subIndex == 4) {
                                showPanel(new Loans_Management());
                            } else {
                                showPanel(new DashBord());
                            }
                        } else {
                            showPanel(new DashBord());
                            JOptionPane.showMessageDialog(new home(), "Youdo not have permission to access this Section. Please login and verify your identity", "System Error Message",
                                    JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Source Image/icons8-error.gif"));
                        }

                    }

                    case 3 -> {
                        if ("Inventory Management".equals(User.getEmployeedep()) || "Customer Management".equals(User.getEmployeedep())) {
                            if (subIndex == 1) {
                                showPanel(new customerReg());
                            } else {
                                showPanel(new DashBord());
                            }
                        } else {
                            showPanel(new DashBord());
                            JOptionPane.showMessageDialog(new home(), "Youdo not have permission to access this Section. Please login and verify your identity", "System Error Message",
                                    JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Source Image/icons8-error.gif"));
                        }
                    }

                    case 4 -> {
                        if ("Inventory Management".equals(User.getEmployeedep()) || "Marketing Management".equals(User.getEmployeedep())) {
                            if (subIndex == 1) {
                                showPanel(new digitalMarketing());
                            } else if (subIndex == 2) {
                                showPanel(new marketReaserch());
                            } else if (subIndex == 3) {
                                showPanel(new advertising());
                            } else if (subIndex == 4) {
                                showPanel(new socialMediaManagement());
                            } else {
                                showPanel(new DashBord());
                            }
                        } else {
                            showPanel(new DashBord());
                            JOptionPane.showMessageDialog(new home(), "Youdo not have permission to access this Section. Please login and verify your identity", "System Error Message",
                                    JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Source Image/icons8-error.gif"));
                        }
                    }

                    case 5 -> {
                        if ("Inventory Management".equals(User.getEmployeedep()) || "Property Managemen".equals(User.getEmployeedep())) {
                            if (subIndex == 1) {
                                showPanel(new stock());
                            } else if (subIndex == 2) {
                                showPanel(new property());
                            } else if (subIndex == 3) {
                                showPanel(new GRN());
                            } else if (subIndex == 4) {
                                showPanel(new Invoice());
                            } else {
                                showPanel(new DashBord());
                            }
                        } else {
                            showPanel(new DashBord());
                            JOptionPane.showMessageDialog(new home(), "Youdo not have permission to access this Section. Please login and verify your identity", "System Error Message",
                                    JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Source Image/icons8-error.gif"));
                        }

                    }

                    case 6 -> {
                        if ("Inventory Management".equals(User.getEmployeedep()) || "Communication & Activity".equals(User.getEmployeedep())) {
                            if (subIndex == 1) {
                                showPanel(new extranal());
                            } else if (subIndex == 2) {
                                showPanel(new Internal());
                            } else if (subIndex == 3) {
                                showPanel(new mailBox());
                            } else {
                                showPanel(new DashBord());
                            }
                        } else {
                            showPanel(new DashBord());
                            JOptionPane.showMessageDialog(new home(), "Youdo not have permission to access this Section. Please login and verify your identity", "System Error Message",
                                    JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Source Image/icons8-error.gif"));
                        }
                    }

                    case 7 -> {
                        if ("Inventory Management".equals(User.getEmployeedep()) || "Reporting".equals(User.getEmployeedep())) {
                            if (subIndex == 1) {
                                showPanel(new report());
                            } else if (subIndex == 2) {
                                showPanel(new attendance());
                            }  else if (subIndex == 3) {
                                showPanel(new events());
                            } else {
                                showPanel(new DashBord());
                            }
                        } else {
                            showPanel(new DashBord());
                            JOptionPane.showMessageDialog(new home(), "Youdo not have permission to access this Section. Please login and verify your identity", "System Error Message",
                                    JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Source Image/icons8-error.gif"));
                        }
                    }

                }
            }

        });
    }

    private void showPanel(Component com) {
        Body.removeAll();
        Body.add(com);
        Body.repaint();
        Body.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        scrollPaneWin111 = new com.nex.cms.components.ScrollPaneWin11();
        menu1 = new com.nex.cms.model.Menu();
        Body = new javax.swing.JPanel();
        header1 = new com.nex.cms.panel.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Home");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 255)));

        scrollPaneWin111.setBorder(null);

        menu1.setBackground(new java.awt.Color(255, 255, 255));
        menu1.setMinimumSize(new java.awt.Dimension(270, 393));
        scrollPaneWin111.setViewportView(menu1);

        Body.setMaximumSize(new java.awt.Dimension(971, 626));
        Body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Body, javax.swing.GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Body, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatMacLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Body;
    private com.nex.cms.panel.Header header1;
    private javax.swing.JPanel jPanel5;
    private com.nex.cms.model.Menu menu1;
    private com.nex.cms.components.ScrollPaneWin11 scrollPaneWin111;
    // End of variables declaration//GEN-END:variables
}
