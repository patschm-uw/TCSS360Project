import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Home window that currently allows for uploading files and displaying an About screen
 * @author Hamza Shanle and Patrick Schmeichel
 *
 */
public class HomeScreen implements ActionListener {
    
    private JFrame frame;
    private JMenuBar mb;
    private JMenu m1, m2;
    private JMenuItem m11, m22;
    private JPanel panel;
    private JLabel label;
    private JButton send, about, upload;
    private JTextArea ta;
    private uploadScreen uScreen;
    private About aScreen;
    private RoomList list;
    
	public HomeScreen() {

        //Creating the Frame
        frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        //Creating the MenuBar and adding components
        mb = new JMenuBar();
        m1 = new JMenu("File");
        m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        m11 = new JMenuItem("Open");
        m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

        panel = new JPanel(); 
        label = new JLabel("File"); 
        send = new JButton("Enter");
        about = new JButton("About");
        upload = new JButton("Upload");
        panel.add(label); 
        panel.add(list);
        panel.add(send);
        panel.add(about);
        about.addActionListener(this);
        panel.add(upload);
        upload.addActionListener(this);
        
        
        ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == about) {
            aScreen = new About();
        }
        if (e.getSource() == upload) {
            uScreen = new uploadScreen();
        }
    }
}


