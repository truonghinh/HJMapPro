/**
 * 
 */
package gov.vn.hcmgis.database.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author HT
 *
 */
public interface IGTable {
	public void SetIsReOderingAllow(Boolean arg);
	public void SetEnable(Boolean arg);
	public void AddMouseListener(MouseListener listener);
	public void RemoveMouseListener(MouseListener listener);
	public void OnMouseClick(MouseEvent arg0);
	public void SetDetailView(IObjectDetailView detailView);
}
