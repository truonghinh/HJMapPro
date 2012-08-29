/**
 * 
 */
package gov.vn.hcmgis.gui;

import javax.swing.JPanel;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;

/**
 * @author hdk
 *
 */
public class DefaultModalDialog extends JPanel implements IGui,IWindow {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected WindowInfo _winfo = new WindowInfo(WindowInfo.MODALDIALOG);
	public DefaultModalDialog() {
		super();
		this.SetWindowSize(100, 100);
	}
	
	public DefaultModalDialog(String title) {
		super();
		this.SetWindowSize(100, 100);
		_winfo.setTitle(title);
	}
	
	public DefaultModalDialog(int width,int height) {
		super();
		this.SetWindowSize(width, height);
	}
	
	public DefaultModalDialog(String title,int width,int height) {
		super();
		_winfo.setTitle(title);
		this.SetWindowSize(width, height);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#Show()
	 */
	@Override
	public void Show() {
		// TODO Auto-generated method stub
		PluginServices.getMDIManager().addCentredWindow(this);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#ShowDialog()
	 */
	@Override
	public void ShowDialog() {
		// TODO Auto-generated method stub
		PluginServices.getMDIManager().addCentredWindow(this);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#Close()
	 */
	@Override
	public void Close() {
		// TODO Auto-generated method stub
		PluginServices.getMDIManager().closeWindow(this);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#Hide()
	 */
	@Override
	public void Hide() {
		// TODO Auto-generated method stub
		PluginServices.getMDIManager().closeWindow(this);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#SetWindowSize(int, int)
	 */
	@Override
	public void SetWindowSize(int width, int height) {
		// TODO Auto-generated method stub
		_winfo.setWidth(width);
		_winfo.setHeight(height);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#SetWindowHeight(int)
	 */
	@Override
	public void SetWindowHeight(int height) {
		// TODO Auto-generated method stub
		_winfo.setHeight(height);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#SetWindowWidth(int)
	 */
	@Override
	public void SetWindowWidth(int width) {
		// TODO Auto-generated method stub
		_winfo.setWidth(width);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#GetHeight()
	 */
	@Override
	public int GetHeight() {
		// TODO Auto-generated method stub
		return _winfo.getHeight();
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.gui.IGui#GetWidth()
	 */
	@Override
	public int GetWidth() {
		// TODO Auto-generated method stub
		return _winfo.getWidth();
	}

	@Override
	public WindowInfo getWindowInfo() {
		// TODO Auto-generated method stub
		return _winfo;
	}

	@Override
	public Object getWindowProfile() {
		// TODO Auto-generated method stub
		return WindowInfo.DIALOG_PROFILE;
	}

	@Override
	public void SetTitle(String title) {
		// TODO Auto-generated method stub
		_winfo.setTitle(title);
	}

	@Override
	public void SetWindowType(int type) {
		// TODO Auto-generated method stub
		WindowInfo wi =new WindowInfo(type);
		wi.setTitle(_winfo.getTitle());
		wi.setWidth(_winfo.getWidth());
		wi.setHeight(_winfo.getHeight());
		_winfo.setWindowInfo(wi);
	}

}
