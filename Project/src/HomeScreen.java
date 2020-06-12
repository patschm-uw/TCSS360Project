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
    //private JTextArea ta;
    private uploadScreen uScreen;
    private About aScreen;
    protected RoomList rlist;
    private SettingScreen sSetting;
    
	public HomeScreen() {
		
        //Creating the Frame
        frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(400, 400);
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
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == about) {
            aScreen = new About();
        }
        if (e.getSource() == upload) {
            
            frame.setVisible(false);
            uScreen = new uploadScreen();
            uScreen.setVisible(true);
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

	/*public static void main(String [] args) {
		new HomeScreen();
		//create main folder
		String path = "HOME";
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdir();
		}
		RoomList rlist = new RoomList();
		String path = (String) System.getProperty("user.dir") + File.separator + "HOME";
		path = path.replace(File.separator, "\\\\");
		
		String roomPath = path + File.separator + rlist.getList().getSelectedValue();
		System.out.println(path);
		System.out.println(rlist.getList().getSelectedValue());
		
	}
	*/
}


