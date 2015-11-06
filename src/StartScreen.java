import java.applet.AudioClip;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartScreen extends JPanel implements ActionListener {

	Font font;
	Sound Menu;
	String MMusic = new File("Menu.wav").getAbsolutePath();
	boolean firstStart = true;
	JButton StartButton;
	JButton ExitButton;
	JButton HelpButton;
	SpaceInvadorz sIObject;
	static JLabel HighScore;

	public StartScreen(SpaceInvadorz Object, int highScore) {
		Menu = new Sound(MMusic);
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
		Menu.loop();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == StartButton) {
			if (firstStart == true) {
				JOptionPane.showMessageDialog(null,
						"Controls: A and D or Left and Right Arrow Keys to move Left and Right");
				JOptionPane.showMessageDialog(null,
						"Primary Objective: Dodge the Boulders as they fall from the Stratosphere");
				JOptionPane.showMessageDialog(null,
						"Mechanics: Points are Scored by Dodging both Boulders per Interval");
				JOptionPane.showMessageDialog(null, "Note: High Score Resets when Game is Refreshed(Closed/Reopened)");
				firstStart = false;
			}
			SpaceInvadorz.setGameState(SpaceInvadorz.gameScreen);
			sIObject.MainRun();
		}
		if (e.getSource() == HelpButton) {
			JOptionPane.showMessageDialog(null, "Controls: A and D to move Left and Right");
			JOptionPane.showMessageDialog(null,
					"Primary Objective: Dodge the Boulders as they fall from the Stratosphere");
			JOptionPane.showMessageDialog(null, "Mechanics: Points are Scored by Dodging both Boulders per Interval");
			JOptionPane.showMessageDialog(null, "Note: High Score Resets when Game is Refreshed(Closed/Reopened)");
		}
		if (e.getSource() == ExitButton) {
			System.exit(0);
		}

	}

	private void playSound(String fileName, String action) {
		AudioClip sound = JApplet.newAudioClip(getClass().getResource(fileName));
		if (action.equals("play")) {
			sound.loop();
		} else if (action.equals("stop")) {
			sound.stop();
		}
	}

	public class Sound {
		private Clip clip;

		public Sound(String p) {
			// specify the sound to play
			// (assuming the sound can be played by the audio system)
			// from a wave File
			try {

				File file = new File(p);
				if (file.exists()) {
					AudioInputStream sound = AudioSystem.getAudioInputStream(file);
					// load the sound into memory (a Clip)
					clip = AudioSystem.getClip();
					clip.open(sound);
				} else {
					throw new RuntimeException("Sound: file not found: " + p);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException("Sound: Malformed URL: " + e);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
				throw new RuntimeException("Sound: Unsupported Audio File: " + e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Sound: Input/Output Error: " + e);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
				throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
			}

			// play, stop, loop the sound clip
		}

		public void play() {
			clip.setFramePosition(0); // Must always rewind!
			clip.start();
		}

		public void loop() {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}

		public void stop() {
			clip.stop();
		}
	}
}
