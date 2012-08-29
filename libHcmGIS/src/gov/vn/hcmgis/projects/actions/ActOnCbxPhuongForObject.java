/**
 * 
 */
package gov.vn.hcmgis.projects.actions;

import gov.vn.hcmgis.projects.gui.ILocationUpdateView;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JComboBox;

/**
 * @author HT
 *
 */
public class ActOnCbxPhuongForObject implements Action {
	private ILocationUpdateView _hook;
	
	public ActOnCbxPhuongForObject()
	{
		this(null);
	}
	
	public ActOnCbxPhuongForObject(ILocationUpdateView hook)
	{
		_hook=hook;
	}
	
	public void SetHook(ILocationUpdateView hook)
	{
		_hook=hook;
	}

	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JComboBox cbx=(JComboBox)arg0.getSource();
		if(!(cbx.getItemCount()>0))
		{
			return;
		}
		_hook.ReadyLoadObject(cbx.getSelectedItem());
	}

	/* (non-Javadoc)
	 * @see javax.swing.Action#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.swing.Action#getValue(java.lang.String)
	 */
	@Override
	public Object getValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.Action#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see javax.swing.Action#putValue(java.lang.String, java.lang.Object)
	 */
	@Override
	public void putValue(String arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.swing.Action#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	@Override
	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.swing.Action#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean arg0) {
		// TODO Auto-generated method stub

	}

}
