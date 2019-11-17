#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "listes_int.h"


/*! \file listes_int.c
 * \brief La documentation pour les parties (types, variables, fonctions…) internes du module sont documentées ici.
 *
 * \copyright PASD
 * \version 2017
 */


/*!
 * Structure servant à définir un élément de type int de la liste.
 * Cette structure n'est visible que depuis listes_int.c .
 * Les fonctions attenantes sont static pour être également masquées. */
struct maillon_struct {
  int val;
  struct maillon_struct* prec;
  struct maillon_struct* suivant;
};

/*! Un maillon est un pointeur sur (référence vers) une struct maillon_struct . */
typedef struct maillon_struct * maillon;



/*!
 * Création d'un maillon dont la valeur est _val .
 * suivant et precedent doivent pointer sur ce maillon.
 * \param _val valeur à stocker dans le maillon.
 * \return nouveau maillon stockant _val et bouclant sur lui-même.
 */
static maillon maillon_creer(int const _val) {
  maillon res = (maillon) malloc(sizeof(struct maillon_struct));
  res->val=_val;
  res->prec = res->suivant = res;
  return res;
}


/*!
 * Test pour savoir si le maillon est unique (bouclant sur lui-même et correspondant à une liste de taille 1).
 * \param m maillon à tester.
 */
static bool maillon_est_unique(maillon const m) {
  return (m->prec == m && m->suivant == m);
}


/*!
 * Destruction de toute la liste chaînée.
 * \param m (pointeur sur un) maillon de la liste chaînée à détruire.
*/
static void maillon_detruire(maillon * const m) {
  if(!maillon_est_unique(*m)){
    (*m)->prec->suivant = (*m)->suivant;
    (*m)->suivant->prec = (*m)->prec;
    maillon_detruire(&((*m)->suivant));
  }
  free(*m);
  return ;
}


/*!
 * Affichage d'un ensefprintf(f,"%d ", m_debut->val);
  if(m_fin != m_debut) // Si on est pas déjà sur le dernier on continue
    maillon_afficher(f,m_debut->suivant,m_fin);mble de maillon à partir d'un début et d'une fin.
 * Le résultat est de la forme \verbatim[ 1 2 3 ]\endverbatim sans saut de ligne.
 * \param f flux où imprimer.
 * \param m_debut premier maillon à imprimer.
 * \param m_fin dernier maillon à imprimer.
 */
static void maillon_afficher(FILE * const f, maillon const m_debut,maillon const m_fin) {
  fprintf(f,"[");
  maillon current = m_debut;
  do{
    fprintf(f," %d",current->val);
    current = current->suivant;
  } while (current != m_fin->suivant);

  fprintf(f, " ]");
  return ;
}


/*!
 * Ajout d'un élément avant le maillon.
 * \param m maillon avant lequel on doit insérer.
 * \param _val valeur entière à insérer.
 */
static void maillon_ajouter_avant(maillon const m, int const _val) {
  maillon nv = maillon_creer(_val);
  nv->prec = m->prec;
  nv->suivant = m;
  m->prec->suivant=nv;
  m->prec=nv;
  return ;
}


/*!
 * Ajout d'un élément après le maillon.
 * \param m maillon après lequel on doit insérer.
 * \param _val valeur entière à insérer.
 */
static void maillon_ajouter_apres(maillon const m, int const _val) {
  maillon nv = maillon_creer(_val);
  nv->prec = m;
  nv->suivant = m->suivant;
  m->suivant->prec=nv;
  m->suivant=nv;
  return ;
}

/*!
 * Suppression du maillon d'avant s'il existe (c.-à-d. si la liste correspondante ne contient qu'un maillon on ne fait rien).
 * \param m maillon avant lequel on doit supprimer.
 */
static void maillon_supprimer_avant(maillon const m) {
  maillon old = m->prec;
  old->prec->suivant=m;
  m->prec=old->prec;
  free(old);
}


/*! suppression du maillon d'après s'il existe (c.-à-d. si la liste correspondante ne contient qu'un maillon on ne fait rien).
 * \param m maillon après lequel on doit supprimer.
*/
static void maillon_supprimer_apres(maillon const m) {
  maillon old = m->suivant;
  old->suivant->prec=m;
  m->suivant=old->suivant;
  free(old);
}


/*!
 * Structure pour la liste en étendant la structure maillon.
 * Quand la liste est vide, il n'y a pas de liste circulaire (et les pointeurs concernés valent NULL).
*/
struct liste_struct {
  unsigned int taille;
  maillon tete;
  maillon courant;
};


liste liste_creer(void) {
  liste maliste = (liste)malloc(sizeof(struct liste_struct));
  maliste->taille=0;
  maliste->tete=NULL;
  maliste->courant=NULL;
  return maliste;
}

bool liste_est_vide(liste const l) {
  return !l->taille;
}

void liste_detruire(liste * const l) {
  maillon_detruire(&((*l)->tete));
  free(*l);
  return ;
}


/*!
 * Pour ajouter une valeur à une liste vide.
 * fonction facultative mais qui peut aider
 * \param l liste où ajouter.
 * \param val valeur entière à jouter.
 */
static void liste_ajouter_a_vide(liste const l, int const val) {
  l->tete=l->courant=maillon_creer(val);
  l->taille++;
  return ;
}


/*!
 * Pour enlever la dernière valeur.
 * fonction facultative mais qui peut aider
 * \param l liste où enlever
 */
static void liste_enlever_dernier(liste const l) {
  /* tant que courant n'est pas l'avant dernier */
  while(l->tete != (l->courant=l->courant->suivant)->suivant->suivant);

  maillon_supprimer_apres(l->courant);
  if(0 == --(l->taille))
    l->tete=l->courant=NULL;
  return ;
}

void liste_insertion_debut(liste const l, int const val) {
  if (liste_est_vide(l)){
    liste_ajouter_a_vide(l,val);
    return ;
  }
  maillon_ajouter_avant(l->tete,val);
  l->taille++;
  l->tete = l->tete->prec;
  return ;
}


void liste_insertion_fin(liste const l, int const val) {
  if (liste_est_vide(l)){
    liste_ajouter_a_vide(l,val);
    return ;
  }
  /* tant que courant n'est pas le dernier */
  while(l->tete != (l->courant=l->courant->suivant)->suivant);
  assert(l->tete == l->courant->suivant);
  maillon_ajouter_apres(l->courant,val);
  l->taille++;
  return ;
}


void liste_insertion_apres(liste const l, int const val) {
  if (liste_est_vide(l)){
    liste_ajouter_a_vide(l,val);
    return ;
  }
  l->taille++;
  maillon_ajouter_apres(l->courant,val);
  return ;
}


void liste_insertion_avant(liste const l, int const val) {
  if (liste_est_vide(l)){
    liste_ajouter_a_vide(l,val);
    return ;
  }
  l->taille++;
  maillon_ajouter_avant(l->courant,val);
  if(l->tete == l->courant) // si le nouvel ajouté est le premier
    l->tete=l->tete->prec;
  return ;
}


void liste_suppression_debut(liste const l) {
  l->tete=l->tete->suivant;
  maillon_supprimer_avant(l->tete);
  if(0 == --(l->taille))
    l->tete=l->courant=NULL;
  return ;
}


void liste_suppression_fin(liste const l) {
  liste_enlever_dernier(l);
}


void liste_suppression_avant(liste const l) {
  maillon_supprimer_avant(l->courant);
  if(0 == --(l->taille))
    l->tete=l->courant=NULL;
  return ;
}


void liste_suppression_apres(liste const l) {
  maillon_supprimer_apres(l->courant);

  if(0 == --(l->taille))
    l->tete=l->courant=NULL;
  return ;
}

void liste_affichage(FILE * const f, liste const l) {
  fprintf(f, "Liste de %d éléments : ",l->taille );
  while(l->tete != (l->courant=l->courant->suivant)->suivant);
  assert(l->tete == l->courant->suivant);
  maillon_afficher(f,l->tete,l->courant);
  return ;
}


void liste_courant_init(liste const l) {
  l->courant=l->tete;
  return ;
}


void liste_courant_suivant(liste const l) {
  l->courant=l->courant->suivant;
  return ;
}


void liste_decalage(liste const l, int n) {
  for(int i = 0; i < n; i++, liste_courant_suivant(l));
  return ;
}


int liste_taille(liste const l) {
  return l->taille;
}


int liste_valeur_tete(liste const l) {
  return l->tete->val;
}


int liste_valeur_courant(liste const l) {
  return l->courant->val;
}


bool liste_est_tete(liste const l) {
  return l->tete==l->courant;
}


bool liste_est_fin (liste const l) {
  return l->courant==l->tete->prec;
}
