import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class uploadScreenNV extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static JPanel panelB;
	static JPanel view;
	static JPanel upload;
	JTextArea text;
	JButton uploadF;
	JButton uploadP;
	JButton uploadW;
	JButton uploadB;
	
	
	uploadScreenNV() {
		
		text = new JTextArea("No file select");
		text.setPreferredSize(new Dimension(300,300));
		JScrollPane scrollPane = new JScrollPane(text);
		text.setEditable(false);
		panelB = new JPanel();
		view = new JPanel();
		upload = new JPanel();
	    uploadF = new JButton("File select");
	    uploadF.setSize(36, 36);  
	    uploadP = new JButton("Picture select");
	    uploadP.setSize(36,36);
	    uploadW = new JButton("Website select");
	    uploadW.setSize(36,36);
	    uploadB = new JButton("Upload");
	    uploadB.setSize(36,36);
	   
	  		 
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
	    uploadF.addActionListener(this);
	    uploadB.addActionListener(this);
	    uploadP.addActionListener(this);
	    uploadW.addActionListener(this);
	    add(panelB, BorderLayout.NORTH);
	    add(view, BorderLayout.CENTER);
	    add(upload, BorderLayout.SOUTH);
	  	

	  	
		
	}
	
   
      
   public static void main(String[] args) {
      new uploadScreenNV();
   }
  



@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource() == uploadF) {
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
	if(e.getSource() == uploadW) {
		text.setText("Enter the link and press enter:");
		text.setEditable(true);
		text.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					text.replaceRange(null,0,31);
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});	
	}
	if(e.getSource() == uploadP) {
		JFileChooser file1 = new JFileChooser();
		file1.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
		file1.addChoosableFileFilter(filter);
		int result1 = file1.showOpenDialog(null);
		if(result1 == JFileChooser.APPROVE_OPTION) {
			File selectedFile = file1.getSelectedFile();
			text.setText(selectedFile.getAbsolutePath());	
		}
	}
	if(e.getSource() == uploadB) {
		JFileChooser fs = new JFileChooser(new File("c:\\"));
		fs.setDialogTitle("Save a File");
		int open = fs.showOpenDialog(null);
		File filePath = fs.getSelectedFile();
		
		String nameF = text.getText();
		if(nameF.endsWith(".jpg") || nameF.endsWith(".gif") || nameF.endsWith(".png")) {
			
			if(open == JFileChooser.APPROVE_OPTION) {
				try {
					File initialImage = new File(nameF);
					BufferedImage bImage = ImageIO.read(initialImage);
					if(nameF.endsWith(".jpg") ) {
						ImageIO.write(bImage, "jpg", filePath);
					} else if(nameF.endsWith(".gif")) {
						ImageIO.write(bImage, "gif", filePath);
					} else {
						ImageIO.write(bImage, "png", filePath);
					}
					
				}catch (IOException e1) {
					e1.printStackTrace();
					text.setText("image is not found");
				}	
			}
			
		}else {
			int result1 = fs.showSaveDialog(null);
			File fi = fs.getSelectedFile();
			if(result1 == JFileChooser.APPROVE_OPTION) {
				String content = text.getText();
				
				try {
					FileWriter fw = new FileWriter(fi.getPath());
					fw.write(content);
					fw.flush();
					text.setText("No file is selected");
					fw.close();
				}catch (IOException e2) {
					e2.printStackTrace();
					text.setText("file can not found");
				}
			}
		}
		
		
		
	}
	
}

	


}
