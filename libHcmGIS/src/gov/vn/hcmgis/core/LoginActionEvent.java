/**
 * 
 */
package gov.vn.hcmgis.core;

import java.awt.event.ActionEvent;

/**
 * @author hdk
 *
 */
public class LoginActionEvent extends ActionEvent {
	
	private String message="";

	public LoginActionEvent(Object source, int id, String command) {
		super(source, id, command);
		// TODO Auto-generated constructor stub
	}
	public LoginActionEvent(Object source, int id, String command,int modifiers)
	{
		super(source, id, command,modifiers);
	}
	public LoginActionEvent(Object source, int id, String command,long when,int modifiers)
	{
		super(source, id, command,when,modifiers);
	}

	@Override
	public String paramString() {
		// TODO Auto-generated method stub
		return super.paramString();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
