import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RoomView extends JPanel
                      implements ListSelectionListener {
	
    /**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel panel;
    private JButton deleteButton;
    private JButton addButton;
    private JList<Items> list;
    private DefaultListModel<Items> listModel;
    private Room room;
    
    public RoomView() {
	
	listModel = new DefaultListModel<Items>();
    
    //Create the list and put it in a scroll pane.
    list = new JList<Items>(listModel);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setSelectedIndex(0);
    list.addListSelectionListener((ListSelectionListener) this);
    list.setVisibleRowCount(5);
    JScrollPane listScrollPane = new JScrollPane(list);
    
    deleteButton = new JButton("Delete Item");
    deleteButton.addActionListener((ActionListener) this);
    addButton = new JButton("Add Item");
    addButton.addActionListener((ActionListener) this);
    
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
    
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == addButton) {
        	new uploadScreen();
        }
        if (e.getSource() == deleteButton) {
        	room.deleteItem(list.getSelectedValue());
          
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
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Room View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        JComponent newContentPane = new RoomView();
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
