import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Kezeba Yifat and Patrick Schmeichel
 *
 */
public class About {

    public About() {
		//Creating the Frame
		JFrame frame = new JFrame("About");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 400);
		JPanel panel = new JPanel();  
		JTextArea ta = new JTextArea("This program displays information about the participants \n"
				+ "and their contribution for Requirment Delivarable 1\n\n");
		Font font = new Font("Arial", Font.BOLD, 14);
		ta.setFont(font);
		ta.append("Group Name:  Ocelots\n\n");
		ta.append("Name and Contribution : \n");
		ta.append("Hamza A Shanle: Cover Page, "
				+ "Table of Content, and Summery Page \n\n");
		ta.append("Name and Contribution : \n");
		ta.append("Kieu Trinh: Requirements Definition / Functional Specification \n\n");
		ta.append("Name and Contribution : \n");
		ta.append("Kezeba Yifat: Requirements Specification / Technical Specification\n\n");
		ta.append("Name and Contribution : \n");
		ta.append("Patrick Schmeichel: Story board / Paper Prototypes\n\n");  
		ta.setBackground(Color.GRAY);
		//Adding Components to the frame.
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		frame.getContentPane().add(BorderLayout.CENTER, ta);
		frame.setVisible(true);
		// call version class
		VersionNumber version = new VersionNumber(null);
		version.setVersion("Version1");
		ta.append("Version: "+ version.getVersion());
		
				
		
	}
}