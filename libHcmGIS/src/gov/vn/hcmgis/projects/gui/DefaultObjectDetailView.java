/**
 * 
 */
package gov.vn.hcmgis.projects.gui;

import javax.swing.JTable;

import gov.vn.hcmgis.database.dataModel.IDataReceiver;

import gov.vn.hcmgis.database.view.IObjectDetailView;

/**
 * @author HT
 *
 */
public class DefaultObjectDetailView extends AbstractObjectDetailView implements
		IObjectDetailView, IDataReceiver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefaultObjectDetailView()
	{
		super();
	}
	
	public DefaultObjectDetailView(ILocationUpdateView hook)
	{
		super(hook);
	}

}
