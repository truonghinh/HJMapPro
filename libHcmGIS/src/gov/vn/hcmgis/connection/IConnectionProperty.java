/***********************************************************************
 * Module:  IConnectionProperty.java
 * Author:  hdk
 * Purpose: Defines the Interface IConnectionProperty
 ***********************************************************************/

package gov.vn.hcmgis.connection;

import java.util.*;

/** @pdOid 1f7ed062-7d69-4415-84bb-5b261bfe5bac */
public interface IConnectionProperty {
   /** @pdOid 37167e45-ff58-4931-9d75-054c21ad1bcb */
   String GetConnectionName();
   /** @param name
    * @pdOid fe00fe95-3e05-49de-955f-61b997a96a67 */
   void SetConnectionName(String name);
   /** @pdOid a8e663ff-6877-4409-8a2c-cec6064b41e9 */
   String GetUrl();
   /** @param url
    * @pdOid 57bb41eb-9d9e-4866-aaa6-48e5f9caf044 */
   void SetUrl(String url);
   /** @pdOid f80a01a4-d2b4-43b0-bf92-83900ded0a64 */
   String GetDriver();
   /** @param driver
    * @pdOid c9276a7a-65b6-49c0-a541-196374e82aa3 */
   void SetDriver(String driver);
   /** @pdOid 77260422-9a3a-4749-8271-e9faa8ad0e12 */
   String GetPort();
   /** @param port
    * @pdOid 53766f11-86e4-4d48-a8af-9942b76b0713 */
   void SetPort(String port);
   /** @pdOid 858cd32d-f3d0-4ba0-9687-bf5a8e5ab949 */
   String GetDatabaseName();
   /** @param name
    * @pdOid 2132f08a-7c18-4091-a88a-7306d6670363 */
   void SetDatabaseName(String name);
   /** @pdOid 93a2d745-7f2a-4c4b-b3f7-90b1292d3b9a */
   boolean GetConnected();
   /** @param connected
    * @pdOid 3890e993-7f05-4392-b368-2a10781bf404 */
   boolean SetConnected(boolean connected);
   /** @pdOid f1bd3605-c305-44b3-894e-370e67445afa */
   IUserInfo GetUserInfo();
   /** @param user
    * @pdOid 33b1fb0a-0ffa-4597-a468-fefe6f24ad55 */
   void SetUserInfo(IUserInfo user);
   String GetIp();
   void SetIp(String ip);

}
