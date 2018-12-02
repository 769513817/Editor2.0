package Editor;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class MyWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyMenuBar menuBar;
	private MyTabPane tabPane;
	
	MyWindow(String title, int w, int h){
		super(title);
		this.setSize(w, h);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch (Exception e) {e.printStackTrace();}
		
		this.setJMenuBar(menuBar = new MyMenuBar());
		this.add(tabPane = new MyTabPane());
		
		menuBar.addFileMenuListener(new FileMenuListener(tabPane));
		
		setIconImage(Toolkit.getDefaultToolkit().createImage("img\\Editor.png"));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
