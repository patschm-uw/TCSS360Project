/**
 * showing all the file in selected folder/directory
 * @author Kieu Trinh, Hamza Shanle
 * @Team Ocelot
 * @Item version 
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;


public class RoomView extends JFrame
                      implements ActionListener {
	
    /**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	private String path;
    private JButton deleteButton;
    private JButton addButton;
    private JButton backButton;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private Dimension screenSize;
    //private File roomPath;
    private Items roomItem;
    
    public RoomView(String path, String name) {
    this.path = path;
    roomItem = new Items(path, name); 
    //roomPath = new File(path);
	listModel = new DefaultListModel<String>();
    for(String item : roomItem.getItemList()) {
    	listModel.addElement(item);
    }
    //Create the list and put it in a scroll pane.
    list = new JList<String>(listModel);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setSelectedIndex(0);
    //list.addListSelectionListener((ListSelectionListener) this);
    list.setVisibleRowCount(5);
    JScrollPane listScrollPane = new JScrollPane(list);
    
    deleteButton = new JButton("Delete Item");
    deleteButton.addActionListener(this);
    addButton = new JButton("Add Item");
    addButton.addActionListener(this);
    //searchButton.addActionListener(this);
    backButton = new JButton("Back to main page");
    backButton.addActionListener(this);
    
    JPanel buttonPane = new JPanel();

    
    GridLayout layout = new GridLayout();
    layout.setHgap(25);

    buttonPane.setLayout(layout);
    buttonPane.add(addButton);
    buttonPane.add(deleteButton);
    buttonPane.add(backButton);
    
    screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setSize(screenSize.width, screenSize.height);
    add(listScrollPane, BorderLayout.CENTER);
    add(buttonPane, BorderLayout.PAGE_END);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == addButton) {
        	File filePath = new File(path);
        	int index = filePath.list().length;
        	new uploadScreen();
        	listModel.add(listModel.getSize(), filePath.list()[index]);
        }
        if (e.getSource() == deleteButton) {
        	roomItem.deleteFile(list.getSelectedValue());
        	listModel.remove(listModel.indexOf(list.getSelectedValue()));
        }
        
        if(e.getSource() == backButton) {
        	this.setVisible(false);
        	HomeScreen home = new HomeScreen();
        	home.setVisible(true);
        }
    }
    
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {

                deleteButton.setEnabled(false);
 
            } else {

                deleteButton.setEnabled(true);
            }
        }
    }
    
    
 
   /* public static void main(String[] args) {
        RoomView room = new RoomView("C:\\Users\\Kelly Trinh\\git\\TCSS360Project\\SettingScreen\\HOME\\TEST","TEST");
    	
    }*/
    

}
