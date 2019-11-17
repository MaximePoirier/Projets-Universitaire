#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "listes_generiques.h"

/*! \file algues.c
 * \brief La structure Algue Anabaena Catenula et son évolution
 *
 * \copyright PASD
 * \version 2017
 */

#define GAUCHE -1
#define DROITE 1
#define T1 4
#define T2 5
#define TMAX 9
#define NBITER 20



typedef struct algue {
  int taille;
  int orientation;
}algue;

algue* algue_init() {
  algue *a;
  a = (algue*)malloc(sizeof(algue));
  a->taille = T1;
  a->orientation = GAUCHE;
  return a;
}

algue* algue_valeur(int _taille, int _orientation)
{
  algue *a;
  a = (algue*)malloc(sizeof(algue));
  a->taille = _taille;
  a->orientation = _orientation;
  return a;
}

/* à définir */
void copier_algue(void* val, void** pt)
{
  *pt = (void*) malloc(sizeof(algue));
  memcpy(*pt,val,sizeof(algue));
  return ;
}

/* à définir */
void afficher_algue(FILE* f, void* val)
{
  fprintf(f, "(%d,%d)",((algue*)val)->taille , ((algue*)val)->orientation );
}

/* à définir */
void detruire_algue(void **pt)
{
  free(*pt);
  *pt=NULL;
  return ;
}

/* à définir */
liste algue_liste_init(void(*_copier)(void* val, void** pt), void(*_afficher)(FILE* f, void* val), void(*_detruire)(void **pt))
{
  liste res = liste_creer(_copier,_afficher,_detruire);
  algue* tmp = algue_init();
  liste_insertion_debut(res,tmp);
  detruire_algue((void**)&tmp);
  return res;
}

/*!
 * une itération consiste à visiter tous les éléments de la liste
 * et à faire évoluer chaque cellule selon la règle (cf énoncé)
*/
void algue_iteration(liste l)
{
  algue* tmp = NULL;
  liste_courant_init(l);
  algue* t = NULL;
  do{
    tmp=liste_valeur_courant(l);
    if(!(tmp->taille < 9 && tmp->taille++)){
      switch(tmp->orientation){
        case GAUCHE:
          t = algue_valeur(5,DROITE);
          liste_insertion_apres(l,t);
          detruire_algue((void**)&t);
          tmp->taille=4;
          liste_courant_suivant(l);
          break;
        case DROITE:
          t = algue_valeur(5,GAUCHE);
          liste_insertion_avant(l,t);
          detruire_algue((void**)&t);
          tmp->taille=4;
          break;
      }
    }
    liste_courant_suivant(l);

  } while(!liste_est_tete(l));
  return ;
}


int main (void)
{
  liste l = algue_liste_init(&copier_algue, &afficher_algue, &detruire_algue);
  FILE* f_out = fopen("test_algues_out.txt","w");

  fprintf(f_out,"la liste initiale\n");
  liste_affichage(f_out,l);
  fprintf(f_out,"\n");
  for (int i=0; i<NBITER ; i++)
    algue_iteration(l);
  fprintf(f_out,"la liste résultat de %d itérations\n",NBITER);
  liste_affichage(f_out,l);
  liste_detruire(&l);
  fclose(f_out);
  return 0 ;
}
