/**
 * showing all the file in selected folder/directory
 * @author Kieu Trinh, Hamza Shanle
 * @Team Ocelot
 * @Item version 
 */
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class RoomView extends JFrame
                      implements ActionListener {
	
    /**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	private String path;
    private JButton deleteButton;
    private JButton addButton;
    private JButton download;
    private JButton backButton;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private Dimension screenSize;
    //private File roomPath;
    private Items roomItem;
    private int index;
    
    public RoomView(String path, String name) {
    index = 0;
    this.path = path;
    roomItem = new Items(path, name); 
    //roomPath = new File(path);
	listModel = new DefaultListModel<String>();
    for(String item : roomItem.getItemList()) {
    	listModel.addElement(item);
    	//index++;
    }
    //Create the list and put it in a scroll pane.
    list = new JList<String>(listModel);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setSelectedIndex(0);
    list.addListSelectionListener(new ListSelectionListener() {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			
		}
    	
    });
    list.addMouseListener(new MouseAdapter() {
    	public void mouseClicked(MouseEvent mouseEvent) {
    		if(mouseEvent.getClickCount() == 2) {
    			try {
    				Desktop.getDesktop().open(new File(path + File.separator + list.getSelectedValue()));
    			} catch (IOException e1) {
    				e1.printStackTrace();
    			}
    		}
    	}
    });
    list.setVisibleRowCount(5);
    JScrollPane listScrollPane = new JScrollPane(list);
    
    deleteButton = new JButton("Delete Item");
    deleteButton.addActionListener(this);
    addButton = new JButton("Add Item");
    addButton.addActionListener(this);
    backButton = new JButton("Back to main page");
    backButton.addActionListener(this);
    download = new JButton("Download");
    download.addActionListener(this);
    
    JPanel buttonPane = new JPanel();
   
    
    GridLayout layout = new GridLayout();
    layout.setHgap(25);
    buttonPane.setLayout(layout);
    buttonPane.add(addButton);
    buttonPane.add(deleteButton);
    buttonPane.add(download);
    buttonPane.add(backButton);
    
    screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setSize(screenSize.width, screenSize.height);
    
    add(buttonPane, BorderLayout.PAGE_END);
    add(listScrollPane, BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == addButton) {
        	setVisible(false);
        	new uploadScreen();
        }
        if (e.getSource() == deleteButton) {
        	roomItem.deleteFile(list.getSelectedValue());
        	listModel.remove(listModel.indexOf(list.getSelectedValue()));
        }
        if(e.getSource() == download) {
        	try {
				roomItem.downloadItem();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
        
        if(e.getSource() == backButton) {
        	this.setVisible(false);
        	RoomList home = new RoomList();
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

}
