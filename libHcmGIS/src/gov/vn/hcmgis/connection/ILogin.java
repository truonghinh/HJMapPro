/***********************************************************************
 * Module:  ILogin.java
 * Author:  hdk
 * Purpose: Defines the Interface ILogin
 ***********************************************************************/

package gov.vn.hcmgis.connection;

import gov.vn.hcmgis.connection.gui.ILoginView;

import java.util.*;

/** @pdOid 43032b66-dcae-473e-b9c8-65e244892bc8 */
public interface ILogin {
	void SetConnectionProperty(IConnectionProperty connPro);
	void Login();
	void SetView(ILoginView view);
}
