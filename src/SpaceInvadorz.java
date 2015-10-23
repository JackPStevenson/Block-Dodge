
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceInvadorz extends JPanel implements ActionListener, KeyListener
{
	URL backgroundURL;
	URL backgroundURL2;
	Image backgroundSky;
	Image backgroundSky2;
	boolean dPressed = false;
	boolean aPressed = false;
	boolean soundP = false;
	GameObject Player;
	GameObject Enemy1;
	GameObject Enemy2;
	JFrame startFrame;
	JFrame mainFrame;
	Timer FPS;
	Font font;
	static final int widthF = 500;
	static final int heightF = 500;
	int musicStage;
	int BaESpeed = 20;
	int bkgX = 0;
	int bkgY = 0;
	int bkgX2 = 0;
	int bkgY2 = -500;
	int dSTime;
	Sound Explo1;
	Sound Explo2;
	Random xRandom;
	Random xRandom2;
	static int gameState = 0;
	public static final int startScreen = 0;
	public static final int gameScreen = 1;
	static int score;
	static int highScore;
	static int lives = 2;
	boolean dShield = false;
	boolean soundFile = false;

	public static int getGameState()
	{
		return gameState;
	}

	public static void setGameState(int a)
	{
		gameState = a;
	}

	public static void main(String[] args)
	{
		Toolkit.getDefaultToolkit();
		SpaceInvadorz Start = new SpaceInvadorz();
		Start.MainStart();
	}

	public void MainStart()
	{
		xRandom = new Random();
		xRandom2 = new Random();
		startFrame = new JFrame("Start Frame");
		try
		{
			startFrame.setContentPane(new JPanel()
			{
				BufferedImage backgroundImage = ImageIO.read(this.getClass().getResourceAsStream("Starting Screen.png"));

				public void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					g.drawImage(backgroundImage, 0, 0, 500, 500, this);
				}
			});
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		StartScreen startingScreen = new StartScreen(this, highScore);
		startFrame.add(startingScreen);
		startFrame.setSize(widthF, heightF);
		startFrame.setVisible(true);
	}

	public void MainRun()
	{
		mainFrame = new JFrame("Game Frame");
		mainFrame.setSize(widthF, heightF);
		mainFrame.setVisible(true);
		backgroundURL = getClass().getResource("blue-clouds-background-cartoon-style-clouds.png");
		try
		{
			backgroundSky = ImageIO.read(backgroundURL);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		backgroundURL2 = getClass().getResource("blue-clouds-background-cartoon-style-clouds.png");
		try
		{
			backgroundSky2 = ImageIO.read(backgroundURL2);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		String E1 = new File("E1.wav").getAbsolutePath();
		String E2 = new File("E2.wav").getAbsolutePath();
		mainFrame.add(this);
		mainFrame.addKeyListener(this);
		Player = new PlayerClass(225, 350, 50, 50);
		Enemy1 = new EnemyClass(50, -50, 50, 50, Player);
		Enemy2 = new EnemyClass(400, -50, 50, 50, Player);
		Explo1 = new Sound(E1);
		Explo2 = new Sound(E2);
		font = new Font("ComicSansMS", Font.PLAIN, 15);
		FPS = new Timer(1000 / 30, this);
		FPS.start();

	}

	private void Update()
	{
		StartScreen.HighScore.setText("High Score: " + highScore / 2);
		Player.Update();
		Enemy1.Update();
		Enemy2.Update();
		if (dPressed == true)
		{
			Player.moveRight();
		}
		if (aPressed == true)
		{
			Player.moveLeft();
		}
		bkgY = bkgY + BaESpeed / 2;
		bkgY2 = bkgY2 + BaESpeed / 2;
		if (bkgY >= 500)
		{
			bkgY = -500;
		}
		if (bkgY2 >= 500)
		{
			bkgY2 = -500;
		}
		if (Player.X > Enemy1.X - 50)
		{
			if (Player.X < (Enemy1.X + 50))
			{
				if (Player.Y > Enemy1.Y)
				{
					if (Player.Y < (Enemy1.Y + 50))
					{

						if (dShield == false)
						{

							soundFile = true;
							if (soundFile = true)
							{
								if (lives > 1)
								{
									Explo1.play();
								} else if (lives == 1)
								{
									Explo2.play();
								}
								soundFile = false;
							}
							lives--;
						}
						dShield = true;
						if (lives == 0)
						{
							score = 0;
							lives = 2;
							Player.X = 225;
							mainFrame.dispose();
							FPS.stop();
						}
					}
				}
			}
		}
		if (Player.X > Enemy2.X - 50)
		{
			if (Player.X < (Enemy2.X + 50))
			{
				if (Player.Y > Enemy2.Y)
				{
					if (Player.Y < (Enemy2.Y + 50))
					{
						if (dShield == false)
						{

							soundFile = true;
							soundFile = true;
							if (soundFile = true)
							{
								if (lives > 1)
								{
									Explo1.play();
								} else if (lives == 1)
								{
									Explo2.play();
								}
								soundFile = false;
							}
							lives--;
						}

						dShield = true;
						if (lives == 0)
						{
							score = 0;
							lives = 2;
							Player.X = 225;
							mainFrame.dispose();
							FPS.stop();
						}
					}
				}
			}
		}
		if (dShield == true)
		{

			dSTime = dSTime + 1;
			if (dSTime > 15)
			{
				dShield = false;
				dSTime = 0;
			}
		}
		if (Enemy1.Y > 450)
		{
			Enemy1.X = xRandom.nextInt(200);
			Enemy1.Y = -50;
		}
		if (Enemy2.Y > 450)
		{
			Enemy2.X = xRandom.nextInt(200) + 250;
			Enemy2.Y = -50;
		}
	}

	public void paint(Graphics g)
	{
		if (gameState == gameScreen)
		{
			g.drawImage(backgroundSky, bkgX, bkgY, null);
			g.drawImage(backgroundSky2, bkgX2, bkgY2, null);
			Player.paint(g);
			Enemy1.paint(g);
			Enemy2.paint(g);
			g.setFont(font);
			g.drawString("Score: " + score / 2, 10, 20);
			g.drawString("High Score: " + highScore / 2, 10, 40);
			g.drawString("Lives: " + lives, 440, 20);
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		Update();
		repaint();
	}

	public void keyTyped(KeyEvent e)
	{

	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_D)
		{
			dPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			aPressed = true;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_D)
		{
			dPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			aPressed = false;
		}
	}

	public class Sound
	{
		private Clip clip;

		public Sound(String p)
		{
			// specify the sound to play
			// (assuming the sound can be played by the audio system)
			// from a wave File
			try
			{

				File file = new File(p);
				if (file.exists())
				{
					AudioInputStream sound = AudioSystem.getAudioInputStream(file);
					// load the sound into memory (a Clip)
					clip = AudioSystem.getClip();
					clip.open(sound);
				} else
				{
					throw new RuntimeException("Sound: file not found: " + p);
				}
			} catch (MalformedURLException e)
			{
				e.printStackTrace();
				throw new RuntimeException("Sound: Malformed URL: " + e);
			} catch (UnsupportedAudioFileException e)
			{
				e.printStackTrace();
				throw new RuntimeException("Sound: Unsupported Audio File: " + e);
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new RuntimeException("Sound: Input/Output Error: " + e);
			} catch (LineUnavailableException e)
			{
				e.printStackTrace();
				throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
			}

			// play, stop, loop the sound clip
		}

		public void play()
		{
			clip.setFramePosition(0); // Must always rewind!
			clip.start();
		}

		public void loop()
		{
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}

		public void stop()
		{
			clip.stop();
		}
	}
	
	
}
