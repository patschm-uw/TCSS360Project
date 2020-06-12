import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * Checks to see if the user is an Admin, currently only checks if "Admin" is typed
 * into the text box, which sets the Admin flag to true. The guest option allows the
 * user through yet keeps the flag false.
 * @author Patrick Schmeichel
 * @Team Ocelot
 * @Version final
 */
public class Authorization implements ActionListener
{
    private final int HEIGHT = 150, WIDTH = 300;
    private JFrame frame;
    private JPanel panelA, panelB;
    private JTextArea message;
    private JTextField entry;
    private JButton enter, cancel, guest;
    private uploadScreen uScreen;
    private boolean Admin = false;
    
    public Authorization() {
        frame = new JFrame("Authorization");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        
        panelA = new JPanel();
        message = new JTextArea("Please enter username:");
        entry = new JTextField(10);
        
        panelB = new JPanel();
        enter = new JButton("Enter");
        enter.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        guest = new JButton("Guest");
        guest.addActionListener(this);
        
        panelA.add(message);
        panelA.add(entry);
        panelB.add(enter);
        panelB.add(cancel);
        panelB.add(guest);
        
        frame.getContentPane().add(BorderLayout.NORTH,panelA);
        frame.getContentPane().add(BorderLayout.SOUTH,panelB);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == enter) {
            String text = entry.getText();
            if(text.equalsIgnoreCase("Admin")) {// check and see if "admin" is typed
                Admin = true;
                frame.dispose();
            }
            else {// otherwise gives an error
                JOptionPane.showMessageDialog(frame,"Unknown User!");
            }
        }
        if(e.getSource() == cancel) {
            frame.dispose();
        }
        if(e.getSource() == guest) {
            Admin = false;
            frame.dispose();
        }
    }
    
    /**
     * Returns whether or not the user is an admin or not
     * @return Admin
     */
    public boolean isAdmin() {
        return Admin;
    }
}
