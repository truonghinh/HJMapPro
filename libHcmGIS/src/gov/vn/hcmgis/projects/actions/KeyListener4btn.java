/**
 * 
 */
package gov.vn.hcmgis.projects.actions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.omg.CORBA._PolicyStub;

/**
 * @author HT
 *
 */
public class KeyListener4btn implements KeyListener {
	private ActPool4btn _actPool;
	
	public KeyListener4btn(ActPool4btn pool)
	{
		_actPool=pool;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		_actPool.Modified();
	}

}
