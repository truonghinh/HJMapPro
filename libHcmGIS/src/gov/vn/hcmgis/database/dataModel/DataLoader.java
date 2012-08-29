/**
 * 
 */
package gov.vn.hcmgis.database.dataModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;

import gov.vn.hcmgis.connection.ConnectionManager;
import gov.vn.hcmgis.connection.ConnectionProperty;
import gov.vn.hcmgis.connection.IConnectionManager;
import gov.vn.hcmgis.connection.IConnectionProperty;
import gov.vn.hcmgis.util.BuildValuesFromPS;

/**
 * @author HT
 *
 */
public class DataLoader extends Thread implements IDataLoader {
	protected IDataReceiver _receiver;
	protected IConnectionProperty _connPro;
	protected IConnectionManager _connMan;
	protected String _currentKey="";
	protected String _currentSql="";
	public DataLoader()
	{
		this(null);
	}
	public DataLoader(IDataReceiver receiver)
	{
		_connPro=ConnectionProperty.CallMe();
		_connMan=ConnectionManager.CallMe();
		_receiver=receiver;
	}
	@Override
	public void LoadData(String key, String sql) {
		// TODO Auto-generated method stub
		if(_receiver==null)
		{
			return;
		}
		_currentKey=key;
		_currentSql=sql;
		
		Connection c=_connMan.GetCurrentConnection();
		if(c==null)
		{
			return;
		}
		try {
			Statement st = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = st.executeQuery(_currentSql);
			_receiver.Receive(_currentKey, result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void SetReceiver(IDataReceiver receiver) {
		// TODO Auto-generated method stub
		_receiver=receiver;
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#start()
	 */
	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		super.start();
	}
	@Override
	public void UpdateData(String key, String sql,HashMap<Integer,Entry<Integer,Object>> values) {
		// TODO Auto-generated method stub
		_currentKey=key;
		_currentSql=sql;
		
		Connection c=_connMan.GetCurrentConnection();
		if(c==null)
		{
			return;
		}
		try {
//			Statement st = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			PreparedStatement ps=c.prepareStatement(sql);
			BuildValuesFromPS.Build(ps, values);
			System.out.println(String.format("line 100 DataLoader,ps= %s",ps.toString()));
			int result = ps.executeUpdate();
			
			if(_receiver!=null)
			{
				_receiver.Receive(_currentKey, result);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void DeleteData(String key, String sql) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void UpdateSpatial(String key, String sql) {
		// TODO Auto-generated method stub
		_currentKey=key;
		_currentSql=sql;
		
		Connection c=_connMan.GetCurrentConnection();
		if(c==null)
		{
			return;
		}
		try {
			Statement st = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			int result = st.executeUpdate(_currentSql);
			System.out.println(String.format("Line 132 DataLoader, sql=%s",_currentSql));
			_receiver.Receive(_currentKey, result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
