import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Home window that currently allows for uploading files and displaying an About screen
 * @author Hamza Shanle, Patrick Schmeichel, Kieu Trinh 
 *
 */
public class HomeScreen implements ActionListener {
    
    protected JFrame frame;
    private JMenuBar mb;
    private JMenu m1, m2;
    private JMenuItem m11, m22;
    private JPanel panel;
    private JLabel label;
    private JTextField tf;
    private JButton send, about, upload, settings;
    private uploadScreen uScreen;
    private About aScreen;
    protected RoomList rlist;
    private SettingScreen sSetting;
    private Authorization auth;
    private boolean Admin = false;
    
	public HomeScreen() {
		
        //Creating the Frame
        frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setVisible(true);

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
        tf = new JTextField(10); 
        send = new JButton("Enter");
        about = new JButton("About");
        upload = new JButton("Upload");
        settings = new JButton("Setting");
        panel.add(label); 
        panel.add(tf);
        panel.add(send);
        panel.add(about);
        about.addActionListener(this);
        panel.add(upload);
        upload.addActionListener(this);
        panel.add(settings);
        settings.addActionListener(this);
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.setVisible(true);
        auth = new Authorization();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == about) {
            aScreen = new About();
        }
        if (e.getSource() == upload) {
            Admin = auth.isAdmin();// checks if user is an admin
            if(Admin) {// allows upload if true
                uScreen = new uploadScreen();
                uScreen.setVisible(true);
            }
            else {// error if false
                JOptionPane.showMessageDialog(frame, "Only an Admin can use this feature");
            }
        }
        if(e.getSource() == settings) {
        	sSetting = new SettingScreen();
        }
    }
    /**
     * Set the visible state of the frame
     * @author Kieu Trinh 
     */
	public void setVisible(boolean stage) {
		frame.setVisible(stage);
		
	}
}