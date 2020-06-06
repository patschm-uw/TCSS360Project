/**
 * 
 */

/**
 * @author Hamza Shanle
 *
 */

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;

 
public class RoomList extends JFrame {
    /**
	 * default serial version
	 */
	private static final long serialVersionUID = 1L;
	private JList<String> RoomList;
    public RoomList() {
        //create the model and add elements
        DefaultListModel<String> list= new DefaultListModel<>();
        list.addElement("Living Room");
        list.addElement("Bathroom");
        list.addElement("Kitchen");
        list.addElement("Dining Room");
        list.addElement("Bedroom");
        list.addElement("Closet");
        list.addElement("Garage");
        list.addElement("Basement");
        list.addElement("Attic");
        
 
        //create the list
        RoomList = new JList<String>(list);
        add(RoomList);
        
      
         
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.setSize(200,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RoomList();
            }
        });
    }       
}
