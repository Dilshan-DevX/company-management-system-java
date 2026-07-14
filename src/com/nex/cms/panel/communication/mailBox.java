package com.nex.cms.panel.communication;

import com.nex.cms.connection.MySQL;
import static com.nex.cms.frame.login.logger;
import com.nex.cms.model.User;
import com.nex.cms.model.tableAlign;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author xDhanu
 */
public class mailBox extends javax.swing.JPanel {

    private int selectedFileId = -1;
    private String selectedFileName = "";

    public mailBox() {
        initComponents();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        jTable1.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(204, 255, 255));
        jTable1.getTableHeader().setForeground(new Color(0, 0, 0));
        jTable1.setRowHeight(30);
        tableAlign.tableCellAlignment(jTable1, 0);

        downloadButton.setEnabled(false);

        jLabel7.setText(User.getFirst_name() + " " + User.getLast_name());
        jLabel6.setText(User.getEmail());
        jLabel8.setText(User.getEmployeedep());

        loadPropertyProducts();
    }

    private void loadPropertyProducts() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `internal_com`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            tableAlign.tableCellAlignment(jTable1, 0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("subject"));
                vector.add(resultSet.getString("desc"));
                vector.add(resultSet.getString("file_name"));
                vector.add(resultSet.getString("employee_employee_id"));
                vector.add(resultSet.getString("uploaded_time"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        selectedFile = new javax.swing.JLabel();
        downloadButton = new com.nex.cms.components.Button();
        button1 = new com.nex.cms.components.Button();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("Mail box");

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setText("Devicion :");

        jLabel8.setText("Devicion Name");

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setText("Name");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-user-30.png"))); // NOI18N

        jLabel6.setText("Email");

        jSeparator1.setForeground(new java.awt.Color(0, 204, 255));
        jSeparator1.setMaximumSize(new java.awt.Dimension(32767, 5));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 255));
        jLabel2.setText("Mail box");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mail ID", "Subject", "Description", "File Name", "Employee ID", "Uploaded Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        selectedFile.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        selectedFile.setText("File Name");

        downloadButton.setBackground(new java.awt.Color(204, 255, 204));
        downloadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-download-26.png"))); // NOI18N
        downloadButton.setText("  Download");
        downloadButton.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        downloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadButtonActionPerformed(evt);
            }
        });

        button1.setBackground(new java.awt.Color(204, 204, 255));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source Image/icons8-print-30.png"))); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(selectedFile, javax.swing.GroupLayout.PREFERRED_SIZE, 927, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(downloadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(507, 507, 507)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selectedFile, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(downloadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(9, 9, 9)))
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = jTable1.getSelectedRow();
                    selectedFileId = Integer.parseInt(jTable1.getValueAt(row, 0).toString());
                    selectedFileName = jTable1.getValueAt(row, 3).toString();
                    selectedFile.setText("Selected: " + selectedFileName);
                    downloadButton.setEnabled(true);

                }
            }
        });

    }//GEN-LAST:event_jTable1MouseClicked

    private void downloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadButtonActionPerformed

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(selectedFileName));
        int result = chooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = chooser.getSelectedFile();

            try {
                String sql = "SELECT `file_data` FROM `internal_com` WHERE id = ?";
                ResultSet rs = MySQL.executeSearch(sql, selectedFileId);

                if (rs.next()) {
                    InputStream input = rs.getBinaryStream("file_data");
                    FileOutputStream fos = new FileOutputStream(fileToSave);

                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = input.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                    }

                    input.close();
                    fos.close();
                    JOptionPane.showMessageDialog(null, "File downloaded to: " + fileToSave.getAbsolutePath());
                } else {
                    JOptionPane.showMessageDialog(null, "File not found in the database.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Download failed: " + ex.getMessage());
            }
        }

        jTable1.clearSelection();
        selectedFile.setText("File Name");

    }//GEN-LAST:event_downloadButtonActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed

        try {

            if (User.getEmployeeId() != null) {
                String path = "src/reports/com_mailReport.jasper";

                LocalDateTime datetime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String fdt = datetime.format(formatter);

                HashMap<String, Object> parameter = new HashMap<>();
                parameter.put("Parameter1", fdt);

                JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
                JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameter, dataSource);
                JasperViewer.viewReport(jasperPrint, false);
                
            } else {
                JOptionPane.showMessageDialog(this, "Youdo not have permission to access this. Please login and verify your identity", "System Error Message",
                    JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/Source Image/icons8-error.gif"));
            }

        } catch (JRException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print com_mailReport fail JRException", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Print com_mailReport fail unexpected exceptions", e);
        }

    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nex.cms.components.Button button1;
    private com.nex.cms.components.Button downloadButton;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel selectedFile;
    // End of variables declaration//GEN-END:variables
}
