/**
 * 
 */
package gov.vn.hcmgis.database.dataModel;

/**
 * @author HT
 *
 */
public class RelationalTable extends TableStructure {

	public RelationalTable(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public RelationalTable(String name,String primaryKey) {
		super(name,primaryKey);
	}
}
