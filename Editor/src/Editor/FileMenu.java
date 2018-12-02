package Editor;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class FileMenu extends MyMenu{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String [] menuItemName = {"新建", "打开", "保存", "另存为...", "退出"};
	public static final String [] menuItemActionCommand = {"new", "open", "save", "save as", "exit"};
	public static final int [] VK = {KeyEvent.VK_N, KeyEvent.VK_O, KeyEvent.VK_S, KeyEvent.VK_S, KeyEvent.VK_P};
	public static final int menuItemLength = 5;
	
	private JMenuItem [] menuItem;
	
	FileMenu(String title) {
		super(title);
		
		this.menuItem = new JMenuItem[FileMenu.menuItemLength];
		
		for (int i = 0;i < FileMenu.menuItemLength;i++) {
			this.menuItem[i] = new JMenuItem(FileMenu.menuItemName[i]);
			this.menuItem[i].setActionCommand(FileMenu.menuItemActionCommand[i]);
			this.add(menuItem[i]);		
			this.menuItem[i].setAccelerator(KeyStroke.getKeyStroke(FileMenu.VK[i], InputEvent.CTRL_MASK)); 
		}
	}
}
