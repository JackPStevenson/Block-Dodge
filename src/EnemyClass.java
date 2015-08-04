import java.awt.Graphics;

public class EnemyClass extends GameObject {

	private long warmUp;
	private int playerLoggedX;
	GameObject playerObject;
	int startX;
	boolean charge = false;

	public EnemyClass(int X, int Y, int Sp, int Wdh, int Hgt, GameObject Player) {
		super(X, Y, Sp, Wdh, Hgt);
		startX = X;
		playerObject = Player;
		warmUp = System.currentTimeMillis();
	}

	public void Update() {
		if (System.currentTimeMillis() - warmUp > 1500) {
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
				Y = 50;
				X = startX;
				charge = false;
			}
		}

	}

	public void paint(Graphics G) {
		G.fillRect(X, Y, Wdh, Hgt);

	}
}
