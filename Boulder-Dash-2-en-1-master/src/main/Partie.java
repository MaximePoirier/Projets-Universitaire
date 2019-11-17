package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entitees.Amibe;
import entiteesabstraites.Entitee;
import entiteesabstraites.Tickable;
import loader.Niveau;

public class Partie {
	private Niveau niveau;
	private int score, nbDiamants, temps_restant;
	private boolean fini, resetCalled, nextLevelCalled;
	private long tempsAuDebutDeLaPartie = System.currentTimeMillis();
	private List<Amibe> listeAmibes = new ArrayList<Amibe>();
	private List<Tickable> listeTickable = new ArrayList<Tickable>();
	private int compteurTicks;

	public Partie(Niveau niveau) {
		this.niveau = niveau;
		this.temps_restant = niveau.getCave_time();
		listeTickable = niveau.getListeTickable();
	}

	public void tick() {
		compteurTicks++;
		if (!checkNextLevelCalled() && !checkReset()) {
			gererLesTickables();
			gererLesAmibes();
			gererTemps();
		}
	}

	public void tickTemps() {
		gererTemps();
	}

	private void gererLesTickables() {
		List<Tickable> listeTickable2 = new ArrayList<Tickable>();
		for (Tickable t : listeTickable) {
			listeTickable2.add(t);
		}

		for (Tickable t : listeTickable2) {
			if (!t.isMort())
				t.tick();
		}
		Coeur.controleur.tick();
	}

	private void gererLesAmibes() {
		if (listeAmibes.size() > 0 && niveau.getAmoeba_time() != -1 && compteurTicks % niveau.getAmoeba_time() == 0) {
			Collections.shuffle(listeAmibes);
			for (Amibe amibe : listeAmibes) {
				if (amibe.sePropager()) {
					return;
				}
			}
			listeAmibes.get(0).transformerTousLesAmibesEnDiamant();
		}
	}

	private boolean checkReset() {
		if (resetCalled == false)
			this.resetCalled = Coeur.controleur.isSpace();
		return resetCalled;
	}

	private boolean checkNextLevelCalled() {
		if (nextLevelCalled == false)
			this.nextLevelCalled = Coeur.controleur.isReset();
		return nextLevelCalled;
	}

	private void gererTemps() {
		temps_restant = (niveau.getCave_time() - (int) (System.currentTimeMillis() - tempsAuDebutDeLaPartie) / 1000);
		if (temps_restant <= 0) {
			resetCalled = true;
			Coeur.tick();
		}
	}

	public void incrementerNbDiamants() {
		nbDiamants++;
		if (nbDiamants > niveau.getDiamonds_required()) {
			score = score + niveau.getDiamond_value_bonus();
		} else {
			score = score + niveau.getDiamond_value();
		}

		if (nbDiamants >= niveau.getDiamonds_required() && niveau.getSortie().isCacher()) {
			niveau.getSortie().seReveler();
		}
	}

	public void put(Entitee e) {
		if (getNiveau().getMap()[e.getX()][e.getY()].isDestructible()) {
			niveau.getMap()[e.getX()][e.getY()] = e;
			e.initialiser();
			if (e instanceof Tickable) {
				listeTickable.add((Tickable) e);
				Collections.sort(listeTickable);
			}
		}
	}

	public void initialiserPartie() {
		niveau.initialiserEntitees();
		listeAmibes = niveau.retourneListeDesAmibes();
	}

	public void demanderReset() {
		this.resetCalled = true;
	}

	public boolean isResetCalled() {
		return resetCalled;
	}

	public boolean isNextLevelCalled() {
		return nextLevelCalled;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public boolean isFini() {
		return fini;
	}

	public int getScore() {
		return score;
	}

	public int getNbDiamants() {
		return nbDiamants;
	}

	public int getTemps_restant() {
		return temps_restant;
	}

	public void setFini(boolean fini) {
		this.fini = fini;
	}

	public List<Amibe> getListeAmibes() {
		return listeAmibes;
	}

	public List<Tickable> getListeTickable() {
		return listeTickable;
	}
}
