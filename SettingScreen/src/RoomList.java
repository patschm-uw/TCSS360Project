/**
 * 
 */

/**
 * @author Hamza Shanle
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
 

public class RoomList extends JPanel
                      implements ListSelectionListener {
    /**
	 * deafult serial version number.
	 */
	private static final long serialVersionUID = 1L;
	private JList<Object> list;
    private DefaultListModel<Object> listModel;
 
    private static final String addString = "Add Room";
    private static final String deleteString = "Delete Room";
    private JButton deleteButton;
    private JTextField roomName;
    private JTextField path;
    private JLabel roomN = new JLabel("Room: ");
    private JLabel pathN = new JLabel("Path: ");
    private Room room;
 
    public RoomList() {
        super(new BorderLayout());
 
        listModel = new DefaultListModel<Object>();
 
        //Create the list and put it in a scroll pane.
        list = new JList<Object>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
 
        JButton addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);
 
        deleteButton = new JButton(deleteString);
        deleteButton.setActionCommand(deleteString);
        deleteButton.addActionListener(new DeleteListener());
 
        roomName = new JTextField(10);
        roomName.addActionListener(addListener);
        roomName.getDocument().addDocumentListener(addListener);
        
        path = new JTextField(10);
        path.addActionListener(addListener);
        path.getDocument().addDocumentListener(addListener);

 
        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        buttonPane.add(deleteButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(roomN);
        buttonPane.add(roomName);
        buttonPane.add(pathN);
        buttonPane.add(path);
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
 
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }
 
    class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            listModel.remove(index);
 
            int size = listModel.getSize();
 
            if (size == 0) { 
                deleteButton.setEnabled(false);
 
            } else { 
                if (index == listModel.getSize()) {
                    index--;
                }
 
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }
 
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;
 
        public AddListener(JButton button) {
            this.button = button;
        }
 
		public void actionPerformed(ActionEvent e) {
            String name = roomName.getText();
 
            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                roomName.requestFocusInWindow();
                roomName.selectAll();
                return;
            }
 
            int index = list.getSelectedIndex(); 
            if (index == -1) { 
                index = 0;
            } else {           
                index++;
            }
            room = new Room(roomName.getText(), path.getText());
 
            listModel.insertElementAt(room.getTitle(), index);
 

            roomName.requestFocusInWindow();
            roomName.setText("");
 

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
 
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }
 
        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }
 
        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }
 
        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }
 
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }
 
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
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
        JFrame frame = new JFrame("RoomList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        JComponent newContentPane = new RoomList();
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
