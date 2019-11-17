package entitees.tickables.ennemis;

import entitees.Amibe;
import entiteesabstraites.Ennemi;
import entiteesabstraites.Entitee;

public class Libellule extends Ennemi {

	public Libellule(int x, int y) {
		super(x, y);
		setDestructible(true);
	}

	public void initialiser() {
		super.initialiser();
		chercherMur();
	}

	public void tick() {
		iASetDirection();
		seDeplacer(getDirection());
	}

	protected boolean contactAutreEntitee(Entitee entitee) {
		if (!super.contactAutreEntitee(entitee)) {
			if (entitee.getClass().equals(Amibe.class)) {
				this.exploser(getDirection(), true);
				return false;
			}
		}
		return true;
	}

	protected void iASetDirection() {
		if (isFullLibre()) {
			setDirection('d');
		} else if (isDroiteLibre()) {
			tournerADroite();
		} else if (isToutDroitLibre()) {

		} else if (isGaucheLibre()) {
			tournerAGauche();
		} else if (isDerriereLibre()) {
			tournerAGauche();
		} 
	}

	protected void chercherMur() {
		if (isFullLibre()) {
			setDirection('d');
		} else if (seCollerMurSensAntiHorraire() != ' ') {
			setDirection(seCollerMurSensAntiHorraire());
		}
	}

	protected char seCollerMurSensAntiHorraire() {
		if (!placeLibre(getX(), getY() + 1) && placeLibre(getX() + 1, getY())) {
			return 'd';
		} else if (!placeLibre(getX() + 1, getY()) && placeLibre(getX(), getY() + 1)) {
			return 'h';
		} else if (!placeLibre(getX(), getY() + 1) && placeLibre(getX() - 1, getY())) {
			return 'g';
		} else if (!placeLibre(getX() - 1, getY()) && placeLibre(getX(), getY() + 1)) {
			return 'b';
		}
		return 'd';
	}
}