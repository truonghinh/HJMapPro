/***********************************************************************
 * Module:  IUserInfo.java
 * Author:  hdk
 * Purpose: Defines the Interface IUserInfo
 ***********************************************************************/

package gov.vn.hcmgis.connection;

import java.util.*;

/** @pdOid 6f933450-df9c-4e81-bfb7-508042a3c4e2 */
public interface IUserInfo {
   /** @pdOid 4e079ae7-e36a-4cc7-bd6f-8a6b560525a7 */
   String GetName();
   /** @param name
    * @pdOid d8d7bfb9-6ded-485c-be02-13b0511b1122 */
   void SetName(String name);
   /** @pdOid 4c7c834a-6124-4185-aeb2-b283ffc348af */
   String GetPass();
   /** @pdOid ee92e15b-bab2-4942-ad2e-d2c0648c354c */
   String GetPassEncrypted();
   /** @param pass
    * @pdOid 17c32014-044c-4518-abe3-ce463a890311 */
   void SetPass(String pass);

}
