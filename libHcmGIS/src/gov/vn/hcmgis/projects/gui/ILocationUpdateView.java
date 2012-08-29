/**
 * 
 */
package gov.vn.hcmgis.projects.gui;

import javax.swing.JTable;

import com.iver.cit.gvsig.fmap.MapControl;

import gov.vn.hcmgis.database.dataModel.TableStructure;
import gov.vn.hcmgis.database.view.IObjectDetailView;
import gov.vn.hcmgis.gui.IGui;

/**
 * @author HT
 *
 */
public interface ILocationUpdateView extends IGui {
	public void SetTableStructureObject(TableStructure tableStructure);
	public void SetTableStructurePhuong(TableStructure tableStructure);
	public void SetTableStructureDuong(TableStructure tableStructure);
	public void SetTableStructureKtvhxh(TableStructure tableStructure);
	public void SetTableStructureThua(TableStructure tableStructure);
	public TableStructure GetTableStructureThua();
	public TableStructure GetTableStructureObject();
	public TableStructure GetTableStructurePhuong();
	public TableStructure GetTableStructureDuong();
	public TableStructure GetTableStructureKtvhxh();
	public void ReadyLoadAllData();
	public void ReadyLoadPhuong();
	public void ReadyLoadDuong();
	public void ReadyLoadKtvhxh();
	
	public void ReadyUpdatePoint();
	public void ReadySavePoint(int keyVal,double XX, double YY);

	/**
	 * Tìm nhà dọc theo đường nằm trong phường
	 * @param arg0: ma_phuong
	 * @param arg1: ten_duong
	 */
	public void ReadyLoadSoNha(Object maphuong,Object tenduong);
	
	/**
	 * tim nha doc theo duong
	 * @param arg:ten_duong
	 */
	public void ReadyLoadSoNha(Object arg);
	
	public void ReadyLoadGiaoLo(Object maphuong,Object tenduong);
	public void ReadyLoadGiaoLo(Object arg);
	
	/**
	 * Tìm các điểm ktvhxh nằm trong phường
	 * @param arg: thuong la ma_phuong
	 */
	public void ReadyLoadKtvhxhTrongPhuong(Object arg);
	/**
	 * Tìm Các đường giao thông nam trong phuong.
	 * @param arg: thuong la ma_phuong
	 */
	public void ReadyLoadDuongTrongPhuong(Object arg);
	
	public Object GetCbxPhuongSelectedItem();
	public Object GetCbxDuongSelectedItem();
	public Object GetCbxGiaoLoSelectedItem();
	public Object GetCbxKtvhxhSelectedItem();
	
	public void ReadyTimSoNha(Object maphuong,Object tenduong);
	public void ReadyZoomToDuong(Object maphuong,Object tenduong);
	public void ReadyZoomToDuong(Object tenduong);
	
	public void ReadyZoomToGiaoLo(Object maphuong,Object tenduong,Object tengiaolo);
	public void ReadyZoomToGiaoLo(Object tenduong,Object tengiaolo);
	public void ReadyZoomToKtvhxh(Object maphuong,Object tenktvhxh);
	public void ReadyZoomToKtvhxh(Object tenktvhxh);
	public void ReadyZoomToPhuong(Object maphuong);
	
	public void ReadyLoadObject(Object maphuong);
	public void ReadyLoadObject();
	
	public void SetDetailView(IObjectDetailView detailView);
	
	public TableStructure GetObjectTable();
	public String GetExactTableName();
	public JTable GetTable();
	
	public MapControl GetMapControl();
	
	public void RefreshAfterUpdatePoint(int rowUpdated);
}
