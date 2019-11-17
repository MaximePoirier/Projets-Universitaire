package entitees.tickables;

import entitees.Amibe;
import entitees.MurMagique;
import entitees.Vide;
import entitees.tickables.ennemis.Libellule;
import entitees.tickables.ennemis.Luciole;
import entiteesabstraites.Entitee;
import entiteesabstraites.Tickable;

public class Pierre extends Tickable {

	public Pierre(int x, int y) {
		super(x, y);
		setDestructible(true);
		getDeplacementsPossibles().add(Rockford.class);
		getDeplacementsPossibles().add(Luciole.class);
		getDeplacementsPossibles().add(Libellule.class);
		getDeplacementsPossibles().add(Amibe.class);
		getDeplacementsPossibles().add(MurMagique.class);
	}

	public void tick() {
		gererChute();
	}

	@Override
	protected boolean contactAutreEntitee(Entitee entitee) {
		// si l'entitee est un mur magique et qu'en dessous du mur magique se
		// trouve du vide, alors la pierre meurt, un diamant apparait en dessous
		// du mur magique et le mur magique perd une charge
		if (entitee.getClass().equals(MurMagique.class)
				&& getMap()[entitee.getX()][entitee.getY() + 1].getClass().equals(Vide.class)) {
			mourir();
			getPartie_actuelle().put(new Diamant(entitee.getX(), entitee.getY() + 1));
			((MurMagique) getMap()[entitee.getX()][entitee.getY()]).decrementerMagicWallTime();
			return false;
		} else if (entitee.getClass().equals(Libellule.class)) {
			exploser('b', true);
			return false;
		}else if (entitee.getClass().equals(Amibe.class)) {
			return false;
		} else if (!entitee.getClass().equals(Vide.class) && !entitee.getClass().equals(MurMagique.class)) {
			exploser('b', false);
			return false;
		} else if (entitee.getClass().equals(MurMagique.class)) {
			return false;
		}
		
		return true;
	}
}
