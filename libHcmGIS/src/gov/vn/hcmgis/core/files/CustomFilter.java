/**
 * 
 */
package gov.vn.hcmgis.core.files;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * @author HT
 *
 */
public class CustomFilter extends FileFilter {

	private String type="";
	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File file) {
		// TODO Auto-generated method stub
		return file.isDirectory() || file.getAbsolutePath().endsWith(".xls") || file.getAbsolutePath().endsWith(".XLS");
	}

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	

}
