/***********************************************************************
 * Module:  IConnectionPool.java
 * Author:  hdk
 * Purpose: Defines the Interface IConnectionPool
 ***********************************************************************/

package gov.vn.hcmgis.connection;

import java.sql.Connection;
import java.util.*;

/** @pdOid a660c786-6c40-46fe-8b53-4c0508a89180 */
public interface IConnectionPool {
   /** @pdOid 47cb61f9-fc1c-40a1-9a49-16f9b37de298 */
   Connection GetConnection();
   /** @param connPro
    * @pdOid 48cce66c-0852-4093-84bb-c21e30eee168 */
   void setup(IConnectionProperty connPro);
   /** @param minPerPart
    * @pdOid 37743821-4933-4856-93c5-2e8c5414b871 */
   void setMinConnection(int minPerPart);
   /** @param maxPerPart
    * @pdOid fb6f9881-e011-467f-ae0c-6fd8bf0c5104 */
   void setMaxConnection(int maxPerPart);
   /** @param partition
    * @pdOid 6c2eb2d7-a391-4e10-99d0-985cc7e6b894 */
   void setPartCount(int partition);
   /** @param connPro 
    * @param minConn 
    * @param maxConn 
    * @param partCount
    * @pdOid 5af4a0c4-5a8e-43aa-b7a1-bad186cbcf80 */
   void setup(IConnectionProperty connPro, int minConn, int maxConn, int partCount);
   void Reset();
   void CloseConnection();
   void ShutdownPool();
}
