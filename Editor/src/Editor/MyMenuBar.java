package Editor;

import javax.swing.JMenuBar;

public class MyMenuBar extends JMenuBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MyMenu []  menu;
	
	final private static String [] menuName   = {"ÎÄ¼þ", "±à¼­"};
	final private static int       menuLength = 2;
	
	public void addFileMenuListener(FileMenuListener listener) {
		this.menu[0].addListener(listener);
	}
	
	public void addEditMenuListener(EditMenuListener listener) {
		this.menu[1].addListener(listener);
	}
	
	MyMenuBar() {
		
		this.menu    = new MyMenu [MyMenuBar.menuLength];
		
		this.menu[0] = new FileMenu(MyMenuBar.menuName[0]);
		this.menu[1] = new EditMenu(MyMenuBar.menuName[1]);
		
		for (int i = 0;i < MyMenuBar.menuLength;i++)
			this.add(this.menu[i]);
		
	}
}
