package entitees;

import entiteesabstraites.Entitee;

public class MurMagique extends Entitee {

	private int magicWallTime;

	public MurMagique(int x, int y, int magicWallTime) {
		super(x, y);
		this.magicWallTime = magicWallTime;
		setDestructible(true);
	}

	public int getMagicWallTime() {
		return magicWallTime;
	}

	public void decrementerMagicWallTime() {
		magicWallTime--;
		if (magicWallTime <= 0) {
			mourir();
			getMap()[getX()][getY()] = new Mur(getX(), getY());
		}
	}
}
