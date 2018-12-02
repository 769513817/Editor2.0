package Editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MyTabPane extends JTabbedPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String defaultTitle = "untitle";
	
	private MyTextArea textarea;
	
	MyTabPane(){
		this.addTextArea();
		this.addTextArea();
	}
	
	public void addTextArea() {		
		this.addTab(null, null, new MyTextArea(this), null);
		
		this.setTabComponentAt(this.getTabCount() - 1, new MyLabel(MyTabPane.defaultTitle, this));
	}
	
	public void addTextArea(File file) throws IOException {
		String str;
		MyTextArea textarea = new MyTextArea(this);
		BufferedReader input =
				new BufferedReader(
						new InputStreamReader(
								new FileInputStream(file)));
		while ((str = input.readLine()) != null)
			textarea.append(str + "\n");
		this.addTab(file.getName(), null, textarea, file.getPath());
	}
}

class MyLabel extends JLabel{
	MyLabel(String title, MyTabPane tabPane){
		super(title);
		Dimension d = this.getPreferredSize();
		d.setSize(d.getWidth() + 32, d.getHeight());
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

	private ImageIcon closeIcon;
	private ImageIcon _closeIcon;
	private ImageIcon NoSelectionIcon;
	
	public void setIconClose() {
		this.setIcon(this.closeIcon);
	}
	
	public void setIcon_Close() {
		this.setIcon(this._closeIcon);
	}
	
	public void setIconNoSelection() {
		this.setIcon(this.NoSelectionIcon);
	}
	
	CloseButton(Dimension d, MyTabPane tabPane){
	
		this.tabPane         =     tabPane;
		this.closeIcon       = new ImageIcon("img\\close.png");
		this._closeIcon      = new ImageIcon("img\\_close.png");
		this.NoSelectionIcon = new ImageIcon("img\\NoSelection.png");
		
		this.setBounds((int)d.getWidth() - 16, (int)d.getHeight() - 16, 16, 16);  // 设置按钮的位置和大小
		//this.setPreferredSize(new Dimension(40, 10));
		this.setBorderPainted(false); // 不绘制边框
		this.setContentAreaFilled(false); // 取消填充
		this.setFocusPainted(false); // 取消获得焦点后绘制边框
		this.setToolTipText("Close"); // 设置tip
		this.setIconClose();
	
		this.addMouseListener(new IsClickedButton());
	}
	
	class IsClickedButton extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			int index = CloseButton.this.tabPane.getSelectedIndex();
			CloseButton.this.tabPane.remove(index);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			CloseButton.this.setIcon_Close(); 
		}
		@Override
		public void mouseExited(MouseEvent e) {
			CloseButton.this.setIconClose(); 
		}
	}
}
