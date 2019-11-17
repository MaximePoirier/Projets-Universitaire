package entitees.tickables;

import entitees.Amibe;
import entitees.Poussiere;
import entitees.Sortie;
import entitees.Vide;
import entiteesabstraites.Entitee;
import entiteesabstraites.Tickable;
import main.Coeur;

public class Rockford extends Tickable {

	private char ancienneDirection = 'd';

	public Rockford(int x, int y) {
		super(x, y);
		setDestructible(true);
		setDirection(' ');
		getDeplacementsPossibles().add(Poussiere.class);
		getDeplacementsPossibles().add(Diamant.class);
		getDeplacementsPossibles().add(Pierre.class);
		getDeplacementsPossibles().add(Sortie.class);
		getDeplacementsPossibles().add(Amibe.class);
		getDeplacementsPossibles().add(Explosion.class);
	}

	public void tick() {
		deplacement();
	}

	public char getAncienneDirection() {
		return ancienneDirection;
	}

	public void setAncienneDirection(char ancienneDirection) {
		this.ancienneDirection = ancienneDirection;
	}

	private void deplacement() {
		setDirection(Coeur.controleur.getDirection());
		if (getDirection() != ' ') {
			seDeplacer(getDirection());
		}
	}
	public void mourir(){
		super.mourir();
		getPartie_actuelle().demanderReset();
	}
	@Override
	protected boolean contactAutreEntitee(Entitee entitee) {
		if (entitee.getClass().equals(Pierre.class)) {
			if (entitee.getX() - getX() > 0
					&& getMap()[entitee.getX() + 1][entitee.getY()].getClass().equals(Vide.class)) {
				((entitees.tickables.Pierre) entitee).seDeplacer('d');
				return true;
			} else if (entitee.getX() - getX() < 0
					&& getMap()[entitee.getX() - 1][entitee.getY()].getClass().equals(Vide.class)) {
				((entitees.tickables.Pierre) entitee).seDeplacer('g');
				return true;
			}
			return false;
		} else if (entitee.getClass().equals(Diamant.class)) {
			entitee.mourir();
			getPartie_actuelle().incrementerNbDiamants();
		} else if (entitee.getClass().equals(Sortie.class)) {
			if (!((Sortie) entitee).isCacher()) {
				getPartie_actuelle().setFini(true);
				return true;
			} else {
				return false;
			}
		} else if (entitee.getClass().equals(Explosion.class)) {
			mourir();
			return false;
		} else if (entitee.getClass().equals(Amibe.class)) {
			mourir();
			return false;
		}
		return true;
	}

}
