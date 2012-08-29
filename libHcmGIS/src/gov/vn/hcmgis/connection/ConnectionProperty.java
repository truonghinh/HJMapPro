/***********************************************************************
 * Module:  ConnectionProperty.java
 * Author:  hdk
 * Purpose: Defines the Class ConnectionProperty
 ***********************************************************************/

package gov.vn.hcmgis.connection;

import java.util.*;

/** @pdOid fd84eddb-1f0a-451f-a16d-238cbc50a267 */
public class ConnectionProperty implements IConnectionProperty {
	private static ConnectionProperty _meClass;
   /** @pdOid 412dce27-3281-4aef-bdfb-45264243b0e2 */
   private static String _connectionName="";
   /** @pdOid 5f481da3-729d-4d9d-ad4b-e93cd48ac4a0 */
   private static String _url="";
   /** @pdOid 280cd074-6efc-4c3c-9045-b802c506c509 */
   private static boolean _connected=true;
   /** @pdOid 420a32cb-0239-47a4-9c39-a480f36ef04f */
   private static String _driver="";
   /** @pdOid 924d1628-2095-4847-b6ae-07b2cd49a984 */
   private static String _port="";
   /** @pdOid 95158379-a7e6-4e06-9898-a589fb49e3e9 */
   private static String _databaseName="";
   
   private static String _ip="localhost";
   
   /** @pdRoleInfo migr=no name=IUserInfo assc=association5 mult=1..1 type=Aggregation */
   private static IUserInfo _userInfo;
   
   private ConnectionProperty(){}
   
   public static ConnectionProperty CallMe()
   {
	   if(_meClass==null)
	   {
		   _meClass=new ConnectionProperty();
	   }
	   return _meClass;
   }

   //region GetMethod and SetMethod
   @Override
   public String GetConnectionName() {
   	// TODO Auto-generated method stub
   	return _connectionName;
   }

   @Override
   public void SetConnectionName(String name) {
   	// TODO Auto-generated method stub
   	_connectionName=name;
   }

   @Override
   public String GetUrl() {
   	// TODO Auto-generated method stub
	   if(!(_driver=="" || _ip=="" || _databaseName==""))
	   {
		   _url=String.format("jdbc:%s://%s/%s", ClassNameUtils.Postgresql,_ip,_databaseName);
	   }
   	return _url;
   }

   @Override
   public void SetUrl(String url) {
   	// TODO Auto-generated method stub
   	_url=url;
   	
   }

   @Override
   public String GetDriver() {
   	// TODO Auto-generated method stub
   	return _driver;
   }

   @Override
   public void SetDriver(String driver) {
   	// TODO Auto-generated method stub
   	_driver=driver;
   }

   @Override
   public String GetPort() {
   	// TODO Auto-generated method stub
   	return _port;
   }

   @Override
   public void SetPort(String port) {
   	// TODO Auto-generated method stub
   	_port=port;
   }

   @Override
   public String GetDatabaseName() {
   	// TODO Auto-generated method stub
   	return _databaseName;
   }

   @Override
   public void SetDatabaseName(String name) {
   	// TODO Auto-generated method stub
   	_databaseName=name;
   }

   @Override
   public boolean GetConnected() {
   	// TODO Auto-generated method stub
   	return _connected;
   }

   @Override
   public boolean SetConnected(boolean connected) {
   	// TODO Auto-generated method stub
   	_connected=connected;
   	return true;
   }

   @Override
   public IUserInfo GetUserInfo() {
   	// TODO Auto-generated method stub
   	return _userInfo;
   }

   @Override
   public void SetUserInfo(IUserInfo user) {
   	// TODO Auto-generated method stub
   	_userInfo=user;
   }

@Override
public String GetIp() {
	// TODO Auto-generated method stub
	return _ip;
}

@Override
public void SetIp(String ip) {
	// TODO Auto-generated method stub
	_ip=ip;
}

   //endregion

}
