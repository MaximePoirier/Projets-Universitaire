#! /usr/local/bin/python
# -*- coding: utf_8 -*-
import math
import random


# Retourne une instance de jeu de taquin terminer de taile n*n
def initial(n):
    etatinitial = []
    for i in range(n*n - 1):
        etatinitial.append(i+1)
    etatinitial.append('v')
    return etatinitial


# Génère un état initial en appliquant nbcoups actions sur un etat final de taille n*n
# (De sorte que toute instance génréré soit résolvable)
def generation(nbcoups, n):
    cpt = 0
    etat = initial(n)
    while cpt < nbcoups:
        r = random.randint(1, 4)
        if r == 1:
            etatinter = droite(etat)
            if etatinter:
                etat = etatinter[0][0]
                cpt += 1
        elif r == 2:
            etatinter = haut(etat)
            if etatinter:
                etat = etatinter[0][0]
                cpt += 1
        elif r == 3:
            etatinter = gauche(etat)
            if etatinter:
                etat = etatinter[0][0]
                cpt += 1
        else:
            etatinter = bas(etat)
            if etatinter:
                etat = etatinter[0][0]
                cpt += 1
    return etat


def final(etat):
    for i in range(len(etat) - 1):
        if etat[i] != i + 1:
            return False
    return True


def actions():
    return [droite, gauche, haut, bas]


def droite(etat):
    n = int(math.sqrt(len(etat)))
    i = etat.index('v')
    listSuiv = []
    if i % n != n - 1:
        etat_suivant = list(etat)
        etat_suivant[i] = etat_suivant[i + 1]
        etat_suivant[i + 1] = 'v'
        listSuiv.append((etat_suivant, 1))
    return listSuiv


def gauche(etat):
    n = int(math.sqrt(len(etat)))
    i = etat.index('v')
    listSuiv = []
    if i % n != 0:
        etat_suivant = list(etat)
        etat_suivant[i] = etat_suivant[i - 1]
        etat_suivant[i - 1] = 'v'
        listSuiv.append((etat_suivant, 1))
    return listSuiv


def haut(etat):
    n = int(math.sqrt(len(etat)))
    i = etat.index('v')
    listSuiv = []
    if i - n >= 0:
        etat_suivant = list(etat)
        etat_suivant[i] = etat_suivant[i - n]
        etat_suivant[i - n] = 'v'
        listSuiv.append((etat_suivant, 1))
    return listSuiv


def bas(etat):
    n = int(math.sqrt(len(etat)))
    i = etat.index('v')
    listSuiv = []
    if i + n < len(etat):
        etat_suivant = list(etat)
        etat_suivant[i] = etat_suivant[i + n]
        etat_suivant[i + n] = 'v'
        listSuiv.append((etat_suivant, 1))
    return listSuiv


def getChemin(noeud):
    return noeud[1]


def getEtat(noeud):
    return noeud[0]


def getCout(noeud):
    return noeud[2]


def getHeuristic(noeud):
    return noeud[3]


def dejavu(etat, chemin):
    for noeud in chemin:
        if etat == getEtat(noeud):
            return True
    return False


def afficher_sol(noeud):
    print("Solution : ")
    chemin = getChemin(noeud)
    for i in chemin:
        print(getEtat(i))
    print(getEtat(noeud))
    print(getCout(noeud))


# nombre de cases mal placées
def heuristic(etat):
    cpt = 0
    j = etat.index('v')
    if j != len(etat) - 1:
        cpt += 1
    for i in range(len(etat)):
        if i != j:
            if etat[i] != i + 1:
                cpt += 1
    return cpt


# Somme des distances (coût) de chaque elt pour qu'ils soient bien placé
def heuristic2(etat):
    cpt = 0
    n = int(math.sqrt(len(etat)))
    for i in range(1, len(etat)):
        index = etat.index(i)
        distance = abs(index - i)
        if distance != 0:
            cpt += int(distance/n) + distance % n
    distance = abs(etat.index('v') - (len(etat)-1))
    if distance != 0:
        cpt += int(distance/n) + distance % n
    return cpt


def noeudsFils(noeud, h):
    ls = []
    for action in actions():
        listSuiv = action(getEtat(noeud))
        for elt in listSuiv:
            etatSuiv = elt[0]
            coutelt = elt[1]
            if not dejavu(etatSuiv, getChemin(noeud)):
                chemSuiv = list(getChemin(noeud))
                chemSuiv.append(noeud)
                if h == 0:
                    ls.append((etatSuiv, chemSuiv, coutelt + getCout(noeud), heuristic(etatSuiv)))
                elif h == 1:
                    ls.append((etatSuiv, chemSuiv, coutelt + getCout(noeud), heuristic2(etatSuiv)))
    return ls


def aStar(listAtt, h):
    while listAtt:
        noeud = listAtt.pop()
        if final(getEtat(noeud)):
            afficher_sol(noeud)
            return True
        listSuiv = noeudsFils(noeud, h)
        listAtt.extend(listSuiv)
        listAtt.sort(key=lambda elt: elt[2] + elt[3], reverse=True)


if __name__ == "__main__":
    etatinitial = generation(60, 4)
    print(etatinitial)
    noeudinitial = [etatinitial, [], 0, heuristic(etatinitial)]
    print("heuristique nombre de chiffres mal placés : ")
    aStar([noeudinitial], 0)
    noeudinitial = [etatinitial, [], 0, heuristic2(etatinitial)]
    print("heuristique somme des distances: ")
    aStar([noeudinitial], 1)

