# include <stack>
# include <stdlib.h>

# include <iostream>

# include "loader_evaluator.hpp"
# include "exparith_unary.hpp"
# include "exparith_unary.hpp"
# include "exparith_binary.hpp"
# include "exparith_variable.hpp"

# include <assert.h>

# define NDEBUG 1

using namespace std ; 


string const sign_read_stop = "." ;

// help write a lot of simular cases
# define CASE_UNARY( sign , exp )		\
  else if ( sign == string_read ) {		\
    assert ( 1 <= l . size () ) ;		\
    Expr * f = l.top () ; 			\
    l.pop () ;					\
    l.push ( new exp ( f ) ) ;			\
  }


// help write a lot of simular cases
# define CASE_BINARY( sign , exp )		  

    
Loader_Evaluator :: Loader_Evaluator ( istream & postfixe_stream ) { 
}



/*!
 * This is a simple context evaluation (variables are managed correctly) with the special characteristic that if a value is not valuated, it reads the value of the variable on an input stream.
 */
class Evaluation_Context_IStream
  : public Evaluation_Context_Simple {
  istream & in ;
public :
  Evaluation_Context_IStream ( istream & in )
    : in ( in ) {}
  /*! Read from the map otherwise from the input stream \c in.
   */
  double get_value ( string const & id ) { 
    return NAN ;
  }
  
} ;


double Loader_Evaluator :: evaluate ( istream & in ) {
  Evaluation_Context_IStream ec ( in ) ;
  return evaluate ( ec ) ;
}


double Loader_Evaluator :: evaluate ( Evaluation_Context & ec ) { 
  return NAN ;
}

