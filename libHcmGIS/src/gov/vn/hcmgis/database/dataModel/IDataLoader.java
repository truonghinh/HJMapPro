/**
 * 
 */
package gov.vn.hcmgis.database.dataModel;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JComboBox;

/**
 * @author HT
 *
 */
public interface IDataLoader 
{
	public void LoadData(String key,String sql);
	public void UpdateData(String key,String sql,HashMap<Integer,Entry<Integer,Object>> values);
	public void DeleteData(String key,String sql);
	public void SetReceiver(IDataReceiver receiver);
	public void UpdateSpatial(String key,String sql);
}
