/**
 * 
 */
package gov.vn.hcmgis.connection;

import java.sql.Connection;

/**
 * @author hdk
 *
 */
public interface IConnectionManager {
	Connection GetSingleConnection(IConnectionProperty connPro);
	void CloseConnection(Connection conn);
	void ShutdownPool();
	Connection GetCurrentConnection();
}
