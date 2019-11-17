# ifndef __EXPARITH_UNARY_HPP_
# define __EXPARITH_UNARY_HPP_

/*!
 * \file
 * This module provides the classes to represent unary arithmetical expressions.
 *
 * \pre For all method, no pointer should NULL
 *
 * \author PASD
 * \date 2016
 */

# include "exparith.hpp"


# undef NDEBUG
# include <assert.h>

/*! Symbol for operator \c exp . */
extern std :: string const sign_exp ;
/*! Symbol for operator \c log . */
extern std :: string const sign_log ;


/*Classe qui définit une expression arithmétique unitaire c'est à dire exp ou log*/
/*Hérite de la class abstraite Expr*/
class Op_Unary : public Expr {
  /*! signe de la fonction (log ou exp) sous forme de string pour utilser toString()*/
  std :: string sign ;
  /*! pointeur vers l'expression qui doit être évaluer par log ou exp*/
  Expr * argument ;
public:
  // CONSTRUCTOR
  Op_Unary ( priority_enum priority ,
	     std :: string sign ,
	     Expr * exp )
    : Expr ( priority )
    , sign ( sign )
    , argument ( exp )
  {
    assert ( NULL != exp ) ;
  } ;
  /*! Destructor. */
  ~ Op_Unary () {
    delete argument ;
  } ;
  std :: string  toString () const ;
  double eval ( Evaluation_Context & ec ) const ;
protected :
  /*! Permet d'évaluer l'expression log ou exp */
  /*! retourne la valeur double evalué*/
  virtual double compute ( double x ) const = 0 ;
} ;



//
// Very powerfull, but ugly
// wait till templates
// in C++11 lambda / functional approach even better
//
/*! This is used to defined simpli similar things: classes for binary operators. */
# define CLASS_OP_UNARY( name , priority , sign )	\
  class name : public Op_Unary {			\
  public:						\
  name ( Expr * Exp )					\
    : Op_Unary ( priority ,				\
		 sign ,					\
		 Exp ) {} ;				\
  ~ name () {} ;					\
  protected :						\
  double compute ( double x ) const ;			\
  } ;

CLASS_OP_UNARY ( Exp , priority_exp_log , sign_exp )
CLASS_OP_UNARY ( Log , priority_exp_log , sign_log )



# endif
