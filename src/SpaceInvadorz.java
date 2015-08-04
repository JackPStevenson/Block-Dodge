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
	GameObject Test;
	GameObject Enemy1;
	GameObject Enemy2;
	JFrame mainFrame;
	Timer FPS;
	static final int widthF = 500;
	static final int heightF = 500;
	int bkgX = 0;
	int bkgY = 0;
	int bkgX2 = 0;
	int bkgY2 = -500;

	public static void main(String[] args) {
		Toolkit.getDefaultToolkit();
		SpaceInvadorz Start = new SpaceInvadorz();
		Start.MainRun();
	}

	public void MainRun() {
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
		mainFrame = new JFrame("Space Invadorz");
		mainFrame.add(this);
		mainFrame.addKeyListener(this);
		Test = new PlayerClass(225, 350, 5, 50, 50);
		Enemy1 = new EnemyClass(50, 50, 15, 50, 50, Test);
		Enemy2 = new EnemyClass(400, 50, 15, 50, 50, Test);
		mainFrame.setSize(widthF, heightF);
		mainFrame.setVisible(true);
		FPS = new Timer(1000 / 30, this);
		FPS.start();

	}

	private void Update() {
		Test.Update();
		Enemy1.Update();
		Enemy2.Update();
		if (dPressed == true) {
			Test.moveRight();
		}
		if (aPressed == true) {
			Test.moveLeft();
		}
		bkgY = bkgY + 2;
		bkgY2 = bkgY2 + 2;
		if (bkgY >= 500) {
			bkgY = -500;
		}
		if (bkgY2 >= 500) {
			bkgY2 = -500;
		}
	}

	public void paint(Graphics g) {
		g.drawImage(backgroundSky, bkgX, bkgY, null);
		g.drawImage(backgroundSky2, bkgX2, bkgY2, null);
		Test.paint(g);
		Enemy1.paint(g);
		Enemy2.paint(g);
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
