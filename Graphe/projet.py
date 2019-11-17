#! /usr/local/bin/python
#-*- coding: utf_8 -*-

import random
import math



class Graphe(object):
	"""Classe représentant un graphe.

	Un graphe est représenté par un dictionnaire.
	La coloration est représenté par une liste où les indices sont les sommets et prend la valeur 1 si le sommet est rouge et 0 si il est bleu.
	"""
	def __init__(self):
		"""Initialise le graphe à vide.
		"""
		self.graphe = {}
		self.couleur = []

	def ajouteSommet(self, sommet):
		"""Ajoute un sommet au graphe sans aucun voisin.
		"""
		if sommet not in self.graphe.keys():
			self.graphe[sommet] = {}

	def ajouteArrete(self, sommet, sommetVoisin, p):
		"""Crée une arrête en reliant sommet avec sommetVoisin en associant le poids p.
		"""
		if sommet != sommetVoisin:
			try:
				self.graphe[sommetVoisin][sommet] = p
				self.graphe[sommet][sommetVoisin] = p
			except KeyError:
				pass

	def supprimeSommet(self, sommet):
		"""Supprime un sommet du graphe.
		"""
		for sommetVoisin in self.graphe[sommet].keys():
			del self.graphe[sommetVoisin][sommet]
		del self.graphe[sommet]

	def supprimeArrete(self, sommet, sommetVoisin):
		"""Supprime une arrête.
		"""
		if sommet in self.graphe[sommetVoisin]:
			self.supprimeSommet(sommet)
			self.supprimeSommet(sommetVoisin)



	def toMatrice(self):
		"""Affichage matriciel du graphe.
		"""
		print " ",
		for i in sorted(self.graphe.keys()):
			print i,
		print
		for i in sorted(self.graphe.keys()):
			print i,
			for j in sorted(self.graphe.keys()):
				if i in self.graphe[j].keys():
					print '1',
				else:
					print '0',
			print

	def toListe(self):
		"""Affiche le graphe sous forme de listes d'adjacences.
		"""
		for i in sorted(self.graphe.keys()):
			print i, " --> ",
			print self.graphe[i].keys()

	def toXML(self):
		"""Affiche le graphe sous une structure XML.
		"""
		from xml.dom.minidom import Document

		try:
			racine = doc.getElementsByName('graphe').item(0)
		except:
			doc = Document()
			racine = doc.createElement("graphe")
			doc.appendChild(racine)

		for sommet in sorted(self.graphe.keys()):
			try:
				noeud = doc.getElementsByName(sommet)
			except:
				noeud = doc.createElement(sommet)
				racine.appendChild(noeud)

			if len(self.graphe[sommet].keys()) == 0:
				element = doc.createTextNode("")
				noeud.appendChild(element)

			for voisin in sorted(self.graphe[sommet].keys()):
				try:
					element = doc.createElement("voisin")
					element.setAttribute("nom", voisin)
					element.setAttribute("poids",str(self.graphe[sommet][voisin]))
					noeud.appendChild(element)
				except:
					pass

		return doc.toprettyxml()

	def __eq__(self, graphe1):
		"""Compare deux graphes.
		"""
		return self.graphe == graphe1

	def __str__(self):
		"""Affichage du graphe.
		"""
		return repr(self.graphe)

	def __repr__(self):
		"""Représentation du graphe.
		"""
		return repr(self.graphe)

	def generation(self, n,p):
		"""Génère un graphe à n sommet où chaque arrete a une probabilité p d'appartenir au graphe
		"""
		for i in range(0,n):
			self.ajouteSommet(i)
		for i in range(0,n):
			for j in range(i+1,n):
				if random.random() <= p:
					self.ajouteArrete(i,j,1)

	def colorie(self,r):
		"""Génère une liste où l'indice correspond à un sommet du graphe et la valeur 1 correspond à la couleur rouge et 0 correspond à la couleur bleu
		avec une probabilité r que le sommet soit rouge (et 1-r que le sommet soit bleu)
		"""
		taille=len(self.graphe.keys())
		self.couleur = [None] * taille
		for i in range(0,taille):
			if random.random() <= r:
				self.couleur[i] = 1;
			else:
				self.couleur[i] = 0;

	def estDestructrice(self,seq):
		"""Test si une séquence seq est 2-destructrice ou non
		"""
		if seq == []:
			return 0
		ensVoisPrec = []
		for i in range(0,len(self.graphe.keys())-2):
			nbVoisR=0
			ensVoisPrec.append(seq[i])
			for j in self.graphe[seq[i]].keys():
				if self.couleur[j] == 1:
					if j not in ensVoisPrec:
						nbVoisR=nbVoisR+1
			if nbVoisR > 2:
				return 0
		return 1




def constSeqDest(graph):
	"""Construit et retourne une sequence 2-destructrice retourne une liste vide si une tel séquence n'existe pas.
	"""
	seq = []
	nbVoisR = [None] * len(graph.graphe.keys())
	tmpNbRouge = 0
	for i in range(0,len(graph.graphe.keys())):
		tmpNbRouge=0
		for j in graph.graphe[i].keys():
			if graph.couleur[j] == 1 :
				tmpNbRouge = tmpNbRouge + 1
		nbVoisR[i] = tmpNbRouge
	tmpindex = 0
	for i in range(0,len(graph.graphe.keys())):
		if min(nbVoisR) > 2:
			return []
		tmpindex = nbVoisR.index(min(nbVoisR))
		seq.append(tmpindex)
		if graph.couleur[tmpindex] == 1:
			for j in graph.graphe[tmpindex].keys():
				nbVoisR[j] = nbVoisR[j] - 1
		nbVoisR[tmpindex] = float("inf")
	return seq

def testA(n,p,r):
	nbExperiences=0.
	nbCasPositifs=0.
	while nbExperiences < 1000:
		nbExperiences = nbExperiences +1
		graph = Graphe()
		graph.generation(n,p)
		graph.colorie(r)
		if constSeqDest(graph) != []:
			nbCasPositifs = nbCasPositifs + 1
	return nbCasPositifs/nbExperiences

def testB(n,p):
	r=0.5
	incertitude = 0.01
	modif = 0.5
	res = 0.
	while 1:
		res = testA(n,p,r)
		if  (0.5 - incertitude < res) and (res < 0.5 + incertitude):
			return r

		modif = (modif/2)
		if res > 0.5 :
			r = r + modif
		else :
			r = r - modif

if __name__ == "__main__":
	# Point d'entrée en exécution.
	print "Donnez la valeur de n :"
	n=input()
	print "Donnez la valeur de p :"
	p=input()
	r = testB(n,p)
	print "Valeur de r retourné par testB : "+str(r)
	print "Valeur retournée par testA avec en paramètre le r retournée par testB : "+str(testA(n,p,r))
	"""
	graph = Graphe()
	graph.generation(10,0.5)
	print
	graph.toListe()
	print
	graph.colorie(0.5)
	print graph.couleur
	seqDest=constSeqDest(graph)
	print seqDest
	print graph.estDestructrice(seqDest)
	"""
