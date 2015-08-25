import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartScreen extends JPanel implements ActionListener {

	Font font;
	JButton StartButton;
	JButton ExitButton;
	SpaceInvadorz sIObject;
	static JLabel HighScore;

	public StartScreen(SpaceInvadorz Object, int highScore) {
		font = new Font("ComicSansMS", Font.PLAIN, 14);
		StartButton = new JButton("Start");
		ExitButton = new JButton("Exit");
		HighScore = new JLabel();
		HighScore.setFont(font);
		sIObject = Object;
		HighScore.setText("High Score: " + highScore);
		add(StartButton);
		add(HighScore);
		add(ExitButton);
		StartButton.addActionListener(this);
		ExitButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == StartButton) {
			SpaceInvadorz.setGameState(SpaceInvadorz.gameScreen);
			sIObject.MainRun();
		}
		if (e.getSource() == ExitButton) {
			System.exit(0);
		}
	}
}
