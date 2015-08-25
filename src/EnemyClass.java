import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EnemyClass extends GameObject {

	GameObject playerObject;
	int startX;
	boolean charge = false;
	static int warmUpDelay = 1500;
	private long warmUp;
	private int playerLoggedX;
	private static BufferedImage EnemyImage;

	public EnemyClass(int X, int Y, int Sp, int Wdh, int Hgt, GameObject Player) {
		super(X, Y, Sp, Wdh, Hgt);
		try {
			EnemyImage = ImageIO.read(this.getClass().getResourceAsStream(
					"Boulder.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		startX = X;
		playerObject = Player;
		warmUp = System.currentTimeMillis();
	}

	public void Update() {
		if (System.currentTimeMillis() - warmUp > warmUpDelay) {
			charge = true;
			warmUp = System.currentTimeMillis();
			playerLoggedX = playerObject.getX();
		}
		if (charge) {

			if (X < playerLoggedX) {
				X = X + Sp;
			}
			if (X > playerLoggedX) {
				X = X - Sp;
			}

			Y = Y + Sp;
			if (Y > 375) {
				SpaceInvadorz.score = SpaceInvadorz.score + 1;
				if (SpaceInvadorz.score > SpaceInvadorz.highScore) {
					SpaceInvadorz.highScore = SpaceInvadorz.score;
					if (warmUpDelay > 1000) {
						warmUpDelay = warmUpDelay - (SpaceInvadorz.score);
					}
				}
				Y = 50;
				X = startX;
				charge = false;
			}
		}

	}

	public void paint(Graphics G) {
		G.drawImage(EnemyImage, X, Y, Wdh, Hgt, null);

	}
}
