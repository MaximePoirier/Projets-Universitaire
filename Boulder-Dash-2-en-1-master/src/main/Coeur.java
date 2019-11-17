package main;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ia.IA;
import ia.IaAleatoire;
import loader.EnsembleDeNiveaux;
import loader.Loader;
import tasks.FrameTask;
import tasks.IATask;
import tasks.TickTask;
import tasks.TickTimeTask;
import vue.Fenetre;
import vue.FinPanel;
import vue.JeuPanel;
import vue.ScorePanel;
import vue.Sprites;

public final class Coeur {

	// valeur entre 25 et +infini
	public static final int FPS = 30;
	public static final boolean IA = true;
	public static final IA ia = new IaAleatoire();
	public static Controleur controleur = new Controleur(38, 37, 40, 39, 78, IA);
	public static final List<Integer> SCORES = new ArrayList<Integer>();
	public static final Fenetre FENETRE = new Fenetre(1000, 750);
	public static int ticks;
	public static ScheduledExecutorService tasks = Executors.newScheduledThreadPool(2);
	public static Partie partieActuelle;
	public static EnsembleDeNiveaux ensembleDeNiveau;
	public static int niveau = 0;
	public static void main(String[] args) {
		Coeur.commencer();
	}

	public static void commencer() {
		String cheminFichier = "BDCFF_ArnoWeber/ArnoDash01.txt";
		Sprites.chargerSprites("images");
		ensembleDeNiveau = Loader.charger_ensemble_de_niveaux(cheminFichier);
		chargerNiveau(niveau, IA);
		if (IA)
			Coeur.commencerJeuIa();
		else
			Coeur.commencerJeu();
	}

  	public static void commencerJeu() { 
   		startScheduleds(); 
 	} 

	public static void commencerJeuIa() {
		starScheduledsIA();
	}

	public static void tick() {
		partieActuelle.tick();
		
		if (partieActuelle.isFini()) {
			SCORES.add(niveau, partieActuelle.getScore());
			niveauSuivant();
		} else if (partieActuelle.isResetCalled()) {
			chargerNiveau(niveau, IA);
		} else if (partieActuelle.isNextLevelCalled()) {
			SCORES.add(niveau, 0);
			niveauSuivant();
		}
		
		if (IA) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					ia.calculerNext();
				}
			});
			t.run();
		}
	}

	private static void niveauSuivant() {
		niveau++;
		if (niveau + 1 <= ensembleDeNiveau.getNombre_de_niveaux()) {
			chargerNiveau(niveau, IA);
		} else {
			finDuJeu();
		}
	}

	private static void chargerNiveau(int index, boolean ia) {
		controleur = controleur.clone();
		partieActuelle = new Partie(ensembleDeNiveau.getNiveaux().get(index).clone());
		partieActuelle.initialiserPartie();
		ticks = (int) (partieActuelle.getNiveau().getCaveDelay() * 1.5);
		if (ia)
			starScheduledsIA();
		else
			startScheduleds();
		Sprites.cacherSortie();
		FENETRE.getContentPane().removeAll();
		FENETRE.getContentPane().setLayout(new BorderLayout());
		FENETRE.getContentPane().add(new JeuPanel(), BorderLayout.CENTER);
		FENETRE.getContentPane().add(new ScorePanel(), BorderLayout.NORTH);
		FENETRE.getContentPane().validate();
	}

	private static void finDuJeu() {
		FENETRE.getContentPane().removeAll();
		FENETRE.getContentPane().add(new FinPanel());
		FENETRE.getContentPane().validate();
		FENETRE.repaint();
		tasks.shutdown();
	}

	private static void startScheduleds() {
		tasks.shutdown();
		controleur.setTourParTour(false);
		tasks = Executors.newScheduledThreadPool(2);
		tasks.scheduleAtFixedRate(new FrameTask(), 0, 1000 / FPS, TimeUnit.MILLISECONDS);
		if (ticks > 1)
			tasks.scheduleAtFixedRate(new TickTask(), 0, 1000 / ticks, TimeUnit.MILLISECONDS);
		else {
			tasks.scheduleAtFixedRate(new TickTimeTask(), 0, 1000 / ticks, TimeUnit.MILLISECONDS);
			controleur.setTourParTour(true);
		}
	}

	private static void starScheduledsIA() {
		tasks.shutdown();
		controleur.setTourParTour(false);
		ia.initialiser();
		tasks = Executors.newScheduledThreadPool(2);
		tasks.scheduleAtFixedRate(new FrameTask(), 0, 1000 / FPS, TimeUnit.MILLISECONDS);
		tasks.scheduleAtFixedRate(new IATask(), 0,  1000 / ticks, TimeUnit.MILLISECONDS);
	}
}
