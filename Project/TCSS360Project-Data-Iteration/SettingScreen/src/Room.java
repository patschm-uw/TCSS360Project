import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

/**
 * The Room class manages a list of Items that it contains
 * @author Patrick Schmeichel
 * @Team Ocelot
 */
public class Room {
    
    /** List of Items in this room */
    private List<Items> myItems;
    
    /** Name of this room */
    private String title;
    
    /** String version of File path of this room */
    private String path;
    
    /** File path of this room */
    private File file;
    
	public Room(String t,String p) {
        this.title = t;
        this.path = p;
        this.file = new File(p);
        myItems = new LinkedList<Items>();
        
    }
    
    /**
     * returns the title of this room
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * returns the file path of this room
     */
    public File getFilePath() {
        return this.file;
    }
    
    /**
     * returns a list of Items in this room
     */
    public List<Items> getItems() {
        return this.myItems;
    }
    
    /**
     * adds a new item to this room
     * @param i
     */
    public void addItem(Items i) {
        myItems.add(i);
    }
    
    /**
     * Returns -1 if item is not in this room's list, otherwise returns the index of the item
     * @param i
     */
    public int getIndex(Items i) {
        int index = -1;
        for (Items x : myItems) {// checks if the item is in the room's linkedlist
            if(x == i) {
                index = this.myItems.indexOf(i);
                break;
            }
        }
        return index;
    }
    
    /**
     * This will delete an Item from the room if it exists in it.
     * If no item's name in the room matches the given String, an error 
     * will pop up.
     * @param i - name of the folder relating to an item (e.g. "Oven")
     */
    public void deleteItem(Items i) {
        if(this.getIndex(i)>=0) {// if item is in the room's list
            JFrame option = new JFrame();
            if(JOptionPane.showConfirmDialog(option, "This will delete all documents in this item's file, do you want to proceed?")
                            == 0) {// if user selects "yes"
                Items item = this.getItems().get(this.getIndex(i));// get item
                List<String> list = item.getItemList();
                for(String s : list) {
                    item.deleteFile(s);
                }
                item.getFile().delete();
            }
        } else {// otherwise display error message
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "This item does not exist in this room.");
            frame.setVisible(true);
        }
    }
    
    /**
     * This method runs the Items download function for each item in the room if the user accepts
     * @throws IOException 
     */
    public void downloadRoom() throws IOException {
        JFrame frame = new JFrame();
        int choice = JOptionPane.showConfirmDialog(frame, "This option may download many files to your PC, do you want to continue?");
        if(choice == 0) {
            for(Items i : this.myItems) {
                i.downloadItem();
            }
        }
    }
	
}
