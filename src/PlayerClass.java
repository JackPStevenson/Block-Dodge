import java.awt.Graphics;

public class PlayerClass extends GameObject {

	public PlayerClass(int X, int Y, int Sp, int Wdh, int Hgt) {
		super(X, Y, Sp, Wdh, Hgt);
	}

	public void Update() {
		if (X > SpaceInvadorz.widthF - 100) {
			X = SpaceInvadorz.widthF - 100;
		}
		if (X < 50) {
			X = 50;
		}
	}

	public void moveRight() {
		X = X + Sp;
	}

	public void moveLeft() {
		X = X - Sp;
	}

	public void paint(Graphics G) {
		G.fillRect(X, Y, Wdh, Hgt);

	}
}
