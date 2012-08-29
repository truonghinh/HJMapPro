/**
 * 
 */
package gov.vn.hcmgis.util;

import java.sql.Types;
import java.util.Map.Entry;

import gov.vn.hcmgis.database.dataModel.TableStructure;

/**
 * @author HT
 *
 */
public class UpdateQueryString extends QueryString {
	private TableStructure _tblStructure;
	private String _fullQueryString;
	public UpdateQueryString(TableStructure tableStructure)
	{
		_tblStructure=tableStructure;
	}
	
	public void SetTableStructure(TableStructure tableStructure)
	{
		_tblStructure=tableStructure;
	}
	
	public String GetSimpleFullUpdateQueryString(String whereClause)
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
		_fullQueryString=String.format("UPDATE %s SET ",_tblStructure.get_name());
		
		String cols="";
		String vals="";
		for(int i=0;i<len;i++)
		{
			Entry<String, Integer> en=_tblStructure.GetColumnAt(i);
			cols=en.getKey();
			int t=en.getValue();
			if(t==Types.DATE)
			{
				vals="to_date(?,'DD MM YYYY')";
			}
			else
			{
				vals="?";
			}
			
			_fullQueryString+=String.format("%s=%s", cols,vals);
			if(i<len-1)
			{
				_fullQueryString+=",";
			}
		}
		if(whereClause!="")
		{
			_fullQueryString+=" WHERE "+whereClause;
		}
//		
		
		//endregion
		return _fullQueryString;
	}
	public String GetSimpleFullUpdateQueryString(Object keyValue)
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
		_fullQueryString=String.format("UPDATE %s SET ",_tblStructure.get_name());
		
		String cols="";
		String vals="";
		for(int i=0;i<len;i++)
		{
			Entry<String, Integer> en=_tblStructure.GetColumnAt(i);
			cols=en.getKey();
			int t=en.getValue();
			if(t==Types.DATE)
			{
				vals="to_date(?,'DD MM YYYY')";
			}
			else
			{
				vals="?";
			}
			
			_fullQueryString+=String.format("%s=%s", cols,vals);
			if(i<len-1)
			{
				_fullQueryString+=",";
			}
		}
		if(!(keyValue==null || _tblStructure.get_primaryKey()==null || _tblStructure.get_primaryKey()=="") )
		{
			_fullQueryString+=String.format(" Where %s='%s'", _tblStructure.get_primaryKey(),keyValue);
		}
//		
		
		//endregion
		return _fullQueryString;
	}
	
	public String GetSimpleFullUpdateQueryString(Object keyValue,String[] columns)
	{
		if(_tblStructure==null)
		{
			return "";
		}
		int len=columns.length;
		if(!(len>0))
		{
			return "";
			
		}
		
		//region lay phan pre
		_fullQueryString=String.format("UPDATE %s SET ",_tblStructure.get_exactlyName());
		
		String cols="";
		String vals="";
		for(int i=0;i<len;i++)
		{
			Entry<String, Integer> en=_tblStructure.GetColumnByName(columns[i]);
			cols=en.getKey();
			int t=en.getValue();
			if(t==Types.DATE)
			{
				vals="to_date(?,'DD MM YYYY')";
			}
			else
			{
				vals="?";
			}
			
			_fullQueryString+=String.format("%s=%s", cols,vals);
			if(i<len-1)
			{
				_fullQueryString+=",";
			}
		}
		if(!(keyValue==null || _tblStructure.get_primaryKey()==null || _tblStructure.get_primaryKey()=="") )
		{
			_fullQueryString+=String.format(" Where %s='%s'", _tblStructure.get_primaryKey(),keyValue);
		}
//		
		
		//endregion
		return _fullQueryString;
	}
}
