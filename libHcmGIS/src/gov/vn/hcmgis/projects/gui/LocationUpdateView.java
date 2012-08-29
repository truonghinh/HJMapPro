/**
 * 
 */
package gov.vn.hcmgis.projects.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.iver.cit.gvsig.fmap.MapControl;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.layers.FBitSet;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.GraphicLayer;
import com.iver.cit.gvsig.fmap.layers.ReadableVectorial;
import com.iver.cit.gvsig.fmap.tools.Behavior.PointBehavior;

import gov.vn.hcmgis.connection.IConnectionProperty;
import gov.vn.hcmgis.core.actions.onTable.ActExImExcel;
import gov.vn.hcmgis.database.dataModel.DataLoader;
import gov.vn.hcmgis.database.dataModel.IDataLoader;
import gov.vn.hcmgis.database.dataModel.IDataReceiver;
import gov.vn.hcmgis.database.dataModel.TableStructure;
import gov.vn.hcmgis.database.view.BaseTableEditable;
import gov.vn.hcmgis.database.view.BaseTableReadOnly;
import gov.vn.hcmgis.database.view.CellRenderer;
import gov.vn.hcmgis.database.view.DefaultTableReadOnly;
import gov.vn.hcmgis.database.view.IGTable;
import gov.vn.hcmgis.database.view.IObjectDetailView;
import gov.vn.hcmgis.database.view.RightTableCellRenderer;
import gov.vn.hcmgis.gui.DefaultModalDialogSingleton;
import gov.vn.hcmgis.map.toolbox.Zoomer;
import gov.vn.hcmgis.projects.Quan8Functions;
import gov.vn.hcmgis.projects.actions.ActOnBtnTimSoNha;
import gov.vn.hcmgis.projects.actions.ActOnBtnUpdatePoint;
import gov.vn.hcmgis.projects.actions.ActOnBtnZoomToDuong;
import gov.vn.hcmgis.projects.actions.ActOnBtnZoomToGiaoLo;
import gov.vn.hcmgis.projects.actions.ActOnBtnZoomToKtvhxh;
import gov.vn.hcmgis.projects.actions.ActOnCbxDuong;
import gov.vn.hcmgis.projects.actions.ActOnCbxPhuong;
import gov.vn.hcmgis.projects.actions.ActOnCbxPhuongForObject;
import gov.vn.hcmgis.projects.businessManagment.DbStructureQ8;
import gov.vn.hcmgis.projects.databaseStructure.ColumnsGiaoThongLine;
import gov.vn.hcmgis.projects.databaseStructure.ColumnsKtvhxh;
import gov.vn.hcmgis.projects.databaseStructure.ColumnsRanhPhuong;
import gov.vn.hcmgis.projects.databaseStructure.ColumnsThua;
import gov.vn.hcmgis.util.UpdatePointListener;

/**
 * @author HT
 *
 */
public abstract class LocationUpdateView extends DefaultModalDialogSingleton implements ILocationUpdateView,IDataReceiver {

	protected TableStructure _tblObject;
	protected TableStructure _tblPhuong;
	protected TableStructure _tblDuong;
	protected TableStructure _tblktvhxh;
	protected TableStructure _tblThua;
	protected IDataLoader _dataLoader;
	protected ActOnCbxPhuong _actOnCbxPhuong;
	protected ActOnCbxDuong _actOnCbxDuong;
	protected ActOnBtnTimSoNha _actOnBtnTimSoNha;
	protected ActOnBtnZoomToDuong _actOnBtnZoomToDuong;
	protected ActOnBtnZoomToGiaoLo _actOnBtnZoomToGiaoLo;
	protected ActOnBtnZoomToKtvhxh _actOnBtnZoomToKtvhxh;
	protected ActOnCbxPhuongForObject _actOnBtnPhuongForObj;
	protected ActOnBtnUpdatePoint _actOnBtnUpdatePoint;
	protected IObjectDetailView _detailView;
	
	public LocationUpdateView() 
	{
		this(null);
	}
	public LocationUpdateView(MapControl map)
	{
		super();
		this.SetWindowSize(832, 318);
		this.setLayout(new BorderLayout());
		this.add(getJSplitPane(), null);
		
		cbxLoaiDn.addItem("Doanh nghiệp");
		cbxLoaiDn.addItem("Hộ kinh doanh");
		
//		acImportExcel=new ActExImExcel();

		_dataLoader=new DataLoader(this);
		_actOnCbxPhuong=new ActOnCbxPhuong(this);
		_actOnCbxDuong=new ActOnCbxDuong(this);
		_actOnBtnTimSoNha=new ActOnBtnTimSoNha(this);
		_actOnBtnZoomToDuong=new ActOnBtnZoomToDuong(this);
		_actOnBtnZoomToGiaoLo=new ActOnBtnZoomToGiaoLo(this);
		_actOnBtnZoomToKtvhxh=new ActOnBtnZoomToKtvhxh(this);
		_actOnBtnPhuongForObj=new ActOnCbxPhuongForObject(this);
		_actOnBtnUpdatePoint =new ActOnBtnUpdatePoint(this,"",new ImageIcon(ImagePath + "\\locatePoint.png"));
		CboPhuong.setAction(_actOnCbxPhuong);
		CboDuong.setAction(_actOnCbxDuong);
		BtnTim.setAction(_actOnBtnTimSoNha);
		BtnDuong.setAction(_actOnBtnZoomToDuong);
		BtnGiaolo.setAction(_actOnBtnZoomToGiaoLo);
		BtnDiavat.setAction(_actOnBtnZoomToKtvhxh);
		CboPhuongInternet.setAction(_actOnBtnPhuongForObj);
		BtnUpdateSptail.setAction(_actOnBtnUpdatePoint);
		_mapControl=map;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JComboBox CboPhuong = null;
	protected JComboBox CboDuong = null;
	protected JButton BtnTim = null;
	protected JCheckBox ChkTimgandung = null;
	protected JCheckBox ChkZoomtoThua = null;
	protected JScrollPane SPnKetqua = null;
	protected JTable TblKetqua = null;
	protected static MapControl _mapControl = null;
	protected JComboBox CboDiavat = null;
	protected JComboBox CboGiaolo = null;
	protected JButton BtnPhuong = null;
	protected JButton BtnDiavat = null;
	protected JButton BtnGiaolo = null;
	protected String ImagePath = System.getProperty("user.dir") + "\\gvSIG\\extensiones\\gov.vn.hcmgis.quan8\\images";  //  @jve:decl-index=0:
	protected GraphicLayer lyr;  //  @jve:decl-index=0:
	protected JButton BtnDuong = null;
	protected String ImagePath1 = null;
	protected JSplitPane jSplitPane = null;
	protected JPanel jPanel = null;
	protected JPanel jPanel1 = null;
	protected JLabel jLabel = null;
	protected JLabel jLabel1 = null;
	protected JLabel jLabel2 = null;
	protected JLabel jLabel3 = null;
	protected JLabel jLabel4 = null;
	protected JScrollPane jScrollPane = null;
	protected DefaultTableReadOnly TblInternet = null;
	protected JLabel jLabel6 = null;
	protected JComboBox CboPhuongInternet = null;
	protected JCheckBox ChkZoomtoInternet = null;
	public double X;
	public double Y;
	protected JButton BtnUpdateSptail = null;
	protected JButton BtnSaveSpatial = null;
	protected Action  acSaveSpatial = new AcSaveSpatial ("Lưu vị trí", null, "Lưu vị trí",KeyEvent.VK_S);  //  @jve:decl-index=0:
	protected Action  acDeleteSpatial = new AcDeleteSpatial ("Xoá vị trí", null, "Lưu vị trí",KeyEvent.VK_S);  //  @jve:decl-index=0:
	protected Action acDelete = new AcDelete("Xóa dữ liệu", null,"Xóa", KeyEvent.VK_T); // @jve:decl-index=0:
	protected Action acAdd = new AcAdd("Thêm dữ liệu", null,"Thêm", KeyEvent.VK_T); // @jve:decl-index=0:
	protected ActExImExcel acImportExcel; // @jve:decl-index=0:
	protected JButton btnDeleteSpatial = null;
	protected int  rowupdated = 0;
	protected JSplitPane jSplitPane1 = null;
	protected JPanel jPanel2 = null;
	protected JScrollPane jScrollPane1 = null;
//	protected ConnecttoPostgis Connectpostgis = null;  //  @jve:decl-index=0:
	protected String dburl = "";
	protected String dbuser = "";  //  @jve:decl-index=0:
	protected String dbpass = "";
//	public Quan8TTCTInternet fTTCT = null;
	protected int currentrow;
	protected JPopupMenu jPopupMenu = null;  //  @jve:decl-index=0:visual-constraint="-62,138"
	//private JMenuItem mniAdd = null;
	protected JMenuItem mniDelete = null;
	protected JMenuItem mniVipham = null;
	protected JMenuItem mniAdd = null;
	protected JMenuItem mniUpdate = null;
	protected JMenuItem mniDeleteRow = null;
	protected JMenuItem mniImportExcel = null;
	protected JMenuItem mniDetail =null;
	protected MouseListener popupListener = new PopupListener();  //  @jve:decl-index=0:
	protected JTabbedPane TbpSoNha = null;
	protected JComboBox CboSonhacu = null;
	protected JComboBox CboSonhamoi = null;
	protected JLabel jLabel5 = null;
	protected JTextField txtFilter = null;  
//	public Quan8Login fLogin =null ;
//	public Quan8TTCTInternet_Vipham fTTVP = null;
	private IConnectionProperty _connPro;
	protected JLabel lblLoiHnhDn;
	protected JComboBox cbxLoaiDn;
	protected String _tableInterNetName="dn_internet";
	
	//region design gui
	
	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jScrollPane1.setBorder(null);
			jScrollPane1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jScrollPane1.setViewportView(getJPanel1());
		}
		return jScrollPane1;
	}
	
	private JSplitPane getJSplitPane1() {
		if (jSplitPane1 == null) {
			jSplitPane1 = new JSplitPane();
			jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			jSplitPane1.setDividerLocation(200);
			jSplitPane1.setContinuousLayout(false);
			jSplitPane1.setTopComponent(getJScrollPane1());
			jSplitPane1.setBottomComponent(getJPanel2());
		}
		return jSplitPane1;
	}
	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.BOTH;
			gridBagConstraints12.weighty = 1.0;
			gridBagConstraints12.weightx = 1.0;
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GridBagLayout());
			jPanel2.add(getSPnKetqua(), gridBagConstraints12);
		}
		return jPanel2;
	}
	
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
			gridBagConstraints24.fill = GridBagConstraints.BOTH;
			gridBagConstraints24.gridy = 1;
			gridBagConstraints24.weightx = 1.0;
			gridBagConstraints24.gridwidth = 4;
			gridBagConstraints24.insets = new Insets(3, 0, 5, 0);
			gridBagConstraints24.gridx = 4;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.fill = GridBagConstraints.BOTH;
			gridBagConstraints8.insets = new Insets(3, 3, 5, 5);
			gridBagConstraints8.gridy = 1;
			jLabel5 = new JLabel();
			jLabel5.setText("Tìm kiếm");
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.gridx = 5;
			gridBagConstraints22.fill = GridBagConstraints.BOTH;
			gridBagConstraints22.insets = new Insets(3, 3, 5, 5);
			gridBagConstraints22.gridy = 3;
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 4;
			gridBagConstraints20.fill = GridBagConstraints.BOTH;
			gridBagConstraints20.insets = new Insets(3, 3, 5, 5);
			gridBagConstraints20.gridy = 3;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.gridx = 0;
			gridBagConstraints16.fill = GridBagConstraints.BOTH;
			gridBagConstraints16.insets = new Insets(3, 3, 5, 5);
			gridBagConstraints16.gridy = 3;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 6;
			gridBagConstraints15.fill = GridBagConstraints.BOTH;
			gridBagConstraints15.insets = new Insets(3, 3, 5, 5);
			gridBagConstraints15.gridy = 3;
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.fill = GridBagConstraints.BOTH;
			gridBagConstraints19.gridy = 0;
			gridBagConstraints19.weightx = 1.0;
			gridBagConstraints19.gridwidth = 4;
			gridBagConstraints19.insets = new Insets(3, 0, 5, 5);
			gridBagConstraints19.gridx = 3;
			GridBagConstraints gbc_cbxLoaiDn = new GridBagConstraints();
			gbc_cbxLoaiDn.fill = GridBagConstraints.BOTH;
			gbc_cbxLoaiDn.gridy = 2;
			gbc_cbxLoaiDn.weightx = 1.0;
			gbc_cbxLoaiDn.gridwidth = 4;
			gbc_cbxLoaiDn.insets = new Insets(3, 0, 5, 5);
			gbc_cbxLoaiDn.gridx = 3;
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.gridx = 1;
			gridBagConstraints18.gridy = 0;
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.gridx = 0;
			gridBagConstraints17.fill = GridBagConstraints.BOTH;
			gridBagConstraints17.gridwidth = 1;
			gridBagConstraints17.insets = new Insets(3, 3, 5, 5);
			gridBagConstraints17.gridy = 0;
			jLabel6 = new JLabel();
			jLabel6.setText("Phường");
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.fill = GridBagConstraints.BOTH;
			gridBagConstraints14.weighty = 1.0;
			gridBagConstraints14.gridx = 0;
			gridBagConstraints14.gridy = 5;
			gridBagConstraints14.gridwidth = 8;
			gridBagConstraints14.weightx = 1.0;
			jPanel = new JPanel();
			GridBagLayout gbl_jPanel = new GridBagLayout();
			gbl_jPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			jPanel.setLayout(gbl_jPanel);
			GridBagConstraints gbc_lblLoiHnhDn = new GridBagConstraints();
			gbc_lblLoiHnhDn.insets = new Insets(0, 0, 5, 5);
			gbc_lblLoiHnhDn.gridx = 0;
			gbc_lblLoiHnhDn.gridy = 2;
			jPanel.add(getLblLoiHnhDn(), gbc_lblLoiHnhDn);
			GridBagConstraints gbc_cmbLoaiDn = new GridBagConstraints();
			gbc_cmbLoaiDn.insets = new Insets(0, 0, 5, 5);
			gbc_cmbLoaiDn.fill = GridBagConstraints.HORIZONTAL;
			gbc_cmbLoaiDn.gridx = 2;
			gbc_cmbLoaiDn.gridy = 2;
			jPanel.add(getCbxLoaiDn(), gbc_cbxLoaiDn);
			jPanel.add(getJScrollPane(), gridBagConstraints14);
			jPanel.add(jLabel6, gridBagConstraints17);
			jPanel.add(getCboPhuongInternet(), gridBagConstraints19);
			jPanel.add(getChkZoomtoInternet(), gridBagConstraints15);
			jPanel.add(getBtnUpdateSptail(), gridBagConstraints16);
			jPanel.add(getBtnSaveSpatial(), gridBagConstraints20);
			jPanel.add(getBtnDeleteSpatial(), gridBagConstraints22);
			jPanel.add(jLabel5, gridBagConstraints8);
			jPanel.add(getTxtFilter(), gridBagConstraints24);
		}
		return jPanel;
	}
	
	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
			gridBagConstraints23.fill = GridBagConstraints.BOTH;
			gridBagConstraints23.gridy = 5;
			gridBagConstraints23.weightx = 1.0;
			gridBagConstraints23.weighty = 1.0;
			gridBagConstraints23.gridwidth = 3;
			gridBagConstraints23.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints23.gridx = 1;
			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.gridx = 0;
			gridBagConstraints51.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints51.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints51.gridy = 5;
			jLabel4 = new JLabel();
			jLabel4.setText("Số nhà");
			jLabel4.setPreferredSize(new Dimension(39, 25));
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.gridx = 0;
			gridBagConstraints41.fill = GridBagConstraints.BOTH;
			gridBagConstraints41.gridy = 4;
			jLabel3 = new JLabel();
			jLabel3.setText("Giao lộ");
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 0;
			gridBagConstraints31.fill = GridBagConstraints.BOTH;
			gridBagConstraints31.gridy = 3;
			jLabel2 = new JLabel();
			jLabel2.setText("Đường");
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.fill = GridBagConstraints.BOTH;
			gridBagConstraints21.gridy = 2;
			jLabel1 = new JLabel();
			jLabel1.setText("Điểm KTVHXH");
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 0;
			gridBagConstraints13.fill = GridBagConstraints.BOTH;
			gridBagConstraints13.gridy = 1;
			jLabel = new JLabel();
			jLabel.setText("Phường");
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 3;
			gridBagConstraints11.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints11.anchor = GridBagConstraints.WEST;
			gridBagConstraints11.gridy = 4;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.fill = GridBagConstraints.BOTH;
			gridBagConstraints10.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints10.gridy = 6;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.BOTH;
			gridBagConstraints9.gridy = 4;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.gridwidth = 2;
			gridBagConstraints9.gridx = 1;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.BOTH;
			gridBagConstraints7.gridy = 3;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.gridwidth = 2;
			gridBagConstraints7.gridx = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 3;
			gridBagConstraints6.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.gridy = 2;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.gridwidth = 2;
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 3;
			gridBagConstraints4.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.gridy = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 2;
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 6;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.anchor = GridBagConstraints.NORTH;
			gridBagConstraints2.gridy = 6;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.gridwidth = 2;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 3;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints.gridy = 3;
			jPanel1 = new JPanel();
			jPanel1.setBorder( BorderFactory.createTitledBorder("Tìm kiếm vị trí" ) ) ;
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(getBtnDuong(), gridBagConstraints);
			jPanel1.add(getCboPhuong(), gridBagConstraints1);
			jPanel1.add(getChkTimgandung(), gridBagConstraints2);
			jPanel1.add(getChkZoomtoThua(), gridBagConstraints3);
			jPanel1.add(getBtnPhuong(), gridBagConstraints4);
			jPanel1.add(getCboDiavat(), gridBagConstraints5);
			jPanel1.add(getBtnDiavat(), gridBagConstraints6);
			jPanel1.add(getCboDuong(), gridBagConstraints7);
			jPanel1.add(getCboGiaolo(), gridBagConstraints9);
			jPanel1.add(getBtnTim(), gridBagConstraints10);
			jPanel1.add(getBtnGiaolo(), gridBagConstraints11);
			jPanel1.add(jLabel, gridBagConstraints13);
			jPanel1.add(jLabel1, gridBagConstraints21);
			jPanel1.add(jLabel2, gridBagConstraints31);
			jPanel1.add(jLabel3, gridBagConstraints41);
			jPanel1.add(jLabel4, gridBagConstraints51);
			jPanel1.add(getTbpSoNha(), gridBagConstraints23);
		}
		return jPanel1;
	}
	
	/**
	 * This method initializes CboDiavat	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCboDiavat() {
		if (CboDiavat == null) {
			CboDiavat = new JComboBox();
		}
		return CboDiavat;
	}

	/**
	 * This method initializes CboGiaolo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCboGiaolo() {
		if (CboGiaolo == null) {
			CboGiaolo = new JComboBox();
			
			//CboGiaolo.setFont(new Font(".VnTime", Font.PLAIN, 13));
			
		}
		return CboGiaolo;
	}

	/**
	 * This method initializes BtnPhuong	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnPhuong() {
		if (BtnPhuong == null) {
			BtnPhuong = new JButton();
			BtnPhuong.setIcon(new ImageIcon(ImagePath + "\\ZoomSeleccion.png"));
//			BtnPhuong.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					if (!CboPhuong.getSelectedItem().equals("Tất cả"))
//						ZoomToPhuong(Integer.parseInt(CboPhuong.getSelectedItem().toString()));
//					//else
//						//mapControl.getViewPort().set
//				}
//			});
		}
		return BtnPhuong;
	}

	/**
	 * This method initializes BtnDiavat	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnDiavat() {
		if (BtnDiavat == null) {
			BtnDiavat = new JButton();
			BtnDiavat.setIcon(new ImageIcon(ImagePath + "\\ZoomSeleccion.png"));
//			BtnDiavat.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					ClearSelection("phuong");
//					ClearSelection("gt_ln");
//					String Ten_Diavat = CboDiavat.getSelectedItem().toString();
//					if (!CboPhuong.getSelectedItem().equals("Tất cả"))
//					{	Integer ma_phuong = Integer.parseInt(CboPhuong.getSelectedItem().toString());
//						ZoomToDiavat(ma_phuong,Ten_Diavat);
//					}
//					else
//					{	
//						ZoomToDiavat(Ten_Diavat);
//					}
//					
//				}
//			});
		}
		return BtnDiavat;
	}

	/**
	 * This method initializes BtnDuong	
	 * 	
	 * @return javax.swing.JButton	
	 */

	/**
	 * This method initializes BtnGiaolo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnGiaolo() {
		if (BtnGiaolo == null) {
			BtnGiaolo = new JButton();
			BtnGiaolo.setIcon(new ImageIcon(ImagePath + "\\ZoomSeleccion.png"));
//			BtnGiaolo.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					ClearSelection("phuong");
//					ClearSelection("gt_ln");
//					String Ten_duong = CboDuong.getSelectedItem().toString();
//					if (!CboPhuong.getSelectedItem().equals("Tất cả"))
//					{
//						Integer ma_phuong = Integer.parseInt(CboPhuong.getSelectedItem().toString());
//						if ((CboGiaolo.getItemCount()>0) && (CboGiaolo.getSelectedItem()!= null))
//						{
//							String Ten_Giaolo = CboGiaolo.getSelectedItem().toString();
//							ZoomToGiaolo(ma_phuong,Ten_duong,Ten_Giaolo);
//						 } 
//						else 
//						ZoomToDuong(ma_phuong, Ten_duong);
//					 }
//					else {
//						if ((CboGiaolo.getItemCount()>0) && (CboGiaolo.getSelectedItem()!= null))
//							{String Ten_Giaolo = CboGiaolo.getSelectedItem().toString();
//							ZoomToGiaolo(Ten_duong,Ten_Giaolo);}
//						else 
//						ZoomToDuong(Ten_duong);
//						}
//				}
//			});
		}
		return BtnGiaolo;
	}
	/**
	 * This method initializes BtnDuong	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnDuong() {
		if (BtnDuong == null) {
			BtnDuong = new JButton();
			BtnDuong.setIcon(new ImageIcon(ImagePath + "\\ZoomSeleccion.png"));			
//			BtnDuong.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					ClearSelection("phuong");
//					ClearSelection("gt_ln");
//					String Ten_duong = CboDuong.getSelectedItem().toString();
//					if (!CboPhuong.getSelectedItem().equals("Tất cả"))
//					{
//						Integer ma_phuong = Integer.parseInt(CboPhuong.getSelectedItem().toString());
//						ZoomToDuong(ma_phuong,Ten_duong);
//					}
//					else 	ZoomToDuong(Ten_duong);
//				}
//			});
;
		}
		return BtnDuong;
	}
	/**
	 * This method initializes ImagePath1	
	 * 	
	 * @return java.lang.String	
	 */
	private String getImagePath1() {
		if (ImagePath1 == null) {
			ImagePath1 = new String();
		}
		return ImagePath1;
	}
	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setBounds(new Rectangle(107, 97, 202, 28));
			jSplitPane.setDividerLocation(350);
			jSplitPane.setRightComponent(getJSplitPane1());
			jSplitPane.setLeftComponent(getJPanel());
		}
		return jSplitPane;
	}
	
	private JLabel getLblLoiHnhDn() {
		if (lblLoiHnhDn == null) {
			lblLoiHnhDn = new JLabel("Loại hình DN");
		}
		return lblLoiHnhDn;
	}
	private JComboBox getCbxLoaiDn() {
		if (cbxLoaiDn == null) {
			cbxLoaiDn = new JComboBox();
			
			cbxLoaiDn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (CboPhuongInternet.getItemCount()>0)
						if (!CboPhuongInternet.getSelectedItem().equals("Tất cả"))
						{
							_tblObject.set_exactlyName(GetExactTableName());
							GetInternet(Integer.parseInt(CboPhuongInternet.getSelectedItem().toString()));
							System.out.println(String.format("line 688 LocationUpdateview, exactly Name= %s",_tblObject.get_exactlyName()));
						}
						else
						    GetInternet();
						    
					
				}
			});
		}
		return cbxLoaiDn;
	}
	
	/**
	 * This method initializes TbpSoNha	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTbpSoNha() {
		if (TbpSoNha == null) {
			TbpSoNha = new JTabbedPane();
			TbpSoNha.setPreferredSize(new Dimension(91, 48));
			TbpSoNha.addTab("Số nhà cũ", null, getCboSonhacu(), null);
			TbpSoNha.addTab("Số nhà mới", null, getCboSonhamoi(), null);
		}
		return TbpSoNha;
	}
	/**
	 * This method initializes CboSonhacu	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCboSonhacu() {
		if (CboSonhacu == null) {
			CboSonhacu = new JComboBox();
			CboSonhacu.setEditable(true);
			CboSonhacu.setSelectedItem(null);
		}
		return CboSonhacu;
	}
	/**
	 * This method initializes CboSonhamoi	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCboSonhamoi() {
		if (CboSonhamoi == null) {
			CboSonhamoi = new JComboBox();
			CboSonhamoi.setEditable(true);
			CboSonhamoi.setSelectedItem(null);
		}
		return CboSonhamoi;
	}
	/**
	 * This method initializes txtFilter	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtFilter() {
		if (txtFilter == null) {
			txtFilter = new JTextField();
			txtFilter.getDocument().addDocumentListener(
	                new DocumentListener() {
	                    public void changedUpdate(DocumentEvent e) {
	                        Filter();
	                    }
	                    public void insertUpdate(DocumentEvent e) {
	                        Filter();
	                    }
	                    public void removeUpdate(DocumentEvent e) {
	                        Filter();
	                    }
	                });
		}
		return txtFilter;
	}
	
	private JMenuItem getMniUpdate() {
		if (mniUpdate == null) {
			mniUpdate = new JMenuItem();
			mniUpdate.setText("Cập nhật vị trí");
			mniUpdate.setAction(acSaveSpatial);
		}
		return mniUpdate;
	}
	private JMenuItem getMniDeleteRow() {
		if (mniDeleteRow == null) {
			mniDeleteRow = new JMenuItem();
			mniDeleteRow.setText("Xóa bản tin");
			mniDeleteRow.setAction(acDelete);
		}
		return mniDeleteRow;
	}
	private JMenuItem getMniVipham() {
		if (mniVipham == null) {
			mniVipham = new JMenuItem();
			mniVipham.setText("Thông tin vi phạm");
			mniVipham.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					currentrow = TblInternet.getSelectedRow(); 
//					Call_TTVP();
					//Reload("internet");
//					if (CboPhuongInternet.getItemCount()>0)
//						if (!CboPhuongInternet.getSelectedItem().equals("Tất cả"))
//							RefreshAdd(Integer.parseInt(CboPhuongInternet.getSelectedItem().toString()));
//						else
//							RefreshAdd();
				}
			});
					}
		return mniVipham;
	}
	private JMenuItem getMniDetail() {
		if (mniDetail == null) {
			mniDetail = new JMenuItem();
			mniDetail.setText("Thông tin chi tiết");
			mniDetail.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					currentrow = TblInternet.getSelectedRow(); 
					Call_TTCT();
					//Reload("internet");
					if (CboPhuongInternet.getItemCount()>0)
						if (!CboPhuongInternet.getSelectedItem().equals("Tất cả"))
							RefreshAdd(Integer.parseInt(CboPhuongInternet.getSelectedItem().toString()));
						else
							RefreshAdd();
				}
			});
			
		}
		return mniDetail;
	}
	
	private JMenuItem getMniDelete() {
		if (mniDelete == null) {
			mniDelete = new JMenuItem();
			mniDelete.setText("Xóa vị trí");
//			mniDelete.setAction(acDeleteSpatial);
			
		}
		return mniDelete;
	}
	private JMenuItem getMniAdd() {
		if (mniAdd == null) {
			mniAdd = new JMenuItem();
			mniAdd.setText("Thêm dữ liệu");
//			mniAdd.setAction(acAdd);
			
		}
		return mniAdd;
	}
	private JMenuItem getMniImport() {
		if (mniImportExcel == null) {
			mniImportExcel = new JMenuItem();
			mniImportExcel.setText("Đọc Dl từ Excel");
			mniImportExcel.setAction(acImportExcel);
			
		}
		return mniImportExcel;
	}
	
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getTblInternet());
		}
		return jScrollPane;
	}
	
	private DefaultTableReadOnly getTblInternet() {
		if (TblInternet == null) {
			TblInternet = new DefaultTableReadOnly();
			
//			((IGTable)TblInternet)
//			TblInternet.addMouseListener(popupListener);
//			TblInternet.addMouseListener(new java.awt.event.MouseAdapter() {
//					public void mouseClicked(java.awt.event.MouseEvent e) {
//									if(e.getClickCount()==2)
//									{
//								currentrow = TblInternet.getSelectedRow(); 
//								Call_TTCT();
//								//Reload("internet");
//								 TblInternet.setAutoCreateRowSorter(false);
//								 TblInternet.setRowSorter(null);
//								// TblTTVP.setEnabled(false);
//								if (CboPhuongInternet.getItemCount()>0)
//									if (!CboPhuongInternet.getSelectedItem().equals("Tất cả"))
//										RefreshAdd(Integer.parseInt(CboPhuongInternet.getSelectedItem().toString()));
//									else
//										RefreshAdd();
//										}
//									
//									}
//											 																						
//			});
			TblInternet.getTableHeader().setReorderingAllowed(false);
			TblInternet.getTableHeader().addMouseListener(popupListener);
			TblInternet.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent e) 
                {
                	if(TblInternet.getSelectedRowCount()>0)
                	{
                		acDeleteSpatial.setEnabled(TblInternet.getValueAt(TblInternet.getSelectedRow(), 7)!=null);
                	}
                	else{
                		acDeleteSpatial.setEnabled(false);
                	}

                	//JOptionPane.showMessageDialog(null,TableName);
                	FLayer[] visibles = _mapControl.getMapContext().getLayers().getVisibles();
		 		    FLayer LayerTableName = null; 
		 		    // Ki` la'm nghen, visible chi co 2 lop nhung cai ham getVisibles tra ve 3 , nen phai sua lai la j = 1. Cai nay de ngam kiu lai sau! 
		 			 for (int j = 1; j < visibles.length ; j++) {
		 				//JOptionPane.showMessageDialog(null,visibles[j].getName().toString() + " " + j);
		 				 if (visibles[j].getName().contains("internet"))
		 					{	
		 						LayerTableName = visibles[j];			
		 					}
		 			 }
		 			if ((LayerTableName != null) && (TblInternet.getRowCount() > 0))
		 				 {
		            	FLyrVect lyrVect = (FLyrVect) LayerTableName ;	            	
		                ReadableVectorial rv = lyrVect.getSource();
		                double XX = 0 ;
		                double YY = 0 ;
		                Integer Columncount = TblInternet.getModel().getColumnCount();
		                	try {
								XX =Double.parseDouble((String) TblInternet.getValueAt(TblInternet.getSelectedRow(),Columncount-2)); 
								YY = Double.parseDouble((String) TblInternet.getValueAt(TblInternet.getSelectedRow(),Columncount-1));
							} catch (Exception e1) {
								XX = 0;
								YY = 0;
							}
							
		                Point2D pReal = new Point2D.Double(XX, YY);
		                try {
		                	if ((ChkZoomtoInternet.isSelected()) && (XX!=0) &&(YY!=0) && LayerTableName != null)
							{
		                		ClearSelection("internet");
		                		 try {
		     						rv.start(); // Dont't forget start and stop
		     						FBitSet selectiontemp = lyrVect.queryByPoint(pReal, 1);
			                		rv.getRecordset().setSelection(selectiontemp);			                		
		     		                rv.stop();
		     					} catch (Exception e1) {
		     						// TODO Auto-generated catch block
		     						e1.printStackTrace();
		     						//JOptionPane.showMessageDialog(null, e1.toString());
		     					}
		     					Rectangle2D rectangle2 = new Rectangle2D.Double(XX-40,YY-40,80,80);
		     					_mapControl.getViewPort().setExtent(rectangle2);		
							}
		                  	
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null,e1.toString());
						}	
		 			} 
		 			
		 			 
	               }
				});
		}
		return TblInternet;
	}
			
	
	/**
	 * This method initializes CboPhuongInternet	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCboPhuongInternet() {
		if (CboPhuongInternet == null) {
			CboPhuongInternet = new JComboBox();
			CboPhuongInternet.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (CboPhuongInternet.getItemCount()>0)
						if (!CboPhuongInternet.getSelectedItem().equals("Tất cả"))
							GetInternet(Integer.parseInt(CboPhuongInternet.getSelectedItem().toString()));
						else
						    GetInternet();
				}
			});
		}
		return CboPhuongInternet;
	}
	
	private void GetInternet()
	{
		this.ReadyLoadObject();
	}
	private void GetInternet(Object maphuong)
	{
		this.ReadyLoadObject(maphuong);
	}
	
	/**
	 * This method initializes ChkZoomtoInternet	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChkZoomtoInternet() {
		if (ChkZoomtoInternet == null) {
			ChkZoomtoInternet = new JCheckBox();
			ChkZoomtoInternet.setText("Zoom đến đối tượng");
//			ChkZoomtoInternet.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					if (TblInternet.getRowCount()>0)
// 					{
//						FLayer[] visibles = mapControl.getMapContext().getLayers().getVisibles();
//			 		    FLayer LayerTableName = null;
//			 		    // Ki` la'm nghen, visible chi co 2 lop nhung cai ham getVisibles tra ve 3 , nen phai sua lai la j = 1. Cai nay de ngam kiu lai sau! 
//			 			 for (int j = 1; j < visibles.length ; j++) {
//			 				//JOptionPane.showMessageDialog(null,visibles[j].getName().toString() + " " + j);
//			 				 if (visibles[j].getName().contains("internet"))
//			 					{	
//			 						LayerTableName = visibles[j];			
//			 					}
//			 				 
//			 			 }
//			 			
//			 			if ((LayerTableName != null) && (TblInternet.getRowCount() > 0))
//			 				 {
//			           // while (vectorialLayerIt.hasNext());
//			            //{
//			                //FLyrVect lyrVect = (FLyrVect) vectorialLayerIt.nextLayer();	        
//			            	
//			            	FLyrVect lyrVect = (FLyrVect) LayerTableName ;	            	
//			                ReadableVectorial rv = lyrVect.getSource();
//			                double XX = 0 ;
//			                double YY = 0 ;
//			                Integer Columncount = TblInternet.getModel().getColumnCount();
//			               // JOptionPane.showMessageDialog(null,Columncount);
//			                try {
//								XX =Double.parseDouble((String) TblInternet.getValueAt(TblInternet.getSelectedRow(),Columncount-2)); 
//								YY = Double.parseDouble((String) TblInternet.getValueAt(TblInternet.getSelectedRow(),Columncount-1));
//							} catch (Exception e2) {
//								// TODO Auto-generated catch block
//								XX = 0.0; 
//								YY = 0.0;
//							}
//							
//							 Point2D pReal = new Point2D.Double(XX, YY);
//				                try {
//				                	if ((ChkZoomtoInternet.isSelected()) && (XX!=0) &&(YY!=0) )
//									{
//				                		ClearSelection("internet");
//				                		 try {
//				     						rv.start(); // Dont't forget start and stop
//				     						FBitSet selectiontemp = lyrVect.queryByPoint(pReal, 1);
//					                		rv.getRecordset().setSelection(selectiontemp);			                		
//				     		                rv.stop();
//				     					} catch (Exception e1) {
//				     						// TODO Auto-generated catch block
//				     						e1.printStackTrace();
//				     						//JOptionPane.showMessageDialog(null, e1.toString());
//				     					}
//				     					Rectangle2D rectangle2 = new Rectangle2D.Double(XX-40,YY-40,80,80);
//				     					mapControl.getViewPort().setExtent(rectangle2);		
//									}
//			                  	
//							} catch (Exception e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}	
//			 			}else{ 
//		 					
//		 				 JOptionPane.showMessageDialog(null, "Hãy thêm layer vào bản đồ ");
//			 			ChkZoomtoInternet.setSelected(false);}
//	                	//if (Chk.isSelected())
//	                		//Selection("tru_nuoc");
//	                	//}
//		               }
//				}
//			});
		}
		return ChkZoomtoInternet;
	}
	
	/**
	 * This method initializes BtnUpdateSptail	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnUpdateSptail() {
		if (BtnUpdateSptail == null) {
			BtnUpdateSptail = new JButton();
			BtnUpdateSptail.setToolTipText("Cập nhật vị trí");
			BtnUpdateSptail.setIcon(new ImageIcon(ImagePath + "\\locatePoint.png"));	
//			BtnUpdateSptail.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					acSaveSpatial.setEnabled(true);	 				
//					Quan8Functions.XXInternet =  0.0;
//					 Quan8Functions.YYInternet = 0.0;
//					TblInternet.setEnabled(false);
////					GovapUpdateSpatialInternetListener li = null;
//					UpdatePointListener li = UpdatePointListener.CallMe(this);
//				    if (li == null) // We create it for the first time.
//				    {
//				        li = new GovapUpdateSpatialInternetListener(mapControl);        
//				        _mapControl.addMapTool("CapnhatKhonggian", new PointBehavior(li));
//				    }
//				   _mapControl.setTool("CapnhatKhonggian");
//				
//									}
//	 			});
		}
		return BtnUpdateSptail;
	}
	
	/**
	 * This method initializes BtnSaveSpatial	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnSaveSpatial() {
		if (BtnSaveSpatial == null) {
			BtnSaveSpatial = new JButton();
			BtnSaveSpatial.setAction(acSaveSpatial);
			BtnSaveSpatial.setVisible(false);
			//BtnSaveSpatial.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return BtnSaveSpatial;
	}
	
	/**
	 * This method initializes btnDeleteSpatial	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnDeleteSpatial() {
		if (btnDeleteSpatial == null) {
			btnDeleteSpatial = new JButton();
			btnDeleteSpatial.setAction(acDeleteSpatial);
			
		}
		return btnDeleteSpatial;
	}
	
	/**
	 * This method initializes CboPhuong	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	
	private JComboBox getCboPhuong() {
		if (CboPhuong == null) {
			CboPhuong = new JComboBox();
		
//			CboPhuong.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					DefaultTableModel dm = (DefaultTableModel)TblKetqua.getModel();
//				    dm.getDataVector().removeAllElements();	
//					if (CboPhuong.getItemCount()>0)
//						if (!CboPhuong.getSelectedItem().equals("Tất cả"))
//						{
//							GetDuong(Integer.parseInt(CboPhuong.getSelectedItem().toString()));					
//							//GetRacYte(Integer.parseInt(CboPhuong.getSelectedItem().toString()));
//					    }
//						else
//							{
//							GetDuong();					
//						    //GetRacYte();
//						    }
//					    
//				}
//			});			
		}
		return CboPhuong;
	}
	
	/**
	 * This method initializes ChkTimgandung	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChkTimgandung() {
		if (ChkTimgandung == null) {
			ChkTimgandung = new JCheckBox();
			ChkTimgandung.setText("Tìm gần đúng");			
		}
		return ChkTimgandung;
	}
	
	/**
	 * This method initializes CboDuong	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCboDuong() {
		if (CboDuong == null) {
			CboDuong = new JComboBox();
			CboDuong.setEditable(false);
			
			//CboDuong.setFont(new Font(".VnTime", Font.PLAIN, 13));
//			CboDuong.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					if (CboDuong.getItemCount()>0)
//					{
//						String Ten_Duong = CboDuong.getSelectedItem().toString();
//						if (!CboPhuong.getSelectedItem().equals("Tất cả"))
//						{
//						Integer ma_phuong = Integer.parseInt(CboPhuong.getSelectedItem().toString());
//						GetSonha(ma_phuong,Ten_Duong);
//						}
//						else GetSonha(Ten_Duong);
//					}
//				}
//			});			
		}
		return CboDuong;
	}
	
	/**
	 * This method initializes ChkZoomtoThua	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChkZoomtoThua() {
		if (ChkZoomtoThua == null) {
			ChkZoomtoThua = new JCheckBox();
			ChkZoomtoThua.setText("Zoom đến vị trí tìm được");
			ChkZoomtoThua.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (TblKetqua.getRowCount()>0)
 					{
						FLayer[] visibles = _mapControl.getMapContext().getLayers().getVisibles();
			 		    FLayer LayerTableName = null;
			 		    // Ki` la'm nghen, visible chi co 2 lop nhung cai ham getVisibles tra ve 3 , nen phai sua lai la j = 1. Cai nay de ngam kiu lai sau! 
			 			 for (int j = 1; j < visibles.length ; j++) {
			 				//JOptionPane.showMessageDialog(null,visibles[j].getName().toString() + " " + j);
			 				 if (visibles[j].getName().contains("thua"))
			 					{	
			 						LayerTableName = visibles[j];			
			 					}
			 			 }
			 			
			 			if ((LayerTableName != null) && (TblKetqua.getRowCount() > 0))
			 				 {
			           // while (vectorialLayerIt.hasNext());
			            //{
			                //FLyrVect lyrVect = (FLyrVect) vectorialLayerIt.nextLayer();	        
			            	
			            	FLyrVect lyrVect = (FLyrVect) LayerTableName ;	            	
			                ReadableVectorial rv = lyrVect.getSource();
			                double XX = 0 ;
			                double YY = 0 ;
			                Integer Columncount = TblKetqua.getModel().getColumnCount();
			               // JOptionPane.showMessageDialog(null,Columncount);
			                try {
								XX =Double.parseDouble((String) TblKetqua.getValueAt(TblKetqua.getSelectedRow(),Columncount-2)); 
								YY = Double.parseDouble((String) TblKetqua.getValueAt(TblKetqua.getSelectedRow(),Columncount-1));
							} catch (Exception e2) {
								// TODO Auto-generated catch block
								XX = 0.0; 
								YY = 0.0;
							}
							
			                Point2D pReal = new Point2D.Double(XX, YY);
			                Rectangle2D rectangle = null;
			                try {
			                	if (ChkZoomtoThua.isSelected())
								{
			                		
			                		//mapControl.getViewPort().setExtent(geom.getBounds2D())
			                		 try {
			     						rv.start(); // Dont't forget start and stop
			     						FBitSet selectiontemp = lyrVect.queryByPoint(pReal, 1);			     						
				                		rv.getRecordset().setSelection(selectiontemp);			                		
			     		                FBitSet selection = rv.getRecordset().getSelection();
			     		                if (selection != null)
			     		                {
			     		                for (int i = selection.nextSetBit(0); i >= 0; i = selection.nextSetBit(i+1)) {
			     		                    // operate on index i here
			     							IGeometry geom = rv.getShape(i);
			     							if (rectangle == null) { // first geom
			     								rectangle = (Rectangle2D) geom.getBounds2D().clone();
			     							} else { // next selected geoms.
			     								rectangle.add(geom.getBounds2D());
			     							}	
			     		                } // for
			     		                }
			     		                rv.stop();
			     					} catch (Exception e1) {
			     						// TODO Auto-generated catch block
			     						e1.printStackTrace();
			     						JOptionPane.showMessageDialog(null, e1.toString());
			     					}
			     					 if (rectangle != null)
			     		            {
			     						Double x = rectangle.getMinX()-10;
			     		            	Double y = rectangle.getMinY()-10;
			     		            	Double w = rectangle.getWidth()+ 20;
			     		            	Double h = rectangle.getHeight() + 20;
			     		            	rectangle.setFrame(x, y, w, h);    
			     		            	_mapControl.getViewPort().setExtent(rectangle);
			     		            	//mapControl.getMapContext().invalidate();
			     		            }  
								}
			                  	
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
			 			}   
	                	//if (Chk.isSelected())
	                		//Selection("tru_nuoc");
	                	//}
		               }
				}
			});
		}
		return ChkZoomtoThua;
	}
	
	/**
	 * This method initializes BtnTim	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnTim() {
		if (BtnTim == null) {
			BtnTim = new JButton();
			BtnTim.setText("Tìm");
//			BtnTim.addActionListener(new java.awt.event.ActionListener() {   
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					try {
//						setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//						ClearSelection("phuong");
//						ClearSelection("gt_ln");
//						String Ten_Duong = "";
//						String So_Nha_Moi = "";
//						String So_Nha_Cu = "";
//						Ten_Duong = CboDuong.getSelectedItem().toString();
//						
//						if(TbpSoNha.getSelectedIndex()==1){
//							try {
//								So_Nha_Moi = CboSonhamoi.getSelectedItem().toString();
//							} catch (Exception e1) {
//								// TODO Auto-generated catch block
//								So_Nha_Moi = "";
//							}
//							if (!CboPhuong.getSelectedItem().equals("Tất cả"))
//								{	
//								Integer ma_phuong = Integer.parseInt(CboPhuong.getSelectedItem().toString());
//								if (ChkTimgandung.isSelected())
//									TimGandung(ma_phuong, Ten_Duong,So_Nha_Moi);
//								else
//									TimKiem(ma_phuong, Ten_Duong,So_Nha_Moi);
//								}
//							else 
//							{
//								if (ChkTimgandung.isSelected())
//									TimGandung(Ten_Duong,So_Nha_Moi);
//								else
//									TimKiem(Ten_Duong,So_Nha_Moi);
//							}
//						}
//						else if(TbpSoNha.getSelectedIndex()==0){
//							try {
//								So_Nha_Cu = CboSonhacu.getSelectedItem().toString();
//							} catch (Exception e1) {
//								// TODO Auto-generated catch block
//								So_Nha_Cu = "";
//							}
//							if (!CboPhuong.getSelectedItem().equals("Tất cả"))
//								{	
//								Integer ma_phuong = Integer.parseInt(CboPhuong.getSelectedItem().toString());
//								if (ChkTimgandung.isSelected())
//									TimGandung_Cu(ma_phuong, Ten_Duong,So_Nha_Cu);
//								else
//									TimKiem_Cu(ma_phuong, Ten_Duong,So_Nha_Cu);
//								}
//							else 
//							{
//								if (ChkTimgandung.isSelected())
//									TimGandung_Cu(Ten_Duong,So_Nha_Cu);
//								else
//									TimKiem_Cu(Ten_Duong,So_Nha_Cu);
//							}
//						}
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						JOptionPane.showMessageDialog(null, e1.toString());
//					}
//					finally
//					{
//						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//												
//					}
//					
//				}
//			
//			});
		}
		return BtnTim;
	}
	
	/**
	 * This method initializes SPnKetqua	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSPnKetqua() {
		if (SPnKetqua == null) {
			SPnKetqua = new JScrollPane();
			SPnKetqua.setViewportView(getTblKetqua());
		}
		return SPnKetqua;
	}
	
	private JTable getTblKetqua() {
		if (TblKetqua == null) {
			TblKetqua = new JTable(){
				public boolean isCellEditable(int row, int column)
				{
					return false;
				}
			};
			TblKetqua.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TblKetqua.setSelectionForeground(Color.blue);
			TblKetqua.setSelectionBackground(Color.yellow);
			TblKetqua.setColumnSelectionAllowed(true);
			TblKetqua.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
			TblKetqua.getTableHeader().setReorderingAllowed(false);
			//TblKetqua.set
			//TblKetqua.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TblKetqua.getSelectionModel().addListSelectionListener(new ListSelectionListener(){ 
                public void valueChanged(ListSelectionEvent e) 
                {
//        			JOptionPane.showMessageDialog(null,_mapControl.toString());
                	FLayer[] visibles = _mapControl.getMapContext().getLayers().getVisibles();
		 		    FLayer LayerTableName = null; 
		 		    // Ki` la'm nghen, visible chi co 2 lop nhung cai ham getVisibles tra ve 3 , nen phai sua lai la j = 1. Cai nay de ngam kiu lai sau! 
		 			 for (int j = 1; j < visibles.length ; j++) {
		 				//JOptionPane.showMessageDialog(null,visibles[j].getName().toString() + " " + j);
		 				 if (visibles[j].getName().contains("thua"))
		 					{	
		 						LayerTableName = visibles[j];			
		 					}
		 			 }
		 			if ((LayerTableName != null) && (TblKetqua.getRowCount() > 0))
		 				 {
		           // while (vectorialLayerIt.hasNext())
		            //{
		                //FLyrVect lyrVect = (FLyrVect) vectorialLayerIt.nextLayer();	        
		            	FLyrVect lyrVect = (FLyrVect) LayerTableName ;	            	
		                ReadableVectorial rv = lyrVect.getSource();
		                double XX = 0 ;
		                double YY = 0 ;
		                Integer Columncount = TblKetqua.getModel().getColumnCount();
		               // JOptionPane.showMessageDialog(null,Columncount);
		                try {
							XX =Double.parseDouble((String) TblKetqua.getValueAt(TblKetqua.getSelectedRow(),Columncount-2)); 
							YY = Double.parseDouble((String) TblKetqua.getValueAt(TblKetqua.getSelectedRow(),Columncount-1));
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							XX = 0.0; 
							YY = 0.0;
						}
		                Point2D pReal = new Point2D.Double(XX, YY);
		                Rectangle2D rectangle = null;
		                try {
		                	if (ChkZoomtoThua.isSelected())
							{
		                		
		                		//mapControl.getViewPort().setExtent(geom.getBounds2D())
		                		 try {
		     						rv.start(); // Dont't forget start and stop
		     						FBitSet selectiontemp = lyrVect.queryByPoint(pReal, 1);
			                		rv.getRecordset().setSelection(selectiontemp);			                		
		     		                FBitSet selection = rv.getRecordset().getSelection();
		     		                if (selection != null)
		     		                {
		     		                for (int i = selection.nextSetBit(0); i >= 0; i = selection.nextSetBit(i+1)) {
		     		                    // operate on index i here
		     							IGeometry geom = rv.getShape(i);
		     							if (rectangle == null) { // first geom
		     								rectangle = (Rectangle2D) geom.getBounds2D().clone();
		     							} else { // next selected geoms.
		     								rectangle.add(geom.getBounds2D());
		     							}	
		     		                } // for
		     		                }
		     		                rv.stop();
		     					} catch (Exception e1) {
		     						// TODO Auto-generated catch block
		     						e1.printStackTrace();
		     						JOptionPane.showMessageDialog(null, e1.toString());
		     					}
		     					 if (rectangle != null)
		     		            {
		     						Double x = rectangle.getMinX()-10;
		     		            	Double y = rectangle.getMinY()-10;
		     		            	Double w = rectangle.getWidth()+ 20;
		     		            	Double h = rectangle.getHeight() + 20;
		     		            	rectangle.setFrame(x, y, w, h);    
		     		            	_mapControl.getViewPort().setExtent(rectangle);
		     		            	//mapControl.getMapContext().invalidate();
		     		            }  
							}
		                  	
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
		 			}   
                	//if (Chk.isSelected())
                		//Selection("tru_nuoc");
                	//}
	               }
				});
		}
		return TblKetqua;
	}
	
	//endregion
	
	//region 

	@Override
	public void SetTableStructureObject(TableStructure tableStructure) {
		// TODO Auto-generated method stub
		_tblObject=tableStructure;
		TblInternet.SetTableStructure(_tblObject);
	}

	@Override
	public TableStructure GetTableStructureObject() {
		// TODO Auto-generated method stub
		return _tblObject;
	}
	
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#SetTableStructurePhuong(gov.vn.hcmgis.database.dataModel.TableStructure)
	 */
	@Override
	public void SetTableStructurePhuong(TableStructure tableStructure) {
		// TODO Auto-generated method stub
		this._tblPhuong=tableStructure;
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#SetTableStructureDuong(gov.vn.hcmgis.database.dataModel.TableStructure)
	 */
	@Override
	public void SetTableStructureDuong(TableStructure tableStructure) {
		// TODO Auto-generated method stub
		this._tblDuong=tableStructure;
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#GetTableStructurePhuong()
	 */
	@Override
	public TableStructure GetTableStructurePhuong() {
		// TODO Auto-generated method stub
		return this._tblPhuong;
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#GetTableStructureDuong()
	 */
	@Override
	public TableStructure GetTableStructureDuong() {
		// TODO Auto-generated method stub
		return this._tblDuong;
	}
	

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#SetTableStructureKtvhxh(gov.vn.hcmgis.database.dataModel.TableStructure)
	 */
	@Override
	public void SetTableStructureKtvhxh(TableStructure tableStructure) {
		// TODO Auto-generated method stub
		this._tblktvhxh=tableStructure;
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#GetTableStructureKtvhxh()
	 */
	@Override
	public TableStructure GetTableStructureKtvhxh() {
		// TODO Auto-generated method stub
		return this._tblktvhxh;
	}
	
	

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#SetTableStructureThua(gov.vn.hcmgis.database.dataModel.TableStructure)
	 */
	@Override
	public void SetTableStructureThua(TableStructure tableStructure) {
		// TODO Auto-generated method stub
		_tblThua=tableStructure;
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#GetTableStructureThua()
	 */
	@Override
	public TableStructure GetTableStructureThua() {
		// TODO Auto-generated method stub
		return _tblThua;
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.database.dataModel.IDataReceiver#Receive(java.lang.String, java.sql.ResultSet)
	 */
	@Override
	public void Receive(String key, ResultSet result) {
		// TODO Auto-generated method stub

		if(key.equals(_tblPhuong.get_name()))
		{
			try {
				CboPhuong.removeAllItems();
				CboPhuongInternet.removeAllItems();
				while( result.next() )
				 {	    	 
					CboPhuong.addItem(result.getInt(1));
					CboPhuongInternet.addItem(result.getInt(1));
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 CboPhuong.addItem("Tất cả");
		}
		else if(key.equals(_tblDuong.get_name()))
		{
			CboDuong.removeAllItems();
			
			try {
				while( result.next() )
				 {	    	 
					CboDuong.addItem(result.getString(1));
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(key.equals(_tblktvhxh.get_name()))
		{
			CboDiavat.removeAllItems();
			try {
				while( result.next() )
				 {	    	 
					CboDiavat.addItem(result.getString(1));
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(key.equals(_tblThua.get_name()))
		{
			CboSonhacu.removeAllItems();
			CboSonhamoi.removeAllItems();
			try {
				while( result.next() )
				 {	    	 
					CboSonhamoi.addItem(result.getString(1));
 					CboSonhacu.addItem(result.getString(2));
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(key.equals("giaolo"))
		{
			CboGiaolo.removeAllItems();
			try {
				while( result.next() )
				 {	    	 
					CboGiaolo.addItem(result.getString(1));
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(key.equals("timsonha"))
		{
			DefaultTableModel dm = (DefaultTableModel)TblKetqua.getModel();
			dm.getDataVector().removeAllElements();	
			Vector rowHeader = new Vector ();
			rowHeader.add ("STT");
			rowHeader.add ("Số nhà mới");			 
			rowHeader.add ("Đường");
			rowHeader.add ("X");
			rowHeader.add ("Y");
			DefaultTableModel model = new DefaultTableModel(rowHeader,0);
			TblKetqua.setModel(model);
			int id=0;
			 try {
				while( result.next() )
				 {	    	 
					 Vector rowData;
					 //lặp hiển thị từng record lên JTable				
					 rowData = new Vector() ;
					 rowData.add (id+1);
					 rowData.add (result.getString(1));
					 rowData.add (result.getString(2));
					 rowData.add (result.getString(3));
					 rowData.add (result.getString(4));
					 model.addRow(rowData) ;	
					 //JOptionPane.showMessageDialog(null,rs.getString(1));
					 id++;
				 }
				RightTableCellRenderer aRightTableCellRenderer = new RightTableCellRenderer();
 				TblKetqua.getColumnModel().getColumn(0).setCellRenderer(aRightTableCellRenderer);
 				TblKetqua.getColumnModel().getColumn(4).setMaxWidth(0);
 				TblKetqua.getColumnModel().getColumn(4).setMinWidth(0);
 				TblKetqua.getColumnModel().getColumn(4).setPreferredWidth(0);
 				TblKetqua.getColumnModel().getColumn(3).setMaxWidth(0);
 				TblKetqua.getColumnModel().getColumn(3).setMinWidth(0);
 				TblKetqua.getColumnModel().getColumn(3).setPreferredWidth(0);
 				if (TblKetqua.getRowCount()>0)
 					{
 						ListSelectionModel selectionModel =	TblKetqua.getSelectionModel();
 						selectionModel.setSelectionInterval(0,0);
						//TblKetqua.requestFocus();
 					}
 			 	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
		else if(key.equals("zoomToExtent"))
		{
			double x =0;
	        double y =0;
	        try {
				while( result.next() )
				 {	    	 
					  x =Double.parseDouble((String) result.getString(1)); 
				      y = Double.parseDouble((String) result.getString(2));
				      //JOptionPane.showMessageDialog(null,X);
				      //JOptionPane.showMessageDialog(null,Y);
				 }
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Zoomer z=Zoomer.CallMe(_mapControl);
	        z.ZoomToExtentWithGivenPoint(x, y);
		}
		else if(key.equals("zoomToPoint"))
		{
			double x =0;
	        double y =0;
	        try {
				while( result.next() )
				 {	    	 
					  x =Double.parseDouble((String) result.getString(1)); 
				      y = Double.parseDouble((String) result.getString(2));
				      //JOptionPane.showMessageDialog(null,X);
				      //JOptionPane.showMessageDialog(null,Y);
				 }
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Zoomer z=Zoomer.CallMe(_mapControl);
	        z.ZoomToPointWithGraphic(x, y);
		}
		else if(key.equals("getObject"))
		{
			DefaultTableModel dm = (DefaultTableModel)TblInternet.getModel();
			dm.getDataVector().removeAllElements();	
			Vector rowHeader = new Vector ();
			rowHeader.add ("STT");
			rowHeader.add ("Mã");
			rowHeader.add ("Chủ cơ sở");			 
			rowHeader.add ("Bảng hiệu");
			rowHeader.add ("Loại hình");
			rowHeader.add ("Số nhà");
			rowHeader.add ("Đường");
			rowHeader.add ("X");
			rowHeader.add ("Y");
			DefaultTableModel model = new DefaultTableModel(rowHeader,0);
			TblInternet.setModel(model);
			int id=0;
			 try {
				while( result.next() )
				 {	    	 
					 Vector rowData;
					 //lặp hiển thị từng record lên JTable				
					 rowData = new Vector() ;
					 rowData.add (id+1);
					 rowData.add (result.getString(1));
					 rowData.add (result.getString(2));
					 rowData.add (result.getString(3));
					 rowData.add (result.getString(4));
					 rowData.add (result.getString(5));
					 rowData.add (result.getString(6));
					 rowData.add (result.getString(7));
					 rowData.add (result.getString(8));
					 model.addRow(rowData) ;	
					 //JOptionPane.showMessageDialog(null,rs.getString(1));
					 id++;
				 }
				RightTableCellRenderer aRightTableCellRenderer = new RightTableCellRenderer();
 				TblInternet.getColumnModel().getColumn(0).setCellRenderer(aRightTableCellRenderer);
 				TblInternet.getColumnModel().getColumn(0).setMaxWidth(35);
 				TblInternet.getColumnModel().getColumn(1).setMaxWidth(0);
 				TblInternet.getColumnModel().getColumn(1).setMinWidth(0);
 				TblInternet.getColumnModel().getColumn(1).setPreferredWidth(0);
 				TblInternet.getColumnModel().getColumn(4).setMaxWidth(0);
 				TblInternet.getColumnModel().getColumn(4).setMinWidth(0);
 				TblInternet.getColumnModel().getColumn(4).setPreferredWidth(0);
 				TblInternet.getColumnModel().getColumn(7).setMaxWidth(0);
 				TblInternet.getColumnModel().getColumn(7).setMinWidth(0);
 				TblInternet.getColumnModel().getColumn(7).setPreferredWidth(0);
 				TblInternet.getColumnModel().getColumn(8).setMaxWidth(0);
 				TblInternet.getColumnModel().getColumn(8).setMinWidth(0);
 				TblInternet.getColumnModel().getColumn(8).setPreferredWidth(0);
 				TblInternet.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
 				TblInternet.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
 				TblInternet.getColumnModel().getColumn(0).setCellRenderer(new CellRenderer());

 				
 				if (TblInternet.getRowCount()>0)
 					{
 						ListSelectionModel selectionModel =	TblInternet.getSelectionModel();
 						selectionModel.setSelectionInterval(0,0);
 					}
 			 	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.database.dataModel.IDataReceiver#Receive(java.lang.String, int)
	 */
	@Override
	public void Receive(String key, int result) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyGetData()
	 */
	@Override
	public void ReadyLoadAllData() {
		// TODO Auto-generated method stub
		this.ReadyLoadPhuong();
		this.ReadyLoadDuongTrongPhuong(CboPhuong.getSelectedItem());
		this.ReadyLoadKtvhxhTrongPhuong(CboPhuong.getSelectedItem());
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyLoadPhuong()
	 */
	@Override
	public void ReadyLoadPhuong() {
		// TODO Auto-generated method stub
		if(_tblPhuong==null)
		{
			return;
		}
		String SQLString = String.format("select p.%s from %s p order by p.%s",ColumnsRanhPhuong.MaPhuong,DbStructureQ8.RanhPhuong,ColumnsRanhPhuong.MaPhuong);
		_dataLoader.LoadData(_tblPhuong.get_name(), SQLString);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyLoadDuong()
	 */
	@Override
	public void ReadyLoadDuong() {
		// TODO Auto-generated method stub
		if(_tblDuong==null)
		{
			return;
		}
		
		String SQLString = String.format("select distinct p.%s from %s p where p.%s is not null order by p.%s",ColumnsGiaoThongLine.TenDuong,DbStructureQ8.GiaoThongDuong,ColumnsGiaoThongLine.TenDuong,ColumnsGiaoThongLine.TenDuong);
		_dataLoader.LoadData(_tblDuong.get_name(), SQLString);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyLoadKtvhxh()
	 */
	@Override
	public void ReadyLoadKtvhxh() {
		// TODO Auto-generated method stub
		if(_tblktvhxh==null)
		{
			return;
		}
		
		String SQLString = String.format("select distinct p.%s from %s p where p.%s is not null order by p.%s",ColumnsKtvhxh.TenKtvhxh,DbStructureQ8.Ktvhxh,ColumnsKtvhxh.TenKtvhxh,ColumnsKtvhxh.TenKtvhxh);
		_dataLoader.LoadData(_tblktvhxh.get_name(), SQLString);
	}

	

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyLoadDuongTrongPhuong()
	 * 
	 */
	@Override
	public void ReadyLoadDuongTrongPhuong(Object arg) {
		// TODO Auto-generated method stub
		if(_tblDuong==null | arg==null)
		{
			return;
		}
		if(!arg.equals("Tất cả"))
		{
	//		String SQLString = String.format("select distinct p.%s from %s p where p.%s is not null order by p.%s",ColumnsGiaoThongLine.TenDuong,DbStructureQ8.GiaoThongDuong,ColumnsGiaoThongLine.TenDuong,ColumnsGiaoThongLine.TenDuong);
			String SQLString_ = "select distinct g.%s from %s g "+
						"where g.%s is not null and intersects (g.the_geom, (select p.the_geom "+
						"from %s p where p.%s =  '%s')) "+								
						"order by g.%s ";
			String SQLString=String.format(SQLString_, 
					ColumnsGiaoThongLine.TenDuong,DbStructureQ8.GiaoThongDuong,ColumnsGiaoThongLine.TenDuong,
					DbStructureQ8.RanhPhuong,ColumnsRanhPhuong.MaPhuong,arg,ColumnsGiaoThongLine.TenDuong);
			_dataLoader.LoadData(_tblDuong.get_name(), SQLString);
		}
		else
		{
			this.ReadyLoadDuong();
		}
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyLoadKtvhxhTrongPhuong(java.lang.Object)
	 */
	@Override
	public void ReadyLoadKtvhxhTrongPhuong(Object arg) {
		// TODO Auto-generated method stub
		if(_tblktvhxh==null | arg==null)
		{
			return;
		}
//		String SQLString2 = "select distinct d.ten	from dc_ktvhxh d "+
//				"where d.ten is not null and Contains ((select p.the_geom	 from phuong p where p.ma_phuong =  '" + ma_phuong + "'), d.the_geom) "+
//				"order by d.ten "; 

		if(!arg.equals("Tất cả"))
		{
			String SQLString_="select distinct k.%s from %s k where k.%s is not null and Contains ((select p.the_geom from %s p where p.%s = '%s'),k.the_geom) order by k.%s";
			String SQLString = String.format(SQLString_,
					ColumnsKtvhxh.TenKtvhxh,DbStructureQ8.Ktvhxh,ColumnsKtvhxh.TenKtvhxh,
					DbStructureQ8.RanhPhuong,ColumnsRanhPhuong.MaPhuong,arg,ColumnsKtvhxh.TenKtvhxh);
			_dataLoader.LoadData(_tblktvhxh.get_name(), SQLString);
		}
		else
		{
			this.ReadyLoadKtvhxh();
		}
		
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyLoadSoNhaCu(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void ReadyLoadSoNha(Object maphuong,Object tenduong) {
		// TODO Auto-generated method stub
		if(_tblThua==null | maphuong==null | tenduong==null)
		{
			return;
		}
		if(!maphuong.equals("Tất cả"))
		{
//			SQLString = "select t.so_nha,t.so_nha_cu from thua t " +
//				 		"where t.ma_phuong = '"+ ma_phuong+"' "+ 
//				 		"and t.ten_duong = '" +Ten_Duong +"' "+
//				 		"order by t.so_nha";
			String SQLString_="select t.%s,t.%s from %s t where t.%s='%s' and t.%s='%s' order by t.%s";
			String SQLString = String.format(SQLString_,
					ColumnsThua.SoNhaMoi,ColumnsThua.SoNhaCu,DbStructureQ8.RanhThua,
					ColumnsThua.MaPhuong,maphuong, ColumnsThua.TenDuong,tenduong,ColumnsThua.SoNhaMoi);
			_dataLoader.LoadData(_tblThua.get_name(), SQLString);
		}
		else
		{
			this.ReadyLoadSoNha(tenduong);
		}
	}


	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyLoadSoNhaMoi(java.lang.Object)
	 */
	@Override
	public void ReadyLoadSoNha(Object tenduong) {
		// TODO Auto-generated method stub
		if(_tblThua==null | tenduong==null)
		{
			return;
		}
		String SQLString_="select t.%s,t.%s from %s t where t.%s='%s' order by t.%s";
		String SQLString = String.format(SQLString_,
				ColumnsThua.SoNhaMoi,ColumnsThua.SoNhaCu,DbStructureQ8.RanhThua,
				ColumnsThua.TenDuong,tenduong,ColumnsThua.SoNhaMoi);
		_dataLoader.LoadData(_tblThua.get_name(), SQLString);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyLoadGiaoLo(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void ReadyLoadGiaoLo(Object maphuong,Object tenduong) {
		// TODO Auto-generated method stub
		
		
		if(_tblThua==null | maphuong==null | tenduong==null)
		{
			return;
		}
		if(!maphuong.equals("Tất cả"))
		{
//			String SQLString2 = "select distinct g.ten_duong from gt_ln g "+
//			"where intersects (g.the_geom, (select p.the_geom from phuong p where p.ma_phuong ='"+ ma_phuong +"')) "+
//		    "and intersects (g.the_geom, (select gg.the_geom from gt_ln gg where gg.ten_duong ='"+Ten_Duong+
//		    "' and intersects (gg.the_geom, (select p.the_geom from phuong p where p.ma_phuong = '"+ma_phuong+"')) limit 1)) "+
//		    "and g.ten_duong !='" + Ten_Duong + "' "+
//			"order by g.ten_duong"; 
			String SQLString_="select distinct g.%s from %s g " +
					"where intersects (g.the_geom, (select p.the_geom from %s p where p.%s ='%s')) " +
					"and intersects (g.the_geom, (select gg.the_geom from %s gg where gg.%s ='%s' " +
					"and intersects (gg.the_geom, (select p.the_geom from %s p where p.%s = '%s')) limit 1)) " +
					"and g.%s !='%s' order by g.%s";
			String SQLString = String.format(SQLString_,
					ColumnsGiaoThongLine.TenDuong,DbStructureQ8.GiaoThongDuong,
					DbStructureQ8.RanhPhuong,ColumnsRanhPhuong.MaPhuong,maphuong,
					DbStructureQ8.GiaoThongDuong,ColumnsThua.TenDuong,tenduong,
					DbStructureQ8.RanhPhuong,ColumnsRanhPhuong.MaPhuong,maphuong,
					ColumnsGiaoThongLine.TenDuong,tenduong,ColumnsGiaoThongLine.TenDuong);
			_dataLoader.LoadData("giaolo", SQLString);
		}
		else
		{
			this.ReadyLoadGiaoLo(tenduong);
		}
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyLoadGiaoLo(java.lang.Object)
	 */
	@Override
	public void ReadyLoadGiaoLo(Object tenduong) {
		// TODO Auto-generated method stub
		if(_tblThua==null | tenduong==null)
		{
			return;
		}
		String SQLString_="select distinct g.%s from %s g " +
				"where intersects (g.the_geom, (select gg.the_geom from %s gg where gg.%s ='%s' limit 1))" +
				"and g.%s !='%s' order by g.%s";
		String SQLString = String.format(SQLString_,
				ColumnsGiaoThongLine.TenDuong,DbStructureQ8.GiaoThongDuong,
				DbStructureQ8.GiaoThongDuong,ColumnsThua.TenDuong,tenduong,
				ColumnsGiaoThongLine.TenDuong,tenduong,ColumnsGiaoThongLine.TenDuong);
		_dataLoader.LoadData("giaolo", SQLString);
		
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#GetCbxPhuongSelectedItem()
	 */
	@Override
	public Object GetCbxPhuongSelectedItem() {
		// TODO Auto-generated method stub
		return CboPhuong.getSelectedItem();
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#GetCbxDuongSelectedItem()
	 */
	@Override
	public Object GetCbxDuongSelectedItem() {
		// TODO Auto-generated method stub
		return CboDuong.getSelectedItem();
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyTimSoNha(java.lang.Boolean)
	 */
	@Override
	public void ReadyTimSoNha(Object maphuong,Object tenduong) {
		// TODO Auto-generated method stub
		if(_tblThua==null || maphuong==null || tenduong==null)
		{
			return;
		}
		Object sonha="";
		String colSonha=ColumnsThua.SoNhaMoi;
		String SQLString_="";
		String SQLString="";
		
		if(TbpSoNha.getSelectedIndex()==1)
		{
			sonha=CboSonhamoi.getSelectedItem();
			colSonha=ColumnsThua.SoNhaMoi;
		}
		else
		{
			sonha=CboSonhacu.getSelectedItem();
			colSonha=ColumnsThua.SoNhaCu;
		}
		if (ChkTimgandung.isSelected())
		{
			if(!maphuong.equals("Tất cả"))
			{
				SQLString=buildSqlForTimSonhaGandung(maphuong, tenduong, sonha, colSonha);
			}
			else
			{
				SQLString=buildSqlForTimSonhaGandung(tenduong, sonha, colSonha);
			}
		}
		else
		{
			if(!maphuong.equals("Tất cả"))
			{
				SQLString=buildSqlForTimSonhaChinhxac(maphuong, tenduong, sonha, colSonha);
			}
			else
			{
				SQLString=buildSqlForTimSonhaChinhxac(tenduong, sonha, colSonha);
			}
		}
		_dataLoader.LoadData("timsonha", SQLString);
	}
	
	//region build sqlString
	
	private String buildSqlForTimSonhaGandung(Object maphuong,Object tenduong,Object sonha,Object colSonha)
	{
		
		String So_Nha_Gan_dung = "";
		String So_Nha_Gan_dung_minus_2 = "";
		String So_Nha_Gan_dung_minus_4 = "";
		String So_Nha_Gan_dung_plus_2 = "";
		String So_Nha_Gan_dung_plus_4 = "";
		String SQLString_="";
		String SQLString="";
		int i=0;
		
		if ((sonha == null) || (sonha.equals("")))
		{
//				String SQLString_ = "select t.so_nha, t.ten_duong, t.ma_phuong,x(centroid(t.the_geom)), y (centroid(t.the_geom)) from thua t "+
//		 		"where (t.ten_duong = '" +Ten_Duong +"' "+
//		 		"or (t.ten_duong != '" +Ten_Duong +"' "+
//				"and Distance (t.the_geom,(select g.the_geom from gt_ln g where g.ten_duong = '" +Ten_Duong +"' "+ "LIMIT 1))<=30 )) "+				 		
//		 		"order by t.ma_phuong,t.ten_duong,t.so_nha";
			SQLString_ = "select t.%s, t.%s, t.%s,x(centroid(t.the_geom)), y (centroid(t.the_geom)) from %s t "+
			 		"where (t.%s = '%s' or (t.%s != '%s' "+
					"and Distance (t.the_geom,(select g.the_geom from %s g where g.%s = '%s' LIMIT 1))<=30 )) "+				 		
			 		"order by t.%s,t.%s,t.%s";
			SQLString=String.format(SQLString_,
					colSonha,ColumnsThua.TenDuong,ColumnsThua.MaPhuong,DbStructureQ8.RanhThua,
					ColumnsThua.TenDuong,tenduong,ColumnsThua.TenDuong,tenduong,
					DbStructureQ8.GiaoThongDuong,ColumnsGiaoThongLine.TenDuong,tenduong,
					ColumnsThua.MaPhuong,ColumnsThua.TenDuong,colSonha);
		}
		else
		{
			String sonhaToString=sonha.toString();
			for (i = 0; i < sonhaToString.length(); i++)
			 { 
				if (!Character.isDigit(sonhaToString.charAt(i)))
	            	break ;
			 }
			 if (i>0)
			 {
			 So_Nha_Gan_dung = sonhaToString.substring(0,i) ;
			 So_Nha_Gan_dung_minus_2 = Integer.toString(Integer.parseInt(So_Nha_Gan_dung)-2);
			 So_Nha_Gan_dung_minus_4 = Integer.toString(Integer.parseInt(So_Nha_Gan_dung)-4);
			 So_Nha_Gan_dung_plus_2 = Integer.toString(Integer.parseInt(So_Nha_Gan_dung)+2);
			 So_Nha_Gan_dung_plus_4 = Integer.toString(Integer.parseInt(So_Nha_Gan_dung)+4);
//			 SQLString = "select t.so_nha,t.ten_duong,x(centroid(t.the_geom)), y (centroid(t.the_geom)) from thua t "+
//		 		"where t.ma_phuong = '"+ ma_phuong+"' "+ 
//		 		"and (t.ten_duong = '" +Ten_Duong +"' "+
//		 		"or (t.ten_duong != '" +Ten_Duong +"' "+
//		 		"and t.ma_phuong = '"+ ma_phuong+"' "+ 
//				"and Distance (t.the_geom,(select g.the_geom from gt_ln g where g.ten_duong = '" +Ten_Duong +"' "+ "LIMIT 1))<=30 )) "+
//				"and ((t.so_nha ='"+So_Nha_Moi+"' " +
//				"or (t.so_nha similar to '%"+So_Nha_Gan_dung+"%' " +
//		 		"AND t.so_nha not similar to '%([0-9]|/)"+So_Nha_Gan_dung+"%' "+  
//		 		"AND t.so_nha not similar to '%" + So_Nha_Gan_dung+"[0-9]%')) " +
//		 		"or (t.so_nha = '" + So_Nha_Gan_dung_minus_2+"') "+
//		 		"or (t.so_nha = '" + So_Nha_Gan_dung_minus_4+"') "+
//		 		"or (t.so_nha = '" + So_Nha_Gan_dung_plus_2+"') "+
//		 		"or (t.so_nha = '" + So_Nha_Gan_dung_plus_4+"')) "+				 	
//		 		"order by t.ten_duong, t.so_nha";
//			 String test=String.format("aa  %s %%   \u002F", "bb");
//			 JOptionPane.showMessageDialog(null, test);
			 SQLString_ = "select t.%s,t.%s,x(centroid(t.the_geom)), y (centroid(t.the_geom)) from %s t "+
				 		"where t.%s = '%s' and (t.%s = '%s' or (t.%s != '%s' and t.%s = '%s' "+ 
						"and Distance (t.the_geom,(select g.the_geom from %s g where g.%s = '%s' LIMIT 1))<=30 )) "+
						"and ((t.%s ='%s' or (t.%s similar to '%%%s%%' " +
				 		"AND t.%s not similar to '%%([0-9]|\u002F)%s%%' "+  
				 		"AND t.%s not similar to '%%%s[0-9]%%')) " +
				 		"or (t.%s = '%s') or (t.%s = '%s') or (t.%s = '%s') or (t.%s = '%s')) "+				 	
				 		"order by t.%s, t.%s";
			 SQLString=String.format(SQLString_,
					 colSonha,ColumnsThua.TenDuong,DbStructureQ8.RanhThua,
					 ColumnsThua.MaPhuong,maphuong,ColumnsThua.TenDuong,tenduong,ColumnsThua.TenDuong,tenduong,ColumnsThua.MaPhuong,maphuong,
					 DbStructureQ8.GiaoThongDuong,ColumnsGiaoThongLine.TenDuong,tenduong,
					 colSonha,sonha,colSonha,So_Nha_Gan_dung,
					 colSonha,So_Nha_Gan_dung,
					 colSonha,So_Nha_Gan_dung,
					 colSonha,So_Nha_Gan_dung_minus_2,colSonha,So_Nha_Gan_dung_minus_4,colSonha,So_Nha_Gan_dung_minus_2,colSonha,So_Nha_Gan_dung_minus_4,
					 ColumnsThua.TenDuong,colSonha);
			 }
			 else
			 {
//				 SQLString = "select t.so_nha,t.ten_duong, x(centroid(t.the_geom)), y (centroid(t.the_geom)) from thua t "+
//		 		"where t.ma_phuong = '"+ ma_phuong+"' "+ 
//		 		"and (t.ten_duong = '" +Ten_Duong +"' "+
//		 		"or (t.ten_duong != '" +Ten_Duong +"' "+
//		 		"and t.ma_phuong = '"+ ma_phuong+"' "+ 
//				"and Distance (t.the_geom,(select g.the_geom from gt_ln g where g.ten_duong = '" +Ten_Duong +"' "+ "LIMIT 1))<=30 )) "+
//				"and t.so_nha like '%"+So_Nha_Moi+"%' " +				 		
//		 		"order by t.ten_duong,t.so_nha";
				 
				 SQLString_ = "select t.%s,t.%s, x(centroid(t.the_geom)), y (centroid(t.the_geom)) from %s t "+
					 		"where t.%s = '%s' and (t.%s = '%s' or (t.%s != '%s' and t.%s = '%s' "+ 
							"and Distance (t.the_geom,(select g.the_geom from %s g where g.%s = '%s' LIMIT 1))<=30 )) "+
							"and t.%s like '%%s%' order by t.%s,t.%s";
				 SQLString=String.format(SQLString_,
						 colSonha,ColumnsThua.TenDuong,DbStructureQ8.RanhThua,
						 ColumnsThua.MaPhuong,maphuong,ColumnsThua.TenDuong,tenduong,ColumnsThua.TenDuong,tenduong,ColumnsThua.MaPhuong,maphuong,
						 DbStructureQ8.GiaoThongDuong,ColumnsGiaoThongLine.TenDuong,tenduong,
						 colSonha,sonha,ColumnsThua.TenDuong,colSonha);
			 }
		}
		
		
		return SQLString;
	}
	
	private String buildSqlForTimSonhaGandung(Object tenduong,Object sonha,Object colSonha)
	{
		
		String So_Nha_Gan_dung = "";
		String So_Nha_Gan_dung_minus_2 = "";
		String So_Nha_Gan_dung_minus_4 = "";
		String So_Nha_Gan_dung_plus_2 = "";
		String So_Nha_Gan_dung_plus_4 = "";
		String SQLString_="";
		String SQLString="";
		int i=0;
		
		if ((sonha == null) || (sonha.equals("")))
		{
//				String SQLString_ = "select t.so_nha, t.ten_duong, t.ma_phuong,x(centroid(t.the_geom)), y (centroid(t.the_geom)) from thua t "+
//		 		"where (t.ten_duong = '" +Ten_Duong +"' "+
//		 		"or (t.ten_duong != '" +Ten_Duong +"' "+
//				"and Distance (t.the_geom,(select g.the_geom from gt_ln g where g.ten_duong = '" +Ten_Duong +"' "+ "LIMIT 1))<=30 )) "+				 		
//		 		"order by t.ma_phuong,t.ten_duong,t.so_nha";
			SQLString_ = "select t.%s, t.%s, t.%s,x(centroid(t.the_geom)), y (centroid(t.the_geom)) from %s t "+
			 		"where (t.%s = '%s' or (t.%s != '%s' "+
					"and Distance (t.the_geom,(select g.the_geom from %s g where g.%s = '%s' LIMIT 1))<=30 )) "+				 		
			 		"order by t.%s,t.%s,t.%s";
			SQLString=String.format(SQLString_,
					colSonha,ColumnsThua.TenDuong,ColumnsThua.MaPhuong,DbStructureQ8.RanhThua,
					ColumnsThua.TenDuong,tenduong,ColumnsThua.TenDuong,tenduong,
					DbStructureQ8.GiaoThongDuong,ColumnsGiaoThongLine.TenDuong,tenduong,
					ColumnsThua.MaPhuong,ColumnsThua.TenDuong,colSonha);
		}
		else
		{
			String sonhaToString=sonha.toString();
			for (i = 0; i < sonhaToString.length(); i++)
			 { 
				if (!Character.isDigit(sonhaToString.charAt(i)))
	            	break ;
			 }
			 if (i>0)
			 {
			 So_Nha_Gan_dung = sonhaToString.substring(0,i) ;
			 So_Nha_Gan_dung_minus_2 = Integer.toString(Integer.parseInt(So_Nha_Gan_dung)-2);
			 So_Nha_Gan_dung_minus_4 = Integer.toString(Integer.parseInt(So_Nha_Gan_dung)-4);
			 So_Nha_Gan_dung_plus_2 = Integer.toString(Integer.parseInt(So_Nha_Gan_dung)+2);
			 So_Nha_Gan_dung_plus_4 = Integer.toString(Integer.parseInt(So_Nha_Gan_dung)+4);
//			 SQLString = "select t.so_nha,t.ten_duong,x(centroid(t.the_geom)), y (centroid(t.the_geom)) from thua t "+
//		 		"where t.ma_phuong = '"+ ma_phuong+"' "+ 
//		 		"and (t.ten_duong = '" +Ten_Duong +"' "+
//		 		"or (t.ten_duong != '" +Ten_Duong +"' "+
//		 		"and t.ma_phuong = '"+ ma_phuong+"' "+ 
//				"and Distance (t.the_geom,(select g.the_geom from gt_ln g where g.ten_duong = '" +Ten_Duong +"' "+ "LIMIT 1))<=30 )) "+
//				"and ((t.so_nha ='"+So_Nha_Moi+"' " +
//				"or (t.so_nha similar to '%"+So_Nha_Gan_dung+"%' " +
//		 		"AND t.so_nha not similar to '%([0-9]|/)"+So_Nha_Gan_dung+"%' "+  
//		 		"AND t.so_nha not similar to '%" + So_Nha_Gan_dung+"[0-9]%')) " +
//		 		"or (t.so_nha = '" + So_Nha_Gan_dung_minus_2+"') "+
//		 		"or (t.so_nha = '" + So_Nha_Gan_dung_minus_4+"') "+
//		 		"or (t.so_nha = '" + So_Nha_Gan_dung_plus_2+"') "+
//		 		"or (t.so_nha = '" + So_Nha_Gan_dung_plus_4+"')) "+				 	
//		 		"order by t.ten_duong, t.so_nha";
			 SQLString_ = "select t.%s,t.%s,x(centroid(t.the_geom)), y (centroid(t.the_geom)) from %s t "+
				 		"where (t.%s = '%s' or (t.%s != '%s' "+ 
						"and Distance (t.the_geom,(select g.the_geom from %s g where g.%s = '%s' LIMIT 1))<=30 )) "+
						"and ((t.%s ='%s' or (t.%s similar to '%%%s%%' " +
				 		"AND t.%s not similar to '%%([0-9]\u002F)%s%%' "+  
				 		"AND t.%s not similar to '%%%s[0-9]%%')) " +
				 		"or (t.%s = '%s') or (t.%s = '%s') or (t.%s = '%s') or (t.%s = '%s')) "+				 	
				 		"order by t.%s, t.%s";
			 SQLString=String.format(SQLString_,
					 colSonha,ColumnsThua.TenDuong,DbStructureQ8.RanhThua,
					 ColumnsThua.TenDuong,tenduong,ColumnsThua.TenDuong,tenduong,
					 DbStructureQ8.GiaoThongDuong,ColumnsGiaoThongLine.TenDuong,tenduong,
					 colSonha,sonha,colSonha,So_Nha_Gan_dung,
					 colSonha,So_Nha_Gan_dung,
					 colSonha,So_Nha_Gan_dung,
					 colSonha,So_Nha_Gan_dung_minus_2,colSonha,So_Nha_Gan_dung_minus_4,colSonha,So_Nha_Gan_dung_minus_2,colSonha,So_Nha_Gan_dung_minus_4,
					 ColumnsThua.TenDuong,colSonha);
			 }
			 else
			 {
//				 SQLString = "select t.so_nha,t.ten_duong, x(centroid(t.the_geom)), y (centroid(t.the_geom)) from thua t "+
//		 		"where t.ma_phuong = '"+ ma_phuong+"' "+ 
//		 		"and (t.ten_duong = '" +Ten_Duong +"' "+
//		 		"or (t.ten_duong != '" +Ten_Duong +"' "+
//		 		"and t.ma_phuong = '"+ ma_phuong+"' "+ 
//				"and Distance (t.the_geom,(select g.the_geom from gt_ln g where g.ten_duong = '" +Ten_Duong +"' "+ "LIMIT 1))<=30 )) "+
//				"and t.so_nha like '%"+So_Nha_Moi+"%' " +				 		
//		 		"order by t.ten_duong,t.so_nha";
				 
				 SQLString_ = "select t.%s,t.%s, x(centroid(t.the_geom)), y (centroid(t.the_geom)) from %s t "+
					 		"where (t.%s = '%s' or (t.%s != '%s' "+ 
							"and Distance (t.the_geom,(select g.the_geom from %s g where g.%s = '%s' LIMIT 1))<=30 )) "+
							"and t.%s like '%%s%' order by t.%s,t.%s";
				 SQLString=String.format(SQLString_,
						 colSonha,ColumnsThua.TenDuong,DbStructureQ8.RanhThua,
						 ColumnsThua.TenDuong,tenduong,ColumnsThua.TenDuong,tenduong,
						 DbStructureQ8.GiaoThongDuong,ColumnsGiaoThongLine.TenDuong,tenduong,
						 colSonha,sonha,ColumnsThua.TenDuong,colSonha);
			 }
		}
		
		
		return SQLString;
	}
	
	private String buildSqlForTimSonhaChinhxac(Object maphuong,Object tenduong,Object sonha,Object colSonha)
	{
		String SQLString_="";
		String SQLString="";
		if ((sonha == null) || (sonha.equals("")))
		{
//			SQLString = "select t.so_nha, x(centroid(t.the_geom)), y (centroid(t.the_geom)) from thua t "+
//			 		"where t.ma_phuong = '"+ ma_phuong+"' "+ 
//				 		"and t.ten_duong = '" +Ten_Duong +"' "+				 		
//				 		"order by t.ten_duong, t.so_nha"; 
			SQLString_ = "select t.%s, x(centroid(t.the_geom)), y (centroid(t.the_geom)) from %s t "+
			 		"where t.%s = '%s' and t.%s = '%s' order by t.%s, t.%s";
			SQLString=String.format(SQLString_,
					colSonha,ColumnsThua.TenDuong,DbStructureQ8.RanhThua,
					ColumnsThua.MaPhuong,maphuong,ColumnsThua.TenDuong,tenduong,ColumnsThua.TenDuong,colSonha);
		}
		else
		{
//			SQLString = "select t.so_nha, x(centroid(t.the_geom)), y (centroid(t.the_geom)) from thua t " +
//			 		"where t.ma_phuong = '"+ ma_phuong+"' "+ 
//			 		"and t.ten_duong = '" +Ten_Duong +"' "+
//			 		"and t.so_nha = '" + So_Nha_Moi +"' "+
//			 		"order by t.ten_duong, t.so_nha";
			SQLString_ = "select t.%s, x(centroid(t.the_geom)), y (centroid(t.the_geom)) from %s t " +
			 		"where t.%s = '%s' and t.%s = '%s' and t.%s = '%s' order by t.%s, t.%s";
			SQLString=String.format(SQLString_,
					colSonha,ColumnsThua.TenDuong,DbStructureQ8.RanhThua,
					ColumnsThua.MaPhuong,maphuong,ColumnsThua.TenDuong,tenduong,colSonha,sonha,ColumnsThua.TenDuong,colSonha);
		}
		return SQLString;
	}
	
	private String buildSqlForTimSonhaChinhxac(Object tenduong,Object sonha,Object colSonha)
	{
		String SQLString_="";
		String SQLString="";
		if ((sonha == null) || (sonha.equals("")))
		{
//			SQLString = "select t.so_nha, x(centroid(t.the_geom)), y (centroid(t.the_geom)) from thua t "+
//			 		"where t.ma_phuong = '"+ ma_phuong+"' "+ 
//				 		"and t.ten_duong = '" +Ten_Duong +"' "+				 		
//				 		"order by t.ten_duong, t.so_nha"; 
			SQLString_ = "select t.%s, x(centroid(t.the_geom)), y (centroid(t.the_geom)) from %s t "+
			 		"where t.%s = '%s' order by t.%s, t.%s";
			SQLString=String.format(SQLString_,
					colSonha,ColumnsThua.TenDuong,DbStructureQ8.RanhThua,
					ColumnsThua.TenDuong,tenduong,ColumnsThua.TenDuong,colSonha);
		}
		else
		{
//			SQLString = "select t.so_nha, x(centroid(t.the_geom)), y (centroid(t.the_geom)) from thua t " +
//			 		"where t.ma_phuong = '"+ ma_phuong+"' "+ 
//			 		"and t.ten_duong = '" +Ten_Duong +"' "+
//			 		"and t.so_nha = '" + So_Nha_Moi +"' "+
//			 		"order by t.ten_duong, t.so_nha";
			SQLString_ = "select t.%s, x(centroid(t.the_geom)), y (centroid(t.the_geom)) from %s t " +
			 		"where t.%s = '%s' and t.%s = '%s' order by t.%s, t.%s";
			SQLString=String.format(SQLString_,
					colSonha,ColumnsThua.TenDuong,DbStructureQ8.RanhThua,
					ColumnsThua.TenDuong,tenduong,colSonha,sonha,ColumnsThua.TenDuong,colSonha);
		}
		return SQLString;
	}

	//endregion
	
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyZoomToDuong(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void ReadyZoomToDuong(Object maphuong, Object tenduong) {
		// TODO Auto-generated method stub
//		String SQLString =
//					"select X(ST_PointN(g.the_geom,2)),Y(ST_PointN(g.the_geom,2)) from gt_ln g "+
//					"where intersects (g.the_geom, (select p.the_geom "+
//					"from phuong p where p.ma_phuong =  '" + ma_phuong+ "')) " +						
//						"and g.ten_duong='"+Ten_duong + "' limit 1";
		if(!(maphuong.equals("Tất cả")))
		{
			String SQLString_ =
						"select X(ST_PointN(g.the_geom,2)),Y(ST_PointN(g.the_geom,2)) from %s g "+
						"where intersects (g.the_geom, (select p.the_geom "+
						"from %s p where p.%s =  '%s')) and g.%s='%s' limit 1";
			String SQLString=String.format(SQLString_,
					DbStructureQ8.GiaoThongDuong,DbStructureQ8.RanhPhuong,
					ColumnsRanhPhuong.MaPhuong,maphuong,ColumnsGiaoThongLine.TenDuong,tenduong);
			_dataLoader.LoadData("zoomToExtent", SQLString);
		}
		else
		{
			this.ReadyZoomToDuong(tenduong);
		}
	}
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyZoomToDuong(java.lang.Object)
	 */
	@Override
	public void ReadyZoomToDuong(Object tenduong) {
		// TODO Auto-generated method stub
			if(tenduong==null)
			{
				return;
			}
//		String SQLString =
//					"select X(ST_PointN(g.the_geom,2)),Y(ST_PointN(g.the_geom,2)) from gt_ln g "+
//					" where g.ten_duong='"+Ten_duong + "' limit 1";	
		String SQLString_ =
					"select X(ST_PointN(g.the_geom,2)),Y(ST_PointN(g.the_geom,2)) from %s g "+
					" where g.%s='%s' limit 1";	
		String SQLString=String.format(SQLString_,
				DbStructureQ8.GiaoThongDuong,ColumnsGiaoThongLine.TenDuong,tenduong);
		_dataLoader.LoadData("zoomToExtent", SQLString);
	}
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyZoomToGiaoLo(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void ReadyZoomToGiaoLo(Object maphuong, Object tenduong,
			Object tengiaolo) {
		// TODO Auto-generated method stub
//		String SQLString_ = "select X(intersection(g1.the_geom, g2.the_geom)),Y(intersection(g1.the_geom, g2.the_geom)) "+
//					"from gt_ln g1, gt_ln g2 "+
//					"where g1.ten_duong = '" +ten_duong + "' and g2.ten_duong = '"+ten_Giaolo+"' "+
//					"and Intersects(g2.the_geom, (select p.the_geom " +
//					//"and intersects (intersection(g1.the_geom, g2.the_geom), (select p.the_geom "+
//							"from phuong p where p.ma_phuong =  '" + ma_phuong+ "')) and Intersects(g1.the_geom, g2.the_geom) " ;
		if(maphuong==null || tenduong==null || tengiaolo==null)
		{
			return;
		}
		if(!(maphuong.equals("Tất cả")))
		{
			String SQLString_ = "select X(intersection(g1.the_geom, g2.the_geom)),Y(intersection(g1.the_geom, g2.the_geom)) "+
						"from %s g1, %s g2 "+
						"where g1.%s = '%s' and g2.%s = '%s' "+
						"and Intersects(g2.the_geom, (select p.the_geom " +
						"from %s p where p.%s =  '%s')) and Intersects(g1.the_geom, g2.the_geom) " ;
			String SQLString=String.format(SQLString_,
					DbStructureQ8.GiaoThongDuong,DbStructureQ8.GiaoThongDuong,
					ColumnsGiaoThongLine.TenDuong,tenduong,ColumnsGiaoThongLine.TenDuong,tengiaolo,
					DbStructureQ8.RanhPhuong,ColumnsRanhPhuong.MaPhuong,maphuong);
			_dataLoader.LoadData("zoomToPoint", SQLString);
		}
		else
		{
			this.ReadyZoomToGiaoLo(tenduong, tengiaolo);
		}
	}
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyZoomToGiaoLo(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void ReadyZoomToGiaoLo(Object tenduong, Object tengiaolo) {
		// TODO Auto-generated method stub
//		 String SQLString = "select X(intersection(g1.the_geom, g2.the_geom)),Y(intersection(g1.the_geom, g2.the_geom)) "+
//					"from gt_ln g1, gt_ln g2 "+
//					"where g1.ten_duong = '" +ten_duong + "' and g2.ten_duong = '"+ten_Giaolo+"' "+
//					"and Intersects(g1.the_geom, g2.the_geom)";
		if(tenduong==null || tengiaolo==null)
		{
			return;
		}
		 String SQLString_ = "select X(intersection(g1.the_geom, g2.the_geom)),Y(intersection(g1.the_geom, g2.the_geom)) "+
					"from %s g1, %s g2 "+
					"where g1.%s = '%s' and g2.%s = '%s' "+
					"and Intersects(g1.the_geom, g2.the_geom)";
		 String SQLString=String.format(SQLString_,
					DbStructureQ8.GiaoThongDuong,DbStructureQ8.GiaoThongDuong,
					ColumnsGiaoThongLine.TenDuong,tenduong,ColumnsGiaoThongLine.TenDuong,tengiaolo);
			_dataLoader.LoadData("zoomToPoint", SQLString);
	}
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyZoomToKtvhxh(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void ReadyZoomToKtvhxh(Object maphuong, Object tenktvhxh) {
		// TODO Auto-generated method stub
		if(maphuong==null || tenktvhxh==null)
		{
			return;
		}
//		
//		String SQLString = "select X(Centroid(d.the_geom)),Y(Centroid(d.the_geom)) "+
//					"from dc_ktvhxh d "+
//					"where d.ten = '" +Ten_Diavat + "' "+
//					"and Contains ((select p.the_geom from phuong p where p.ma_phuong ='" + ma_phuong+ "'), d.the_geom) "	;
		if(!(maphuong.equals("Tất cả")))
		{
			String SQLString_ = "select X(Centroid(d.the_geom)),Y(Centroid(d.the_geom)) "+
					"from %s d "+
					"where d.%s = '%s' "+
					"and Contains ((select p.the_geom from %s p where p.%s ='%s'), d.the_geom) "	;
			String SQLString=String.format(SQLString_,
					DbStructureQ8.Ktvhxh,ColumnsKtvhxh.TenKtvhxh,tenktvhxh,
					DbStructureQ8.RanhPhuong,ColumnsRanhPhuong.MaPhuong,maphuong);
			_dataLoader.LoadData("zoomToPoint", SQLString);
		}
		else
		{
			this.ReadyZoomToKtvhxh(tenktvhxh);
		}
	}
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyZoomToKtvhxh(java.lang.Object)
	 */
	@Override
	public void ReadyZoomToKtvhxh(Object tenktvhxh) {
		// TODO Auto-generated method stub
		if(tenktvhxh==null)
		{
			return;
		}
		String SQLString_ = "select X(Centroid(d.the_geom)),Y(Centroid(d.the_geom)) "+
				"from %s d "+
				"where d.%s = '%s' ";
		String SQLString=String.format(SQLString_,
				DbStructureQ8.Ktvhxh,ColumnsKtvhxh.TenKtvhxh,tenktvhxh);
		_dataLoader.LoadData("zoomToPoint", SQLString);
	}
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyZoomToPhuong(java.lang.Object)
	 */
	@Override
	public void ReadyZoomToPhuong(Object maphuong) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#GetCbxGiaoLoSelectedItem()
	 */
	@Override
	public Object GetCbxGiaoLoSelectedItem() {
		// TODO Auto-generated method stub
		return CboGiaolo.getSelectedItem();
	}
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#GetCbxKtvhxhSelectedItem()
	 */
	@Override
	public Object GetCbxKtvhxhSelectedItem() {
		// TODO Auto-generated method stub
		return CboDiavat.getSelectedItem();
	}
	
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyLoadObject()
	 */
	@Override
	public void ReadyLoadObject() {
		// TODO Auto-generated method stub
		String exactTable="";
		int i=cbxLoaiDn.getSelectedIndex();
		if(i==0)
		{
			exactTable="dn_"+_tblObject.get_name();
		}
		else
		{
			exactTable="hkd_"+_tblObject.get_name();
		}
		String SQLString_ = "SELECT %s, ten_chu, banghieu,loaihinh_kd, so_nha, ten_duong, X(the_geom), Y(the_geom) from %s order by %s";
		String SQLString=String.format(SQLString_,
				_tblObject.get_primaryKey(),exactTable,_tblObject.get_primaryKey());
		_dataLoader.LoadData("getObject", SQLString);
	}
	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#ReadyLoadObject(java.lang.Object)
	 */
	@Override
	public void ReadyLoadObject(Object maphuong) {
		// TODO Auto-generated method stub
		if(maphuong==null || _tblObject==null)
		{
			return;
		}
		if(!(maphuong.equals("Tất cả")))
		{
//			
//			String SQLString_ = "SELECT ma_internet, ten_chu, banghieu,loaihinh_kd, so_nha, ten_duong, X(the_geom), Y(the_geom) from "+_tableInterNetName+" where phuong = '" + ma_phuong + "' or phuong = 'Phường "+ma_phuong+"' order by ma_internet";
			String exactTable="";
			int i=cbxLoaiDn.getSelectedIndex();
			if(i==0)
			{
				exactTable="dn_"+_tblObject.get_name();
			}
			else
			{
				exactTable="hkd_"+_tblObject.get_name();
			}
			String SQLString_ = "SELECT %s, ten_chu, banghieu,loaihinh_kd, so_nha, ten_duong, X(the_geom), Y(the_geom) from %s where phuong = '%s' or phuong = 'Phường %s' order by %s";
			String SQLString=String.format(SQLString_,
					_tblObject.get_primaryKey(),exactTable,maphuong,maphuong,_tblObject.get_primaryKey());
			_dataLoader.LoadData("getObject", SQLString);
		}
		else
		{
			this.ReadyLoadObject();
		}
	}
	
	//endregion
	
	class AcSaveSpatial extends AbstractAction {
		public AcSaveSpatial(String text, ImageIcon icon,
                String desc, Integer mnemonic) {
		  super(text, icon);
		}
		public void actionPerformed(java.awt.event.ActionEvent e) {
			Object[] option = {"Có", "Không"};
			int viewRow = TblInternet.getSelectedRow();
			rowupdated = TblInternet.convertRowIndexToModel(viewRow);
			ClearSelection("thua");
			TblInternet.setEnabled(true);
	    	//response = JOptionPane.showConfirmDialog(null,"Bạn có thực sự muốn xóa dữ liệu","Thông báo",JOptionPane.YES_NO_OPTION);
			int response = javax.swing.JOptionPane.showOptionDialog(null, "Bạn có thực sự muốn cập nhật vị trí mới ?",
		                "Thông báo", javax.swing.JOptionPane.YES_NO_OPTION,
		                javax.swing.JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
	    	if (response == 0) 
	    	  {
	    		Integer ma_gas = Integer.parseInt((String) TblInternet.getValueAt(TblInternet.getSelectedRow(),1) );
	    		SaveSpatial(ma_gas, Quan8Functions.XXInternet, Quan8Functions.YYInternet);
	    		TblInternet.setAutoCreateRowSorter(false);
				 TblInternet.setRowSorter(null);
	    	  }
	    	else 
	    		{
	    		Quan8Functions.XXInternet = 0.0;
	    		Quan8Functions.YYInternet = 0.0;
		    	}
	    	//refresh
    		if (CboPhuongInternet.getItemCount()>0)
				if (!CboPhuongInternet.getSelectedItem().equals("Tất cả"))
					Refresh(Integer.parseInt(CboPhuongInternet.getSelectedItem().toString()));
				else
					Refresh();
    		acSaveSpatial.setEnabled(false);
		}
	}
	
	class AcAdd extends AbstractAction {
		public AcAdd(String text, ImageIcon icon, String desc,
				Integer mnemonic) {
			super(text, icon);
		}

		public void actionPerformed(java.awt.event.ActionEvent e) {
			
				try {
					currentrow=TblInternet.getRowCount();
					String phuong =CboPhuongInternet.getSelectedItem().toString();
					String loaihinh= TblInternet.getValueAt(TblInternet.getSelectedRow(), 4).toString();
					Integer ma= Integer.parseInt(TblInternet.getValueAt(TblInternet.getSelectedRow(), 1).toString());
					_detailView.SetKeyValue(ma);
//					fTTCT = new Quan8TTCTInternet(ma,phuong,loaihinh,_tableInterNetName);
					_detailView.SetModal(true);
					//fTTCT.setAlwaysOnTop(true);
					_detailView.SetLocationRelativeTo(TblInternet);
					_detailView.SetVisible(true);	
					//Reload("internet");
					 TblInternet.setAutoCreateRowSorter(false);
					 TblInternet.setRowSorter(null);
					// TblTTVP.setEnabled(false);
					if (CboPhuongInternet.getItemCount()>0)
						if (!CboPhuongInternet.getSelectedItem().equals("Tất cả"))
							RefreshAdd(Integer.parseInt(CboPhuongInternet.getSelectedItem().toString()));
						else
							RefreshAdd();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
		
	}
	
	public void ClearSelection(String TableName)
	{
			try {
				FLayer[] visibles = _mapControl.getMapContext().getLayers().getVisibles();
				FLayer LayerTableName = null; 
				// Ki` la'm nghen, visible chi co 2 lop nhung cai ham getVisibles tra ve 3 , nen phai sua lai la j = 1. Cai nay de ngam kiu lai sau! 
				 for (int j = 1; j < visibles.length ; j++) {
					//JOptionPane.showMessageDialog(null,visibles[j].getName().toString() + " " + j);
					 if (visibles[j].getName().contains(TableName))
						{	
							LayerTableName = visibles[j];			
						}
				 }
				if ((LayerTableName != null))
					 {
						FLyrVect lyrVect = (FLyrVect) LayerTableName ;	            	
						ReadableVectorial rv = lyrVect.getSource();
						rv.getRecordset().clearSelection();
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.toString());
			}
	}
	
	public  void Refresh ()	
	{
		this.ReadyLoadObject();
		_mapControl.getMapContext().getGraphicsLayer().clearAllGraphics();		
			_mapControl.getMapContext().invalidate();
			_mapControl.repaint();
	}
	
	private void RefreshAdd(Object maphuong)
	{
		this.ReadyLoadObject();
//		String exactTable="";
//		int i=cbxLoaiDn.getSelectedIndex();
//		if(i==0)
//		{
//			exactTable="dn_"+_tblObject.get_name();
//		}
//		else
//		{
//			exactTable="hkd_"+_tblObject.get_name();
//		}
////		String SQLString_ = "SELECT ma_internet, ten_chu, banghieu,loaihinh_kd, so_nha, ten_duong, X(the_geom), Y(the_geom) from internet order by ma_internet";
//		String SQLString_ = "SELECT %s, ten_chu, banghieu,loaihinh_kd, so_nha, ten_duong, X(the_geom), Y(the_geom) from %s order by %s";
//		String SQLString=String.format(SQLString_, _tblObject.get_primaryKey(),exactTable,_tblObject.get_primaryKey());
	}
	
	public void Refresh (Object maphuong)	
	{
		this.ReadyLoadObject(maphuong);
		_mapControl.getMapContext().getGraphicsLayer().clearAllGraphics();		
		_mapControl.getMapContext().invalidate();
		_mapControl.repaint();
		
	}
	private void RefreshAdd()
	{
		this.ReadyLoadObject();
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#SetDetailView(gov.vn.hcmgis.projects.gui.IObjectDetailView)
	 */
	@Override
	public void SetDetailView(IObjectDetailView detailView) {
		// TODO Auto-generated method stub
		_detailView=detailView;
		_detailView.SetHook(this);
		TblInternet.SetDetailView(detailView);
	}
	
	public void Call_TTCT()
	{
		try {
			Integer ma = Integer.parseInt(TblInternet.getValueAt(TblInternet.getSelectedRow(), 1).toString());
			//Integer	matuyen =Integer.parseInt(TblHoGD.getValueAt(TblHoGD.getSelectedRow(), 2).toString());
			//Integer	phuong =Integer.parseInt(TblHoGD.getValueAt(TblHoGD.getSelectedRow(), 5).toString());
			//Integer	ma_hogd =Integer.parseInt(TblHoGD.getValueAt(TblHoGD.getSelectedRow(), 11).toString());
			
//			fTTCT = new Quan8TTCTInternet(ma,_tableInterNetName);
			_detailView.SetKeyValue(ma);
			_detailView.SetModal(true);
			//fTTCT.setAlwaysOnTop(true);
			_detailView.SetLocationRelativeTo(TblInternet);
			_detailView.SetVisible(true);	
			//Refresh();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	void SaveSpatial(Integer ma_gas, double XX, double YY)
	{
		if ((XX == 0) || (YY == 0))
		{
			JOptionPane.showMessageDialog(null,"Cập nhật không thành công. Vui lòng chọn công cụ cập nhật không gian!");
			return;
		}
		else 
		{
				String exactTable="";
				int i=cbxLoaiDn.getSelectedIndex();
				if(i==0)
				{
					exactTable="dn_"+_tblObject.get_name();
				}
				else
				{
					exactTable="hkd_"+_tblObject.get_name();
				}

				   // int updaterow = TblInternet.getSelectedRow();
//				    String UpdateSQLString_ = " UPDATE internet set the_geom = GeomFromText('POINT(";
//				    UpdateSQLString += XX + " " + YY+ ")', 32648) WHERE ma_internet = ?";
				    String UpdateSQLString_ = " UPDATE %s set the_geom = GeomFromText('POINT(%s %s)', 32648) WHERE %s = '%s'";
				    String UpdateSQLString=String.format(UpdateSQLString_,exactTable,XX,YY,_tblObject.get_primaryKey(),ma_gas);
				   _dataLoader.LoadData("saveSpatial", UpdateSQLString);
				     Quan8Functions.XXInternet = 0.0;
				     Quan8Functions.YYInternet = 0.0;
		

	}
	}
	
	class AcDeleteSpatial extends AbstractAction {
		public AcDeleteSpatial(String text, ImageIcon icon,
                String desc, Integer mnemonic) {
		  super(text, icon);
		}
		public void actionPerformed(java.awt.event.ActionEvent e) {
			Object[] option = {"Có", "Không"};    
	    	//response = JOptionPane.showConfirmDialog(null,"Bạn có thực sự muốn xóa dữ liệu","Thông báo",JOptionPane.YES_NO_OPTION);
			ClearSelection("thua");
			TblInternet.setEnabled(true);
			int viewRow = TblInternet.getSelectedRow();
			rowupdated = TblInternet.convertRowIndexToModel(viewRow);
			int response = javax.swing.JOptionPane.showOptionDialog(null, "Bạn có thực sự muốn xóa vị trí ?",
		                "Thông báo", javax.swing.JOptionPane.YES_NO_OPTION,
		                javax.swing.JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
	    	if (response == 0) 
	    	  {
	    		Integer ma = Integer.parseInt((String) TblInternet.getValueAt(TblInternet.getSelectedRow(),1) );
	    		DeleteSpatial(ma);
	    		TblInternet.setAutoCreateRowSorter(false);
				 TblInternet.setRowSorter(null);
	    	  }
	    	    		if (CboPhuongInternet.getItemCount()>0)
    				if (!CboPhuongInternet.getSelectedItem().equals("Tất cả"))
    					Refresh(Integer.parseInt(CboPhuongInternet.getSelectedItem().toString()));
    				else
    					Refresh();
		}
	}
	
	void DeleteSpatial(Integer ma_gas)
	{
		try {
			String exactTable="";
			int i=cbxLoaiDn.getSelectedIndex();
			if(i==0)
			{
				exactTable="dn_"+_tblObject.get_name();
			}
			else
			{
				exactTable="hkd_"+_tblObject.get_name();
			}
			    int updaterow = TblInternet.getSelectedRow();
//			    String UpdateSQLString_ = " UPDATE internet set the_geom = null WHERE ma_internet = ?";
			    String UpdateSQLString_ = " UPDATE %s set the_geom = null WHERE %s = '%s'";
			    String UpdateSQLString=String.format(UpdateSQLString_,exactTable,_tblObject.get_primaryKey(),ma_gas);
			    _dataLoader.LoadData("deleteSpatial", UpdateSQLString);
			 }
			  
		  catch (Exception ex) {
			  JOptionPane.showMessageDialog(null,ex);
		  }
	}
	
	class AcDelete extends AbstractAction {
		public AcDelete(String text, ImageIcon icon, String desc,
				Integer mnemonic) {
			super(text, icon);
		}

		public void actionPerformed(java.awt.event.ActionEvent e) {
			
			Object[] option = { "Có", "Không" };
			int response = javax.swing.JOptionPane.showOptionDialog(null,
					"Bạn có thực sự muốn xóa dữ liệu ?", "Thông báo",
					javax.swing.JOptionPane.YES_NO_OPTION,
					javax.swing.JOptionPane.QUESTION_MESSAGE, null, option,
					option[1]);
			if (response == 0) {
				int viewRow = TblInternet.getSelectedRow();

				rowupdated = TblInternet.convertRowIndexToModel(viewRow);
				
	    		Integer ma = Integer.parseInt((String) TblInternet.getValueAt(TblInternet.getSelectedRow(),1) );
	    		Delete(ma);
	    		TblInternet.setAutoCreateRowSorter(false);
				 TblInternet.setRowSorter(null);
	    		if (CboPhuongInternet.getItemCount()>0)
					if (!CboPhuongInternet.getSelectedItem().equals("Tất cả"))
						Refresh(Integer.parseInt(CboPhuongInternet.getSelectedItem().toString()));
					else
						Refresh();			
			}
		}
		
	}
	public void Delete(int ma) 
	{
		String exactTable="";
		int i=cbxLoaiDn.getSelectedIndex();
		if(i==0)
		{
			exactTable="dn_"+_tblObject.get_name();
		}
		else
		{
			exactTable="hkd_"+_tblObject.get_name();
		}
//			    String UpdateSQLString_ = " DELETE FROM internet WHERE ma_internet = ?";
			    String UpdateSQLString_ = " DELETE FROM %s WHERE %s = '%s'";
			    String UpdateSQLString=String.format(UpdateSQLString_,exactTable,_tblObject.get_primaryKey(),ma);
			    _dataLoader.LoadData("deleteAtt", UpdateSQLString);

	}
class PopupListener extends MouseAdapter {
		
		public void mouseClicked(MouseEvent e) {
			
			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
			
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			showPopup(e);
		
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			showPopup(e);
			
		}
		 private void showPopup(MouseEvent e) {
		      if (e.isPopupTrigger()) {
		    	  JPopupMenu popupMenu = new JPopupMenu();
		    	 // popupMenu.setBorder(BorderFactory.createLineBorder(Color.RED));
		    	  popupMenu.add(getMniAdd());
		    	  popupMenu.add(getMniDeleteRow());
		    	  popupMenu.addSeparator();
		    	  popupMenu.add(getMniDelete());
		    	  popupMenu.add(getMniUpdate());
		    	  popupMenu.addSeparator();
		    	  popupMenu.add(getMniDetail());
		    	  popupMenu.add(getMniVipham());
		    	  popupMenu.addSeparator();
		    	  popupMenu.add(getMniImport());
		    	     	  popupMenu.show(e.getComponent(), e.getX(), e.getY());
		    	  
		      }
		    }
		 
		}

/* (non-Javadoc)
 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#GetObjectTable()
 */
@Override
public TableStructure GetObjectTable() {
	// TODO Auto-generated method stub
	return _tblObject;
}
/* (non-Javadoc)
 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#GetExactTableName()
 */
@Override
public String GetExactTableName() {
	// TODO Auto-generated method stub
	int i=cbxLoaiDn.getSelectedIndex();
	String exactTable="";
	if(i==0)
	{
		exactTable="dn_"+_tblObject.get_name();
	}
	else
	{
		exactTable="hkd_"+_tblObject.get_name();
	}
	return exactTable;
}
public void Filter()
{
	 DefaultTableModel dm = (DefaultTableModel)TblInternet.getModel();
	 TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dm);
			 TblInternet.setRowSorter(sorter);
			 
	 String text = txtFilter.getText();
        if (text.length() == 0) {
          sorter.setRowFilter(null);
        } 
        else
          sorter.setRowFilter(RowFilter.regexFilter("(?i)"+ text, 2));
  
       // sorter.setRowFilter(RowFilter.regexFilter(text.toUpperCase(), 2));
    
}
/* (non-Javadoc)
 * @see gov.vn.hcmgis.projects.gui.ILocationUpdateView#GetTable()
 */
@Override
public JTable GetTable() {
	// TODO Auto-generated method stub
	return this.TblInternet;
}
@Override
public MapControl GetMapControl() {
	// TODO Auto-generated method stub
	return this._mapControl;
}
@Override
public void RefreshAfterUpdatePoint(int row_updated) {
	// TODO Auto-generated method stub
	if (CboPhuongInternet.getItemCount()>0)
	{
		if (!CboPhuongInternet.getSelectedItem().equals("Tất cả"))
			Refresh(Integer.parseInt(CboPhuongInternet.getSelectedItem().toString()));
		else
			Refresh();
		
		System.out.println(String.format("line 3082 LocationUpdateView: rowupdated=%s",row_updated));
		TblInternet.requestFocus();
		TblInternet.setRowSelectionInterval(row_updated,row_updated);
	}
	acSaveSpatial.setEnabled(false);
}
@Override
public void ReadySavePoint(int keyVal,double XX, double YY) {
	// TODO Auto-generated method stub
	if ((XX == 0) || (YY == 0))
	{
		JOptionPane.showMessageDialog(null,"Cập nhật không thành công. Vui lòng chọn công cụ cập nhật không gian!");
		return;
	}
	else 
		try {
			 
			    String UpdateSQLString = " UPDATE "+this.GetExactTableName()+" set the_geom = GeomFromText('POINT(";
			    UpdateSQLString += XX + " " + YY+ ")', 32648) WHERE "+this._tblObject.get_primaryKey()+" = '"+keyVal+"'";
			    _dataLoader.UpdateSpatial("updatePoint", UpdateSQLString);
//			     GoVapFunctions.XXInternet = 0.0;
//			     GoVapFunctions.YYInternet = 0.0;
			 }
			  
		  catch (Exception ex) {
			  JOptionPane.showMessageDialog(null,ex);
		  }
}
@Override
public void ReadyUpdatePoint() {
	// TODO Auto-generated method stub
	acSaveSpatial.setEnabled(true);	 				
	Quan8Functions.XXInternet =  0.0;
	 Quan8Functions.YYInternet = 0.0;
	TblInternet.setEnabled(false);
//	GovapUpdateSpatialInternetListener li = null;
	UpdatePointListener li = UpdatePointListener.CallMe(this);
	if(_mapControl.getMapTool("CapnhatKhonggian")==null)
	{
		_mapControl.addMapTool("CapnhatKhonggian", new PointBehavior(li));
	}
   _mapControl.setTool("CapnhatKhonggian");
}
	
}
