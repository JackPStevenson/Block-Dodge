import java.awt.Graphics;

public class PlayerClass extends GameObject {

	public PlayerClass(int X, int Y, int Sp, int Wdh, int Hgt) {
		super(X, Y, Sp, Wdh, Hgt);
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
		G.fillRect(X, Y, Wdh, Hgt);

	}
}
