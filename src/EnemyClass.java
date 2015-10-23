import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EnemyClass extends GameObject {

	GameObject playerObject;
	int startX;
	boolean charge = false;
	static int speed = 15;
	private static BufferedImage EnemyImage;

	public EnemyClass(int X, int Y, int Wdh, int Hgt, GameObject Player) {
		super(X, Y, Wdh, Hgt);
		try {
			EnemyImage = ImageIO.read(this.getClass().getResourceAsStream("Boulder.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		startX = X;
		playerObject = Player;
	}

	public void Update() {
		if(SpaceInvadorz.score / 10 < 40){
			Y = Y + speed + (SpaceInvadorz.score / 10);	
		}
		
		if (Y > 450) {
			SpaceInvadorz.score = SpaceInvadorz.score + 1;
			if (SpaceInvadorz.score > SpaceInvadorz.highScore) {
				SpaceInvadorz.highScore = SpaceInvadorz.score;
			}
		}

	}

	public void paint(Graphics G) {
		G.drawImage(EnemyImage, X, Y, Wdh, Hgt, null);

	}
}
