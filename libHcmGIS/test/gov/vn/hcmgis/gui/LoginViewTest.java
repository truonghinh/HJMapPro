package gov.vn.hcmgis.gui;

import static org.junit.Assert.*;
import gov.vn.hcmgis.connection.ClassNameUtils;
import gov.vn.hcmgis.connection.ConnectionProperty;
import gov.vn.hcmgis.connection.IConnectionProperty;
import gov.vn.hcmgis.connection.IUserInfo;
import gov.vn.hcmgis.connection.UserInfo;
import gov.vn.hcmgis.connection.gui.ILoginView;
import gov.vn.hcmgis.connection.gui.LoginView;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;

import com.iver.cit.gvsig.project.documents.view.gui.View;

public class LoginViewTest extends UnitilsJUnit4 {

	ILoginView view;
	@Before
	public void setUp() throws Exception 
	{
		view=new LoginView();
		view.ClearParams();
		IConnectionProperty _connPro=ConnectionProperty.CallMe();
		_connPro.SetDriver(ClassNameUtils.PgDriverClassName);
		_connPro.SetConnectionName("new connect");
		_connPro.SetDatabaseName("quan8");
		_connPro.SetIp("localhost");
		IUserInfo _user=new UserInfo();
		_user.SetName("postgres");
		_user.SetPass("postgres");
		_connPro.SetUserInfo(_user);
		
		view.SetConnectionProperty(_connPro);
	}

	@Test
	public void testPerform() {
//		view.Show();
		view.LoginInTest();
	}

}
