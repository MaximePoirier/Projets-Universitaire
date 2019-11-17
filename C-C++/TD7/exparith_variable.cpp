# include "exparith_variable.hpp"


# include <assert.h>
# define NDEBUG 1


using namespace std ;


std :: string const sign_set = ":=" ;


double Variable :: eval ( Evaluation_Context & ec ) const{
  return ec.get_value(this->id);
}


string Variable :: toString () const {
  return this->id ;
}


double Set :: eval ( Evaluation_Context & ec ) const {
  double val = this->value->eval(ec);
  ec.valuate(this->variable->toString(),val);
  return val;
}


string Set :: toString () const {
  string s;
  s = this->variable->toString() + " " + sign_set + " " + this->value->toString();
  return s;
}
