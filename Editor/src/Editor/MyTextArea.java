package Editor;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Editor.MyScrollPane.LineNumberBar;

public class MyTextArea extends JTextArea{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isSaved;
	private File file;
	private CloseButton  closeButton;
	private LineNumberBar lineNumberBar;
	
	
	MyTextArea(File file){
		this.isSaved = true;
		this.file    = file;
		this.lineNumberBar = null;
		
		/////////////////
		//Style

		this.setTabSize(4); // 设置Tab键空格数
		this.setBackground(Color.DARK_GRAY); // 设置背景颜色
		this.setForeground(Color.WHITE); // 设置字符颜色
		this.setCaretColor(Color.WHITE); // 设置插入光标颜色
		this.setFont(new Font("幼圆", Font.PLAIN, 24)); // 设置字体
		this.setSelectionColor(Color.LIGHT_GRAY);
		
		//////////////////
		
		this.setLineWrap(true);
		this.getDocument().addDocumentListener(new DocumentListener() { // 检测文本区是否被改变

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (MyTextArea.this.isSaved) {
					if (MyTextArea.this.closeButton != null) {
						MyTextArea.this.closeButton.setIconNotSaved();
					}MyTextArea.this.isSaved = false;
				} if (MyTextArea.this.lineNumberBar != null) {
					int i = MyTextArea.this.lineNumberBar.getLineCount();
					int n = MyTextArea.this.getLineCount();
				
					if (n > i) {
						for (;i < n;i++)
							MyTextArea.this.lineNumberBar.addLine();
					}else if (n < i) {
						for (;i > n;i--)
							MyTextArea.this.lineNumberBar.reduceLine();
					}
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}
			
		});
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public File getFile() {
		return this.file;
	}
	
	public void setCloseButton(CloseButton b) {
		this.closeButton = b;
	}
	
	public CloseButton getCloseButton() {
		return this.closeButton;
	}
	
	public boolean isSaved() {
		return this.isSaved;
	}
	
	public void Saved() {
		this.isSaved = true;
	}
	
	public void addLineNumberBar(LineNumberBar lineNumberBar) {
		this.lineNumberBar = lineNumberBar;
	}
	
}
