import java.awt.BorderLayout;

import javax.swing.*;

/**
 * very basic export screen, no functionality yet
 * @author Patrick Schmeichel
 *
 */
public class ExportScreen extends JFrame
{
    private final int HEIGHT = 150, WIDTH = 300;
    private JPanel ScreenA, ScreenB;
    private JLabel filename, message;
    private JTextField enter_name;
    private JButton ok, cancel;
    
    ExportScreen() {
        //initialize components
        ScreenA = new JPanel();
        ScreenB = new JPanel();
        filename = new JLabel("File name:");
        message = new JLabel("Please enter a name for the file.");
        enter_name = new JTextField(10);
        ok = new JButton("Ok");
        cancel = new JButton("Cancel");
        //set up window
        ScreenA.add(filename);
        ScreenA.add(enter_name);
        ScreenA.add(message);
        ScreenB.add(ok);
        ScreenB.add(cancel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(ScreenA, BorderLayout.CENTER);
        add(ScreenB, BorderLayout.SOUTH);
        setTitle("Export Settings");
        setSize(WIDTH,HEIGHT);
        setVisible(true);
    }
}
