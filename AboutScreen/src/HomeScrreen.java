
import javax.swing.*;
import java.awt.*;
public class HomeScrreen {
	public static void main(String args[]) {

        //Creating the Frame
        JFrame frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

        JPanel panel = new JPanel(); 
        JLabel label = new JLabel("File");
        JTextField tf = new JTextField(10); 
        JButton send = new JButton("Enter");
        JButton reset = new JButton("About");
        panel.add(label); 
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        
        JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }
}


