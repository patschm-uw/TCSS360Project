/**
 * Show all the rooms in the house
 * @author Kieu Trinh
 * @Team Ocelot
 * @Item version 
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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



public class RoomList extends JPanel{
	private static final long serialVersionUID = 1L;
	private JList<String> list;
    private final DefaultListModel<String> listModel;
    private String homePath;

    private JButton addButton; 
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField roomName;
    private JLabel roomN = new JLabel("Room: ");
    private int index;
    private File roomPath;
    private RoomView room;
    
    public RoomList() {
    	super(new BorderLayout());
    	
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
				//need to call the room class
				String path = (String) System.getProperty("user.dir") + File.separator + "HOME";
				String roomPath = path + File.separator + list.getSelectedValue();
				HomeScreen home = new HomeScreen();
				home.setVisible(false);
				room = new RoomView(roomPath, list.getSelectedValue());
				room.setVisible(true);
				 //JOptionPane.showMessageDialog(null, "This file does exist");
			}
        });
        JScrollPane scrollPane = new JScrollPane(list);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        //buttonPane.add(Box.createHorizontalStrut(5));
        //buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        //buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(roomN);
        buttonPane.add(roomName);
        addButton = add();
        buttonPane.add(addButton);
        deleteButton = delete();
        buttonPane.add(deleteButton);
        searchButton = search();
        buttonPane.add(searchButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(buttonPane, BorderLayout.NORTH);
    }
    
    private JButton add() {
    	addButton = new JButton("Add Room");
        addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File newRoom = new File(homePath + File.separator + roomName.getText());
				if(!listModel.contains(roomName.getText())) {
					newRoom.mkdirs();
					listModel.add(index,roomPath.list()[index]);
				}else {
			         JOptionPane.showMessageDialog(null, "This file does exist");
				}
			}
        });
        return addButton; 
    }
    private JButton delete() {
    	deleteButton = new JButton("Delete Room");
        deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File newRoom = new File(homePath + File.separator + roomName.getText());
				if(listModel.contains(roomName.getText())) {
					newRoom.delete();
					listModel.remove(listModel.indexOf(roomName.getText()));
				}else {
			         JOptionPane.showMessageDialog(null, "This file does exist");
				}
			}
        });
        return deleteButton;
    }
    
    private JButton search() {
    	searchButton = new JButton("Search Room");
        searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listModel.contains(roomName.getText())) {
		            int index = listModel.indexOf(roomName.getText());
		            listModel.set(0, listModel.get(index));
		        } else {
		            
			        JOptionPane.showMessageDialog(null, "File is not found");
			         
		        }
			}
        });
        return searchButton;
    }
    public static void main(String[] args) {
    	JFrame frame = new JFrame("Test");
    	RoomList room = new RoomList();
    	
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setVisible(true);
        frame.add(room);
    }


	
}
