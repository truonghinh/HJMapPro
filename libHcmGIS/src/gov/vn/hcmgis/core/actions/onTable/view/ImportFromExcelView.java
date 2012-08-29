/**
 * 
 */
package gov.vn.hcmgis.core.actions.onTable.view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTable;

/**
 * @author HT
 *
 */
public class ImportFromExcelView extends JPanel 
{
	private JTextField textField;
	private final JButton btnNewButton = new JButton("...");
	private JTable tblExcel;
	private JTable tblCsdl;
	private DefaultTableModel mdlExcel;
	private DefaultTableModel mdlCsdl;
	
	public ImportFromExcelView() {
		mdlExcel=new DefaultTableModel();
		mdlCsdl=new DefaultTableModel();
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(41, 31, 276, 20);
		add(textField);
		textField.setColumns(10);
		btnNewButton.setBounds(327, 30, 46, 23);
		add(btnNewButton);
		
		JLabel lblFile = new JLabel("File:");
		lblFile.setBounds(10, 34, 46, 14);
		add(lblFile);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "C\u1ED9t trong file Excel", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 71, 207, 196);
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		tblExcel = new JTable(mdlExcel);
		tblExcel.setShowGrid(false);
		scrollPane.setViewportView(tblExcel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "C\u1ED9t trong CSDL", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(227, 71, 213, 196);
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		
		tblCsdl = new JTable(mdlCsdl);
		scrollPane_1.setViewportView(tblCsdl);
		
		JButton btnNhp = new JButton("Nhập");
		btnNhp.setBounds(383, 30, 57, 23);
		add(btnNhp);
	}
	
	public void NotifyColumnsInExcel(String[] columns)
	{
		
	}
}
