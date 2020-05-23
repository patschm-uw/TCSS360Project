import java.awt.*;
import javax.swing.*;
public class SettingScreen extends JFrame {
   /**
	 * serialVerison
	 */
	private static final long serialVersionUID = 1L;
   JPanel panel1;
   JPanel panel2;
   JLabel name_label, email_label, message;
   JTextField name_text;
   JTextField email_text;
   JButton submit;
   JButton recieve;
   JButton export;
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
      submit = new JButton("SUBMIT");
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
      panel2.add(submit);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      add(panel1, BorderLayout.CENTER);
      add(panel2, BorderLayout.AFTER_LAST_LINE);
      setTitle("Setting Screen");
      setSize(400,200);
      setVisible(true);
   }
   public static void main(String[] args) {
      new SettingScreen();
   }
}
