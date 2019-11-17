package entitees;

import entiteesabstraites.Entitee;

public class Poussiere extends Entitee {

	public Poussiere(int x, int y) {
		super(x, y);
		setDestructible(true);
	}
}
