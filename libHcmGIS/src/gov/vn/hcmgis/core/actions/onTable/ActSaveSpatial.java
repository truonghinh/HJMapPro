package gov.vn.hcmgis.core.actions.onTable;

import gov.vn.hcmgis.projects.gui.ILocationUpdateView;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

public class ActSaveSpatial extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ILocationUpdateView _hook;
	public ActSaveSpatial(ILocationUpdateView hook, String text, ImageIcon icon)
	{
		super(text,icon);
		_hook=hook;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
