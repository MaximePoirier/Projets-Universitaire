#include <stdlib.h>
#include <ctype.h>
#include "motus.h"
#include "chaine.h"


/*!
 * \file motus.c
 * \brief Les implémentations relatives au jeu Motus
 * \author PASD
 * \version 1.0
 * \date 2017
 *
 * La structure et les fonctions nécessaires au jeu et à l'affichage
 *
 */


struct motus {
  /*! taille des mots du jeu */
  unsigned int t_mot;
  /*! le nombre d'essais autorisés */
  unsigned int nb_essai;
  /*! mot à trouver */
  chaine mot;
  /*! mots proposés */
  chaine propositions;
  /*! le code pour l'affichage */
  chaine resultats; 
};


motus motus_creer(unsigned int _t_mot, unsigned int _nb_essai, char* mot)
{
  // à écrire 
}

void motus_detruire(motus* m)
{
  // à écrire
}


void motus_afficher(motus m, unsigned int numero, bool gagne)
{
  clrscr();   
  unsigned int taille = chaine_extraire_taille(m->propositions);
  unsigned int nb_resultats = taille / (m->t_mot);
  for (unsigned int i=0; i<nb_resultats; i++) {
    for (unsigned int j=0;  j<m->t_mot; j++) {
      char c1 = chaine_extraire_char_i((m->propositions),i*m->t_mot+j);
      char c2 = chaine_extraire_char_i((m->resultats),i*m->t_mot+j);
      switch(c2) {
      case CHAR_BIEN_PLACE:
	couleur("37");
	fprintf(stdout,"%c",c1);
	couleur("0");
	break;
      case CHAR_MAL_PLACE:
	couleur("34");
	fprintf(stdout,"%c",c1);
	couleur("0");
	break;
      case CHAR_NON_PRESENT:
	couleur("31");
	fprintf(stdout,"%c",c1);
	couleur("0");
      }
    }
    fprintf(stdout,"\n");
  }
  for (unsigned int i=nb_resultats; i<m->nb_essai; i++) {
    for (unsigned int j=0;  j<m->t_mot; j++) 
      fprintf(stdout,CHAR_EN_ATTENTE);
    fprintf(stdout,"\n");
  }
  if ((numero-1 != m->nb_essai) && (!gagne))
    fprintf(stdout,"Proposition numéro %d : ",numero);   
}

chaine chaine_code(chaine ch, chaine mot, bool* gagne)
{
  //à écrire
}

void motus_jeu(motus m)
{
  // à écrire
}
