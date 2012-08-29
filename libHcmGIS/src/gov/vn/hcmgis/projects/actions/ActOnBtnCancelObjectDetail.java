/**
 * 
 */
package gov.vn.hcmgis.projects.actions;

import gov.vn.hcmgis.projects.gui.AbstractObjectDetailView;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * @author HT
 *
 */
public class ActOnBtnCancelObjectDetail extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected AbstractObjectDetailView _hook;
	
	public ActOnBtnCancelObjectDetail(AbstractObjectDetailView hook,String title,ImageIcon icon)
	{
		super(title,icon);
		this._hook=hook;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this._hook.Cancel();
	}

}
