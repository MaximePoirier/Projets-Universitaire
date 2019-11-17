package entitees.tickables.ennemis;

import entitees.Amibe;
import entiteesabstraites.Ennemi;
import entiteesabstraites.Entitee;

public class Luciole extends Ennemi {

	public Luciole(int x, int y) {
		super(x, y);
		setDestructible(true);
		setDirection('g');
	}


	public void tick() {
		iASetDirection();
		seDeplacer(getDirection());
	}

	protected boolean contactAutreEntitee(Entitee entitee) {
		if (!super.contactAutreEntitee(entitee)) {
			if (entitee.getClass().equals(Amibe.class)) {
				this.exploser(getDirection(), false);
				return false;
			}
		}
		return true;
	}

	protected void iASetDirection() {
		if (isFullLibre()) {
			setDirection('g');
		} else if (isGaucheLibre()) {
			tournerAGauche();
		} else if (isToutDroitLibre()) {

		} else if (isDroiteLibre()) {
			tournerADroite();
		} else if (isDerriereLibre()) {
			tournerADroite();
		}
	}

}
