import java.awt.Graphics;

public class GameObject {
	protected int X;
	protected int Y;
	protected int Sp;
	protected int Wdh;
	protected int Hgt;

	public GameObject(int X, int Y, int Wdh, int Hgt) {
		this.X = X;
		this.Y = Y;
		this.Wdh = Wdh;
		this.Hgt = Hgt;
	}

	public void Update() {

	}

	public void moveRight() {

	}

	public void moveLeft() {

	}

	public void paint(Graphics G) {

	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}
}
