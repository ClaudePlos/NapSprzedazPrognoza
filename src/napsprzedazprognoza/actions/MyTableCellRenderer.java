/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napsprzedazprognoza.actions;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author k.skowronski
 */
public class MyTableCellRenderer extends DefaultTableCellRenderer {
    
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setOpaque(isSelected);
            TableModel model = table.getModel();
         
                TableModel myModel = (TableModel) model;
                
                    
  
                    if (!isSelected) {
                        setBackground(Color.WHITE);
                        setOpaque(true);
                    } 
                    
                    if ( row % 2 == 1)
                    {
                        setBackground( new Color(0xE8F2FE));
                    }
                
            
            return this;
        }
    
}
