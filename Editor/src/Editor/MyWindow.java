package Editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class MyWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String iconPath = "img/Editor.png";
	private MyMenuBar menuBar;
	private MyTabPane tabPane;
	
	MyWindow(String title, int w, int h){
		super(title);
		this.setSize(w, h);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch (Exception e) {e.printStackTrace();}
		
		this.add(menuBar = new MyMenuBar(), BorderLayout.NORTH);
		this.add(tabPane = new MyTabPane(), BorderLayout.CENTER);
		
		menuBar.addFileMenuListener(new FileMenuListener(tabPane));
		menuBar.addEditMenuListener(new EditMenuListener());
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(MyWindow.iconPath)); // 给程序设置图标 还必须是Image类
		this.addWindowListener(new IsAllFileSavedListener());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	class IsAllFileSavedListener extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			if (!MyWindow.this.tabPane.isAllTextAreaSaved()) {
				if (JOptionPane.showConfirmDialog(
						null,
						"是否继续关闭?",
						"警告!!! 有文件尚未保存",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE,
						new ImageIcon("img/Warning.png")) == JOptionPane.CANCEL_OPTION) {
					MyWindow.this.setDefaultCloseOperation(MyWindow.DO_NOTHING_ON_CLOSE);
					return;
				}
			}MyWindow.this.setDefaultCloseOperation(MyWindow.EXIT_ON_CLOSE);
		}
	}
	
}
