/**
 * 
 */
package gov.vn.hcmgis.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * @author hdk
 *
 */
public class UserDAO {
	IConnectionPool _connPool;
	IConnectionProperty _connPro;
	IUserInfo _user;
	IConnectionManager _connMan;
	public UserDAO()
	{
		_connMan=ConnectionManager.CallMe();
		_connPool=(IConnectionPool)_connMan;
		_connPro=ConnectionProperty.CallMe();
		
	}
	
	public UserDAO(IConnectionProperty connPro)
	{
		_connMan=ConnectionManager.CallMe();
		_connPro=connPro;
	}
	
//	private Connection openConn() {
//		Connection conn = null;
//
//		try {
//			conn = DriverManager.getConnection (
//					"jdbc:postgresql://localhost/quan8", "postgres", "postgres");
//		} catch (SQLException e) {
//			e.printStackTrace ();
//		}
//
//		return conn;
//	}
//
//	private void closeConn(Connection conn) {
//		try {
//			conn.close ();
//		} catch (SQLException e) {
//			e.printStackTrace ();
//		}
//
//	}
	
	private Connection connect()
	{
//		_connPro.SetDriver(ClassNameUtils.PgDriverClassName);
//		_connPro.SetConnectionName("new connect");
//		_connPro.SetDatabaseName("quan8");
//		_connPro.SetIp("localhost");
//		_user=new UserInfo();
//		_user.SetName("postgres");
//		_user.SetPass("postgres");
//		_connPro.SetUserInfo(_user);
//		_connPool.setup(_connPro);
		return _connMan.GetSingleConnection(_connPro);
	}
	
	//region FindByName
	public IUserInfo FindByName(String name)
	{
		IUserInfo user=null;
		Connection conn=connect();
		String sql="SELECT id, username, pass from users where username LIKE ?";
		PreparedStatement select;
		
		try 
		{
//			JOptionPane.showMessageDialog(null, name);
			select=conn.prepareStatement(sql);
			
			select.setString (1, name);

			ResultSet result = select.executeQuery ();
			if (result.next ()) {// process results one row at a time
				int id = result.getInt(1);
				String username = result.getString(2);
				String pass = result.getString(3);
				System.out.println(String.format("line 93 UserDAO: pass=%s", pass));
				user = new UserInfo(username,pass);
			}
			else
			{
				System.out.println("người dùng không đúng");
			}
			select.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace ();
		}
		finally
		{
			
			_connMan.CloseConnection(conn);
		}
		return user;
	}
	//endregion
	
	public IUserInfo FindUserByPass(Object pwd)
	{
		IUserInfo user=UserInfo.Empty();
		Connection conn=connect();
		
		String sql="SELECT id, username, pass from users where pass LIKE ?";
		PreparedStatement select;
		
		try 
		{
			select=conn.prepareStatement(sql);
			
			select.setString (1, pwd.toString());

			ResultSet result = select.executeQuery ();
			if (result.next ()) {// process results one row at a time
				int id = result.getInt(1);
				String username = result.getString(2);
				String pass = result.getString (3);


				user = new UserInfo(username,pass);
			}
			else
			{
				System.out.println("Mật khẩu không đúng");
			}
			select.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace ();
		}
		finally
		{
			
			_connMan.CloseConnection(conn);
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public int CheckUserByName()
	{
//		JOptionPane.showMessageDialog(null, _connPro.GetUserInfo().GetName());
		IUserInfo u=this.FindByName(_connPro.GetUserInfo().GetName());
//		JOptionPane.showMessageDialog(null, u.GetName());
		int result=TypeOfErrorOnUser.WrongUserNameAndPass;
		result=((Comparable<IUserInfo>)u).compareTo(_connPro.GetUserInfo());
//		JOptionPane.showMessageDialog(null, result);
		return result;
	}

}
