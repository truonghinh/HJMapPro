package gov.vn.hcmgis.connection.gui;

import gov.vn.hcmgis.connection.ClassNameUtils;
import gov.vn.hcmgis.connection.ConnectionProperty;
import gov.vn.hcmgis.connection.IConnectionProperty;
import gov.vn.hcmgis.connection.ILogin;
import gov.vn.hcmgis.connection.ILoginController;
import gov.vn.hcmgis.connection.IUserInfo;
import gov.vn.hcmgis.connection.LoginController;
import gov.vn.hcmgis.connection.Loginer;
import gov.vn.hcmgis.connection.UserInfo;
import gov.vn.hcmgis.connection.TypeOfErrorOnUser;
import gov.vn.hcmgis.core.LoginActionEvent;
import gov.vn.hcmgis.gui.DefaultModalDialog;
import gov.vn.hcmgis.gui.IGui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Rectangle;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;

public class LoginView extends DefaultModalDialog implements ILoginView,IWindow {
	private static final long serialVersionUID = 1L;
//	protected WindowInfo _winfo = new WindowInfo(WindowInfo.MODALDIALOG);
	protected ILoginController _controller;
	protected ILogin _engine;
	protected IUserInfo _userInfo;
	protected IConnectionProperty _connPro;
	protected JTextField txtConnName=new JTextField();
	protected JTextField txtUrl=new JTextField();
	protected JTextField txtIp;
	protected JTextField txtPort=new JTextField();
	protected JTextField txtDbName=new JTextField();
	protected JTextField txtUser=new JTextField();
	protected JPasswordField PwdPassword=new JPasswordField();
	protected JPanel pnlConnectionParam = new JPanel();
	protected JLabel lblConnName = new JLabel();
	protected JLabel lblDriver = new JLabel();
	protected JComboBox cmbDriver = new JComboBox();
	protected JLabel lblUrl = new JLabel();
	protected JLabel lblPort = new JLabel();
	protected JLabel lblDbName = new JLabel();
	protected JLabel lblWarning = new JLabel();
	protected JLabel lblUser = new JLabel();
	protected JLabel lblPass = new JLabel();
	protected JLabel lblConnected = new JLabel();
	protected JCheckBox chkConnected = new JCheckBox();
	protected JLabel lblWarningCapslock = new JLabel();
	protected JButton btnClose = new JButton("Close");
	protected JButton btnOk = new JButton("OK");
	
	public LoginView() {
		super();
		_engine=new Loginer();
		_engine.SetView(this);
		_controller=new LoginController(_engine,this);
//		this._winfo.setWidth(100);
//		this._winfo.setHeight(100);
		super.SetWindowSize(357, 349);
//		((IGui)this).SetWindowSize(349, 343);
		this.init();
		
		_controller.AddCommandOfButton(ETypeOfButtonInLoginView.OK, btnOk.getText());
		_controller.AddCommandOfButton(ETypeOfButtonInLoginView.CLOSE, btnClose.getText());
		
//		_userInfo=new UserInfo();
		_connPro=ConnectionProperty.CallMe();
	}
	
	public LoginView(String title) {
		super();
		_engine=new Loginer();
		_engine.SetView(this);
		_controller=new LoginController(_engine,this);
//		this._winfo.setWidth(100);
//		this._winfo.setHeight(100);
		super.SetWindowSize(359, 334);
//		((IGui)this).SetWindowSize(349, 343);
		this.init();
		
		_controller.AddCommandOfButton(ETypeOfButtonInLoginView.OK, btnOk.getText());
		_controller.AddCommandOfButton(ETypeOfButtonInLoginView.CLOSE, btnClose.getText());
		
//		_userInfo=new UserInfo();
		_connPro=ConnectionProperty.CallMe();
		_winfo.setTitle(title);
	}
	
	private void init()
	{
		setLayout(null);
		
		
		pnlConnectionParam.setBorder(new TitledBorder(null, "Connection Params", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlConnectionParam.setBounds(10, 11, 335, 278);
		add(pnlConnectionParam);
		pnlConnectionParam.setLayout(null);
		
		
		lblConnName.setText("connection_name:");
		lblConnName.setBounds(new Rectangle(10, 30, 141, 21));
		lblConnName.setBounds(10, 33, 141, 21);
		pnlConnectionParam.add(lblConnName);
		
//		JOptionPane.showMessageDialog(null, txtConnName.toString());
		txtConnName.setEnabled(false);
		txtConnName.setBounds(new Rectangle(155, 30, 166, 21));
		txtConnName.setBounds(155, 33, 166, 21);
		pnlConnectionParam.add(txtConnName);
		
		
		lblDriver.setText("driver:");
		lblDriver.setBounds(new Rectangle(10, 55, 141, 21));
		lblDriver.setBounds(10, 58, 141, 21);
		pnlConnectionParam.add(lblDriver);
		cmbDriver.insertItemAt(ClassNameUtils.Postgresql, 0);
		cmbDriver.setSelectedIndex(0);
		
		
		cmbDriver.setEnabled(false);
		cmbDriver.setBounds(new Rectangle(155, 55, 166, 21));
		cmbDriver.setBounds(155, 58, 166, 21);
		pnlConnectionParam.add(cmbDriver);
		
		
		lblUrl.setText("server_url:");
		lblUrl.setBounds(new Rectangle(10, 80, 141, 21));
		lblUrl.setBounds(10, 83, 141, 21);
		pnlConnectionParam.add(lblUrl);
		
		txtIp = new JTextField();
		txtIp.setText("localhost");
		txtIp.setEnabled(true);
		txtIp.setBounds(new Rectangle(155, 80, 166, 21));
		txtIp.setBounds(155, 83, 166, 21);
		pnlConnectionParam.add(txtIp);
		
		
		lblPort.setText("port:");
		lblPort.setBounds(new Rectangle(10, 105, 141, 21));
		lblPort.setBounds(10, 108, 141, 21);
		pnlConnectionParam.add(lblPort);
		
		txtPort = new JTextField();
		txtPort.setText("5432");
		txtPort.setEnabled(false);
		txtPort.setBounds(new Rectangle(155, 105, 166, 21));
		txtPort.setBounds(155, 108, 166, 21);
		pnlConnectionParam.add(txtPort);
		
		
		lblDbName.setText("database_name:");
		lblDbName.setBounds(new Rectangle(10, 130, 141, 21));
		lblDbName.setBounds(10, 133, 141, 21);
		pnlConnectionParam.add(lblDbName);
		
		txtDbName = new JTextField();
		txtDbName.setText("quan8");
		txtDbName.setEnabled(false);
		txtDbName.setBounds(new Rectangle(155, 130, 166, 21));
		txtDbName.setBounds(155, 133, 166, 21);
		pnlConnectionParam.add(txtDbName);
		
		
		lblWarning.setText("warning_you_must_input_the_exact_name_this_difference_between_capital_letters_and_small_letters");
		lblWarning.setBounds(new Rectangle(10, 155, 310, 41));
		lblWarning.setBounds(10, 158, 310, 41);
		pnlConnectionParam.add(lblWarning);
		
		
		lblUser.setText("user:");
		lblUser.setBounds(new Rectangle(10, 197, 141, 21));
		lblUser.setBounds(10, 200, 141, 21);
		pnlConnectionParam.add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setText("postgres");
		txtUser.setBounds(new Rectangle(155, 197, 166, 21));
		txtUser.setBounds(155, 200, 166, 21);
		pnlConnectionParam.add(txtUser);
		
		
		lblPass.setText("password:");
		lblPass.setBounds(new Rectangle(10, 222, 141, 21));
		lblPass.setBounds(10, 225, 141, 21);
		pnlConnectionParam.add(lblPass);
		
		PwdPassword = new JPasswordField();
		PwdPassword.setText("postgres");
		PwdPassword.setBounds(new Rectangle(155, 222, 166, 21));
		PwdPassword.setBounds(155, 225, 166, 21);
		pnlConnectionParam.add(PwdPassword);
		
		
		lblConnected.setText("connected:");
		lblConnected.setBounds(new Rectangle(10, 247, 141, 21));
		lblConnected.setBounds(10, 250, 141, 21);
		pnlConnectionParam.add(lblConnected);
		
		
		chkConnected.setSelected(true);
		chkConnected.setBounds(new Rectangle(155, 247, 26, 21));
		chkConnected.setBounds(155, 250, 26, 21);
		pnlConnectionParam.add(chkConnected);
		
		
		lblWarningCapslock.setText("");
		lblWarningCapslock.setBounds(new Rectangle(189, 249, 130, 20));
		lblWarningCapslock.setBounds(191, 250, 130, 20);
		pnlConnectionParam.add(lblWarningCapslock);
		btnClose.addActionListener((ActionListener) _controller);
		
		
		btnClose.setBounds(201, 305, 91, 23);
		add(btnClose);
		btnOk.addActionListener((ActionListener) _controller);
		
		
		btnOk.setBounds(77, 305, 91, 23);
		add(btnOk);
	}
	
	public void OkAction(ActionEvent arg)
	{
		_userInfo=new UserInfo();
//		JOptionPane.showMessageDialog(null, _userInfo.GetName());
		_userInfo.SetName(txtUser.getText().trim());
//		JOptionPane.showMessageDialog(null, _userInfo.GetName());
		_userInfo.SetPass(new String(PwdPassword.getPassword()).trim());
//		JOptionPane.showMessageDialog(null, _userInfo.GetPass());
		_connPro.SetConnectionName(txtConnName.getText().trim());
//		JOptionPane.showMessageDialog(null, _connPro.GetConnectionName());
		_connPro.SetDatabaseName(txtDbName.getText().trim());
//		JOptionPane.showMessageDialog(null, _connPro.GetDatabaseName());
		_connPro.SetDriver(cmbDriver.getSelectedItem().toString());
//		JOptionPane.showMessageDialog(null, _connPro.GetDriver());
		_connPro.SetIp(txtIp.getText().trim());
//		JOptionPane.showMessageDialog(null, _connPro.GetIp());
		_connPro.SetPort(txtPort.getText().trim());
//		JOptionPane.showMessageDialog(null, _connPro.GetPort());
		_connPro.SetConnected(chkConnected.isSelected());
		_connPro.SetUserInfo(_userInfo);
//		JOptionPane.showMessageDialog(null, _connPro.GetConnected());
		_engine.SetConnectionProperty(_connPro);
		_engine.Login();
		
		
	}

	/**
	 * 
	 */
	

	public void Show() {
		// TODO Auto-generated method stub
//		PluginServices.getMDIManager().addCentredWindow(this);
		if(_connPro.GetUserInfo()!=null)
		{
			txtUser.setText(_connPro.GetUserInfo().GetName());
			PwdPassword.setText(_connPro.GetUserInfo().GetPass());
		}
		super.Show();

	}

	public void ShowDialog() {
		// TODO Auto-generated method stub
//		PluginServices.getMDIManager().addCentredWindow(this);
		if(_connPro.GetUserInfo()!=null)
		{
			txtUser.setText(_connPro.GetUserInfo().GetName());
			PwdPassword.setText(_connPro.GetUserInfo().GetPass());
		}
		super.ShowDialog();
	}

	public void Close() {
		// TODO Auto-generated method stub
		PluginServices.getMDIManager().closeWindow(this);
	}

	public void Hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void GetInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Login() {
		// TODO Auto-generated method stub
		LoginActionEvent eLogin=new LoginActionEvent(btnOk, 0, btnOk.getText());
		eLogin.setMessage("xin chao");
		((ActionListener) _controller).actionPerformed(eLogin);
	}

	@Override
	public void SetDefaultInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IConnectionProperty GetConnectionProperty() {
		// TODO Auto-generated method stub
		return _connPro;
	}

	@Override
	public void ReceiveChekingResult(int arg) {
		// TODO Auto-generated method stub
		if(arg==TypeOfErrorOnUser.NoError)
		{
			this.Close();
			System.out.println(String.format("line 294 LoginView: %s", "Đăng nhập ok"));
		}
		else if(arg==TypeOfErrorOnUser.WrongPass)
		{
			System.out.println(String.format("line 297 LoginView: %s", "sai pass"));
		}
		else if(arg==TypeOfErrorOnUser.WrongUserName)
		{
			System.out.println(String.format("line 297 LoginView: %s", "sai username"));
		}
		else if(arg==TypeOfErrorOnUser.WrongPass)
		{
			System.out.println(String.format("line 297 LoginView: %s", "sai pass"));
		}
		else if(arg==TypeOfErrorOnUser.WrongUserNameAndPass)
		{
			System.out.println(String.format("line 297 LoginView: %s", "sai ca username va pass"));
		}
		else
		{
			System.out.println(String.format("line 297 LoginView: %s", "chua handle duoc"));
		}
	}

	@Override
	public void SetConnectionProperty(IConnectionProperty connPro) {
		// TODO Auto-generated method stub
		_connPro=connPro;
	}

	@Override
	public void ClearParams() {
		// TODO Auto-generated method stub
		txtConnName.setText("");
		txtDbName.setText("");
		txtIp.setText("");
		txtPort.setText("");
		txtUrl.setText("");
		txtUser.setText("");
		cmbDriver.removeAllItems();
	}

	@Override
	public void LoginInTest() {
		// TODO Auto-generated method stub
//		JOptionPane.showMessageDialog(null, _connPro.GetUserInfo().GetName());
		_engine.SetConnectionProperty(_connPro);
		_engine.Login();
	}

	@Override
	public WindowInfo getWindowInfo() {
		// TODO Auto-generated method stub
		return this._winfo;
	}
//
	@Override
	public Object getWindowProfile() {
		// TODO Auto-generated method stub
		return super.getWindowProfile();
	}
//
//	@Override
//	public void SetWindowSize(int width, int height) {
//		// TODO Auto-generated method stub
//		_winfo.setWidth(width);
//		_winfo.setHeight(height);
//	}
//
//	@Override
//	public void SetWindowHeight(int height) {
//		// TODO Auto-generated method stub
//		_winfo.setWidth(height);
//	}
//
//	@Override
//	public void SetWindowWidth(int width) {
//		// TODO Auto-generated method stub
//		_winfo.setWidth(width);
//	}
//
//	@Override
//	public int GetHeight() {
//		// TODO Auto-generated method stub
//		return _winfo.getHeight();
//	}
//
//	@Override
//	public int GetWidth() {
//		// TODO Auto-generated method stub
//		return _winfo.getWidth();
//	}
}
