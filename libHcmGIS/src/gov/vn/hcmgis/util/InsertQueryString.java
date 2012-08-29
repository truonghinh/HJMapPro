/**
 * 
 */
package gov.vn.hcmgis.util;

import gov.vn.hcmgis.database.dataModel.TableStructure;

import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

/**
 * @author HT
 *
 */
public class InsertQueryString extends QueryString 
{
	private String _tableName;
	private String[] _columns;
	private HashMap<String,Object> _pairColumnsValues;
	private HashMap<String,Object> _keys;
	private TableStructure _tblStructure;
	private String _fullQueryString;
	private String _preQueryString;
	private String _extQueryString;
	
	public InsertQueryString()
	{
		_tableName="";
		_columns=null;
	}
	public InsertQueryString(String tableName,String[] columns)
	{
		_tableName=tableName;
		_columns=columns;
	}
	
	public InsertQueryString(String tableName,HashMap<String,Object> columnsAndValues)
	{
		_tableName=tableName;
		_pairColumnsValues=columnsAndValues;
	}
	
	public InsertQueryString(TableStructure tableStructure)
	{
		_tblStructure=tableStructure;
	}
	
	public void SetTableStructure(TableStructure tableStructure)
	{
		_tblStructure=tableStructure;
	}
	
	public String GetFullQueryStringWithSelectClause(Boolean ignoreExistItem,String whereClause)
	{
		if(_tblStructure==null)
		{
			return "";
		}
		int len=_tblStructure.ColumnsCount();
		if(!(len>0))
		{
			return "";
			
		}
		
		//region lay phan pre
		_fullQueryString=String.format("INSERT INTO %s(",_tableName);
		
		String cols="";
		String vals="";
		for(int i=0;i<len;i++)
		{
			Entry<String, Integer> en=_tblStructure.GetColumnAt(i);
			cols+=en.getKey();
			int t=en.getValue();
			if(t==Types.DATE)
			{
				vals+="to_date(?,'DD MM YYYY')";
			}
			else
			{
				vals+="?";
			}
			if(i<len-1)
			{
				cols+=",";
				vals+=",";
			}
		}
//		Object vals=null;
//		Set<Entry<String, Object>> set=_pairColumnsValues.entrySet();
//		Iterator<Entry<String, Object>> i=set.iterator();
//		Entry<String, Object> me=null;
		
//		int num=0;
//		while(i.hasNext())
//		{
//			me=i.next();
//			cols=me.getKey();
//			if(num<len-1)
//			{
//				cols+=",";
//			}
//		}
		_fullQueryString+=cols+") ";
		
		//endregion
		
		//region lay phan ext
		String whereClauseKey="";
		if(ignoreExistItem)
		{
			whereClauseKey=" WHERE NOT EXISTS ";
		}
		else
		{
			whereClauseKey=" WHERE ";
		}
		_fullQueryString+=String.format("SELECT %s %s %s ",vals,whereClauseKey,whereClause);
		
		//endregion
		return _fullQueryString;
	}
	
	/**
	 * @return the _tableName
	 */
	public String get_tableName() {
		return _tableName;
	}
	/**
	 * @param _tableName the _tableName to set
	 */
	public void set_tableName(String tableName) {
		this._tableName = tableName;
	}
	/**
	 * @return the _columns
	 */
	public String[] get_columns() {
		return _columns;
	}
	/**
	 * @param _columns the _columns to set
	 */
	public void set_columns(String[] columns) {
		this._columns = columns;
	}
	
}
