#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#undef NDEBUG

#include "arbres.h"

/*!
 * \file
 * \brief
 * Les implémentations des deux structures arbre (cf arbres.h) et noeud
 * cette dernière est cachée et permet de construire effectivement l'arbre binaire
 * les fonctions associées à noeud sont donc "static"
 * \copyright PASD
 * \version 2017 
 */

typedef struct noeud_struct* noeud ;

struct noeud_struct {
  void* val;
  noeud f_g;
  noeud f_d;
} ; 


struct arbre_struct {
  noeud racine;
  void (*copier) (void *, void**);
  void (*detruire) (void **);
  int  (*comparer) (void *, void*);
}; 


/*!
 * Création d'un noeud
 * \param val la valeur affectée à ce noeud à partir d'une copie
 * \param copier fonction pour copier les valeurs au sein d'un noeud
 * \return un noeud feuille initialisé à val
 */
static noeud noeud_creer(void* val, void(*copier)(void *val, void** pt))
{
  noeud n = (noeud)malloc(sizeof(struct noeud_struct));
  n->f_g=n->f_d=NULL;
  copier(val,&(n->val));
  return n;
}


/*!
 * Destruction d'un noeud et de tous les noeuds se trouvant en dessous. 
 * \param n_pt le noeud départ de la destruction (remis à NULL à la fin)
 * \param detruire la fonction permettant de détruire la valeur au sein du noeud
 */
static void noeud_detruire_recursivement(noeud* n_pt, void(*detruire)(void** pt)) {
  if((*n_pt) == NULL)
    return;
  noeud_detruire_recursivement(&((*n_pt)->f_g),detruire);
  noeud_detruire_recursivement(&((*n_pt)->f_d),detruire);
  detruire(&((*n_pt)->val));
  free(*n_pt);
  (*n_pt)=NULL;
      
}


/*!
 * Détruit un noeud sans détruire les noeuds se trouvant en dessous.
 * Il faut donc assurer la structure de l'arbre binaire de recherche après la suppression.
 * \param n_pt noeud à détruire car il contient la valeur à supprimer
 * \param detruire la fonction permettant de détruire une valeur au sein d'un noeud
 */
static void noeud_detruire_simple(noeud* const n_pt, void(* detruire)(void** pt ))
{
  if(!*n_pt)
    return;
  
  if((*n_pt)->f_g == NULL || NULL == (*n_pt)->f_d){
    noeud pt=*n_pt;
    *n_pt=NULL;
    if(NULL != pt->f_d){
      (*n_pt)=pt->f_d;
    }
    else if(pt->f_g != NULL){
      (*n_pt)=pt->f_g;
    }
    detruire(&(pt->val));
    free(pt);
  }
  else{
    detruire(&((*n_pt)->val));
    for(noeud n = *n_pt; NULL != n->f_d;n=n->f_d){
      n->val=n->f_d->val;
      if (n->f_d->f_d == NULL){
	free(n->f_d);
	n->f_d=NULL;
	break;
      }
    }
  }
}

/*! 
 * affichage selon le parcours prefixe à partir d'un noeud donné
 * \param n le noeud débutant l'affichage
 * \param f le flux de sortie
 * \param afficher la fonction permettant d'afficher la valeur au sein d'un noeud
 */
static void noeud_afficher_prefixe(noeud n, FILE* f, void(* afficher)(void* val, FILE* f)) {
  if    (NULL == n){
    return ;
  }
  if(NULL != n->val){
    afficher(n->val,f);
  }  

  if(n->f_g)
    noeud_afficher_prefixe(n->f_g,f,afficher);
  if(n->f_d)
    noeud_afficher_prefixe(n->f_d,f,afficher);
  
}

/*! 
 * affichage selon le parcours infixe à partir d'un noeud donné
 * \param n le noeud débutant l'affichage
 * \param f le flux de sortie
 * \param afficher la fonction permettant d'afficher la valeur au sein d'un noeud
 */
static void noeud_afficher_infixe(noeud n, FILE * f, void(* afficher)(void* val, FILE * f))
{
  if    (n == NULL){
    return ;
  }
  if(n->f_g != NULL){
    noeud_afficher_infixe(n->f_g,f,afficher);
  }
  if(NULL != n->val){
    afficher(n->val,f);
  }
  if(n->f_d != NULL){
    noeud_afficher_infixe(n->f_d,f,afficher);
  }
}

/*! 
 * affichage selon le parcours postfixe à partir d'un noeud donné
 * \param n le noeud débutant l'affichage
 * \param f le flux de sortie
 * \param afficher la fonction permettant d'afficher la valeur au sein d'un noeud
 */
static void noeud_afficher_postfixe(noeud n, FILE * f, void(* afficher)(void* val, FILE * f))
{
  if    (NULL == n){
    return ;
  }
  if(n->f_g != NULL)
    noeud_afficher_postfixe(n->f_g,f,afficher);
  if(n->f_d != NULL)
    noeud_afficher_postfixe(n->f_d,f,afficher);
  if(NULL != n->val){
    afficher(n->val,f);
  }
}

/*!
 * 1+le nombre de noeud en dessous du noeud donné
 * \param n le noeud de départ
 * \return le nombre de noeuds à partir de n
 */
int noeud_taille(noeud n) {
  if(NULL == n){
    return 0;
  }
  else{
    int tg=noeud_taille(n->f_g);
    int td=noeud_taille(n->f_d);
    return 1+tg+td;
  }
}



arbre arbre_creer(void(* copier)(void* val, void** pt), void(* detruire)(void** pt), int(* comparer)(void *val1, void * val2))
{
  arbre a=(arbre)malloc(sizeof(struct arbre_struct));
  a->racine=NULL;
  a->copier=copier;
  a->detruire=detruire;
  a->comparer=comparer;
  return a;
}


void arbre_detruire(arbre* a ) {
  if(NULL != (*a)){
    if(NULL != (*a)->racine){
      noeud_detruire_recursivement(&((*a)->racine ),(*a)->detruire);
    }
    free(*a);
    (*a)=NULL;
  }
}

/*!
 * A compléter
 */

static noeud* arbre_chercher_position(arbre a, void* val)
{
  noeud* n = &(a->racine);
  while ((*n) != NULL) {
    int comp = a->comparer(val,(*n)->val);
    if (comp<=-1) 
      n = &((*n)->f_g);
    else if (comp>=1) 
      n = &((*n)->f_d);
    else 
      return n;
  }
  return n;
}



void arbre_insertion(arbre a, void *val )
{
  noeud *pt=arbre_chercher_position(a,val);
  if((*pt) == NULL){
    (*pt)=noeud_creer(val,a->copier);
  }
}


void arbre_afficher_prefixe(arbre a, FILE * f, void(* afficher)(void * val, FILE * f))
{
  noeud_afficher_prefixe(a->racine,f,afficher);
}

void arbre_afficher_infixe(arbre a, FILE * f, void(* afficher)(void* val, FILE* f))
{
  noeud_afficher_infixe(a->racine,f,afficher);
}

void arbre_afficher_postfixe(arbre a, FILE * f, void(* afficher)(void* val, FILE* f))
{
  noeud_afficher_postfixe(a->racine,f,afficher);
}


bool arbre_est_vide(arbre a)
{
  if(a->racine == NULL){
    return true;
  }
  else return false;
}



int arbre_taille(arbre a )
{
  return noeud_taille(a->racine);
}



void* arbre_rechercher(arbre a, void* val) {
  noeud *n=arbre_chercher_position(a,val);
  return *n ? (*n)->val : NULL;
}


void arbre_supprimer(arbre a, void* val)
{
  noeud *n=arbre_chercher_position(a,val);
  if((*n) != NULL){
    noeud_detruire_simple(n,a->detruire);
  }
}


