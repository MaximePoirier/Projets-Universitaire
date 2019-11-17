#ifndef LISTES_INT
#define LISTES_INT

#include <stdbool.h>


/*! \file listes_int.h
 * \brief Module liste d'entiers.
 *
 * Les listes d'entiers sont codés dans une liste chaînée circulaire dont on connaît la tête.
 * La taille de la liste est aussi enregistrée et n'est jamais recalculée.
 *
 * Afin de parcourir la liste, elle enregistre également une position courante.
 * Le parcours doit toujours être initialisé avant d'être utilisé.
 *
 * \copyright PASD
 * \version 2017
 */


/*!
 * Cette structure permet d'enregistrer une liste d'entiers. 
 * Elle se base sur une autre structure assurant le chaînage et l'enregistrement des valeurs.
 * Ces deux structures sont cachées et ne doivent jamais être manipulées directement.
 */
typedef struct liste_struct * liste;


/*! 
 * Création d'une liste vide. 
 * \return une nouvelle liste vide.
 */
liste liste_creer(void);


/*!
 * Pour savoir si une liste est vide ou non.
 * \param l liste à tester.
 * \return true ssi la liste est vide.
 */
bool liste_est_vide(liste const l);


/*!
 * Destruction de la liste.
 * \param l (pointeur vers la) liste à détruire.
 */
void liste_detruire(liste * const l);


/*! 
 * Insertion en début de liste.
 * À la sortie de la fonction, tete pointe désormais sur ce nouvel élément (prendre en compte le cas de la liste vide).
 * \param l liste où insérer.
 * \param val valeur entière à insérer.
 */
void liste_insertion_debut(liste const l, int const val);

/*! 
 * Insertion en fin de liste.
 * prendre en compte le cas de la liste vide
 * \param l liste où insérer.
 * \param val valeur entière à insérer.
 */
void liste_insertion_fin(liste const l, int const val);

/*! 
 * Insertion après le maillon courant.
 * \param l liste où insérer.
 * \param val valeur entière à insérer.
 */
void liste_insertion_apres(liste const l, int const val);


/*! 
 * Insertion avant le maillon courant.
 * Si tete = courant alors tete doit être mis à jour .
 * \param l liste où insérer.
 * \param val valeur entière à insérer.
 */
void liste_insertion_avant(liste const l, int const val);


/*! 
 * Suppression de l'élément en tete de liste (mettre à jour tete). 
 * \param l liste d'où supprimer.
 */
void liste_suppression_debut(liste const l);


/*! 
 * Suppression de l'élément en pied de liste.
 * \param l liste d'où supprimer.
 */
void liste_suppression_fin(liste const l);


/*! 
 * Suppression de l'élément avant courant s'il existe (tete doit être modifié si nécessaire).
 * \param l liste d'où supprimer.
 */
void liste_suppression_avant(liste const l);


/*! 
 * Suppression de l'élément apres courant s'il existe (tete doit être modifié si nécessaire).
 * \param l liste d'où supprimer.
 */
void liste_suppression_apres(liste const l);


/*! 
 * Affichage des éléments de la liste.
 * Le résultat est de la forme :
 * \verbatim Liste de 5 éléments : [ -1 -3 10 15 20 ]\endverbatim
 * sans passage à la ligne à la fin.
 * \param f flux où imprimer.
 * \param l liste à afficher.
 */
void liste_affichage(FILE * const f, liste const l);


/*! 
 * Initialise courant à tete.
 * \param l liste à parcourir.
 */
void liste_courant_init(liste const l);


/*!
 * Passe courant sur l'élément suivant.
 * \param l liste parcourue.
 */
void liste_courant_suivant(liste const l);


/*! 
 * Décalage de courant de n positions.
 * Si n est négatif, il s'agit de décalage dans l'autre sens.
 * \param n nombre de décalage à faire.
 * \param l liste parcourue.
*/
void liste_decalage(liste const l, int n);


/*!
 * Retourne la taille de la liste.
 * \param l liste sur laquelle porte la demande.
 * \return le nombre d'éléments dans la liste.
 */
int liste_taille(liste const l);


/*!
 * Retourne la valeur de tete.
 * \param l liste sur laquelle porte la demande.
 * \return le nombre d'éléments dans la liste.
 */
int liste_valeur_tete(liste const l);


/*!
 * Retourne la valeur de courant.
 * \param l liste parcourue sur laquelle porte la demande.
 * \return la value de l'élément courant de la liste.
 */
int liste_valeur_courant(liste const l);


/*!
 * Retourne vrai si courant pointe sur tete. 
 * \param l liste sur laquelle porte la demande.
 * \return true si la tete est l'élément courant.
 */
bool liste_est_tete (liste const l);


/*!
 * Retourne vrai si courant pointe sur la fin de la liste. 
 * \param l liste sur laquelle porte la demande.
 * \return true si l'élément courant est le dernier (précédent de tete).
 */
bool liste_est_fin (liste const l);


#endif
