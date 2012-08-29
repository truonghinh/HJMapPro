/**
 * 
 */
package gov.vn.hcmgis.core.actions.onTable;

import gov.vn.hcmgis.database.view.IGTable;
import gov.vn.hcmgis.database.view.IObjectDetailView;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JTable;

/**
 * @author HT
 *
 */
public class ActShowDetail extends AbstractAction {

	protected IObjectDetailView _detailView;
	protected JTable _hook;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActShowDetail(JTable hook, String text, ImageIcon icon)
	{
		this(hook,null,text,icon);
	}
	
	public ActShowDetail(JTable hook,IObjectDetailView view,String text,ImageIcon icon)
	{
		super(text,icon);
		_hook=hook;
		_detailView=view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("line 31 ActShowDetail actionperformed");
		int row=_hook.getSelectedRow();
		_detailView.SetKeyValue(_hook.getValueAt(row, 1));
		if(_detailView==null)
		{
			return;
		}
		System.out.println(String.format("line 51 ActShowDetail actionperformed, primarykey=%s, value=%s",_detailView.GetKeyName(),_detailView.GetKeyValue()));
		_detailView.ReadyLoadDetail(null);
		_detailView.ShowDialog();
	}
	
	public void SetView(IObjectDetailView view)
	{
		this._detailView=view;
	}
	
}
