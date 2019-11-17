package ia;

import entitees.Poussiere;
import entitees.Sortie;
import entitees.Vide;
import entitees.tickables.Diamant;
import entitees.tickables.Pierre;
import entiteesabstraites.Entitee;

public class Noeud implements Comparable<Noeud>{
	private Entitee entite;
	private int x, y;
	private int cout;
	private int heuristique;
	private boolean traversable;
		
	public Noeud(Entitee entite, int cout, int heuristique) {
		this.entite = entite;
		this.x = entite.getX();
		this.y = entite.getY();
		this.cout = cout;
		this.heuristique = heuristique;
		if(entite.getClass().equals(Diamant.class) || entite.getClass().equals(Vide.class) || entite.getClass().equals(Pierre.class) || entite.getClass().equals(Sortie.class) || entite.getClass().equals(Poussiere.class)){
			this.traversable = true;
		} else {
			this.traversable = false;
		}
	}

	@Override
	public int compareTo(Noeud n) {
		if (heuristique>n.heuristique){
			return 1;
		} else if (heuristique==n.heuristique){
			return 0;
		} else {
			return -1;
		}
	}

	public Entitee getEntite() {
		return entite;
	}

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}

	public int getHeuristique() {
		return heuristique;
	}

	public void setHeuristique(int heuristique) {
		this.heuristique = heuristique;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isTraversable() {
		return traversable;
	}
	
	
	
}
