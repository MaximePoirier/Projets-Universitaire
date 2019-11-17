#! /usr/local/bin/python
# -*- coding: utf_8 -*-


def initial():
    return [5, 0, 5, 2, 1]


def final(etat):
    if etat[1] == etat[4]:
        return True
    else:
        return False


def actions():
    return [jeter, transferer, remplir]


def jeter(etat):
    [ia, ib, na, nb, fb] = etat
    listSuiv = []
    if ia > 0:
        listSuiv.append([0, ib, na, nb, fb])
    if ib > 0:
        listSuiv.append([ia, 0, na, nb, fb])
    return listSuiv


def transferer(etat):
    [ia, ib, na, nb, fb] = etat
    listSuiv = []
    if nb-ib >= ia > 0:
        listSuiv.append([0, ia+ib, na, nb, fb])
    if na-ia >= ib > 0:
        listSuiv.append([ia+ib, 0, na, nb, fb])
    return listSuiv


def remplir(etat):
    [ia, ib, na, nb, fb] = etat
    listSuiv = []
    if nb-ib > 0 and ia >0:
        listSuiv.append([ia-(nb-ib), nb, na, nb, fb])
    if na-ia > 0 and ib >0:
        listSuiv.append([na, ib-(na-ia), na, nb, fb])
    return listSuiv

def afficher_sol(noeud):
    print("Solution :")
    chemin = getChemin(noeud)
    for i in chemin:
        print(getEtat(i))
    print(getEtat(noeud))
    print("Longueur : " + str(len(chemin) + 1))


def getChemin(noeud):
    return noeud[1]


def getEtat(noeud):
    return noeud[0]


def dejavu(etat, chemin):
    for noeud in chemin:
        if etat == getEtat(noeud):
            return True
    return False


def noeudsFils(noeud):
    ls = []
    for action in actions():
        listSuiv = action(getEtat(noeud))
        for etatSuiv in listSuiv:
            if not dejavu(etatSuiv, getChemin(noeud)):
                cheminSuiv = list(getChemin(noeud))
                cheminSuiv.append(noeud)
                ls.append((etatSuiv, cheminSuiv))
    return ls


def rp_rec(noeud):
    if final(getEtat(noeud)):
        afficher_sol(noeud)
        return True
    else:
        listSuiv = noeudsFils(noeud)
        if not listSuiv:
            return False
        for suiv in listSuiv:
            if rp_rec(suiv):
                return True
        return False


if __name__ == "__main__":
    noeudinitial = [initial(), []]
    print(rp_rec(noeudinitial))
