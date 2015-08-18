import java.awt.Graphics;

public class EnemyClass extends GameObject {

	private long warmUp;
	private int playerLoggedX;
	GameObject playerObject;
	int startX;
	boolean charge = false;
	static int warmUpDelay = 1500;

	public EnemyClass(int X, int Y, int Sp, int Wdh, int Hgt, GameObject Player) {
		super(X, Y, Sp, Wdh, Hgt);
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
		G.fillRect(X, Y, Wdh, Hgt);

	}
}
