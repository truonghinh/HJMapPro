/**
 * 
 */
package gov.vn.hcmgis.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.hsqldb.Types;

/**
 * @author HT
 *
 */
public class BuildValuesFromPS {
//	private PreparedStatement _ps;
//	private HashMap<Integer,Object> _values;
	
	public static void Build(PreparedStatement ps,HashMap<Integer,Entry<Integer,Object>> values) throws NumberFormatException, SQLException
	{
		int len=values.size();
		
		for(int i=1;i<=len;i++)
		{
			Object value=values.get(i).getValue();
			int type=values.get(i).getKey();
			if(value==null)
			{
				ps.setNull(i, type);
			}
			else
			{
				if(type==Types.INTEGER)
				{
					ps.setInt(i,Integer.valueOf(value.toString()));
				}
				else if(type==Types.CHAR)
				{
					ps.setString(i,value.toString());
				}
				else if(type==Types.VARCHAR)
				{
					ps.setString(i,value.toString());
				}
				else if(type==Types.DATE)
				{
					ps.setString(i,value.toString());
				}
				else if(type==Types.DOUBLE)
				{
					ps.setDouble(i,Double.valueOf(value.toString()));
				}
			}
		}
		
	}
}
