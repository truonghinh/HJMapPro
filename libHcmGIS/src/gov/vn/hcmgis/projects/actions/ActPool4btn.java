/**
 * 
 */
package gov.vn.hcmgis.projects.actions;

import java.awt.event.ActionEvent;

/**
 * @author HT
 *
 */
public class ActPool4btn {
	protected ActOnBtnAddObjectDetail _addAction;
	protected ActOnBtnDeleteObjectDetail _delAction;
	protected ActOnBtnSaveObjectDetail _saveAction;
	protected ActOnBtnCancelObjectDetail _cancelAction;
	
	private static ActPool4btn _meClass=null;
	
	/**
	 * @return the _addAction
	 */
	public ActOnBtnAddObjectDetail get_addAction() {
		return _addAction;
	}
	
	
	/**
	 * @param _addAction the _addAction to set
	 */
	public void set_addAction(ActOnBtnAddObjectDetail addAction) {
		this._addAction = addAction;
	}
	/**
	 * @return the _delAction
	 */
	public ActOnBtnDeleteObjectDetail get_delAction() {
		return _delAction;
	}
	/**
	 * @param _delAction the _delAction to set
	 */
	public void set_delAction(ActOnBtnDeleteObjectDetail delAction) {
		this._delAction = delAction;
	}
	/**
	 * @return the _saveAction
	 */
	public ActOnBtnSaveObjectDetail get_saveAction() {
		return _saveAction;
	}
	/**
	 * @param _saveAction the _saveAction to set
	 */
	public void set_saveAction(ActOnBtnSaveObjectDetail saveAction) {
		this._saveAction = saveAction;
	}
	/**
	 * @return the _cancelAction
	 */
	public ActOnBtnCancelObjectDetail get_cancelAction() {
		return _cancelAction;
	}
	/**
	 * @param _cancelAction the _cancelAction to set
	 */
	public void set_cancelAction(ActOnBtnCancelObjectDetail cancelAction) {
		this._cancelAction = cancelAction;
	}
	private ActPool4btn(ActOnBtnAddObjectDetail add,ActOnBtnDeleteObjectDetail del, ActOnBtnSaveObjectDetail save,ActOnBtnCancelObjectDetail cancel)
	{
		_addAction=add;
		_delAction=del;
		_saveAction=save;
		_cancelAction=cancel;
		try
		{
		_saveAction.setEnabled(false);
		_cancelAction.setEnabled(false);
		_delAction.setEnabled(true);
		_addAction.setEnabled(true);
		}
		catch(Exception e){}
	}
	private ActPool4btn()
	{
		this(null,null,null,null);
	}
	
	public static ActPool4btn CallMe()
	{
		if(_meClass==null)
		{
			_meClass=new ActPool4btn();
		}
		return _meClass;
	}
	
	public static ActPool4btn CallMe(ActOnBtnAddObjectDetail add,ActOnBtnDeleteObjectDetail del, ActOnBtnSaveObjectDetail save,ActOnBtnCancelObjectDetail cancel)
	{
		if(_meClass==null)
		{
			_meClass=new ActPool4btn(add,del,save,cancel);
		}
		return _meClass;
	}
	
	public void AddActionPerformed(ActionEvent e)
	{
		_saveAction.setEnabled(true);
		_cancelAction.setEnabled(true);
		_delAction.setEnabled(false);
		_addAction.setEnabled(false);
	}
	
	public void CancelActionPerformed(ActionEvent e)
	{
		_saveAction.setEnabled(false);
		_cancelAction.setEnabled(false);
		_delAction.setEnabled(true);
		_addAction.setEnabled(true);
	}
	
	public void SaveActionPerformed(ActionEvent e)
	{
		_saveAction.setEnabled(false);
		_cancelAction.setEnabled(false);
		_delAction.setEnabled(true);
		_addAction.setEnabled(true);
	}
	
	public void DeleteActionPerformed(ActionEvent e)
	{
		_saveAction.setEnabled(false);
		_cancelAction.setEnabled(false);
		_delAction.setEnabled(true);
		_addAction.setEnabled(true);
	}
	
	public void Modified()
	{
		_saveAction.setEnabled(true);
		_cancelAction.setEnabled(true);
		_delAction.setEnabled(false);
		_addAction.setEnabled(false);
	}
	
}
