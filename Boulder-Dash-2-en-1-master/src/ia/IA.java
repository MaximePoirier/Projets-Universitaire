package ia;

import entiteesabstraites.Entitee;
import loader.Niveau;
import main.Coeur;

public abstract class IA {

	protected char direction;
	protected boolean prete=true;
	protected Entitee[][] map;
	protected Niveau niveau;
	public IA() {
	}

	public void initialiser() {
		map = Coeur.partieActuelle.getNiveau().getMap();
		niveau = Coeur.partieActuelle.getNiveau();
	}

	public boolean prete() {
		return prete;
	}

	public char direction() {
		prete = false;
		return direction;
	}
	protected abstract void calculTrajet();
	public void calculerNext() {
		calculTrajet();
		prete = true;
	}

	
}
