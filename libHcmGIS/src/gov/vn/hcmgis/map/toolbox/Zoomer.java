/**
 * 
 */
package gov.vn.hcmgis.map.toolbox;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JOptionPane;

import com.iver.cit.gvsig.fmap.MapControl;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.core.ShapeFactory;
import com.iver.cit.gvsig.fmap.core.v02.FConstant;
import com.iver.cit.gvsig.fmap.core.v02.FSymbol;
import com.iver.cit.gvsig.fmap.layers.FBitSet;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.GraphicLayer;
import com.iver.cit.gvsig.fmap.layers.ReadableVectorial;
import com.iver.cit.gvsig.fmap.rendering.FGraphic;

/**
 * @author HT
 *
 */
public class Zoomer {
	private static MapControl _mapControl;
	private static Zoomer _meClass=null;
	private Zoomer(MapControl map)
	{
		_mapControl=map;
	}
	
	public static Zoomer CallMe(MapControl map)
	{
		
		if(_meClass==null)
		{
			_meClass=new Zoomer(map);
		}
		else
		{
			_mapControl=map;
		}
		return _meClass;
	}
	
	public void ZoomToExtentWithGivenPoint(Double x, Double y)
	{
		FLayer[] visibles = _mapControl.getMapContext().getLayers().getVisibles();
	    FLayer LayerTableName = null;
	    // Ki` la'm nghen, visible chi co 2 lop nhung cai ham getVisibles tra ve 3 , nen phai sua lai la j = 1. Cai nay de ngam kiu lai sau! 
		 for (int j = 1; j < visibles.length ; j++) {
			//JOptionPane.showMessageDialog(null,visibles[j].getName().toString() + " " + j);
			 if (visibles[j].getName().contains("gt_ln"))
				{	
					LayerTableName = visibles[j];			
				}
		 }
		
		if ((LayerTableName != null) && (x != 0) &&(y!=0))
			 {
   // while (vectorialLayerIt.hasNext());
    //{
        //FLyrVect lyrVect = (FLyrVect) vectorialLayerIt.nextLayer();	        
    	
    	FLyrVect lyrVect = (FLyrVect) LayerTableName ;	            	
        ReadableVectorial rv = lyrVect.getSource();            
        Point2D pReal = new Point2D.Double(x,y);
        Rectangle2D rectangle = null;           
        
        try {
        	           		
        		//mapControl.getViewPort().setExtent(geom.getBounds2D())
        		 try {
						rv.start(); // Dont't forget start and stop
						FBitSet selectiontemp = lyrVect.queryByPoint(pReal,1);			     						
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
						Double minx = rectangle.getMinX()-10;
		            	Double miny = rectangle.getMinY()-10;
		            	Double w = rectangle.getWidth()+ 20;
		            	Double h = rectangle.getHeight() + 20;
		            	rectangle.setFrame(minx, miny, w, h);    
		            	_mapControl.getViewPort().setExtent(rectangle);
		            	//mapControl.getMapContext().invalidate();
		            	//JOptionPane.showMessageDialog(null,rectangle.getCenterX());
		            } 
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}	
	} 
	}
	
	public void ZoomToPointWithGraphic(Double x, Double y)
	{
		if ((x!= 0) && (y!= 0))
		 {
			 Rectangle2D rectangle2 = new Rectangle2D.Double(x-200,y-200,400,400);
			 _mapControl.getViewPort().setExtent(rectangle2);
			 //JOp1tionPane.showMessageDialog(null,"hehe");
			 _mapControl.getMapContext().getGraphicsLayer().clearAllGraphics();
			 //mapControl.getMapContext().invalidate();
		
			 Point2D pReal = new Point2D.Double (x,y);
			 //	JOptionPane.showMessageDialog(null,pReal.getX() + " " + pReal.getY());
			 GraphicLayer lyr= _mapControl.getMapContext().getGraphicsLayer();
			 Color theColor2 = new Color(1, 0, 0, .0f); 
		
			 FSymbol theSymbol2 = new FSymbol(FConstant.SYMBOL_STYLE_FILL_TRANSPARENT);
			 int idSymbol2 = lyr.addSymbol(theSymbol2);
			 IGeometry geom2 = ShapeFactory.createCircle(pReal, 30);
			 FGraphic theGraphic2 = new FGraphic(geom2, idSymbol2);
			 lyr.addGraphic(theGraphic2);			       
			 _mapControl.drawGraphics();
			 _mapControl.getMapContext().invalidate();
		}
	}
}
