/**
 * 
 */
package gov.vn.hcmgis.util;

import gov.vn.hcmgis.projects.businessManagment.DbStructureQ8;
import gov.vn.hcmgis.projects.gui.FeatureSelectionSet;
import gov.vn.hcmgis.projects.gui.ILocationUpdateView;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.geom.Point2D;

import javax.swing.JTable;

import com.iver.andami.messages.NotificationManager;
import com.iver.cit.gvsig.fmap.MapControl;
import com.iver.cit.gvsig.fmap.ViewPort;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.core.ShapeFactory;
import com.iver.cit.gvsig.fmap.core.v02.FConstant;
import com.iver.cit.gvsig.fmap.core.v02.FSymbol;
import com.iver.cit.gvsig.fmap.layers.GraphicLayer;
import com.iver.cit.gvsig.fmap.rendering.FGraphic;
import com.iver.cit.gvsig.fmap.tools.BehaviorException;
import com.iver.cit.gvsig.fmap.tools.Events.PointEvent;
import com.iver.cit.gvsig.fmap.tools.Listeners.PointListener;

/**
 * @author HT
 *
 */
public class UpdatePointListener implements PointListener {
	private Cursor _cursor = java.awt.Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);  //  @jve:decl-index=0:
	private MapControl _mapControl = null;
//	private GovapInternet dlg = null;
	private ILocationUpdateView _hook;
	private GraphicLayer lyr;
	protected JTable _table;
	
	private static UpdatePointListener _meClass=null;
	
	private UpdatePointListener(ILocationUpdateView hook)
	{
		_hook=hook;
		this._mapControl=hook.GetMapControl();
		this._table=hook.GetTable();
	}
	
	public static UpdatePointListener CallMe(ILocationUpdateView hook)
	{
		if(_meClass==null)
		{
			_meClass=new UpdatePointListener(hook);
		}
		return _meClass;
	}

	/* (non-Javadoc)
	 * @see com.iver.cit.gvsig.fmap.tools.Listeners.ToolListener#getCursor()
	 */
	@Override
	public Cursor getCursor() {
		// TODO Auto-generated method stub
		return this._cursor;
	}

	/* (non-Javadoc)
	 * @see com.iver.cit.gvsig.fmap.tools.Listeners.ToolListener#cancelDrawing()
	 */
	@Override
	public boolean cancelDrawing() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.iver.cit.gvsig.fmap.tools.Listeners.PointListener#point(com.iver.cit.gvsig.fmap.tools.Events.PointEvent)
	 */
	@Override
	public void point(PointEvent event) throws BehaviorException {
		// TODO Auto-generated method stub
		try { 
			_mapControl.getMapContext().getGraphicsLayer().clearAllGraphics();		
			_mapControl.getMapContext().invalidate();
			_mapControl.repaint();					     
			
			ViewPort vp = _mapControl.getViewPort();
			Point2D pReal = vp.toMapPoint(event.getPoint());
			Color theColor = Color.red;
			lyr= _mapControl.getMapContext().getGraphicsLayer();
			  
	    	FSymbol theSymbol = new FSymbol(FConstant.SYMBOL_TYPE_POINT);
	    	theSymbol.setColor(Color.RED);
	        int idSymbol = lyr.addSymbol(theSymbol);	        
	        IGeometry geom = ShapeFactory.createPoint2D(pReal.getX(),pReal.getY());
	        FGraphic theGraphic = new FGraphic(geom, idSymbol);
	      
	        lyr.addGraphic(theGraphic);
	        _mapControl.drawGraphics();	
	        Object[] option = {"Có", "Không"};
			int viewRow = this._table.getSelectedRow();
			int rowupdated = this._table.convertRowIndexToModel(viewRow);
//			ClearSelection("thua");
			FeatureSelectionSet.ClearSelection(_mapControl, DbStructureQ8.RanhThua);
			this._table.setEnabled(true);
//			TblInternet.setEnabled(true);
	    	//response = JOptionPane.showConfirmDialog(null,"Bạn có thực sự muốn xóa dữ liệu","Thông báo",JOptionPane.YES_NO_OPTION);
			int response = javax.swing.JOptionPane.showOptionDialog(null, "Bạn có thực sự muốn cập nhật vị trí mới ?",
		                "Thông báo", javax.swing.JOptionPane.YES_NO_OPTION,
		                javax.swing.JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
	    	if (response == 0) 
	    	  {
	    		Integer ma_gas = Integer.parseInt((String) this._table.getValueAt(this._table.getSelectedRow(),1) );
	    		_hook.ReadySavePoint(ma_gas, pReal.getX(),pReal.getY());
	    		this._table.setAutoCreateRowSorter(false);
	    		this._table.setRowSorter(null);
	    	  }
	    	else 
	    		{
	    			_mapControl.setTool("pan");
		    	}
	    	//refresh
	    	_hook.RefreshAfterUpdatePoint(rowupdated);
//    		if (CboPhuongInternet.getItemCount()>0)
//				if (!CboPhuongInternet.getSelectedItem().equals("Tất cả"))
//					Refresh(Integer.parseInt(CboPhuongInternet.getSelectedItem().toString()));
//				else
//					Refresh();
//    		acSaveSpatial.setEnabled(false);
	       		
			
			} 
		 catch (Exception e) {
			e.printStackTrace();
			NotificationManager.addError(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.iver.cit.gvsig.fmap.tools.Listeners.PointListener#pointDoubleClick(com.iver.cit.gvsig.fmap.tools.Events.PointEvent)
	 */
	@Override
	public void pointDoubleClick(PointEvent event) throws BehaviorException {
		// TODO Auto-generated method stub

	}
	
	

}
