import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;



import javax.swing.*;
public class uploadScreen extends JFrame {
	static JLabel label;
	static JPanel panelB;
	static JPanel view;
	static JPanel upload;
	
	uploadScreen() {
		
		JTextArea text = new JTextArea("No file select");
		text.setPreferredSize(new Dimension(300,300));
		JScrollPane scrollPane = new JScrollPane(text);
		text.setEditable(false);
		panelB = new JPanel();
		view = new JPanel();
		upload = new JPanel();
		
	    JButton uploadF = new JButton("File select");
	    uploadF.setSize(36, 36);
	    JButton uploadP = new JButton("Picture select");
	    uploadP.setSize(36,36);
	    JButton uploadW = new JButton("Website select");
	    uploadW.setSize(36,36);
	    JButton uploadB = new JButton("Upload");
	    upload.setSize(36,36);
	  	uploadF.addActionListener(new ActionListener(){
	  		  @Override
	  		  public void actionPerformed(ActionEvent e) {
	  			JFileChooser fileChooser = new JFileChooser();
	  			fileChooser.setDialogTitle("Upload Files");
	  			int result = fileChooser.showOpenDialog(null);
	  			if (result == JFileChooser.APPROVE_OPTION) { 
	                File file = fileChooser.getSelectedFile();
	                try {
	                	
	                	FileReader input = new FileReader(file.getAbsoluteFile());
	                	text.read(input, file.getAbsoluteFile());
	                
	                	
	               
	                } catch (IOException e1) {
						
						e1.printStackTrace();
						text.setText("file is not found");
					}
	  			}
	  		  
	  			}
	  			
	  		
	  	});
	  	panelB.add(uploadF);
	  	panelB.add(uploadW);
	    panelB.add(uploadP);
	    view.add(scrollPane);
	    upload.add(uploadB);
	  	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("Upload Screen");
	    setVisible(true);
	    setSize(400,400);
	    setLayout(new BorderLayout());
	    add(panelB, BorderLayout.NORTH);
	    add(view, BorderLayout.CENTER);
	    add(upload, BorderLayout.SOUTH);
	  	//add(text);
	    setVisible(true);
	  	
		
	}
	
   
      
   public static void main(String[] args) {
      new uploadScreen();
   }

}
