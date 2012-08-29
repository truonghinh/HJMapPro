/**
 * 
 */
package gov.vn.hcmgis.database.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author HT
 *
 */
public class CellRenderer extends DefaultTableCellRenderer {
	public Component getTableCellRendererComponent(
			JTable table, Object value, boolean isSelected, boolean hasFocus,int row,int col)
			{
    	setHorizontalAlignment(SwingConstants.CENTER);
			if(table.getModel().getValueAt(table.convertRowIndexToModel(row),7) == null) {
				setForeground(Color.red);
			}else {
				setForeground(null);
			}
			if (isSelected)
				setForeground(Color.red);
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

			} 
}
