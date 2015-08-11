import javax.swing.JButton;
import javax.swing.JPanel;

public class StartScreen extends JPanel {
	JButton StartButton;
	JButton ExitButton;

	public StartScreen() {
		StartButton = new JButton("Start");
		ExitButton = new JButton("Exit");
		add(StartButton);
		add(ExitButton);
	}
}
