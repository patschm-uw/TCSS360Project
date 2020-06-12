import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * this class will be our setting screen in which 
 * importing and exporting setting 
 * @author Hamza Shanle
 */
public class SettingScreen extends JFrame implements ActionListener {
   /**
	 * serialVerison
	 */
	private static final long serialVersionUID = 1L;
	// window variables.
	private JPanel panel1;
    private JPanel panel2;
    //Jlabel for the name and email info.
    private JLabel name_label, email_label;
    // the textfields to get name and email.
    private JTextField name_text;
    private JTextField email_text;
    //Buttons to either import or export settings.
    private JButton recieve;
    private JButton export;
    
    
   SettingScreen() {
      // Name Label
      name_label = new JLabel();
      name_label.setText("Name :");
      name_text = new JTextField(10);
      // email Label
      email_label = new JLabel();
      email_label.setText("Email:");
      email_text = new JTextField(10);
      // Buttons
      export = new JButton("EXPORT");
      recieve = new JButton("IMPORT");
      panel1 = new JPanel();
      panel2 = new JPanel();
      
      panel1.add(name_label);
      panel1.add(name_text);
      panel1.add(email_label);
      panel1.add(email_text);
      panel2.add(export);
      panel2.add(recieve);
      recieve.addActionListener(this);
      
      
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      add(panel1, BorderLayout.CENTER);
      add(panel2, BorderLayout.AFTER_LAST_LINE);
      setTitle("Setting Screen");
      setSize(400,200);
      setVisible(true);
   }
	  @Override
	  public void actionPerformed(ActionEvent e) {
		  if (e.getSource() == recieve) {
			  new ImportScreen();
		  }
		  if (e.getSource() == export) {
			  new ExportScreen();
		  }
	  }
}