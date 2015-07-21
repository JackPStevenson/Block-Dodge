import java.awt.Graphics;

public class GameObject {
	private int X;
	private int Y;
	private int Sp;
	private int Wdh;
	private int Hgt;

	public GameObject(int X, int Y, int Sp, int Wdh, int Hgt) {
		this.X = X;
		this.Y = Y;
		this.Sp = Sp;
		this.Wdh = Wdh;
		this.Hgt = Hgt;
	}

	public void Update() {
		if (X > SpaceInvadorz.WidthF - 100) {
			X = SpaceInvadorz.WidthF - 100;
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
