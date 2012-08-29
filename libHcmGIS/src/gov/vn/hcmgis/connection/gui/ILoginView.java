/**
 * 
 */
package gov.vn.hcmgis.connection.gui;

import java.awt.event.ActionEvent;

import gov.vn.hcmgis.connection.IConnectionProperty;
import gov.vn.hcmgis.gui.IGui;

/**
 * @author hdk
 *
 */
public interface ILoginView extends IGui {
	public void GetInfo();
	public void Login();
	public void SetDefaultInfo();
	public void OkAction(ActionEvent arg);
	public IConnectionProperty GetConnectionProperty();
	public void ReceiveChekingResult(int arg);
	public void SetConnectionProperty(IConnectionProperty connPro);
	public void ClearParams();
	public void LoginInTest();
}
