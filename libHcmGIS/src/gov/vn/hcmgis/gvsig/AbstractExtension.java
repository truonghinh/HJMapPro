/**
 * 
 */
package gov.vn.hcmgis.gvsig;

import gov.vn.hcmgis.connection.ConnectionProperty;
import gov.vn.hcmgis.connection.IConnectionProperty;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.IExtension;
import com.iver.andami.plugins.status.IExtensionStatus;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.project.documents.view.gui.View;

/**
 * @author HT
 *
 */
public abstract class AbstractExtension implements IGextension {

	protected IConnectionProperty _connPro;
	/* (non-Javadoc)
	 * @see com.iver.andami.plugins.IExtension#initialize()
	 */
	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.iver.andami.plugins.IExtension#postInitialize()
	 */
	@Override
	public void postInitialize() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.iver.andami.plugins.IExtension#terminate()
	 */
	@Override
	public void terminate() {
		// TODO Auto-generated method stub

	}


	/* (non-Javadoc)
	 * @see com.iver.andami.plugins.IExtension#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		_connPro=ConnectionProperty.CallMe();
		if(_connPro.GetUserInfo()==null)
		{
			return false;
		}
		if (_connPro.GetUserInfo().GetName().equals("")){
			return false;}
	  else
		return true;
	}

	/* (non-Javadoc)
	 * @see com.iver.andami.plugins.IExtension#isVisible()
	 */
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		IWindow v =PluginServices.getMDIManager().getActiveWindow(); 
		if (v instanceof View) { return true;} 
		else  { return false;}
	}

	/* (non-Javadoc)
	 * @see com.iver.andami.plugins.IExtension#getStatus()
	 */
	@Override
	public IExtensionStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.iver.andami.plugins.IExtension#getStatus(com.iver.andami.plugins.IExtension)
	 */
	@Override
	public IExtensionStatus getStatus(IExtension extension) {
		// TODO Auto-generated method stub
		return null;
	}

}
