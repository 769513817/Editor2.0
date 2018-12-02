package Editor;

import javax.swing.JMenu;

public class MyMenu extends JMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyMenuListener listener;
	
	MyMenu(String title){
		super(title);
	}
	
	public void addListener(MyMenuListener listener) {
		this.listener = listener;
		for (int i = 0;i < this.getItemCount();i++) {
			this.getItem(i).addActionListener(this.listener);
		}
	}	
}
