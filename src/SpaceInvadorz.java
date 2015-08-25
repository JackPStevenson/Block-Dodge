import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceInvadorz extends JPanel implements ActionListener,
		KeyListener {
	URL backgroundURL;
	URL backgroundURL2;
	Image backgroundSky;
	Image backgroundSky2;
	boolean dPressed = false;
	boolean aPressed = false;
	GameObject Player;
	GameObject Enemy1;
	GameObject Enemy2;
	JFrame startFrame;
	JFrame mainFrame;
	Timer FPS;
	Font font;
	static final int widthF = 500;
	static final int heightF = 500;
	int BaESpeed = 20;
	int bkgX = 0;
	int bkgY = 0;
	int bkgX2 = 0;
	int bkgY2 = -500;
	int dSTime;
	static int gameState = 0;
	public static final int startScreen = 0;
	public static final int gameScreen = 1;
	public static final int gOScreen = 2;
	static int score;
	static int highScore;
	static int lives = 2;
	boolean dShield = false;

	public static int getGameState() {
		return gameState;
	}

	public static void setGameState(int a) {
		gameState = a;
	}

	public static void main(String[] args) {
		Toolkit.getDefaultToolkit();
		SpaceInvadorz Start = new SpaceInvadorz();
		Start.MainStart();
	}

	public void MainStart() {
		startFrame = new JFrame("Start Frame");
		StartScreen startingScreen = new StartScreen(this, highScore);
		startFrame.add(startingScreen);
		startFrame.setSize(widthF, heightF);
		startFrame.setVisible(true);

	}

	public void MainRun() {
		mainFrame = new JFrame("Game Frame");
		mainFrame.setSize(widthF, heightF);
		mainFrame.setVisible(true);
		backgroundURL = getClass().getResource(
				"blue-clouds-background-cartoon-style-clouds.png");
		try {
			backgroundSky = ImageIO.read(backgroundURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		backgroundURL2 = getClass().getResource(
				"blue-clouds-background-cartoon-style-clouds.png");
		try {
			backgroundSky2 = ImageIO.read(backgroundURL2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		mainFrame.add(this);
		mainFrame.addKeyListener(this);
		Player = new PlayerClass(225, 350, 5, 50, 50);
		Enemy1 = new EnemyClass(50, 50, BaESpeed, 50, 50, Player);
		Enemy2 = new EnemyClass(400, 50, BaESpeed, 50, 50, Player);
		font = new Font("ComicSansMS", Font.PLAIN, 15);
		FPS = new Timer(1000 / 30, this);
		FPS.start();

	}

	private void Update() {
		StartScreen.HighScore.setText("High Score: " + highScore / 2);
		Player.Update();
		Enemy1.Update();
		Enemy2.Update();
		if (dPressed == true) {
			Player.moveRight();
		}
		if (aPressed == true) {
			Player.moveLeft();
		}
		bkgY = bkgY + BaESpeed / 2;
		bkgY2 = bkgY2 + BaESpeed / 2;
		if (bkgY >= 500) {
			bkgY = -500;
		}
		if (bkgY2 >= 500) {
			bkgY2 = -500;
		}
		if (Player.X > Enemy1.X - 50) {
			if (Player.X < (Enemy1.X + 50)) {
				if (Player.Y > Enemy1.Y) {
					if (Player.Y < (Enemy1.Y + 50)) {
						if (dShield == false) {
							lives--;
						}
						dShield = true;
						if (lives == 0) {
							EnemyClass.warmUpDelay = 1500;
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
		if (Player.X > Enemy2.X - 50) {
			if (Player.X < (Enemy2.X + 50)) {
				if (Player.Y > Enemy2.Y) {
					if (Player.Y < (Enemy2.Y + 50)) {
						if (dShield == false) {
							lives--;
						}
						dShield = true;
						if (lives == 0) {
							EnemyClass.warmUpDelay = 1500;
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
		if (dShield == true) {
			dSTime = dSTime + 1;
			if (dSTime > 15) {
				dShield = false;
				dSTime = 0;
			}
		}
	}

	public void paint(Graphics g) {
		if (gameState == gameScreen) {
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

	public void actionPerformed(ActionEvent e) {
		Update();
		repaint();
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			dPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			aPressed = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			dPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			aPressed = false;
		}
	}
}
