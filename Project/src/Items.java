import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Items class that lists out all the items from the folder path.
 * @author Kieu Trinh
 * @Team Ocelot
 * @Item version 
 */
public class Items {
    /**
     * take the path string of the folder 
     */
    private String path;
    /**
     * Name of this Item
     */
    private String name;
    /**
     * The list string contain all the name of the item in the folder
     */
    private List<String> fileContent;
    /**
     * file the folder path
     */
    private File file;
    
    /**
     * The constructor that take the path string and initialize all the variables
     * @param thepath
     */
    public Items(String thepath, String theName) {
        path = thepath;
        name = theName;
        fileContent = new ArrayList<String>();
        file = new File(thepath);
    }
    
    /**
     * return all the item name in the folder
     * @return list fileContent
     */
    public List<String> getItemList(){
        for(String content : file.list()) {
            fileContent.add(content);
        }
        return fileContent;
    }
    /**
     * return all the file path of the items in folder
     * @return fileContent
     */
    public File[] getItemPath(){
        return file.listFiles();
    }
    /**
     * return the size of the fileContent list
     * @return int
     */
    public int getSize() {
        return fileContent.size();
    }
    
    /**
     * returns the filepath of this Item
     */
    public File getFile() {
        return this.file;
    }
    
    /**
     * returns the name of this Item
     */
    public String getName() {
        return this.name;
    }
    /**
     *Get the element of the list from the index 
     * @param index
     * @return
     */
    public String getElement(int index) {
        return fileContent.get(index);
    }
    /**
     * check if the folder contain the Item
     * @param name
     * @return true or false
     */
    public boolean contain(String name) {
        boolean check = false;
        for (String file : fileContent) {
            if (file.equalsIgnoreCase(name)) {
                check = true;
            }
        }
        return check;
    }
    /**
     * delete the item from the folder
     * @param fileName
     */
    public void deleteFile(String fileName) {
        File dFileName = new File(path + "\\"+fileName);
        if(contain(fileName)) {
            dFileName.delete();
        }
        else {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "This file does not exist in this item's folder.");
            frame.setVisible(true);
        }
    }
    
    /**
     * Download the folder this Item is associated with to the user's Downloads folder
     * @throws IOException 
     */
    public void downloadItem() throws IOException {
        int count = 0;
        JFrame frame = new JFrame();
        String home = System.getProperty("user.home");
        String downloads = home + "\\Downloads\\";
        for(String s : this.fileContent) {
            uploadScreen.copyFile(path + "\\" + s, downloads + s);
            count++;
        }
        String output = count + " files have been sent to your downloads folder";
        JOptionPane.showMessageDialog(frame, output);
    }
}