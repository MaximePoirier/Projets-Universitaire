# ifndef __EXPARITH_BINARY_HPP_
# define __EXPARITH_BINARY_HPP_

/*!
 * \file 
 * This module provides the classes to represent binary arithmetical expressions.
 *
 * \pre For all method, no pointer should NULL
 *
 * \author PASD
 * \date 2016
 */

# include "exparith.hpp"


# undef NDEBUG
# include <assert.h>




/*! Symbol for operator \c + . */
extern std :: string const sign_add ; 
/*! Symbol for operator \c - (binary). */
extern std :: string const sign_sub ; 
/*! Symbol for operator\c * . */
extern std :: string const sign_mul ; 
/*! Symbol for operator \c ∕ . */
extern std :: string const sign_div ; 




/*!Classe qui définit une expression arithmétique binaire c'est à dire qui prend deux arguments (+,-,*,/)  */
class Op_Binary : public Expr {
  /*! signe de la fonction (+,-,*,/) sous forme de string pour utilser toString() */
  std :: string sign ;
  /*! pointeur vers l'expression à gauche (peut être de type variable, constante, ou une autre expression de type unaire ou binaire) */
  Expr * left ; 
  /*! pointeur vers l'expression à droite (peut être de type variable, constante, ou une autre expression de type unaire ou binaire) */
  Expr * right ;
public:
  // CONSTRUCTOR 
  Op_Binary ( priority_enum priority , 
	      std :: string sign , 
	      Expr * left , 
	      Expr * right ) 
    : Expr ( priority ) 
    , sign ( sign ) 
    , left ( left ) 
    , right ( right ) 
  {} ; 
  /*! Destructor. */
  ~ Op_Binary () {
    delete left ; 
    delete right ; 
  } ; 
  std :: string  toString () const ; 
  /*! Ensures that left is evaluated before right. */
  double eval ( Evaluation_Context & ec ) const ;
  /*! Retourne le signe sous forme de string de l'expression binaire */
  std :: string get_sign () const ; 
protected :
  /*! Permet d'évaluer l'expression arithmétique  */
  /*! Retourne la valeur double de l'expression évalué*/
  virtual double compute ( double left ,
			   double right ) const = 0 ;
} ; 



//
// SAME
//
# define CLASS_OP_BINARY( name , priority, sign )	\
  class name : public Op_Binary {			\
  public:						\
  name ( Expr * exp1 ,					\
	 Expr * exp2 )					\
    : Op_Binary ( priority				\
		  , sign				\
		  , exp1				\
		  , exp2 ) {} ;				\
  /*! Destructor. */					\
  ~ name () {} ;					\
  protected :						\
  double compute ( double left ,			\
		   double right ) const ;		\
  } ; 


CLASS_OP_BINARY ( Add , priority_add_sub , sign_add )
CLASS_OP_BINARY ( Sub , priority_add_sub , sign_sub )
CLASS_OP_BINARY ( Mul , priority_mul_div , sign_mul )
CLASS_OP_BINARY ( Div , priority_mul_div , sign_div )



# endif



