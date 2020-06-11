/**
 * Show all the rooms in the house
 * @author Kieu Trinh and Hamza Shanle
 * @Team Ocelot
 * @Item version 
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class RoomList extends HomeScreen{
	private static final long serialVersionUID = 1L;
	private JPanel roomPanel;
	/**
	 * The list contain all the room in home
	 */
	
	JList<String> list;
	/**
	 * list contain the element/name of the room
	 */
    private final DefaultListModel<String> listModel;
    /**
     * the path of the home folder
     */
    private String homePath;
    /**
     * the pah of the selected item in the list
     */
    private String selectedPath;
    /**
     * add the new room button to the list
     */
    private JButton addButton; 
    /**
     * delete any select room in the list
     */
    private JButton deleteButton;
    /**
     * serach any room button
     */
    private JButton searchButton;
    /**
     * take the name of the room to do the function
     */
    private JTextField roomName;
    /**
     * label the room textfile
     */
    private JLabel roomN = new JLabel("Room: ");
    /**
     * take an index of an list
     */
    private int index;
    /**
     * take the path of the selected room 
     */
    private File roomPath;
    //private RoomView room;
    /**
     * constructor to initialize all the variable file and create the room list.
     */
    public RoomList() {
    	//super(new BorderLayout());
    	roomPanel = new JPanel();
    	roomPanel.setLayout(new BorderLayout());
    	roomPanel.setVisible(true);
    	index = 0;
    	homePath = System.getProperty("user.dir") + File.separator + "HOME";
    	roomPath = new File(homePath);
    	roomName = new JTextField();
        roomName.setEditable(true);
        roomName.setSize(36, 36);
        
    	//Create the list and put it in a scroll pane.
    	listModel = new DefaultListModel<>();
    	list = new JList<>(listModel);
    	for(String s: roomPath.list()) {
    		listModel.add(index, s);
    		index++;
    	}
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedPath = homePath + File.separator + "TEST";
			}
        });
        //open the room by double click the item
        list.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent mouseEvent) {
        		if(mouseEvent.getClickCount() == 2) {
        			frame.setVisible(false);
        			new RoomView(selectedPath, list.getSelectedValue());
        		}
        	}
        });
        JScrollPane scrollPane = new JScrollPane(list);
        roomPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.add(roomN);
        buttonPane.add(roomName);
        addButton = add();
        buttonPane.add(addButton);
        deleteButton = delete();
        buttonPane.add(deleteButton);
        searchButton = search();
        buttonPane.add(searchButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        roomPanel.add(buttonPane, BorderLayout.NORTH);
        //add to the frame from super class
        frame.getContentPane().add(roomPanel, BorderLayout.CENTER);
    }
    
    public JList<String> getList(){
    	return list;
    }
    
    /**
     * create an add button and implement its actionlistener
     * @return addButton
     */
    private JButton add() {
    	addButton = new JButton("Add Room");
        addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File newRoom = new File(homePath + File.separator + roomName.getText());
				if(!listModel.contains(roomName.getText())) {
					newRoom.mkdirs(); // create new folder in the main folder
					listModel.add(index,roomPath.list()[index]);
					roomName.setText("");
				}else {
			         JOptionPane.showMessageDialog(null, "This file does exist");
				}
			}
        });
        
        return addButton; 
    }
    /**
     * create an delete button and implement its actionlistener by delete the folder directory
     * in user local directory and in the list.
     * @return addButton
     */
    private JButton delete() {
    	deleteButton = new JButton("Delete Room");
        deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File newRoom = new File(homePath + File.separator + list.getSelectedValue());
				//roomName.getText());
		//if(listModel.contains(roomName.getText())) {
				if(listModel.contains(list.getSelectedValue())) {
					newRoom.delete();
					listModel.remove(listModel.indexOf(list.getSelectedValue()));
			//roomName.getText()
					}else {
					JOptionPane.showMessageDialog(null, "This file does not exist");
				}
			}
        });
        return deleteButton;
    }
    /**
     * create an search button and implement its actionlistener, loop through all the element
     * in the list to find the search room.
     * @return addButton
     */
    private JButton search() {
    	searchButton = new JButton("Search Room");
        searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listModel.contains(roomName.getText())) {
		            int index = listModel.indexOf(roomName.getText());
		            listModel.set(0, listModel.get(index));//show it in the fist place of the list
		        } else { 
			        JOptionPane.showMessageDialog(null, "File is not found");
		        }
			}
        });
        return searchButton;
    }
    
    public static void main(String[] args) {
    	//JFrame frame = new JFrame("Test");
    	RoomList room = new RoomList();
    	
    	/*frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setVisible(true);
        frame.add(room);*/
    }


	
}
