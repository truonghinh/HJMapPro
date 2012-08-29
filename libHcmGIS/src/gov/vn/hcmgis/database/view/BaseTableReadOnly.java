/**
 * 
 */
package gov.vn.hcmgis.database.view;

import gov.vn.hcmgis.core.actions.onTable.ActExImExcel;
import gov.vn.hcmgis.core.actions.onTable.ActShowDetail;
import gov.vn.hcmgis.core.adapters.DefaultMouseEventOnTable;
import gov.vn.hcmgis.core.adapters.MouseEventOnTable;
import gov.vn.hcmgis.database.dataModel.IDataReceiver;
import gov.vn.hcmgis.database.dataModel.TableStructure;
import gov.vn.hcmgis.projects.gui.ILocationUpdateView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

/**
 * @author HT
 *
 */
public abstract class BaseTableReadOnly extends JTable implements IGTable,IDataReceiver {
	private static final long serialVersionUID = 1L;
	protected MouseEventOnTable mouseEventTable;

	protected JMenuItem mniDelete = null;
	protected JMenuItem mniVipham = null;
	protected JMenuItem mniAdd = null;
	protected JMenuItem mniUpdate = null;
	protected JMenuItem mniDeleteRow = null;
	protected JMenuItem mniImportExcel = null;
	protected JMenuItem mniDetail =null;
	
	protected Action  actSaveSpatial;
	protected Action actDelete;
	protected Action actDeleteSpatial;
	protected Action actAdd;
	protected ActExImExcel acImportExcel;
	protected ActShowDetail actDetail;
	
	protected IObjectDetailView _detailView;
	protected TableStructure _tableStructure;
	protected JMenuItem mniUpdate1 = null;
	protected JMenuItem getMniUpdate1() {
		if (mniUpdate1 == null) {
			mniUpdate1 = new JMenuItem();
			mniUpdate1.setText("Cập nhật vị trí");
//			mniUpdate1.setAction(actSaveSpatial);
		}
		return mniUpdate;
	}
	
	public BaseTableReadOnly() 
	{
		super();
		iniActions();
		mouseEventTable=new DefaultMouseEventOnTable(this);
//		mouseEventTable.Add(getMniAdd());
//		mouseEventTable.Add(getMniDeleteRow());
//		mouseEventTable.AddSeparator();
//		mouseEventTable.Add(getMniDelete());
//		mouseEventTable.Add(getMniUpdate());
//		mouseEventTable.AddSeparator();
		mouseEventTable.Add(getMniDetail());
		mouseEventTable.AddSeparator();
		mouseEventTable.Add(getMniImport());
		this.addMouseListener(mouseEventTable);
	}
	
	//region protected part
		protected void iniActions() {
			// TODO Auto-generated method stub
			actDetail=new ActShowDetail(this,this._detailView,"Thông tin chi tiết",null);
			acImportExcel=new ActExImExcel("Đọc Dl từ Excel",null);
			acImportExcel.setEnabled(false);
		}
		
		protected JMenuItem getMniUpdate() {
			if (mniUpdate == null) {
				mniUpdate = new JMenuItem();
				mniUpdate.setText("Cập nhật vị trí");
//				mniUpdate.setAction(actSaveSpatial);
			}
			return mniUpdate;
		}
		protected JMenuItem getMniDeleteRow() {
			if (mniDeleteRow == null) {
				mniDeleteRow = new JMenuItem();
				mniDeleteRow.setText("Xóa bản tin");
				mniDeleteRow.setAction(actDelete);
			}
			return mniDeleteRow;
		}
			protected JMenuItem getMniDetail() {
			if (mniDetail == null) {
				mniDetail = new JMenuItem();
				mniDetail.setText("Thông tin chi tiết");
//				actDetail.setEnabled(true);
				mniDetail.setAction(actDetail);
//				mniDetail.setEnabled(true);
				
			}
			return mniDetail;
		}
		
			protected JMenuItem getMniDelete() {
			if (mniDelete == null) {
				mniDelete = new JMenuItem();
				mniDelete.setText("Xóa vị trí");
				mniDelete.setAction(actDeleteSpatial);
				
			}
			return mniDelete;
		}
			protected JMenuItem getMniAdd() {
			if (mniAdd == null) {
				mniAdd = new JMenuItem();
				mniAdd.setText("Thêm dữ liệu");
				mniAdd.setAction(actAdd);
				
			}
			return mniAdd;
		}
			protected JMenuItem getMniImport() {
			if (mniImportExcel == null) {
				mniImportExcel = new JMenuItem();
				mniImportExcel.setText("Đọc Dl từ Excel");
				mniImportExcel.setAction(acImportExcel);
				
			}
			return mniImportExcel;
		}
		//endregion

	
	/* (non-Javadoc)
	 * @see javax.swing.JTable#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.database.view.IGTable#SetIsReOderingAllow(java.lang.Boolean)
	 */
	@Override
	public void SetIsReOderingAllow(Boolean arg) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.database.view.IGTable#SetEnable(java.lang.Boolean)
	 */
	@Override
	public void SetEnable(Boolean arg) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.database.view.IGTable#AddMouseListener(java.awt.event.MouseListener)
	 */
	@Override
	public void AddMouseListener(MouseListener listener) {
		// TODO Auto-generated method stub
		this.addMouseListener(listener);
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.database.view.IGTable#RemoveMouseListener(java.awt.event.MouseListener)
	 */
	@Override
	public void RemoveMouseListener(MouseListener listener) {
		// TODO Auto-generated method stub
		this.removeMouseListener(listener);
	}

	@Override
	public void Receive(String key, ResultSet result) {
		// TODO Auto-generated method stub
		
	}
	
	

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.database.dataModel.IDataReceiver#Receive(java.lang.String, int)
	 */
	@Override
	public void Receive(String key, int result) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.database.view.IGTable#OnMouseClick(java.awt.event.MouseEvent)
	 */
	@Override
	public void OnMouseClick(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getButton()==arg0.BUTTON1)
			{
				if(arg0.getClickCount()==2)
				{
					System.out.println(String.format("line 128 MouseEventOnTable,%s", arg0.getSource()));
					actDetail.actionPerformed(null);
				}
			}
	}

	/* (non-Javadoc)
	 * @see gov.vn.hcmgis.database.view.IGTable#SetDetailView(gov.vn.hcmgis.database.view.IObjectDetailView)
	 */
	@Override
	public void SetDetailView(IObjectDetailView detailView) {
		// TODO Auto-generated method stub
		this._detailView=detailView;
		actDetail.SetView(this._detailView);
	}
	
	public void SetTableStructure(TableStructure table)
	{
		_tableStructure=table;
		acImportExcel.SetTableStructure(table);
	}
	
}
