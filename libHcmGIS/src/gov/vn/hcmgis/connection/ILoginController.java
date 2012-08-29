package gov.vn.hcmgis.connection;

import gov.vn.hcmgis.connection.gui.ETypeOfButtonInLoginView;

public interface ILoginController {

	void AddCommandOfButton(ETypeOfButtonInLoginView typeOfButton,String command);
	void ResetButtonCommands();
	void Login();
}
