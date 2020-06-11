import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author Kieu Trinh and Patrick Schmeichel
 * @Team Ocelot
 * @upload screen version
 */

public class uploadScreen extends JFrame{
    
    private static final long serialVersionUID = 1L;
    /**
     * Panel button include file button, picture button and website button
     */
    private JPanel panelB;
    /**
     * Upload panel contain upload button
     */
    private JPanel upload;
    /**
     * review the file or take user input/link
     */
    private JTextArea review;
    /**
     * File select button
     */
    private JButton uploadF;
    /**
     * Picture select button
     */
    private JButton uploadP;
    
    /**
     * Website select button
     */
    private JButton uploadW;
    /**
     * upload button
     */
    private JButton uploadB;
    /**
     * the button allow user to open the file before upload it
     */
    private JButton previewFile;
    /**
     * Allow user go back to previous page
     */
    private JButton Back;
    /**
     * Files that will contain the source location of a file and the destination respectively
     */
    private File selectedFile, file;
    
    /**
     * Set size of the frame fit the screen size
     */
    private Dimension screenSize;
    
    /**
     * initialize all the button and panel, setup the GUI frame 
     */
   
    public uploadScreen() {
    	setTitle("Upload Screen");
        setVisible(true);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width-200, screenSize.height-100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        review = new JTextArea("No file select");
        panelB = new JPanel();
        upload = new JPanel();
        uploadF = new JButton("File select");
        uploadP = new JButton("Picture select");
        uploadW = new JButton("Website select");
        previewFile = new JButton("Open Files");
        uploadB = new JButton("Upload");
        Back = new JButton("Back to Home page");
        start();
    }
    
    /**
     * implementing all the buttons, allows user to select, preview and upload the files from the 
     * main upload frame.
     */
    private void start() {
    	//setup for selecting files buttons and it's functionality
        uploadF.setSize(36, 36); 
        panelB.add(uploadF);
        uploadF.addActionListener(new ActionListener() { // action for file selection
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Upload Files");
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) { 
                    selectedFile = fileChooser.getSelectedFile();
                    review.setText(selectedFile.getAbsolutePath());
                }       
            }
        });
        
      //setup for selecting Images buttons and it's functionality
        uploadP.setSize(36,36);
        uploadP.addActionListener(new ActionListener() { // action for picture selection
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
                fileChooser.addChoosableFileFilter(filter);
                int result1 = fileChooser.showOpenDialog(null);
                if(result1 == JFileChooser.APPROVE_OPTION) {
                	selectedFile = fileChooser.getSelectedFile();
                	review.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        panelB.add(uploadP);
        
        //setup for upload website button
        uploadW.setSize(36,36);
        uploadW.addActionListener(new ActionListener() { // action for websites
            @Override
            public void actionPerformed(ActionEvent e){
                review.setText("Enter all the links and website below:");
                review.setEditable(true);
            }
        });
        panelB.add(uploadW);
        add(panelB, BorderLayout.NORTH);//add panel contain uploadW b button to main frame 
        
        review.setPreferredSize(new Dimension(screenSize.width,screenSize.height-36));
        review.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(review); 
        add(scrollPane, BorderLayout.CENTER);
        
        //setup for selecting preview file buttons and it's functionality
        previewFile.setSize(36,36);
        previewFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String path = review.getText(); 
				if(review.getText().endsWith(".pdf")) {//allow to open pdf files
					try {
						Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + path);
					} catch (IOException e1) {
						e1.printStackTrace();
						review.setText("can not opened PDF files");
					}
				}
				//allow user to open doc files
				if(review.getText().endsWith(".docx") || review.getText().endsWith(".doc")) {
					try {
						Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + path);
					} catch (IOException e1) {
						e1.printStackTrace();
						review.setText("can not opened doc files");
					}
				}
				
				if(review.getText().endsWith(".txt")) {//open text file
					try {
                        FileReader input = new FileReader(selectedFile.getAbsoluteFile());
                        review.read(input, selectedFile.getAbsoluteFile());
                    } catch (IOException e1) {
                        
                        e1.printStackTrace();
                        review.setText("file is not found");
                    }
					
				}
				//open image files
				if(review.getText().endsWith("JPG") || review.getText().endsWith("GIF") ||review.getText().endsWith("PNG")
						|| review.getText().endsWith("jpg") || review.getText().endsWith("gif") ||review.getText().endsWith("png")) {
					 DrawingImages img = new DrawingImages(review.getText());
				     img.setVisible(true);

				    
				}
			}
        });
        upload.add(previewFile);//add button to upload panel
        
        //setup for uploading buttons and it's functionality
        uploadB.setSize(36,36);
        uploadB.addActionListener(new ActionListener() { // action for uploading
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir") + File.separator + "HOME");
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("MS Office Documents", "docx", "xlsx", "pptx"));
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
                fileChooser.setDialogTitle("Save a File");
                int result = fileChooser.showSaveDialog(null);
                File filePath = fileChooser.getSelectedFile();
                
                //Upload the image type
                String nameF = review.getText();
                if(nameF.endsWith(".jpg") || nameF.endsWith(".gif") || nameF.endsWith(".png")) {
                    if(result == JFileChooser.APPROVE_OPTION) {
                        try {
                        	copyFile(selectedFile.getAbsolutePath(),filePath.getAbsolutePath());
                            
                        }catch (IOException e1) {
                            e1.printStackTrace();
                            review.setText("image is not found");
                        }   
                    }
                    
                }else { //upload documents or text files
                    
                    file = fileChooser.getSelectedFile();
                    if(result == JFileChooser.APPROVE_OPTION) {
                        String content = review.getText();
                        try {
                            if(selectedFile != null) { // if a document was selected previously
                                copyFile(selectedFile.getAbsolutePath(),file.getAbsolutePath());
                            }
                            else { // otherwise write a text file
                                FileWriter fw = new FileWriter(file.getPath());
                                fw.write(content);
                                fw.flush();
                                fw.close();
                            }
                            review.setText("No file is selected");
                        }catch (IOException e2) {
                            e2.printStackTrace();
                            review.setText("file can not be found");
                        }
                    }
                } 
            }               
        });
        upload.add(uploadB);
        Back.setSize(36,36);
        Back.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
			    setVisible(false);
				//HomeScreen prev = new HomeScreen(); 
				//prev.setVisible(true);
			}
        
        });
        upload.add(Back);
        add(upload, BorderLayout.SOUTH);
    }
    
    /**  This method accepts two file paths, a source and a destination where the
     *   file in the source path will be copied to the destination.
     * @param from - File path where a file was selected
     * @param to - File path where the selected file will be copied to
     * @throws IOException
     */
    public static void copyFile(String from, String to) throws IOException{
        Path src = Paths.get(from);
        Path dest = Paths.get(to);
        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
    }
  
}
/**
 * the Frame will display the image from it path.
 * @author Kieu Trinh
 * @Team Ocelot 
 */
class DrawingImages extends JFrame{
	private static final long serialVersionUID = 2L;
	/**
	 * taing the string path of the image.
	 */
	private String path;
	/**
	 * setup the frame and display the image on the frame
	 * @param Path
	 */
	public DrawingImages(String Path){
		path = Path;
		
		BufferedImage image = null;
        try
        {
          image = ImageIO.read(new File(path));
        }
        catch (Exception e)
        {
          e.printStackTrace();
          System.exit(1);
        }
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        getContentPane().add(jLabel, BorderLayout.CENTER);
        
        setSize(new Dimension(400,400));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
}