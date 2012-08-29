/**
 * 
 */
package gov.vn.hcmgis.projects.gui;

import javax.swing.JOptionPane;

import com.iver.cit.gvsig.fmap.MapControl;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.ReadableVectorial;

/**
 * @author HT
 *
 */
public class FeatureSelectionSet {
	public static void ClearSelection(MapControl mapControl, String TableName)
	{
			try {
				FLayer[] visibles = mapControl.getMapContext().getLayers().getVisibles();
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
}
