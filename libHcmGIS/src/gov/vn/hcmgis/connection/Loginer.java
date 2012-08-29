/***********************************************************************
 * Module:  Loginer.java
 * Author:  hdk
 * Purpose: Defines the Class Loginer
 ***********************************************************************/

package gov.vn.hcmgis.connection;

import gov.vn.hcmgis.connection.gui.ILoginView;

import java.util.*;

/** @pdOid 912c0960-ace1-4f38-b1cc-cd5f76da7e87 */
public class Loginer implements ILogin {
	protected UserDAO _userDao;
   /** @pdRoleInfo migr=no name=IConnectionPool assc=association6 mult=1..1 type=Aggregation */
	protected IConnectionPool _connectionPool;
   /** @pdRoleInfo migr=no name=IConnectionProperty assc=association9 mult=1..1 */
	protected IConnectionProperty _connectionProperty;
   protected ILoginView _view;
   public Loginer(){}
   
   public Loginer(ILoginView view)
   {
	   _view=view;
   }
   
   /** @pdOid 4b250f6a-14da-4bbf-85f1-01542c7069ac */
   public IConnectionPool getConnectionPool() {
      // TODO: implement
      return null;
   }
   
   /** @pdOid 4955c273-6bcf-43bb-aaac-1f035fd7f5c1 */
   public IConnectionProperty getConnectionProperty() {
      // TODO: implement
      return null;
   }
   
   /** @param connPro
    * @pdOid 24027391-6348-4243-88aa-70fc10749119 */
   public void setConnectionProperty(IConnectionProperty connPro) {
      // TODO: implement
   }
   
   /** @pdOid e12692ef-7b39-485f-b753-b29d485d897b */
   public void login() {
      // TODO: implement
   }
   
   /** @pdOid 85574dd8-4c62-470b-8557-ed5b976fe6c5 */
   public void logout() {
      // TODO: implement
   }
   
   /** @pdOid 8d88926b-debf-4097-8a0f-9fbbcc3cc0e5 */
   public void shutdown() {
      // TODO: implement
   }

@Override
public void SetConnectionProperty(IConnectionProperty connPro) {
	// TODO Auto-generated method stub
	_connectionProperty=connPro;
}

@Override
public void Login() {
	// TODO Auto-generated method stub
	_userDao=new UserDAO(_connectionProperty);
	int result=_userDao.CheckUserByName();
	_view.ReceiveChekingResult(result);
}

@Override
public void SetView(ILoginView view) {
	// TODO Auto-generated method stub
	_view=view;
}

}
