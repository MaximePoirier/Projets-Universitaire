#! /usr/local/bin/python
# -*- coding: utf_8 -*-


def initial():
    return [0, 0, 0, 1, 0, 1]


def final(etat):
    if etat == [1, 1, 1, 1, 1, 1]:
        return True
    else:
        return False


def inc(a):
    if a == 0:
        return 1
    else:
        return 0


def actions():
    return [action]


def action(etat):
    etatInter = []
    listSuiv = []
    for i in range(len(etat) - 1):
        etatInter = list(etat)
        etatInter[i] = inc(etatInter[i])
        etatInter[i + 1] = inc(etatInter[i + 1])
        listSuiv.append((etatInter, 1))
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
    print(len(chemin) + 1)


def noeudsFils(noeud):
    ls = []
    for action in actions():
        listSuiv = action(getEtat(noeud))
        for elt in listSuiv:
            etatSuiv = elt[0]
            coutelt = elt[1]
            if not dejavu(etatSuiv, getChemin(noeud)):
                chemSuiv = list(getChemin(noeud))
                chemSuiv.append(noeud)
                ls.append((etatSuiv, chemSuiv, coutelt + getCout(noeud), heuristic(etatSuiv)))
    return ls


def heuristic(etat):
    return (len(etat) - sum(etat)) / 2


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


def aStar(listAtt):
    while listAtt:
        noeud = listAtt.pop()
        if final(getEtat(noeud)):
            afficher_sol(noeud)
            return True
        listSuiv = noeudsFils(noeud)
        listAtt.extend(listSuiv)
        listAtt.sort(key=lambda elt: elt[2] + elt[3], reverse=True)


if __name__ == "__main__":
    noeudinitial = [initial(), [], 0, heuristic(initial())]

    print(aStar([noeudinitial]))

    print(rp_rec(noeudinitial))

