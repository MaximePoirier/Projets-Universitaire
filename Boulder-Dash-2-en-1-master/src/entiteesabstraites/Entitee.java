package entiteesabstraites;

import entitees.Amibe;
import entitees.Mur;
import entitees.MurEnTitane;
import entitees.MurMagique;
import entitees.Poussiere;
import entitees.Sortie;
import entitees.Vide;
import entitees.tickables.Diamant;
import entitees.tickables.Pierre;
import entitees.tickables.Rockford;
import entitees.tickables.ennemis.Libellule;
import entitees.tickables.ennemis.Luciole;
import main.Coeur;
import main.Partie;

public abstract class Entitee implements Cloneable {

	private int x, y;
	private boolean destructible;
	private Partie partieActuelle = Coeur.partieActuelle;

	protected Entitee(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void mourir() {
		if (destructible) {
			getMap()[this.x][this.y] = new Vide(this.x, this.y);
		}
	}

	public void initialiser() {
		partieActuelle = Coeur.partieActuelle;
	}

	public Entitee clone() {
		if (getClass().equals(Mur.class)) {
			return new Mur(x, y);
		} else if (getClass().equals(Diamant.class)) {
			return new Diamant(x, y);
		} else if (getClass().equals(Amibe.class)) {
			return new Amibe(x, y);
		} else if (getClass().equals(Luciole.class)) {
			return new Luciole(x, y);
		} else if (getClass().equals(Libellule.class)) {
			return new Libellule(x, y);
		} else if (getClass().equals(MurEnTitane.class)) {
			return new MurEnTitane(x, y);
		} else if (getClass().equals(Pierre.class)) {
			return new Pierre(x, y);
		} else if (getClass().equals(Poussiere.class)) {
			return new Poussiere(x, y);
		} else if (getClass().equals(Rockford.class)) {
			return new Rockford(x, y);
		} else if (getClass().equals(Sortie.class)) {
			return new Sortie(x, y);
		} else if (getClass().equals(MurMagique.class)) {
			return new MurMagique(x, y, ((MurMagique) this).getMagicWallTime());
		} else {
			return new Vide(x, y);
		}
	}

	public boolean isDestrutible() {
		return destructible;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isDestructible() {
		return destructible;
	}

	public void setDestructible(boolean destructible) {
		this.destructible = destructible;
	}

	public Entitee[][] getMap() {
		return partieActuelle.getNiveau().getMap();
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Partie getPartie_actuelle() {
		return partieActuelle;
	}

	public Partie getPartieActuelle() {
		return partieActuelle;
	}

}
