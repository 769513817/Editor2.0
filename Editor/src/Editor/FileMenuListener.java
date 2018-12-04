package Editor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
					
					File file;
					BufferedWriter output;
					int 	     i = tabPane.getSelectedIndex();
					MyScrollPane s = (MyScrollPane)tabPane.getComponentAt(i);
					MyTextArea 	 t = s.getMyTextArea();
					MyLabel 	 l = (MyLabel)tabPane.getTabComponentAt(i);
					CloseButton  b = (CloseButton)l.getComponent(0);
					
					file = t.getFile();
					
					if (file == null) {
						chooser.setDialogTitle("另存为");
						chooser.setSelectedFile(new File("*.txt"));

						if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(null)) {
							if(chooser.getSelectedFile().exists())
								if(JOptionPane.showConfirmDialog(
										null,
										"是否继续保存？",
										"警告!!! 已存在同名文件",
										JOptionPane.YES_NO_CANCEL_OPTION,
										JOptionPane.WARNING_MESSAGE,
										new ImageIcon("img/Warning.png")) == JOptionPane.YES_OPTION);
								else return;
							file = chooser.getSelectedFile();
							t.setFile(file);
						}else return;
					}
					output = 
						new BufferedWriter(
								new OutputStreamWriter(
										new FileOutputStream(file)));
					output.write(t.getText());
					output.close();
					t.Saved();
					l.setText(file.getName());
					b.setIconClose();
				break;
					
				case "save as" :
					
					File file1;
					BufferedWriter output1;
					int 	     i1 = tabPane.getSelectedIndex();
					MyScrollPane s1 = (MyScrollPane)tabPane.getComponentAt(i1);
					MyTextArea 	 t1 = s1.getMyTextArea();
					MyLabel 	 l1 = (MyLabel)tabPane.getTabComponentAt(i1);
					CloseButton  b1 = (CloseButton)l1.getComponent(0);
					
					chooser.setDialogTitle("另存为");
					chooser.setSelectedFile(new File("*.txt"));
					if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(null)) {
						if(chooser.getSelectedFile().exists())
							if(JOptionPane.showConfirmDialog(
									null,
									"是否继续保存？",
									"警告!!! 已存在同名文件",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.WARNING_MESSAGE,
									new ImageIcon("img/Warning.png")) == JOptionPane.YES_OPTION);
							else return;
					file1 = chooser.getSelectedFile();	

					t1.setFile(file1);					
					output1 = 
						new BufferedWriter(
								new OutputStreamWriter(
										new FileOutputStream(file1)));
					output1.write(t1.getText());
					output1.close();
					t1.Saved();
					l1.setText(file1.getName());
					b1.setIconClose();
					}
				break;
				
				case "exit" :
					System.exit(0);
				break;
				
				}
			}catch(IOException e) {e.printStackTrace();}
		}

	@Override
	public void setMyTabPane(MyTabPane tabPane) {
		this.tabPane = tabPane;
	}
}
