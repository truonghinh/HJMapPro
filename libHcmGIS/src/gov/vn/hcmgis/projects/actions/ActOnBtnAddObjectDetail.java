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
public class ActOnBtnAddObjectDetail extends AbstractAction {

//	protected ActPool4btn _pool;
	protected AbstractObjectDetailView _hook;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ActOnBtnAddObjectDetail(AbstractObjectDetailView hook,String title,ImageIcon icon)
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
		this._hook.ReadyAddDetail();
	}

}
