/**
 * 
 */
package gov.vn.hcmgis.database.dataModel;

import java.sql.ResultSet;

/**
 * @author HT
 *
 */
public interface IDataReceiver {
	public void Receive(String key,ResultSet result);
	public void Receive(String key,int result);
}
