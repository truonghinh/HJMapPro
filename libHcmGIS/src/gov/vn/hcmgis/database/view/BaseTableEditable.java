/**
 * 
 */
package gov.vn.hcmgis.database.view;

import java.sql.ResultSet;

import gov.vn.hcmgis.core.adapters.DefaultMouseEventOnTable;
import gov.vn.hcmgis.core.adapters.MouseEventOnTable;
import gov.vn.hcmgis.database.dataModel.IDataReceiver;

import javax.swing.JTable;

/**
 * @author hdk
 *
 */
public abstract class BaseTableEditable extends BaseTableReadOnly implements IGTable,ITableEditor,IDataReceiver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	public BaseTableEditable() 
	{
		super();
	}

	
	/* (non-Javadoc)
	 * @see javax.swing.JTable#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void Receive(String key, ResultSet result) {
		// TODO Auto-generated method stub
		
	}
	
	

}
