package packidge;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


// Preliminary UI: 37:46
// User Interaction: 118:12
// Autonomous Evolution: 63:55
// Report: 31:21

public class Main {
	
	public int generations = 10;
	
	public Main() {
		JFrame frame = new JFrame("Favorite Color Finder");
		frame.setSize(875, 700);
		MainScreen component = new MainScreen(frame);
		frame.setFocusable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel generationPanel = new JPanel(new GridBagLayout());
		JLabel generationLabel  = new JLabel("Number of Generations");
		JTextField generationInput = new JTextField(3);
		JButton ok = new JButton("Go!");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				component.setGenerations(Integer.parseInt(generationInput.getText()));
				frame.addMouseListener(component);
				frame.add(component);
				generationPanel.setVisible(false);
			}
		});
		
		generationPanel.add(generationLabel);
		generationPanel.add(generationInput);
		generationPanel.add(ok);
		frame.add(generationPanel);
		frame.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
	}
}
