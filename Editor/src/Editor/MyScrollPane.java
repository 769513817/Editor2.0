package Editor;

import javax.swing.JScrollPane;

public class MyScrollPane extends JScrollPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MyScrollPane(MyTextArea textarea){
		super(textarea);

		//this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

}
