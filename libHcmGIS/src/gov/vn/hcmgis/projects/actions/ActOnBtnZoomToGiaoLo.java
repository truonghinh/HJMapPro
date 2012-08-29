package gov.vn.hcmgis.projects.actions;

import gov.vn.hcmgis.projects.gui.ILocationUpdateView;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

public class ActOnBtnZoomToGiaoLo implements Action {

	private ILocationUpdateView _hook;
	
	public ActOnBtnZoomToGiaoLo()
	{
		this(null);
	}
	
	public ActOnBtnZoomToGiaoLo(ILocationUpdateView hook)
	{
		_hook=hook;
	}
	
	public void SetHook(ILocationUpdateView hook)
	{
		_hook=hook;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		_hook.ReadyZoomToGiaoLo(_hook.GetCbxPhuongSelectedItem(), _hook.GetCbxDuongSelectedItem(),_hook.GetCbxGiaoLoSelectedItem());
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void putValue(String arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnabled(boolean arg0) {
		// TODO Auto-generated method stub

	}

}
