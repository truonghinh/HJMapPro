/**
 * 
 */
package gov.vn.hcmgis.core.adapters;


import gov.vn.hcmgis.database.view.IGTable;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * @author HT
 *
 */
public class MouseEventOnTable extends MouseAdapter {
	//region fields
	protected JPopupMenu popupMenu;
	protected IGTable _hook;
	//endregion
	public MouseEventOnTable(IGTable hook)
	{
		_hook=hook;
		popupMenu=new JPopupMenu();
//		popupMenu.add(getMniAdd());
//	   	popupMenu.add(getMniDeleteRow());
//	   	popupMenu.addSeparator();
//	   	popupMenu.add(getMniDelete());
//	   	popupMenu.add(getMniUpdate());
//	   	popupMenu.addSeparator();
//	   	popupMenu.add(getMniDetail());
//	   	popupMenu.addSeparator();
//	   	popupMenu.add(getMniImport());
	}
	
	

	
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		_hook.OnMouseClick(arg0);
//		if(arg0.getButton()==arg0.BUTTON1)
//		{
//			if(arg0.getClickCount()==2)
//			{
//				System.out.println(String.format("line 128 MouseEventOnTable,%s", arg0.getSource()));
//				actDetail.actionPerformed(null);
//			}
//		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
//		super.mouseDragged(arg0);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
//		super.mouseEntered(arg0);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
//		super.mouseExited(arg0);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
//		super.mouseMoved(arg0);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getButton()==arg0.BUTTON3)
		{
			
			ShowPopup(arg0);
		}
		else
		{
			System.out.println(String.format("line 182 MouseEventOnTable,%s", arg0.getButton()));
		}
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getButton()==arg0.BUTTON3)
		{
			ShowPopup(arg0);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseWheelMoved(java.awt.event.MouseWheelEvent)
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		super.mouseWheelMoved(arg0);
	}
	
	public void ShowPopup(MouseEvent e)
	{
		if (e.isPopupTrigger()) 
		{
		//chua hien duoc cac item
//		JPopupMenu popupMenu1=new JPopupMenu("popup");
//		popupMenu.add(getMniAdd());
//		popupMenu.add(getMniDeleteRow());
//	   	popupMenu.addSeparator();
//	   	popupMenu.add(getMniDelete());
//	   	popupMenu.add(getMniUpdate());
//	   	popupMenu.addSeparator();
//	   	popupMenu.add(getMniDetail());
//	   	popupMenu.addSeparator();
//	   	popupMenu.add(getMniImport());
		popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}
		else
		{
			System.out.println(String.format("line 213 MouseEventOnTable,%s", e.isPopupTrigger()));
		}
	}
	
	public void Add(JMenuItem item)
	{
		popupMenu.add(item);
	}
	
	public void Add(Component comp)
	{
		popupMenu.add(comp);
	}
	
	public void Add(JPopupMenu popup)
	{
		popupMenu.add(popup);
	}
	
	public void AddSeparator()
	{
		popupMenu.addSeparator();
	}
	
	public void RemoveMenuItem(int index)
	{
		popupMenu.remove(index);
		
	}
	
	public void Remove(Component comp)
	{
		popupMenu.remove(comp);
	}
	
	public void Remove(JPopupMenu popup)
	{
		popupMenu.remove(popup);
	}
	
	public void RemoveAllMenuItem()
	{
		popupMenu.removeAll();
	}
}
