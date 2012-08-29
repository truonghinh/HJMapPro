/***********************************************************************
 * Module:  UserInfo.java
 * Author:  hdk
 * Purpose: Defines the Class UserInfo
 ***********************************************************************/

package gov.vn.hcmgis.connection;

import java.util.*;
import gov.vn.hcmgis.util.Encryption;

/** @pdOid af046010-4e75-4047-b864-02d985d05ed5 */
public class UserInfo implements IUserInfo,Comparable<IUserInfo> {
   /** @pdOid b4a68504-eb92-445a-8f40-e0e8fd23c69d */
   private static String _userName;
   private static String _pass;
   private static String _passEncrypted;
   public static IUserInfo Empty()
   {
	   IUserInfo user=new UserInfo();
	   user.SetName("noname");
	   user.SetPass("nopass");
	   return user;
   }
   
   public UserInfo()
   {
	   this._userName="";
	   this._pass="";
   }
   public UserInfo(String name,String pass)
   {
	   this._userName=name;
	   this._pass=pass;
   }
   
   @Override
public String GetName() {
	// TODO Auto-generated method stub
	return _userName;
}
@Override
public void SetName(String name) {
	// TODO Auto-generated method stub
	_userName=name;
	
}
@Override
public String GetPass() {
	// TODO Auto-generated method stub
	return _pass;
}
@Override
public String GetPassEncrypted() {
	// TODO Auto-generated method stub
	return _passEncrypted;
}
@Override
public void SetPass(String pass) {
	// TODO Auto-generated method stub
	_pass=pass;
	try {
		_passEncrypted= new String(Encryption.EncryptPassSimply(pass));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		_passEncrypted=_pass;
	}
}

@Override
public int compareTo(IUserInfo o) {
	// TODO Auto-generated method stub
	System.out.println(String.format("line 74 UserInfo: user1=%s,pass1=%s, user2=%s,pass2=%s",this._userName,this._pass,o.GetName(),o.GetPass()));
	if(this._userName.equals(o.GetName()) && this._pass.equals(o.GetPass()))
	{
		return TypeOfErrorOnUser.NoError;
	}
	else if( this._userName.equals(o.GetName()))
	{
		return TypeOfErrorOnUser.WrongPass;
	}
	else if(this._pass.equals(o.GetPass()))
	{
		return TypeOfErrorOnUser.WrongUserName;
	}
	else
	{
		return TypeOfErrorOnUser.WrongUserNameAndPass;
	}
}

}
