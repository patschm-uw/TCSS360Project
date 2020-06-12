import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
/**
 * Window that allows user to import settings from a local file
 * @author Kezeba Yifat
 * @Team Ocelot
 */
public class ImportScreen extends JFrame {
	/** File opener to browse directory. */
	private JFileChooser myFileChooser;
	/**
	 * serialVerison
	 */
	private static final long serialVersionUID = 1L;
	JPanel panelTop;
	JPanel panelBottom;
	JLabel select;
	JTextField directoryText;
	JButton browse;
	JButton Ok;
	ImportScreen() {
		this.myFileChooser = new JFileChooser();
		// Name Label
		select = new JLabel();
		select.setText("Select a File :");
		directoryText = new JTextField(10);
		// Buttons
		browse = new JButton("Browse");
		Ok = new JButton("OK");
		panelTop = new JPanel();
		panelBottom = new JPanel();
		panelTop.add(select);
		panelTop.add(directoryText);
		panelBottom.add(Ok);
		panelTop.add(browse);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(panelTop, BorderLayout.CENTER);
		add(panelBottom, BorderLayout.AFTER_LAST_LINE);
		setTitle("Import Setting");
		setSize(400,200);
		setVisible(true);
		browse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myFileChooser = new JFileChooser();
				myFileChooser.setDialogTitle("Select a File");
				myFileChooser.showSaveDialog(null);				 
				File f = myFileChooser.getSelectedFile();
				String filename = f.getAbsolutePath();
				directoryText.setText(filename);
				int returnValue = myFileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
				}
				else {
				}
			}
		});
		Ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}

		});
	}	


}	