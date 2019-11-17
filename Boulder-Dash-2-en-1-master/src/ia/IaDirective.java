package ia;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class IaDirective extends IA{
	private Noeud[][] graphe;

	public IaDirective() {
		super();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				graphe[i][j]=new Noeud(map[i][j], 0, 0);
			}
		}
		
	}

	@Override
	protected void calculTrajet() {
		// TODO Auto-generated method stub
		
	}
	
	private LinkedList<Noeud> cheminPlusCourt(Noeud depart, Noeud objectif){
		Queue<Noeud> closedList = new LinkedList<Noeud>();
		Queue<Noeud> openList = new PriorityQueue<Noeud>();
		while (!openList.isEmpty()){
			Noeud u = openList.remove();
			if(u.getEntite().getX() == objectif.getX() && u.getY() == objectif.getY()){
				return reconstituerChemin(u);
			}
			Queue<Noeud> voisins = VoisinsNoeud(u);
			for(Noeud v : voisins){
				if(!((openList.contains(v) && v.getCout()<u.getCout()+1) || closedList.contains(v))){
					v.setCout(u.getCout()+1);
					v.setHeuristique(v.getCout()+Math.abs(objectif.getX()-v.getX())+Math.abs(objectif.getY()-v.getY()));
					openList.add(v);
				}
			}
			closedList.add(u);
		}
		return new LinkedList<Noeud>();
	}
	private Queue<Noeud> VoisinsNoeud(Noeud u){
		Queue<Noeud> voisins = new LinkedList<Noeud>();
		if(positionValide(graphe[u.getX()-1][u.getY()])){
			voisins.add(graphe[u.getX()-1][u.getY()]);
		}
		if(positionValide(graphe[u.getX()+1][u.getY()])){
			voisins.add(graphe[u.getX()+1][u.getY()]);
		}
		if(positionValide(graphe[u.getX()][u.getY()-1])){
			voisins.add(graphe[u.getX()][u.getY()-1]);
		}
		if(positionValide(graphe[u.getX()][u.getY()+1])){
			voisins.add(graphe[u.getX()][u.getY()+1]);
		}
		return voisins;
	}
	private boolean positionValide(Noeud v){
		return (v.getX()>=0 && v.getX()<graphe.length && v.getY()>=0 && v.getY()<graphe[0].length && v.isTraversable());
	}
	
	private LinkedList<Noeud> reconstituerChemin(Noeud u){
		return new LinkedList<Noeud>();
	}
	
}
