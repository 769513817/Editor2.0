package Editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;


public class MyTabPane extends JTabbedPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String defaultTitle = "Untitle";
	
	MyTabPane(){
		this.addTextArea();
		this.setOpaque(true); // 设置为不透明
		this.setBackground(Color.LIGHT_GRAY);
	}
	
	public void addTextArea() {
		this.addTab(null, null, new MyScrollPane(new MyTextArea(null)), MyTabPane.defaultTitle);
		
		int currentIndex = this.getTabCount() - 1;
				
		this.setTabComponentAt(currentIndex, new MyLabel(MyTabPane.defaultTitle, this));
		//this.setSelectedIndex(currentIndex); // 设置当前选择选项卡为新选项卡
	}
	
	public void addTextArea(File file) throws IOException {
		BufferedReader input =
				new BufferedReader(
						new InputStreamReader(
								new FileInputStream(file)));
		MyTextArea textarea = new MyTextArea(file);
		String str;
		while ((str = input.readLine()) != null)
			textarea.append(str + "\n");
		input.close();
		textarea.Saved();
		this.addTab(null, null, new MyScrollPane(textarea), file.getPath());
		int currentIndex = this.getTabCount() - 1;
		this.setTabComponentAt(currentIndex, new MyLabel(file.getName(), this));
		//this.setSelectedIndex(currentIndex); // 设置当前选择选项卡为新选项卡
	}
	
	public boolean isAllTextAreaSaved() {
		int n = this.getTabCount();
		for (int i = 0;i < n;i++) {
			MyScrollPane s = (MyScrollPane)this.getComponentAt(i);
			MyTextArea   t = s.getMyTextArea();
			if (!t.isSaved())
				return false;
		}
		return true;
	}
	
}

class MyLabel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int offsetX = 32;
	private final int offsetY = 0;
	
	MyLabel(String title, MyTabPane tabPane){
		super(title);
		Dimension d = this.getPreferredSize();
		d.setSize(d.getWidth() + this.offsetX, d.getHeight() + this.offsetY);
		this.setPreferredSize(d);
		this.add(new CloseButton(d, tabPane));
	}
}

class CloseButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MyTabPane tabPane;
	private MyTextArea textarea;

	private ImageIcon closeIcon;
	private ImageIcon _closeIcon;
	private ImageIcon NoSelectionIcon;
	private ImageIcon NotSaved;

	private final int w = 16;
	private final int h = 16;
	
	CloseButton(Dimension d, MyTabPane tabPane){
	
		this.tabPane   =  tabPane;
		int i = this.tabPane.getTabCount() - 1;
		MyScrollPane s = (MyScrollPane)this.tabPane.getComponentAt(i);
		this.textarea = s.getMyTextArea();
		this.textarea.setCloseButton(this);
		
		this.closeIcon       = new ImageIcon("img\\close.png");
		this._closeIcon      = new ImageIcon("img\\_close.png");
		this.NoSelectionIcon = new ImageIcon("img\\NoSelection.png");
		this.NotSaved         = new ImageIcon("img\\NotSaved.png");
		
		this.setBounds((int)d.getWidth() - this.w, (int)d.getHeight() - this.h, this.w, this.h);  // 设置按钮的位置和大小
		//this.setPreferredSize(new Dimension(40, 10));
		this.setBorderPainted(false); 		// 不绘制边框
		this.setContentAreaFilled(false); 	// 取消填充
		this.setFocusPainted(false); 		// 取消获得焦点后绘制边框
		this.setToolTipText("Close"); 		// 设置tip
		this.setIconClose();
	
		this.addMouseListener(new IsClickedButton());
	}
	
	public void setIconClose() {
		this.setIcon(this.closeIcon);
	}
	
	public void setIcon_Close() {
		this.setIcon(this._closeIcon);
	}
	
	public void setIconNoSelection() {
		this.setIcon(this.NoSelectionIcon);
	}
	
	public void setIconNotSaved() {
		this.setIcon(this.NotSaved);
	}
	
	class IsClickedButton extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if (!CloseButton.this.textarea.isSaved())
				if (JOptionPane.showConfirmDialog(
						null,
						"是否继续关闭?",
						"警告!!! 文件尚未保存",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE,
						new ImageIcon("img/Warning.png")) == JOptionPane.CANCEL_OPTION)
					return;
			CloseButton.this.tabPane.remove(CloseButton.this.tabPane.getSelectedIndex());
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
				CloseButton.this.setIcon_Close();
		}
		@Override
		public void mouseExited(MouseEvent e) {
			if (CloseButton.this.textarea.isSaved())
				CloseButton.this.setIconClose();
			else 
				CloseButton.this.setIconNotSaved();
		}
	}
}
