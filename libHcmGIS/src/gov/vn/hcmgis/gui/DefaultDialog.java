/**
 * 
 */
package gov.vn.hcmgis.gui;

import javax.swing.JDialog;

/**
 * @author HT
 *
 */
public abstract class DefaultDialog extends JDialog implements IGui {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#Show()
	 */
	@Override
	public void Show() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#ShowDialog()
	 */
	@Override
	public void ShowDialog() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#Close()
	 */
	@Override
	public void Close() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#Hide()
	 */
	@Override
	public void Hide() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#SetWindowSize(int, int)
	 */
	@Override
	public void SetWindowSize(int width, int height) {
		// TODO Auto-generated method stub
		this.setSize(width, height);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#SetWindowHeight(int)
	 */
	@Override
	public void SetWindowHeight(int height) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#SetWindowWidth(int)
	 */
	@Override
	public void SetWindowWidth(int width) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#GetHeight()
	 */
	@Override
	public int GetHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#GetWidth()
	 */
	@Override
	public int GetWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#SetTitle(java.lang.String)
	 */
	@Override
	public void SetTitle(String title) {
		// TODO Auto-generated method stub
		this.setTitle(title);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#SetWindowType(int)
	 */
	@Override
	public void SetWindowType(int type) {
		// TODO Auto-generated method stub

	}

}
