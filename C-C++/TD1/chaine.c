#include "chaine.h"

#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <ctype.h>

/*!
 * \file chaine.c
 * \brief Les implémentations relatives à la manipulation des chaînes de caractères
 * \author PASD
 * \version 1.0
 * \date 2017
 *
 * La structure et toutes les fonctions de manipulation des chaines 
 *
 */

struct chaine {
  unsigned int taille;
  char* tab;
};

chaine chaine_creer_vide()
{
  chaine ch= (chaine) malloc(sizeof(struct chaine));
  ch->taille=0;
  ch->tab=(char*)malloc(sizeof(char));
  return ch;
}

chaine chaine_creer_char(char* c)
{
  int t=sizeof(c);
  chaine ch= (chaine) malloc(sizeof(struct chaine));
  ch->taille=t;
  ch->tab=(char*)malloc(t*sizeof(char));
  int i =0;
  while('\0' != c[i]){
    ch->tab[i]=c[i];
    i++;
  }
  return ch;
}

void chaine_detruire(chaine* ch)
{
  if ((*ch)!=NULL) {
    free((*ch)->tab);
    free((*ch));
    (*ch)=NULL;
  }
}


void chaine_afficher(FILE* f, chaine ch)
{
  int i =0;
  while('\0' != ch->tab[i]){
    fprintf(f,"%c",ch->tab[i]);
    i++;
  }
}

unsigned int chaine_extraire_taille(chaine ch)
{
  return ch->taille;
}


bool chaine_est_vide(chaine ch)
{
  if(ch->taille == 0){
    return true;
  }
  else return false;
}

bool chaine_est_egal(chaine ch1, chaine ch2)
{
  int i=0;
  while('\0' != ch1->tab[i]){
    if (ch1->tab[i] == ch2->tab[i]){
      i++;
    }
    else return false;
  }
  return true;
}

void chaine_concatener(chaine ch1, chaine ch2)
{
  char* c=(char*)malloc((ch1->taille)*sizeof(char));
  int k =0;
  while('\0' != ch1->tab[k]){
    c[k]=ch1->tab[k];
    k++;
  }
  int t = ch1->taille;
  free(ch1->tab);
  ch1->tab = (char*)malloc((t+ch2->taille)*sizeof(char));
  ch1->taille = t+ch2->taille;
  int i=0;
  while('\0'!= c[i]){
    ch1->tab[i]=c[i];
    i++;
  }
  int j =0;
  while('\0' != ch2->tab[j]){
    ch1->tab[i]=ch2->tab[j];
    i++;
    j++;
  }
}

char chaine_extraire_char_i(chaine ch, const unsigned int i)
{
  return ch->tab[i];
}

void chaine_modifier_char_i(chaine ch, const unsigned int i, const char c)
{
  ch->tab[i]=c;
}

chaine chaine_copier(chaine ch1)
{
  chaine ch= (chaine) malloc(sizeof(struct chaine));
  ch->tab = (char*)malloc((ch1->taille)*sizeof(char));
  ch->taille=ch1->taille;
  int i =0;
  while('\0' != ch1->tab[i]){
    ch->tab[i]=ch1->tab[i];
    i++;
  }
  return ch;
}

void chaine_en_minuscules(chaine ch)
{
  int i =0;
  while('\0' != ch->tab[i]){
    ch->tab[i]=tolower(ch->tab[i]);
    i++;
  }
}

void chaine_en_majuscules(chaine ch)
{
  int i = 0;
  while('\0' != ch->tab[i]){
    ch->tab[i]=toupper(ch->tab[i]);
    i++;
  }
}

bool chaine_appartenir(const char c, chaine ch, int* i)
{
  int j =0;
  while('\0' != ch->tab[j]){
    if (c == ch->tab[j]){
     *i=j;
      return true;
    }
    else j++;
  }
  return false;
}

chaine chaine_lire(FILE* f, unsigned int taille)
{
  chaine ch= (chaine) malloc(sizeof(struct chaine));
  ch->tab = (char*)malloc((taille)*sizeof(char));
  ch->taille=taille;
  unsigned int i=0;
  while (i < taille){
    fscanf(f,"%c",&ch->tab[i]);
    i++;
  }
  return ch;
}





