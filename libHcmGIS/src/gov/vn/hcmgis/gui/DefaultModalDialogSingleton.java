/**
 * 
 */
package gov.vn.hcmgis.gui;

import javax.swing.JPanel;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.SingletonWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;

/**
 * @author HT
 *
 */
public class DefaultModalDialogSingleton extends JPanel implements IGui,IWindow,SingletonWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected WindowInfo _winfo = new WindowInfo(WindowInfo.RESIZABLE);
	
	public DefaultModalDialogSingleton() {
		super();
		this.SetWindowSize(100, 100);
	}
	
	public DefaultModalDialogSingleton(String title) {
		super();
		this.SetWindowSize(100, 100);
		_winfo.setTitle(title);
	}
	
	public DefaultModalDialogSingleton(int width,int height) {
		super();
		this.SetWindowSize(width, height);
	}
	
	public DefaultModalDialogSingleton(String title,int width,int height) {
		super();
		_winfo.setTitle(title);
		this.SetWindowSize(width, height);
	}
	
	@Override
	public Object getWindowModel() {
		// TODO Auto-generated method stub
		return null;
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
	public void Show() {
		// TODO Auto-generated method stub
		PluginServices.getMDIManager().addCentredWindow(this);
	}

	@Override
	public void ShowDialog() {
		// TODO Auto-generated method stub
		PluginServices.getMDIManager().addCentredWindow(this);
	}

	@Override
	public void Close() {
		// TODO Auto-generated method stub
		PluginServices.getMDIManager().closeSingletonWindow(this);
	}

	@Override
	public void Hide() {
		// TODO Auto-generated method stub
		PluginServices.getMDIManager().closeWindow(this);
	}

	@Override
	public void SetWindowSize(int width, int height) {
		// TODO Auto-generated method stub
		_winfo.setWidth(width);
		_winfo.setHeight(height);
	}

	@Override
	public void SetWindowHeight(int height) {
		// TODO Auto-generated method stub
		_winfo.setHeight(height);
	}

	@Override
	public void SetWindowWidth(int width) {
		// TODO Auto-generated method stub
		_winfo.setWidth(width);
	}

	@Override
	public int GetHeight() {
		// TODO Auto-generated method stub
		return _winfo.getHeight();
	}

	@Override
	public int GetWidth() {
		// TODO Auto-generated method stub
		return _winfo.getWidth();
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
