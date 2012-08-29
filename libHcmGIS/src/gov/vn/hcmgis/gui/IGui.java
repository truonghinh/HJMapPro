package gov.vn.hcmgis.gui;

public interface IGui {
	public void Show();
	public void ShowDialog();
	public void Close();
	public void Hide();
	public void SetWindowSize(int width,int height);
	public void SetWindowHeight(int height);
	public void SetWindowWidth(int width);
	public int GetHeight();
	public int GetWidth();
	public void SetTitle(String title);
	public void SetWindowType(int type);
}
