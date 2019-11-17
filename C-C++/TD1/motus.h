#ifndef MOTUS_H
#define MOTUS_H

#include "chaine.h"

/*! \file
 * \brief Mise en place du jeu motus
 *
 * \copyright PASD
 * \version 2017
 */

#define CHAR_BIEN_PLACE '='
#define CHAR_MAL_PLACE '!'
#define CHAR_NON_PRESENT '.'
#define CHAR_EN_ATTENTE '*'

#define clrscr() printf("\033[H\033[2J")
#define couleur(param) printf("\033[%sm",param)

/*!
 * Cette struture permet de créer les différents éléments du jeu
 * cette structure est cachée et ne peut pas être manipulée directement.
 */
typedef struct motus* motus;

/*!
 * Création du jeu
 * \param _t_mot la taille des mots à proposer/deviner
 * \param _nb_essai nombre d'essais possibles avant de perdre
 * \param mot choisi à deviner (la génération de ce mot est à l'extérieur de la fonction)
 * \return le jeu (les données initiales)
 */
motus motus_creer(unsigned int _t_mot, unsigned int _nb_essai, char* mot);

/*!
 *  Pour détruire correctement les allocations
 * \param le jeu à désallouer
 */
void motus_detruire(motus* m);

/*! 
 * Fonction disponible (déjà implémentée) pour afficher le jeu
 * \param m le jeu
 * \param numero le numéro de l'essai
 * \param gagne pour indiquer si le jeu est gagné ou pas
 */
void motus_afficher(motus m, unsigned int numero, bool gagne);

/*!  
 * Fonction de codage pour la comparaison de deux mots
 * \param ch le premier mot
 * \param mot le deuxième mot
 * \return gagne pour indiquer que les deux mots sont identiques
 * \return renvoie le codage de la comparaison (cf exemples dans l'énoncé)
 */
chaine chaine_code(chaine ch, chaine mot, bool* gagne);

/*!
 * Déroulement du jeu
 * \param m la structure du jeu
 */
void motus_jeu(motus m);
  
#endif
