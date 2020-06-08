import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

/**
 * The Room class manages a list of Items that it contains
 * @author Patrick Schmeichel
 * @Team Ocelot
 */
public class Room implements ActionListener{
    
    /** List of Items in this room */
    private List<Items> myItems;
    
    /** Name of this room */
    private static String title;
    
    /** String version of File path of this room */
    private static String path;
    
    /** File path of this room */
    private File file;
    
    private static JPanel panel;
    private JButton deleteButton;
    private JButton addButton;
    private JList<Items> list;
    private DefaultListModel<Items> listModel;
    
    @SuppressWarnings("static-access")
	public Room(String t,String p) {
        this.title = t;
        this.path = p;
        this.file = new File(p);
        myItems = new LinkedList<Items>();
        
        
        listModel = new DefaultListModel<Items>();
        
        //Create the list and put it in a scroll pane.
        list = new JList<Items>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener((ListSelectionListener) this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        
        deleteButton = new JButton("Delete Item");
        deleteButton.addActionListener(this);
        addButton = new JButton("Add Item");
        addButton.addActionListener(this);
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        
        buttonPane.add(deleteButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        
        panel = new JPanel();
        panel.add(listScrollPane, BorderLayout.CENTER);
        panel.add(buttonPane, BorderLayout.PAGE_END);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == addButton) {
        	new uploadScreen();
        }
        if (e.getSource() == deleteButton) {
        	deleteItem(list.getSelectedValue());
          
        }
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
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Room View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        JComponent newContentPane = new Room(title, path);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
	
}
