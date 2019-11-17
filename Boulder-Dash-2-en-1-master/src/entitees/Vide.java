package entitees;

import entiteesabstraites.Entitee;

public class Vide extends Entitee {

	public Vide(int x, int y) {
		super(x, y);
		setDestructible(true);
	}
}
