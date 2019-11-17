#ifndef LISTES_GENERIQUES
#define LISTES_GENERIQUES
#include <stdbool.h>

/*! \file listes_generiques.h
 * \brief Module liste générique.
 *
 * La structure est très similaire à celle pour les entiers
 * L'objectif est de rajouter des fonctions dans cette structure
 * afin de construire une liste d'éléments de type quelconque
 *
 * \copyright PASD
 * \version 2017
 */


typedef struct liste_struct * liste;

liste liste_creer (void (* _copie)(void* val, void** pt),
		    void (* _afficher)(FILE* f, void * val),
		    void (* _detruire)(void** pt)) ;

void liste_detruire (liste * l);

void liste_insertion_debut (liste l, void * val);

void liste_insertion_fin (liste l, void * val);

void liste_insertion_apres (liste l, void * val);

void liste_insertion_avant (liste l, void * val);

void liste_suppression_debut (liste l);

void liste_suppression_fin (liste l);

void liste_suppression_avant (liste l);

void liste_suppression_apres (liste l);

void liste_affichage (FILE * f, liste l);

bool liste_est_fin (liste const l);

void liste_courant_init (liste l);

void liste_courant_suivant (liste l);

void liste_decalage (liste l, int n);

unsigned int liste_taille (liste l);

void* liste_valeur_tete (liste l);

void* liste_valeur_courant (liste l);

bool liste_est_tete (liste l);


#endif
