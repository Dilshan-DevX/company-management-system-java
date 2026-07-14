package com.nex.cms.model;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public class tableAlign {

    public static void tableCellAlignment(JTable table, int alignment) {
        
        //Jtable table = table where cells need to be centered;
        //int alignment = 0;
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }
}
