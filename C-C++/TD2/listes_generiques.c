#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "listes_generiques.h"

/*! \file listes_generiques.c
 * \brief L'implémentation des fonctions
 * Les définitions des fonctions sont identiques à celles de liste des entiers.
 * Mais certaines nécessitent un paramètre supplémentaire la fonction copier
 * afficher ou detruire qui permettent de rendre générique la liste.
 * Pour l'essentiel il s'agit de reprendre les fonctions pour les listes
 * d'entiers et de les adapter à la généricité .
 *
 * \copyright PASD
 * \version 2017
 */

typedef struct maillon_struct * maillon;

struct maillon_struct {
	void* val;
	maillon suivant;
	maillon prec;
};


static maillon maillon_creer(void * val, void(*copier)(void* val, void** pt)) {
	maillon res = ( maillon ) malloc(sizeof(struct maillon_struct));
	copier(val,&(res->val));
	res->suivant=res->prec=res;
	return res;
}


static bool maillon_est_unique(maillon m){
	return (m->prec == m && m->suivant == m);
}


static void maillon_detruire(maillon* m, void(* detruire)(void** pt)) {
  if(!maillon_est_unique(*m)){
		(*m)->prec->suivant = (*m)->suivant;
		(*m)->suivant->prec = (*m)->prec;
    maillon_detruire(&((*m)->suivant),detruire);
	}
	detruire(&((*m)->val));
	free(*m);
  return ;
}


static void maillon_afficher(FILE* f, maillon m_debut, maillon m_fin, void(* afficher )(FILE *f, void* val)) {
	fprintf(f,"[ ");
	maillon current = m_debut;
	do{
		afficher(f,current->val);
		current = current->suivant;
	} while (current != m_fin->suivant);

	fprintf(f, "]");
	return ;
}


static void maillon_ajouter_avant(maillon m, void* _val, void(*copier)(void* val, void** pt)) {
	maillon nv = maillon_creer(_val,copier);
	nv->prec = m->prec;
	nv->suivant = m;
	m->prec->suivant=nv;
	m->prec=nv;
	return ;
}


static void maillon_ajouter_apres(maillon m, void * _val, void(*copier)(void* val, void** pt)) {
	maillon nv = maillon_creer(_val,copier);
	nv->prec = m;
	nv->suivant = m->suivant;
	m->suivant->prec=nv;
	m->suivant=nv;
	return ;
}


static void maillon_supprimer_avant(maillon m, void(*detruire)(void** pt)) {
	maillon old = m->prec;
  old->prec->suivant=m;
  m->prec=old->prec;
	detruire(&(old->val));
  free(old);
}


static void maillon_supprimer_apres(maillon m, void(*detruire)(void** pt)) {
	maillon old = m->suivant;
  old->suivant->prec=m;
  m->suivant=old->suivant;
	detruire(&(old->val));
  free(old);
}


/*!
 * la structure définit 3 champs supplémentaires qui sont des pointeurs sur fonction
*/
struct liste_struct {
	unsigned int taille;
	maillon tete;
	maillon courant;
	void ( *copier ) ( void * val , void ** pt );
	void ( *afficher ) ( FILE * f , void * val );
	void ( *detruire ) ( void ** pt);
};


liste liste_creer(void(*_copier)(void* val, void** pt),
		    					void(*_afficher)(FILE *f, void* val),
		    					void(* _detruire )(void** pt))
{
		liste maliste = (liste)malloc(sizeof(struct liste_struct));
		maliste->taille=0;
		maliste->tete=NULL;
		maliste->courant=NULL;
		maliste->copier=_copier;
		maliste->afficher=_afficher;
		maliste->detruire=_detruire;
		return maliste;
}


void liste_detruire(liste* l){
  maillon_detruire(&((*l)->tete),(*l)->detruire);
  free(*l);
}


void liste_insertion_debut(liste l, void* val){
	if (!l->taille){
		l->tete=l->courant=maillon_creer(val,l->copier);
		l->taille++;
    return ;
  }
  maillon_ajouter_avant(l->tete,val,l->copier);
  l->taille++;
  l->tete = l->tete->prec;
  return ;
}


void liste_insertion_fin(liste l, void* val){
	if (!l->taille){
		l->tete=l->courant=maillon_creer(val,l->copier);
		l->taille++;
    return ;
  }
  /* tant que courant n'est pas le dernier */
  while(l->tete != (l->courant=l->courant->suivant)->suivant);

  maillon_ajouter_apres(l->courant,val,l->copier);
  l->taille++;
  return ;
}


void liste_insertion_avant(liste l, void* val){
	if (!l->taille){
		l->tete=l->courant=maillon_creer(val,l->copier);
		l->taille++;
    return ;
  }
  l->taille++;
  maillon_ajouter_avant(l->courant,val,l->copier);
  if(l->tete == l->courant) // si le nouvel ajouté est le premier
    l->tete=l->tete->prec;
  return ;
}


void liste_insertion_apres(liste l, void* val){
	if (!l->taille){
		l->tete=l->courant=maillon_creer(val,l->copier);
		l->taille++;
    return ;
  }
  l->taille++;
  maillon_ajouter_apres(l->courant,val,l->copier);
  return ;
}


void liste_suppression_debut(liste l){
	l->tete=l->tete->suivant;
  maillon_supprimer_avant(l->tete,l->detruire);
  if(0 == --(l->taille))
    l->tete=l->courant=NULL;
  return ;
}


void liste_suppression_fin(liste l){
	/* tant que courant n'est pas l'avant dernier */
  while(l->tete != (l->courant=l->courant->suivant)->suivant->suivant);

  maillon_supprimer_apres(l->courant,l->detruire);
  if(0 == --(l->taille))
    l->tete=l->courant=NULL;
  return ;
}


void liste_suppression_avant(liste l){
  maillon_supprimer_avant(l->courant,l->detruire);
  if(0 == --(l->taille))
    l->tete=l->courant=NULL;
  return ;
}


void liste_suppression_apres(liste l){
	maillon_supprimer_apres(l->courant,l->detruire);

  if(0 == --(l->taille))
    l->tete=l->courant=NULL;
  return ;
}


void liste_affichage(FILE* f,liste l){
  fprintf(f, "Liste de %d éléments : ",l->taille );
  while(l->tete != (l->courant=l->courant->suivant)->suivant);

  maillon_afficher(f,l->tete,l->courant,l->afficher);
  return ;
}


void liste_decalage(liste l,int n){
  for(int i = 0; i < n; i++, liste_courant_suivant(l));
}


void * liste_valeur_tete(liste l){
  return l->tete->val;
}



void * liste_valeur_courant(liste l){
  return (void *)l->courant->val;
}


bool liste_est_tete(liste l){
  return l->tete==l->courant;
}


bool liste_est_fin (liste const l) {
  return l->courant==l->tete->prec;
}


void liste_courant_init(liste l){
  l->courant=l->tete;
  return ;
}


void liste_courant_suivant(liste l){
  l->courant=l->courant->suivant;
  return ;
}
