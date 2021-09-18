import javax.swing.JFrame;

// Preliminary UI: 37:46

public class Main {
	public Main() {
		JFrame frame = new JFrame("Favorite Color Finder");
		MainScreen component = new MainScreen(frame);
		frame.addMouseListener(component);
		frame.setSize(775, 600);
		frame.add(component);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
	}
}
