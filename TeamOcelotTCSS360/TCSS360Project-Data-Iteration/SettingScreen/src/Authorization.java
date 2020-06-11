import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Authorization implements ActionListener
{
    private final int HEIGHT = 150, WIDTH = 300;
    private JFrame frame;
    private JPanel panelA, panelB;
    private JTextArea message;
    private JTextField entry;
    private JButton enter, cancel;
    private uploadScreen upload;
    
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
        
        panelA.add(message);
        panelA.add(entry);
        panelB.add(enter);
        panelB.add(cancel);
        
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
                upload = new uploadScreen();
                frame.dispose();
            }
            else {
                JOptionPane.showMessageDialog(frame,"Unknown User!");
            }
        }
        if(e.getSource() == cancel) {
            frame.dispose();
        }
    }
}
