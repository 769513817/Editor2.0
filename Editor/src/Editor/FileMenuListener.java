package Editor;

import java.awt.event.ActionEvent;

import java.io.IOException;


import javax.swing.JFileChooser;

public class FileMenuListener implements MyMenuListener{
	
	private MyTabPane tabPane;
	
	FileMenuListener(MyTabPane tabPane){
		this.setMyTabPane(tabPane);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		JFileChooser chooser = new JFileChooser();

		try {
			switch(arg0.getActionCommand()){
				case "new" :
					tabPane.addTextArea();
				break;
			
				case "open" :
					if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this.tabPane))
							tabPane.addTextArea(chooser.getSelectedFile());

				break;
				
				case "save" :
					break;
				
			}
		}catch(IOException e) {e.printStackTrace();}
	}

	@Override
	public void setMyTabPane(MyTabPane tabPane) {
		this.tabPane = tabPane;
	}
}
