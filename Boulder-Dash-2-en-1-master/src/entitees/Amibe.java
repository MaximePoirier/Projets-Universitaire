package entitees;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import entitees.tickables.Diamant;
import entitees.tickables.Pierre;
import entiteesabstraites.Entitee;

public class Amibe extends Entitee {

	public Amibe(int x, int y) {
		super(x, y);
		setDestructible(true);
	}

	public boolean sePropager() {
		List<Point> points = new ArrayList<Point>();
		if (placeLibre(getX() + 1, getY())) {
			points.add(new Point(getX() + 1, getY()));
		}
		if (placeLibre(getX() - 1, getY())) {
			points.add(new Point(getX() - 1, getY()));
		}
		if (placeLibre(getX(), getY() - 1)) {
			points.add(new Point(getX(), getY() - 1));
		}
		if (placeLibre(getX(), getY() + 1)) {
			points.add(new Point(getX(), getY() + 1));
		}
		if (!points.isEmpty()) {
			int rng = (int) (Math.random() * points.size());
			getPartie_actuelle().put(new Amibe(points.get(rng).x, points.get(rng).y));
			getPartie_actuelle().getListeAmibes().add(((Amibe) getMap()[points.get(rng).x][points.get(rng).y]));
			checkDetruireAmibes();
			return true;
		} else {
			return false;
		}
	}

	private void checkDetruireAmibes() {
		if (getPartie_actuelle().getListeAmibes().size() >= 200) {
			for (Amibe amibe : getPartie_actuelle().getListeAmibes()) {
				getPartie_actuelle().put(new Pierre(amibe.getX(), amibe.getY()));
			}
			getPartie_actuelle().getListeAmibes().removeAll(getPartie_actuelle().getListeAmibes());
		}
	}

	public void transformerTousLesAmibesEnDiamant() {
		for (Amibe amibe : getPartie_actuelle().getListeAmibes()) {
			getPartie_actuelle().put(new Diamant(amibe.getX(), amibe.getY()));
		}
		getPartie_actuelle().getListeAmibes().removeAll(getPartie_actuelle().getListeAmibes());
	}

	public void mourir() {
		getPartie_actuelle().getListeAmibes().remove(this);
		super.mourir();
	}

	private boolean placeLibre(int x, int y) {
		if (getMap()[x][y].getClass().equals(Vide.class) || getMap()[x][y].getClass().equals(Poussiere.class)) {
			return true;
		}
		return false;
	}
}
