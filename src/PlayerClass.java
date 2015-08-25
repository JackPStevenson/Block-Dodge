import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlayerClass extends GameObject {

	private static BufferedImage PlayerImage;

	public PlayerClass(int X, int Y, int Sp, int Wdh, int Hgt) {
		super(X, Y, Sp, Wdh, Hgt);
		try {
			PlayerImage = ImageIO.read(this.getClass().getResourceAsStream(
					"Pixel Ship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Update() {
		if (X > SpaceInvadorz.widthF - 130) {
			X = SpaceInvadorz.widthF - 130;
		}
		if (X < 80) {
			X = 80;
		}
	}

	public void moveRight() {
		X = X + Sp;
	}

	public void moveLeft() {
		X = X - Sp;
	}

	public void paint(Graphics G) {
		G.drawImage(PlayerImage, X, Y, Wdh, Hgt, null);

	}
}
