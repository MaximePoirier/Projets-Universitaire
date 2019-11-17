package entitees.tickables;

import entitees.Vide;
import entiteesabstraites.Entitee;
import entiteesabstraites.Tickable;

public class Explosion extends Tickable {

	private int ticksAvantFinExplosion = 3;

	public Explosion(int x, int y) {
		super(x, y);
		setDestructible(true);
	}

	public void tick() {
		if (ticksAvantFinExplosion > 0)
			ticksAvantFinExplosion--;
		else {
			mourir();
			getPartie_actuelle().put(new Vide(getX(), getY()));
		}
	}

	@Override
	protected boolean contactAutreEntitee(Entitee entitee) {

		return false;
	}
}
