/**
 * 
 */
package gov.vn.hcmgis.core.actions.onTable;

import gov.vn.hcmgis.connection.ConnectionManager;
import gov.vn.hcmgis.connection.ConnectionProperty;
import gov.vn.hcmgis.connection.IConnectionManager;
import gov.vn.hcmgis.connection.IConnectionProperty;
import gov.vn.hcmgis.core.files.CustomFilter;
import gov.vn.hcmgis.database.dataModel.RelationalTable;
import gov.vn.hcmgis.database.dataModel.TableStructure;
import gov.vn.hcmgis.util.BuildValuesFromPS;
import gov.vn.hcmgis.util.InsertQueryString;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

/**
 * @author HT
 *
 */
public class ActExImExcel extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TableStructure _table=null;
	IConnectionProperty _connPro;
	IConnectionManager _connMan;
	
	public ActExImExcel(TableStructure table,String title,ImageIcon icon)
	{
		super(title,icon);
		this._table=table;
	}
	
	public ActExImExcel(String title,ImageIcon icon)
	{
		super(title,icon);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			ImportFromExel(0,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SetTableStructure(TableStructure table)
	{
		this._table=table;
	}
	
	public int ImportFromExel(int sheetNum,Boolean hasColHeader) throws IOException, SQLException
	{
		if(_table==null)
		{
			return 0;
		}
		int rownum=0;
		_connPro=ConnectionProperty.CallMe();
		_connMan=ConnectionManager.CallMe();
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		chooser.addChoosableFileFilter(new CustomFilter());
		chooser.setAcceptAllFileFilterUsed(false);
	  
		int state = chooser.showSaveDialog(null); 
	    File file = chooser.getSelectedFile();
	    
	    if (file != null && state == JFileChooser.APPROVE_OPTION )  
        {  
	    	InputStream inp = new FileInputStream(file);
	    	HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
	    	@SuppressWarnings("unchecked")
			Iterable<Row> sheet11 = (Iterable<Row>) wb.getSheetAt(sheetNum);
	    	Connection c=null;
	    	try
	    	{
	    		c =_connMan.GetCurrentConnection();
	    		if(c.isClosed())
	    		{
	    			JOptionPane.showMessageDialog(null,"Kết nối đã đóng.");
	    			return -1;
	    		}
	    		
	    		Boolean gotHeader=false;
	    		if(hasColHeader)
	    		{
	    			gotHeader=false;
	    		}
	    		else
	    		{
	    			gotHeader=true;
	    		}
	    		
	    		try {
		    		InsertQueryString queryString=new InsertQueryString(_table);
		    		
		    		for ( Row row:sheet11) 
		    		{
		    			if(!gotHeader)
		    			{
		    				gotHeader=true;
		    				continue;
		    			}
		    			HashMap<Integer,Entry<Integer,Object>> values=new HashMap<Integer, Entry<Integer,Object>>();
		    			String AddSQL=queryString.GetFullQueryStringWithSelectClause(true, "(SELECT "+_table.get_primaryKey()+" FROM "+_table.get_exactlyName()+" WHERE "+_table.get_primaryKey()+"=?)");
//		    			JOptionPane.showMessageDialog(null, String.format("line 128 ActExImExcel, AddSQL=%s",AddSQL));
		    			System.out.println(String.format("line 128 ActExImExcel, AddSQL=%s", AddSQL));
//		    				   Class.forName("org.postgresql.Driver");
		    				   //DriverManager.getConnection(dburl, dbuser, dbpass);
		    				   
		    				  // SQL = "insert into dvt(ma_dvt,ten_dvt,ghichu_dvt) SELECT ?,?,? WHERE NOT EXISTS (SELECT ma_dvt FROM dvt WHERE ma_dvt=?)";
//		    				   AddSQL = "INSERT INTO internet(ma_internet, ten_chu, banghieu, so_nha, ten_duong,phuong, so_dkkd, ngaycap_dkkd, loaihinh_kd,so_dt,von,ghichu ) ";
		    				  // AddSQL += " ngaycap_dkkd, so_gcn, ngaycap_gcn, thoihan, ghichu ) ";
//		    				   AddSQL += " SELECT ?,?,?,?,?,?,?,to_date(?,'DD MM YYYY'),?,?,?,? WHERE NOT EXISTS (SELECT ma_internet FROM internet WHERE ma_internet=?)";
		    				   
		    				   
		    				   PreparedStatement ps = c.prepareStatement(AddSQL);
		    				   int len=_table.ColumnsCount();
		    				   System.out.println(String.format("line 150 ActExImExcel, len=%s", len));
							   //ma
		    				   for(int j=0;j<len;j++)
		    				   {
		    					   Entry<String,Integer> col=_table.GetColumnAt(j);
		    					   int type=col.getValue();
		    					   if(type==Types.INTEGER||type==Types.DOUBLE)
		    					   {
		    						   values.put(j+1, new SimpleEntry<Integer,Object>(type,row.getCell(j).getNumericCellValue()));
		    					   }
		    					   else if(type==Types.CHAR || type==Types.VARCHAR)
		    					   {
		    						   values.put(j+1, new SimpleEntry<Integer,Object>(type,row.getCell(j).getStringCellValue()));
		    					   }
		    				   }
		    				   BuildValuesFromPS.Build(ps, values);
//								if (row.getCell(0) != null)
//									   ps.setInt(1, (int) row.getCell(0).getNumericCellValue());
//								   else 
//									   ps.setNull(1,java.sql.Types.INTEGER);
//							
//							   //tenchu
//							   
//								if (row.getCell(1) != null)
//									   ps.setString(2, (String) row.getCell(1).getStringCellValue());
//								   else 
//									   ps.setNull(2, java.sql.Types.CHAR);
//							  //banhhieu
//								
//								if (row.getCell(2) != null)
//									   ps.setString(3, (String) row.getCell(2).getStringCellValue());
//								   else 
//									   ps.setNull(3,java.sql.Types.CHAR);
//														  					
//							
//							   //sonha
//							   try {
//								if (row.getCell(3) != null)
//									   ps.setInt(4, (int)row.getCell(3).getNumericCellValue());
//								   else 
//									   ps.setNull(4,java.sql.Types.CHAR);
//							}
//		    		catch (Exception e1) {
//								// TODO Auto-generated catch block
//								if (row.getCell(3) != null)
//									   ps.setString(4, (String) row.getCell(3).getStringCellValue());
//								   else 
//									   ps.setNull(4,java.sql.Types.CHAR);
//							}
//							   //tenduong
//							   if (row.getCell(4) != null)
//								   ps.setString(5, (String) row.getCell(4).getStringCellValue());
//							   else 
//								   ps.setNull(5,java.sql.Types.CHAR);
//							   //tenphuong
//							   if (row.getCell(5) != null)
//								   ps.setInt(6, (int) row.getCell(5).getNumericCellValue());
//							   else 
//								   ps.setNull(6,java.sql.Types.INTEGER);
//							   //sodkkd
//							  							try {
//									if (row.getCell(6) != null)
//										   ps.setString(7, (String) row.getCell(6).getStringCellValue());
//									   else 
//										   ps.setNull(7,java.sql.Types.CHAR);
//								} catch (Exception e1) {
//									// TODO Auto-generated catch block
//									if (row.getCell(6) != null)
//										   ps.setInt(7, (int) row.getCell(6).getNumericCellValue());
//									   else 
//										   ps.setNull(7,java.sql.Types.CHAR);
//								}
//							
//							   //ngaycap
//							   try {
//								if ((row.getCell(7) != null) && (!row.getCell(7).getStringCellValue().equals("") ))						     
//									   ps.setString(8,row.getCell(7).getStringCellValue());
//								   else 
//									   ps.setNull(8,java.sql.Types.CHAR);
//							} catch (Exception e1) {
//								// TODO Auto-generated catch block
//								JOptionPane.showMessageDialog(null,e1.toString());
//							}
//							   //loaihinh
//							   if (row.getCell(8) != null)
//								   ps.setString(9, (String) row.getCell(8).getStringCellValue());
//							   else 
//								   ps.setNull(9,java.sql.Types.CHAR);
//							   //sodt
//							   if ((row.getCell(9) != null) && (!row.getCell(9).getStringCellValue().equals("") ))						     
//								   ps.setString(10,row.getCell(9).getStringCellValue());
//							   else 
//								   ps.setNull(10,java.sql.Types.CHAR);
//							   //von
//							   if (row.getCell(10) != null)
//								   ps.setInt(11, (int) row.getCell(10).getNumericCellValue());
//							   else 
//								   ps.setNull(11,java.sql.Types.DOUBLE);
//							   //ghichu
//							   if (row.getCell(11) != null)
//								   ps.setString(12, (String) row.getCell(11).getStringCellValue());
//							   else 
//								   ps.setNull(12,java.sql.Types.CHAR);
//							   //magas
//							   try {
//								if (row.getCell(0) != null)
//									   ps.setInt(13, (int) row.getCell(0).getNumericCellValue());
//								   else 
//									   ps.setNull(13,java.sql.Types.INTEGER);
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								JOptionPane.showMessageDialog(null,e.toString());
//							}						   	  
							   ps.executeUpdate(); 
							   ps.close();
							   
							  					}}
		    			catch (Exception ex) {
							// TODO Auto-generated catch block
		    				JOptionPane.showMessageDialog(null,ex);
								    		}
	    	}
	    	catch (Exception ex) 
	    	{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,ex);
	    	}
	    	finally
	    	{
	    		
	    		if(c!=null & !c.isClosed())
	    		{
	    			_connMan.CloseConnection(c);
	    		}
	    	}
			
        }
	    
		return rownum;
	}

}
