import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceInvadorz extends JPanel implements ActionListener,
		KeyListener {
	boolean dPressed = false;
	boolean aPressed = false;
	GameObject Test;
	JFrame MainFrame;
	Timer FPS;
	static final int WidthF = 500;
	static final int HeightF = 500;

	public static void main(String[] args) {
		SpaceInvadorz Start = new SpaceInvadorz();
		Start.MainRun();
	}

	public void MainRun() {
		MainFrame = new JFrame("Space Invadorz");
		MainFrame.add(this);
		MainFrame.addKeyListener(this);
		Test = new GameObject(225, 350, 5, 50, 50);
		MainFrame.setSize(WidthF, HeightF);
		MainFrame.setVisible(true);
		FPS = new Timer(1000 / 30, this);
		FPS.start();

	}

	private void Update() {
		Test.Update();
		if (dPressed == true) {
			Test.moveRight();
		}
		if (aPressed == true) {
			Test.moveLeft();
		}
	}

	public void paint(Graphics G) {
		Test.paint(G);
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
