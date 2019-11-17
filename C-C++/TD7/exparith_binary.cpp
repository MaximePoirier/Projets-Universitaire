# include "exparith_binary.hpp"


# include <assert.h>
# define NDEBUG 1


using namespace std ;


std :: string const sign_add = "+" ;
std :: string const sign_sub = "-" ;
std :: string const sign_mul = "*" ;
std :: string const sign_div = "/" ;



double Op_Binary :: eval ( Evaluation_Context & ec ) const {
  double left = this->left->eval(ec);
  double right = this->right->eval(ec);
  return compute(left,right);
}


string Op_Binary :: toString () const {
  string s;
  if(this->left->get_priority() < this->get_priority()){
    s = "( " + this->left->toString()+" ) "+this->sign ;
  }
  else{
    s = this->left->toString()+ " " + this->sign;
  }
  if(this->right->get_priority() < this->get_priority()){
    s += " ( " + this->right->toString() + " )";
  }
  else{
    s += " "+this->right->toString();
  }
  return s;
}


# define COMPUTE_BINARY( class , op )				\
    double class :: compute ( double left ,			\
    double right ) const {					\
    return left op right ;						\
  }

COMPUTE_BINARY ( Add , + )
COMPUTE_BINARY ( Sub , - )
COMPUTE_BINARY ( Mul , * )
COMPUTE_BINARY ( Div , / )
