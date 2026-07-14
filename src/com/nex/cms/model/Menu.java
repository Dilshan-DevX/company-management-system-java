package com.nex.cms.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class Menu extends JComponent {

    public MenuEvent getEvent() {
        return event;
    }

    public void setEvent(MenuEvent event) {
        this.event = event;
    }

    private MenuEvent event;
    private MigLayout layout;

    private String[][] menuItems = new String[][]{
        {"Dashbord"},
        {"Human Resourse Department", "Employee", "Employees Leave", "Employee Traning", "Attendance", "Payments"},
        {"Accounting Department", "Account Management", "Transections", "Budgets", "Loans"},
        {"Customer Relationship", "Customer Registration"},
        {"Marketing Department", "Digital Marketing", "Market Research & Analysis", "Advertising & Promotions", "Social Media Management"},
        {"Inventory Management", "Stock Management", "Property Management", "Goods Received Note", "Invoice"},
        {"Communication Tools", "External Tools", "Internal Tools","Mail Box"},
        {"Reporting & Analytics", "Report", "Attendance","Events"}};

    public Menu() {
        initComponents();

    }

    public void initComponents() {
        layout = new MigLayout("wrap 1, fillx, gapy 0, inset 2", "fill");
        setLayout(layout);
        setOpaque(true);

        for (int i = 0; i < menuItems.length; i++) {
            addMenu(menuItems[i][0], i);

        }

    }

    private Icon getIcon(int index) {
        URL url = getClass().getResource("/Source Image/" + index + ".png");
        if (url != null) {
            return new ImageIcon(url);
        } else {
            return null;
        }
    }

    private void addMenu(String menuName, int index) {
        int length = menuItems[index].length;
        MenuItem item = new MenuItem(menuName, index, length > 1);
        Icon icon = getIcon(index);
        if (icon != null) {
            item.setIcon(icon);
        }
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (length > 1) {
                    if (!item.isSelected()) {
                        item.setSelected(true);
                        addSubMenu(item, index, length, getComponentZOrder(item));
                    } else {
                        //  Hide menu
                        hideMenu(item, index);
                        item.setSelected(false);
                    }
                } else {
                    if (event != null) {
                        event.selected(index, 0);
                    }
                }
            }
        });
        add(item);
        revalidate();
        repaint();
    }

    private void addSubMenu(MenuItem item, int index, int length, int indexZOrder) {
        JPanel panel1 = new JPanel(new MigLayout("wrap 1, fillx, inset 0, gapy 0", "fill"));
        panel1.setName(index + "");
        panel1.setOpaque(false);
        for (int i = 1; i < length; i++) {
            MenuItem subItem = new MenuItem(menuItems[index][i], i, false);
            subItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (event != null) {
                        event.selected(index, subItem.getIndex());
                    }
                }

            });
            subItem.initSubMenu(i, length);
            panel1.add(subItem);
        }

        add(panel1, "h 0!", indexZOrder + 1);
        revalidate();
        repaint();
        MenuAnimation.showMenu(panel1, item, layout, true);
    }

    private void hideMenu(MenuItem item, int index) {
        for (Component com : getComponents()) {
            if (com instanceof JPanel && com.getName() != null && com.getName().equals(index + "")) {
                com.setName(null);
                MenuAnimation.showMenu(com, item, layout, false);
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setColor(new Color(255, 255, 255));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        super.paintComponent(grphcs);
    }
}
