/**
 * 
 */
package gov.vn.hcmgis.projects.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import org.gvsig.gui.beans.swing.textBoxWithCalendar.JCalendarDateDialog;

import gov.vn.hcmgis.database.dataModel.DataLoader;
import gov.vn.hcmgis.database.dataModel.IDataLoader;
import gov.vn.hcmgis.database.dataModel.IDataReceiver;
import gov.vn.hcmgis.database.view.IObjectDetailView;
import gov.vn.hcmgis.gui.DefaultDialog;
import gov.vn.hcmgis.projects.actions.ActOnBtnAddObjectDetail;
import gov.vn.hcmgis.projects.actions.ActOnBtnCancelObjectDetail;
import gov.vn.hcmgis.projects.actions.ActOnBtnDeleteObjectDetail;
import gov.vn.hcmgis.projects.actions.ActOnBtnSaveObjectDetail;
import gov.vn.hcmgis.projects.actions.ActPool4btn;
import gov.vn.hcmgis.projects.actions.KeyListener4btn;
import gov.vn.hcmgis.util.UpdateQueryString;

/**
 * @author HT
 *
 */
public abstract class AbstractObjectDetailView extends DefaultDialog implements IObjectDetailView,IDataReceiver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//region fields for gui
	protected JSplitPane jSplitPane = null;
	protected JPanel jPanel = null;
	protected JPanel jPanel1 = null;
	protected JButton btnThem = null;
	protected JButton btnLuu = null;
	protected JButton btnXoa = null;
	protected JButton btnHuy = null;
	protected JLabel jLabel = null;
	protected JTextField txtMa = null;
	protected JLabel jLabel1 = null;
	protected JTextField txtChucoso = null;
	protected JLabel jLabel2 = null;
	protected JTextField txtLoaihinh = null;
	protected JLabel jLabel3 = null;
	protected JTextField txtBanghieu = null;
	protected JLabel jLabel4 = null;
	protected JLabel jLabel5 = null;
	protected JLabel jLabel6 = null;
	protected JTextField txtSonha = null;
	protected JLabel jLabel7 = null;
	protected JLabel jLabel8 = null;
	protected JTextField txtDkkd = null;
	protected JFormattedTextField  txtNgaycapDKKD = null;
	protected JLabel jLabel12 = null;
	protected JTextField txtGhichu = null;
	protected JPanel jPanel3 = null;
	protected String dburl = "";
	protected String dbuser = "";  //  @jve:decl-index=0:
	protected String dbpass = "";
	protected JTextField txtPhuong = null;
	protected JCalendarDateDialog jCalendarDateDialog = null;
	protected JLabel jLabel9 = null;
	protected JTextField txtSoMay = null;
	protected JTextField txtDuong = null;
	protected JLabel jLabel10 = null;
	protected JTextField txtDt = null;
	protected JLabel jLabel11 = null;
	protected JFormattedTextField txtVon = null;
	protected JButton btnExit = null;
	boolean bAdd=false;
//	protected KeyListener mykeylistener = new MyKeyListener();
	protected Object _keyValue;
	protected String _keyName;
	
	
//	public Action acSave = new AcSave("Lưu", null,"Lưu ", KeyEvent.VK_S);
//	public Action acAdd = new AcAdd("Thêm", null,"Thêm ", KeyEvent.VK_S);
//	public Action acCancel = new AcCancel("Hủy", null,"Hủy ", KeyEvent.VK_S);
//	public Action acDelete = new AcDelete("Xóa", null,"Xóa ", KeyEvent.VK_S);
	//endregion
	protected ILocationUpdateView _hook;
	protected IDataLoader _dataLoader;
	protected ActPool4btn _actPool;
	protected KeyListener4btn _keyListener;
	protected ActOnBtnAddObjectDetail _actAdd;
	protected ActOnBtnDeleteObjectDetail _actDel;
	protected ActOnBtnSaveObjectDetail _actSave;
	protected ActOnBtnCancelObjectDetail _actCancel;
	public AbstractObjectDetailView(ILocationUpdateView hook)
	{
		_hook=hook;
		initialize();
		if(hook!=null)
		{
			this.setLocationRelativeTo(hook.GetTable());
		}
		_dataLoader=new DataLoader(this);
		initAction();
	}
	public AbstractObjectDetailView()
	{
		this(null);
	}
	
	private void initAction()
	{
		_actAdd=new ActOnBtnAddObjectDetail(this,"Thêm", null);
		_actDel=new ActOnBtnDeleteObjectDetail(this,"Xóa",null);
		_actSave=new ActOnBtnSaveObjectDetail(this,"Lưu", null);
		_actCancel=new ActOnBtnCancelObjectDetail(this,"Hủy", null);
		_actPool=ActPool4btn.CallMe(_actAdd, _actDel, _actSave, _actCancel);
		_keyListener=new KeyListener4btn(_actPool);
		
		btnThem.setAction(_actAdd);
		btnXoa.setAction(_actDel);
		btnLuu.setAction(_actSave);
		btnHuy.setAction(_actCancel);
		
		txtBanghieu.addKeyListener(_keyListener);
		txtChucoso.addKeyListener(_keyListener);
		txtDkkd.addKeyListener(_keyListener);
		txtDt.addKeyListener(_keyListener);
		txtDuong.addKeyListener(_keyListener);
		txtGhichu.addKeyListener(_keyListener);
		txtNgaycapDKKD.addKeyListener(_keyListener);
		txtPhuong.addKeyListener(_keyListener);
		txtSonha.addKeyListener(_keyListener);
		txtVon.addKeyListener(_keyListener);
	}
	
	/**
	 * @return the _hook
	 */
	public ILocationUpdateView get_hook() {
		return _hook;
	}
	/**
	 * @param _hook the _hook to set
	 */
	public void set_hook(ILocationUpdateView hook) {
		this._hook = hook;
		this.setLocationRelativeTo(hook.GetTable());
	}


	@Override
	public void Receive(String key, ResultSet result) {
		// TODO Auto-generated method stub
		if(key.equals("load"))
		{
			try {
				while( result.next() )
				 {	  
					txtChucoso.setText(result.getString(2));
					txtBanghieu.setText(result.getString(3));
					txtSonha.setText(result.getString(4));
					txtDuong.setText(result.getString(5));
					txtDkkd.setText(result.getString(6));
					txtNgaycapDKKD.setText(result.getString(7));
					txtGhichu.setText(result.getString(8));
					txtLoaihinh.setText(result.getString(9));
					txtPhuong.setText(result.getString(11));
					txtDt.setText(result.getString(12));
					txtVon.setText(result.getString(13));
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(key.equals("save"))
		{
			
		}
		else if(key.equals("add"))
		{
			
		}
		else if(key.equals("update"))
		{
			
		}
		else if(key.equals("cancel"))
		{
			
		}
		
	}
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.IObjectDetailView#ReadyLoadDetail(java.lang.Object)
	 */
	@Override
	public void ReadyLoadDetail(Object arg) {
		// TODO Auto-generated method stub
//		String SQLString_ = "SELECT "+_hook.GetObjectTable().get_primaryKey()+", ten_chu, banghieu, so_nha, ten_duong, so_dkkd, ngaycap_dkkd, ghichu, loaihinh_kd, so_may, phuong,so_dt,von FROM "+_hook.GetExactTableName()+" where "+_hook.GetObjectTable().get_primaryKey()+"='"+ma+"'";
		String SQLString_ = "SELECT %s, ten_chu, banghieu, so_nha, ten_duong, so_dkkd, ngaycap_dkkd, ghichu, loaihinh_kd, phuong,so_dt,von FROM %s where %s='%s'";
		String SQLString=String.format(SQLString_,this._keyName,_hook.GetExactTableName(),this._keyName,this._keyValue);
		this._dataLoader.LoadData("load", SQLString);
	}
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.IObjectDetailView#ReadySaveDetail()
	 */
	@Override
	public void ReadySaveDetail() {
		// TODO Auto-generated method stub
		
		if(txtChucoso.getText().trim().equals("") || txtChucoso.getText()==null)
		{
			JOptionPane.showMessageDialog(null,"Hãy nhập tên chủ cơ sở");
			txtChucoso.requestFocus();
			return;
		}
		else if(txtSonha.getText().trim().equals("") || txtSonha.getText()==null)
		{
			JOptionPane.showMessageDialog(null,"Hãy nhập số nhà");
			txtSonha.requestFocus();
			return;
		}
		else if(txtDuong.getText().trim().equals("") || txtDuong.getText()==null)
		{
			JOptionPane.showMessageDialog(null,"Hãy nhập tên đường");
			txtDuong.requestFocus();
			return;
		}
		else if(txtPhuong.getText().trim().equals("") || txtPhuong.getText()==null)
		{
			JOptionPane.showMessageDialog(null,"Hãy nhập phường");
			txtPhuong.requestFocus();
			return;
		}
		this._actPool.SaveActionPerformed(null);
	}
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.IObjectDetailView#ReadyAddDetail()
	 */
	@Override
	public void ReadyAddDetail() {
		// TODO Auto-generated method stub
		System.out.println("line 247 AbstractObjectDetail, addDetail");
		this._actPool.AddActionPerformed(null);
		txtSoMay.setText("");
		txtSonha.setText("");
		txtDuong.setText("");
		txtGhichu.setText("");
		txtChucoso.setText("");
		txtChucoso.requestFocus();
		txtMa.setText("");
		txtBanghieu.setText("");
		txtDkkd.setText("");
		txtDt.setText("");
		txtVon.setValue(null);
		txtNgaycapDKKD.setText("");
//		txtThoihan.setText("");
		txtChucoso.requestFocus();
	}
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.IObjectDetailView#ReadyUpdateDetail()
	 */
	@Override
	public void ReadyUpdateDetail() {
		// TODO Auto-generated method stub
		this._actPool.SaveActionPerformed(null);
		
//		String UpdateSQLString_ = " INSERT INTO "+_hook.GetExactTableName()+"( ten_chu, banghieu, so_nha, ten_duong, so_dkkd, ngaycap_dkkd, ghichu, loaihinh_kd, so_may, phuong,so_dt,von)";
//	    UpdateSQLString_ += "  VALUES (?, ?, ?, ?, ?, to_date(?,'DD MM YYYY'), ?, ?, ?, ?,?,?);";
//		UpdateQueryString qrs=new UpdateQueryString(this._hook.GetObjectTable());
//		String UpdateSQLString=qrs.GetSimpleFullUpdateQueryString(txtMa.getText());
//		System.out.println(String.format("line 278 AbstractObjectDetail. sqlupdate=%s", UpdateSQLString));
//		
//		HashMap<Integer,Entry<Integer,Object>> values=new HashMap<Integer, Entry<Integer,Object>>();
//		values.put(1, value)
//		_dataLoader.UpdateData("update", UpdateSQLString,values);
//	    String UpdateSQLString_ = " INSERT INTO "+_hook.GetExactTableName()+"( ten_chu, banghieu, so_nha, ten_duong, so_dkkd, ngaycap_dkkd, ghichu, loaihinh_kd, so_may, phuong,so_dt,von)";
//	    UpdateSQLString_ += "  VALUES (?, ?, ?, ?, ?, to_date(?,'DD MM YYYY'), ?, ?, ?, ?,?,?);";
	    
	    
//	    ps = c.prepareStatement(UpdateSQLString);
//	   //tenchu
//	    ps.setString(1,txtChucoso.getText());
//	    ps.setString(2,txtBanghieu.getText());
//	    ps.setString(3,txtSonha.getText());
//	    ps.setString(4,txtDuong.getText());
//	    ps.setString(5,txtDkkd.getText());
//	    if(txtNgaycapDKKD.getText()==null || txtNgaycapDKKD.getText().trim().equals("") )
//		   {
//	    	 ps.setString(6,null);
//		    					   }
//	    else{
//	    ps.setString(6,txtNgaycapDKKD.getText());
//	    }
//	    ps.setString(7,txtGhichu.getText());
//	    ps.setString(8,txtLoaihinh.getText());
//	    ps.setString(9,txtSoMay.getText());
//	    ps.setInt(10,Integer.valueOf(txtPhuong.getText()));
//	    ps.setString(11,txtDt.getText());
//	    if(txtVon.getValue()== null)
//	    {
//	    ps.setNull(12,Types.DOUBLE);
//	    }
//	    else 
//	    {
//		    ps.setDouble(12,((Number) txtVon.getValue()).doubleValue());
//
//	    }
	}
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.IObjectDetailView#Cancel()
	 */
	@Override
	public void Cancel() {
		// TODO Auto-generated method stub
		this._actPool.CancelActionPerformed(null);
	}
	
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.database.view.IObjectDetailView#ReadyDeleteDetail()
	 */
	@Override
	public void ReadyDeleteDetail() {
		// TODO Auto-generated method stub
		this._actPool.DeleteActionPerformed(null);
	}
	//region gui
	private void initialize() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.gridx = 0;
//		this.setSize(780, 235);
		this.SetWindowSize(780, 235);
		getContentPane().setLayout(new GridBagLayout());
//		this.setTitle("Thông tin chi tiết");
		getContentPane().add(getJSplitPane(), gridBagConstraints);
	}
	
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setDividerLocation(165);
			jSplitPane.setContinuousLayout(false);
			jSplitPane.setTopComponent(getJPanel());
			jSplitPane.setBottomComponent(getJPanel1());
			jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		}
		return jSplitPane;
	}
	
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
			gridBagConstraints28.fill = GridBagConstraints.BOTH;
			gridBagConstraints28.gridy = 1;
			gridBagConstraints28.weightx = 1.0;
			gridBagConstraints28.gridwidth = 1;
			gridBagConstraints28.gridx = 5;
			GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
			gridBagConstraints27.gridx = 4;
			gridBagConstraints27.fill = GridBagConstraints.BOTH;
			gridBagConstraints27.insets = new Insets(0, 3, 0, 3);
			gridBagConstraints27.gridy = 1;
			jLabel11 = new JLabel();
			jLabel11.setText("Vốn");
			GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
			gridBagConstraints26.fill = GridBagConstraints.BOTH;
			gridBagConstraints26.gridy = 1;
			gridBagConstraints26.weightx = 1.0;
			gridBagConstraints26.gridx = 1;
			GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
			gridBagConstraints23.gridx = 0;
			gridBagConstraints23.fill = GridBagConstraints.BOTH;
			gridBagConstraints23.insets = new Insets(0, 0, 0, 3);
			gridBagConstraints23.gridy = 1;
			jLabel10 = new JLabel();
			jLabel10.setText("Điện thoại");
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.fill = GridBagConstraints.BOTH;
			gridBagConstraints17.gridy = 2;
			gridBagConstraints17.weightx = 1.0;
			gridBagConstraints17.gridwidth = 3;
			gridBagConstraints17.gridx = 3;
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.fill = GridBagConstraints.BOTH;
			gridBagConstraints22.gridy = 1;
			gridBagConstraints22.weightx = 1.0;
			gridBagConstraints22.gridx = 3;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 2;
			gridBagConstraints21.fill = GridBagConstraints.BOTH;
			gridBagConstraints21.insets = new Insets(0, 3, 0, 3);
			gridBagConstraints21.gridy = 1;
			jLabel9 = new JLabel();
			jLabel9.setText("Số máy");
			jLabel9.setVisible(false);
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.fill = GridBagConstraints.BOTH;
			gridBagConstraints18.gridy = 2;
			gridBagConstraints18.weightx = 1.0;
			gridBagConstraints18.gridwidth = 4;
			gridBagConstraints18.gridx = 7;
			GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
			gridBagConstraints32.gridx = 0;
			gridBagConstraints32.gridwidth = 14;
			gridBagConstraints32.fill = GridBagConstraints.BOTH;
			gridBagConstraints32.gridheight = 2;
			gridBagConstraints32.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints32.gridy = 3;
			jLabel12 = new JLabel();
			jLabel12.setText("Ghi chú");
			jLabel8 = new JLabel();
			jLabel8.setText("Ngày ĐKKD");
			jLabel7 = new JLabel();
			jLabel7.setText("Số ĐKKD");
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.fill = GridBagConstraints.BOTH;
			gridBagConstraints16.gridy = 2;
			gridBagConstraints16.weightx = 1.0;
			gridBagConstraints16.gridx = 1;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 6;
			gridBagConstraints15.fill = GridBagConstraints.BOTH;
			gridBagConstraints15.insets = new Insets(0, 3, 0, 3);
			gridBagConstraints15.gridy = 2;
			jLabel6 = new JLabel();
			jLabel6.setText("Phường");
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridx = 2;
			gridBagConstraints14.fill = GridBagConstraints.BOTH;
			gridBagConstraints14.insets = new Insets(0, 3, 0, 3);
			gridBagConstraints14.gridy = 2;
			jLabel5 = new JLabel();
			jLabel5.setText("Đường");
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 0;
			gridBagConstraints13.fill = GridBagConstraints.BOTH;
			gridBagConstraints13.insets = new Insets(0, 0, 0, 3);
			gridBagConstraints13.gridy = 2;
			jLabel4 = new JLabel();
			jLabel4.setText("Số nhà");
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.BOTH;
			gridBagConstraints12.gridy = 0;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.gridwidth = 3;
			gridBagConstraints12.gridx = 5;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 4;
			gridBagConstraints11.fill = GridBagConstraints.BOTH;
			gridBagConstraints11.insets = new Insets(0, 3, 0, 3);
			gridBagConstraints11.gridy = 0;
			jLabel3 = new JLabel();
			jLabel3.setText("Bảng hiệu");
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = GridBagConstraints.BOTH;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.weightx = 1.0;
			gridBagConstraints10.gridx = 7;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 6;
			gridBagConstraints9.fill = GridBagConstraints.BOTH;
			gridBagConstraints9.insets = new Insets(0, 3, 0, 3);
			gridBagConstraints9.gridy = 1;
			jLabel2 = new JLabel();
			jLabel2.setText("Loại hình KD");
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.fill = GridBagConstraints.BOTH;
			gridBagConstraints8.gridy = 0;
			gridBagConstraints8.weightx = 1.0;
			gridBagConstraints8.gridx = 3;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 2;
			gridBagConstraints7.fill = GridBagConstraints.BOTH;
			gridBagConstraints7.insets = new Insets(0, 3, 0, 3);
			gridBagConstraints7.gridy = 0;
			jLabel1 = new JLabel();
			jLabel1.setText("Chủ cơ sở");
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.gridx = 1;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.insets = new Insets(0, 0, 0, 3);
			gridBagConstraints5.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("Mã hộ");
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.setBorder(BorderFactory.createTitledBorder(null, "Thông tin chi tiết", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel.add(jLabel, gridBagConstraints5);
			jPanel.add(getTxtMa(), gridBagConstraints6);
			jPanel.add(jLabel1, gridBagConstraints7);
			jPanel.add(getTxtChucoso(), gridBagConstraints8);
			jPanel.add(jLabel2, gridBagConstraints9);
			jPanel.add(getTxtLoaihinh(), gridBagConstraints10);
			jPanel.add(jLabel3, gridBagConstraints11);
			jPanel.add(getTxtBanghieu(), gridBagConstraints12);
			jPanel.add(jLabel4, gridBagConstraints13);
			jPanel.add(jLabel5, gridBagConstraints14);
			jPanel.add(jLabel6, gridBagConstraints15);
			jPanel.add(getTxtSonha(), gridBagConstraints16);
			jPanel.add(getJPanel3(), gridBagConstraints32);
			jPanel.add(getTxtPhuong(), gridBagConstraints18);
			jPanel.add(jLabel9, gridBagConstraints21);
			jPanel.add(getTxtSoMay(), gridBagConstraints22);
			jPanel.add(getTxtDuong(), gridBagConstraints17);
			jPanel.add(jLabel10, gridBagConstraints23);
			jPanel.add(getTxtDt(), gridBagConstraints26);
			jPanel.add(jLabel11, gridBagConstraints27);
			jPanel.add(getTxtVon(), gridBagConstraints28);
		}
		return jPanel;
	}
	
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 5;
			gridBagConstraints31.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints31.fill = GridBagConstraints.BOTH;
			gridBagConstraints31.gridy = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 4;
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints4.gridy = 0;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints3.gridy = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 2;
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints2.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints1.gridy = 0;
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(getBtnThem(), gridBagConstraints1);
			jPanel1.add(getBtnLuu(), gridBagConstraints2);
			jPanel1.add(getBtnXoa(), gridBagConstraints3);
			jPanel1.add(getBtnHuy(), gridBagConstraints4);
			jPanel1.add(getBtnExit(), gridBagConstraints31);
		}
		return jPanel1;
	}
	
	private JButton getBtnThem() {
		if (btnThem == null) {
			btnThem = new JButton();
			btnThem.setText("Thêm");
//			btnThem.setAction(acAdd);
		}
		return btnThem;
	}
	
	private JTextField getTxtDuong() {
		if (txtDuong == null) {
			txtDuong = new JTextField();
//			txtDuong.addKeyListener(mykeylistener);
		}
		return txtDuong;
	}
	
	private JFormattedTextField getTxtVon() {
		if (txtVon == null) {
			txtVon = new JFormattedTextField(NumberFormat.getInstance());
//			txtVon.addKeyListener(mykeylistener);
		}
		return txtVon;
	}
	
	private JTextField getTxtDt() {
		if (txtDt == null) {
			txtDt = new JTextField();
//			txtDt.addKeyListener(mykeylistener);
		}
		return txtDt;
	}
	
	private JButton getBtnLuu() {
		if (btnLuu == null) {
			btnLuu = new JButton();
			btnLuu.setText("Lưu");
//			btnLuu.setAction(acSave);
		}
		return btnLuu;
	}
	
	private JButton getBtnXoa() {
		if (btnXoa == null) {
			btnXoa = new JButton();
			btnXoa.setText("Xoá");
//			btnXoa.setAction(acDelete);
		}
		return btnXoa;
	}
	
	private JButton getBtnHuy() {
		if (btnHuy == null) {
			btnHuy = new JButton();
			btnHuy.setText("Huỷ");
//			btnHuy.setAction(acCancel);
		}
		return btnHuy;
	}
	
	private JTextField getTxtMa() {
		if (txtMa == null) {
			txtMa = new JTextField();
			txtMa.setEditable(false);
		}
		return txtMa;
	}
	/**
	 * This method initializes txtChucoso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtChucoso() {
		if (txtChucoso == null) {
			txtChucoso = new JTextField();
//			txtChucoso.addKeyListener(mykeylistener);
		}
		return txtChucoso;
	}

	/**
	 * This method initializes txtLoaihinh	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtLoaihinh() {
		if (txtLoaihinh == null) {
			txtLoaihinh = new JTextField();
			txtLoaihinh.setEditable(false);
//			txtLoaihinh.addKeyListener(mykeylistener);
		}
		return txtLoaihinh;
	}

	/**
	 * This method initializes txtBanghieu	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtBanghieu() {
		if (txtBanghieu == null) {
			txtBanghieu = new JTextField();
//			txtBanghieu.addKeyListener(mykeylistener);
		}
		return txtBanghieu;
	}
	
	private JTextField getTxtSonha() {
		if (txtSonha == null) {
			txtSonha = new JTextField();
//			txtSonha.addKeyListener(mykeylistener);
		}
		return txtSonha;
	}
	
	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton();
			btnExit.setText("Đóng");
//			btnExit.addActionListener(new java.awt.event.ActionListener() {
		}
		return btnExit;
	}
		
		private JPanel getJPanel3() {
			if (jPanel3 == null) {
				GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
				gridBagConstraints30.fill = GridBagConstraints.BOTH;
				gridBagConstraints30.gridx = 1;
				gridBagConstraints30.gridy = 1;
				gridBagConstraints30.weightx = 1.0;
				gridBagConstraints30.gridwidth = 11;
				GridBagConstraints gridBagConstraints29 = new GridBagConstraints();
				gridBagConstraints29.fill = GridBagConstraints.BOTH;
				gridBagConstraints29.gridx = 0;
				gridBagConstraints29.gridy = 1;
				gridBagConstraints29.insets = new Insets(0, 0, 0, 3);
				GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
				gridBagConstraints25.fill = GridBagConstraints.BOTH;
				gridBagConstraints25.gridy = 0;
				gridBagConstraints25.weightx = 1.0;
				gridBagConstraints25.gridx = 3;
				GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
				gridBagConstraints20.fill = GridBagConstraints.BOTH;
				gridBagConstraints20.gridy = 0;
				gridBagConstraints20.insets = new Insets(0, 3, 0, 3);
				gridBagConstraints20.gridx = 2;
				GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
				gridBagConstraints24.fill = GridBagConstraints.BOTH;
				gridBagConstraints24.gridy = 0;
				gridBagConstraints24.weightx = 1.0;
				gridBagConstraints24.gridx = 1;
				GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
				gridBagConstraints19.fill = GridBagConstraints.BOTH;
				gridBagConstraints19.gridy = 0;
				gridBagConstraints19.insets = new Insets(0, 0, 0, 3);
				gridBagConstraints19.gridx = 0;
				jPanel3 = new JPanel();
				jPanel3.setLayout(new GridBagLayout());
				jPanel3.setBorder(BorderFactory.createTitledBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED), "Giấy phép ĐKKD", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));

				jPanel3.add(jLabel7, gridBagConstraints19);
				jPanel3.add(getTxtDkkd(), gridBagConstraints24);
				jPanel3.add(jLabel8, gridBagConstraints20);
				jPanel3.add(getTxtNgaycapDKKD(), gridBagConstraints25);
				jPanel3.add(jLabel12, gridBagConstraints29);
				jPanel3.add(getTxtGhichu(), gridBagConstraints30);
			}
			return jPanel3;
		}
		
		private JTextField getTxtDkkd() {
			if (txtDkkd == null) {
				txtDkkd = new JTextField();
//				txtDkkd.addKeyListener(mykeylistener);
			}
			return txtDkkd;
		}
		
		private JFormattedTextField getTxtNgaycapDKKD() {
			if (txtNgaycapDKKD == null) {
				txtNgaycapDKKD = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
//				txtNgaycapDKKD.addKeyListener(mykeylistener);
//				txtNgaycapDKKD.addMouseListener(new java.awt.event.MouseAdapter() {
			}
			return txtNgaycapDKKD;
		}
			
			private JTextField getTxtGhichu() {
				if (txtGhichu == null) {
					txtGhichu = new JTextField();
//					txtGhichu.addKeyListener(mykeylistener);
				}
				return txtGhichu;
			}
			
			private JTextField getTxtPhuong() {
				if (txtPhuong == null) {
					txtPhuong = new JTextField();
//					txtPhuong.addKeyListener(mykeylistener);
					txtPhuong.setEditable(false);
				}
				return txtPhuong;
			}
			
			private JTextField getTxtSoMay() {
				if (txtSoMay == null) {
					txtSoMay = new JTextField();
					txtSoMay.setVisible(false);
//					txtSoMay.addKeyListener(mykeylistener);
				}
				return txtSoMay;
			}
			
//			class MyKeyListener implements KeyListener {
//
//				public void keyPressed(KeyEvent e) {
//					// TODO Auto-generated method stub
//				}
//
//				public void keyReleased(KeyEvent e) {
//					// TODO Auto-generated method stub
//				}
//
//				public void keyTyped(KeyEvent e) {
////					acSave.setEnabled(true);			
////					acCancel.setEnabled(true);
////					acDelete.setEnabled(false);
////					acAdd.setEnabled(false);
//				}
//
//			}
			
			class AcSave extends AbstractAction {
				public AcSave(String text, ImageIcon icon, String desc,
						Integer mnemonic) {
					super(text, icon);
				}

				public void actionPerformed(java.awt.event.ActionEvent e) {
						Save();
						
							}}
			
			public void Save()
			{
				if(txtChucoso.getText().trim().equals("") || txtChucoso.getText()==null)
				{
					JOptionPane.showMessageDialog(null,"Hãy nhập tên chủ cơ sở");
					txtChucoso.requestFocus();
				}
				else if(txtSonha.getText().trim().equals("") || txtSonha.getText()==null)
				{
					JOptionPane.showMessageDialog(null,"Hãy nhập số nhà");
					txtSonha.requestFocus();
				}
				else if(txtDuong.getText().trim().equals("") || txtDuong.getText()==null)
				{
					JOptionPane.showMessageDialog(null,"Hãy nhập tên đường");
					txtDuong.requestFocus();
				}
				else if(txtPhuong.getText().trim().equals("") || txtPhuong.getText()==null)
				{
					JOptionPane.showMessageDialog(null,"Hãy nhập phường");
					txtPhuong.requestFocus();
				}
					else if(bAdd)
				{
				
						try{
//						 Class.forName("org.postgresql.Driver");
//						   Connection c = DriverManager.getConnection(dburl, dbuser,dbpass);
//						   PreparedStatement ps = null;
//						     String UpdateSQLString = " INSERT INTO internet( ten_chu, banghieu, so_nha, ten_duong, so_dkkd, ngaycap_dkkd, ghichu, loaihinh_kd, so_may, phuong,so_dt,von)";
//						    UpdateSQLString += "  VALUES (?, ?, ?, ?, ?, to_date(?,'DD MM YYYY'), ?, ?, ?, ?,?,?);";
//						    ps = c.prepareStatement(UpdateSQLString);
//						   //tenchu
//						    ps.setString(1,txtChucoso.getText());
//						    ps.setString(2,txtBanghieu.getText());
//						    ps.setString(3,txtSonha.getText());
//						    ps.setString(4,txtDuong.getText());
//						    ps.setString(5,txtDkkd.getText());
//						    if(txtNgaycapDKKD.getText()==null || txtNgaycapDKKD.getText().trim().equals("") )
//							   {
//						    	 ps.setString(6,null);
//							    					   }
//						    else{
//						    ps.setString(6,txtNgaycapDKKD.getText());
//						    }
//						    ps.setString(7,txtGhichu.getText());
//						    ps.setString(8,txtLoaihinh.getText());
//						    ps.setString(9,txtSoMay.getText());
//						    ps.setInt(10,Integer.valueOf(txtPhuong.getText()));
//						    ps.setString(11,txtDt.getText());
//						    if(txtVon.getValue()== null)
//						    {
//						    ps.setNull(12,Types.DOUBLE);
//						    }
//						    else 
//						    {
//							    ps.setDouble(12,((Number) txtVon.getValue()).doubleValue());
//
//						    }
//						    ps.executeUpdate(); 
//						    ps.close();
//						    c.close();
//						    JOptionPane.showMessageDialog(null,"Lưu thông tin thành công");
//						    bAdd = false;
//							acSave.setEnabled(false);
//							acCancel.setEnabled(false);
//							acAdd.setEnabled(true);
//							acDelete.setEnabled(true);
//							dispose();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,e);

					} 
						
				}else
				{
					Update();
						}
				 
			}
			public void Update()
			{
				try{
//					 Class.forName("org.postgresql.Driver");
//					   Connection c = DriverManager.getConnection(dburl, dbuser,dbpass);
//					   PreparedStatement ps = null;
//					     String UpdateSQLString = " UPDATE internet SET ten_chu=?, banghieu=?, so_nha=?, ten_duong=?, so_dkkd=?, ngaycap_dkkd=to_date(?,'DD MM YYYY'), ghichu=?, loaihinh_kd=?, so_may=?,phuong=?,so_dt=?,von=? ";
//					    UpdateSQLString += " WHERE ma_internet=?;";
//					    ps = c.prepareStatement(UpdateSQLString);
//					   //tenchu
//					    ps.setString(1,txtChucoso.getText());
//					    ps.setString(2,txtBanghieu.getText());
//					    ps.setString(3,txtSonha.getText());
//					    ps.setString(4,txtDuong.getText());
//					    ps.setString(5,txtDkkd.getText());
//					    if(txtNgaycapDKKD.getText()==null || txtNgaycapDKKD.getText().trim().equals("") )
//						   {
//					    	 ps.setString(6,null);
//						    					   }
//					    else{
//					    ps.setString(6,txtNgaycapDKKD.getText());
//					    }
//					    ps.setString(7,txtGhichu.getText());
//					    ps.setString(8,txtLoaihinh.getText());
//					    ps.setString(9,txtSoMay.getText());
//					    ps.setInt(10,Integer.valueOf(txtPhuong.getText()));
//					    ps.setString(11,txtDt.getText());
//					    if(txtVon.getValue()== null)
//					    {
//					    ps.setNull(12,Types.DOUBLE);
//					    }
//					    else 
//					    {
//						    ps.setDouble(12,((Number) txtVon.getValue()).doubleValue());
//
//					    }
//					    
//					    ps.setInt(13,Integer.valueOf(txtMa.getText()));	 	 
//					    ps.executeUpdate(); 
//					    ps.close();
//					    c.close();
//					    JOptionPane.showMessageDialog(null,"Cập nhật thông tin thành công");
//					    acSave.setEnabled(false);
//						acCancel.setEnabled(false);
//						acAdd.setEnabled(true);
//						acDelete.setEnabled(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e);

				} 
			}
	//endregion
			/* (non-Javadoc)
			 * @see gov.vn.hcmgis.database.view.IObjectDetailView#SetHook(gov.vn.hcmgis.projects.gui.ILocationUpdateView)
			 */
			@Override
			public void SetHook(ILocationUpdateView hook) {
				// TODO Auto-generated method stub
				this._hook=hook;
				this.setLocationRelativeTo(hook.GetTable());
			}
			/* (non-Javadoc)
			 * @see gov.vn.hcmgis.database.view.IObjectDetailView#GetHook()
			 */
			@Override
			public ILocationUpdateView GetHook() {
				// TODO Auto-generated method stub
				return this._hook;
			}
			/* (non-Javadoc)
			 * @see gov.vn.hcmgis.database.view.IObjectDetailView#SetModal(java.lang.Boolean)
			 */
			@Override
			public void SetModal(Boolean arg) {
				// TODO Auto-generated method stub
				this.setModal(arg);
			}
			/* (non-Javadoc)
			 * @see gov.vn.hcmgis.database.view.IObjectDetailView#SetLocationRelativeTo(javax.swing.JTable)
			 */
			@Override
			public void SetLocationRelativeTo(JTable table) {
				// TODO Auto-generated method stub
				this.setLocationRelativeTo(table);
			}
			/* (non-Javadoc)
			 * @see gov.vn.hcmgis.database.view.IObjectDetailView#SetVisible(java.lang.Boolean)
			 */
			@Override
			public void SetVisible(Boolean arg) {
				// TODO Auto-generated method stub
				this.setVisible(arg);
			}
			/* (non-Javadoc)
			 * @see gov.vn.hcmgis.database.view.IObjectDetailView#SetKey(java.lang.String, java.lang.Object)
			 */
			@Override
			public void SetKey(String name, Object value) {
				// TODO Auto-generated method stub
				_keyName=name;
				_keyValue=value;
			}
			/* (non-Javadoc)
			 * @see gov.vn.hcmgis.database.view.IObjectDetailView#SetKeyValue(java.lang.Object)
			 */
			@Override
			public void SetKeyValue(Object value) {
				// TODO Auto-generated method stub
				this._keyValue=value;
			}
			/* (non-Javadoc)
			 * @see gov.vn.hcmgis.database.view.IObjectDetailView#GetKeyName()
			 */
			@Override
			public String GetKeyName() {
				// TODO Auto-generated method stub
				return this._keyName;
			}
			/* (non-Javadoc)
			 * @see gov.vn.hcmgis.database.view.IObjectDetailView#GetKeyValue()
			 */
			@Override
			public Object GetKeyValue() {
				// TODO Auto-generated method stub
				return this._keyValue;
			}
			/* (non-Javadoc)
			 * @see gov.vn.hcmgis.database.dataModel.IDataReceiver#Receive(java.lang.String, int)
			 */
			@Override
			public void Receive(String key, int result) {
				// TODO Auto-generated method stub
				
			}

			
}
