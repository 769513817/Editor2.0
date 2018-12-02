package Editor;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyTextArea extends JTextArea{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FileSaveFlag flag;
	private MyTabPane tabPane;

	MyTextArea(MyTabPane tabPane){
		this.flag = new FileSaveFlag();
		this.tabPane = tabPane;
		this.setFont(new Font("΢���ź�", Font.PLAIN, 30));
		this.setLineWrap(true);
		this.getDocument().addDocumentListener(new DocumentListener() { // ����ı����Ƿ񱻸ı�

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (MyTextArea.this.flag.getFlag()) {
					MyTextArea.this.flag.setFlag(false);
					int index = MyTextArea.this.tabPane.getSelectedIndex();
					String title = MyTextArea.this.tabPane.getTitleAt(index);
					MyTextArea.this.tabPane.setTitleAt(index,title + "*"); // �ı�ͼ��ϱ�־��ʾδ����
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
	
}
