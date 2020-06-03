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

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Kieu Trinh and Patrick Schmeichel
 * @Team Ocelot
 * @upload screen version
 */

public class uploadScreen extends JFrame {
    
    private static final long serialVersionUID = 1L;
    /**
     * Panel button include file button, picture button and website button
     */
    private JPanel panelB;
    /**
     * panel allow user review the content of the files, contain the JTextArea
     */
    private JPanel view;
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
     * Files that will contain the source location of a file and the destination respectively
     */
    private File selectedFile, file;
    
    /**
     * initialize all the button and panel, setup the GUI frame 
     */
    public uploadScreen() {
        setTitle("Upload Screen");
        setVisible(true);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        review = new JTextArea("No file select");
        panelB = new JPanel();
        view = new JPanel();
        upload = new JPanel();
        uploadF = new JButton("File select");
        uploadP = new JButton("Picture select");
        uploadW = new JButton("Website select");
        uploadB = new JButton("Upload");
        start();
    }
    
    private void start() {
        
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
                    try {
                        
                        FileReader input = new FileReader(selectedFile.getAbsoluteFile());
                        review.read(input, selectedFile.getAbsoluteFile());
                   
                    } catch (IOException e1) {
                        
                        e1.printStackTrace();
                        review.setText("file is not found");
                    }
                }   
                
            }
            
        });
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
                    File Image = fileChooser.getSelectedFile();
                    review.setText(Image.getAbsolutePath()); 
                }
            }
        });
        panelB.add(uploadP);
        
        uploadW.setSize(36,36);
        uploadW.addActionListener(new ActionListener() { // action for websites
            @Override
            public void actionPerformed(ActionEvent e) {
                review.setText("Enter all the links and website below:");
                review.setEditable(true);
            }
        });
        panelB.add(uploadW);
        add(panelB, BorderLayout.NORTH);
        
        review.setPreferredSize(new Dimension(300,300));
        review.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(review); 
        view.add(scrollPane);
        add(view, BorderLayout.CENTER);
        
        
        uploadB.setSize(36,36);
        uploadB.addActionListener(new ActionListener() { // action for uploading
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
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
                            File initialImage = new File(nameF);
                            copyFile(initialImage.getAbsolutePath(),filePath.getAbsolutePath());
                            /*BufferedImage bImage = ImageIO.read(initialImage);
                            if(nameF.endsWith(".jpg") ) {
                                ImageIO.write(bImage, "jpg", filePath);
                            } else if(nameF.endsWith(".gif")) {
                                ImageIO.write(bImage, "gif", filePath);
                            } else {
                                ImageIO.write(bImage, "png", filePath);
                            }*/
                            
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