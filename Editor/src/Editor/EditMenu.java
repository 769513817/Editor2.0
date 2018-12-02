package Editor;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class EditMenu extends MyMenu{
	
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public static final String [] menuItemName = 
	{"����", "����", "����", "ճ��", "ɾ��", "����...", "������һ��", "�滻", "ת��", "ȫѡ", "ʱ��//����"};
	public static final String [] menuItemActionCommand = 
	{"undo", "cut", "copy", "paste", "del", "find", "findnext", "repalce", "goto", "all", "time//date"};
	public static final int [] VK = 
	{KeyEvent.VK_Z, KeyEvent.VK_X, KeyEvent.VK_C, KeyEvent.VK_V, KeyEvent.VK_DELETE, KeyEvent.VK_F,
	KeyEvent.VK_F3, KeyEvent.VK_R, KeyEvent.VK_G, KeyEvent.VK_A, KeyEvent.VK_F5};
	public static final int menuItemLength = 11;
	
	private JMenuItem [] menuItem;
	
	EditMenu(String title){
		super(title);
		
		this.menuItem = new JMenuItem [EditMenu.menuItemLength];
		
		for (int i = 0;i < EditMenu.menuItemLength;i++) {
			this.menuItem[i] = new JMenuItem(EditMenu.menuItemName[i]);
			this.menuItem[i].setActionCommand(EditMenu.menuItemActionCommand[i]);
			this.menuItem[i].setEnabled(false);
			this.add(menuItem[i]);
			this.menuItem[i].setAccelerator(KeyStroke.getKeyStroke(EditMenu.VK[i], InputEvent.CTRL_MASK));
		}this.menuItem[10].setEnabled(true);
	}
}
