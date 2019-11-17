package entitees;

import entiteesabstraites.Entitee;

public class Mur extends Entitee {

	public Mur(int x, int y) {
		super(x, y);
		setDestructible(true);
	}
}
