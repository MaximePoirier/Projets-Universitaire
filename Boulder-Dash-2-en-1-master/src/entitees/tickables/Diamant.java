package entitees.tickables;

import entitees.MurMagique;
import entitees.Vide;
import entitees.tickables.ennemis.Libellule;
import entitees.tickables.ennemis.Luciole;
import entiteesabstraites.Ennemi;
import entiteesabstraites.Entitee;
import entiteesabstraites.Tickable;

public class Diamant extends Tickable {

	public Diamant(int x, int y) {
		super(x, y);
		setDestructible(true);
		getDeplacementsPossibles().add(Rockford.class);
		getDeplacementsPossibles().add(MurMagique.class);
		getDeplacementsPossibles().add(Libellule.class);
		getDeplacementsPossibles().add(Luciole.class);
	}


	public void tick() {
		gererChute();
	}

	@Override
	protected boolean contactAutreEntitee(Entitee entitee) {

		// si l'entitee est un mur magique et qu'en dessous du mur magique se
		// trouve du vide, alors le diamant meurt, une pierre apparait en
		// dessous du mur magique et le mur magique perd une charge
		if (entitee.getClass().equals(MurMagique.class)
				&& getMap()[entitee.getX()][entitee.getY() + 1].getClass().equals(Vide.class)) {
			mourir();
			getPartie_actuelle().put(new Pierre(entitee.getX(), entitee.getY() + 1));
			((Pierre) getMap()[entitee.getX()][entitee.getY() + 1]).setChute(true);
			((MurMagique) getMap()[entitee.getX()][entitee.getY()]).decrementerMagicWallTime();
			return false;
		}

		else if (entitee.getClass().equals(Rockford.class)) {
			getPartie_actuelle().incrementerNbDiamants();
			mourir();
			return false;
		} else if (entitee instanceof Ennemi) {
			if (entitee.getClass().equals(Libellule.class))
				exploser('b', true);
			else
				exploser('b', false);
			return false;
		}
		return true;
	}

}
