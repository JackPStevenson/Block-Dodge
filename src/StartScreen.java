import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartScreen extends JPanel implements ActionListener {

	Font font;
	JButton StartButton;
	JButton ExitButton;
	JButton HelpButton;
	SpaceInvadorz sIObject;
	static JLabel HighScore;

	public StartScreen(SpaceInvadorz Object, int highScore) {
		font = new Font("ComicSansMS", Font.PLAIN, 14);
		StartButton = new JButton("Start");
		ExitButton = new JButton("Exit");
		HelpButton = new JButton("Help");
		HighScore = new JLabel();
		HighScore.setFont(font);
		sIObject = Object;
		HighScore.setText("High Score: " + highScore);
		add(StartButton);
		add(HelpButton);
		add(HighScore);
		add(ExitButton);
		StartButton.addActionListener(this);
		ExitButton.addActionListener(this);
		HelpButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == StartButton) {
			SpaceInvadorz.setGameState(SpaceInvadorz.gameScreen);
			sIObject.MainRun();
		}
		if (e.getSource() == HelpButton) {
			JOptionPane.showMessageDialog(null, "Controls: A and D to move Left and Right");
			JOptionPane.showMessageDialog(null, "Primary Objective: Dodge the Boulders as they Track you Down!");
			JOptionPane.showMessageDialog(null, "Mechanics: Points are Scored by Dodging both Boulders per Interval");
			JOptionPane.showMessageDialog(null, "Note: High Score Resets when Game is Reset");
		}
		if (e.getSource() == ExitButton) {
			System.exit(0);
		}
		
	}
}
