package gov.vn.hcmgis.connection;

import gov.vn.hcmgis.connection.gui.ETypeOfButtonInLoginView;
import gov.vn.hcmgis.connection.gui.ILoginView;
import gov.vn.hcmgis.core.LoginActionEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JOptionPane;


public class LoginController implements ActionListener, ILoginController {

	private ILogin _engine;
	private ILoginView _view;
	private Hashtable<ETypeOfButtonInLoginView,String > _hashButton;
	public LoginController(ILogin engine, ILoginView view)
	{
		_engine=engine;
		_view=view;
		ResetButtonCommands();
	}
	public LoginController(){}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		JOptionPane.showMessageDialog(null, e.getSource().toString());
		if(e.getActionCommand()==_hashButton.get(ETypeOfButtonInLoginView.OK))
		{
		_view.OkAction(e);
		}
		else if(e.getActionCommand()==_hashButton.get(ETypeOfButtonInLoginView.CLOSE))
		{
			_view.Close();
		}
		else if(e.getActionCommand()==_hashButton.get(ETypeOfButtonInLoginView.CANCEL))
		{
			_view.Close();
		}
		else
		{
			JOptionPane.showMessageDialog(null, e.getActionCommand());
		}
//		LoginActionEvent elogin=(LoginActionEvent)e;
//		JOptionPane.showMessageDialog(null, elogin.getMessage());
	}
	@Override
	public void AddCommandOfButton(ETypeOfButtonInLoginView typeOfButton,String command) {
		// TODO Auto-generated method stub
		if(_hashButton.containsKey(typeOfButton))
		{
			_hashButton.remove(typeOfButton);
		}
//		JOptionPane.showMessageDialog(null, command);
		try
		{
			_hashButton.put(typeOfButton, command);
		}
		catch (NullPointerException e) {
			// TODO: handle exception
		}
	}
	@Override
	public void ResetButtonCommands() {
		// TODO Auto-generated method stub
		_hashButton=new Hashtable<ETypeOfButtonInLoginView,String>();
		_hashButton.put(ETypeOfButtonInLoginView.OK, "OK");
		_hashButton.put(ETypeOfButtonInLoginView.CANCEL, "Cancel");
		_hashButton.put(ETypeOfButtonInLoginView.CLOSE, "Close");
	}
	@Override
	public void Login() {
		// TODO Auto-generated method stub
		_engine.SetConnectionProperty(_view.GetConnectionProperty());
		_engine.Login();
	}

}
