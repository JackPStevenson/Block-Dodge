import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlayerClass extends GameObject {

	private static BufferedImage PlayerImage;
	int Sp = 5;
	public PlayerClass(int X, int Y, int Wdh, int Hgt) {
		super(X, Y, Wdh, Hgt);
		try {
			PlayerImage = ImageIO.read(this.getClass().getResourceAsStream(
					"Pixel Ship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Update() {
		if (X > SpaceInvadorz.widthF - 70) {
			X = SpaceInvadorz.widthF - 70;
		}
		if (X < 20) {
			X = 20;
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
