# include "exparith_unary.hpp"


# include <assert.h>
# define NDEBUG 1


using namespace std ;



std :: string const sign_exp = "exp" ;

std :: string const sign_log = "log" ;



double Op_Unary :: eval ( Evaluation_Context & ec ) const {
  double val = this->argument->eval(ec);
  return compute(val);
}


string Op_Unary :: toString () const {
  string s;
  s = this->sign + " ( " + this->argument->toString() + " )";
  return s;
}


# define COMPUTE_UNARY( class , op )		\
  double class :: compute ( double x ) const {	\
    return op ( x ) ;				\
  }


COMPUTE_UNARY ( Exp , exp )
COMPUTE_UNARY ( Log, log )
