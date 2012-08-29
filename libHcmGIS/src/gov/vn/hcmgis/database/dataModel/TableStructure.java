/**
 * 
 */
package gov.vn.hcmgis.database.dataModel;

import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author HT
 *
 */
public abstract class TableStructure {
	protected HashMap<String, Integer> _columns;
	protected String _name;
	protected String _primaryKey;
	protected String _exactlyName;
	
	public TableStructure(String name)
	{
		this(name,"");
	}
	public TableStructure(String name,String primaryKey)
	{
		this._name=name;
		this._primaryKey=primaryKey;
		this._columns=new HashMap<String, Integer>();
	}
	
	public Set<Entry<String, Integer>> GetAllEntries()
	{
		return _columns.entrySet();
	}
	
	public int ColumnsCount()
	{
		return _columns.size();
	}
	
	public void ClearColumns()
	{
		_columns.clear();
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Integer> CloneColumns()
	{
		HashMap<String, Integer> cl=(HashMap<String, Integer>) _columns.clone();
		return cl;
	}

	/**
	 * @return the _columns
	 */
	public HashMap<String, Integer> get_columns() {
		return _columns;
	}

	/**
	 * @param _columns the _columns to set
	 */
	public void set_columns(HashMap<String, Integer> columns) {
		this._columns = columns;
	}

	/**
	 * @return the _name
	 */
	public String get_name() {
		return _name;
	}

	/**
	 * @param _name the _name to set
	 */
	public void set_name(String name) {
		this._name = name;
	}

	public String get_exactlyName() {
		return _exactlyName;
	}
	public void set_exactlyName(String exactlyName) {
		this._exactlyName = exactlyName;
	}
	/**
	 * @return the _primaryKey
	 */
	public String get_primaryKey() {
		return _primaryKey;
	}
	/**
	 * @param _primaryKey the _primaryKey to set
	 */
	public void set_primaryKey(String primaryKey) {
		this._primaryKey = primaryKey;
	}
	public void AddColumn(String name,Integer type)
	{
		_columns.put(name, type);
	}
	
	public Entry<String, Integer> GetColumnAt(int index)
	{
		int count=_columns.size();
		if(index>=count | index<0)
		{
			return null;
		}
		
		Set<Entry<String, Integer>> set=this.GetAllEntries();
		Iterator<Entry<String, Integer>> i=set.iterator();
		Entry<String, Integer> me=null;
		int num=0;
		while(i.hasNext())
		{
			if(num!=index)
			{
				continue;
			}
			else
			{
				 me=i.next();
				 break;
			}
			
		}
		return me;
	}
	
	public Entry<String, Integer> GetColumnByName(String name)
	{
		int count=_columns.size();
		if(count==0)
		{
			return null;
		}
		Set<Entry<String, Integer>> set=this.GetAllEntries();
		Iterator<Entry<String, Integer>> i=set.iterator();
		Entry<String, Integer> me=null;
		int num=0;
		while(i.hasNext())
		{
			me=i.next();
			
			if(me.getKey()!=name)
			{
				continue;
			}
			else
			{
				 
				 break;
			}
			
		}
		return me;
	}
	
}
