/**
 * 
 */
package gov.vn.hcmgis.database.view;

import javax.swing.JTable;

import gov.vn.hcmgis.gui.IGui;
import gov.vn.hcmgis.projects.gui.ILocationUpdateView;

/**
 * @author HT
 *
 */
public interface IObjectDetailView extends IGui {
	public void ReadyLoadDetail(Object arg);
	public void ReadySaveDetail();
	public void ReadyAddDetail();
	public void ReadyUpdateDetail();
	public void ReadyDeleteDetail();
	public void Cancel();
	public void SetHook(ILocationUpdateView hook);
	public ILocationUpdateView GetHook();
	
	public void SetModal(Boolean arg);
	public void SetLocationRelativeTo(JTable table);
	public void SetVisible(Boolean arg);
	public void SetKey(String name,Object value);
	public void SetKeyValue(Object value);
	public String GetKeyName();
	public Object GetKeyValue();
}
