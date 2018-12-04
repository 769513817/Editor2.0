package Editor;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyScrollPane extends JScrollPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MyTextArea textarea;
	
	
	MyScrollPane(MyTextArea textarea){
		super(textarea);
		this.textarea = textarea;
		this.addLineNumberBar( new LineNumberBar(this.textarea.getLineCount()));
		//this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.setBorder(BorderFactory.createEmptyBorder()); // 去除边框
	}	
	
	class LineNumberBar extends MyTextArea{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private int lineCount;
		
		LineNumberBar(int lineCount){
			super(null);
			this.lineCount = lineCount;
			this.setLineWrap(false);
			this.setSelectionColor(this.getBackground()); // 看上去好像没有被选择了一样
			this.setForeground(Color.GRAY);
			this.setEditable(false);
			
			this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			
			String str = "0";
			
			for (int i = 1;i < this.lineCount;i++)
				str += "\n" + String.valueOf(i);
			
			this.setText(str);
			
		}
		
		public void addLine() {
			this.append("\n" + String.valueOf(this.lineCount++));
		}
		
		public void reduceLine() {
			
			this.lineCount--;
			
			String str = "0";
			
			for (int i = 1;i < this.lineCount;i++)
				str += "\n" + String.valueOf(i);
			
			this.setText(str);
		}
		
	}
	
	public void addLineNumberBar(LineNumberBar lineNumberBar) {
		this.textarea.addLineNumberBar(lineNumberBar);
		this.setRowHeaderView(lineNumberBar);
	}
	
	public MyTextArea getMyTextArea() {
		return this.textarea;
	}
	
}
