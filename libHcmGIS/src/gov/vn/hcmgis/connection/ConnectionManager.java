/***********************************************************************
 * Module:  ConnectionManager.java
 * Author:  hdk
 * Purpose: Defines the Class ConnectionManager
 ***********************************************************************/

package gov.vn.hcmgis.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;

/** @pdOid be58b015-8326-4f44-a18c-52627cb81fe3 */
public class ConnectionManager implements IConnectionManager, IConnectionPool {
   /** @pdRoleInfo migr=no name=BoneCP assc=association7 mult=1..1 type=Composition */
	private static ConnectionManager _meClass=null;
   private BoneCP _boneCP;
   /** @pdRoleInfo migr=no name=Connection assc=association8 mult=1..1 type=Composition */
   private Connection _connection;
   /** @pdRoleInfo migr=no name=IConnectionProperty assc=association10 mult=1..1 type=Composition */
   private IConnectionProperty _connectionProperty;
   private int _minConn;
   private int _maxConn;
   private int _partCount;
   int helper_threads ;
   boolean doPreparedStatement = false;
   //region Construction

   private ConnectionManager()
   {
	   _boneCP=null;
	   _connection=null;
	   _minConn=5;
	   _maxConn=10;
	   _partCount=1;
	   helper_threads = 5;
	   try
		{
			Class.forName(ClassNameUtils.PgDriverClassName);
		}
		catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace ();
		}
   }
 //endregion
   
   public static ConnectionManager CallMe()
   {
	   if(_meClass==null)
	   {
		   _meClass=new ConnectionManager();
	   }
	   return _meClass;
   }
   
@Override
public Connection GetConnection() {
	// TODO Auto-generated method stub
	if(_boneCP==null)
	{
		System.out.println(String.format("line 56 ConnectionManager cp=null"));
	}
	try {
		_connection = _boneCP.getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(e);
	}
	try {
		System.out.println(_connection.getCatalog());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return _connection;
}
@Override

public void setup(IConnectionProperty connPro) {
	// TODO Auto-generated method stub
	_connectionProperty=connPro;
	System.out.println(_connectionProperty.GetUrl());
	BoneCPConfig config=new BoneCPConfig();
	System.out.println(_connectionProperty.GetUrl());
	System.out.println(_connectionProperty.GetUserInfo().GetName());
	System.out.println(_connectionProperty.GetUserInfo().GetPass());
	config.setJdbcUrl(_connectionProperty.GetUrl()); // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
	config.setUsername(_connectionProperty.GetUserInfo().GetName()); 
	config.setPassword(_connectionProperty.GetUserInfo().GetPass());
	config.setMinConnectionsPerPartition(_minConn);
	config.setMaxConnectionsPerPartition(_maxConn);
	config.setPartitionCount(_partCount);
	config.setDisableConnectionTracking(true);
    if (doPreparedStatement) {
    	config.setStatementsCacheSize(10);
    } else {
    	config.setStatementsCacheSize(0);
    }

    config.setReleaseHelperThreads(helper_threads);
    config.setStatementReleaseHelperThreads(15);
    config.setAcquireIncrement(5);
    config.setPoolName("me");
    config.setLazyInit(false);
    try {
		_boneCP = new BoneCP(config);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	
}
@Override
public void setMinConnection(int minPerPart) {
	// TODO Auto-generated method stub
	_minConn=minPerPart;
}
@Override
public void setMaxConnection(int maxPerPart) {
	// TODO Auto-generated method stub
	_maxConn=maxPerPart;
}
@Override
public void setPartCount(int partition) {
	// TODO Auto-generated method stub
	_partCount=partition;
}
/**
 * Khoi tao pool
 * @author hdk
 * @param connPro: connectionProperty
 * @param minConn: minConnectionPerPartition:Default=5
 * @param maxConn: maxConnectionPerPartition:Default=10
 * @param partCount: PartitionCount:Default=1
 */
@Override
public void setup(IConnectionProperty connPro, int minConn, int maxConn,
		int partCount) {
	// TODO Auto-generated method stub
	_connectionProperty=connPro;
	_minConn=minConn;
	_maxConn=maxConn;
	_partCount=partCount;
	BoneCPConfig config = new BoneCPConfig();
	
	config.setJdbcUrl(_connectionProperty.GetUrl()); // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
	config.setUsername(_connectionProperty.GetUserInfo().GetName()); 
	config.setPassword(_connectionProperty.GetUserInfo().GetPass());
	config.setMinConnectionsPerPartition(_minConn);
	config.setMaxConnectionsPerPartition(_maxConn);
	config.setPartitionCount(_partCount);
	try {
		_boneCP = new BoneCP(config);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
}


@Override
public void Reset() {
	// TODO Auto-generated method stub
	if(_connectionProperty==null)
	{
		System.out.print(String.format("khong reset duoc"));
		return;
	}
	BoneCPConfig config = new BoneCPConfig();
	config.setJdbcUrl(_connectionProperty.GetUrl()); // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
	config.setUsername(_connectionProperty.GetUserInfo().GetName()); 
	config.setPassword(_connectionProperty.GetUserInfo().GetPass());
	config.setMinConnectionsPerPartition(_minConn);
	config.setMaxConnectionsPerPartition(_maxConn);
	config.setPartitionCount(_partCount);

	try {
		_boneCP = new BoneCP(config);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}


@Override
public void CloseConnection() {
	// TODO Auto-generated method stub
	if(_connection!=null)
	{
		try {
			_connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


@Override
public void ShutdownPool() {
	// TODO Auto-generated method stub
	if(_boneCP!=null)
	{
		_boneCP.shutdown();
	}
	
}


@Override
public Connection GetSingleConnection(IConnectionProperty connPro) {
	// TODO Auto-generated method stub
	Connection conn = null;
	_connectionProperty=connPro;
//	JOptionPane.showMessageDialog(null, connPro.GetUserInfo().GetName());
	try {
//		conn = DriverManager.getConnection (
//				"jdbc:postgresql://localhost/quan8", "postgres", "postgres");
		conn = DriverManager.getConnection (connPro.GetUrl(),connPro.GetUserInfo().GetName(),connPro.GetUserInfo().GetPass());
	} catch (SQLException e) {
		e.printStackTrace ();
	}

	return conn;
}


@Override
public void CloseConnection(Connection conn) {
	// TODO Auto-generated method stub
	if(conn==null)
	{
		return;
	}
	try {
		conn.close ();
	} catch (SQLException e) {
		e.printStackTrace ();
	}
}

@Override
public Connection GetCurrentConnection() {
	// TODO Auto-generated method stub
	Connection conn = null;

//	JOptionPane.showMessageDialog(null, connPro.GetUserInfo().GetName());
	try {
//		conn = DriverManager.getConnection (
//				"jdbc:postgresql://localhost/quan8", "postgres", "postgres");
		conn = DriverManager.getConnection (_connectionProperty.GetUrl(),_connectionProperty.GetUserInfo().GetName(),_connectionProperty.GetUserInfo().GetPass());
	} catch (SQLException e) {
		e.printStackTrace ();
	}
	
	return conn;
}

}
