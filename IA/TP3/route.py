def base_regles():
    regles = [
        ([('panneau', 'ecole')], ('prox_etab', 'vrai')),
        ([('panneau_vit', 30)], ('limit_vit', 'vrai')),
        ([('meteo', 'pluie')], ('mauv_visib', 'vrai')),
        ([('meteo', 'neige')], ('chaussee_glissante', 'vrai')),
        ([('mauv_visib', 'vrai')], ('danger', 'vrai')),
        ([('chaussee_glissante', 'vrai')], ('danger', 'vrai')),
        ([('lieu', 'en_ville'), ('type_veh', 'leger'),
          ('limit_vit', 'faux'), ('prox_etab', 'faux'),
          ('danger', 'faux')], ('vitesse', 50)),
        ([('lieu', 'en_ville'), ('type_veh', 'leger'),
          ('limit_vit', 'faux'), ('prox_etab', 'faux'),
          ('danger', 'vrai'), ('vitesse', 40)]),
        ([('lieu', 'en_ville'), ('type_veh', 'leger'),
          ('limit_vit', 'vrai'), ('panneau_vit', 30),
          ('prox_etab', 'faux'), ('danger', 'faux')],
         ('vitesse', 30))]

    br = {}
    for regle in regles:
        if regle[1] in br:
            br[regle[1]].append(regle[0])
        else:
            br[regle[1]] = [regle[0]]
    print(br)
    return br


def base_faits():
    return [('lieu', 'en_ville'), ('type_veh', 'leger'), ('meteo', 'pluie') ]


def chainage_avant_sat(regles, faits):
    while True:
        f = nouveau_fait_derive(regles, faits)
        if f is None:
            break
        faits.append(f)


def nouveau_fait_derive(regles, faits):
    for f in regles.keys():
        print('1. pour la regle qui a pour tete', f)
        if f not in faits:
            print(f, 'pas dans les faits')
            for premisse in regles[f]:
                ok = True
                for c in premisse:
                    print('je cherche à montrer si la premisse est satisfaite : ', c)
                    if not satisfait(c, faits):
                        ok = False
                if ok:
                    return f
    return None


def satisfait(c, faits):
    if c in faits:
        print(c, 'satisfaite')
        return True
    (attr, val) = c
    if val == 'faux':
        print('l attribut', attr, 'not "vrai" ==>', not (attr, 'vrai') in faits)
        return not (attr, 'vrai') in faits


def chainage_avant_but(regles, faits, attr):
    while True:
        f = nouveau_fait_derive(regles, faits)
        if not f is None:
            faits.append(f)
        if f is None or f[0] == attr:
            break


def reponse(attr, faits):
    for (a, v) in faits:
        if attr == a:
            print(attr, '=', v)


if __name__ == "__main__":
    chainage_avant_but(base_regles(), base_faits(), 'vitesse')
