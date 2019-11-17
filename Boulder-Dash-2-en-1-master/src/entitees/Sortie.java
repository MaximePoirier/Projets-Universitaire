package entitees;

import entiteesabstraites.Entitee;
import vue.Sprites;

public class Sortie extends Entitee {

	private boolean cacher;

	public Sortie(int x, int y) {
		super(x, y);
		cacher = true;
	}

	public void seReveler() {
		cacher = false;
		Sprites.devoilerSortie();
	}

	public boolean isCacher() {
		return cacher;
	}

}
